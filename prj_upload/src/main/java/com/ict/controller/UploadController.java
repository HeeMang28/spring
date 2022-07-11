package com.ict.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
		log.info("upload form");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload_data\\temp";
		// MultipartFile은 폼에서 보낸 파일을 자바 내부에서 명령을 받을 수 있도록 도와줍니다.
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("--------------------");
			log.info("Upload file Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());
			
			// 다시 자바 변수였던 multipartFile을 외부 파일로 변환하기 위해서는
			// new File(저장위치, 원본MultipartFile) 을 이용해야 합니다.
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			} // end try~catch
		} // end for
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		
		log.info("ajax post update!");
		
		String uploadFolder = "C:\\upload_data\\temp";
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("-------------------");
			// \\ 경로가 포함된 파일명
			log.info("Upload file name: " + multipartFile.getOriginalFilename());
			log.info("Upload file size: " + multipartFile.getSize());
			
			 String uploadFileName = multipartFile.getOriginalFilename();
			// \\ 를 빼고 온전한 파일이름만 추출해서 uploadFileName에 저장
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			log.info("last file name : " + uploadFileName);
			
			File saveFile = new File(uploadFolder, uploadFileName);
			 
			try {
				multipartFile.transferTo(saveFile);
			} catch(Exception e) {
				log.error(e.getMessage());
			} 
		} // end for
	}
	
}
