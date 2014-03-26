package com.syuesoft.model;

/**
 * StSellOrderitem entity. @author MyEclipse Persistence Tools
 */

public class StSellOrderitem extends BaseBean {

	// Fields

	private Integer selldIndex;
	private StSellOrder stSellOrder;
	private String partsId;
	private Double selldCnt;
	private Double selldPrice;
	private Double selldAmount;
	private Double selldCostPrice;
	private Double selldCostAmount;
	private Double selldCutRate;
	private String sellRemark;
	private Double partsNowCount;
	private Short storeId;

	// Constructors

	/** default constructor */
	public StSellOrderitem() {
	}

	/** full constructor */
	public StSellOrderitem(StSellOrder stSellOrder, String partsId,
			Double selldCnt, Double selldPrice, Double selldAmount,
			Double selldCostPrice, Double selldCostAmount, Double selldCutRate,
			String sellRemark, Short storeId) {
		this.stSellOrder = stSellOrder;
		this.partsId = partsId;
		this.selldCnt = selldCnt;
		this.selldPrice = selldPrice;
		this.selldAmount = selldAmount;
		this.selldCostPrice = selldCostPrice;
		this.selldCostAmount = selldCostAmount;
		this.selldCutRate = selldCutRate;
		this.sellRemark = sellRemark;
		this.storeId = storeId;
	}

	public Integer getSelldIndex() {
		return this.selldIndex;
	}

	public Double getPartsNowCount() {
		return partsNowCount;
	}

	public void setPartsNowCount(Double partsNowCount) {
		this.partsNowCount = partsNowCount;
	}

	public void setSelldIndex(Integer selldIndex) {
		this.selldIndex = selldIndex;
	}

	public StSellOrder getStSellOrder() {
		return this.stSellOrder;
	}

	public void setStSellOrder(StSellOrder stSellOrder) {
		this.stSellOrder = stSellOrder;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Double getSelldCnt() {
		return this.selldCnt;
	}

	public void setSelldCnt(Double selldCnt) {
		this.selldCnt = selldCnt;
	}

	public Double getSelldPrice() {
		return this.selldPrice;
	}

	public void setSelldPrice(Double selldPrice) {
		this.selldPrice = selldPrice;
	}

	public Double getSelldAmount() {
		return this.selldAmount;
	}

	public void setSelldAmount(Double selldAmount) {
		this.selldAmount = selldAmount;
	}

	public Double getSelldCostPrice() {
		return this.selldCostPrice;
	}

	public void setSelldCostPrice(Double selldCostPrice) {
		this.selldCostPrice = selldCostPrice;
	}

	public Double getSelldCostAmount() {
		return this.selldCostAmount;
	}

	public void setSelldCostAmount(Double selldCostAmount) {
		this.selldCostAmount = selldCostAmount;
	}

	public Double getSelldCutRate() {
		return this.selldCutRate;
	}

	public void setSelldCutRate(Double selldCutRate) {
		this.selldCutRate = selldCutRate;
	}

	public String getSellRemark() {
		return this.sellRemark;
	}

	public void setSellRemark(String sellRemark) {
		this.sellRemark = sellRemark;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

}