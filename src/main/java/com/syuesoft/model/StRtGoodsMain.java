package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StRtGoodsMain entity. @author MyEclipse Persistence Tools
 */


public class StRtGoodsMain extends BaseBean {

	// Fields

	private String strtgmId;
	private Date strtgmDate;
	private Short stfId;
	private Double strtgmSumCost;
	private Double strtgmSumNoCost;
	private Short storeId;
	private Boolean strtgmPaymentStatus;
	private Boolean strtgmCheckStatus;
	private Boolean strtgmStatus;
	private String strtgmRemark;
	private Double numTotal;
	private String storageId;
	private Short relcampId;
	private Set stRtGoodsDetails = new HashSet(0);
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public StRtGoodsMain() {
	}

	/** full constructor */
	public StRtGoodsMain(Date strtgmDate, Short stfId,
			Double strtgmSumCost, Short storeId,
			Boolean strtgmPaymentStatus, Boolean strtgmCheckStatus,
			Boolean strtgmStatus, String strtgmRemark, Double numTotal,
			String storageId, Short relcampId, Set stRtGoodsDetails) {
		this.strtgmDate = strtgmDate;
		this.stfId = stfId;
		this.strtgmSumCost = strtgmSumCost;
		this.storeId = storeId;
		this.strtgmPaymentStatus = strtgmPaymentStatus;
		this.strtgmCheckStatus = strtgmCheckStatus;
		this.strtgmStatus = strtgmStatus;
		this.strtgmRemark = strtgmRemark;
		this.numTotal = numTotal;
		this.storageId = storageId;
		this.relcampId = relcampId;
		this.stRtGoodsDetails = stRtGoodsDetails;
	}

	public Double getStrtgmSumNoCost() {
		return strtgmSumNoCost;
	}

	public void setStrtgmSumNoCost(Double strtgmSumNoCost) {
		this.strtgmSumNoCost = strtgmSumNoCost;
	}

	public String getStrtgmId() {
		return strtgmId;
	}

	public void setStrtgmId(String strtgmId) {
		this.strtgmId = strtgmId;
	}

	public Date getStrtgmDate() {
		return strtgmDate;
	}

	public void setStrtgmDate(Date strtgmDate) {
		this.strtgmDate = strtgmDate;
	}

	public Short getStfId() {
		return stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public Double getStrtgmSumCost() {
		return strtgmSumCost;
	}

	public void setStrtgmSumCost(Double strtgmSumCost) {
		this.strtgmSumCost = strtgmSumCost;
	}

	public Short getStoreId() {
		return storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public Boolean getStrtgmPaymentStatus() {
		return strtgmPaymentStatus;
	}

	public void setStrtgmPaymentStatus(Boolean strtgmPaymentStatus) {
		this.strtgmPaymentStatus = strtgmPaymentStatus;
	}

	public Boolean getStrtgmCheckStatus() {
		return strtgmCheckStatus;
	}

	public void setStrtgmCheckStatus(Boolean strtgmCheckStatus) {
		this.strtgmCheckStatus = strtgmCheckStatus;
	}

	public Boolean getStrtgmStatus() {
		return strtgmStatus;
	}

	public void setStrtgmStatus(Boolean strtgmStatus) {
		this.strtgmStatus = strtgmStatus;
	}

	public String getStrtgmRemark() {
		return strtgmRemark;
	}

	public void setStrtgmRemark(String strtgmRemark) {
		this.strtgmRemark = strtgmRemark;
	}

	public Double getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(Double numTotal) {
		this.numTotal = numTotal;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public Short getRelcampId() {
		return relcampId;
	}

	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}

	public Set getStRtGoodsDetails() {
		return stRtGoodsDetails;
	}

	public void setStRtGoodsDetails(Set stRtGoodsDetails) {
		this.stRtGoodsDetails = stRtGoodsDetails;
	}


}