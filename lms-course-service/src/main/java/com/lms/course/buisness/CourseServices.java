package com.lms.course.buisness;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.course.entity.Course;

public interface CourseServices {

	public ResponseEntity<List<Course>> getAllCourses();
	public ResponseEntity<Course> courseById(int id);
	//public ResponseEntity <List<Course>> courseByName(String name);
	public ResponseEntity<List<Course>> courseByDescription(String name);
	public ResponseEntity<List<Course>> filterByDate(String filter,String date);
	public ResponseEntity<Course> addCourse(Course course);
	public ResponseEntity<Course> deleteCourse(int courseId);
	public ResponseEntity<Course> updateCourser(int id,Course course);
	public ResponseEntity<List<Course>> filterCourses(String name,String description,String date,String filter);
	
	
}
