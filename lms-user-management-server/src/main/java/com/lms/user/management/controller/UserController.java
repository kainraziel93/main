package com.lms.user.management.controller;

import java.nio.file.spi.FileSystemProvider;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.user.management.buisness.UserCredentialsServices;
import com.lms.user.management.entity.UserCredentials;
import com.lms.user.management.enums.Role;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserCredentialsServices services;
	
	@GetMapping("users")
	public ResponseEntity<List<UserCredentials>> users(){
		System.out.println("security context =>"+SecurityContextHolder.getContext().getAuthentication());
		return this.services.users();
	}
	
	@GetMapping("email/{email}")
	public ResponseEntity<UserCredentials> userByEmail(@PathVariable String email){
		UserCredentials userCredentials = null;
		User user = (User)SecurityContextHolder.getContext().
				    getAuthentication().getPrincipal();
		if(user.getUsername().equals(email)  || user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
			return this.services.findUserByEmail(email);
		}
		else {
			return new ResponseEntity<UserCredentials>(userCredentials,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
	}
	
	@GetMapping("attach_role_user")
	public ResponseEntity<String> attachRole(@RequestParam String email
			,@RequestParam String roleName){
		return this.services.attachRoleToUser(email, roleName);
	}
	
	@PostMapping("update")
	public ResponseEntity<String> updateUser(@RequestParam String email
			,@RequestBody UserCredentials userCredentials){
		User user = (User)SecurityContextHolder.getContext().
			    getAuthentication().getPrincipal();
		if(user.getUsername().equals(email)) {
			return this.services.updateUser(email,userCredentials);
		}
		else {
			return new ResponseEntity<String>("you do not have the right to access this ressource", HttpStatus.NON_AUTHORITATIVE_INFORMATION); 
		}
	}
	
	@PostMapping("add")
	public ResponseEntity<String> signup(@RequestBody UserCredentials userCredentials){
		return this.services.addUser(userCredentials);
	}
	
	@PostMapping("remove/{email}")
	public ResponseEntity<String> removeUser(@PathVariable String email){
		return this.services.RemoveUser(email);
	}
	
	
	
	
}
