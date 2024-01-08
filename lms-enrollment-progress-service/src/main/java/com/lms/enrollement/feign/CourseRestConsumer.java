package com.lms.enrollement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import com.lms.enrollement.entity.Course;

@FeignClient("LMS-COURSES")
public interface CourseRestConsumer {

	@GetMapping("course/{id}")
	
	public ResponseEntity<Course> courseById(@RequestHeader("Authorization") String token,@PathVariable int id);
}
