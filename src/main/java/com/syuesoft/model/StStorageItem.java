package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * StStorageItem entity. @author MyEclipse Persistence Tools
 */

public class StStorageItem implements java.io.Serializable {

	// Fields

	private Integer itemIndex;
	private StGoodsStorage stGoodsStorage;
	private FrtParts frtParts;
	private double partsCount;
	private String itemRemark;
	private Double taxPrice;
	private Double notaxPrice;
	private Double taxTotalamont;
	private Double notaxTotalamont;
	private Double surplusCount;
	private Set stOutInItems = new HashSet(0);

	// Constructors

	/** default constructor */
	public StStorageItem() {
	}

	/** full constructor */
	public StStorageItem(StGoodsStorage stGoodsStorage, FrtParts frtParts,
			double partsCount, String itemRemark, Double taxPrice,
			Double notaxPrice, Double taxTotalamont, Double notaxTotalamont,
			Double surplusCount, Set stOutInItems) {
		this.stGoodsStorage = stGoodsStorage;
		this.frtParts = frtParts;
		this.partsCount = partsCount;
		this.itemRemark = itemRemark;
		this.taxPrice = taxPrice;
		this.notaxPrice = notaxPrice;
		this.taxTotalamont = taxTotalamont;
		this.notaxTotalamont = notaxTotalamont;
		this.surplusCount = surplusCount;
		this.stOutInItems = stOutInItems;
	}

	// Property accessors

	public Integer getItemIndex() {
		return this.itemIndex;
	}

	public void setItemIndex(Integer itemIndex) {
		this.itemIndex = itemIndex;
	}

	public StGoodsStorage getStGoodsStorage() {
		return this.stGoodsStorage;
	}

	public void setStGoodsStorage(StGoodsStorage stGoodsStorage) {
		this.stGoodsStorage = stGoodsStorage;
	}

	public FrtParts getFrtParts() {
		return this.frtParts;
	}

	public void setFrtParts(FrtParts frtParts) {
		this.frtParts = frtParts;
	}

	public double getPartsCount() {
		return this.partsCount;
	}

	public void setPartsCount(double partsCount) {
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

	public Double getSurplusCount() {
		return this.surplusCount;
	}

	public void setSurplusCount(Double surplusCount) {
		this.surplusCount = surplusCount;
	}

	public Set getStOutInItems() {
		return this.stOutInItems;
	}

	public void setStOutInItems(Set stOutInItems) {
		this.stOutInItems = stOutInItems;
	}

}