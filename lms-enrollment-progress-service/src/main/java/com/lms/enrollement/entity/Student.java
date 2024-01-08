package com.lms.enrollement.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Student {

	@Id
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	
	

	public Student(long id, String firstname, String lastname, String email, String password, String phoneNumber) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	public Student() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+" phoneNumber=" + phoneNumber +  "]";
	}
	
	
}
