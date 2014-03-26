package com.syuesoft.model;

/**
 * BasCarStatus entity. @author MyEclipse Persistence Tools
 */

public class BasCarStatus extends BaseBean{

	// Fields

	private Short statusId;
	private String statusName;
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
	public BasCarStatus() {
	}

	public BasCarStatus(String statusReason, String statusName, String remark) {
		this.statusName = statusName;
		this.remark = remark;
	}

	// Property accessors

	public Short getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Short statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}