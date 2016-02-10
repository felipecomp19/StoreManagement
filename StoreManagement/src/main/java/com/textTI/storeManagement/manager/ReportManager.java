package com.textTI.storeManagement.manager;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Report;
import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.model.constants.ReportConstants;
import com.textTI.storeManagement.model.report.EvolutionOfIndicatorReportData;

@Component
public class ReportManager {

	@Autowired
	private MessageSource msgSrc;
	
	@Autowired
	private IndicatorManager indicatorManager;
	
	protected static final Logger logger = LoggerFactory.getLogger(ReportManager.class);
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
	
	public List<Report> getAll(Locale locale) {
		List<Report> reports = new ArrayList<Report>();
		
		Report report1 = new Report();
		report1.setCode(ReportConstants.REP_RESULT_OF_MONTH_CODE);
		report1.setDescription(msgSrc.getMessage(ReportConstants.REP_RESULT_OF_MONTH_DESC,null, locale));
		reports.add(report1);
		
		Report report2 = new Report();
		report2.setCode(ReportConstants.REP_CUMULATIVE_RESULT_CODE);
		report2.setDescription(msgSrc.getMessage(ReportConstants.REP_CUMULATIVE_RESULT_DESC,null, locale));
		reports.add(report2);
		
		Report report3 = new Report();
		report3.setCode(ReportConstants.REP_EVOLUTION_OF_INDICATORS_CODE);
		report3.setDescription(msgSrc.getMessage(ReportConstants.REP_EVOLUTION_OF_INDICATORS_DESC,null, locale));
		reports.add(report3);

		return reports;
	}

	public List<Indicator> generateReportResultOfMonth(User loggedUser, String selectedMonth, String selectedYear, Long storeId) {
		
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new ArrayList<Indicator>();
		
		List<Indicator> result = this.indicatorManager.getAllByUserAndMonth(loggedUser, selectedMonth, selectedYear, storeId); 
		
		return result;
	}
	
	public Indicator generateReportResultOfMonthTotals(User loggedUser,
			String month, String year, Long storeId) {
		
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new Indicator();
		
		return this.indicatorManager.generateReportResultOfMonthTotals(loggedUser, month, year, storeId);
	}

