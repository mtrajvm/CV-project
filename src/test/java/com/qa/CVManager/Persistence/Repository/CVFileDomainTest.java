package com.qa.CVManager.Persistence.Repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import com.qa.CVManager.Persistence.Domain.CVFile;


public class CVFileDomainTest {
	
	@Test
	public void getFileName() {
		String fileName = "cvfile";
		CVFile cvFile = new CVFile();		
		cvFile.setFileName(fileName);		
		
		assertEquals(fileName, cvFile.getFileName());	
	}
	
	@Test
	public void getFileType() {
		String fileType = "pdf";
		CVFile cvFile = new CVFile();		
		cvFile.setFileType(fileType);		
		
		assertEquals(fileType, cvFile.getFileType());	
	}
	
	@Test
	public void getFileTimeOfUpload() {
		Date fileTimeOfUpload = new Date();
		CVFile cvFile = new CVFile();		
		cvFile.setTimeofUpload(fileTimeOfUpload);	
		
		assertEquals(fileTimeOfUpload, cvFile.getTimeofUpload());	
	}
	
	@Test
	public void getFileFlag() {
		String fileFlag = "secondary";
		CVFile cvFile = new CVFile();		
		cvFile.setFileFlag(fileFlag);	
		
		assertEquals(fileFlag, cvFile.getFileFlag());
	}
}
