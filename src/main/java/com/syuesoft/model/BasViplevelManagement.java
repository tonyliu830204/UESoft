package com.syuesoft.model;

/**
 * BasViplevelManagement entity. @author MyEclipse Persistence Tools
 */

public class BasViplevelManagement extends BaseBean {

	// Fields

	private Integer viptypeId;
	private String viptypeName;
	private String projectDiscount;
	private String workhoureDiscount;
	private String scoreRate;

	// Constructors

	/** default constructor */
	public BasViplevelManagement() {
	}

	/** full constructor */
	public BasViplevelManagement(String viptypeName, String projectDiscount,
			String workhoureDiscount, String scoreRate) {
		this.viptypeName = viptypeName;
		this.projectDiscount = projectDiscount;
		this.workhoureDiscount = workhoureDiscount;
		this.scoreRate = scoreRate;
	}

	// Property accessors

	public Integer getViptypeId() {
		return this.viptypeId;
	}

	public void setViptypeId(Integer viptypeId) {
		this.viptypeId = viptypeId;
	}

	public String getViptypeName() {
		return this.viptypeName;
	}

	public void setViptypeName(String viptypeName) {
		this.viptypeName = viptypeName;
	}

	public String getProjectDiscount() {
		return this.projectDiscount;
	}

	public void setProjectDiscount(String projectDiscount) {
		this.projectDiscount = projectDiscount;
	}

	public String getWorkhoureDiscount() {
		return this.workhoureDiscount;
	}

	public void setWorkhoureDiscount(String workhoureDiscount) {
		this.workhoureDiscount = workhoureDiscount;
	}

	public String getScoreRate() {
		return this.scoreRate;
	}

	public void setScoreRate(String scoreRate) {
		this.scoreRate = scoreRate;
	}

}