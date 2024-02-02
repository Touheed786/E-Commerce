package com.touheed.ecommerceserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touheed.ecommerceserver.entities.Role;

public interface RoleDao extends JpaRepository<Role, String> {
	 public Role findByRoleName(String roleName);
}
