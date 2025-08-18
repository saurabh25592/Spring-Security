package com.auth.YTSpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

	@GetMapping("/home")
	public String home() {
		return "home";//html page
	}

	
	@GetMapping("/admin/home")
	public String  handleAdmin() {
		return "admin_home";//html page
	}
	
	@GetMapping("/login")
	public String login() {
		return "custumlogin";//html page
	}

}
