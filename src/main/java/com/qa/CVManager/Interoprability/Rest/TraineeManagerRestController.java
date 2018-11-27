package com.qa.CVManager.Interoprability.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.CVManager.Business.Service.UserService;
import com.qa.CVManager.Persistence.Domain.CVFile;
import com.qa.CVManager.Persistence.Domain.User;

@RestController
@RequestMapping("/api/traineemanager")
public class TraineeManagerRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/trainees")
	public Iterable<User> getTrainees() {
		return userService.getUserByAccountTypeTrainee();
	}	
	
	@GetMapping("/trainee/{userNameOfUser}")
	public User show(@PathVariable String userNameOfUser) {
		return userService.getUserByUserName(userNameOfUser);
	}	
	
	@GetMapping("/cvdownload/{cvFileNum}/{idOfUser}")
	public CVFile CVFileDownload(@PathVariable String idOfUser, @PathVariable String cvFileNum) {
		return userService.downloadCVFileFromUser(idOfUser, cvFileNum);
	}
	
	@PutMapping("/cvflag/{cvFileNum}/{idOfUser}/{cvFlagStatus}")
	public String CVFileFlagUpdate(@PathVariable String cvFlagStatus, @PathVariable String idOfUser, @PathVariable String cvFileNum) {
		return userService.updateCVFileFlagForUser(cvFlagStatus, idOfUser, cvFileNum);
	}

}
