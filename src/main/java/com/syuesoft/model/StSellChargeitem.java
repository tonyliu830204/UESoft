package com.syuesoft.model;

import java.util.Date;

/**
 * StSellChargeitem entity. @author MyEclipse Persistence Tools
 */

public class StSellChargeitem extends BaseBean {

	// Fields

	private String sscId;
	private StSellCharge stSellCharge;
	private Short stfId;
	private String paymentId;
	private Double sscPayable;
	private Double sscPaymentMoney;
	private Double sscArrears;
	private Date sscDate;
	private Short sscUnFinished;
	private String sscRemark;

	// Constructors

	/** default constructor */
	public StSellChargeitem() {
	}

	public String getSscId() {
		return sscId;
	}

	public void setSscId(String sscId) {
		this.sscId = sscId;
	}

	public StSellCharge getStSellCharge() {
		return stSellCharge;
	}

	public void setStSellCharge(StSellCharge stSellCharge) {
		this.stSellCharge = stSellCharge;
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

	public Double getSscPayable() {
		return sscPayable;
	}

	public void setSscPayable(Double sscPayable) {
		this.sscPayable = sscPayable;
	}

	public Double getSscPaymentMoney() {
		return sscPaymentMoney;
	}

	public void setSscPaymentMoney(Double sscPaymentMoney) {
		this.sscPaymentMoney = sscPaymentMoney;
	}

	public Double getSscArrears() {
		return sscArrears;
	}

	public void setSscArrears(Double sscArrears) {
		this.sscArrears = sscArrears;
	}

	public Date getSscDate() {
		return sscDate;
	}

	public void setSscDate(Date sscDate) {
		this.sscDate = sscDate;
	}

	public Short getSscUnFinished() {
		return sscUnFinished;
	}

	public void setSscUnFinished(Short sscUnFinished) {
		this.sscUnFinished = sscUnFinished;
	}

	public String getSscRemark() {
		return sscRemark;
	}

	public void setSscRemark(String sscRemark) {
		this.sscRemark = sscRemark;
	}
	
}