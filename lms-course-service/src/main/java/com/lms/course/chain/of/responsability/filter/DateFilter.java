package com.lms.course.chain.of.responsability.filter;

import java.time.LocalDate;
import java.util.List;

import com.lms.course.entity.Course;
import com.lms.course.repository.CourseRepository;

public class DateFilter extends FilterChain {

	private String dateFilter;
	private LocalDate date;
	private CourseRepository courseRepo;
	public DateFilter(String dateFilter,LocalDate date,CourseRepository courseRepo) {
		
		this.dateFilter = dateFilter;
		this.date = date;
		this.courseRepo = courseRepo;
	}


	public void setFilter(String filter) {
		this.dateFilter = filter;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public List<Course> filter() {
		List<Course> courses;
		if(this.getNextFilter() == null) {
			courses = filterDate(dateFilter);
		}
		else {
			 courses = this.getNextFilter().filter();
			
			if(courses != null) {
				List<Course> filteredCourse;
				if(dateFilter.equals("before")) {
					filteredCourse =courses.stream().filter(x->x.getStartDate().isBefore(date)).toList();
				}
				else if(dateFilter.equals("after")) {
					filteredCourse =courses.stream().filter(x->x.getStartDate().isAfter(date)).toList();
				}
				else  if(dateFilter.equals("before_end_date")) {
					filteredCourse =courses.stream().filter(x->x.getEndDate().isBefore(date)).toList();;
				}
				else {
					filteredCourse =  null;
				}
				return filteredCourse;
			}
		}
		
		return courses;
	}
	
	public List<Course>  filterDate(String filter){
		List<Course> courses;
			if(dateFilter.equals("before")) {
				courses =this.courseRepo.findAllByStartDateBefore(date);
			}
			else if(dateFilter.equals("after")) {
				courses =this.courseRepo.findAllByStartDateBefore(date);
			}
			else  if(dateFilter.equals("before_end_date")) {
			  courses =this.courseRepo.findAllByEndDateBefore(date);
			}
			else {
				courses =  null;
			}
			return courses;
	}
	
	
}
