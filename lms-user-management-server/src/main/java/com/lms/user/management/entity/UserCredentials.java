package com.lms.user.management.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.user.management.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
@Entity
public class UserCredentials {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String firstname;
	private String lastname;
	@Column(unique = true)
	private String email;
	private String password;
	private String phoneNumber;
	@ElementCollection(targetClass =  Role.class,fetch = FetchType.EAGER)
	@JoinTable(name = "tblRoles", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "roleName", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
	
	public UserCredentials() {
		
	}
	
	public UserCredentials(String firstname, String lastname, String email, String phoneNumber, String password,
			Set<Role> roles) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
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
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public long getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserCredentials [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", roles=" + roles + "]";
	}
	
	
	
}
