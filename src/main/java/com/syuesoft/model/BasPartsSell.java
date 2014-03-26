package com.syuesoft.model;

/**
 * BasPartsSell entity. @author MyEclipse Persistence Tools
 */

public class BasPartsSell extends BaseBean{

	// Fields

	private Short psellId;
	private String psellName;
	private Short psellPoint;
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
	public BasPartsSell() {
	}

	/** minimal constructor */
	public BasPartsSell(Short psellId) {
		this.psellId = psellId;
	}

	/** full constructor */
	public BasPartsSell(Short psellId, Short psellPoint, String psellName, String remark) {
		this.psellId = psellId;
		this.psellPoint = psellPoint;
		this.psellName = psellName;
		this.remark = remark;
	}

	// Property accessors

	public Short getPsellId() {
		return this.psellId;
	}

	public void setPsellId(Short psellId) {
		this.psellId = psellId;
	}

	public String getPsellName() {
		return this.psellName;
	}

	public void setPsellName(String psellName) {
		this.psellName = psellName;
	}

	public Short getPsellPoint(){
		return this.psellPoint;
	}
	
	public void setPsellPoint(Short psellPoint){
		this.psellPoint = psellPoint;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}