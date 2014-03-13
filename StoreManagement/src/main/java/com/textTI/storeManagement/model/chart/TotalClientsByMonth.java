package com.textTI.storeManagement.model.chart;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TotalClientsByMonth {
	
	private BigInteger storeId;
	private String storeName;
	private String storeDesc;
	
	private BigDecimal countClientsJan;
	private BigDecimal countClientsFeb;
	private BigDecimal countClientsMar;
	private BigDecimal countClientsApr;
	private BigDecimal countClientsMay;
	private BigDecimal countClientsJun;
	private BigDecimal countClientsJul;
	private BigDecimal countClientsAug;
	private BigDecimal countClientsSet;
	private BigDecimal countClientsOct;
	private BigDecimal countClientsNov;
	private BigDecimal countClientsDez;
	private BigInteger year;
	
	public BigInteger getStoreId() {
		return storeId;
	}
	public void setStoreId(BigInteger storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreDesc() {
		return storeDesc;
	}
	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}
	
	public String getStoreNameWithDesc()
	{
		return this.storeName + " - " + this.storeDesc;
	}
	public BigDecimal getCountClientsJan() {
		return countClientsJan;
	}
	public void setCountClientsJan(BigDecimal countClientsJan) {
		this.countClientsJan = countClientsJan;
	}
	public BigDecimal getCountClientsFeb() {
		return countClientsFeb;
	}
	public void setCountClientsFeb(BigDecimal countClientsFeb) {
		this.countClientsFeb = countClientsFeb;
	}
	public BigDecimal getCountClientsMar() {
		return countClientsMar;
	}
	public void setCountClientsMar(BigDecimal countClientsMar) {
		this.countClientsMar = countClientsMar;
	}
	public BigDecimal getCountClientsApr() {
		return countClientsApr;
	}
	public void setCountClientsApr(BigDecimal countClientsApr) {
		this.countClientsApr = countClientsApr;
	}
	public BigDecimal getCountClientsMay() {
		return countClientsMay;
	}
	public void setCountClientsMay(BigDecimal countClientsMay) {
		this.countClientsMay = countClientsMay;
	}
	public BigDecimal getCountClientsJun() {
		return countClientsJun;
	}
	public void setCountClientsJun(BigDecimal countClientsJun) {
		this.countClientsJun = countClientsJun;
	}
	public BigDecimal getCountClientsJul() {
		return countClientsJul;
	}
	public void setCountClientsJul(BigDecimal countClientsJul) {
		this.countClientsJul = countClientsJul;
	}
	public BigDecimal getCountClientsAug() {
		return countClientsAug;
	}
	public void setCountClientsAug(BigDecimal countClientsAug) {
		this.countClientsAug = countClientsAug;
	}
	public BigDecimal getCountClientsSet() {
		return countClientsSet;
	}
	public void setCountClientsSet(BigDecimal countClientsSet) {
		this.countClientsSet = countClientsSet;
	}
	public BigDecimal getCountClientsOct() {
		return countClientsOct;
	}
	public void setCountClientsOct(BigDecimal countClientsOct) {
		this.countClientsOct = countClientsOct;
	}
	public BigDecimal getCountClientsNov() {
		return countClientsNov;
	}
	public void setCountClientsNov(BigDecimal countClientsNov) {
		this.countClientsNov = countClientsNov;
	}
	public BigDecimal getCountClientsDez() {
		return countClientsDez;
	}
	public void setCountClientsDez(BigDecimal countClientsDez) {
		this.countClientsDez = countClientsDez;
	}
	public BigInteger getYear() {
		return year;
	}
	public void setYear(BigInteger year) {
		this.year = year;
	}
	
	
}
