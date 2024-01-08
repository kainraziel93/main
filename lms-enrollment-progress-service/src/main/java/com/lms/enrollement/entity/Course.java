package com.lms.enrollement.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {

	@Id
	private int id;
	private String courseName;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Course() {
		
	}

	

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", description=" + description + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

	

}
