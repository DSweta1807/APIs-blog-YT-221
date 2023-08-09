package com.CWS.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.CWS.blog.entity.User;
import com.CWS.blog.exception.ResourceNotFoundException;
import com.CWS.blog.repository.UserRepo;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		email is treated as username here
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new  ResourceNotFoundException("User", "email: "+username, 0)) ;
		
		return user;
	}

}
