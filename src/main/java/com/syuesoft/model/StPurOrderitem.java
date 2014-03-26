package com.syuesoft.model;

/**
 * StPurOrderitem entity. @author MyEclipse Persistence Tools
 */

public class StPurOrderitem extends BaseBean {

	// Fields

	private Integer stpredIndex;
	private StPurOrder stPurOrder;
	private String partsId;
	private Double partsCount;
	private String itemRemark;
	private Double taxPrice;
	private Double notaxPrice;
	private Double taxTotalamont;
	private Double notaxTotalamont;

	// Constructors

	/** default constructor */
	public StPurOrderitem() {
	}

	/** minimal constructor */
	public StPurOrderitem(String partsId) {
		this.partsId = partsId;
	}

	/** full constructor */
	public StPurOrderitem(StPurOrder stPurOrder, String partsId,
			Double partsCount, String itemRemark, Double taxPrice,
			Double notaxPrice, Double taxTotalamont, Double notaxTotalamont) {
		this.stPurOrder = stPurOrder;
		this.partsId = partsId;
		this.partsCount = partsCount;
		this.itemRemark = itemRemark;
		this.taxPrice = taxPrice;
		this.notaxPrice = notaxPrice;
		this.taxTotalamont = taxTotalamont;
		this.notaxTotalamont = notaxTotalamont;
	}

	// Property accessors

	public Integer getStpredIndex() {
		return this.stpredIndex;
	}

	public void setStpredIndex(Integer stpredIndex) {
		this.stpredIndex = stpredIndex;
	}

	public StPurOrder getStPurOrder() {
		return this.stPurOrder;
	}

	public void setStPurOrder(StPurOrder stPurOrder) {
		this.stPurOrder = stPurOrder;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Double getPartsCount() {
		return this.partsCount;
	}

	public void setPartsCount(Double partsCount) {
		this.partsCount = partsCount;
	}

	public String getItemRemark() {
		return this.itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public Double getTaxPrice() {
		return this.taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Double getNotaxPrice() {
		return this.notaxPrice;
	}

	public void setNotaxPrice(Double notaxPrice) {
		this.notaxPrice = notaxPrice;
	}

	public Double getTaxTotalamont() {
		return this.taxTotalamont;
	}

	public void setTaxTotalamont(Double taxTotalamont) {
		this.taxTotalamont = taxTotalamont;
	}

	public Double getNotaxTotalamont() {
		return this.notaxTotalamont;
	}

	public void setNotaxTotalamont(Double notaxTotalamont) {
		this.notaxTotalamont = notaxTotalamont;
	}

}