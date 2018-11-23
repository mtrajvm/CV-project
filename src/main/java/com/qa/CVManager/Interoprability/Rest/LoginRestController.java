package com.qa.CVManager.Interoprability.Rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginRestController {
	
	@PostMapping("/login")
	public boolean login() {
		return true;
	}
	
	@PostMapping("/logout")
	public boolean logout() {
		return true;		
	}
}
