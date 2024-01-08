package com.lms.enrollement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.enrollement.entity.Course;

public interface CourseRepo  extends JpaRepository<Course, Integer>{

	
}
