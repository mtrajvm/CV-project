package com.qa.CVManager.Interoprability.Rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserRestControlller {
	
	@Autowired
	UserRepository userRepo;
		

		@GetMapping("/users")
	    public Iterable<User> user() {
			System.out.println("get all");
	        return userRepo.findAll();
	    }

	    @PostMapping("/user")
	    public User save(@Valid @RequestBody User user) {
			System.out.println("post ");
	    	userRepo.save(user);
	        return user;
	    }

		@GetMapping("/user/{id}")
	    public Optional<User> show(@PathVariable String id) {
			System.out.println("get id");
	        return userRepo.findById(id);
	    }

		@PutMapping("/user/{id}")
	    public User update(@PathVariable String id, @RequestBody User user) {
			System.out.println("put");
	        Optional<User> optUser = userRepo.findById(id);
	        User c = optUser.get();
	        if(user.getUserName() != null)
	            c.setUserName(user.getUserName());
	        if(user.getPassword() != null)
	            c.setPassword(user.getPassword());
	        if(user.getAccountType() != null)
	            c.setAccountType(user.getAccountType());
	        userRepo.save(c);
	        return c;
	    }

		@DeleteMapping("/user/{id}")
	    public String delete( @PathVariable String id) {
			System.out.println(id);
			userRepo.deleteById(id);
	        return "";
	    }

}
