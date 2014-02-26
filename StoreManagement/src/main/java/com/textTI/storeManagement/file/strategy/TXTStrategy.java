package com.textTI.storeManagement.file.strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TXTStrategy implements IReadFileStrategy{

	protected static final Logger logger = LoggerFactory.getLogger(TXTStrategy.class);
	
	@Override
	public String read(File file) {
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			content = sb.toString();
			br.close();
			
			logger.debug("####### reading file:  " + file.getName());
		}catch (FileNotFoundException e){
			e.printStackTrace();
			logger.error("FileNotFoundException ex:" + e.getMessage());
		}
		catch (IOException ex) {
			logger.error("IOException ex: " + ex.getMessage());
		}
		return content;
	}

}
