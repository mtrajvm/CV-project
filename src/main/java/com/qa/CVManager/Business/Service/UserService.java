package com.qa.CVManager.Business.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qa.CVManager.Constants.Constants;
import com.qa.CVManager.Interoprability.Rest.Helpers.RestHelperMethods;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Iterable<User> getUser() {
		return userRepo.findAll();
	}
	
	public Iterable<User> getUserByAccountTypeTrainee(){
		return userRepo.findByAccountType(Constants.ACCOUNT_TYPE_TRAINEE);
	}

	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return user;
	}

	public User getUserByID(String idOfUser) {
		return RestHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
	}
	
	public User getUserByUserName(String userNameOfUser) {
		return RestHelperMethods.getUserIfExistsByUserName(userRepo, userNameOfUser);
	}
	

	public User updateUser(String userNameOfUser, User userObjectWithNewDetails) {
		User userObjectWithOldDetails = RestHelperMethods.getUserIfExistsByUserName(userRepo, userNameOfUser);
		if (!RestHelperMethods.isNull(userObjectWithOldDetails)) {
			RestHelperMethods.updateUsername(userObjectWithNewDetails, userObjectWithOldDetails);
			RestHelperMethods.updatePassword(userObjectWithNewDetails, userObjectWithOldDetails);
			RestHelperMethods.updateAccountType(userObjectWithNewDetails, userObjectWithOldDetails);
			userRepo.save(userObjectWithOldDetails);
			return userObjectWithOldDetails;
		}
		return null;

	}

	public String deleteUser(String userNameOfUser) {
		User userObject = RestHelperMethods.getUserIfExistsByUserName(userRepo, userNameOfUser);
		if (!RestHelperMethods.isNull(userObject)) {
			userRepo.delete(userObject);
		} else {
			return "UserName: " + userNameOfUser + " Doesn't Exist";
		}
		return "Successfully deleted User with UserName: " + userNameOfUser;
	}
}
