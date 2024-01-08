package com.lms.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.course.buisness.CourseServices;
import com.lms.course.entity.Course;

@RestController
@RequestMapping("course")
public class CourseController {

	@Autowired
	private CourseServices courseServices;
	
	@GetMapping("courses")
	public ResponseEntity<List<Course>> courses(){
		return courseServices.getAllCourses();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Course> courseById(@PathVariable int id){
		return courseServices.courseById(id);
	}
	@GetMapping("course_By_Date")
	public ResponseEntity<List<Course>> courseByDateFilter(@RequestParam("filter") String filter
			,@RequestParam("date") String date){
		return this.courseServices.filterByDate(filter, date);
	}
	
	@GetMapping("course_by_description")
	public ResponseEntity<List<Course>> courseByDescription(@RequestParam("description") String description){
		return this.courseServices.courseByDescription(description);
	}
	/*
	@GetMapping("course_by_name/{name}")
	public ResponseEntity<Course> courseByName(@PathVariable String name){
		return this.courseServices.courseByName(name);
	}*/
	
	@PostMapping("add")
	public ResponseEntity<Course> addCourse(@RequestBody Course course){
		return this.courseServices.addCourse(course);
	}
	
	@PostMapping("update/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable int id,@RequestBody Course course){
		return this.courseServices.updateCourser(id, course);
	}
	
	@PostMapping("remove/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable int id){
		return this.courseServices.deleteCourse(id);
	}
	
	@GetMapping("filter")
	public ResponseEntity<List<Course>> filterCourses(@RequestParam(name="filter",defaultValue="") String filter
			,@RequestParam(name="date",defaultValue ="")String date
			,@RequestParam(name="description",defaultValue ="")String description
			,@RequestParam(name="name",defaultValue ="")String name
			)
	{
		return this.courseServices.filterCourses(name,description,date,filter);
	}
}
