package com.syuesoft.model;

import java.util.Date;

/**
 * FinCollectionSchedule entity. @author MyEclipse Persistence Tools
 */

public class BatchPaymentDetail extends BaseBean {

	// Fields

	private String bpdId;
	private String bbgId;
	private Short stfId;
	private String paymentId;
	private Double bpdPayable;
	private Double bpdPaymentMoney;
	private Double bpdArrears;
	private Date bpdDate;
	private Short bpdUnFinished;
	private String bpdRemark;

	// Constructors

	/** default constructor */
	public BatchPaymentDetail() {
			
	}

	public String getBpdId() {
		return bpdId;
	}

	public void setBpdId(String bpdId) {
		this.bpdId = bpdId;
	}

	public String getBbgId() {
		return bbgId;
	}

	public void setBbgId(String bbgId) {
		this.bbgId = bbgId;
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

	public Double getBpdPayable() {
		return bpdPayable;
	}

	public void setBpdPayable(Double bpdPayable) {
		this.bpdPayable = bpdPayable;
	}

	public Double getBpdPaymentMoney() {
		return bpdPaymentMoney;
	}

	public void setBpdPaymentMoney(Double bpdPaymentMoney) {
		this.bpdPaymentMoney = bpdPaymentMoney;
	}

	public Double getBpdArrears() {
		return bpdArrears;
	}

	public void setBpdArrears(Double bpdArrears) {
		this.bpdArrears = bpdArrears;
	}

	public Date getBpdDate() {
		return bpdDate;
	}

	public void setBpdDate(Date bpdDate) {
		this.bpdDate = bpdDate;
	}


	public Short getBpdUnFinished() {
		return bpdUnFinished;
	}

	public void setBpdUnFinished(Short bpdUnFinished) {
		this.bpdUnFinished = bpdUnFinished;
	}

	public String getBpdRemark() {
		return bpdRemark;
	}

	public void setBpdRemark(String bpdRemark) {
		this.bpdRemark = bpdRemark;
	}

}