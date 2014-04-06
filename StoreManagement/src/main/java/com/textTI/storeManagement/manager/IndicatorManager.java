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
			String selectedMonth, String selectedYear) {
		return this.indicatorDAO.getAllByUserAndMonth(loggedUser.getStoresId(),selectedMonth, selectedYear);
	}
	
	public Indicator generateReportResultOfMonthTotals(User loggedUser,
			String month, String year) {
		Indicator indicator = this.indicatorDAO.generateReportResultOfMonthTotals(loggedUser.getStoresId(), month, year);
		if(indicator != null){
			Calendar mycal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
			indicator.setWorkedDays(mycal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		
		return indicator;
	}

	public List<Indicator> generateReportevolutionOfIndicators(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo) {
		return this.indicatorDAO.getAllByUserAndMonthYearInterval(loggedUser.getStoresId(),monthFrom, yearFrom, monthTo, yearTo);
	}

	public List<Indicator> generateReportCumulativeResult(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo) {
		
		return this.indicatorDAO.generateReportCumulativeResult(loggedUser.getStoresId(),monthFrom, yearFrom, monthTo, yearTo);
	}

	public Indicator generateReportCumulativeResultTotals(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo) {
		
		return this.indicatorDAO.generateReportCumulativeResultTotals(loggedUser.getStoresId(),monthFrom, yearFrom, monthTo, yearTo);
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
}
