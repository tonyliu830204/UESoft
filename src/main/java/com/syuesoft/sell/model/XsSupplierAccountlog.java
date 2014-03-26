package com.syuesoft.sell.model;

import java.sql.Timestamp;

/**
 * XsSupplierAccountlog entity. @author MyEclipse Persistence Tools
 */

public class XsSupplierAccountlog implements java.io.Serializable {

	// Fields

	private Integer accountLogId;
	private XsSuppliertraderAccount xsSuppliertraderAccount;
	private Timestamp date;
	private Double receivedMoney;
	private Integer receivedWay;
	private Integer stfId;
	private Integer invoice;
	private String invoiceNum;
	private Integer examine;
	private String remark;

	// Constructors

	/** default constructor */
	public XsSupplierAccountlog() {
	}

	/** full constructor */
	public XsSupplierAccountlog(
			XsSuppliertraderAccount xsSuppliertraderAccount, Timestamp date,
			Double receivedMoney, Integer receivedWay, Integer stfId,
			Integer invoice, String invoiceNum, Integer examine, String remark) {
		this.xsSuppliertraderAccount = xsSuppliertraderAccount;
		this.date = date;
		this.receivedMoney = receivedMoney;
		this.receivedWay = receivedWay;
		this.stfId = stfId;
		this.invoice = invoice;
		this.invoiceNum = invoiceNum;
		this.examine = examine;
		this.remark = remark;
	}

	// Property accessors

	public Integer getAccountLogId() {
		return this.accountLogId;
	}

	public void setAccountLogId(Integer accountLogId) {
		this.accountLogId = accountLogId;
	}

	public XsSuppliertraderAccount getXsSuppliertraderAccount() {
		return this.xsSuppliertraderAccount;
	}

	public void setXsSuppliertraderAccount(
			XsSuppliertraderAccount xsSuppliertraderAccount) {
		this.xsSuppliertraderAccount = xsSuppliertraderAccount;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Double getReceivedMoney() {
		return this.receivedMoney;
	}

	public void setReceivedMoney(Double receivedMoney) {
		this.receivedMoney = receivedMoney;
	}

	public Integer getReceivedWay() {
		return this.receivedWay;
	}

	public void setReceivedWay(Integer receivedWay) {
		this.receivedWay = receivedWay;
	}

	public Integer getStfId() {
		return this.stfId;
	}

	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}

	public Integer getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}

	public String getInvoiceNum() {
		return this.invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public Integer getExamine() {
		return this.examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}