	public List<EvolutionOfIndicatorReportData> generateReportevolutionOfIndicators(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo, Long storeId) {
		
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new ArrayList<EvolutionOfIndicatorReportData>();
		
		List<Indicator> indicators = this.indicatorManager.generateReportevolutionOfIndicators(loggedUser, monthFrom, yearFrom, monthTo, yearTo, storeId);
		
		EvolutionOfIndicatorReportData reportData = null;
		List<EvolutionOfIndicatorReportData> result = new ArrayList<EvolutionOfIndicatorReportData>();
		Employee currentEmplyee = null;
		
		for (Indicator indicator : indicators) {
			if(currentEmplyee == null || currentEmplyee.getId() != indicator.getEmployee().getId()){
				currentEmplyee = indicator.getEmployee();
				
				Map<Date,BigDecimal> achievementOfGoalsMap = new TreeMap<Date,BigDecimal>();
				Map<Date,BigDecimal> averageValueOfTheProductMap = new TreeMap<Date,BigDecimal>();
				Map<Date,BigDecimal> averageTicketMap = new TreeMap<Date,BigDecimal>();
				Map<Date,BigDecimal> itemsPerSaleMap = new TreeMap<Date,BigDecimal>();
				Map<Date,BigDecimal> conversionRateMap = new TreeMap<Date,BigDecimal>();
				Map<Date,BigDecimal> averageSalesPerDayMap = new TreeMap<Date,BigDecimal>();
				
				this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, achievementOfGoalsMap);
				this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, averageValueOfTheProductMap);
				this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, averageTicketMap);
				this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, itemsPerSaleMap);
				this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, conversionRateMap);
				this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, averageSalesPerDayMap);
				
				reportData = new EvolutionOfIndicatorReportData(achievementOfGoalsMap, averageValueOfTheProductMap,averageTicketMap,itemsPerSaleMap,conversionRateMap,averageSalesPerDayMap);
				reportData.setEmployee(currentEmplyee);
				result.add(reportData);
			}
			
			try {
				Date dt = new Date(this.dateFormat.parse(indicator.getMonth() + "/" + indicator.getYear()).getTime());
				
				reportData.getAchievementOfGoalsMap().put(dt, new BigDecimal(indicator.getFormattedAchievementOfGoals()));
				reportData.getAverageValueOfTheProductMap().put(dt,indicator.getAverageValueOfTheProduct());
				reportData.getAverageTicketMap().put(dt,indicator.getAverageTicket());
				reportData.getItemsPerSaleMap().put(dt,indicator.getItemsPerSale());
				reportData.getConversionRateMap().put(dt,new BigDecimal(indicator.getFormattedConversionRate()));
				reportData.getAverageSalesPerDayMap().put(dt,indicator.getAverageSalesPerDay());

			} catch (ParseException e) {
				logger.error("Error trying to create a Date in ReportManager.generateReportevolutionOfIndicators(...");
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public EvolutionOfIndicatorReportData generateReportevolutionOfIndicatorsTotals(
			User loggedUser, String monthFrom, String yearFrom, String monthTo,
			String yearTo, Long storeId) {
		
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new EvolutionOfIndicatorReportData();
		
		List<Indicator> indicators = this.indicatorManager.generateReportevolutionOfIndicatorsTotals(loggedUser, monthFrom, yearFrom, monthTo, yearTo, storeId);
		
		Map<Date,BigDecimal> achievementOfGoalsMap = new TreeMap<Date,BigDecimal>();
		Map<Date,BigDecimal> averageValueOfTheProductMap = new TreeMap<Date,BigDecimal>();
		Map<Date,BigDecimal> averageTicketMap = new TreeMap<Date,BigDecimal>();
		Map<Date,BigDecimal> itemsPerSaleMap = new TreeMap<Date,BigDecimal>();
		Map<Date,BigDecimal> conversionRateMap = new TreeMap<Date,BigDecimal>();
		Map<Date,BigDecimal> averageSalesPerDayMap = new TreeMap<Date,BigDecimal>();
		
		this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, achievementOfGoalsMap);
		this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, averageValueOfTheProductMap);
		this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, averageTicketMap);
		this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, itemsPerSaleMap);
		this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, conversionRateMap);
		this.populateDateIndicatorValueMap(String.valueOf(monthFrom), yearFrom, String.valueOf(monthTo), yearTo, averageSalesPerDayMap);
		
		EvolutionOfIndicatorReportData reportData = new EvolutionOfIndicatorReportData(achievementOfGoalsMap, averageValueOfTheProductMap,averageTicketMap,itemsPerSaleMap,conversionRateMap,averageSalesPerDayMap);

		for (Indicator indicator : indicators) {
			try {
				Date dt = new Date(this.dateFormat.parse(indicator.getMonth() + "/" + indicator.getYear()).getTime());
				
				Calendar cal = new GregorianCalendar(indicator.getYear(), indicator.getMonth() - 1, 1);
				int workedDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				//regra da MORANA
				//Janeiro e Dezembro tem um dia a menos de trabalho
				if((Calendar.JANUARY ==  cal.get(Calendar.MONTH)) || (Calendar.DECEMBER == cal.get(Calendar.MONTH)))
					workedDays = workedDays - 1;
				
				indicator.setWorkedDays(workedDays);
				
				reportData.getAchievementOfGoalsMap().put(dt, new BigDecimal(indicator.getFormattedAchievementOfGoals()));
				reportData.getAverageValueOfTheProductMap().put(dt,indicator.getAverageValueOfTheProduct());
				reportData.getAverageTicketMap().put(dt,indicator.getAverageTicket());
				reportData.getItemsPerSaleMap().put(dt,indicator.getItemsPerSale());
				reportData.getConversionRateMap().put(dt, new BigDecimal(indicator.getFormattedConversionRate()));
				reportData.getAverageSalesPerDayMap().put(dt,indicator.getAverageSalesPerDay());
			} catch (ParseException e) {
				logger.error("Error trying to create a Date in ReportManager.generateReportevolutionOfIndicatorsTotals(...");
				e.printStackTrace();
			}
		}
		
		return reportData;
	}
	
	private void populateDateIndicatorValueMap(String monthFrom,
			String yearFrom, String monthTo, String yearTo,
			Map<Date, BigDecimal> dateIndValueMap) {
		
		Date dtFrom;
		Date dtTo;
		try {
			dtFrom = new Date(this.dateFormat.parse(monthFrom + "/" + yearFrom).getTime());
			dtTo = new Date(this.dateFormat.parse(monthTo + "/" + yearTo).getTime());
			
			Calendar aux = Calendar.getInstance();
			aux.set(Integer.parseInt(yearFrom), (Integer.parseInt(monthFrom)-1),1,0,0,0);
			while (dtFrom.before(dtTo) || dtFrom.equals(dtTo)) {
				dateIndValueMap.put(dtFrom, new BigDecimal("0.00"));
				
				aux.add(Calendar.MONTH, 1);
				dtFrom = new Date(this.dateFormat.parse( (aux.get(Calendar.MONTH)+1) + "/" + aux.get(Calendar.YEAR)).getTime());
			}
		
		} catch (ParseException e) {
			logger.error("Error trying to create a Date in ReportManager.populateDateIndicatorValueMap(...");
			e.printStackTrace();
		}
	}

	public List<Indicator> generateReportCumulativeResult(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo, Long storeId) {
		
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new ArrayList<Indicator>();
		
		List<Indicator> result = this.indicatorManager.generateReportCumulativeResult(loggedUser, monthFrom, yearFrom, monthTo, yearTo, storeId);
		
		return result;
	}

	public Indicator generateReportCumulativeResultTotals(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo, Long storeId) {
		
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new Indicator();
		
		return this.indicatorManager.generateReportCumulativeResultTotals(loggedUser, monthFrom, yearFrom, monthTo, yearTo, storeId);
	}

	public List<Indicator> getIndicatorsByStoreMonthAndYear(User loggedUser,
			int store, int month, int year) {
		
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new ArrayList<Indicator>();
		
		return this.indicatorManager.getIndicatorsByStoreMonthAndYear(store,month,year);
	}

	public Indicator getIndicatorsByStoreMonthAndYearTotals(User loggedUser,
			int store, int month, int year) {
		
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new Indicator();
		
		return this.indicatorManager.getIndicatorsByStoreMonthAndYearTotals(store,month,year);
	}
}
