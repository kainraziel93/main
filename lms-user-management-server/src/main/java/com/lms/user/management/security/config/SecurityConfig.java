package com.lms.user.management.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lms.user.management.enums.Role;
import com.lms.user.management.security.buisness.CustomUserDetailService;
import com.lms.user.management.security.filter.JwtAuthenticationFilter;
import com.lms.user.management.utils.Constants;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests((req)->
		req
		.requestMatchers("user/users","user/attach_role_user").hasAuthority(Role.ADMIN.name())
		.requestMatchers("user/email/**","user/update")
		.hasAnyAuthority(Role.ADMIN.name(),Role.STUDENT.name(),Role.TEACHER.name())
		.anyRequest().permitAll()
				).addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
				
				.build();
	}
	

}
