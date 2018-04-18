package com.datnguyen.socialnetwork.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;



public class SignupForm {
	
	
	@Size(min=5, max=10, message="Your name should be between 5 - 10 characters.")
	private String name;

	@Min(value=5, message="Please insert at least 5 characters")
	private String lastname;

	@NotNull(message="Please select a password")
	@Length(min=5, max=10, message="Password should be between 5 - 10 charactes")
	private String password;
	
	@Pattern(regexp="[0-9]+", message="Wrong zip!")
	private String zip;
	
	@Pattern(regexp=".+@.+\\..+", message="Wrong email!")
	private String email;
	
	@Range(min=18, message="You cannot subscribe if you are under 18 years old.")
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
}