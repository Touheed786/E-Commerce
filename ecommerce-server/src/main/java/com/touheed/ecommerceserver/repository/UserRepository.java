package com.touheed.ecommerceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.touheed.ecommerceserver.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
