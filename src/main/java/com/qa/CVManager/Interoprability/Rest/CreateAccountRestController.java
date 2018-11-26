package com.qa.CVManager.Interoprability.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.User;

@RestController
@RequestMapping("/api/createaccount")
public class CreateAccountRestController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public User save(@RequestBody User user) {
		return userService.saveUser(user);
	}
}