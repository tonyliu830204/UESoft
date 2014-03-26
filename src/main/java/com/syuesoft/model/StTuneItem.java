package com.syuesoft.model;

/**
 * StTuneItem entity. @author MyEclipse Persistence Tools
 */

public class StTuneItem extends BaseBean {

	// Fields

	private Integer indexId;
	private StGoodsTune stGoodsTune;
	private Integer partsId;
	private Integer partsCount;
	private Double unitPrice;
	private Double itemAmount;
	private Boolean priceItem;
	private String itemRemark;

	// Constructors

	/** default constructor */
	public StTuneItem() {
	}

	/** minimal constructor */
	public StTuneItem(Integer indexId) {
		this.indexId = indexId;
	}

	/** full constructor */
	public StTuneItem(Integer indexId, StGoodsTune stGoodsTune,
			Integer partsId, Integer partsCount, Double unitPrice,
			Double itemAmount, Boolean priceItem, String itemRemark) {
		this.indexId = indexId;
		this.stGoodsTune = stGoodsTune;
		this.partsId = partsId;
		this.partsCount = partsCount;
		this.unitPrice = unitPrice;
		this.itemAmount = itemAmount;
		this.priceItem = priceItem;
		this.itemRemark = itemRemark;
	}

	// Property accessors

	public Integer getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public StGoodsTune getStGoodsTune() {
		return this.stGoodsTune;
	}

	public void setStGoodsTune(StGoodsTune stGoodsTune) {
		this.stGoodsTune = stGoodsTune;
	}

	public Integer getPartsId() {
		return this.partsId;
	}

	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
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

	public Boolean getPriceItem() {
		return this.priceItem;
	}

	public void setPriceItem(Boolean priceItem) {
		this.priceItem = priceItem;
	}

	public String getItemRemark() {
		return this.itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

}