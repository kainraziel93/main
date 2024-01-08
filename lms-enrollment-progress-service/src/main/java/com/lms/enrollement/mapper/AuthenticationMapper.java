package com.lms.enrollement.mapper;
import java.util.List;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticationMapper {

	
	private Map<String, Object> decriptedToken;
	
	public AuthenticationMapper(Map<String, Object> decriptedToken) {
		this.decriptedToken = decriptedToken;
	}
		
	public UsernamePasswordAuthenticationToken map() {
		UsernamePasswordAuthenticationToken authentication=null;
		
		try {
			@SuppressWarnings("unchecked")
			List<String> roles=(List<String>)decriptedToken.get("roles");
			String username = (String) decriptedToken.get("username");
			List<SimpleGrantedAuthority> authorities = roles
					                                   .stream()
					                                   .map(x->new SimpleGrantedAuthority(x))
					                                   .toList();
			authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return authentication;
	}
}
