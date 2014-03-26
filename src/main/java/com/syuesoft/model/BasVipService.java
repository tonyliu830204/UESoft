package com.syuesoft.model;

/**
 * BasVipService entity. @author MyEclipse Persistence Tools
 */

public class BasVipService extends BaseBean {

	// Fields

	private Integer vipId;
	private String vipName;
	private String memo;

	// Constructors

	/** default constructor */
	public BasVipService() {
	}

	/** full constructor */
	public BasVipService(String vipName, String memo) {
		this.vipName = vipName;
		this.memo = memo;
	}

	// Property accessors

	public Integer getVipId() {
		return this.vipId;
	}

	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}

	public String getVipName() {
		return this.vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}