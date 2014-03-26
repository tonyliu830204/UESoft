package com.syuesoft.model;

import java.util.Date;

/**
 * BasVipWeekIntegralRegulation entity. @author MyEclipse Persistence Tools
 */

public class BatchBalanceGather extends BaseBean {

	// Fields
	private String bbgId;
	private Double bbgReceivables;
	private Double bbgCumulative;
	private Double bbgArrears;
	private Short bbgUnFinished;
	private Short bbgDifferenceBalance;
	private Short bbgAllowArrearsAge;
	private Short bbgReceivablesArrearsAge;
	private Boolean bbgAllowFlag;
	private Double bbgAllowArrearsAmount;
	private Short bbgAllowArrearsNumber;
	private Boolean bbgPrintFlag;
	private Short bbgSubstitute;
	private String customId;
	private Date bbgDate;
	// Constructors

	/** default constructor */
	public BatchBalanceGather() {
	}

	public String getBbgId() {
		return bbgId;
	}

	public void setBbgId(String bbgId) {
		this.bbgId = bbgId;
	}

	public Double getBbgReceivables() {
		return bbgReceivables;
	}

	public void setBbgReceivables(Double bbgReceivables) {
		this.bbgReceivables = bbgReceivables;
	}

	public Double getBbgCumulative() {
		return bbgCumulative;
	}

	public void setBbgCumulative(Double bbgCumulative) {
		this.bbgCumulative = bbgCumulative;
	}

	public Double getBbgArrears() {
		return bbgArrears;
	}

	public void setBbgArrears(Double bbgArrears) {
		this.bbgArrears = bbgArrears;
	}

	public Short getBbgUnFinished() {
		return bbgUnFinished;
	}

	public void setBbgUnFinished(Short bbgUnFinished) {
		this.bbgUnFinished = bbgUnFinished;
	}

	public Short getBbgDifferenceBalance() {
		return bbgDifferenceBalance;
	}

	public void setBbgDifferenceBalance(Short bbgDifferenceBalance) {
		this.bbgDifferenceBalance = bbgDifferenceBalance;
	}

	public Short getBbgAllowArrearsAge() {
		return bbgAllowArrearsAge;
	}

	public void setBbgAllowArrearsAge(Short bbgAllowArrearsAge) {
		this.bbgAllowArrearsAge = bbgAllowArrearsAge;
	}

	public Short getBbgReceivablesArrearsAge() {
		return bbgReceivablesArrearsAge;
	}

	public void setBbgReceivablesArrearsAge(Short bbgReceivablesArrearsAge) {
		this.bbgReceivablesArrearsAge = bbgReceivablesArrearsAge;
	}

	public Boolean getBbgAllowFlag() {
		return bbgAllowFlag;
	}

	public void setBbgAllowFlag(Boolean bbgAllowFlag) {
		this.bbgAllowFlag = bbgAllowFlag;
	}

	public Double getBbgAllowArrearsAmount() {
		return bbgAllowArrearsAmount;
	}

	public void setBbgAllowArrearsAmount(Double bbgAllowArrearsAmount) {
		this.bbgAllowArrearsAmount = bbgAllowArrearsAmount;
	}

	public Short getBbgAllowArrearsNumber() {
		return bbgAllowArrearsNumber;
	}

	public void setBbgAllowArrearsNumber(Short bbgAllowArrearsNumber) {
		this.bbgAllowArrearsNumber = bbgAllowArrearsNumber;
	}

	public Boolean getBbgPrintFlag() {
		return bbgPrintFlag;
	}

	public void setBbgPrintFlag(Boolean bbgPrintFlag) {
		this.bbgPrintFlag = bbgPrintFlag;
	}

	public Short getBbgSubstitute() {
		return bbgSubstitute;
	}

	public void setBbgSubstitute(Short bbgSubstitute) {
		this.bbgSubstitute = bbgSubstitute;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public Date getBbgDate() {
		return bbgDate;
	}

	public void setBbgDate(Date bbgDate) {
		this.bbgDate = bbgDate;
	}

	
}