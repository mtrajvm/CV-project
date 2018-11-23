package com.qa.CVManager.Interoprability.Rest.Helpers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.bson.types.Binary;

import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

public class RestHelperMethods {

	static public User getUserIfExists(UserRepository userRepo, String idOfUser) {
		Optional<User> optUser = userRepo.findById(idOfUser);
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

	static public String writeFileToProjectFolder(Binary file, String fileName) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(fileName);
			fileOutputStream.write(file.getData());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Failure to open file to write to";
		} catch (IOException e) {
			e.printStackTrace();
			return "Failure to close file Output Stream";
		}

		return "CV Downaloaded Successfully";
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

}
