package com.textTI.storeManagement.file.strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TXTStrategy implements IReadFileStrategy{

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
			
			System.out.println("####### reading file:  " + file.getName());
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException ex) {
			System.out.println("IOException ex: " + ex.getMessage());
		}
		return content;
	}

}
