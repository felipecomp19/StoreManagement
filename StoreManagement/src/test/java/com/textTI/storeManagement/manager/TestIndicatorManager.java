package com.textTI.storeManagement.manager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Store;

public class TestIndicatorManager extends BaseManagerTestCase {

	@Autowired
	private EmployeeManager employeeManager;
	
	@Autowired
	private StoreManager storeManager;
	
	@Autowired
	private IndicatorManager indicatorManager;
	
	@Test
	public void testCreateEmployee()
	{
		Store st = this.createAndInsertStore("Morana");
		
		Employee emp = createAndInsertEmployee(st);
		
		Indicator indicator = new Indicator();
		indicator.setEmployee(emp);
		indicator.setMonth(1);
		indicator.setYear(2014);
		indicator.setWorkedDays(24);
		indicator.setGoal(new BigDecimal("17265"));
		indicator.setValueOfSales(new BigDecimal("13211"));
		indicator.setNumberOfAttendances(466); //Qual seria o valor?
		indicator.setNumberOfSales(210);
		indicator.setNumberOfItemsSold(337);
		
		BigDecimal achievementOfGoals = indicator.getValueOfSales().divide(indicator.getGoal(), 2, RoundingMode.HALF_UP); //0.76
		BigDecimal averageValueOfTheProduct = indicator.getValueOfSales().divide(new BigDecimal(indicator.getNumberOfItemsSold()), 2, RoundingMode.HALF_UP);
		BigDecimal averageTicket = indicator.getValueOfSales().divide(new BigDecimal(indicator.getNumberOfSales()), 2, RoundingMode.HALF_UP);
		BigDecimal itemsPerSale = new BigDecimal(indicator.getNumberOfItemsSold()).divide(new BigDecimal(indicator.getNumberOfSales()),2, RoundingMode.HALF_UP);
		BigDecimal conversionRate = new BigDecimal(indicator.getNumberOfSales()).divide(new BigDecimal(indicator.getNumberOfAttendances()),2, RoundingMode.HALF_UP);
		BigDecimal averageSalesPerDay = indicator.getValueOfSales().divide(new BigDecimal(indicator.getWorkedDays()), 2, RoundingMode.HALF_UP);
		
		try{
			this.indicatorManager.insert(indicator);
			Assert.assertTrue(indicator.getId() > 0);
			Assert.assertEquals(achievementOfGoals, indicator.getAchievementOfGoals());
			Assert.assertEquals(averageValueOfTheProduct, indicator.getAverageValueOfTheProduct());
			Assert.assertEquals(averageTicket, indicator.getAverageTicket());
			Assert.assertEquals(itemsPerSale, indicator.getItemsPerSale());
			Assert.assertEquals(conversionRate, indicator.getConversionRate());
			Assert.assertEquals(averageSalesPerDay, indicator.getAverageSalesPerDay());
		}catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}finally
		{
			this.indicatorManager.delete(indicator);
			this.employeeManager.delete(emp);
			this.storeManager.delete(st);
		}
	}
}
