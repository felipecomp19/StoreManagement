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
			
			String fileName = generateFileName(img, multipartFile);
			
			file = new File(filePath + fileName.trim());

			multipartFile.transferTo(file);
			
			//String fileRelativePath = relativePath + fileName;
			img.setFileName(fileName);
			this.imagenDAO.update(img);
		}
	}

	private String generateFileName(Imagen img, MultipartFile multipartFile) {
		String extension = this.getExtension(multipartFile.getOriginalFilename());
		String name = img.getName().replace(" ", "").trim(); //troca espaço por branco (o trim() não estava funcioando)
		String fileName = img.getId() + name + "." + extension;
		return fileName;
	}
	
	private String getExtension(String originalFilename) {
		String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());

		return extension;
	}

	public List<Imagen> getAllImagens(){
		return this.imagenDAO.getAll();
	}

	public Imagen getById(long id) {
		return (Imagen) this.imagenDAO.getById(id, Imagen.class);
	}

	public void delete(Imagen img, String filePath) {
		File imagenFile = new File(filePath + img.getFileName());
		
		if(imagenFile.exists())
			imagenFile.delete();
		this.imagenDAO.delete(img);
	}
}
