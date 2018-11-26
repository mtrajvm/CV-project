package com.qa.CVManager.Interoprability.Rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.User;

@RestController
@RequestMapping("/api/admin")
public class AdminRestControlller {

	@Autowired
	UserService userService;
	
	
	@GetMapping("/users")
	public Iterable<User> user() {
		return userService.getUser();
	}

	@PostMapping("/user")
	public User save(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/user/{userNameOfUser}")
	public User show(@PathVariable String userNameOfUser) {
		return userService.getUserByUserName(userNameOfUser);
	}

	@PutMapping("/user/{userNameOfUser}")
	public User update(@PathVariable String userNameOfUser, @RequestBody User userObjectWithNewDetails) {
		return userService.updateUser(userNameOfUser, userObjectWithNewDetails);
	}

	@DeleteMapping("/user/{userNameOfUser}")
	public String delete(@PathVariable String userNameOfUser) {
		return userService.deleteUser(userNameOfUser);
	}

}
