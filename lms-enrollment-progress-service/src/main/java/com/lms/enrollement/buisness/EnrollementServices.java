package com.lms.enrollement.buisness;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.lms.enrollement.entity.Course;
import com.lms.enrollement.entity.Student;

public interface EnrollementServices {

	public ResponseEntity<String> addEnrollement(String studentEmail,int courseId,String date);
	public ResponseEntity<String> removeEnrollement(int EnrollementId);
	public ResponseEntity<List<Student>> showCourseEnrolledStudents(int courseId);
	public ResponseEntity<List<Course>> showStudentCourses(String email);
}
