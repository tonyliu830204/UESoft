package com.syuesoft.model;

/**
 * BasRepairPackageParts entity. @author MyEclipse Persistence Tools
 */

public class BasRepairPackageParts extends BaseBean{

	// Fields

	private Short id;
	private BasRepairPackage basRepairPackage;
	private String partsId;
	private String partsName;
	private Short punitId;
	private Double partsNum;
	private Double partsRepairPrice;
	private Double partsAmount;
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
	public BasRepairPackageParts() {
	}

	/** full constructor */
	public BasRepairPackageParts(BasRepairPackage basRepairPackage, String partsId, String partsName, Short punitId, Double partsNum, Double partsRepairPrice, Double partsAmount, Short storeId) {
		this.basRepairPackage = basRepairPackage;
		this.partsId = partsId;
		this.partsName = partsName;
		this.punitId = punitId;
		this.partsNum = partsNum;
		this.partsRepairPrice = partsRepairPrice;
		this.partsAmount = partsAmount;
		this.storeId = storeId;
	}

	// Property accessors

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public BasRepairPackage getBasRepairPackage() {
		return this.basRepairPackage;
	}

	public void setBasRepairPackage(BasRepairPackage basRepairPackage) {
		this.basRepairPackage = basRepairPackage;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Short getPunitId() {
		return this.punitId;
	}

	public void setPunitId(Short punitId) {
		this.punitId = punitId;
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

	public Short getStoreId() {
		return storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public String getPartsName() {
		return partsName;
	}

	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}

}