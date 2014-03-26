package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasCarStyle entity. @author MyEclipse Persistence Tools
 */

public class BasCarStyle extends BaseBean{

	// Fields

	private Short carCstlName;
	private BasCarType basCarType;
	private String cstlName;
	private Double ctypePrice;
	private Set frtCars = new HashSet(0);
	 private Integer enterpriseId;

     public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
	public BasCarStyle() {
	}

	/** minimal constructor */
	public BasCarStyle(String cstlName) {
		this.cstlName = cstlName;
	}

	/** full constructor */
	public BasCarStyle(BasCarType basCarType, String cstlName, Double ctypePrice, Set frtCars) {
		this.basCarType = basCarType;
		this.cstlName = cstlName;
		this.ctypePrice = ctypePrice;
		this.frtCars = frtCars;
	}

	// Property accessors

	public Short getCarCstlName() {
		return this.carCstlName;
	}

	public void setCarCstlName(Short carCstlName) {
		this.carCstlName = carCstlName;
	}

	public BasCarType getBasCarType() {
		return this.basCarType;
	}

	public void setBasCarType(BasCarType basCarType) {
		this.basCarType = basCarType;
	}

	public String getCstlName() {
		return this.cstlName;
	}

	public void setCstlName(String cstlName) {
		this.cstlName = cstlName;
	}

	public Double getCtypePrice() {
		return this.ctypePrice;
	}

	public void setCtypePrice(Double ctypePrice) {
		this.ctypePrice = ctypePrice;
	}

	public Set getFrtCars() {
		return this.frtCars;
	}

	public void setFrtCars(Set frtCars) {
		this.frtCars = frtCars;
	}

}