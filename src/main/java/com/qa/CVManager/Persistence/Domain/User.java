package com.qa.CVManager.Persistence.Domain;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
	@Id
	private String id;
	
	private String userName;
	private String password;
	private String accountType;
	private Binary cvPDFFile;

	
	public User() {
		super();
	}

	public Binary getCvPDFFile() {
		return cvPDFFile;
	}

	public void setCvPDFFile(Binary cvPDFFile) {
		this.cvPDFFile = cvPDFFile;
	}

	public User( String userName, String password, String accountType) {
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
