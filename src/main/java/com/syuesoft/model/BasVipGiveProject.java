package com.syuesoft.model;

/**
 * BasVipGiveProject entity. @author MyEclipse Persistence Tools
 */

public class BasVipGiveProject extends BaseBean{

	// Fields

	private Integer vipGpId;
	private String vipGpName;
	private String vipGpNote;
	private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasVipGiveProject() {
	}

	/** minimal constructor */
	public BasVipGiveProject(String vipGpName) {
		this.vipGpName = vipGpName;
	}

	/** full constructor */
	public BasVipGiveProject(String vipGpName, String vipGpNote) {
		this.vipGpName = vipGpName;
		this.vipGpNote = vipGpNote;
	}

	// Property accessors

	public Integer getVipGpId() {
		return this.vipGpId;
	}

	public void setVipGpId(Integer vipGpId) {
		this.vipGpId = vipGpId;
	}

	public String getVipGpName() {
		return this.vipGpName;
	}

	public void setVipGpName(String vipGpName) {
		this.vipGpName = vipGpName;
	}

	public String getVipGpNote() {
		return this.vipGpNote;
	}

	public void setVipGpNote(String vipGpNote) {
		this.vipGpNote = vipGpNote;
	}

}