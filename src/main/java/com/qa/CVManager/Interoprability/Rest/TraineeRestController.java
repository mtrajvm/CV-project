package com.qa.CVManager.Interoprability.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.User;

@RestController
@RequestMapping("/api/trainee")
public class TraineeRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/{userNameOfUser}")
	public User show(@PathVariable String userNameOfUser) {
		return userService.getUserByUserName(userNameOfUser);
	}

}
