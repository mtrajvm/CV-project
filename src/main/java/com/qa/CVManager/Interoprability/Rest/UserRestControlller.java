package com.qa.CVManager.Interoprability.Rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserRestControlller {
	
	@Autowired
	UserRepository userRepo;
	
		@RequestMapping(method=RequestMethod.GET)
	    public Iterable<User> user() {
	        return userRepo.findAll();
	    }

	    @RequestMapping(method=RequestMethod.POST)
	    public User save(@RequestBody User user) {
	    	userRepo.save(user);

	        return user;
	    }

	    @RequestMapping(method=RequestMethod.GET, value="/{id}")
	    public Optional<User> show(@PathVariable String id) {
	        return userRepo.findById(id);
	    }

	    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
	    public User update(@PathVariable String id, @RequestBody User user) {
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

	    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	    public String delete(@PathVariable String id) {
	        Optional<User> optUser = userRepo.findById(id);
	        User user = optUser.get();
	        userRepo.delete(user);

	        return "";
	    }

}
