package com.qa.CVManager.HelperMethods;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.qa.CVManager.Constants.Constants;
import com.qa.CVManager.Constants.ReturnStrings;
import com.qa.CVManager.Persistence.Domain.User;

public class UserHelperMethodsTest {
	
	@Test
	public void isNull() {

		User user = null;
		boolean isNull = UserHelperMethods.isNull(user);
		
		assertEquals(true, isNull );
	}
	
	@Test
	public void updateUserName() {

		String userName1 = "abbas1.wadiwala@academytrainee.com";
		String userName2 = "abbas2.wadiwala@academytrainee.com";
		User userWithOldDetails = new User();
		userWithOldDetails.setUserName(userName1);
		User userWithNewDetails = new User();
		userWithNewDetails.setUserName(userName2);
		
		String updateStatus = UserHelperMethods.updateUsername(userWithNewDetails, userWithOldDetails);
		assertEquals(ReturnStrings.USERNAME_UPDATED, updateStatus );
		
		userWithOldDetails.setUserName(null);
		String updateStatus2 = UserHelperMethods.updateUsername(userWithOldDetails, userWithNewDetails);
		assertEquals(ReturnStrings.NO_CHANGES_WERE_MADE, updateStatus2 );
		
		
	}
	
	@Test
	public void updatePassword() {

		String password1 = "Welcome1";
		String password2 = "Welcome2";
		User userWithOldDetails = new User();
		userWithOldDetails.setPassword(password1);
		User userWithNewDetails = new User();
		userWithNewDetails.setPassword(password2);
		
		String updateStatus = UserHelperMethods.updatePassword(userWithNewDetails, userWithOldDetails);
		assertEquals(ReturnStrings.PASSWORD_UPDATED, updateStatus );
		
		userWithOldDetails.setPassword(null);
		String updateStatus2 = UserHelperMethods.updatePassword(userWithOldDetails, userWithNewDetails);
		assertEquals(ReturnStrings.NO_CHANGES_WERE_MADE, updateStatus2 );
		
		
	}
	
	@Test
	public void updateAccountType() {

		String accountTypeTrainee = "trainee";
		String AccountTypeTraineeManager = "traineemanager";
		User userWithOldDetails = new User();
		userWithOldDetails.setAccountType(accountTypeTrainee);
		User userWithNewDetails = new User();
		userWithNewDetails.setAccountType(AccountTypeTraineeManager);
		
		String updateStatus = UserHelperMethods.updateAccountType(userWithNewDetails, userWithOldDetails);
		assertEquals(ReturnStrings.ACCOUNTTYPE_UPDATED, updateStatus );
		
		userWithOldDetails.setAccountType(null);
		String updateStatus2 = UserHelperMethods.updateAccountType(userWithOldDetails, userWithNewDetails);
		assertEquals(ReturnStrings.NO_CHANGES_WERE_MADE, updateStatus2 );
		
		
	}
	
	@Test
	public void checkIfUserNameIsAllowed() {
		String allowedUserName = "abbas1.wadiwala@academytrainee.com";
		String disallowedUserName = "abbas1.wadiwala";
		
		boolean isUserNameAllowed = UserHelperMethods.checkIfUserNameIsLegit(allowedUserName);		
		assertEquals(true, isUserNameAllowed);
		
		isUserNameAllowed = UserHelperMethods.checkIfUserNameIsLegit(disallowedUserName);
		assertEquals(false, isUserNameAllowed);		
	}
	
	@Test
	public void setAccountTypeAccordingToUserNameEmail() {
		String userNameTrainee = "abbas1.wadiwala@academytrainee.com";
		String userNameQA = "abbas1.wadiwala@qa.com";
		User user = new User();
		
		String accountType = UserHelperMethods.setAccountTypeAccordingToUserNameEmail(user, userNameTrainee);		
		assertEquals(Constants.ACCOUNT_TYPE_TRAINEE, accountType);		
		
		accountType = UserHelperMethods.setAccountTypeAccordingToUserNameEmail(user, userNameQA);
		assertEquals(Constants.ACCOUNT_TYPE_SALES, accountType);			
	}
	
	@Test
	public void checkIfUserAlreadyExists() {
		String userNameTrainee = "abbas1.wadiwala@academytrainee.com";
		String userNameQA = "abbas1.wadiwala@qa.com";
		User user = new User();
		
		String accountType = UserHelperMethods.setAccountTypeAccordingToUserNameEmail(user, userNameTrainee);		
		assertEquals(Constants.ACCOUNT_TYPE_TRAINEE, accountType);		
		
		accountType = UserHelperMethods.setAccountTypeAccordingToUserNameEmail(user, userNameQA);
		assertEquals(Constants.ACCOUNT_TYPE_SALES, accountType);			
	}
	
	
}
