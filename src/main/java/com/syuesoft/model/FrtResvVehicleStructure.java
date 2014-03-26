package com.syuesoft.model;

/**
 * FrtResvVehicleStructure entity. @author MyEclipse Persistence Tools
 */

public class FrtResvVehicleStructure extends BaseBean {

	// Fields

	private Short id;
	private FrtResevation frtResevation;
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
	public FrtResvVehicleStructure() {
	}

	/** minimal constructor */
	public FrtResvVehicleStructure(Short id) {
		this.id = id;
	}

	/** full constructor */
	public FrtResvVehicleStructure(Short id, FrtResevation frtResevation, String code, String name, String state) {
		this.id = id;
		this.frtResevation = frtResevation;
		this.code = code;
		this.name = name;
		this.state = state;
	}

	// Property accessors

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public FrtResevation getFrtResevation() {
		return this.frtResevation;
	}

	public void setFrtResevation(FrtResevation frtResevation) {
		this.frtResevation = frtResevation;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}