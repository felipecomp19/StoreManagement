package com.textTI.storeManagement.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name="tb_indicators")
public class Indicator extends BaseModel {

	private static final long serialVersionUID = 3425455337016429803L;

	@Column(name="month")
	private int month;
	
	@Column(name="year")
	private int year;
	
	@Transient
	private Store store;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee", nullable = false)
	@JsonManagedReference
	private Employee employee;
	
	@Column(name="workedDays")
	private int workedDays;
	
	private BigDecimal goal;
	
	private BigDecimal valueOfSales;
	
	private int numberOfAttendances;
	
	private int numberOfSales;
	
	private int numberOfItemsSold;
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getWorkedDays() {
		return workedDays;
	}

	public void setWorkedDays(int workedDays) {
		this.workedDays = workedDays;
	}

	public BigDecimal getGoal() {
		return goal;
	}

	public void setGoal(BigDecimal bigDecimal) {
		this.goal = bigDecimal;
	}

	public BigDecimal getValueOfSales() {
		return valueOfSales;
	}

	public void setValueOfSales(BigDecimal valueOfSales) {
		this.valueOfSales = valueOfSales;
	}

	public int getNumberOfAttendances() {
		return numberOfAttendances;
	}

	public void setNumberOfAttendances(int numberOfAttendances) {
		this.numberOfAttendances = numberOfAttendances;
	}

	public int getNumberOfSales() {
		return numberOfSales;
	}

	public void setNumberOfSales(int numberOfSales) {
		this.numberOfSales = numberOfSales;
	}

	public int getNumberOfItemsSold() {
		return numberOfItemsSold;
	}

	public void setNumberOfItemsSold(int numberOfItemsSold) {
		this.numberOfItemsSold = numberOfItemsSold;
	}
	
	//calculated
	public BigDecimal getAchievementOfGoals()
	{
		return this.valueOfSales.divide(this.goal, 2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getAverageValueOfTheProduct(){
		return this.valueOfSales.divide(new BigDecimal(this.numberOfItemsSold), 2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getAverageTicket()
	{
		return this.valueOfSales.divide(new BigDecimal(this.numberOfSales), 2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getItemsPerSale()
	{
		return new BigDecimal(this.numberOfItemsSold).divide(new BigDecimal(this.numberOfSales), 2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getConversionRate(){
		return new BigDecimal(this.numberOfSales).divide(new BigDecimal(this.numberOfAttendances), 2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getAverageSalesPerDay(){
		return this.valueOfSales.divide(new BigDecimal(this.workedDays), 2, RoundingMode.HALF_UP);
	}
	
	public String formatedGoal()
	{
		return "";
	}
	
	public String getFormattedAverageTicket()
	{
		return NumberFormat.getPercentInstance().format(this.valueOfSales.divide(new BigDecimal(this.numberOfSales), 2, RoundingMode.HALF_UP));
	}
}
