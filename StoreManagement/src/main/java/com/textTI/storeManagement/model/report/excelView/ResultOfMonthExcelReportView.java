package com.textTI.storeManagement.model.report.excelView;

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

import com.textTI.storeManagement.model.Indicator;

public class ResultOfMonthExcelReportView extends AbstractExcelView {
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ExcelReportData reportData = (ExcelReportData) model.get("reportData");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + reportData.getReport().getDescription() + ".xls\"");

		HSSFSheet excelSheet = workbook.createSheet("Indicadores");
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		
		setExcelHeader(excelSheet, style, font, reportData.getReportHeaders());
	    setExcelRows(excelSheet, style, font, reportData);
	}

	private void setExcelHeader(HSSFSheet excelSheet, HSSFCellStyle style, HSSFFont font, List<String> headers) {
		HSSFRow headerRow = excelSheet.createRow(0);
		
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		style.setFillBackgroundColor(new HSSFColor.BLUE().getIndex());
		style.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
		style.setFont(font);
		
		headerRow.setRowStyle(style);
		
		int headerIndex = 0;
		for (String header : headers) {
			HSSFCell cell = headerRow.createCell(headerIndex);
			cell.setCellStyle(style);
			cell.setCellValue(header);

			headerIndex++;
		}
	}
	
	private void setExcelRows(HSSFSheet excelSheet, HSSFCellStyle style, HSSFFont font, ExcelReportData reportData) {
		int rowNum = 1;
		for (Indicator indicator : reportData.getIndicators()) {
			//create the row data
			HSSFRow row = excelSheet.createRow(rowNum++);
			row.createCell(0).setCellValue(indicator.getEmployee().getStore().getNameWithDesc());
			row.createCell(1).setCellValue(indicator.getEmployee().getName());
			row.createCell(2).setCellValue(indicator.getYear());
			row.createCell(3).setCellValue(indicator.getMonth());
			row.createCell(4).setCellValue(indicator.getWorkedDays());
			row.createCell(5).setCellValue(indicator.getGoal().toString());
			row.createCell(6).setCellValue(indicator.getValueOfSales().toString());
			row.createCell(7).setCellValue(indicator.getNumberOfAttendances());
			row.createCell(8).setCellValue(indicator.getNumberOfSales());
			row.createCell(9).setCellValue(indicator.getNumberOfItemsSold());
			row.createCell(10).setCellValue(indicator.getFormattedAchievementOfGoals());
			row.createCell(11).setCellValue(indicator.getAverageValueOfTheProduct().toString());
			row.createCell(12).setCellValue(indicator.getAverageTicket().toString());
			row.createCell(13).setCellValue(indicator.getItemsPerSale().toString());
			row.createCell(14).setCellValue(indicator.getFormattedConversionRate());
			row.createCell(15).setCellValue(indicator.getAverageSalesPerDay().toString());
		}
		
		
		
		setExcelTotalsRow(excelSheet, style, font, reportData, rowNum++);
	}

	private void setExcelTotalsRow(HSSFSheet excelSheet, HSSFCellStyle style, HSSFFont font, ExcelReportData reportData, int rowTotalsNum) {
		Indicator indicator = reportData.getTotals();
		
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		style.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
		style.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
		style.setFont(font);
		
		HSSFRow row = excelSheet.createRow(rowTotalsNum);
		
		HSSFCell cell0 = row.createCell(0);
		HSSFCell cell1 = row.createCell(1);
		HSSFCell cell2 = row.createCell(2);
		HSSFCell cell3 = row.createCell(3);
		HSSFCell cell4 = row.createCell(4);
		HSSFCell cell5 = row.createCell(5);
		HSSFCell cell6 = row.createCell(6);
		HSSFCell cell7 = row.createCell(7);
		HSSFCell cell8 = row.createCell(8);
		HSSFCell cell9 = row.createCell(9);
		HSSFCell cell10 = row.createCell(10);
		HSSFCell cell11 = row.createCell(11);
		HSSFCell cell12 = row.createCell(12);
		HSSFCell cell13 = row.createCell(13);
		HSSFCell cell14 = row.createCell(14);
		HSSFCell cell15 = row.createCell(15);
		
		cell0.setCellStyle(style);
		cell1.setCellStyle(style);
		cell2.setCellStyle(style);
		cell3.setCellStyle(style);
		cell4.setCellStyle(style);
		cell5.setCellStyle(style);
		cell6.setCellStyle(style);
		cell7.setCellStyle(style);
		cell8.setCellStyle(style);
		cell9.setCellStyle(style);
		cell10.setCellStyle(style);
		cell11.setCellStyle(style);
		cell12.setCellStyle(style);
		cell13.setCellStyle(style);
		cell14.setCellStyle(style);
		cell15.setCellStyle(style);
		
		cell0.setCellValue("Totais");
		cell1.setCellValue("");
		cell2.setCellValue(indicator.getYear());
		cell3.setCellValue(indicator.getMonth());
		cell4.setCellValue(indicator.getWorkedDays());
		cell5.setCellValue(indicator.getGoal().toString());
		cell6.setCellValue(indicator.getValueOfSales().toString());
		cell7.setCellValue(indicator.getNumberOfAttendances());
		cell8.setCellValue(indicator.getNumberOfSales());
		cell9.setCellValue(indicator.getNumberOfItemsSold());
		cell10.setCellValue(indicator.getFormattedAchievementOfGoals());
		cell11.setCellValue(indicator.getAverageValueOfTheProduct().toString());
		cell12.setCellValue(indicator.getAverageTicket().toString());
		cell13.setCellValue(indicator.getItemsPerSale().toString());
		cell14.setCellValue(indicator.getFormattedConversionRate());
		cell15.setCellValue(indicator.getAverageSalesPerDay().toString());
		
		row.setRowStyle(style);
	}
}
