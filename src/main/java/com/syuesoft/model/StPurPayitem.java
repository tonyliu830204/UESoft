package com.syuesoft.model;

/**
 * StPurPayitem entity. @author MyEclipse Persistence Tools
 */

public class StPurPayitem extends BaseBean {

	// Fields

	private String partsId;
	private StPurPay stPurPay;
	private Integer partsCount;
	private Double unitPrice;
	private Double itemAmount;
	private String itemRemark;

	// Constructors

	/** default constructor */
	public StPurPayitem() {
	}

	/** minimal constructor */
	public StPurPayitem(String partsId) {
		this.partsId = partsId;
	}

	/** full constructor */
	public StPurPayitem(String partsId, StPurPay stPurPay, Integer partsCount,
			Double unitPrice, Double itemAmount, String itemRemark) {
		this.partsId = partsId;
		this.stPurPay = stPurPay;
		this.partsCount = partsCount;
		this.unitPrice = unitPrice;
		this.itemAmount = itemAmount;
		this.itemRemark = itemRemark;
	}

	// Property accessors

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public StPurPay getStPurPay() {
		return this.stPurPay;
	}

	public void setStPurPay(StPurPay stPurPay) {
		this.stPurPay = stPurPay;
	}

	public Integer getPartsCount() {
		return this.partsCount;
	}

	public void setPartsCount(Integer partsCount) {
		this.partsCount = partsCount;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getItemAmount() {
		return this.itemAmount;
	}

	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}

	public String getItemRemark() {
		return this.itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

}