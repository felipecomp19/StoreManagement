package com.textTI.storeManagement.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImagensManager {

	private final String filePath = "/home/felipe/workspace/StoreManagement/StoreManagement/src/main/webapp/upload/imagens/";

	public void save(List<MultipartFile> files) throws IllegalStateException, IOException {
		File file = null;
		for (MultipartFile multipartFile : files) {
			file = new File(this.filePath + multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
		}
	}
	
	public List<String> getAllImagens(){
		File imgFolder = new File(this.filePath);
		File files[] = imgFolder.listFiles();
		
		List<String> imagens = new ArrayList<String>();
		for (File img : files) {
			imagens.add("/upload/imagens/" + img.getName());
		}
		
		return imagens;
	}
}
