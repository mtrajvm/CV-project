package com.qa.CVManager.Persistence.Domain;

import java.util.Date;

import org.bson.types.Binary;

public class CVFile {
	
	private Binary fileBinaryData;
	private String fileName;
	private String fileType;
	private Date timeofUpload;
	private String fileFlag;
	
	public CVFile() {
		super();
	}
	
	public CVFile(Binary fileBinaryData, String fileName, String fileType, Date timeofUpload, String fileFlag) {
		super();
		this.fileBinaryData = fileBinaryData;
		this.fileName = fileName;
		this.fileType = fileType;
		this.timeofUpload = timeofUpload;
		this.fileFlag = fileFlag;
	}

	
	public Binary getFileBinaryData() {
		return fileBinaryData;
	}

	public void setFileBinaryData(Binary fileBinaryData) {
		this.fileBinaryData = fileBinaryData;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getTimeofUpload() {
		return timeofUpload;
	}

	public void setTimeofUpload(Date timeofUpload) {
		this.timeofUpload = timeofUpload;
	}

	public String getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}
	
	
}