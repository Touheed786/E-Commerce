	package com.touheed.ecommerceserver.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.touheed.ecommerceserver.configuration.JwtUtil;
import com.touheed.ecommerceserver.dao.UserDao;
import com.touheed.ecommerceserver.entities.JwtRequest;
import com.touheed.ecommerceserver.entities.JwtResponse;
import com.touheed.ecommerceserver.entities.User;


@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception
	{
		String userName = jwtRequest.getUserName();
		String password = jwtRequest.getPassword();
		System.out.println("User data : "+userName+" "+ password);
		authenticates(userName, password);
		final UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		
		User user = userDao.findById(userName).get();
		return new JwtResponse(user, newGeneratedToken);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findById(username).get();
		if(user !=null)
		{
			return new org.springframework.security.core.userdetails.User(user.getUserName(),
					user.getPassword(),
					getAuthorities(user));
		}else {
			throw new UsernameNotFoundException("Username not Valid");
		}
	}
	
	private Set getAuthorities(User user)
	{
		Set authorities = new HashSet();
		
		user.getRoles().forEach((role)->{
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		return authorities;
	}

	private void authenticates(String userName,String password) throws Exception
	{
		try {			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			throw new Exception("User is Disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credential from user");
		}
	}

}
