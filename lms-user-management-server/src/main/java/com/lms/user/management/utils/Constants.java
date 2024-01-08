package com.lms.user.management.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.algorithms.Algorithm;
public class Constants {

	public static final  String[] ALLOWED_ENDPOINTS = {
			"auth/login"
			,"auth/validate"
			,"auth/show_token"
			,"swagger-ui/index.html"
			,"user/add"
			};
	
	private  final  static String secretKey = "zaadoud";
	
	public static final Algorithm ALGORITHM() {
		Algorithm algo = Algorithm.HMAC512(secretKey);
		return algo;
	}
	
	public static boolean containAllowedUri(String requestUri) {
		boolean check = false;
		for(String i : ALLOWED_ENDPOINTS ) {
			if(requestUri.endsWith(i)) {
				check=true;
				break;
			}
		}
		return check;
	}
}
