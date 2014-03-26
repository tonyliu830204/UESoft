package com.syuesoft.model;

/**
 * BasScoreContentset entity. @author MyEclipse Persistence Tools
 */

public class BasScoreContentset extends BaseBean{

	// Fields

	private Integer scoreContentsetId;
	private String scoreContentsetName;
	private String scoreContentsetDelete;

	// Constructors

	/** default constructor */
	public BasScoreContentset() {
	}

	/** full constructor */
	public BasScoreContentset(String scoreContentsetName,
			String scoreContentsetDelete) {
		this.scoreContentsetName = scoreContentsetName;
		this.scoreContentsetDelete = scoreContentsetDelete;
	}

	// Property accessors

	public Integer getScoreContentsetId() {
		return this.scoreContentsetId;
	}

	public void setScoreContentsetId(Integer scoreContentsetId) {
		this.scoreContentsetId = scoreContentsetId;
	}

	public String getScoreContentsetName() {
		return this.scoreContentsetName;
	}

	public void setScoreContentsetName(String scoreContentsetName) {
		this.scoreContentsetName = scoreContentsetName;
	}

	public String getScoreContentsetDelete() {
		return this.scoreContentsetDelete;
	}

	public void setScoreContentsetDelete(String scoreContentsetDelete) {
		this.scoreContentsetDelete = scoreContentsetDelete;
	}

}