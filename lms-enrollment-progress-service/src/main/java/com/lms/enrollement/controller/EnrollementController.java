package com.lms.enrollement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.enrollement.buisness.EnrollementServices;
import com.lms.enrollement.entity.Course;
import com.lms.enrollement.entity.Student;

@RequestMapping("enrollement")
@RestController
public class EnrollementController {

	@Autowired
	private EnrollementServices enrollementService;
	
	@GetMapping("add")
	public ResponseEntity<String> addEnrollement(@RequestParam String email
			,@RequestParam int course
			,@RequestParam String date){
		return this.enrollementService.addEnrollement(email, course,date);
		
	}
	
	@GetMapping("course_enrolled_Student/{course}")
	public ResponseEntity<List<Student>> courseEnrolledStudent(
			@PathVariable int course){
		return this.enrollementService.showCourseEnrolledStudents(course);
	}
	
	@GetMapping(""
			+ "/{email}")
	public ResponseEntity<List<Course>> studentCourseEnrollement(
			@PathVariable String email){
		return this.enrollementService.showStudentCourses(email);
	}
	
}
