package com.lms.course.buisness;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.course.chain.of.responsability.filter.DateFilter;
import com.lms.course.chain.of.responsability.filter.DescriptionFilter;
import com.lms.course.chain.of.responsability.filter.FilterChain;
import com.lms.course.chain.of.responsability.filter.NameFilter;
import com.lms.course.entity.Course;
import com.lms.course.repository.CourseRepository;
@Service
public class CourseServicesDefault implements CourseServices {

	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public ResponseEntity<Course> courseById(int id) {
		Course course;
		HttpStatus status = HttpStatus.OK;
		try {
			course = courseRepo.findById(id).get();
		} catch (Exception e) {
			course =null;
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Course>(course,status);
	}

/**	@Override
	public ResponseEntity <List<Course>> courseByName(String name) {
		List<Course> courses;
		HttpStatus status = HttpStatus.OK;
		try {
			courses = courseRepo.findCourseByCourseName(name);
		} catch (Exception e) {
			courses =null;
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<Course>>(courses,status);
	}
	*/

	@Override
	public ResponseEntity<List<Course>> filterByDate(String filter, String date) {
		List<Course> courses;
		LocalDate dateMap = dateMapper(date);
 			if(filter.equals("before")) {
				courses =this.courseRepo.findAllByStartDateBefore(dateMap);
			}
			else if(filter.equals("after")) {
				courses =this.courseRepo.findAllByStartDateBefore(dateMap);
			}
			else  if(filter.equals("before_end_date")) {
				courses = this.courseRepo.findAllByEndDateBefore(dateMap);;
			}
			else {
				courses =null;
			}
		
		return new ResponseEntity<List<Course>>(courses,courses !=null? HttpStatus.OK:HttpStatus.BAD_REQUEST);
	}

	
	
	public LocalDate dateMapper(String date) {
		System.out.println("dkhel hnaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		String[] stringDate = date.split("-");
		System.out.println("striiiiiiiiiiiiiiiiiiiiii "+stringDate);
		 
		return LocalDate.parse(date);
	}

	@Override
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> courses = this.courseRepo.findAll();
		return new ResponseEntity<List<Course>>(courses,courses !=null? HttpStatus.OK:HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<List<Course>> courseByDescription(String description) {
		
		List<Course> course;
		HttpStatus status = HttpStatus.OK;
		try {
			course = courseRepo.findAllByDescriptionContains(description);
		} catch (Exception e) {
			course =null;
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<Course>>(course,status);
	}

	@Override
	public ResponseEntity<Course> addCourse(Course course) {
		Course courseToAdd = this.courseRepo.save(course);
		
		return new ResponseEntity<Course>(courseToAdd,courseToAdd !=null? HttpStatus.OK:HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Course> deleteCourse(int courseId) {
		Course course = this.courseRepo.findById(courseId).get();
		this.courseRepo.delete(course);
		return new ResponseEntity<Course>(course,course !=null? HttpStatus.OK:HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Course> updateCourser(int id, Course course) {
		Course c = this.courseRepo.findById(id).get();
		Course check;
		if(c!=null) {
			c.setCourseName(course.getCourseName());
			c.setDescription(course.getDescription());
			c.setStartDate(course.getStartDate());
			c.setEndDate(course.getEndDate());
			check = this.courseRepo.save(c);
		}
		else {
			check = null;
		}
		return new ResponseEntity<Course>(c,check==null?HttpStatus.BAD_REQUEST:HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	@Override
	public ResponseEntity<List<Course>> filterCourses(String name, String description, String date, String filter) {
		List<FilterChain> chains =new ArrayList<>();
		if(!name.equals("")) {
			chains.add(new NameFilter(name,courseRepo));
		}
		if(!description.equals("")) {
			chains.add(new DescriptionFilter(description,courseRepo));
		}
		if(!date.equals("")) {
			chains.add(new DateFilter(filter, LocalDate.parse(date),courseRepo));
		}
	
		if(chains !=null) {
			
			FilterChain filterChain = chains.get(0);
			for(int i=0;i<chains.size()-1;i++) {
				filterChain.setNextFilter(chains.get(i+1));
			}
			List<Course> filtredCourses = filterChain.filter();
			return new ResponseEntity<List<Course>>(filtredCourses,HttpStatus.OK);
		}
		
		return null;
	}

	

}
