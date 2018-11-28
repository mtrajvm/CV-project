package com.qa.CVManager.Interoprability.Rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.qa.CVManager.Business.Service.MongoUserDetailsService;
import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

public class SalesRestControllerTest {
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private SalesRestController salesRestController;
	
	@Mock
	UserService userService;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	MongoUserDetailsService userDetailsService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getUserByUserName() throws Exception {
		String userName = "abbas.wadiwala@academytrainee.com";
		User user = new User(userName, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		Optional<User> myUser = Optional.of(user);
				
		when(userRepo.findByUserName(userName)).thenReturn(myUser);
		when(userService.getUserByUserName(userName)).thenReturn(user);
		User person = salesRestController.show(userName);	
		
		assertThat(person.getUserName(), is(userName));		
		
	}
}
