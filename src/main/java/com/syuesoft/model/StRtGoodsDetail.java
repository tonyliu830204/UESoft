package com.syuesoft.model;

/**
 * StRtGoodsDetail entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class StRtGoodsDetail extends BaseBean {

	// Fields

	private Integer strtgdIndex;
	private StRtGoodsMain stRtGoodsMain;
	private String partsId;
	private Double strtgdCnt;
	private Double strtgdCostPrice;
	private Double strtgdCostAmount;
	private String strtgdRemark;
	private Double strtgdNoCostPrice;
	private Double strtgdNoCostAmount;

	// Constructors

	/** default constructor */
	public StRtGoodsDetail() {
	}

	/** minimal constructor */
	public StRtGoodsDetail(Integer strtgdIndex) {
		this.strtgdIndex = strtgdIndex;
	}

	/** full constructor */
	public StRtGoodsDetail(Integer strtgdIndex, StRtGoodsMain stRtGoodsMain,
			String partsId, Double strtgdCnt,Double strtgdCostPrice,
			Double strtgdCostAmount, String strtgdRemark) {
		this.strtgdIndex = strtgdIndex;
		this.stRtGoodsMain = stRtGoodsMain;
		this.partsId = partsId;
		this.strtgdCnt = strtgdCnt;
		this.strtgdCostPrice = strtgdCostPrice;
		this.strtgdCostAmount = strtgdCostAmount;
		this.strtgdRemark = strtgdRemark;
	}

	
	public Double getStrtgdNoCostPrice() {
		return strtgdNoCostPrice;
	}

	public void setStrtgdNoCostPrice(Double strtgdNoCostPrice) {
		this.strtgdNoCostPrice = strtgdNoCostPrice;
	}

	public Double getStrtgdNoCostAmount() {
		return strtgdNoCostAmount;
	}

	public void setStrtgdNoCostAmount(Double strtgdNoCostAmount) {
		this.strtgdNoCostAmount = strtgdNoCostAmount;
	}

	public Integer getStrtgdIndex() {
		return this.strtgdIndex;
	}

	public void setStrtgdIndex(Integer strtgdIndex) {
		this.strtgdIndex = strtgdIndex;
	}

	public StRtGoodsMain getStRtGoodsMain() {
		return this.stRtGoodsMain;
	}

	public void setStRtGoodsMain(StRtGoodsMain stRtGoodsMain) {
		this.stRtGoodsMain = stRtGoodsMain;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Double getStrtgdCnt() {
		return this.strtgdCnt;
	}

	public void setStrtgdCnt(Double strtgdCnt) {
		this.strtgdCnt = strtgdCnt;
	}

	public Double getStrtgdCostPrice() {
		return this.strtgdCostPrice;
	}

	public void setStrtgdCostPrice(Double strtgdCostPrice) {
		this.strtgdCostPrice = strtgdCostPrice;
	}

	public Double getStrtgdCostAmount() {
		return this.strtgdCostAmount;
	}

	public void setStrtgdCostAmount(Double strtgdCostAmount) {
		this.strtgdCostAmount = strtgdCostAmount;
	}

	public String getStrtgdRemark() {
		return this.strtgdRemark;
	}

	public void setStrtgdRemark(String strtgdRemark) {
		this.strtgdRemark = strtgdRemark;
	}

}