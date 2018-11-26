package com.qa.CVManager.Interoprability.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.User;

@RestController
@RequestMapping("/api/traineemanager")
public class TraineeManagerRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/trainees")
	public Iterable<User> getTrainees() {
		return userService.getUserByAccountTypeTrainee();
	}	

}
