package com.lms.user.management;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import com.lms.user.management.buisness.UserCredentialsServices;
import com.lms.user.management.entity.UserCredentials;
import com.lms.user.management.enums.Role;
@SpringBootApplication
@EnableDiscoveryClient
public class LmsUserManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsUserManagementServerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(UserCredentialsServices userService) {
		return args ->{
			UserCredentials user1 = new UserCredentials("zaadoud", "marouan","zaadoud@gmail.com", "077858555", "1234",new HashSet<>(Arrays.asList(Role.ADMIN)));
			UserCredentials user2 = new UserCredentials("zidan", "bsaili","bsaili@gmail.com", "0774568555", "1234",new HashSet<>(Arrays.asList(Role.TEACHER)));
			UserCredentials user3 = new UserCredentials("adam", "achahbar","achahbar@gmail.com", "0799476077", "1234",new HashSet<>(Arrays.asList(Role.STUDENT)));
			userService.addUser(user1);
			userService.addUser(user2);
			userService.addUser(user3);
		};
	}

}
