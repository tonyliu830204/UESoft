package com.syuesoft.model;

/**
 * BasSumscoreRegulation entity. @author MyEclipse Persistence Tools
 */

public class BasSumscoreRegulation extends BaseBean{

	// Fields

	private Integer sumscoreRegulationId;
	private String sumscoreRegulationManner;
	private String sumscoreRegulationStartamount;
	private String sumscoreRegulationEndtamount;
	private String sumscoreRegulationBonusrate;
	private String sumscoreRegulationAddtamount;
	private String sumscoreRegulationTyoe;

	// Constructors

	/** default constructor */
	public BasSumscoreRegulation() {
	}

	/** full constructor */
	public BasSumscoreRegulation(String sumscoreRegulationManner,
			String sumscoreRegulationStartamount,
			String sumscoreRegulationEndtamount,
			String sumscoreRegulationBonusrate,
			String sumscoreRegulationAddtamount, String sumscoreRegulationTyoe) {
		this.sumscoreRegulationManner = sumscoreRegulationManner;
		this.sumscoreRegulationStartamount = sumscoreRegulationStartamount;
		this.sumscoreRegulationEndtamount = sumscoreRegulationEndtamount;
		this.sumscoreRegulationBonusrate = sumscoreRegulationBonusrate;
		this.sumscoreRegulationAddtamount = sumscoreRegulationAddtamount;
		this.sumscoreRegulationTyoe = sumscoreRegulationTyoe;
	}

	// Property accessors

	public Integer getSumscoreRegulationId() {
		return this.sumscoreRegulationId;
	}

	public void setSumscoreRegulationId(Integer sumscoreRegulationId) {
		this.sumscoreRegulationId = sumscoreRegulationId;
	}

	public String getSumscoreRegulationManner() {
		return this.sumscoreRegulationManner;
	}

	public void setSumscoreRegulationManner(String sumscoreRegulationManner) {
		this.sumscoreRegulationManner = sumscoreRegulationManner;
	}

	public String getSumscoreRegulationStartamount() {
		return this.sumscoreRegulationStartamount;
	}

	public void setSumscoreRegulationStartamount(
			String sumscoreRegulationStartamount) {
		this.sumscoreRegulationStartamount = sumscoreRegulationStartamount;
	}

	public String getSumscoreRegulationEndtamount() {
		return this.sumscoreRegulationEndtamount;
	}

	public void setSumscoreRegulationEndtamount(
			String sumscoreRegulationEndtamount) {
		this.sumscoreRegulationEndtamount = sumscoreRegulationEndtamount;
	}

	public String getSumscoreRegulationBonusrate() {
		return this.sumscoreRegulationBonusrate;
	}

	public void setSumscoreRegulationBonusrate(
			String sumscoreRegulationBonusrate) {
		this.sumscoreRegulationBonusrate = sumscoreRegulationBonusrate;
	}

	public String getSumscoreRegulationAddtamount() {
		return this.sumscoreRegulationAddtamount;
	}

	public void setSumscoreRegulationAddtamount(
			String sumscoreRegulationAddtamount) {
		this.sumscoreRegulationAddtamount = sumscoreRegulationAddtamount;
	}

	public String getSumscoreRegulationTyoe() {
		return this.sumscoreRegulationTyoe;
	}

	public void setSumscoreRegulationTyoe(String sumscoreRegulationTyoe) {
		this.sumscoreRegulationTyoe = sumscoreRegulationTyoe;
	}

}