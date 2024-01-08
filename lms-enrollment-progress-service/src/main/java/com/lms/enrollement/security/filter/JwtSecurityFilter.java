
package com.lms.enrollement.security.filter;

import java.io.IOException;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lms.enrollement.mapper.AuthenticationMapper;
import com.lms.enrollement.utils.Constants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtSecurityFilter extends OncePerRequestFilter {

	
	private AuthenticationObjectProxy authenticationObkectProxy;
	
	public JwtSecurityFilter(AuthenticationObjectProxy authenticationObjectProxy) {
		this.authenticationObkectProxy = authenticationObjectProxy;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {	
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(header!=null && header.toLowerCase().contains("bearer")) {
			
		  String token = header.substring(7);
		  Map<String,Object> claimsMap = this.authenticationObkectProxy.validateToken(token);
		  AuthenticationMapper authenticationMapper = new AuthenticationMapper(claimsMap);
		  
		  Authentication authentication = authenticationMapper.map();
		  if(authentication !=null) {
			  Constants.TOKEN = header;
			  
			  SecurityContextHolder.getContext().setAuthentication(authentication);
			  System.out.println(authentication);
		  }
		}		
		filterChain.doFilter(request, response);
		
	}


	
}
