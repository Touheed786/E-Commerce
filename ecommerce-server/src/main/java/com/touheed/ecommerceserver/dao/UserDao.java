package com.touheed.ecommerceserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touheed.ecommerceserver.entities.User;

public interface UserDao extends JpaRepository<User, String>{

}
