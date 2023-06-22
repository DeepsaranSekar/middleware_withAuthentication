package com.nuance.middleware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nuance.middleware.filter.CustomAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
  private final CustomAuthenticationFilter customAuthenticationFilter;

  public SecurityConfig(CustomAuthenticationFilter customAuthenticationFilter) {
		this.customAuthenticationFilter = customAuthenticationFilter;
	}
  


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http
     	.csrf().disable()
        .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/**/free/**").permitAll()
        .anyRequest().authenticated();
     return http.build();
  }
  

}