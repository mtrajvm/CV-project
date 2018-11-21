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

import com.qa.CVManager.Interoprability.Rest.Helpers.RestHelperMethods;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserRestControlller {
	
	@Autowired
	UserRepository userRepo;
		

		@GetMapping("/users")
	    public Iterable<User> user() {
	        return userRepo.findAll();
	    }

	    @PostMapping("/user")
	    public User save(@RequestBody User user) {
	    	userRepo.save(user);
	        return user;
	    }

		@GetMapping("/user/{idOfUser}")
	    public User show(@PathVariable String idOfUser) {
	        return RestHelperMethods.getUserIfExists(userRepo, idOfUser);
	    }

		@PutMapping("/user/{idOfUser}")
	    public User update(@PathVariable String idOfUser, @RequestBody User userObjectWithNewDetails) {
	        
	        User userObjectWithOldDetails = RestHelperMethods.getUserIfExists(userRepo, idOfUser);
	        if(!RestHelperMethods.isNull(userObjectWithOldDetails)) {
		        RestHelperMethods.updateUsername(userObjectWithNewDetails, userObjectWithOldDetails);
		        RestHelperMethods.updatePassword(userObjectWithNewDetails, userObjectWithOldDetails);
		        RestHelperMethods.updateAccountType(userObjectWithNewDetails, userObjectWithOldDetails);
		        userRepo.save(userObjectWithOldDetails);
		        return userObjectWithOldDetails;
	        }	        
	        return null;

	    }

		@DeleteMapping("/user/{idOfUser}")
	    public String delete(@PathVariable String idOfUser) {
			User userObject = RestHelperMethods.getUserIfExists(userRepo, idOfUser);
			if (!RestHelperMethods.isNull(userObject)) {
				userRepo.delete(userObject);
			} 
			else {
				return "User with ID: " + idOfUser + " Doesn't Exist";
			}
	        return "Successfully deleted User with ID: " + idOfUser;
	    }

}
