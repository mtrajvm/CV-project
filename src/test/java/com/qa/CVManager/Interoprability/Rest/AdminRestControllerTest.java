package com.qa.CVManager.Interoprability.Rest;



import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.qa.CVManager.Business.Service.MongoUserDetailsService;
import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;


public class AdminRestControllerTest {
	
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private AdminRestControlller adminRestControlller;
	
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
		User person = adminRestControlller.show(userName);
		
		assertThat(person.getUserName(), is(userName));
		
		
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
		when(userService.getUser()).thenReturn(userIterable);
		Iterable<User> userIterable1 =  adminRestControlller.user();
		
		assertThat(userIterable1, is(userIterable));		
		
	}
	
	@Test
	public void saveAUser() throws Exception {
		String userName1 = "abbas1.wadiwala@academytrainee.com";
		User user1 = new User(userName1, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		
		when(userRepo.save(user1)).thenReturn(user1);
		when(userService.saveUser(user1)).thenReturn(user1);
		User user2 = adminRestControlller.save(user1);	
		
		assertThat(user2, is(user1));		
		
	}
	
	@Test
	public void updateAUser() throws Exception {
		String userName1 = "abbas1.wadiwala@academytrainee.com";
		User user1 = new User(userName1, passwordEncoder.encode("Welcome1"), "trainee", "abbas", "wadiwala");
		
		
		when(userRepo.save(user1)).thenReturn(user1);
		when(userService.saveUser(user1)).thenReturn(user1);
		User user2 = adminRestControlller.save(user1);
		
		assertThat(user2, is(user1));
		
		String userName2 = "abbas1.wadiwala@academytrainee.com";
		User user3 = new User(userName1, passwordEncoder.encode("Welcome1"), "traineemanager", "abbas", "wadiwala");
		
		
		when(userRepo.save(user3)).thenReturn(user3);
		when(userService.updateUser(userName2, user3)).thenReturn(user3);
		User user4 = adminRestControlller.update(userName2, user3);
		
		assertThat(user4, is(user3));	
		
	}
	
	
	
}
