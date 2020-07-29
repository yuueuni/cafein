package com.cafe.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
//	리눅스 기준으로 파일 경로를 작성 ( 루트 경로인 /으로 시작한다. )
//	윈도우라면 workspace의 드라이브를 파악하여 JVM이 알아서 처리해준다.
//	따라서 workspace가 C드라이브에 있다면 C드라이브에 upload 폴더를 생성해 놓아야 한다.
	
	private static final String SAVE_PATH = "/home/ubuntu/images";
//	private static final String SAVE_PATH = "/SSAFY/final_project/yobang_vue/src/assets/images";
	
//	private static final String PREFIX_URL = "/upload/";
	
	public String restore(MultipartFile multipartFile) {
		
		String saveFileName = null;
		
		try {
			String originFilename = multipartFile.getOriginalFilename();
			System.out.println("file name: " + originFilename);
			
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			extName=extName.toLowerCase();
			System.out.println("extension name: " + extName);
			
			Long size = multipartFile.getSize();
			System.out.println("size: " + size);
			
			saveFileName = genSaveFileName(extName);
			System.out.println("save name: " + saveFileName);
			
			System.out.println("RESULT: " + writeFile(multipartFile, saveFileName));
		} 
		catch(IOException e) 
		{
			throw new RuntimeException(e);
		}
		finally {
			System.out.println("FIL");
		}
		
		return saveFileName;
	}
	
	private String genSaveFileName(String extName) {
		
		Calendar cal = Calendar.getInstance();
		
		String name = "";
		name += cal.get(Calendar.YEAR);
		name += cal.get(Calendar.MONTH);
		name += cal.get(Calendar.DATE);
		name += cal.get(Calendar.HOUR);
		name += cal.get(Calendar.MINUTE);
		name += cal.get(Calendar.SECOND);
		name += cal.get(Calendar.MILLISECOND);
		name += extName;
		
		return name;
	}
	
	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException{
		boolean result = false;

		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
		
		return result;
	}
}
