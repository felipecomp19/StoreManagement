package com.textTI.storeManagement.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomFileWriter {
	
	private File fileToWrite;
	
	public CustomFileWriter(File fileToWrite) {
		this.fileToWrite = fileToWrite;
	}

	public void write(String content)
	{
		try {
			// if file doesnt exists, then create it
			if (!this.fileToWrite.exists()) 
				this.fileToWrite.createNewFile();
			
			FileWriter fw = new FileWriter(this.fileToWrite.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
