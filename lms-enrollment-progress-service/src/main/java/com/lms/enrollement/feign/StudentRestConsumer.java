package com.lms.enrollement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lms.enrollement.entity.Student;


@FeignClient("user-management")
public interface StudentRestConsumer {

	@GetMapping("user/email/{email}")
	public ResponseEntity<Student> getStudentByEmail(@RequestHeader("Authorization") String token,@PathVariable String email);
}
