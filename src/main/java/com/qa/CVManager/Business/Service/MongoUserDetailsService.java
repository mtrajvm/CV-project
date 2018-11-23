package com.qa.CVManager.Business.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.qa.CVManager.Interoprability.Rest.Helpers.RestHelperMethods;
import com.qa.CVManager.Persistence.Respository.UserRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		// Conflict
		com.qa.CVManager.Persistence.Domain.User userObject = userRepo.findByUserName(userName);

		if (RestHelperMethods.isNull(userObject)) {
			throw new UsernameNotFoundException("User not found");
		}

		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

		return new User(userObject.getUserName(), userObject.getPassword(), authorities);
	}

}
