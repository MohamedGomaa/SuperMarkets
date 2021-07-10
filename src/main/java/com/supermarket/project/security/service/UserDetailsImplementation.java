package com.supermarket.project.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supermarket.project.model.Users;

public class UserDetailsImplementation implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String userName;
	
	private String email;
	
	@JsonIgnore
	private String password; 
	
	private Collection<? extends GrantedAuthority> authorities;

	
	
	public UserDetailsImplementation(Long id, String userName, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImplementation build(Users theUser) {
		List<GrantedAuthority> authorities = theUser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
		
		return new UserDetailsImplementation(
				theUser.getId(),
				theUser.getUserName(),
				theUser.getEmail(),
				theUser.getPassword(),
				authorities);
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object theObject) {
		if(this == theObject) {
			return true;
		}
		if (theObject ==null || getClass() != theObject.getClass()) {
			return false;
		}
		
		UserDetailsImplementation theUser = (UserDetailsImplementation) theObject;
		return Objects.equals(id, theUser.id);
	}

}
