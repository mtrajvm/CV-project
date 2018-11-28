package com.qa.CVManager.Interoprability.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.CVFile;
import com.qa.CVManager.Persistence.Domain.User;

@RestController
@RequestMapping("/api/sales")
public class SalesRestController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/trainees")
	public Iterable<User> getTraineesWithCVFileFlagGreen() {
		return userService.getUserByAccountTypeTraineeAndCVFileFlagGreen();
	}	
	
	@GetMapping("/trainee/{userNameOfUser}")
	public User show(@PathVariable String userNameOfUser) {
		return userService.getUserByUserName(userNameOfUser);
	}		
	
	@GetMapping("/cvdownload/{cvFileNum}/{idOfUser}")
	public CVFile CVFileDownload(@PathVariable String idOfUser, @PathVariable String cvFileNum) {
		return userService.downloadCVFileFromUser(idOfUser, cvFileNum);
	}

}