package com.syuesoft.model;

/**
 * BasCarSellers entity. @author MyEclipse Persistence Tools
 */

public class BasCarSellers extends BaseBean {

	// Fields

	private Short slsId;
	private String slsName;
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
	public BasCarSellers() {
	}

	/** minimal constructor */
	public BasCarSellers(Short slsId) {
		this.slsId = slsId;
	}

	/** full constructor */
	public BasCarSellers(Short slsId, String slsName, String remark) {
		this.slsId = slsId;
		this.slsName = slsName;
		this.remark = remark;
	}

	// Property accessors

	public Short getSlsId() {
		return this.slsId;
	}

	public void setSlsId(Short slsId) {
		this.slsId = slsId;
	}

	public String getSlsName() {
		return this.slsName;
	}

	public void setSlsName(String slsName) {
		this.slsName = slsName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}