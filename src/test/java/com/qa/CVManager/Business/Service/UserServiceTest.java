package com.qa.CVManager.Business.Service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
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

import com.qa.CVManager.Constants.Constants;
import com.qa.CVManager.Interoprability.Rest.AdminRestControlller;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

public class UserServiceTest {
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserService userService;
		
	@Mock
	private UserRepository userRepo;
	
	@Mock
	MongoUserDetailsService userDetailsService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getAllUsers() throws Exception {
		String userName1 = "abbas1.wadiwala@academytrainee.com";
		String userName2 = "abbas2.wadiwala@academytrainee.com";
		User user1 = new User(userName1, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		User user2 = new User(userName2, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		User userArray[] = {user1, user2};
		List<User> userList = Arrays.asList(userArray);
		Iterable<User> userIterable = Arrays.asList(userArray);
		
		when(userRepo.findAll()).thenReturn(userList);
		Iterable<User> userIterable1 =  userService.getUser();
		
		assertThat(userIterable1, is(userIterable));				
	}
		
	@Test
	public void getUserByAccountType() throws Exception {
		String userName = "abbas.wadiwala@academytrainee.com";
		User user = new User(userName, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		User userArray[] = {user};
		Iterable<User> usersThatAreTraineesIterable = Arrays.asList(userArray);
						
		when(userRepo.findByAccountType(Constants.ACCOUNT_TYPE_TRAINEE)).thenReturn(usersThatAreTraineesIterable);
		Iterable<User> usersThatAreTraineesIterable2 = userService.getUserByAccountTypeTrainee();	
		
		assertThat(usersThatAreTraineesIterable2, is(usersThatAreTraineesIterable));		
		
	}
	
	@Test
	public void saveAUser() throws Exception {
		String userName1 = "abbas1.wadiwala@academytrainee.com";
		User user1 = new User(userName1, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		
		when(userRepo.save(user1)).thenReturn(user1);
		User user2 = userService.saveUser(user1);	
		
		assertThat(user2, is(user1));		
		
		String userName2 = "abbas1.wadiwala";
		User user3 = new User(userName2, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		when(userRepo.save(user3)).thenReturn(user3);
		User user4 = userService.saveUser(user3);	
		
		assertEquals(null, user4);
		
	}
	
	@Test
	public void updateAUser() throws Exception {
		String userName1 = "abbas1.wadiwala@academytrainee.com";
		User user1 = new User(userName1, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		
		when(userRepo.save(user1)).thenReturn(user1);
		User user2 = userService.saveUser(user1);
		
		assertThat(user2, is(user1));		

		User user3 = new User(userName1, passwordEncoder.encode("Welcome1"), "traineemanager", "abbas", "wadiwala");		
		
		when(userRepo.save(user3)).thenReturn(user3);
		User user4 = userService.updateUser(userName1, user3);
		
		
	}
	
	@Test
	public void getUserByUserName() throws Exception {
		String userName = "abbas.wadiwala@academytrainee.com";
		User user = new User(userName, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		Optional<User> myUser = Optional.of(user);
				
		when(userRepo.findByUserName(userName)).thenReturn(myUser);
		User person = userService.getUserByUserName(userName);	
		
		assertThat(person.getUserName(), is(userName));		
		
	}
	
	
	@Test
	public void getUserByUserID() throws Exception {
		String id = "1";
		User user = new User();
		user.setId(id);
		Optional<User> myUser = Optional.of(user);
				
		when(userRepo.findById(id)).thenReturn(myUser);
		User person = userService.getUserByID(id);	
		
		assertThat(person.getId(), is(id));		
		
	}
	

}
