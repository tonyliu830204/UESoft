package com.syuesoft.model;

/**
 * BasPartsPosition entity. @author MyEclipse Persistence Tools
 */

public class BasPartsPosition extends BaseBean{

	// Fields

	private Short posId;
	private String posName;
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
	public BasPartsPosition() {
	}

	/** minimal constructor */
	public BasPartsPosition(Short posId) {
		this.posId = posId;
	}

	/** full constructor */
	public BasPartsPosition(Short posId, String posName, String remark) {
		this.posId = posId;
		this.posName = posName;
		this.remark = remark;
	}

	// Property accessors

	public Short getPosId() {
		return this.posId;
	}

	public void setPosId(Short posId) {
		this.posId = posId;
	}

	public String getPosName() {
		return this.posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}