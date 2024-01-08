package com.lms.user.management.security.buisness;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lms.user.management.entity.UserCredentials;
import com.lms.user.management.enums.Role;
import com.lms.user.management.utils.Constants;
@Service
public class SecurityServicesDefault implements SecurityServices{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Override
	public String generateToken(UserCredentials credentials) {
		System.out.println("dkhel l generate token");
		try {
			Authentication authentication = this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
			
			if(authentication.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
				Set<Role> roles=new HashSet<>(authentication.getAuthorities()
						.stream().map(x->this.GrantedAuthorityToRoleMapper(x)).toList());
				credentials.setRoles(roles);
				return this.createToken(credentials);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		return null;
	}

	@Override
	public String validateToken(String token) {
		try {
			JWT.require(Constants.ALGORITHM()).build().verify(token);
			return "this token is verifieed";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "coudn t verify the token";
	}
	
	public String createToken(UserCredentials credentials) {
		List<String> roles = credentials.getRoles().stream().map(r->r.name()).toList();
		String[] strings = roles.stream().toArray(String[]::new);
		System.out.println(roles);
		return JWT.create()
			    .withIssuer(credentials.getEmail())
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + 60*60*1000))
				.withArrayClaim("roles", strings)
				.sign(Constants.ALGORITHM());
	}

	
	@Override
	public Map<String, Object> showToken(String token) {
		DecodedJWT decodedJwt = JWT.require(Constants.ALGORITHM()).build()
		.verify(token);
		Map<String, Object> registredClaims = new HashMap<>();
		registredClaims.put("issuedAt", decodedJwt.getIssuedAt());
		registredClaims.put("expirationDate",decodedJwt.getExpiresAt());
		registredClaims.put("username",decodedJwt.getIssuer());
		registredClaims.put("roles", decodedJwt.getClaim("roles").asArray(String.class));
		return registredClaims;
	}
	
	public Role GrantedAuthorityToRoleMapper(GrantedAuthority a) {
		Role role;
		String authority = a.getAuthority();
		if(authority.toLowerCase().equals("admin")) {
			role = Role.ADMIN;
		}
		else if(authority.toLowerCase().equals("teacher")) {
			role = Role.TEACHER;
		}
		else {
			role = Role.STUDENT;
		}
		
		return role;
	}
	
	

}
