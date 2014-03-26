package com.syuesoft.model;

/**
 * BasVipWeekIntegralRegulation entity. @author MyEclipse Persistence Tools
 */

public class BasVipWeekIntegralRegulation extends BaseBean {

	// Fields

	private Integer weekId;
	private String weekInteReId;
	private Long weekInteRatio;
	private String weekNote;

	// Constructors

	/** default constructor */
	public BasVipWeekIntegralRegulation() {
	}

	/** full constructor */
	public BasVipWeekIntegralRegulation(String weekInteReId,
			Long weekInteRatio, String weekNote) {
		this.weekInteReId = weekInteReId;
		this.weekInteRatio = weekInteRatio;
		this.weekNote = weekNote;
	}

	// Property accessors

	public Integer getWeekId() {
		return this.weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public String getWeekInteReId() {
		return this.weekInteReId;
	}

	public void setWeekInteReId(String weekInteReId) {
		this.weekInteReId = weekInteReId;
	}

	public Long getWeekInteRatio() {
		return this.weekInteRatio;
	}

	public void setWeekInteRatio(Long weekInteRatio) {
		this.weekInteRatio = weekInteRatio;
	}

	public String getWeekNote() {
		return this.weekNote;
	}

	public void setWeekNote(String weekNote) {
		this.weekNote = weekNote;
	}

}