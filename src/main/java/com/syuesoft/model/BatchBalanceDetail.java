package com.syuesoft.model;

import java.util.Date;

/**
 * BasVipWeekIntegralRegulation entity. @author MyEclipse Persistence Tools
 */

public class BatchBalanceDetail extends BaseBean {

	// Fields
	private Integer bbdId;
	private String bbgId;
	private String strikeId;
	// Constructors

	/** default constructor */
	public BatchBalanceDetail() {
	}

	public Integer getBbdId() {
		return bbdId;
	}

	public void setBbdId(Integer bbdId) {
		this.bbdId = bbdId;
	}

	public String getBbgId() {
		return bbgId;
	}

	public void setBbgId(String bbgId) {
		this.bbgId = bbgId;
	}

	public String getStrikeId() {
		return strikeId;
	}

	public void setStrikeId(String strikeId) {
		this.strikeId = strikeId;
	}
	
}