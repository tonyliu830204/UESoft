package com.syuesoft.model;

import java.util.Date;

public class SubstituteSuitPayment extends BaseBean {
	private String sspId;
	private String customId;
	private Double sspReceivables;
	private Double sspCumulative;
	private Short sspAllowArrearsAge;
	private Short sspReceivablesArrearsAge;
	private Boolean sspAllowFlag;
	private Double sspAllowArrearsAmount;
	private Short sspAllowArrearsNumber;
	private Boolean sspPrintFlag;
	private Short sspUnFinished;
	private Short sspType;
	private Short sspBatch;
	private Double sspArrears;
	private Date sspDate;
	private String balanceId;
	private Short sspDifferenceBalance;
	private Short sspBatchTag;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }

	public String getSspId() {
		return sspId;
	}

	public void setSspId(String sspId) {
		this.sspId = sspId;
	}
	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public Double getSspReceivables() {
		return sspReceivables;
	}

	public void setSspReceivables(Double sspReceivables) {
		this.sspReceivables = sspReceivables;
	}

	public Double getSspCumulative() {
		return sspCumulative;
	}

	public void setSspCumulative(Double sspCumulative) {
		this.sspCumulative = sspCumulative;
	}

	public Short getSspAllowArrearsAge() {
		return sspAllowArrearsAge;
	}

	public void setSspAllowArrearsAge(Short sspAllowArrearsAge) {
		this.sspAllowArrearsAge = sspAllowArrearsAge;
	}

	public Short getSspReceivablesArrearsAge() {
		return sspReceivablesArrearsAge;
	}

	public void setSspReceivablesArrearsAge(Short sspReceivablesArrearsAge) {
		this.sspReceivablesArrearsAge = sspReceivablesArrearsAge;
	}

	public Boolean getSspAllowFlag() {
		return sspAllowFlag;
	}

	public void setSspAllowFlag(Boolean sspAllowFlag) {
		this.sspAllowFlag = sspAllowFlag;
	}

	public Double getSspAllowArrearsAmount() {
		return sspAllowArrearsAmount;
	}

	public void setSspAllowArrearsAmount(Double sspAllowArrearsAmount) {
		this.sspAllowArrearsAmount = sspAllowArrearsAmount;
	}

	public Short getSspAllowArrearsNumber() {
		return sspAllowArrearsNumber;
	}

	public void setSspAllowArrearsNumber(Short sspAllowArrearsNumber) {
		this.sspAllowArrearsNumber = sspAllowArrearsNumber;
	}

	public Boolean getSspPrintFlag() {
		return sspPrintFlag;
	}

	public void setSspPrintFlag(Boolean sspPrintFlag) {
		this.sspPrintFlag = sspPrintFlag;
	}

	public Short getSspUnFinished() {
		return sspUnFinished;
	}

	public void setSspUnFinished(Short sspUnFinished) {
		this.sspUnFinished = sspUnFinished;
	}

	public Short getSspType() {
		return sspType;
	}

	public void setSspType(Short sspType) {
		this.sspType = sspType;
	}

	public Short getSspBatch() {
		return sspBatch;
	}

	public void setSspBatch(Short sspBatch) {
		this.sspBatch = sspBatch;
	}

	public Double getSspArrears() {
		return sspArrears;
	}

	public void setSspArrears(Double sspArrears) {
		this.sspArrears = sspArrears;
	}

	public Date getSspDate() {
		return sspDate;
	}

	public void setSspDate(Date sspDate) {
		this.sspDate = sspDate;
	}

	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public Short getSspDifferenceBalance() {
		return sspDifferenceBalance;
	}

	public void setSspDifferenceBalance(Short sspDifferenceBalance) {
		this.sspDifferenceBalance = sspDifferenceBalance;
	}

	public Short getSspBatchTag() {
		return sspBatchTag;
	}

	public void setSspBatchTag(Short sspBatchTag) {
		this.sspBatchTag = sspBatchTag;
	}
	

}
