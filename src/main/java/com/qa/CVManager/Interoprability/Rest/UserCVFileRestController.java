package com.qa.CVManager.Interoprability.Rest;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qa.CVManager.Interoprability.Rest.Helpers.RestHelperMethods;
import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserCVFileRestController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/cvupload/{idOfUser}")
	public String singleFileUpload(@RequestParam("file") MultipartFile multipart, @PathVariable String idOfUser) {

		User userObject = RestHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
		if (!RestHelperMethods.isNull(userObject)) {
			try {
				userObject.setCvPDFFile(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
				return "Failed to get Byte data from MultipartFile: " + multipart.getOriginalFilename();
			}
			userRepo.save(userObject);
		} else {
			return "No User Found With ID: " + idOfUser;
		}

		return "Success, added MultipartFile: " + multipart.getOriginalFilename() + ", to User with ID : " + idOfUser;
	}

	@GetMapping("/cvdownload/{idOfUser}")
	public byte[] retrieveFile(@PathVariable String idOfUser) {
		User userObject = RestHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
		if (!RestHelperMethods.isNull(userObject)) {
			Binary cvFile = userObject.getCvPDFFile();
			if (!RestHelperMethods.isNull(cvFile)) {
				return cvFile.getData();
				//RestHelperMethods.writeFileToProjectFolder(cvFile, "test.pdf");
			} else {
				return null;
				//return "CV Doesn't Exist For Download";
			}

		} else {
			return null;
			//return "No User Found With ID: " + idOfUser;
		}

		//return "CV Downaloaded Successfully";
	}

	@GetMapping("/cvdelete/{idOfUser}")
	public String singleFileDelete(@PathVariable String idOfUser) {
		User userObject = RestHelperMethods.getUserIfExistsByUserID(userRepo, idOfUser);
		if (!RestHelperMethods.isNull(userObject)) {
			userObject.setCvPDFFile(null);
			userRepo.save(userObject);
		} else {
			return "No User Found With ID: " + idOfUser;
		}

		return "Success, deleted cv file blonging to User with ID : " + idOfUser;
	}
}
