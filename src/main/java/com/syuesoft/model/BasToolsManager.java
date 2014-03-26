package com.syuesoft.model;

import java.util.Date;

/**
 * BasToolsManager entity. @author MyEclipse Persistence Tools
 */

public class BasToolsManager extends BaseBean{

	// Fields

	private Short toolsId;
	private Date recordDate;
	private String toolsName;
	private String toolsType;
	private Short toolsUnit;
	private Short toolsState;
	private Double procurementPrice;
	private Short supplier;
	private String linkman;
	private String telphone;
	private Short buyer;

	// Constructors

	/** default constructor */
	public BasToolsManager() {
	}

	/** full constructor */
	public BasToolsManager(Date recordDate, String toolsName, String toolsType,
			Short toolsUnit, Short toolsState, Double procurementPrice,
			Short supplier, String linkman, String telphone, Short buyer) {
		this.recordDate = recordDate;
		this.toolsName = toolsName;
		this.toolsType = toolsType;
		this.toolsUnit = toolsUnit;
		this.toolsState = toolsState;
		this.procurementPrice = procurementPrice;
		this.supplier = supplier;
		this.linkman = linkman;
		this.telphone = telphone;
		this.buyer = buyer;
	}

	// Property accessors

	public Short getToolsId() {
		return this.toolsId;
	}

	public void setToolsId(Short toolsId) {
		this.toolsId = toolsId;
	}

	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getToolsName() {
		return this.toolsName;
	}

	public void setToolsName(String toolsName) {
		this.toolsName = toolsName;
	}

	public String getToolsType() {
		return this.toolsType;
	}

	public void setToolsType(String toolsType) {
		this.toolsType = toolsType;
	}

	public Short getToolsUnit() {
		return this.toolsUnit;
	}

	public void setToolsUnit(Short toolsUnit) {
		this.toolsUnit = toolsUnit;
	}

	public Short getToolsState() {
		return this.toolsState;
	}

	public void setToolsState(Short toolsState) {
		this.toolsState = toolsState;
	}

	public Double getProcurementPrice() {
		return this.procurementPrice;
	}

	public void setProcurementPrice(Double procurementPrice) {
		this.procurementPrice = procurementPrice;
	}

	public Short getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Short supplier) {
		this.supplier = supplier;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Short getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Short buyer) {
		this.buyer = buyer;
	}

}