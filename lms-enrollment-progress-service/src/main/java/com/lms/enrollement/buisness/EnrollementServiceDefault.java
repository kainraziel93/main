package com.lms.enrollement.buisness;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lms.enrollement.Enum.Progress;
import com.lms.enrollement.entity.Course;
import com.lms.enrollement.entity.Enrollement;
import com.lms.enrollement.entity.Student;
import com.lms.enrollement.feign.CourseRestConsumer;
import com.lms.enrollement.feign.StudentRestConsumer;
import com.lms.enrollement.repository.CourseRepo;
import com.lms.enrollement.repository.EnrollementRepo;
import com.lms.enrollement.repository.StudentRepo;
import com.lms.enrollement.utils.Constants;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class EnrollementServiceDefault implements EnrollementServices {

	@Autowired
	private EnrollementRepo enrollementRepo;
	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private CourseRestConsumer courseConsumer;
	@Autowired
	private StudentRestConsumer studentConsumer;
	@Autowired
	private  EntityManager manager;
	@Override
	@Transactional
	public ResponseEntity<String> addEnrollement(String studentEmail,int courseId,String date) {
		Course  course;
		Student student = this.studentRepo.findStudentByEmail(studentEmail);
		try {
			course = this.courseRepo.findById(courseId).get();
			
		} catch (Exception e) {
			 course = courseConsumer.courseById(Constants.TOKEN,courseId).getBody();
			 manager.persist(course);
		}
		 if(student == null) {
			 student =  studentConsumer.getStudentByEmail(Constants.TOKEN, studentEmail).getBody();
			 manager.persist(student);
		 }
		 System.out.println("hna student "+student);
		if(student!=null & course !=null) {
			Enrollement en= this.addEnrollement(course, student, date);
			this.enrollementRepo.save(en);
			return new ResponseEntity<String>("enrollemet =>"+en+" added suceefully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("error",HttpStatus.BAD_REQUEST);
		}	
		
	}

	@Override
	public ResponseEntity<String> removeEnrollement(int enrollementId) {
		ResponseEntity<String> response;
		try {
			Enrollement enrollement = enrollementRepo.findById(enrollementId).get();
			response=new ResponseEntity<String>("Removed=> "+enrollement,HttpStatus.OK);
		
		} catch (Exception e) {
			response=new ResponseEntity<String>("No enrollement with the id "+enrollementId+" found"
					,HttpStatus.OK);
		}
		return response;
	}

	@Override
	public ResponseEntity<List<Student>> showCourseEnrolledStudents(int courseId) {
		Course course = this.courseRepo.findById(courseId).get();
	    List<Enrollement> enrollementsByCourse=this.enrollementRepo.findAllByCourse(course);
	    List<Student> students = enrollementsByCourse.stream().map(x->x.getStudent()).toList();
	    System.out.println("showCourseEnrolledStudents "+students);
		if( course !=null & enrollementsByCourse !=null & students !=null) {
			return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
		}
		return new ResponseEntity<List<Student>>(students,HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<List<Course>> showStudentCourses(String email) {
		Student student=this.studentRepo.findStudentByEmail(email);
		 List<Enrollement> enrollementsByStudent=this.enrollementRepo.findAllByStudent(student);
		 List<Course> courses = enrollementsByStudent.stream().map(x->x.getCourse()).toList();
		 System.out.println("showStudentCourses "+courses);
		 if( student !=null & enrollementsByStudent !=null & courses !=null) {
				return new ResponseEntity<List<Course>>(courses,HttpStatus.OK);
			}
		 return new ResponseEntity<List<Course>>(courses,HttpStatus.BAD_REQUEST);
	}
	
	public Enrollement addEnrollement(Course course,Student student,String date) {
		Enrollement en= new Enrollement();
		en.setCourse(course);
		en.setStudent(student);
		LocalDate enrollementDate = LocalDate.parse(date);System.out.println("hnaa dtae =>"+enrollementDate);
		if(enrollementDate.isBefore(course.getEndDate())) {System.out.println("dkhel hna");
			en.setEnrollementDate(LocalDate.parse(date));
			if(course.getEndDate().isBefore(LocalDate.now())) {
				if(enrollementDate.isBefore(course.getStartDate())) {
					en.setProgress(Progress.Not_Started);
				}
				else {
					en.setProgress(Progress.Enrolled);
				}
			}
			else {
				en.setProgress(Progress.Completed);
			}
		}
		else {
			return null;
		}
		
		return en;
	}
	
	

}
