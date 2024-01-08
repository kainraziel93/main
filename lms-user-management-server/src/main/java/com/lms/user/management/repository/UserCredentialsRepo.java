package com.lms.user.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.user.management.entity.UserCredentials;

public interface UserCredentialsRepo  extends JpaRepository<UserCredentials, Long>{

	public UserCredentials findUserCredentialsByEmail(String email);
}
