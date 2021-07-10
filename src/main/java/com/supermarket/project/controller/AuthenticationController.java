package com.supermarket.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.supermarket.project.repository.*;
import com.supermarket.project.model.*;
import com.supermarket.project.payload.request.*;
import com.supermarket.project.payload.response.*;
import com.supermarket.project.security.service.UserDetailsImplementation;
import com.supermarket.project.security.jwt.JwtUtils;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager theAuthenticationManager;

	@Autowired
	UserRepository theUserRepository;

	@Autowired
	RoleRepository theRoleRepository;

	@Autowired
	PasswordEncoder theEncoder;

	@Autowired
	JwtUtils theJwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = theAuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = theJwtUtils.generateToken(authentication);

		UserDetailsImplementation theUserDetails = (UserDetailsImplementation) authentication.getPrincipal();

		List<String> roles = theUserDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, theUserDetails.getId(), theUserDetails.getUsername(),
				theUserDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){

		if(theUserRepository.existsByUserName(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username already token!"));
		}
		if(theUserRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		//creating new user's account 
		
		Users newUser = new Users(signupRequest.getUsername()
									,signupRequest.getEmail()
									,theEncoder.encode(signupRequest.getPassword()));
		
		Set<String> strRoles = signupRequest.getRole();
		
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null ) {
			Role userRole = theRoleRepository.findByRoleName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
			
			roles.add(userRole);
		}else {
			
			strRoles.forEach(role ->{
				switch (role) {
				case "admin":
					Role adminRole = theRoleRepository.findByRoleName(ERole.ROLE_ADMIN)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = theRoleRepository.findByRoleName(ERole.ROLE_MODERATOR)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
					roles.add(modRole);
					break;
				default:
					Role userRole = theRoleRepository.findByRoleName(ERole.ROLE_USER)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
					roles.add(userRole);
					break;
				}
			});
		}
		
		
		newUser.setRoles(roles);
		theUserRepository.save(newUser);
		
		return ResponseEntity.ok(new MessageResponse("User successfully registered!"));
	}
}
