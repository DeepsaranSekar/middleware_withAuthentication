package com.nuance.middleware.filter;

import com.nuance.middleware.authentication.CustomAuthentication;
import com.nuance.middleware.security.CustomAuthenticationManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

	@Value("${secret.header.keyName}")
	private String keyName;
	 
	private final CustomAuthenticationManager customAuthenticationManager;

	public CustomAuthenticationFilter(CustomAuthenticationManager customAuthenticationManager) {
		this.customAuthenticationManager = customAuthenticationManager;
	}

   @Override
   protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    // 1. create an authentication object which is not yet authenticated
    // 2. delegate the authentication object to the manager
    // 3. get back the authentication from the manager
    // 4. if the object is authenticated then send request to the next filter in the chain
    String key = String.valueOf(request.getHeader(keyName));
    CustomAuthentication ca = new CustomAuthentication(false, key);

    var a = customAuthenticationManager.authenticate(ca);

    if (a.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(a);
      filterChain.doFilter(request, response); // only when authentication worked
    }
  }
}