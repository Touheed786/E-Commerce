package com.touheed.ecommerceserver.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.touheed.ecommerceserver.dao.RoleDao;
import com.touheed.ecommerceserver.dao.UserDao;
import com.touheed.ecommerceserver.entities.Role;
import com.touheed.ecommerceserver.entities.User;

@Service
public class UserService {
	

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User RegisterUser(User user) throws Exception
	{
		Role role = roleDao.findById("User").get();
		System.out.println(role.getRoleName());
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userDao.save(user);
	}
	
	public void initRolesAndUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("This is Admin Role");
		roleDao.save(adminRole);
		
	
		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly created record");
		roleDao.save(userRole);
		
		
	
		User adminUser = new User();
		
		adminUser.setUserName("admin");
		adminUser.setFirstName("Allen");
		adminUser.setLastName("Mac");
		adminUser.setPassword(getEncodedPassword("admin"));
		
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRoles(adminRoles);
		
		userDao.save(adminUser);
		
		
		User user = new User();
		
		user.setUserName("touheed");
		user.setFirstName("Touheed");
		user.setLastName("Sab");
		user.setPassword(getEncodedPassword("123456"));
		
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		user.setRoles(userRoles);
		
		userDao.save(user);
	}
	
	public String getEncodedPassword(String password)
	{
		return passwordEncoder.encode(password);
	}
}
