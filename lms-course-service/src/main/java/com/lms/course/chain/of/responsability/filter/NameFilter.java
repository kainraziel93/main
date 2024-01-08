package com.lms.course.chain.of.responsability.filter;

import java.util.List;

import com.lms.course.entity.Course;
import com.lms.course.repository.CourseRepository;

public class NameFilter  extends FilterChain{

	private String name;
	private CourseRepository courseRepo;
	public NameFilter() {}

	public NameFilter(String name,CourseRepository courseRepo) {
		this.name = name;
		this.courseRepo = courseRepo;
	}

	@Override
	public List<Course> filter() {
		List<Course> courses;
		if(this.getNextFilter() == null) {
			courses = this.courseRepo.findCourseByCourseNameContains(name);
		}
		else {
			courses = this.getNextFilter().filter();
			if(courses !=null) {
				List<Course> filtredCourses = courses.stream().filter(x->x.getCourseName().contains(name)).toList();
				return filtredCourses;
			}
			
		}
		
		return null;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
