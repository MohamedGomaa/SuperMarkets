package com.supermarket.project.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.supermarket.project.security.service.UserDetailsServiceImplementation;


public class AuthTokenFilter extends OncePerRequestFilter{

	private static final Logger theLogger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	@Autowired
	private JwtUtils theJwtUtils;
	
	@Autowired
	private UserDetailsServiceImplementation theUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = parseJwt(request);
			
			if(jwt != null && theJwtUtils.validateToken(jwt)) {
				
				String userName = theJwtUtils.getUserNameFromJwtToken(jwt);
				
				UserDetails theUserDetails = theUserDetailsService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(theUserDetails,null, theUserDetails.getAuthorities());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}catch(Exception excep) {
			theLogger.error("Cannot set user authentication: {}", excep);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String parseJwt(HttpServletRequest theRequest) {
		String headerAuth = theRequest.getHeader("Authorization");
		
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		
		return null;
	}
	
	

}
