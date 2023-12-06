package com.cg.adminmicroservice.service;

import java.util.ArrayList;

import com.cg.adminmicroservice.entity.Admin;
import com.cg.adminmicroservice.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//it is used to mark the class as a service provider or it provides the control for business functionalities
@Service
public class AdminDetailsService implements UserDetailsService {
	@Autowired
	AdminRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin =repo.findByUsername(username);
		if(admin==null)
		{
			return null;
		}
		String name = admin.getUsername();
		String pwd = admin.getPassword();
		return new User(name, pwd, new ArrayList<>());

	}
}
