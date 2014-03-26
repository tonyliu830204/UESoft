package com.syuesoft.model;

/**
 * BasPartsUnit entity. @author MyEclipse Persistence Tools
 */

public class BasPartsUnit extends BaseBean{

	// Fields

	private Short punitId;
	private String punitName;
	private String remark;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
	public BasPartsUnit() {
	}

	/** minimal constructor */
	public BasPartsUnit(Short punitId) {
		this.punitId = punitId;
	}

	/** full constructor */
	public BasPartsUnit(Short punitId, String punitName, String remark) {
		this.punitId = punitId;
		this.punitName = punitName;
		this.remark = remark;
	}

	// Property accessors

	public Short getPunitId() {
		return this.punitId;
	}

	public void setPunitId(Short punitId) {
		this.punitId = punitId;
	}

	public String getPunitName() {
		return this.punitName;
	}

	public void setPunitName(String punitName) {
		this.punitName = punitName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}