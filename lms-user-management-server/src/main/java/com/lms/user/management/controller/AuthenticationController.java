package com.lms.user.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.user.management.entity.UserCredentials;
import com.lms.user.management.security.buisness.SecurityServices;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private SecurityServices authenticationService;
	
	@PostMapping("/login")
	public String login(@RequestBody UserCredentials user) {
		
		return this.authenticationService.generateToken(user);
	}
	
	
	@GetMapping ("validate")
	public String validateToken(@RequestParam("token") String token) {
		return this.authenticationService.validateToken(token);
	}
	
	@GetMapping("show_token")
	public Map<String, Object> showToken(@RequestParam("token") String token) {
		
		return this.authenticationService.showToken(token);
	}
}
