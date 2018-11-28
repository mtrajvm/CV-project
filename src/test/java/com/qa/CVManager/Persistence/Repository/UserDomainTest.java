package com.qa.CVManager.Persistence.Repository;

import static org.junit.Assert.*;

import org.junit.Test;

import com.qa.CVManager.Persistence.Domain.CVFile;
import com.qa.CVManager.Persistence.Domain.User;

public class UserDomainTest {
	
	@Test
	public void userGetUserName() {
		String userName1 = "abbas1.wadiwala@academytrainee.com";
		User user1 = new User();
		user1.setUserName(userName1);
		assertEquals(userName1, user1.getUserName());	
	}
	
	@Test
	public void userGetID() {
		String id = "1";
		User user1 = new User();
		user1.setId(id);
		assertEquals(id, user1.getId());	
	}
	
	@Test
	public void userGetPassword() {
		String password = "Welcome1";
		User user1 = new User();
		user1.setPassword(password);
		assertEquals(password, user1.getPassword());		

	}
	
	@Test
	public void userGetAccountType() {
		String accountType = "trainee";
		User user1 = new User();
		user1.setAccountType(accountType);
		assertEquals(accountType, user1.getAccountType());		
	}
	
	@Test
	public void userGetFirstName() {
		String firstName = "abbas";
		User user1 = new User();
		user1.setFirstName(firstName);
		assertEquals(firstName, user1.getFirstName());		
	}
	
	@Test
	public void userGetSurName() {
		String surName = "wadiwala";
		User user1 = new User();
		user1.setSurName(surName);
		assertEquals(surName, user1.getSurName());		
	}
	
	@Test
	public void userGetCVFile1() {
		CVFile cv = null;
		User user1 = new User();
		user1.setCvFile1(cv);
		assertEquals(cv, user1.getCvFile1());		
	}
	
	@Test
	public void userGetCVFile2() {
		CVFile cv = null;
		User user1 = new User();
		user1.setCvFile2(cv);
		assertEquals(cv, user1.getCvFile2());		
	}
	
	@Test
	public void userGetCVFile3() {
		CVFile cv = null;
		User user1 = new User();
		user1.setCvFile2(cv);
		assertEquals(cv, user1.getCvFile3());		
	}
}
