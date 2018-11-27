package com.qa.CVManager.HelperMethods;

import java.util.Optional;

import com.qa.CVManager.Constants.Constants;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

public class UserHelperMethods {

	static public User getUserIfExistsByUserID(UserRepository userRepo, String idOfUser) {
		Optional<User> optUser = userRepo.findById(idOfUser);
		if (optUser.isPresent()) {
			return optUser.get();
		}
		return null;
	}	
	
	static public User getUserIfExistsByUserName(UserRepository userRepo, String userNameOfUser) {
		Optional<User> optUser = userRepo.findByUserName(userNameOfUser);
		if (optUser.isPresent()) {
			return optUser.get();
		}
		return null;
	}

	static public boolean isNull(Object object) {
		if (object == null) {
			return true;
		}
		return false;
	}

	static public String updateUsername(User userObjectWithNewDetails, User userObjectWithOldDetails) {
		if (!isNull(userObjectWithNewDetails.getUserName())) {
			userObjectWithOldDetails.setUserName(userObjectWithNewDetails.getUserName());
			return "User's userName updated";
		}
		return "No Changes to userName to be made";
	}

	static public String updatePassword(User userObjectWithNewDetails, User userObjectWithOldDetails) {
		if (!isNull(userObjectWithNewDetails.getPassword())) {
			userObjectWithOldDetails.setPassword(userObjectWithNewDetails.getPassword());
			return "User's password updated";
		}
		return "No Changes to password to be made";
	}

	static public String updateAccountType(User userObjectWithNewDetails, User userObjectWithOldDetails) {
		if (!isNull(userObjectWithNewDetails.getAccountType())) {
			userObjectWithOldDetails.setAccountType(userObjectWithNewDetails.getAccountType());
			return "User's accountType updated";
		}
		return "No Changes to accountType to be made";
	}
	
	static public boolean checkIfUserAlreadyExists(UserRepository userRepo , User user) {
		if(isNull(userRepo.findByUserName(user.getUserName()))) {
			return true;
		}		
		return false;
	}
	
	static public boolean checkIfUserNameIsLegit(String userName) {
		if(userName.toLowerCase().contains("@qa.com") || userName.toLowerCase().contains("@academytrainee.com")) {
			return true;
		}		
		return false;
	}
	
	static public String setAccountTypeAccordingToUserNameEmail(User user, String userName) {
		if(userName.toLowerCase().contains("@qa.com")) {
			user.setAccountType(Constants.ACCOUNT_TYPE_SALES);
		}
		else if (userName.toLowerCase().contains("@academytrainee.com")) {
			user.setAccountType(Constants.ACCOUNT_TYPE_TRAINEE);
		}		
		return user.getAccountType();
	}

}
