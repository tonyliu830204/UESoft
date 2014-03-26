package com.syuesoft.frt.vo;

import com.syuesoft.model.FrtPreClearing;

public class FrtPrePartsVo {

//	private Integer partsIndex;
	private FrtPreClearing frtPreClearing;
	private String partsId;
	private Double partsCount;
	private Double partsPrice;
	private Double partsAmount;
	private Short relcampId;
	private Short reptypId;
	private Short claimsType;
	private Double settlementDiscount;
	private String partsName;
	private Short partsFlg;
	private Double partsTaxCost;
	private Double partsNoTaxCost;
	
	private Double partsRate;
	private Double partsRateAmount;
	
	private Short storeId;
	private Integer indexId;
	
	
	
	private Boolean flag=false;
	public FrtPrePartsVo() {
		// TODO Auto-generated constructor stub
	}
//	public Integer getPartsIndex() {
//		return partsIndex;
//	}
//	public void setPartsIndex(Integer partsIndex) {
//		this.partsIndex = partsIndex;
//	}
	public FrtPreClearing getFrtPreClearing() {
		return frtPreClearing;
	}
	public void setFrtPreClearing(FrtPreClearing frtPreClearing) {
		this.frtPreClearing = frtPreClearing;
	}
	public String getPartsId() {
		return partsId;
	}
	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}
	public Double getPartsCount() {
		return partsCount;
	}
	public void setPartsCount(Double partsCount) {
		this.partsCount = partsCount;
	}
	public Double getPartsPrice() {
		return partsPrice;
	}
	public void setPartsPrice(Double partsPrice) {
		this.partsPrice = partsPrice;
	}
	public Double getPartsAmount() {
		return partsAmount;
	}
	public void setPartsAmount(Double partsAmount) {
		this.partsAmount = partsAmount;
	}
	public Short getRelcampId() {
		return relcampId;
	}
	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}
	public Short getReptypId() {
		return reptypId;
	}
	public void setReptypId(Short reptypId) {
		this.reptypId = reptypId;
	}
	public Short getClaimsType() {
		return claimsType;
	}
	public void setClaimsType(Short claimsType) {
		this.claimsType = claimsType;
	}
	public Double getSettlementDiscount() {
		return settlementDiscount;
	}
	public void setSettlementDiscount(Double settlementDiscount) {
		this.settlementDiscount = settlementDiscount;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public Short getPartsFlg() {
		return partsFlg;
	}
	public void setPartsFlg(Short partsFlg) {
		this.partsFlg = partsFlg;
	}
	public Double getPartsRate() {
		return partsRate;
	}
	public void setPartsRate(Double partsRate) {
		this.partsRate = partsRate;
	}
	public Double getPartsRateAmount() {
		return partsRateAmount;
	}
	public void setPartsRateAmount(Double partsRateAmount) {
		this.partsRateAmount = partsRateAmount;
	}
	public Short getStoreId() {
		return storeId;
	}
	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}
	public Integer getIndexId() {
		return indexId;
	}
	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Double getPartsTaxCost() {
		return partsTaxCost;
	}
	public void setPartsTaxCost(Double partsTaxCost) {
		this.partsTaxCost = partsTaxCost;
	}
	public Double getPartsNoTaxCost() {
		return partsNoTaxCost;
	}
	public void setPartsNoTaxCost(Double partsNoTaxCost) {
		this.partsNoTaxCost = partsNoTaxCost;
	}

}
