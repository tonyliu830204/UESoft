package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StPreOutMain entity. @author MyEclipse Persistence Tools
 */

public class StPreOutMain extends BaseBean {

	// Fields

	private String stpremId;
	private Date stpremTime;
	private String receptionId;
	private Short stfId;
	private Double stpremSumAmount;
	private Double stpremSumCost;
	private Short stpremStfId;
	private Short stpremFlg;
	private Short storeId;
	private String stomRemark;
	private Double numTotal;
	private Set stPreOutDetails = new HashSet(0);
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public StPreOutMain() {
	}

	/** minimal constructor */
	public StPreOutMain(String stpremId, Short stfId, Short stpremFlg) {
		this.stpremId = stpremId;
		this.stfId = stfId;
		this.stpremFlg = stpremFlg;
	}

	/** full constructor */
	public StPreOutMain(String stpremId, Date stpremTime,
			String receptionId, Short stfId, Double stpremSumAmount,
			Double stpremSumCost, Short stpremStfId, Short stpremFlg,
			Short storeId, String stomRemark, Double numTotal,
			Set stPreOutDetails) {
		this.stpremId = stpremId;
		this.stpremTime = stpremTime;
		this.receptionId = receptionId;
		this.stfId = stfId;
		this.stpremSumAmount = stpremSumAmount;
		this.stpremSumCost = stpremSumCost;
		this.stpremStfId = stpremStfId;
		this.stpremFlg = stpremFlg;
		this.storeId = storeId;
		this.stomRemark = stomRemark;
		this.numTotal = numTotal;
		this.stPreOutDetails = stPreOutDetails;
	}

	// Property accessors

	public String getStpremId() {
		return this.stpremId;
	}

	public void setStpremId(String stpremId) {
		this.stpremId = stpremId;
	}

	public Date getStpremTime() {
		return this.stpremTime;
	}

	public void setStpremTime(Date stpremTime) {
		this.stpremTime = stpremTime;
	}

	public String getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

	public Short getStfId() {
		return this.stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public Double getStpremSumAmount() {
		return this.stpremSumAmount;
	}

	public void setStpremSumAmount(Double stpremSumAmount) {
		this.stpremSumAmount = stpremSumAmount;
	}

	public Double getStpremSumCost() {
		return this.stpremSumCost;
	}

	public void setStpremSumCost(Double stpremSumCost) {
		this.stpremSumCost = stpremSumCost;
	}

	public Short getStpremStfId() {
		return this.stpremStfId;
	}

	public void setStpremStfId(Short stpremStfId) {
		this.stpremStfId = stpremStfId;
	}

	public Short getStpremFlg() {
		return this.stpremFlg;
	}

	public void setStpremFlg(Short stpremFlg) {
		this.stpremFlg = stpremFlg;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public String getStomRemark() {
		return this.stomRemark;
	}

	public void setStomRemark(String stomRemark) {
		this.stomRemark = stomRemark;
	}

	public Double getNumTotal() {
		return this.numTotal;
	}

	public void setNumTotal(Double numTotal) {
		this.numTotal = numTotal;
	}

	public Set getStPreOutDetails() {
		return this.stPreOutDetails;
	}

	public void setStPreOutDetails(Set stPreOutDetails) {
		this.stPreOutDetails = stPreOutDetails;
	}

}