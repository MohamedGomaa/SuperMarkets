package com.supermarket.project.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.supermarket.project.model.Users;
import com.supermarket.project.repository.UserRepository;



@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	
	@Autowired
	private UserRepository theUserRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users theUser = theUserRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		return UserDetailsImplementation.build(theUser);
	}
	
	

}
