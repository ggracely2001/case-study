package com.cg.usermicroservice.service;

import java.util.ArrayList;

import com.cg.usermicroservice.entity.UserLogin;
import com.cg.usermicroservice.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//it is used to mark the class as a service provider or it provides the control for business functionalities
@Service
public class MentorDetailsService implements UserDetailsService {
	@Autowired
	UserLoginRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserLogin user =repo.findByUsername(username);
		if(user==null)
		{
			return null;
		}
		String name = user.getUsername();
		String pwd = user.getPassword();
		return new User(name, pwd, new ArrayList<>());

	}
}
