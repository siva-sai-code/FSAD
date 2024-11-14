package com.bookexchange.entity;

public class UserRequest {
	
	private String email;
	private String password;
	private String fullname;
	
	public UserRequest() {
		super();
	}
	
	public UserRequest(String email, String password, String fullname) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
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
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", password=" + password + ", fullname=" + fullname + "]";
	}
 }
