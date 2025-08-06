package com.jbk.SecurityDemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/home")
	public ResponseEntity<String> home(){
		return ResponseEntity.ok("Hi Welcome to spring Security........");
	}
	
}
