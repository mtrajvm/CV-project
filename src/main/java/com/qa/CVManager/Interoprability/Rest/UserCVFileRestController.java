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

	@PostMapping("/cvupload/{id}")
	public String singleFileUpload(@RequestParam("file") MultipartFile multipart, @PathVariable String id) {

		try {
			Optional<User> optUser = userRepo.findById(id);
			User c = optUser.get();
			c.setCvPDFFile(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			userRepo.save(c);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failure";
		}

		return "Success";
	}

	@GetMapping("/cvdownload/{id}")
	public String retrieveFile(@PathVariable String id) {
		Optional<User> optUser = userRepo.findById(id);
		User c = optUser.get();
		Binary cvFile = c.getCvPDFFile();
		if (cvFile != null) {
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream("test.pdf");
				fileOutputStream.write(cvFile.getData());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failure";
			} finally {
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "Failure";
					}
				}
			}

		}
		else {
			return "CV Doesn't Exist For Download";
		}

		return "CV Downaloaded";
	}

	@GetMapping("/cvdelete/{id}")
	public String singleFileDelete(@PathVariable String id) {
		
		
		Optional<User> optUser = userRepo.findById(id);
		User c = optUser.get();
		c.setCvPDFFile(null);
		userRepo.save(c);

		return "CV Removed";
	}
}
