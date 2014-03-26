package com.syuesoft.model;

/**
 * BasCarPhonetrackerresult entity. @author MyEclipse Persistence Tools
 */

public class BasCarPhonetrackerresult extends BaseBean {

	// Fields

	private Short carPhonetrackerresultId;
	private String carPhonetrackerresultName;
	private String carPhonetrackerresultRemark;
	 private Integer enterpriseId;

     public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
	public BasCarPhonetrackerresult() {
	}

	/** minimal constructor */
	public BasCarPhonetrackerresult(Short carPhonetrackerresultId) {
		this.carPhonetrackerresultId = carPhonetrackerresultId;
	}

	/** full constructor */
	public BasCarPhonetrackerresult(Short carPhonetrackerresultId,
			String carPhonetrackerresultName, String carPhonetrackerresultRemark) {
		this.carPhonetrackerresultId = carPhonetrackerresultId;
		this.carPhonetrackerresultName = carPhonetrackerresultName;
		this.carPhonetrackerresultRemark = carPhonetrackerresultRemark;
	}

	// Property accessors

	public Short getCarPhonetrackerresultId() {
		return this.carPhonetrackerresultId;
	}

	public void setCarPhonetrackerresultId(Short carPhonetrackerresultId) {
		this.carPhonetrackerresultId = carPhonetrackerresultId;
	}

	public String getCarPhonetrackerresultName() {
		return this.carPhonetrackerresultName;
	}

	public void setCarPhonetrackerresultName(String carPhonetrackerresultName) {
		this.carPhonetrackerresultName = carPhonetrackerresultName;
	}

	public String getCarPhonetrackerresultRemark() {
		return this.carPhonetrackerresultRemark;
	}

	public void setCarPhonetrackerresultRemark(
			String carPhonetrackerresultRemark) {
		this.carPhonetrackerresultRemark = carPhonetrackerresultRemark;
	}

}