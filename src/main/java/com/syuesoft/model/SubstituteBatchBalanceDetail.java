package com.syuesoft.model;

import java.util.Date;

public class SubstituteBatchBalanceDetail extends BaseBean {
	private String sbbdId;
	private String sspId;
	private Short stfId;
	private String paymentId;
	private Double sbbdPayable;
	private Double sbbdPaymentMoney;
	private Double sbbdArrears;
	private Date sbbdDate;
	private Short sbbdUnFinished;
	private Short sbbdDifferenceBalance;
	private String sbbdRemark;
	private Short sbbdType;
	private String customId;
	
	public SubstituteBatchBalanceDetail() {
		// TODO Auto-generated constructor stub
	}

	public String getSbbdId() {
		return sbbdId;
	}

	public void setSbbdId(String sbbdId) {
		this.sbbdId = sbbdId;
	}

	public String getSspId() {
		return sspId;
	}

	public void setSspId(String sspId) {
		this.sspId = sspId;
	}

	public Short getStfId() {
		return stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Double getSbbdPayable() {
		return sbbdPayable;
	}

	public void setSbbdPayable(Double sbbdPayable) {
		this.sbbdPayable = sbbdPayable;
	}

	public Double getSbbdPaymentMoney() {
		return sbbdPaymentMoney;
	}

	public void setSbbdPaymentMoney(Double sbbdPaymentMoney) {
		this.sbbdPaymentMoney = sbbdPaymentMoney;
	}

	public Double getSbbdArrears() {
		return sbbdArrears;
	}

	public void setSbbdArrears(Double sbbdArrears) {
		this.sbbdArrears = sbbdArrears;
	}

	public Date getSbbdDate() {
		return sbbdDate;
	}

	public void setSbbdDate(Date sbbdDate) {
		this.sbbdDate = sbbdDate;
	}

	public Short getSbbdUnFinished() {
		return sbbdUnFinished;
	}

	public void setSbbdUnFinished(Short sbbdUnFinished) {
		this.sbbdUnFinished = sbbdUnFinished;
	}

	public Short getSbbdDifferenceBalance() {
		return sbbdDifferenceBalance;
	}

	public void setSbbdDifferenceBalance(Short sbbdDifferenceBalance) {
		this.sbbdDifferenceBalance = sbbdDifferenceBalance;
	}

	public String getSbbdRemark() {
		return sbbdRemark;
	}

	public void setSbbdRemark(String sbbdRemark) {
		this.sbbdRemark = sbbdRemark;
	}

	public Short getSbbdType() {
		return sbbdType;
	}

	public void setSbbdType(Short sbbdType) {
		this.sbbdType = sbbdType;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	
}
