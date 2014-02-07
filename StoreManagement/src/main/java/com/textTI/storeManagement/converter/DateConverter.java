package com.textTI.storeManagement.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String,Date>{

	protected static final Logger logger = LoggerFactory.getLogger(DateConverter.class);
	
	@Override
	public Date convert(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		Date result = null; 
		
		try {
			if(stringDate != null && stringDate != "")
				result = sdf.parse(stringDate);
		} catch (ParseException e) {	
			logger.error("Error converting String date to Date in class DateConverter");
			e.printStackTrace();
		}
		
		return result;
	}

}
