package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * StOutItem entity. @author MyEclipse Persistence Tools
 */

public class StOutItem implements java.io.Serializable {

	// Fields

	private Integer indexId;
	private StOutMain stOutMain;
	private String partsId;
	private Double itemPrice;
	private Double itemCount;
	private Short itemCharge;
	private Short discount;
	private Short storeId;
	private Short claimsType;
	private String outItemRemark;
	private Double notaxCastamont;
	private Double taxCastamont;
	private Double taxCast;
	private Double notaxCast;
	private Double amount;
	private Integer rcptpartsIndex;
	private Set stOutInItems = new HashSet(0);

	// Constructors

	/** default constructor */
	public StOutItem() {
	}

	/** full constructor */
	public StOutItem(StOutMain stOutMain, String partsId, Double itemPrice,
			Double itemCount, Short itemCharge, Short discount, Short storeId,
			Short claimsType, String outItemRemark, Double notaxCastamont,
			Double taxCastamont, Double taxCast, Double notaxCast,
			Double amount, Set stOutInItems,Integer rcptpartsIndex) {
		this.stOutMain = stOutMain;
		this.partsId = partsId;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
		this.itemCharge = itemCharge;
		this.discount = discount;
		this.storeId = storeId;
		this.claimsType = claimsType;
		this.outItemRemark = outItemRemark;
		this.notaxCastamont = notaxCastamont;
		this.taxCastamont = taxCastamont;
		this.taxCast = taxCast;
		this.notaxCast = notaxCast;
		this.amount = amount;
		this.stOutInItems = stOutInItems;
		this.rcptpartsIndex=rcptpartsIndex;
	}

	// Property accessors

	public Integer getIndexId() {
		return this.indexId;
	}

	public Integer getRcptpartsIndex() {
		return rcptpartsIndex;
	}

	public void setRcptpartsIndex(Integer rcptpartsIndex) {
		this.rcptpartsIndex = rcptpartsIndex;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public StOutMain getStOutMain() {
		return this.stOutMain;
	}

	public void setStOutMain(StOutMain stOutMain) {
		this.stOutMain = stOutMain;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Double getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public Short getItemCharge() {
		return this.itemCharge;
	}

	public void setItemCharge(Short itemCharge) {
		this.itemCharge = itemCharge;
	}

	public Short getDiscount() {
		return this.discount;
	}

	public void setDiscount(Short discount) {
		this.discount = discount;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public Short getClaimsType() {
		return this.claimsType;
	}

	public void setClaimsType(Short claimsType) {
		this.claimsType = claimsType;
	}

	public String getOutItemRemark() {
		return this.outItemRemark;
	}

	public void setOutItemRemark(String outItemRemark) {
		this.outItemRemark = outItemRemark;
	}

	public Double getNotaxCastamont() {
		return this.notaxCastamont;
	}

	public void setNotaxCastamont(Double notaxCastamont) {
		this.notaxCastamont = notaxCastamont;
	}

	public Double getTaxCastamont() {
		return this.taxCastamont;
	}

	public void setTaxCastamont(Double taxCastamont) {
		this.taxCastamont = taxCastamont;
	}

	public Double getTaxCast() {
		return this.taxCast;
	}

	public void setTaxCast(Double taxCast) {
		this.taxCast = taxCast;
	}

	public Double getNotaxCast() {
		return this.notaxCast;
	}

	public void setNotaxCast(Double notaxCast) {
		this.notaxCast = notaxCast;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Set getStOutInItems() {
		return this.stOutInItems;
	}

	public void setStOutInItems(Set stOutInItems) {
		this.stOutInItems = stOutInItems;
	}

}