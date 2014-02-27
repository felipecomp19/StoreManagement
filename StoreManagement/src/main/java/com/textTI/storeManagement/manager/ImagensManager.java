package com.textTI.storeManagement.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.textTI.storeManagement.dao.ImagenDAO;
import com.textTI.storeManagement.model.Imagen;

@Component
public class ImagensManager {

	//private final String filePath = "/home/felipe/workspace/StoreManagement/StoreManagement/src/main/webapp/upload/imagens/";
	@Autowired
	private ImagenDAO imagenDAO;
	
	/**
	 * @param img 
	 * @param files
	 * @param filePath the absolute path to store the imagens
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void save(Imagen img, List<MultipartFile> files, String filePath, String relativePath) throws IllegalStateException, IOException {
		File file = null;
		
		for (MultipartFile multipartFile : files) {
			this.imagenDAO.insert(img);
			
			String extension = this.getExtension(multipartFile.getOriginalFilename());
			String name = img.getName().replace(" ", "").trim();
			String fileName = img.getId() + name + "." + extension;
			file = new File(filePath + fileName.trim());

			multipartFile.transferTo(file);
			
			String fileRelativePath = relativePath + fileName;
			img.setFileName(fileRelativePath);
			this.imagenDAO.update(img);
		}
	}
	
	private String getExtension(String originalFilename) {
		String[] split = originalFilename.split(".");
		
		String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());

		return extension;
	}

	public List<Imagen> getAllImagens(){
		return this.imagenDAO.getAll();
	}
}
