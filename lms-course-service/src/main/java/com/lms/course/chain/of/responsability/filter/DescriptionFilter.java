package com.lms.course.chain.of.responsability.filter;

import java.util.List;
import java.util.stream.Collectors;
import com.lms.course.entity.Course;
import com.lms.course.repository.CourseRepository;

public class DescriptionFilter extends FilterChain {

	private CourseRepository courseRepo;
	private String description;
	
	public DescriptionFilter(String description,CourseRepository courseRepo) {
		this.description = description;
		this.courseRepo = courseRepo;
	}
	
	@Override
	public List<Course> filter() {
		List<Course> courses;
		if(this.getNextFilter() == null) {
			courses = this.courseRepo.findAllByDescriptionContains(description);
			
			
		}
		else {
			courses = this.getNextFilter().filter();
			if(courses !=null) {
				List<Course> filtredCourses = courses.stream().filter(x->x.getDescription().contains(description)).collect(Collectors.toList());
				System.out.println("filtred =>"+filtredCourses );
				return filtredCourses;
			}
			
		}
		
		return courses;
	}
	

}
