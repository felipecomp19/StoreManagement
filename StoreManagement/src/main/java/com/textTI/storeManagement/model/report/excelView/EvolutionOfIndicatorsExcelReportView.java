package com.textTI.storeManagement.model.report.excelView;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.textTI.storeManagement.model.report.EvolutionOfIndicatorReportData;

public class EvolutionOfIndicatorsExcelReportView extends AbstractExcelView {
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		EvolutionOfIndicatorsExcelReportData reportData = (EvolutionOfIndicatorsExcelReportData) model.get("reportData");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + reportData.getReport().getDescription() + ".xls\"");


		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		
		int sheetNum = 1;
		for (String sheet : reportData.getReportSheets()) {
			HSSFSheet excelSheet = workbook.createSheet(sheet);
			this.setSheetHeader(excelSheet, style, font, reportData.getReportHeaders());
			this.setExcelRows(excelSheet, style, font, reportData, sheetNum);
			sheetNum++;
		}
	}

	private void setSheetHeader(HSSFSheet excelSheet, HSSFCellStyle style, HSSFFont font, List<String> headers) {
		HSSFRow headerRow = excelSheet.createRow(0);
		
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		style.setFillBackgroundColor(new HSSFColor.BLUE().getIndex());
		style.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
		style.setFont(font);
		
		headerRow.setRowStyle(style);
		
		int headerIndex = 0;
		for (String header : headers) {
			HSSFCell cellDate = headerRow.createCell(headerIndex);
			cellDate.setCellStyle(style);
			cellDate.setCellValue(header);

			headerIndex++;
		}
	}
	
	private void setExcelRows(HSSFSheet excelSheet, HSSFCellStyle style, HSSFFont font, EvolutionOfIndicatorsExcelReportData reportData, int sheetNum) {
		
		if(sheetNum == 1)//sheet Achivment of goals
		{
			int rowNum = 1;
			for (EvolutionOfIndicatorReportData employeeData : reportData.getIndicators()) {
				HSSFRow row = excelSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(employeeData.getEmployee().getStore().getNameWithDesc());
				row.createCell(1).setCellValue(employeeData.getEmployee().getName());
				int column = 2;
				for (BigDecimal value : employeeData.getAchievementOfGoalsMapValues()) {
					row.createCell(column).setCellValue(value.toString());
					column++;
				}
			}

			HSSFRow row = excelSheet.createRow(rowNum++);
			this.setExcelTotalsRow(row,style,font, reportData.getTotals().getAchievementOfGoalsMapValues());
			
		}else if(sheetNum == 2)//sheet Average value of the product
		{
			int rowNum = 1;
			for (EvolutionOfIndicatorReportData employeeData : reportData.getIndicators()) {
				HSSFRow row = excelSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(employeeData.getEmployee().getStore().getNameWithDesc());
				row.createCell(1).setCellValue(employeeData.getEmployee().getName());
				int column = 2;
				for (BigDecimal value : employeeData.getAverageValueOfTheProductMapValues()) {
					row.createCell(column).setCellValue(value.toString());
					column++;
				}
			}
			HSSFRow row = excelSheet.createRow(rowNum++);
			this.setExcelTotalsRow(row,style,font, reportData.getTotals().getAverageValueOfTheProductMapValues());
			
		}else if(sheetNum == 3)//sheet Average Ticket
		{
			int rowNum = 1;
			for (EvolutionOfIndicatorReportData employeeData : reportData.getIndicators()) {
				HSSFRow row = excelSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(employeeData.getEmployee().getStore().getNameWithDesc());
				row.createCell(1).setCellValue(employeeData.getEmployee().getName());
				int column = 2;
				for (BigDecimal value : employeeData.getAverageTicketMapValues()) {
					row.createCell(column).setCellValue(value.toString());
					column++;
				}
			}
			HSSFRow row = excelSheet.createRow(rowNum++);
			this.setExcelTotalsRow(row,style,font, reportData.getTotals().getAverageTicketMapValues());
			
		}else if(sheetNum == 4)//sheet Items per sale
		{
			int rowNum = 1;
			for (EvolutionOfIndicatorReportData employeeData : reportData.getIndicators()) {
				HSSFRow row = excelSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(employeeData.getEmployee().getStore().getNameWithDesc());
				row.createCell(1).setCellValue(employeeData.getEmployee().getName());
				int column = 2;
				for (BigDecimal value : employeeData.getItemsPerSaleMapValues()) {
					row.createCell(column).setCellValue(value.toString());
					column++;
				}
			}
			HSSFRow row = excelSheet.createRow(rowNum++);
			this.setExcelTotalsRow(row,style,font, reportData.getTotals().getItemsPerSaleMapValues());
			
		}else if(sheetNum == 5)//sheet Conversion rate
		{
			int rowNum = 1;
			for (EvolutionOfIndicatorReportData employeeData : reportData.getIndicators()) {
				HSSFRow row = excelSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(employeeData.getEmployee().getStore().getNameWithDesc());
				row.createCell(1).setCellValue(employeeData.getEmployee().getName());
				int column = 2;
				for (BigDecimal value : employeeData.getConversionRateMapValues()) {
					row.createCell(column).setCellValue(value.toString());
					column++;
				}
			}
			HSSFRow row = excelSheet.createRow(rowNum++);
			this.setExcelTotalsRow(row,style,font, reportData.getTotals().getConversionRateMapValues());
			
		}else if(sheetNum == 6)//sheet Average sales per day
		{
			int rowNum = 1;
			for (EvolutionOfIndicatorReportData employeeData : reportData.getIndicators()) {
				HSSFRow row = excelSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(employeeData.getEmployee().getStore().getNameWithDesc());
				row.createCell(1).setCellValue(employeeData.getEmployee().getName());
				int column = 2;
				for (BigDecimal value : employeeData.getAverageSalesPerDayMapValues()) {
					row.createCell(column).setCellValue(value.toString());
					column++;
				}
			}
			
			HSSFRow row = excelSheet.createRow(rowNum++);
			this.setExcelTotalsRow(row,style,font, reportData.getTotals().getAverageSalesPerDayMapValues());
		}
	}
	
	private void setExcelTotalsRow(HSSFRow row, HSSFCellStyle style, HSSFFont font, Collection<BigDecimal> totalValues)
	{
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		style.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
		style.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
		style.setFont(font);
		
		HSSFCell cell0 = row.createCell(0);
		cell0.setCellStyle(style);
		cell0.setCellValue("Total");
		int column = 2;
		for (BigDecimal value : totalValues) {
			HSSFCell cell = row.createCell(column);
			cell.setCellStyle(style);
			cell.setCellValue(value.toString());
			column++;
		}
	}
}
