package com.syuesoft.model;

import java.util.Date;

/**
 * FrtRcptItem entity. @author MyEclipse Persistence Tools
 */

public class FrtRcptItem extends BaseBean {

	// Fields

	private Integer rcptitemIndex;
	private BasClaimsType basClaimsType;
	private FrtReception frtReception;
	private BasRepairType basRepairType;
	private String repitemId;
	private String repitemName;
	private Double repitemNum;
	private Double rcptitemInnerTime;
	private Double repitemAmount;
	private Short stfId;
	private Date planStartTime;
	private Date planComplTime;
	private String receptionRemark;
	private Short rcptitemFlg;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public FrtRcptItem() {
	}

	/** full constructor */
	public FrtRcptItem(BasClaimsType basClaimsType, FrtReception frtReception, BasRepairType basRepairType, String repitemId, String repitemName, Double repitemNum, Double rcptitemInnerTime,
			Double repitemAmount, Short stfId, Date planStartTime, Date planComplTime, String receptionRemark) {
		this.basClaimsType = basClaimsType;
		this.frtReception = frtReception;
		this.basRepairType = basRepairType;
		this.repitemId = repitemId;
		this.repitemName = repitemName;
		this.repitemNum = repitemNum;
		this.rcptitemInnerTime = rcptitemInnerTime;
		this.repitemAmount = repitemAmount;
		this.stfId = stfId;
		this.planStartTime = planStartTime;
		this.planComplTime = planComplTime;
		this.receptionRemark = receptionRemark;
	}

	// Property accessors

	public Integer getRcptitemIndex() {
		return this.rcptitemIndex;
	}

	public void setRcptitemIndex(Integer rcptitemIndex) {
		this.rcptitemIndex = rcptitemIndex;
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

	public Double getRepitemNum() {
		return this.repitemNum;
	}

	public void setRepitemNum(Double repitemNum) {
		this.repitemNum = repitemNum;
	}

	public Double getRcptitemInnerTime() {
		return this.rcptitemInnerTime;
	}

	public void setRcptitemInnerTime(Double rcptitemInnerTime) {
		this.rcptitemInnerTime = rcptitemInnerTime;
	}

	public Double getRepitemAmount() {
		return this.repitemAmount;
	}

	public void setRepitemAmount(Double repitemAmount) {
		this.repitemAmount = repitemAmount;
	}

	public Short getStfId() {
		return this.stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public Date getPlanStartTime() {
		return this.planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}

	public Date getPlanComplTime() {
		return this.planComplTime;
	}

	public void setPlanComplTime(Date planComplTime) {
		this.planComplTime = planComplTime;
	}

	public String getReceptionRemark() {
		return this.receptionRemark;
	}

	public void setReceptionRemark(String receptionRemark) {
		this.receptionRemark = receptionRemark;
	}

	public Short getRcptitemFlg() {
		return rcptitemFlg;
	}

	public void setRcptitemFlg(Short rcptitemFlg) {
		this.rcptitemFlg = rcptitemFlg;
	}

}