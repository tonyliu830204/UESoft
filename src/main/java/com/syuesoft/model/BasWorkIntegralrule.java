package com.syuesoft.model;

/**
 * BasWorkIntegralrule entity. @author MyEclipse Persistence Tools
 */

public class BasWorkIntegralrule extends BaseBean {

	// Fields

	private Integer workIntegralruleId;
	private String workIntegralruleManner;
	private Integer workIntegralruleStartamount;
	private Integer workIntegralruleEndtamount;
	private String workIntegralruleBonusrate;
	private String workIntegralruleTyoe;

	// Constructors

	/** default constructor */
	public BasWorkIntegralrule() {
	}

	/** minimal constructor */
	public BasWorkIntegralrule(Integer workIntegralruleId) {
		this.workIntegralruleId = workIntegralruleId;
	}

	/** full constructor */
	public BasWorkIntegralrule(Integer workIntegralruleId,
			String workIntegralruleManner, Integer workIntegralruleStartamount,
			Integer workIntegralruleEndtamount,
			String workIntegralruleBonusrate, String workIntegralruleTyoe) {
		this.workIntegralruleId = workIntegralruleId;
		this.workIntegralruleManner = workIntegralruleManner;
		this.workIntegralruleStartamount = workIntegralruleStartamount;
		this.workIntegralruleEndtamount = workIntegralruleEndtamount;
		this.workIntegralruleBonusrate = workIntegralruleBonusrate;
		this.workIntegralruleTyoe = workIntegralruleTyoe;
	}

	// Property accessors

	public Integer getWorkIntegralruleId() {
		return this.workIntegralruleId;
	}

	public void setWorkIntegralruleId(Integer workIntegralruleId) {
		this.workIntegralruleId = workIntegralruleId;
	}

	public String getWorkIntegralruleManner() {
		return this.workIntegralruleManner;
	}

	public void setWorkIntegralruleManner(String workIntegralruleManner) {
		this.workIntegralruleManner = workIntegralruleManner;
	}

	public Integer getWorkIntegralruleStartamount() {
		return this.workIntegralruleStartamount;
	}

	public void setWorkIntegralruleStartamount(
			Integer workIntegralruleStartamount) {
		this.workIntegralruleStartamount = workIntegralruleStartamount;
	}

	public Integer getWorkIntegralruleEndtamount() {
		return this.workIntegralruleEndtamount;
	}

	public void setWorkIntegralruleEndtamount(Integer workIntegralruleEndtamount) {
		this.workIntegralruleEndtamount = workIntegralruleEndtamount;
	}

	public String getWorkIntegralruleBonusrate() {
		return this.workIntegralruleBonusrate;
	}

	public void setWorkIntegralruleBonusrate(String workIntegralruleBonusrate) {
		this.workIntegralruleBonusrate = workIntegralruleBonusrate;
	}

	public String getWorkIntegralruleTyoe() {
		return this.workIntegralruleTyoe;
	}

	public void setWorkIntegralruleTyoe(String workIntegralruleTyoe) {
		this.workIntegralruleTyoe = workIntegralruleTyoe;
	}

}