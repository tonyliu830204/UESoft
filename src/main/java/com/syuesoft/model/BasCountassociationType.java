package com.syuesoft.model;

/**
 * BasCountassociationType entity. @author MyEclipse Persistence Tools
 */

public class BasCountassociationType extends BaseBean{

	// Fields

	private Integer typeId;
	private String typeName;
	private String memo;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public BasCountassociationType() {
	}

	/** minimal constructor */
	public BasCountassociationType(Integer typeId) {
		this.typeId = typeId;
	}

	/** full constructor */
	public BasCountassociationType(Integer typeId, String typeName, String memo) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.memo = memo;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}