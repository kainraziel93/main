package com.lms.enrollement.entity;

import java.time.LocalDate;
import com.lms.enrollement.Enum.Progress;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "student_id", "course_id" }) })
public class Enrollement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="course_id")
	private Course course;
	@ManyToOne()
	@JoinColumn(name="student_id")
	private Student student;
	private LocalDate enrollementDate;
	@Enumerated(EnumType.STRING)
	private Progress progress;
	
	public Enrollement() {
		
	}

	public Enrollement(int id, Course courses, Student students, LocalDate enrollementDate, Progress progress) {
		super();
		this.id = id;
		this.course = courses;
		this.student = students;
		this.enrollementDate = enrollementDate;
		this.progress = progress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course courses) {
		this.course = courses;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student students) {
		this.student = students;
	}

	public LocalDate getEnrollementDate() {
		return enrollementDate;
	}

	public void setEnrollementDate(LocalDate enrollementDate) {
		this.enrollementDate = enrollementDate;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "Enrollement [id=" + id + ", courses=" + course + ", students=" + student + ", enrollementDate="
				+ enrollementDate + ", progress=" + progress + "]";
	}

	
	
}
	