package com.textTI.storeManagement.model.report.excelView;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.report.IndicatorsSummary;

public class IndicatorsExcelReportView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ExcelReportData reportData = (ExcelReportData) model.get("reportData");
		
		HSSFSheet excelSheet = workbook.createSheet("Indicadores");

		setExcelHeader(excelSheet, reportData.getHeaders());
	    setExcelRows(excelSheet,reportData);
	}

	private void setExcelHeader(HSSFSheet excelSheet, List<String> headers) {
		HSSFRow headerRow = excelSheet.createRow(0);
		
		int cellNum = 0;
		for (String header : headers) {
			headerRow.createCell(cellNum).setCellValue(header);
			cellNum++;
		}
	}
	
	private void setExcelRows(HSSFSheet excelSheet, ExcelReportData reportData) {
		int rowNum = 1;
		for (Indicator indicator : reportData.getIndicators()) {
			//create the row data
			HSSFRow row = excelSheet.createRow(rowNum++);
			row.createCell(0).setCellValue(indicator.getEmployee().getStore().getNameWithDesc());
			row.createCell(1).setCellValue(indicator.getEmployee().getName());
			rowNum++;
		}
	}
}
