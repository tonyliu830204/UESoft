package com.syuesoft.model;

import java.util.Date;

/**
 * StVendorAccount entity. @author MyEclipse Persistence Tools
 */

public class StVendorAccount implements java.io.Serializable {

	// Fields

	private Integer accountIndex;
	private Date accountDate;
	private String accountReceipt;
	private String receiptId;
	private Short relcampId;
	private Short operType;
	private Double recAmount;
	private Double paidAmount;
	private Double nowPaidAmount;
	private Double vendorBalance;
	private Short stfId;
	private String vendorRemark;

	// Constructors

	/** default constructor */
	public StVendorAccount() {
	}

	/** full constructor */
	public StVendorAccount(Date accountDate, String accountReceipt,
			String receiptId, Short relcampId, Short operType,
			Double recAmount, Double paidAmount,Double nowPaidAmount, Double vendorBalance,
			Short stfId, String vendorRemark) {
		this.accountDate = accountDate;
		this.accountReceipt = accountReceipt;
		this.receiptId = receiptId;
		this.relcampId = relcampId;
		this.operType = operType;
		this.recAmount = recAmount;
		this.paidAmount = paidAmount;
		this.vendorBalance = vendorBalance;
		this.stfId = stfId;
		this.vendorRemark = vendorRemark;
		this.nowPaidAmount=nowPaidAmount;
	}

	// Property accessors

	public Integer getAccountIndex() {
		return this.accountIndex;
	}

	public Double getNowPaidAmount() {
		return nowPaidAmount;
	}

	public void setNowPaidAmount(Double nowPaidAmount) {
		this.nowPaidAmount = nowPaidAmount;
	}

	public void setAccountIndex(Integer accountIndex) {
		this.accountIndex = accountIndex;
	}

	public Date getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	public String getAccountReceipt() {
		return this.accountReceipt;
	}

	public void setAccountReceipt(String accountReceipt) {
		this.accountReceipt = accountReceipt;
	}

	public String getReceiptId() {
		return this.receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public Short getRelcampId() {
		return this.relcampId;
	}

	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}

	public Short getOperType() {
		return this.operType;
	}

	public void setOperType(Short operType) {
		this.operType = operType;
	}

	public Double getRecAmount() {
		return this.recAmount;
	}

	public void setRecAmount(Double recAmount) {
		this.recAmount = recAmount;
	}

	public Double getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getVendorBalance() {
		return this.vendorBalance;
	}

	public void setVendorBalance(Double vendorBalance) {
		this.vendorBalance = vendorBalance;
	}

	public Short getStfId() {
		return this.stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public String getVendorRemark() {
		return this.vendorRemark;
	}

	public void setVendorRemark(String vendorRemark) {
		this.vendorRemark = vendorRemark;
	}

}