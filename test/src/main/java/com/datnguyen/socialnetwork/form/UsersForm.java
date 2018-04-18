package com.datnguyen.socialnetwork.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UsersForm {
	@Size(min=2, max=45, message="Your name should be between 2 - 45 characters.")
	private String userName;	
	@Size(min=1, max=45, message="Your name should be between 1 - 45 characters.")
    private String lastName;
	@Size(min=1, max=45, message="Your name should be between 1 - 45 characters.")	
	private String firstName;  
	@Email(message="Please type correct email")
	@NotEmpty(message="Please Enter email")
	private String email;	
	@Size(min=8, max=45, message="Your password should be between 8 - 45 characters.")
    private String password;	
    private String confirmedPassword;
    private boolean invalidRecaptcha;
     
	public boolean isInvalidRecaptcha() {
		return invalidRecaptcha;
	}
	public void setInvalidRecaptcha(boolean invalidRecaptcha) {
		this.invalidRecaptcha = invalidRecaptcha;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
