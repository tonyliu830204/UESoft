package com.syuesoft.model;

/**
 * BasPartsState entity. @author MyEclipse Persistence Tools
 */

public class BasPartsState extends BaseBean{

	// Fields

	private Short stateId;
	private String stateName;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
	public BasPartsState() {
	}

	/** minimal constructor */
	public BasPartsState(Short stateId) {
		this.stateId = stateId;
	}

	/** full constructor */
	public BasPartsState(Short stateId, String stateName) {
		this.stateId = stateId;
		this.stateName = stateName;
	}

	// Property accessors

	public Short getStateId() {
		return this.stateId;
	}

	public void setStateId(Short stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}