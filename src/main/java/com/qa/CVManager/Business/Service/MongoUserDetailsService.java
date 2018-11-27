package com.qa.CVManager.Business.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.qa.CVManager.HelperMethods.UserHelperMethods;
import com.qa.CVManager.Persistence.Respository.UserRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) {

		// Conflict Of Class User from import springframework and from domain
		com.qa.CVManager.Persistence.Domain.User userObject = UserHelperMethods.getUserIfExistsByUserName(userRepo, userName);
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(userObject.getAccountType()));

		return new User(userObject.getUserName(), userObject.getPassword(), authorities);
	}

}
