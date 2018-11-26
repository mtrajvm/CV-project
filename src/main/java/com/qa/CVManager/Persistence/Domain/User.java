package com.qa.CVManager.Persistence.Domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String id;

	private String userName;
	private String password;
	private String accountType;
	private CVFile cvFile1;
	private CVFile cvFile2;
	private CVFile cvFile3;
	
	
	public CVFile getCvFile1() {
		return cvFile1;
	}


	public void setCvFile1(CVFile cvFile1) {
		this.cvFile1 = cvFile1;
	}


	public CVFile getCvFile2() {
		return cvFile2;
	}


	public void setCvFile2(CVFile cvFile2) {
		this.cvFile2 = cvFile2;
	}


	public CVFile getCvFile3() {
		return cvFile3;
	}


	public void setCvFile3(CVFile cvFile3) {
		this.cvFile3 = cvFile3;
	}


	public User() {
		super();
	}

	
	public User(String userName, String password, String accountType) {
		super();
		this.userName = userName;
		this.password = password;
		this.accountType = accountType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
