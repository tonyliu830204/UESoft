package com.syuesoft.model;

/**
 * FrtRepairItem entity. @author MyEclipse Persistence Tools
 */

public class FrtRepairItem extends BaseBean {

	// Fields

	private Integer repitemId;
	private String repitemName;
	private String repitemCode;
	private Short repitemSeries;
	private Double repitemAmount;
	private Double repitemTime;
	private Double internalTime;
	private Double claimTime;
	private String fitCar;
	private String repitemRemark;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public FrtRepairItem() {
	}


	// Property accessors

	public Integer getRepitemId() {
		return this.repitemId;
	}

	public void setRepitemId(Integer repitemId) {
		this.repitemId = repitemId;
	}

	public String getRepitemName() {
		return this.repitemName;
	}

	public void setRepitemName(String repitemName) {
		this.repitemName = repitemName;
	}

	public String getRepitemCode() {
		return this.repitemCode;
	}

	public void setRepitemCode(String repitemCode) {
		this.repitemCode = repitemCode;
	}
	public Double getRepitemAmount() {
		return this.repitemAmount;
	}

	public void setRepitemAmount(Double repitemAmount) {
		this.repitemAmount = repitemAmount;
	}

	public Double getRepitemTime() {
		return this.repitemTime;
	}

	public void setRepitemTime(Double repitemTime) {
		this.repitemTime = repitemTime;
	}

	public Double getInternalTime() {
		return this.internalTime;
	}

	public void setInternalTime(Double internalTime) {
		this.internalTime = internalTime;
	}

	public Double getClaimTime() {
		return this.claimTime;
	}

	public void setClaimTime(Double claimTime) {
		this.claimTime = claimTime;
	}

	public String getFitCar() {
		return this.fitCar;
	}

	public void setFitCar(String fitCar) {
		this.fitCar = fitCar;
	}

	public String getRepitemRemark() {
		return this.repitemRemark;
	}

	public void setRepitemRemark(String repitemRemark) {
		this.repitemRemark = repitemRemark;
	}


	public Short getRepitemSeries() {
		return repitemSeries;
	}


	public void setRepitemSeries(Short repitemSeries) {
		this.repitemSeries = repitemSeries;
	}

}