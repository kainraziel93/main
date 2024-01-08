package com.lms.enrollement.security.filter;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-MANAGEMENT")
public interface AuthenticationObjectProxy {

	@GetMapping("auth/show_token")
	public Map<String, Object> validateToken(@RequestParam("token")String token);
	
}
