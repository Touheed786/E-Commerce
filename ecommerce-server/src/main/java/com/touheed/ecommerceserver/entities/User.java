package com.touheed.ecommerceserver.entities;

import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;


@Entity
public class User {

	@Id
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	
	
	
	public User() {
		super();
	}

	
	public User(String userName, String firstName, String lastName, String password, Set<Role> role) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	
	 @JoinTable(
		        name = "USER_ROLE", 
		        joinColumns = @JoinColumn(name = "role_id"), 
		        inverseJoinColumns = @JoinColumn(name = "user_id")
		    )
	private Set<Role>role;
	
	
	
	
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return role;
	}
	
	public void setRoles(Set<Role> role) {
		this.role = role;
	}


}
