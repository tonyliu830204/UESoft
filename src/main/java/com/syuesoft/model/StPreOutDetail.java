package com.syuesoft.model;

/**
 * StPreOutDetail entity. @author MyEclipse Persistence Tools
 */
public class StPreOutDetail extends BaseBean {

	// Fields

	private Integer stpredIndex;
	private StPreOutMain stPreOutMain;
	private String partsId;
	private Double stpredCnt;
	private Short chargeId;
	private Boolean stpredStatus;
	private Short claimsId;
	private Short storeId;
	private Double taxcast;
	private Double notaxcast;
	private Double itemprice;
	private Double amont;
	private Double taxcastamont;
	private Double notaxcastamont;
	private String itemRemark; 

	// Constructors

	/** default constructor */
	public StPreOutDetail() {
	}

	/** minimal constructor */
	public StPreOutDetail(Integer stpredIndex, String partsId, Short chargeId,
			Short storeId,String itemRemark) {
		this.stpredIndex = stpredIndex;
		this.partsId = partsId;
		this.chargeId = chargeId;
		this.storeId = storeId;
		this.itemRemark = itemRemark;
	}

	/** full constructor */
	public StPreOutDetail(Integer stpredIndex, StPreOutMain stPreOutMain,
			String partsId, Double stpredCnt, Short chargeId,
			Boolean stpredStatus, Short claimsId, Short storeId,
			Double taxcast, Double notaxcast, Double itemprice, Double amont,
			Double taxcastamont, Double notaxcastamont,String itemRemark) {
		this.stpredIndex = stpredIndex;
		this.stPreOutMain = stPreOutMain;
		this.partsId = partsId;
		this.stpredCnt = stpredCnt;
		this.chargeId = chargeId;
		this.stpredStatus = stpredStatus;
		this.claimsId = claimsId;
		this.storeId = storeId;
		this.taxcast = taxcast;
		this.notaxcast = notaxcast;
		this.itemprice = itemprice;
		this.amont = amont;
		this.taxcastamont = taxcastamont;
		this.notaxcastamont = notaxcastamont;
		this.itemRemark = itemRemark;
	}

	// Property accessors

	public Integer getStpredIndex() {
		return this.stpredIndex;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public void setStpredIndex(Integer stpredIndex) {
		this.stpredIndex = stpredIndex;
	}

	public StPreOutMain getStPreOutMain() {
		return this.stPreOutMain;
	}

	public void setStPreOutMain(StPreOutMain stPreOutMain) {
		this.stPreOutMain = stPreOutMain;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Double getStpredCnt() {
		return this.stpredCnt;
	}

	public void setStpredCnt(Double stpredCnt) {
		this.stpredCnt = stpredCnt;
	}

	public Short getChargeId() {
		return this.chargeId;
	}

	public void setChargeId(Short chargeId) {
		this.chargeId = chargeId;
	}

	public Boolean getStpredStatus() {
		return this.stpredStatus;
	}

	public void setStpredStatus(Boolean stpredStatus) {
		this.stpredStatus = stpredStatus;
	}

	public Short getClaimsId() {
		return this.claimsId;
	}

	public void setClaimsId(Short claimsId) {
		this.claimsId = claimsId;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public Double getTaxcast() {
		return this.taxcast;
	}

	public void setTaxcast(Double taxcast) {
		this.taxcast = taxcast;
	}

	public Double getNotaxcast() {
		return this.notaxcast;
	}

	public void setNotaxcast(Double notaxcast) {
		this.notaxcast = notaxcast;
	}

	public Double getItemprice() {
		return this.itemprice;
	}

	public void setItemprice(Double itemprice) {
		this.itemprice = itemprice;
	}

	public Double getAmont() {
		return this.amont;
	}

	public void setAmont(Double amont) {
		this.amont = amont;
	}

	public Double getTaxcastamont() {
		return this.taxcastamont;
	}

	public void setTaxcastamont(Double taxcastamont) {
		this.taxcastamont = taxcastamont;
	}

	public Double getNotaxcastamont() {
		return this.notaxcastamont;
	}

	public void setNotaxcastamont(Double notaxcastamont) {
		this.notaxcastamont = notaxcastamont;
	}

}