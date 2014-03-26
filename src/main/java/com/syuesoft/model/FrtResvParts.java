package com.syuesoft.model;

/**
 * FrtResvParts entity. @author MyEclipse Persistence Tools
 */

public class FrtResvParts extends BaseBean {

	// Fields

	private Integer partsIndex;
	private FrtResevation frtResevation;
	private BasPartsUnit basPartsUnit;
	private String partsId;
	private String partsName;
	private Double partsNum;
	private Double partsRepairPrice;
	private Double partsAmount;
	private Short partsFlg;
	private Short storeId;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }

	// Constructors

	/** default constructor */
	public FrtResvParts() {
	}

	/** minimal constructor */
	public FrtResvParts(Integer partsIndex, String partsId) {
		this.partsIndex = partsIndex;
		this.partsId = partsId;
	}

	/** full constructor */
	public FrtResvParts(Integer partsIndex, FrtResevation frtResevation, BasPartsUnit basPartsUnit, String partsId, String partsName, Double partsNum, Double partsRepairPrice, Double partsAmount) {
		this.partsIndex = partsIndex;
		this.frtResevation = frtResevation;
		this.basPartsUnit = basPartsUnit;
		this.partsId = partsId;
		this.partsName = partsName;
		this.partsNum = partsNum;
		this.partsRepairPrice = partsRepairPrice;
		this.partsAmount = partsAmount;
	}

	// Property accessors

	public Integer getPartsIndex() {
		return this.partsIndex;
	}

	public void setPartsIndex(Integer partsIndex) {
		this.partsIndex = partsIndex;
	}

	public FrtResevation getFrtResevation() {
		return this.frtResevation;
	}

	public void setFrtResevation(FrtResevation frtResevation) {
		this.frtResevation = frtResevation;
	}

	public BasPartsUnit getBasPartsUnit() {
		return this.basPartsUnit;
	}

	public void setBasPartsUnit(BasPartsUnit basPartsUnit) {
		this.basPartsUnit = basPartsUnit;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public String getPartsName() {
		return this.partsName;
	}

	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}

	public Double getPartsNum() {
		return this.partsNum;
	}

	public void setPartsNum(Double partsNum) {
		this.partsNum = partsNum;
	}

	public Double getPartsRepairPrice() {
		return this.partsRepairPrice;
	}

	public void setPartsRepairPrice(Double partsRepairPrice) {
		this.partsRepairPrice = partsRepairPrice;
	}

	public Double getPartsAmount() {
		return this.partsAmount;
	}

	public void setPartsAmount(Double partsAmount) {
		this.partsAmount = partsAmount;
	}

	public Short getPartsFlg() {
		return partsFlg;
	}

	public void setPartsFlg(Short partsFlg) {
		this.partsFlg = partsFlg;
	}

	public Short getStoreId() {
		return storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

}