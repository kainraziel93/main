package com.lms.enrollement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.enrollement.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

	public Student findStudentByEmail(String email);
}
