package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasCarType entity. @author MyEclipse Persistence Tools
 */

public class BasCarType extends BaseBean{

	// Fields

	private Short ctypeId;
	private BasCarBrand basCarBrand;
	private String ctypeName;
	private Double ctypePrice;
	private Set basCarStyles = new HashSet(0);
	 private Integer enterpriseId;

     public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
	public BasCarType() {
	}

	/** minimal constructor */
	public BasCarType(String ctypeName) {
		this.ctypeName = ctypeName;
	}

	/** full constructor */
	public BasCarType(BasCarBrand basCarBrand, String ctypeName, Double ctypePrice, Set basCarStyles) {
		this.basCarBrand = basCarBrand;
		this.ctypeName = ctypeName;
		this.ctypePrice = ctypePrice;
		this.basCarStyles = basCarStyles;
	}

	// Property accessors

	public Short getCtypeId() {
		return this.ctypeId;
	}

	public void setCtypeId(Short ctypeId) {
		this.ctypeId = ctypeId;
	}

	public BasCarBrand getBasCarBrand() {
		return this.basCarBrand;
	}

	public void setBasCarBrand(BasCarBrand basCarBrand) {
		this.basCarBrand = basCarBrand;
	}

	public String getCtypeName() {
		return this.ctypeName;
	}

	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}

	public Double getCtypePrice() {
		return this.ctypePrice;
	}

	public void setCtypePrice(Double ctypePrice) {
		this.ctypePrice = ctypePrice;
	}

	public Set getBasCarStyles() {
		return this.basCarStyles;
	}

	public void setBasCarStyles(Set basCarStyles) {
		this.basCarStyles = basCarStyles;
	}

}