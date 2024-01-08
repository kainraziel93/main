package com.lms.enrollement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lms.enrollement.security.filter.AuthenticationObjectProxy;
import com.lms.enrollement.security.filter.JwtSecurityFilter;
@Configuration
@EnableWebSecurity
public class SecurityFilterChainBean {

	 @Autowired
	private AuthenticationObjectProxy authenticationObjectProxy;
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.addFilterBefore(new JwtSecurityFilter(authenticationObjectProxy)
						,UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests().requestMatchers("enrollement/add"
						).hasAuthority("ADMIN")
				.anyRequest().permitAll()
				.and()
				.build();
	}
}
