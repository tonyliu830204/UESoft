package com.syuesoft.model;

/**
 * FrtResvVehicleStructure entity. @author MyEclipse Persistence Tools
 */

public class FrtReceptionVehicleStructure extends BaseBean {

	// Fields

	private Short id;
	private FrtReception frtReception;
	private String code;
	private String name;
	private String state;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public FrtReceptionVehicleStructure() {
	}

	/** minimal constructor */
	public FrtReceptionVehicleStructure(Short id) {
		this.id = id;
	}

	/** full constructor */
	public FrtReceptionVehicleStructure(Short id, FrtReception frtReception, String code, String name, String state) {
		this.id = id;
		this.frtReception = frtReception;
		this.code = code;
		this.name = name;
		this.state = state;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public FrtReception getFrtReception() {
		return frtReception;
	}

	public void setFrtReception(FrtReception frtReception) {
		this.frtReception = frtReception;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}