package com.textTI.storeManagement.converter;

import org.springframework.core.convert.converter.Converter;

import com.textTI.storeManagement.model.Report;

public class ReportConverter implements Converter<String, Report>{

	@Override
	public Report convert(String id) {
		Report report = new Report();
		report.setCode(Integer.parseInt(id));

		return report;
	}

}
