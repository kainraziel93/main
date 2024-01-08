package com.lms.enrollement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.enrollement.entity.Course;
import com.lms.enrollement.entity.Enrollement;
import com.lms.enrollement.entity.Student;

public interface EnrollementRepo  extends JpaRepository<Enrollement, Integer>{
	public List<Enrollement> findAllByStudent(Student student); 
	public List<Enrollement> findAllByCourse(Course course);
}
