package com.touheed.ecommerceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.touheed.ecommerceserver.entities.Role;
import com.touheed.ecommerceserver.services.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/createNewRole")
	public Role createRole(@RequestBody Role role)
	{
		return roleService.createNewRole(role);
	}
}
