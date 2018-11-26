package com.qa.CVManager.Interoprability.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.CVFile;
import com.qa.CVManager.Persistence.Domain.User;

@RestController
@RequestMapping("/api/trainee")
public class TraineeRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/{userNameOfUser}")
	public User show(@PathVariable String userNameOfUser) {
		return userService.getUserByUserName(userNameOfUser);
	}
	
	@PostMapping("/cvupload/{cvFileNum}/{idOfUser}")
	public String CVFileUpload(@RequestParam("file") MultipartFile multipart, @PathVariable String idOfUser, @PathVariable String cvFileNum) {
		return userService.uploadCVFileToUser(multipart, idOfUser, cvFileNum);
	}
	
	@DeleteMapping("/cvdelete/{cvFileNum}/{idOfUser}")
	public String CVFileDelete(@PathVariable String idOfUser, @PathVariable String cvFileNum) {
		return userService.deleteCVFileOfUser(idOfUser, cvFileNum);
	}
	
	@GetMapping("/cvdownload/{cvFileNum}/{idOfUser}")
	public CVFile CVFileDownload(@PathVariable String idOfUser, @PathVariable String cvFileNum) {
		return userService.downloadCVFileFromUser(idOfUser, cvFileNum);
	}

}
