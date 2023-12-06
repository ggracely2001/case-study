package com.cg.usermicroservice.config;

import com.cg.usermicroservice.service.MentorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	
	//it is used for automatic dependency injection
	@Autowired
	private MentorDetailsService userDetailsService;
	
	//it is used for automatic dependency injection
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	//override annotation was used to override an element declared in the superclass
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	//authorizeRequests is used to provide the authorization for httpservlet request
	//antMatchers is used to  to configure the URL paths from which the Springboot application security should permit requests based on the user's roles
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/v3/api-docs/**","/api/users/reg","/api/users/authenticate","/api/users/**").permitAll().anyRequest().authenticated()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {  //it provides interface for authentication if the input authentication is valid and verified
		return super.authenticationManagerBean();
	}
	
	//to specify that it returns a bean to be managed by Spring context
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
}

