package com.lms.user.management.security.buisness;

import java.util.Map;

import com.lms.user.management.entity.UserCredentials;


public interface SecurityServices {

	public String generateToken(UserCredentials credentials);
	public String validateToken(String token);
	public Map<String,Object> showToken(String token);
}
