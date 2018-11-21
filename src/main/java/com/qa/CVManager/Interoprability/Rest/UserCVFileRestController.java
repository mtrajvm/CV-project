package com.qa.CVManager.Interoprability.Rest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

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

import com.qa.CVManager.Persistence.Domain.User;
import com.qa.CVManager.Persistence.Respository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserCVFileRestController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/cvupload/{idOfUser}")
	public String singleFileUpload(@RequestParam("file") MultipartFile multipart, @PathVariable String idOfUser) {
	
		Optional<User> optUser = userRepo.findById(idOfUser);
		if(optUser.isPresent()) {				
			try {
				User userObject = optUser.get();
				userObject.setCvPDFFile(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
				userRepo.save(userObject);
			} catch (IOException e) {
				e.printStackTrace();
				return "Failed to get Byte data from MultipartFile: " + multipart.getOriginalFilename();
			}
		}
		else {
			return "No User Found With ID: " + idOfUser;
		}
		
		return "Success, added MultipartFile: " + multipart.getOriginalFilename() + ", to User with ID : " + idOfUser;
	}

	@GetMapping("/cvdownload/{idOfUser}")
	public String retrieveFile(@PathVariable String idOfUser) {
		Optional<User> optUser = userRepo.findById(idOfUser);
		if(optUser.isPresent()) {	
			User userObject = optUser.get();
			Binary cvFile = userObject.getCvPDFFile();
			if (cvFile != null) {
				FileOutputStream fileOutputStream = null;
				try {
					fileOutputStream = new FileOutputStream("test.pdf");
					fileOutputStream.write(cvFile.getData());
				} catch (IOException e) {
					e.printStackTrace();
					return "Failure to get data from Binary file";
				} finally {
					if (fileOutputStream != null) {
						try {
							fileOutputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
							return "Failure to close file Output Stream";
						}
					}
				}
			}
			else {
				return "CV Doesn't Exist For Download";
			}
			
		}
		else {
			return "No User Found With ID: " + idOfUser;
		}
		
		return "CV Downaloaded";
	}

	@GetMapping("/cvdelete/{idOfUser}")
	public String singleFileDelete(@PathVariable String idOfUser) {
	
		Optional<User> optUser = userRepo.findById(idOfUser);
		if(optUser.isPresent()) {					
			User userObject = optUser.get();
			userObject.setCvPDFFile(null);
			userRepo.save(userObject);			
		}
		else {
			return "No User Found With ID: " + idOfUser;
		}
		
		return "Success, deleted cv file blonging to User with ID : " + idOfUser;
	}
}
