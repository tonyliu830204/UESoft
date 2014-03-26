package com.syuesoft.model;

/**
 * BasMaterialIntegralrule entity. @author MyEclipse Persistence Tools
 */

public class BasMaterialIntegralrule extends BaseBean{

	// Fields

	private Integer materialIntegralruleId;
	private String materialIntegralruleManner;
	private Integer materialIntegralruleStartamount;
	private Integer materialIntegralruleEndtamount;
	private String materialIntegralruleBonusrate;
	private String materialIntegralruleTyoe;

	// Constructors

	/** default constructor */
	public BasMaterialIntegralrule() {
	}

	/** full constructor */
	public BasMaterialIntegralrule(String materialIntegralruleManner,
			Integer materialIntegralruleStartamount,
			Integer materialIntegralruleEndtamount,
			String materialIntegralruleBonusrate,
			String materialIntegralruleTyoe) {
		this.materialIntegralruleManner = materialIntegralruleManner;
		this.materialIntegralruleStartamount = materialIntegralruleStartamount;
		this.materialIntegralruleEndtamount = materialIntegralruleEndtamount;
		this.materialIntegralruleBonusrate = materialIntegralruleBonusrate;
		this.materialIntegralruleTyoe = materialIntegralruleTyoe;
	}

	// Property accessors

	public Integer getMaterialIntegralruleId() {
		return this.materialIntegralruleId;
	}

	public void setMaterialIntegralruleId(Integer materialIntegralruleId) {
		this.materialIntegralruleId = materialIntegralruleId;
	}

	public String getMaterialIntegralruleManner() {
		return this.materialIntegralruleManner;
	}

	public void setMaterialIntegralruleManner(String materialIntegralruleManner) {
		this.materialIntegralruleManner = materialIntegralruleManner;
	}

	public Integer getMaterialIntegralruleStartamount() {
		return this.materialIntegralruleStartamount;
	}

	public void setMaterialIntegralruleStartamount(
			Integer materialIntegralruleStartamount) {
		this.materialIntegralruleStartamount = materialIntegralruleStartamount;
	}

	public Integer getMaterialIntegralruleEndtamount() {
		return this.materialIntegralruleEndtamount;
	}

	public void setMaterialIntegralruleEndtamount(
			Integer materialIntegralruleEndtamount) {
		this.materialIntegralruleEndtamount = materialIntegralruleEndtamount;
	}

	public String getMaterialIntegralruleBonusrate() {
		return this.materialIntegralruleBonusrate;
	}

	public void setMaterialIntegralruleBonusrate(
			String materialIntegralruleBonusrate) {
		this.materialIntegralruleBonusrate = materialIntegralruleBonusrate;
	}

	public String getMaterialIntegralruleTyoe() {
		return this.materialIntegralruleTyoe;
	}

	public void setMaterialIntegralruleTyoe(String materialIntegralruleTyoe) {
		this.materialIntegralruleTyoe = materialIntegralruleTyoe;
	}

}