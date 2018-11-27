package com.qa.CVManager.HelperMethods;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import com.qa.CVManager.Constants.Constants;
import com.qa.CVManager.Persistence.Domain.CVFile;
import com.qa.CVManager.Persistence.Domain.User;

public class CVFileHelperMethods {
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
	
	static public String uploadCVDependingOnFileNum(User userObject, MultipartFile multipart, String cvFileNum ) {
		switch (cvFileNum) {
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
		
		return "CV Number: " + cvFileNum + " Uploaded Successfully";
	}
	
	static public String deleteCVDependingOnFileNum(User userObject, String cvFileNum ) {
		switch (cvFileNum) {
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
		
		return "CV Number: " + cvFileNum + " Deleted Successfully";
	}
	
	static public CVFile downloadCVDependingOnFileNum(User userObject, String cvFileNum ) {
		switch (cvFileNum) {
		case "1" :
			return userObject.getCvFile1();
		case "2" :
			return userObject.getCvFile2();
		case "3" :
			return userObject.getCvFile3();
		}		
		return null;
	}
	
	public static String updateCVFileFlagDependingOnFileNum(String cvFlagStatus, User userObject, String cvFileNum) {
		switch (cvFileNum) {
		case "1" :
			userObject.getCvFile1().setFileFlag(cvFlagStatus);
			break;
		case "2" :
			userObject.getCvFile2().setFileFlag(cvFlagStatus);
			break;
		case "3" :
			userObject.getCvFile3().setFileFlag(cvFlagStatus);
			break;
		}		
		return "CV Number: " + cvFileNum + " updated Successfully to Flag: " + cvFlagStatus;
		
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

	
}
