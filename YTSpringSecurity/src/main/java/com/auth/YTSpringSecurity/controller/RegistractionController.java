package com.auth.YTSpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.YTSpringSecurity.model.User;
import com.auth.YTSpringSecurity.model.UserRepository;

@RestController
public class RegistractionController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/register/user")
	public User createUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);		
	}
}
