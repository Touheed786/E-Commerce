package com.touheed.ecommerceserver.entities;

public class JwtRequest {
	
	private String userName;
	private String Password;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
}
