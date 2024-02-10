package com.touheed.ecommerceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.touheed.ecommerceserver.entities.User;
import com.touheed.ecommerceserver.services.UserService;

import jakarta.annotation.PostConstruct;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@PostConstruct
	public void initUsersAndRole()
	{
		this.userService.initRolesAndUser();
	}
	
	@PostMapping("/create")
	public User createUser(@RequestBody User user) throws Exception {
		return userService.RegisterUser(user);
	}
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin()
	{
		 return "This URL is only accessible for Admin";
	}
	
	@PreAuthorize("hasAnyRole('Admin','User')")
	@GetMapping("/forUser")
	public String forUser()
	{
		 return "This URL is only accessible for User";
	}
}
