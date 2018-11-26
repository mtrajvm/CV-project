package com.qa.CVManager.Interoprability.Rest.Helpers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import com.qa.CVManager.Constants.Constants;
import com.qa.CVManager.Persistence.Domain.CVFile;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

public class RestHelperMethods {

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
	
	static public String uploadCVDependingOnFileNum(User userObject, MultipartFile multipart, String CVFileNum ) {
		switch (CVFileNum) {
		case "1" :
			userObject.setCvFile1(intialiseCVForUpload(multipart));
			break;
		case "2" :
			userObject.setCvFile2(intialiseCVForUpload(multipart));
			break;
		case "3" :
			userObject.setCvFile3(intialiseCVForUpload(multipart));
			break;
		}
		
		return "CV Number: " + CVFileNum + " Uploaded Successfully";
	}
	
	static public String deleteCVDependingOnFileNum(User userObject, String CVFileNum ) {
		switch (CVFileNum) {
		case "1" :
			userObject.setCvFile1(null);
			break;
		case "2" :
			userObject.setCvFile2(null);
			break;
		case "3" :
			userObject.setCvFile3(null);
			break;
		}
		
		return "CV Number: " + CVFileNum + " Deleted Successfully";
	}
	
	static public CVFile downloadCVDependingOnFileNum(User userObject, String CVFileNum ) {
		switch (CVFileNum) {
		case "1" :
			return userObject.getCvFile1();
		case "2" :
			return userObject.getCvFile2();
		case "3" :
			return userObject.getCvFile3();
		}		
		return null;
	}
	
	
	static private CVFile intialiseCVForUpload(MultipartFile multipart) {
		String fullFileName = multipart.getOriginalFilename();
		return new CVFile(getBinaryDataFromMultiPartFile(multipart),
						  getFileNameFromFullFileName(fullFileName), 
						  getFileExtentionFromFullFileName(fullFileName), 
						  new Date(), 
						  Constants.CVFILE_FLAG_DEFUALT_NOT_CHECKED);		
	}
	
	static private String getFileNameFromFullFileName(String fileName) {
		return 	fileName.substring(0, fileName.lastIndexOf("."));		
	}
	
	static private String getFileExtentionFromFullFileName(String fileName) {
		return 	fileName.substring(fileName.lastIndexOf(".") + 1);		
	}
	
	static private Binary getBinaryDataFromMultiPartFile(MultipartFile multipart) {
		try {
			return new Binary(BsonBinarySubType.BINARY, multipart.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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
