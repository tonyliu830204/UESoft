package com.syuesoft.model;

import java.util.Date;

public class SubstitutePaymentDetail extends BaseBean {

	private String spdId;
	private String sspId;
	private Short stfId;
	private String paymentId;
	private Double spdPayable;
	private Double spdPaymentMoney;
	private Double spdArrears;
	private Date spdDate;
	private Short spdUnFinished;
	private String spdRemark;
	
	public SubstitutePaymentDetail() {
		// TODO Auto-generated constructor stub
	}

	public String getSpdId() {
		return spdId;
	}

	public void setSpdId(String spdId) {
		this.spdId = spdId;
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

	public Double getSpdPayable() {
		return spdPayable;
	}

	public void setSpdPayable(Double spdPayable) {
		this.spdPayable = spdPayable;
	}

	public Double getSpdPaymentMoney() {
		return spdPaymentMoney;
	}

	public void setSpdPaymentMoney(Double spdPaymentMoney) {
		this.spdPaymentMoney = spdPaymentMoney;
	}

	public Double getSpdArrears() {
		return spdArrears;
	}

	public void setSpdArrears(Double spdArrears) {
		this.spdArrears = spdArrears;
	}

	public Date getSpdDate() {
		return spdDate;
	}

	public void setSpdDate(Date spdDate) {
		this.spdDate = spdDate;
	}

	public Short getSpdUnFinished() {
		return spdUnFinished;
	}

	public void setSpdUnFinished(Short spdUnFinished) {
		this.spdUnFinished = spdUnFinished;
	}

	public String getSpdRemark() {
		return spdRemark;
	}

	public void setSpdRemark(String spdRemark) {
		this.spdRemark = spdRemark;
	}
	
	
}
