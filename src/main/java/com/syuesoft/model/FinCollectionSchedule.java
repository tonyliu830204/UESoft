package com.syuesoft.model;

import java.util.Date;

/**
 * FinCollectionSchedule entity. @author MyEclipse Persistence Tools
 */

public class FinCollectionSchedule extends BaseBean {
    private static final long serialVersionUID = 1L;
    private String fcsId;
	private String mrId;
	private Short stfId;
	private String paymentId;
	private Double fcsPayable;
	private Double fcsPaymentMoney;
	private Double fcsArrears;
	private Date fcsDate;
	private Short fcsUnFinished;
	private String fcsRemark;
	private Double  fcsPayment;                  //找零
//	private Double fcsReserve;
    private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }

	// Constructors

	/** default constructor */
	public FinCollectionSchedule() {
			
	}

	public String getFcsId() {
		return fcsId;
	}

	public void setFcsId(String fcsId) {
		this.fcsId = fcsId;
	}

	public String getMrId() {
		return this.mrId;
	}

	public void setMrId(String mrId) {
		this.mrId = mrId;
	}

	public Short getStfId() {
		return this.stfId;
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

	public Double getFcsPaymentMoney() {
		return fcsPaymentMoney;
	}


	public void setFcsPaymentMoney(Double fcsPaymentMoney) {
		this.fcsPaymentMoney = fcsPaymentMoney;
	}


	public Double getFcsArrears() {
		return fcsArrears;
	}


	public void setFcsArrears(Double fcsArrears) {
		this.fcsArrears = fcsArrears;
	}


	public Date getFcsDate() {
		return fcsDate;
	}


	public void setFcsDate(Date fcsDate) {
		this.fcsDate = fcsDate;
	}
	public Short getFcsUnFinished() {
		return fcsUnFinished;
	}

	public void setFcsUnFinished(Short fcsUnFinished) {
		this.fcsUnFinished = fcsUnFinished;
	}

	public String getFcsRemark() {
		return fcsRemark;
	}


	public void setFcsRemark(String fcsRemark) {
		this.fcsRemark = fcsRemark;
	}


	public Double getFcsPayable() {
		return fcsPayable;
	}


	public void setFcsPayable(Double fcsPayable) {
		this.fcsPayable = fcsPayable;
	}

	public Double getFcsPayment() {
		return fcsPayment;
	}

	public void setFcsPayment(Double fcsPayment) {
		this.fcsPayment = fcsPayment;
	}

//	public Double getFcsReserve() {
//		return fcsReserve;
//	}
//
//	public void setFcsReserve(Double fcsReserve) {
//		this.fcsReserve = fcsReserve;
//	}
}