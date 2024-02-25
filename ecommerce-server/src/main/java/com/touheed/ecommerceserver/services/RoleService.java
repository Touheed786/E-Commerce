package com.touheed.ecommerceserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touheed.ecommerceserver.entities.Role;
import com.touheed.ecommerceserver.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleDao;
	public Role createNewRole(Role role)
	{
		return roleDao.save(role);
	}
}
