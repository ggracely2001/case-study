package com.cg.usermicroservice.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.usermicroservice.service.MentorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//it indicates the java class as a bean or component
@Component 
public class JwtRequestFilter extends OncePerRequestFilter {
	
	//it is used for automatic dependency injection
	
	@Autowired  
	private MentorDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
    //override annotation was used to override an element declared in the superclass
	@Override  
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String authorizationHeader=request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        //authorization header is used to provide credentials that authenticate a user agent with a server
        if (authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
       //doFilter() method is invoked every time when user request to any resource, to which the filter is mapped
       chain.doFilter(request, response);
	}

}