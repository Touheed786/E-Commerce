package com.touheed.ecommerceserver.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.touheed.ecommerceserver.configuration.JwtUtil;
import com.touheed.ecommerceserver.entities.JwtRequest;
import com.touheed.ecommerceserver.entities.JwtResponse;
import com.touheed.ecommerceserver.entities.User;
import com.touheed.ecommerceserver.services.JwtUserDetailsService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
//@RequestMapping("/login")
public class AuthenticationController {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	
	@PostMapping("/authenticate")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		return jwtUserDetailsService.createJwtToken(jwtRequest);
	}
}
