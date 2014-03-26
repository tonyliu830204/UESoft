package com.syuesoft.model;

/**
 * BasRelationCampanyAttr entity. @author MyEclipse Persistence Tools
 */

public class BasRelationCampanyAttr extends BaseBean{

	// Fields

	private Short attrId;
	private String attrName;
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
	public BasRelationCampanyAttr() {
	}

	/** full constructor */
	public BasRelationCampanyAttr(String attrName) {
		this.attrName = attrName;
	}

	// Property accessors

	public Short getAttrId() {
		return this.attrId;
	}

	public void setAttrId(Short attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}