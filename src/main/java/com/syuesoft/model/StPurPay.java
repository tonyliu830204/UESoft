package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StPurPay entity. @author MyEclipse Persistence Tools
 */

public class StPurPay extends BaseBean {

	// Fields

	private String purPayId;
	private String cerNo;
	private Date payDate;
	private Boolean payment;
	private Double unpaidTotalAmount;
	private Double paidTotalAmount;
	private Double totalPaid;
	private Double perpaid;
	private String prepayId;
	private Set stPurPayitems = new HashSet(0);

	// Constructors

	/** default constructor */
	public StPurPay() {
	}

	/** minimal constructor */
	public StPurPay(String purPayId) {
		this.purPayId = purPayId;
	}

	/** full constructor */
	public StPurPay(String purPayId, String cerNo, Date payDate,
			Boolean payment, Double unpaidTotalAmount, Double paidTotalAmount,
			Double totalPaid, Double perpaid, String prepayId, Set stPurPayitems) {
		this.purPayId = purPayId;
		this.cerNo = cerNo;
		this.payDate = payDate;
		this.payment = payment;
		this.unpaidTotalAmount = unpaidTotalAmount;
		this.paidTotalAmount = paidTotalAmount;
		this.totalPaid = totalPaid;
		this.perpaid = perpaid;
		this.prepayId = prepayId;
		this.stPurPayitems = stPurPayitems;
	}

	// Property accessors

	public String getPurPayId() {
		return this.purPayId;
	}

	public void setPurPayId(String purPayId) {
		this.purPayId = purPayId;
	}

	public String getCerNo() {
		return this.cerNo;
	}

	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}

	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Boolean getPayment() {
		return this.payment;
	}

	public void setPayment(Boolean payment) {
		this.payment = payment;
	}

	public Double getUnpaidTotalAmount() {
		return this.unpaidTotalAmount;
	}

	public void setUnpaidTotalAmount(Double unpaidTotalAmount) {
		this.unpaidTotalAmount = unpaidTotalAmount;
	}

	public Double getPaidTotalAmount() {
		return this.paidTotalAmount;
	}

	public void setPaidTotalAmount(Double paidTotalAmount) {
		this.paidTotalAmount = paidTotalAmount;
	}

	public Double getTotalPaid() {
		return this.totalPaid;
	}

	public void setTotalPaid(Double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public Double getPerpaid() {
		return this.perpaid;
	}

	public void setPerpaid(Double perpaid) {
		this.perpaid = perpaid;
	}

	public String getPrepayId() {
		return this.prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public Set getStPurPayitems() {
		return this.stPurPayitems;
	}

	public void setStPurPayitems(Set stPurPayitems) {
		this.stPurPayitems = stPurPayitems;
	}

}