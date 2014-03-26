package com.syuesoft.model;

/**
 * VipService entity. @author MyEclipse Persistence Tools
 */

public class VipService extends BaseBean {

	// Fields

	private Integer vipId;
	private String vipName;
	private String memo;

	// Constructors

	/** default constructor */
	public VipService() {
	}

	/** minimal constructor */
	public VipService(Integer vipId) {
		this.vipId = vipId;
	}

	/** full constructor */
	public VipService(Integer vipId, String vipName, String memo) {
		this.vipId = vipId;
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