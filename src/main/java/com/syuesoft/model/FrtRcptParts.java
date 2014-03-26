package com.syuesoft.model;

/**
 * FrtRcptParts entity. @author MyEclipse Persistence Tools
 */

public class FrtRcptParts extends BaseBean {

	// Fields

	private Integer rcptpartsIndex;
	private BasClaimsType basClaimsType;
	private FrtReception frtReception;
	private BasRepairType basRepairType;
	private String partsId;
	private String partsName;
	private Double partsNum;
	private Short punitId;
	private Double partsRepairPrice;
	private Double partsAmount;
	private String partsRemark;
	private Short storeId;
	private Short progress;
	private Short rcptpartsFlg;
  private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public FrtRcptParts() {
	}

	/** full constructor */
	public FrtRcptParts(BasClaimsType basClaimsType, FrtReception frtReception, BasRepairType basRepairType, String partsId, String partsName, Double partsNum, Short punitId,
			Double partsRepairPrice, Double partsAmount, String partsRemark, Short storeId) {
		this.basClaimsType = basClaimsType;
		this.frtReception = frtReception;
		this.basRepairType = basRepairType;
		this.partsId = partsId;
		this.partsName = partsName;
		this.partsNum = partsNum;
		this.punitId = punitId;
		this.partsRepairPrice = partsRepairPrice;
		this.partsAmount = partsAmount;
		this.partsRemark = partsRemark;
		this.storeId = storeId;
	}

	// Property accessors

	public Integer getRcptpartsIndex() {
		return this.rcptpartsIndex;
	}

	public void setRcptpartsIndex(Integer rcptpartsIndex) {
		this.rcptpartsIndex = rcptpartsIndex;
	}

	public BasClaimsType getBasClaimsType() {
		return this.basClaimsType;
	}

	public void setBasClaimsType(BasClaimsType basClaimsType) {
		this.basClaimsType = basClaimsType;
	}

	public FrtReception getFrtReception() {
		return this.frtReception;
	}

	public void setFrtReception(FrtReception frtReception) {
		this.frtReception = frtReception;
	}

	public BasRepairType getBasRepairType() {
		return this.basRepairType;
	}

	public void setBasRepairType(BasRepairType basRepairType) {
		this.basRepairType = basRepairType;
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

	public Short getPunitId() {
		return this.punitId;
	}

	public void setPunitId(Short punitId) {
		this.punitId = punitId;
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

	public String getPartsRemark() {
		return this.partsRemark;
	}

	public void setPartsRemark(String partsRemark) {
		this.partsRemark = partsRemark;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public Short getProgress() {
		return progress;
	}

	public void setProgress(Short progress) {
		this.progress = progress;
	}

	public Short getRcptpartsFlg() {
		return rcptpartsFlg;
	}

	public void setRcptpartsFlg(Short rcptpartsFlg) {
		this.rcptpartsFlg = rcptpartsFlg;
	}

	
}