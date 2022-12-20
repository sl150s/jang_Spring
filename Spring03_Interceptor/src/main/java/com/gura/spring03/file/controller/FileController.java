package com.gura.spring03.file.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring03.file.dto.FileDto;
/*
 * 
[ spring mvc 파일 업로드 처리 ]

1. pom.xml에 commons-io, commons-fileupload가 dependency에 명시 되어 있어야한다. 
<!-- 파일업로드 처리를 위한 라이브러리 (SmartEditor 에서도 필요함) -->
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.4</version>
		</dependency>
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.3.1</version>
		</dependency>

2. servlet-context.xml에 MultipartResolver bean 설정이 있어야한다. 
3. MultipartFile 객체를 컨트롤러에서 받아서 사용하면 된다. 
 *
 */

@Controller
public class FileController {
	
	@RequestMapping("/file/insertform")
	public String insertform() {
		return "file/insertform";
	}
	
	//1) 전송되는 name="title", name="myFile"을 FileDto 객체에 담아 인자로 사용 하는 방법 ===================
	// FileDto에는 폼 전송된 title, myFile 정보가 들어있다. 
	//FileDto에 필드로 설정해야한다. (private MultipartFile myFile)
	@RequestMapping("file/upload2")
	public String upload2(FileDto dto, HttpServletRequest request) {
		//FileDto로부터 업로드된 파일의 정보를 담고 있는 MultipartFile 객체의 참조값 얻어내기 
		MultipartFile myFile= dto.getMyFile();
		
		//1. 원본 파일명
	      String orgFileName=myFile.getOriginalFilename();
	      //2. 파일의 크기
	      long fileSize=myFile.getSize();
	      //다운시켜주려면 파일의 크기가 필요하다.
	      
	      // webapp/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
	      String realPath=request.getServletContext().getRealPath("/upload");
	      //저장할 파일의 상세 경로(File.separator : 리눅스에선 /, 자바에선 \ 이용)
	      String filePath=realPath+File.separator;
	      //디렉토리를 만들 파일 객체 생성
	      File upload=new File(filePath);
	      if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
	         upload.mkdir(); //만들어 준다.
	      }
	      //저장할 파일 명을 구성한다.
	      String saveFileName=
	            System.currentTimeMillis()+orgFileName;
	      		//System.currentTimeMillis() : 파일 이름 앞에서 숫자 붙이기 
	      		//저장할 파일명을 구성하는데, 구성하기에 앞서 중복을 피하기 위해 랜덤 숫자를 붙인다( System.currentTimeMillis())
	      
	      
	      try {
	         //3. 임시폴더에 저장된 업로드된 파일을 원하는 곳에 원하는 이름으로 이동시키기(전송하기)
	    	 /*
	    	  * - 임시폴더란? 
	    	  * 웹서버는 파일을 전송 받으면 시스템의 temporary 폴더(임시폴더)에 해당파일을 복잡한 
	    	  * 파일명으로 일단 저장해놓는다. 해당 폴더에 저장된 파일은 사용하지 않으면 일정시간 이후에
	    	  * 자동으로 삭제된다. 따라서 해당 파일을 사용하기 위해서는 다른 곳으로 이동시켜야 한다.
	    	  * 이동 시키기 위해서 어떤 경로, 어떤 파일명으로 이동시킬지 결정해야 한다.
	    	  */
	         myFile.transferTo(new File(filePath+saveFileName));
	         System.out.println(filePath+saveFileName);
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      return "file/upload";
	      
	      
	}
	
	
	//2) 전송되는 name="title", name="myFile"을 각각 인자로 받아 사용하는 방법 ============ 
	@RequestMapping("/file/upload")
	public String upload(String title, MultipartFile myFile, HttpServletRequest request ) {
		 //1. 원본 파일명
	      String orgFileName=myFile.getOriginalFilename();
	      //2. 파일의 크기
	      long fileSize=myFile.getSize();
	      //다운시켜주려면 파일의 크기가 필요하다.
	      
	      // webapp/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
	      String realPath=request.getServletContext().getRealPath("/upload");
	      //저장할 파일의 상세 경로(File.separator : 리눅스에선 /, 자바에선 \ 이용)
	      String filePath=realPath+File.separator;
	      //디렉토리를 만들 파일 객체 생성
	      File upload=new File(filePath);
	      if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
	         upload.mkdir(); //만들어 준다.
	      }
	      //저장할 파일 명을 구성한다.
	      String saveFileName=
	            System.currentTimeMillis()+orgFileName;
	      		//System.currentTimeMillis() : 파일 이름 앞에서 숫자 붙이기 
	      		//저장할 파일명을 구성하는데, 구성하기에 앞서 중복을 피하기 위해 랜덤 숫자를 붙인다( System.currentTimeMillis())
	      
	      
	      try {
	         //3. 임시폴더에 저장된 업로드된 파일을 원하는 곳에 원하는 이름으로 이동시키기(전송하기)
	    	 /*
	    	  * - 임시폴더란? 
	    	  * 웹서버는 파일을 전송 받으면 시스템의 temporary 폴더(임시폴더)에 해당파일을 복잡한 
	    	  * 파일명으로 일단 저장해놓는다. 해당 폴더에 저장된 파일은 사용하지 않으면 일정시간 이후에
	    	  * 자동으로 삭제된다. 따라서 해당 파일을 사용하기 위해서는 다른 곳으로 이동시켜야 한다.
	    	  * 이동 시키기 위해서 어떤 경로, 어떤 파일명으로 이동시킬지 결정해야 한다.
	    	  */
	         myFile.transferTo(new File(filePath+saveFileName));
	         System.out.println(filePath+saveFileName);
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      return "file/upload";
	   }

}
