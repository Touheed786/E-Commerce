package com.touheed.ecommerceserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {
	
	@Id
	private String roleName;
	private String roleDescription;
	
	public Role() {
		super();
	}
	public Role(String roleName, String roleDescription) {
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}


	
	
}
