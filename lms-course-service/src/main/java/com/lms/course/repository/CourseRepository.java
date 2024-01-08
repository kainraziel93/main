package com.lms.course.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	public List<Course> findAllByDescriptionContains(String description);
	public List<Course> findAllByStartDateBefore(LocalDate date);
	public List<Course> findAllByEndDateBefore(LocalDate date);
	public List<Course> findAllByStartDateAfter(LocalDate date);
	public List<Course> findCourseByCourseNameContains(String name);
}
