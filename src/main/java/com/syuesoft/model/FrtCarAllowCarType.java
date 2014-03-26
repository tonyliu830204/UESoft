package com.syuesoft.model;

/**
 * FrtCarAllowCarType entity. @author MyEclipse Persistence Tools
 */

public class FrtCarAllowCarType extends BaseBean {

	// Fields

	private Short allowCarTypeId;
	private String allowCarTypeName;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	// Constructors

	/** default constructor */
	public FrtCarAllowCarType() {
	}

	/** full constructor */
	public FrtCarAllowCarType(String allowCarTypeName) {
		this.allowCarTypeName = allowCarTypeName;
	}

	// Property accessors

	public Short getAllowCarTypeId() {
		return this.allowCarTypeId;
	}

	public void setAllowCarTypeId(Short allowCarTypeId) {
		this.allowCarTypeId = allowCarTypeId;
	}

	public String getAllowCarTypeName() {
		return this.allowCarTypeName;
	}

	public void setAllowCarTypeName(String allowCarTypeName) {
		this.allowCarTypeName = allowCarTypeName;
	}

}