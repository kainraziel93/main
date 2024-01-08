package com.lms.user.management.buisness;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.user.management.entity.UserCredentials;

public interface UserCredentialsServices {

	public ResponseEntity<String> addUser(UserCredentials user);
	public ResponseEntity<String> RemoveUser(String email);
	public ResponseEntity<UserCredentials> findUserByEmail(String email);
	public ResponseEntity<List<UserCredentials>> users();
	public ResponseEntity<String> updateUser(String email,UserCredentials user);
	public ResponseEntity<String> attachRoleToUser(String email,String role);
	public ResponseEntity<String> removeRoleFromUser(String email,String role);
}
