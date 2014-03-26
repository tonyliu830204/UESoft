package com.syuesoft.model;

/**
 * BasVipClassification entity. @author MyEclipse Persistence Tools
 */

public class BasVipClassification extends BaseBean{

	// Fields

	private Integer vipClassificationId;
	private String vipClassificationName;
	private String vipClassificationDicount;
	private String vipClassificationCoefficient;

	// Constructors

	/** default constructor */
	public BasVipClassification() {
	}

	/** full constructor */
	public BasVipClassification(String vipClassificationName,
			String vipClassificationDicount, String vipClassificationCoefficient) {
		this.vipClassificationName = vipClassificationName;
		this.vipClassificationDicount = vipClassificationDicount;
		this.vipClassificationCoefficient = vipClassificationCoefficient;
	}

	// Property accessors

	public Integer getVipClassificationId() {
		return this.vipClassificationId;
	}

	public void setVipClassificationId(Integer vipClassificationId) {
		this.vipClassificationId = vipClassificationId;
	}

	public String getVipClassificationName() {
		return this.vipClassificationName;
	}

	public void setVipClassificationName(String vipClassificationName) {
		this.vipClassificationName = vipClassificationName;
	}

	public String getVipClassificationDicount() {
		return this.vipClassificationDicount;
	}

	public void setVipClassificationDicount(String vipClassificationDicount) {
		this.vipClassificationDicount = vipClassificationDicount;
	}

	public String getVipClassificationCoefficient() {
		return this.vipClassificationCoefficient;
	}

	public void setVipClassificationCoefficient(
			String vipClassificationCoefficient) {
		this.vipClassificationCoefficient = vipClassificationCoefficient;
	}

}