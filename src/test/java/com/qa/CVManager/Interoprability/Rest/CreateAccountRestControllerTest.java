package com.qa.CVManager.Interoprability.Rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
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

public class CreateAccountRestControllerTest {
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private CreateAccountRestController createAccountRestController;
	
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
	public void saveAUser() throws Exception {
		String userName1 = "abbas1.wadiwala@academytrainee.com";
		User user1 = new User(userName1, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		
		when(userRepo.save(user1)).thenReturn(user1);
		when(userService.saveUser(user1)).thenReturn(user1);
		User user2 = createAccountRestController.save(user1);
				
		assertThat(user2, is(user1));		
		
	}
}
