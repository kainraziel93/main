package com.lms.user.management.security.filter;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.lms.user.management.security.buisness.SecurityServices;
import com.lms.user.management.security.buisness.SecurityServicesDefault;
import com.lms.user.management.utils.Constants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private UserDetailsService userDetailsService;
	private SecurityServices securityServices;
	
	public JwtAuthenticationFilter(UserDetailsService userDetailsService, SecurityServices securityServices) {
		this.userDetailsService = userDetailsService;
		this.securityServices = securityServices;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
			String header = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
			System.out.println(header);
			System.out.println(request.getRequestURI());
			if(header != null) {
				if(header.toLowerCase().startsWith("bearer")
						& !Constants.containAllowedUri(request.getRequestURI())) {
					System.out.println("dkhel hnaaaaaaaaaaaaa");
					String token = header.substring(7);
					Map<String, Object> claims = securityServices.showToken(token);
					User user = (User) userDetailsService.loadUserByUsername((String)claims.get("username"));
					System.out.println(user);
					Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);	
				}
				
			}
			
			filterChain.doFilter(request, response);
			
	}

}
