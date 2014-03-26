package com.syuesoft.model;

/**
 * BasCarParts entity. @author MyEclipse Persistence Tools
 */

public class BasCarParts extends BaseBean{

	// Fields

	private Short carPartId;
	private String carPartName;//
	private String carPartRemark;
	 private Integer enterpriseId;

     public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
	public BasCarParts() {
	}

	/** minimal constructor */
	public BasCarParts(Short carPartId) {
		this.carPartId = carPartId;
	}

	/** full constructor */
	public BasCarParts(Short carPartId, String carPartName, String carPartRemark) {
		this.carPartId = carPartId;
		this.carPartName = carPartName;
		this.carPartRemark = carPartRemark;
	}

	// Property accessors

	public Short getCarPartId() {
		return this.carPartId;
	}

	public void setCarPartId(Short carPartId) {
		this.carPartId = carPartId;
	}

	public String getCarPartName() {
		return this.carPartName;
	}

	public void setCarPartName(String carPartName) {
		this.carPartName = carPartName;
	}

	public String getCarPartRemark() {
		return this.carPartRemark;
	}

	public void setCarPartRemark(String carPartRemark) {
		this.carPartRemark = carPartRemark;
	}

}