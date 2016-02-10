package com.textTI.storeManagement.model.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EvolutionOfIndicatorReportData extends ReportData {
	
	private Map<Date,BigDecimal> achievementOfGoalsMap;
	
	private Map<Date,BigDecimal> averageValueOfTheProductMap;
	
	private Map<Date,BigDecimal> averageTicketMap;
	
	private Map<Date,BigDecimal> itemsPerSaleMap;
	
	private Map<Date,BigDecimal> conversionRateMap;
	
	private Map<Date,BigDecimal> averageSalesPerDayMap;
	
	public EvolutionOfIndicatorReportData(
			Map<Date, BigDecimal> achievementOfGoalsMap,
			Map<Date, BigDecimal> averageValueOfTheProductMap,
			Map<Date, BigDecimal> averageTicketMap,
			Map<Date, BigDecimal> itemsPerSaleMap,
			Map<Date, BigDecimal> conversionRateMap,
			Map<Date, BigDecimal> averageSalesPerDayMap) {
		super();
		this.achievementOfGoalsMap = achievementOfGoalsMap;
		this.averageValueOfTheProductMap = averageValueOfTheProductMap;
		this.averageTicketMap = averageTicketMap;
		this.itemsPerSaleMap = itemsPerSaleMap;
		this.conversionRateMap = conversionRateMap;
		this.averageSalesPerDayMap = averageSalesPerDayMap;
	}

	public EvolutionOfIndicatorReportData(Map<Date, BigDecimal> defaultValues) {
		this.achievementOfGoalsMap = defaultValues;
		this.averageValueOfTheProductMap = defaultValues;
		this.averageTicketMap = defaultValues;
		this.itemsPerSaleMap = defaultValues;
		this.conversionRateMap = defaultValues;
		this.averageSalesPerDayMap = defaultValues;
	}

	public EvolutionOfIndicatorReportData() {
		// TODO Auto-generated constructor stub
	}

	public Map<Date, BigDecimal> getAchievementOfGoalsMap() {
		return achievementOfGoalsMap;
	}

	public void setAchievementOfGoalsMap(Map<Date, BigDecimal> achievementOfGoalsMap) {
		this.achievementOfGoalsMap = achievementOfGoalsMap;
	}

	public Map<Date, BigDecimal> getAverageValueOfTheProductMap() {
		return averageValueOfTheProductMap;
	}

	public void setAverageValueOfTheProductMap(
			Map<Date, BigDecimal> averageValueOfTheProductMap) {
		this.averageValueOfTheProductMap = averageValueOfTheProductMap;
	}

	public Map<Date, BigDecimal> getAverageTicketMap() {
		return averageTicketMap;
	}

	public void setAverageTicketMap(Map<Date, BigDecimal> averageTicketMap) {
		this.averageTicketMap = averageTicketMap;
	}

	public Map<Date, BigDecimal> getItemsPerSaleMap() {
		return itemsPerSaleMap;
	}

	public void setItemsPerSaleMap(Map<Date, BigDecimal> itemsPerSaleMap) {
		this.itemsPerSaleMap = itemsPerSaleMap;
	}

	public Map<Date, BigDecimal> getConversionRateMap() {
		return conversionRateMap;
	}

	public void setConversionRateMap(Map<Date, BigDecimal> conversionRateMap) {
		this.conversionRateMap = conversionRateMap;
	}

	public Map<Date, BigDecimal> getAverageSalesPerDayMap() {
		return averageSalesPerDayMap;
	}

	public void setAverageSalesPerDayMap(Map<Date, BigDecimal> averageSalesPerDayMap) {
		this.averageSalesPerDayMap = averageSalesPerDayMap;
	}
	
	public List<String> getKeys(){
		List<Date> keys = new ArrayList<Date>(this.achievementOfGoalsMap.keySet());
		
		Collections.sort(keys);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy"); // Set your date format
		
		List<String> formattedKeys = new ArrayList<String>();
		for (Date date : keys) {
			formattedKeys.add(sdf.format(date));		 // Get Date String according to date format
		}
		
		return formattedKeys;
	}
	
	public Collection<BigDecimal> getAchievementOfGoalsMapValues()
	{
		return this.achievementOfGoalsMap.values();
	}
	
	public Collection<BigDecimal> getAverageValueOfTheProductMapValues()
	{
		return this.averageValueOfTheProductMap.values();
	}
	
	public Collection<BigDecimal> getAverageTicketMapValues()
	{
		return this.averageTicketMap.values();
	}
	
	public Collection<BigDecimal> getItemsPerSaleMapValues()
	{
		return this.itemsPerSaleMap.values();
	}
	
	public Collection<BigDecimal> getConversionRateMapValues()
	{
		return this.conversionRateMap.values();
	}
	
	public Collection<BigDecimal> getAverageSalesPerDayMapValues()
	{
		return this.averageSalesPerDayMap.values();
	}
}






