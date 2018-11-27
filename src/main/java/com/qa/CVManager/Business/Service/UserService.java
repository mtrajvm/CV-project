package com.qa.CVManager.Business.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qa.CVManager.Constants.Constants;
import com.qa.CVManager.HelperMethods.CVFileHelperMethods;
import com.qa.CVManager.HelperMethods.UserHelperMethods;
import com.qa.CVManager.Persistence.Domain.CVFile;
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
	
	public Iterable<User> getUserByAccountTypeTraineeAndCVFileFlagGreen(){
		return userRepo.findByAccountTypeAndCVFileFlag(Constants.ACCOUNT_TYPE_TRAINEE, 
														Constants.CVFILE_FLAG_CHECKED_PERFECT, Constants.CVFILE_FLAG_CHECKED_NEEDS_REFACTORING,
														Constants.CVFILE_FLAG_CHECKED_PERFECT, Constants.CVFILE_FLAG_CHECKED_NEEDS_REFACTORING,
														Constants.CVFILE_FLAG_CHECKED_PERFECT, Constants.CVFILE_FLAG_CHECKED_NEEDS_REFACTORING);
	}
	
	public User saveUser(User user) {
		if(UserHelperMethods.checkIfUserNameIsLegit(user.getUserName())) {
				if(!UserHelperMethods.checkIfUserAlreadyExists(userRepo, user)) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setUserName(user.getUserName().toLowerCase());
				UserHelperMethods.setAccountTypeAccordingToUserNameEmail(user, user.getUserName());
				userRepo.save(user);
				return user;
			}				
		}				
		return null;		
	}

	public User getUserByID(String idOfUser) {
		return UserHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
	}
	
	public User getUserByUserName(String userNameOfUser) {
		return UserHelperMethods.getUserIfExistsByUserName(userRepo, userNameOfUser);
	}
	

	public User updateUser(String userNameOfUser, User userObjectWithNewDetails) {
		User userObjectWithOldDetails = UserHelperMethods.getUserIfExistsByUserName(userRepo, userNameOfUser);
		if (!UserHelperMethods.isNull(userObjectWithOldDetails)) {
			UserHelperMethods.updateUsername(userObjectWithNewDetails, userObjectWithOldDetails);
			UserHelperMethods.updateAccountType(userObjectWithNewDetails, userObjectWithOldDetails);
			userRepo.save(userObjectWithOldDetails);
			return userObjectWithOldDetails;
		}
		return null;

	}

	public String deleteUser(String userNameOfUser) {
		User userObject = UserHelperMethods.getUserIfExistsByUserName(userRepo, userNameOfUser);
		if (!UserHelperMethods.isNull(userObject)) {
			userRepo.delete(userObject);
		} else {
			return "UserName: " + userNameOfUser + " Doesn't Exist";
		}
		return "Successfully deleted User with UserName: " + userNameOfUser;
	}
	
	public String uploadCVFileToUser(MultipartFile multipart, String idOfUser, String cvFileNum) {
		User userObject = UserHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
		if (!UserHelperMethods.isNull(userObject)) {			
			CVFileHelperMethods.uploadCVDependingOnFileNum(userObject, multipart, cvFileNum);						
			userRepo.save(userObject);
		} else {
			return "No User Found With ID: " + idOfUser;
		}

		return "Success, added MultipartFile: " + multipart.getOriginalFilename() + ", to User with ID : " + idOfUser;
	}
	
	
	public String deleteCVFileOfUser(String idOfUser, String cvFileNum) {
		User userObject = UserHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
		if (!UserHelperMethods.isNull(userObject)) {
			CVFileHelperMethods.deleteCVDependingOnFileNum(userObject, cvFileNum);
			userRepo.save(userObject);
		} else {
			return "No User Found With ID: " + idOfUser;
		}

		return "Success, deleted cv file blonging to User with ID : " + idOfUser;
	}
	
	public CVFile downloadCVFileFromUser(String idOfUser, String cvFileNum) {
		User userObject = UserHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
		if (!UserHelperMethods.isNull(userObject)) {
			return CVFileHelperMethods.downloadCVDependingOnFileNum(userObject, cvFileNum);
		} else {
			return null;
		}
		
	}

	public String updateCVFileFlagForUser(String cvFlagStatus, String idOfUser, String cvFileNum) {
		User userObject = UserHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
		if (!UserHelperMethods.isNull(userObject)) {
			CVFileHelperMethods.updateCVFileFlagDependingOnFileNum(cvFlagStatus, userObject, cvFileNum);
			userRepo.save(userObject);
		} else {
			return "No User Found With ID: " + idOfUser;
		}
		
		return "Success, updated cv file flag blonging to User with ID : " + idOfUser;
	}

	
}
