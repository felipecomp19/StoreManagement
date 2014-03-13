package com.textTI.storeManagement.converter;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class BigDecimalConverter implements Converter<String,BigDecimal>{

	protected static final Logger logger = LoggerFactory.getLogger(BigDecimalConverter.class);
	
	@Override
	public BigDecimal convert(String value) {
		
		BigDecimal result = null;
		if(value != null)
		{
			value = value.replace(".", "").replace(",", ".");
			result = new BigDecimal(value);
		}
		
		return result;
	}

}
