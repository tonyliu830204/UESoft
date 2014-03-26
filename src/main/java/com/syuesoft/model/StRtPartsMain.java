package com.syuesoft.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StRtPartsMain entity. @author MyEclipse Persistence Tools
 */

public class StRtPartsMain extends BaseBean {

	// Fields

	private String strtpmId;
	private Date strtpmDate;
	private String receptionId;
	private String strtpmType;
	private Short stfId;
	private Double strtpmSumCnt;
	private Short storeId;
	private String strtpmRemark;
	private Short manager;
	private Double strtpmCostAmont;
	private Double strtpmAmont;
	private Set stRtPartsDetails = new HashSet(0);
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public StRtPartsMain() {
	}

	/** full constructor */
	public StRtPartsMain(Date strtpmDate, String receptionId,
			String strtpmType, Short stfId, Double strtpmSumCnt,
			Short storeId, String strtpmRemark, Short manager,
			Double strtpmCostAmont, Double strtpmAmont, Set stRtPartsDetails) {
		this.strtpmDate = strtpmDate;
		this.receptionId = receptionId;
		this.strtpmType = strtpmType;
		this.stfId = stfId;
		this.strtpmSumCnt = strtpmSumCnt;
		this.storeId = storeId;
		this.strtpmRemark = strtpmRemark;
		this.manager = manager;
		this.strtpmCostAmont = strtpmCostAmont;
		this.strtpmAmont = strtpmAmont;
		this.stRtPartsDetails = stRtPartsDetails;
	}

	// Property accessors

	public String getStrtpmId() {
		return this.strtpmId;
	}

	public void setStrtpmId(String strtpmId) {
		this.strtpmId = strtpmId;
	}

	public Date getStrtpmDate() {
		return this.strtpmDate;
	}

	public void setStrtpmDate(Date strtpmDate) {
		this.strtpmDate = strtpmDate;
	}

	public String getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

	public String getStrtpmType() {
		return this.strtpmType;
	}

	public void setStrtpmType(String strtpmType) {
		this.strtpmType = strtpmType;
	}

	public Short getStfId() {
		return this.stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public Double getStrtpmSumCnt() {
		return this.strtpmSumCnt;
	}

	public void setStrtpmSumCnt(Double strtpmSumCnt) {
		this.strtpmSumCnt = strtpmSumCnt;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public String getStrtpmRemark() {
		return this.strtpmRemark;
	}

	public void setStrtpmRemark(String strtpmRemark) {
		this.strtpmRemark = strtpmRemark;
	}

	public Short getManager() {
		return this.manager;
	}

	public void setManager(Short manager) {
		this.manager = manager;
	}

	public Double getStrtpmCostAmont() {
		return this.strtpmCostAmont;
	}

	public void setStrtpmCostAmont(Double strtpmCostAmont) {
		this.strtpmCostAmont = strtpmCostAmont;
	}

	public Double getStrtpmAmont() {
		return this.strtpmAmont;
	}

	public void setStrtpmAmont(Double strtpmAmont) {
		this.strtpmAmont = strtpmAmont;
	}

	public Set getStRtPartsDetails() {
		return this.stRtPartsDetails;
	}

	public void setStRtPartsDetails(Set stRtPartsDetails) {
		this.stRtPartsDetails = stRtPartsDetails;
	}

}