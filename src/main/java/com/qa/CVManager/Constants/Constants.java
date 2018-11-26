package com.qa.CVManager.Constants;

public class Constants {
	public static final String ACCOUNT_TYPE_ADMIN = "admin";
	public static final String ACCOUNT_TYPE_TRAINEEMANAGER = "traineemanager";
	public static final String ACCOUNT_TYPE_SALES = "sales";
	public static final String ACCOUNT_TYPE_TRAINEE = "trainee";
	
	public static final String MAPPING_PATHS_ADMIN = "/api/admin/**";
	public static final String MAPPING_PATHS_TRAINEEMANGER = "/api/traineemanager/**";
	public static final String MAPPING_PATHS_SALES = "/api/sales/**";
	public static final String MAPPING_PATHS_TRAINEE = "/api/trainee/**";
	
	
	public static final String REACT_LOGIN_PAGE_URL = "http://localhost:3000/";
	public static final String REACT_FAILURE_LOGIN_PAGE_URL = "http://localhost:3000/?error=true";
	
	public static final String CVFILE_FLAG_DEFUALT_NOT_CHECKED = "black";
	public static final String CVFILE_FLAG_CHECKED_NOT_ACCEPTABLE = "red";
	public static final String CVFILE_FLAG_CHECKED_NEED_SREFACTORING = "amber";
	public static final String CVFILE_FLAG_CHECKED_PERFECT = "green";
	
}
