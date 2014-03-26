package com.syuesoft.model;

/**
 * FrtPreParts entity. @author MyEclipse Persistence Tools
 */

public class FrtPreParts extends BaseBean {

	// Fields

	private Integer partsIndex;
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
	private Short storeId;
	private Double partsTaxCost;
	private Double partsNoTaxCost;
	
	private Double partsRate;
	private Double partsRateAmount;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors
	

	/** default constructor */
	public FrtPreParts() {
	}

	/** minimal constructor */
	public FrtPreParts(Integer partsIndex) {
		this.partsIndex = partsIndex;
	}

	/** full constructor */
	public FrtPreParts(Integer partsIndex, FrtPreClearing frtPreClearing, String partsId, Double partsCount, Double partsPrice, Double partsAmount, Short relcampId, Short reptypId,
			Double settlementDiscount, String partsName) {
		this.partsIndex = partsIndex;
		this.frtPreClearing = frtPreClearing;
		this.partsId = partsId;
		this.partsCount = partsCount;
		this.partsPrice = partsPrice;
		this.partsAmount = partsAmount;
		this.relcampId = relcampId;
		this.reptypId = reptypId;
		this.settlementDiscount = settlementDiscount;
		this.partsName = partsName;
	}

	// Property accessors

	public Integer getPartsIndex() {
		return this.partsIndex;
	}

	public void setPartsIndex(Integer partsIndex) {
		this.partsIndex = partsIndex;
	}

	public FrtPreClearing getFrtPreClearing() {
		return this.frtPreClearing;
	}

	public void setFrtPreClearing(FrtPreClearing frtPreClearing) {
		this.frtPreClearing = frtPreClearing;
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


	public Double getPartsPrice() {
		return this.partsPrice;
	}

	public void setPartsPrice(Double partsPrice) {
		this.partsPrice = partsPrice;
	}

	public Double getPartsAmount() {
		return this.partsAmount;
	}

	public void setPartsAmount(Double partsAmount) {
		this.partsAmount = partsAmount;
	}

	public Short getRelcampId() {
		return this.relcampId;
	}

	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}

	public Short getReptypId() {
		return this.reptypId;
	}

	public void setReptypId(Short reptypId) {
		this.reptypId = reptypId;
	}

	public Double getSettlementDiscount() {
		return this.settlementDiscount;
	}

	public void setSettlementDiscount(Double settlementDiscount) {
		this.settlementDiscount = settlementDiscount;
	}

	public String getPartsName() {
		return this.partsName;
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

	public Short getClaimsType() {
		return claimsType;
	}

	public void setClaimsType(Short claimsType) {
		this.claimsType = claimsType;
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

	public Double getPartsNoTaxCost() {
		return partsNoTaxCost;
	}

	public void setPartsNoTaxCost(Double partsNoTaxCost) {
		this.partsNoTaxCost = partsNoTaxCost;
	}

	public Double getPartsTaxCost() {
		return partsTaxCost;
	}

	public void setPartsTaxCost(Double partsTaxCost) {
		this.partsTaxCost = partsTaxCost;
	}
	
}