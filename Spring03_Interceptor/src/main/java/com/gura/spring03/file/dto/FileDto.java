package com.gura.spring03.file.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {
	private int num;
	private String title;
	private String orgFileName;
	private String saveFileName;
	
	//*************************************************************
	//업로드된 파일의 정보를 담을 필드 추가 **** 
	private MultipartFile myFile;
	//<input type="file" name="myFile"/>  ---> input name 속성의 value와 일치해야한다.
	//*************************************************************
	
	//디폴트 생성자 
	public FileDto() {}
	public FileDto(int num, String title, String orgFileName, String saveFileName, MultipartFile myFile) {
		super();
		this.num = num;
		this.title = title;
		this.orgFileName = orgFileName;
		this.saveFileName = saveFileName;
		this.myFile = myFile;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public MultipartFile getMyFile() {
		return myFile;
	}
	public void setMyFile(MultipartFile myFile) {
		this.myFile = myFile;
	};
	
	
}
