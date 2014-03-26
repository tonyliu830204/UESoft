package com.syuesoft.model;

import java.util.Date;

/**
 * FinCollectionSchedule entity. @author MyEclipse Persistence Tools
 */

public class FinCounterclaimSchedule extends BaseBean {
    private static final long serialVersionUID = 1L;
    private String fccsId;
	private String crId;
	private Short stfId;
	private String paymentId;
	private Double fccsPayable;
	private Double fccsPaymentMoney;
	private Double fccsArrears;
	private Date fccsDate;
	private Short fccsUnFinished;
	private String fccsRemark;
	private Double  fcsPayment;           //找零
	// Constructors

	/** default constructor */
	public FinCounterclaimSchedule() {
			
	}

	public String getFccsId() {
		return fccsId;
	}

	public void setFccsId(String fccsId) {
		this.fccsId = fccsId;
	}

	public String getCrId() {
		return crId;
	}

	public void setCrId(String crId) {
		this.crId = crId;
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

	public Double getFccsPaymentMoney() {
		return fccsPaymentMoney;
	}

	public void setFccsPaymentMoney(Double fccsPaymentMoney) {
		this.fccsPaymentMoney = fccsPaymentMoney;
	}

	public Double getFccsArrears() {
		return fccsArrears;
	}

	public void setFccsArrears(Double fccsArrears) {
		this.fccsArrears = fccsArrears;
	}

	public Date getFccsDate() {
		return fccsDate;
	}

	public void setFccsDate(Date fccsDate) {
		this.fccsDate = fccsDate;
	}


	public Short getFccsUnFinished() {
		return fccsUnFinished;
	}

	public void setFccsUnFinished(Short fccsUnFinished) {
		this.fccsUnFinished = fccsUnFinished;
	}

	public String getFccsRemark() {
		return fccsRemark;
	}

	public void setFccsRemark(String fccsRemark) {
		this.fccsRemark = fccsRemark;
	}

	public Double getFccsPayable() {
		return fccsPayable;
	}

	public void setFccsPayable(Double fccsPayable) {
		this.fccsPayable = fccsPayable;
	}

    public Double getFcsPayment()
    {
        return fcsPayment;
    }

    public void setFcsPayment(Double fcsPayment)
    {
        this.fcsPayment = fcsPayment;
    }
}