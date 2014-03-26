package com.syuesoft.sell.model;

import java.sql.Timestamp;

/**
 * XsSellReceivablesDetail entity. @author MyEclipse Persistence Tools
 */

public class XsSellCollectionsDetail implements java.io.Serializable {

	// Fields

	private Integer detailsId;
	private XsSellCollections xsSellCollections;
	private String date;
	private Double receivedMoney;
	private Integer receivedWay;
	private Integer stfId;
	private Integer invoice;
	private String invoiceNum;

	private Integer examine;
	private String remark;
	private Integer detailType;
	private XsChilddictionary xsSellCollectionsDetailWay;// 
	private XsChilddictionary xsSellCollectionsDetailInvoice;// 
	private XsChilddictionary xsSellCollectionsDetailExamine;// c
	private XsChilddictionary xsSellCollectionsDetailDetailType;// 

	
	public XsChilddictionary getXsSellCollectionsDetailWay() {
		return xsSellCollectionsDetailWay;
	}

	public void setXsSellCollectionsDetailWay(
			XsChilddictionary xsSellCollectionsDetailWay) {
		this.xsSellCollectionsDetailWay = xsSellCollectionsDetailWay;
	}

	public XsChilddictionary getXsSellCollectionsDetailInvoice() {
		return xsSellCollectionsDetailInvoice;
	}

	public void setXsSellCollectionsDetailInvoice(
			XsChilddictionary xsSellCollectionsDetailInvoice) {
		this.xsSellCollectionsDetailInvoice = xsSellCollectionsDetailInvoice;
	}

	public XsChilddictionary getXsSellCollectionsDetailExamine() {
		return xsSellCollectionsDetailExamine;
	}

	public void setXsSellCollectionsDetailExamine(
			XsChilddictionary xsSellCollectionsDetailExamine) {
		this.xsSellCollectionsDetailExamine = xsSellCollectionsDetailExamine;
	}

	public XsChilddictionary getXsSellCollectionsDetailDetailType() {
		return xsSellCollectionsDetailDetailType;
	}

	public void setXsSellCollectionsDetailDetailType(
			XsChilddictionary xsSellCollectionsDetailDetailType) {
		this.xsSellCollectionsDetailDetailType = xsSellCollectionsDetailDetailType;
	}

	public Integer getDetailType() {
		return this.detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}

	// Constructors

	/** default constructor */
	public XsSellCollectionsDetail() {
	}

	/** full constructor */
	public XsSellCollectionsDetail(XsSellCollections xsSellCollections,
			String date, Double receivedMoney, Integer receivedWay,
			Integer stfId, Integer invoice, String invoiceNum, Integer examine,
			String remark) {
		this.xsSellCollections = xsSellCollections;
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

	public Integer getDetailsId() {
		return this.detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public XsSellCollections getXsSellCollections() {
		return this.xsSellCollections;
	}

	public void setXsSellCollections(XsSellCollections xsSellCollections) {
		this.xsSellCollections = xsSellCollections;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
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