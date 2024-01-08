package com.lms.user.management.buisness;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.lms.user.management.entity.UserCredentials;
import com.lms.user.management.enums.Role;
import com.lms.user.management.repository.UserCredentialsRepo;

@Service
public class UserCredentialServicesDefault implements UserCredentialsServices {

	@Autowired
	private UserCredentialsRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public ResponseEntity<String> addUser(UserCredentials user) {
		UserCredentials checkUserExist = this.userRepo.findUserCredentialsByEmail(user.getEmail());
		String message = "user =>"+user+" added succefully";
		HttpStatus status = HttpStatus.OK;
		if(checkUserExist == null) {
			try {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepo.save(user);
			} catch (Exception e) {
				 message = "error adding  =>"+user;
				 status = HttpStatus.BAD_REQUEST;
			}
		}
		else {
			message = "this email has already a registred user email  =>"+user.getEmail();
			 status = HttpStatus.BAD_REQUEST;
		}
		
		
		return new ResponseEntity<String>(message,status);
	}

	@Override
	public ResponseEntity<String> RemoveUser(String email) {
		String message = " user with email => "+email+" removed succefully ";
		HttpStatus status = HttpStatus.OK;
		if(checkUserExist(email)) {
			UserCredentials user = this.userByEmail(email);
			this.userRepo.delete(user);
		}
		else {
			 message = " user with email => "+email+" not found";
			 status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(message,status);
	}

	@Override
	public ResponseEntity<UserCredentials> findUserByEmail(String email) {
		String message;
		HttpStatus status = HttpStatus.OK;
		UserCredentials user = userRepo.findUserCredentialsByEmail(email);
		if(user ==null) {
			message = user+"";
			status = HttpStatus.BAD_REQUEST;
		}
		
		
		
		return new ResponseEntity<UserCredentials>(user,status);
	}

	@Override
	public ResponseEntity<List<UserCredentials>> users() {
		
		return new ResponseEntity<List<UserCredentials>>(this.userRepo.findAll(),HttpStatus.OK);
	}



	@Override
	public ResponseEntity<String> attachRoleToUser(String email, String roleName) {
		UserCredentials u = this.userByEmail(email);
		Role role;
		String message;
		HttpStatus status = HttpStatus.OK;
		if(u !=null) {
			
			role = this.roleMapper(roleName);
			if(role != null) {
				boolean check = u.getRoles().contains(role);
				if(!check) {
					if(u.getRoles()==null) {
						u.setRoles(new HashSet<>());
					}
					u.getRoles().add(role);
					this.userRepo.save(u);
					message = "role attached to the user";
					
				}
				else {
					message="user with email => "+email+" already has the role =>"+roleName;
					status = HttpStatus.BAD_REQUEST;
				}
			}
			else {
				message =" this role does not exist";
				status = HttpStatus.BAD_REQUEST;
			}
		}
		else {
			message =" this email does not exist";
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(message,status);
	}
	
	
	public boolean checkUserExist(String email) {
		 UserCredentials credentials = this.userRepo.findUserCredentialsByEmail(email);
		 return credentials == null? false : true;
	}
	
	public UserCredentials userByEmail(String email) {
		return this.userRepo.findUserCredentialsByEmail(email);
	}

	@Override
	public ResponseEntity<String> updateUser(String email, UserCredentials user) {
		UserCredentials u = this.userByEmail(email);
		String message="user updated succefully";
		HttpStatus status = HttpStatus.OK;
		if(u!=null) {
			u.setFirstname(user.getFirstname());
			u.setLastname(user.getLastname());
			u.setPassword(passwordEncoder.encode(user.getPassword()));
			u.setPhoneNumber(user.getPhoneNumber());
			userRepo.save(u);
		}
		else {
			 message = " email does not exist";
			 status = HttpStatus.BAD_REQUEST;
		}
		 return new ResponseEntity<String>(message,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> removeRoleFromUser(String email, String role) {
		
		return null;
	}
	
	public Role roleMapper(String roleName ) {
		Role role;
		if(roleName.toLowerCase().equals("admin")) {
			role = Role.ADMIN;
		}
		else if(roleName.toLowerCase().equals("teacher")) {
			role = Role.TEACHER;
		}
		else if(roleName.toLowerCase().equals("student")) {
			role = Role.STUDENT;
		} else {
			role = null;
		}
		return role;
	}


	
	

}
