package com.qa.CVManager.Interoprability.Rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.qa.CVManager.Business.Service.MongoUserDetailsService;
import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Constants.Constants;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

public class TraineeManagerRestControllerTest {
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private TraineeManagerRestController traineeManagerRestController;
	
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
		User person = traineeManagerRestController.show(userName);	
		
		assertThat(person.getUserName(), is(userName));		
		
	}
	
	@Test
	public void getUserByAccountType() throws Exception {
		String userName = "abbas.wadiwala@academytrainee.com";
		User user = new User(userName, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		User userArray[] = {user};
		Iterable<User> usersThatAreTraineesIterable = Arrays.asList(userArray);
						
		when(userRepo.findByAccountType(Constants.ACCOUNT_TYPE_TRAINEE)).thenReturn(usersThatAreTraineesIterable);
		when(userService.getUserByAccountTypeTrainee()).thenReturn(usersThatAreTraineesIterable);
		Iterable<User> usersThatAreTraineesIterable2 = traineeManagerRestController.getTrainees();	
		
		assertThat(usersThatAreTraineesIterable2, is(usersThatAreTraineesIterable));		
		
	}
	
}
