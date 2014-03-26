package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasCarColor entity. @author MyEclipse Persistence Tools
 */

public class BasCarColor extends BaseBean{

	// Fields

	private Short color;
	private String colorName;
	private String remark;
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
	public BasCarColor() {
	}

	/** minimal constructor */
	public BasCarColor(String colorName) {
		this.colorName = colorName;
	}

	/** full constructor */
	public BasCarColor(String colorName, String remark, Set frtCars) {
		this.colorName = colorName;
		this.remark = remark;
		this.frtCars = frtCars;
	}

	// Property accessors

	public Short getColor() {
		return this.color;
	}

	public void setColor(Short color) {
		this.color = color;
	}

	public String getColorName() {
		return this.colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getFrtCars() {
		return this.frtCars;
	}

	public void setFrtCars(Set frtCars) {
		this.frtCars = frtCars;
	}

}