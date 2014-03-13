package com.textTI.storeManagement.converter;

import org.springframework.core.convert.converter.Converter;

public class IntConverter implements Converter<String, Integer>{

	@Override
	public Integer convert(String value) {
		
		return Integer.parseInt(value);
	}

}
