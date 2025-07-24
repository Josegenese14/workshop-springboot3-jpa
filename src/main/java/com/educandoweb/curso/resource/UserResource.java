package com.educandoweb.curso.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.curso.entities.User;
import com.educandoweb.curso.repositories.UserRepository;
import com.educandoweb.curso.services.UserServices;

@RestController
@RequestMapping(value= "/users")

public class UserResource {

    private final UserRepository userRepository;
	
	@Autowired
	private UserServices service;

    UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	// listar usuarios
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list= service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
