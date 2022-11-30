package com.akash.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImpl implements SecurityService {

	private Logger LOGGER=LoggerFactory.getLogger(SecurityServiceImpl.class);
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;
	@Override
	public boolean login(String username, String password) {
		LOGGER.info("Inside SecurityServiceImpl()");
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
		authenticationManager.authenticate(token);

		boolean result=token.isAuthenticated();
		if(result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		return result;
	}

}
