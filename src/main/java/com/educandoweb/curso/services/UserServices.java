package com.educandoweb.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.curso.entities.User;
import com.educandoweb.curso.repositories.UserRepository;
import com.educandoweb.curso.services.exceptions.DatabaseException;
import com.educandoweb.curso.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository repository;
	
	//pesquisa e retorna lista de usuarios
	public List<User> findAll(){
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj= repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//retornar usuario salvo
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	//deletar usuario
	public void delete (Long id) {
		
		try {
		repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	//alterar usuario
	@Transactional
	public User update(Long id, User obj) {
	    User entity = findById(id); 
	    updateData(entity, obj);
	    return repository.save(entity);
	
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
