package com.textTI.storeManagement.file;

import java.io.File;
import java.io.FileNotFoundException;

import com.textTI.storeManagement.file.strategy.IReadFileStrategy;

public class CustomFileReader {

	private File folderOrFile;
	private IReadFileStrategy readStrategy;

	public CustomFileReader(File fileOrfolder, IReadFileStrategy readStrategy) {
		super();
		this.folderOrFile = fileOrfolder;
		this.readStrategy = readStrategy;
	}

	public String readFiles() {
		return this.readFilesForFolder(this.folderOrFile);
	}
	
	public String readSingleFile() {
		return this.readStrategy.read(this.folderOrFile);
	}

	/**
	 * 
	 * @param folder
	 * @throws FileNotFoundException
	 * 
	 *             Le o diret�rio passado de forma recursiva. Ou seja, se dentro
	 *             do diret�rio tiver outros diret�rios estes tamb�m ser�o lidos
	 * 
	 */
	private String readFilesForFolder(final File folder) {
		String content = "";
		for (final File file : folder.listFiles()) {
			if (file.isDirectory()) {
				readFilesForFolder(file);
			} else {
				content += this.readStrategy.read(file);
			}
		}
		
		return content;
	}
}
