package com.syuesoft.sell.model;

import java.util.HashSet;
import java.util.Set;

/**
 * XsSellLoan entity. @author MyEclipse Persistence Tools
 */

public class XsSellLoan implements java.io.Serializable {

	// Fields

	private Integer loanId;
	private XsCarSellInfo xsCarSellInfo;
	private Integer loanBank;
	private Double firstPayment;
	private Double balance;
	private Float year;
	private String carPlate;
	private String rate;
	private String startDate;
	private Double monthAmount;
	private Integer loanYears;
	private Integer loanWay;
	private Double amount;
	private String loanStartdate;
	private String loanLastdate;
	private String recordDate;
	private String remark;
	private Set xsSellLoanDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public XsSellLoan() {
	}

	/** full constructor */
	public XsSellLoan(XsCarSellInfo xsCarSellInfo, Integer loanBank,
			Double firstPayment, Double balance, Float year, String carPlate,
			String rate, String startDate, Double monthAmount,
			Integer loanYears, Integer loanWay, Double amount,
			String loanStartdate, String loanLastdate, String recordDate,
			String remark, Set xsSellLoanDetails) {
		this.xsCarSellInfo = xsCarSellInfo;
		this.loanBank = loanBank;
		this.firstPayment = firstPayment;
		this.balance = balance;
		this.year = year;
		this.carPlate = carPlate;
		this.rate = rate;
		this.startDate = startDate;
		this.monthAmount = monthAmount;
		this.loanYears = loanYears;
		this.loanWay = loanWay;
		this.amount = amount;
		this.loanStartdate = loanStartdate;
		this.loanLastdate = loanLastdate;
		this.recordDate = recordDate;
		this.remark = remark;
		this.xsSellLoanDetails = xsSellLoanDetails;
	}

	// Property accessors

	public Integer getLoanId() {
		return this.loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public XsCarSellInfo getXsCarSellInfo() {
		return this.xsCarSellInfo;
	}

	public void setXsCarSellInfo(XsCarSellInfo xsCarSellInfo) {
		this.xsCarSellInfo = xsCarSellInfo;
	}

	public Integer getLoanBank() {
		return this.loanBank;
	}

	public void setLoanBank(Integer loanBank) {
		this.loanBank = loanBank;
	}

	public Double getFirstPayment() {
		return this.firstPayment;
	}

	public void setFirstPayment(Double firstPayment) {
		this.firstPayment = firstPayment;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Float getYear() {
		return this.year;
	}

	public void setYear(Float year) {
		this.year = year;
	}

	public String getCarPlate() {
		return this.carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Double getMonthAmount() {
		return this.monthAmount;
	}

	public void setMonthAmount(Double monthAmount) {
		this.monthAmount = monthAmount;
	}

	public Integer getLoanYears() {
		return this.loanYears;
	}

	public void setLoanYears(Integer loanYears) {
		this.loanYears = loanYears;
	}

	public Integer getLoanWay() {
		return this.loanWay;
	}

	public void setLoanWay(Integer loanWay) {
		this.loanWay = loanWay;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getLoanStartdate() {
		return this.loanStartdate;
	}

	public void setLoanStartdate(String loanStartdate) {
		this.loanStartdate = loanStartdate;
	}

	public String getLoanLastdate() {
		return this.loanLastdate;
	}

	public void setLoanLastdate(String loanLastdate) {
		this.loanLastdate = loanLastdate;
	}

	public String getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getXsSellLoanDetails() {
		return this.xsSellLoanDetails;
	}

	public void setXsSellLoanDetails(Set xsSellLoanDetails) {
		this.xsSellLoanDetails = xsSellLoanDetails;
	}

}