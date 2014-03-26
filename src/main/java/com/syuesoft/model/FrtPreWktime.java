package com.syuesoft.model;

/**
 * FrtPreWktime entity. @author MyEclipse Persistence Tools
 */

public class FrtPreWktime extends BaseBean {

	// Fields

	private Integer wktimeIndex;
	private FrtPreClearing frtPreClearing;
	private String repitemId;
	private String repitemName;
	private Double wktimeNum;
	private Double innerWktime;
	private Double wktimeAmount;
	private Short relcampId;
	private Short reptypId;
	private Short claimsType;
	private Short stfId;
	private Double settlementDiscount;
	private Short wktimeFlg;
	private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public FrtPreWktime() {
	}

	/** minimal constructor */
	public FrtPreWktime(Integer wktimeIndex) {
		this.wktimeIndex = wktimeIndex;
	}

	/** full constructor */
	public FrtPreWktime(Integer wktimeIndex, FrtPreClearing frtPreClearing, String repitemId, String repitemName, Double wktimeNum, Double innerWktime, Double wktimeAmount, Short relcampId,
			Short reptypId, Short stfId, Double settlementDiscount) {
		this.wktimeIndex = wktimeIndex;
		this.frtPreClearing = frtPreClearing;
		this.repitemId = repitemId;
		this.repitemName = repitemName;
		this.wktimeNum = wktimeNum;
		this.innerWktime = innerWktime;
		this.wktimeAmount = wktimeAmount;
		this.relcampId = relcampId;
		this.reptypId = reptypId;
		this.stfId = stfId;
		this.settlementDiscount = settlementDiscount;
	}

	// Property accessors

	public Integer getWktimeIndex() {
		return this.wktimeIndex;
	}

	public void setWktimeIndex(Integer wktimeIndex) {
		this.wktimeIndex = wktimeIndex;
	}

	public FrtPreClearing getFrtPreClearing() {
		return this.frtPreClearing;
	}

	public void setFrtPreClearing(FrtPreClearing frtPreClearing) {
		this.frtPreClearing = frtPreClearing;
	}

	public String getRepitemId() {
		return this.repitemId;
	}

	public void setRepitemId(String repitemId) {
		this.repitemId = repitemId;
	}

	public String getRepitemName() {
		return this.repitemName;
	}

	public void setRepitemName(String repitemName) {
		this.repitemName = repitemName;
	}


	public Double getWktimeNum() {
		return wktimeNum;
	}

	public void setWktimeNum(Double wktimeNum) {
		this.wktimeNum = wktimeNum;
	}

	public Double getInnerWktime() {
		return this.innerWktime;
	}

	public void setInnerWktime(Double innerWktime) {
		this.innerWktime = innerWktime;
	}

	public Double getWktimeAmount() {
		return this.wktimeAmount;
	}

	public void setWktimeAmount(Double wktimeAmount) {
		this.wktimeAmount = wktimeAmount;
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

	public Short getStfId() {
		return this.stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public Double getSettlementDiscount() {
		return this.settlementDiscount;
	}

	public void setSettlementDiscount(Double settlementDiscount) {
		this.settlementDiscount = settlementDiscount;
	}

	public Short getWktimeFlg() {
		return wktimeFlg;
	}

	public void setWktimeFlg(Short wktimeFlg) {
		this.wktimeFlg = wktimeFlg;
	}

	public Short getClaimsType() {
		return claimsType;
	}

	public void setClaimsType(Short claimsType) {
		this.claimsType = claimsType;
	}

}