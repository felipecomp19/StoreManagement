package com.textTI.storeManagement.manager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.IndicatorDAO;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.User;

@Component
public class IndicatorManager {
	
	@Autowired
	private IndicatorDAO indicatorDAO;

	public List<Indicator> getAll() {
		return this.indicatorDAO.getAll();
	}

	public Indicator getById(long id) {

		return (Indicator) this.indicatorDAO.getById(id, Indicator.class);
	}

	public void update(Indicator indicator) {
			this.populateCalculatedValues(indicator);
			this.indicatorDAO.update(indicator);
	}

	public void insert(Indicator indicator) {
			indicator.setEnabled(true);
			this.populateCalculatedValues(indicator);
			this.indicatorDAO.insert(indicator);
	}
	
	private void populateCalculatedValues(Indicator indicator) {
		indicator.setAchievementOfGoals(null);
		indicator.setAverageSalesPerDay(null);
		indicator.setAverageTicket(null);
		indicator.setAverageValueOfTheProduct(null);
		indicator.setConversionRate(null);
		indicator.setItemsPerSale(null);
		
	}

	public void delete(Indicator emp) {
		this.indicatorDAO.delete(emp);
	}

	public List<Indicator> getAllByUser(User loggedUser) {
		return this.indicatorDAO.getAllByUser(loggedUser.getStoresId());
	}

	public List<Indicator> getAllByUserAndMonth(User loggedUser,
			String selectedMonth, String selectedYear, Long storeId) {
		return this.indicatorDAO.getAllByUserAndMonth(loggedUser.getStoresId(),selectedMonth, selectedYear, storeId);
	}
	
	public Indicator generateReportResultOfMonthTotals(User loggedUser,
			String month, String year, Long storeId) {
		Indicator indicator = this.indicatorDAO.generateReportResultOfMonthTotals(loggedUser.getStoresId(), month, year, storeId);
		if(indicator != null){
			Calendar mycal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
			int workedDays = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			//regra da MORANA
			//Janeiro e Dezembro tem um dia a menos de trabalho
			if((Calendar.JANUARY == (Integer.parseInt(month) - 1)) || (Calendar.DECEMBER == (Integer.parseInt(month) - 1)))
				workedDays = workedDays - 1;
			
			indicator.setWorkedDays(workedDays);
		}
		
		return indicator;
	}

	public List<Indicator> generateReportevolutionOfIndicators(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo, Long storeId) {
		return this.indicatorDAO.getAllByUserAndMonthYearInterval(loggedUser.getStoresId(),monthFrom, yearFrom, monthTo, yearTo, storeId);
	}

	public List<Indicator> generateReportCumulativeResult(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo, Long storeId) {
		
		return this.indicatorDAO.generateReportCumulativeResult(loggedUser.getStoresId(),monthFrom, yearFrom, monthTo, yearTo, storeId);
	}

	public Indicator generateReportCumulativeResultTotals(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo, Long storeId) {
		
		Indicator result = this.indicatorDAO.generateReportCumulativeResultTotals(loggedUser.getStoresId(),monthFrom, yearFrom, monthTo, yearTo, storeId);
		
		Calendar mycalFrom = new GregorianCalendar(Integer.parseInt(yearFrom), Integer.parseInt(monthFrom) - 1, 1);
		Calendar mycalTo = new GregorianCalendar(Integer.parseInt(yearTo), Integer.parseInt(monthTo) - 1, 1);
		
		int workedDays = 0;
		int totalOfDays = 0;
		while(mycalFrom.getTime().compareTo(mycalTo.getTime()) <= 0 ){
			workedDays = mycalFrom.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			//regra da MORANA
			//Janeiro e Dezembro tem um dia a menos de trabalho
			if((Calendar.JANUARY ==  mycalFrom.get(Calendar.MONTH)) || (Calendar.DECEMBER == mycalFrom.get(Calendar.MONTH)))
				workedDays = workedDays - 1;
			
			totalOfDays = totalOfDays + workedDays;
			
			mycalFrom.add(Calendar.MONTH, 1);
		}
		
		result.setWorkedDays(totalOfDays);
		return result;
	}

	public List<String> getDistinctYears() {
		return this.indicatorDAO.getDistinctYears();
	}

	public List<Indicator> getIndicatorsByStoreMonthAndYear(int store,
			int month, int year) {
		return this.indicatorDAO.getIndicatorsByStoreMonthAndYear(store,month,year);
	}

	public Indicator getIndicatorsByStoreMonthAndYearTotals(int store,
			int month, int year) {
		return this.indicatorDAO.getIndicatorsByStoreMonthAndYearTotals(store,month,year);
	}

	public Indicator getByMonthAndYear(Long employeeId, int month, int year) {
		return this.indicatorDAO.getByMonthAndYear(employeeId, month, year);
	}

	public List<Indicator> generateReportevolutionOfIndicatorsTotals(
			User loggedUser, String monthFrom, String yearFrom, String monthTo,
			String yearTo, Long storeId) {
		
		return this.indicatorDAO.getAllByUserAndMonthYearIntervalTotals(loggedUser.getStoresId(),monthFrom, yearFrom, monthTo, yearTo, storeId);
	}
}
