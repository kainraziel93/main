package com.lms.user.management.security.buisness;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lms.user.management.entity.UserCredentials;
import com.lms.user.management.repository.UserCredentialsRepo;

@Component
public class CustomUserDetailService implements UserDetailsService {

	private UserCredentialsRepo userRepo;
	
	public CustomUserDetailService(UserCredentialsRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials userByEmail = this.userRepo.findUserCredentialsByEmail(username);
		System.out.println("3laaach "+userByEmail);
		User user;
		if(userByEmail != null) {
			List<SimpleGrantedAuthority> authorities = userByEmail.getRoles()
														.stream()
														.map(x->new SimpleGrantedAuthority(x.name()))
														.toList();
			user = new User(userByEmail.getEmail(),userByEmail.getPassword(),authorities);
			
		}
		else {
			user = null;
		}
		return user;
	}
}
