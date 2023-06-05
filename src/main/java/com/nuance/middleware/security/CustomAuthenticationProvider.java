package com.nuance.middleware.security;

import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	private final HttpServletRequest request;
	
	public CustomAuthenticationProvider(HttpServletRequest request) {
        this.request = request;
    }
	
	private static final String AUTHORIZATION_HEADER_KEY = "X-Authorization";
	private static final String AUTHORIZATION_HEADER_VALUE = "MTIzNDU6MTIzNDU=";

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("Entered Authentication()");
		
		String authorizationHeaderValue = request.getHeader(AUTHORIZATION_HEADER_KEY);
		logger.info("authorizationHeaderValue : " + authorizationHeaderValue);
		logger.info("authorizationHeaderValue.equals(AUTHORIZATION_VALUE)" + authorizationHeaderValue.equals(AUTHORIZATION_HEADER_VALUE));
		
		
		if (authorizationHeaderValue != null && authorizationHeaderValue.equals(AUTHORIZATION_HEADER_VALUE) ) {
			logger.info("Authentication successful for authorizationHeaderValue: ", authorizationHeaderValue);
			
			String[] credentials = new String(Base64.getDecoder().decode(authorizationHeaderValue)).split(":");
			String username = credentials[0];
			String password = credentials[1];
			
			logger.info("username : ["+ username+ "]password : ["+password+"]");
			return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
		}else {
			System.out.println("Invalid authorizationHeaderValue");
			logger.error("Invalid authorizationHeaderValue");
			throw new BadCredentialsException("Invalid authorizationHeaderValue");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}