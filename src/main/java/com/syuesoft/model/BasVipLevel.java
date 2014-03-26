package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasVipLevel entity. @author MyEclipse Persistence Tools
 */

public class BasVipLevel extends BaseBean{

	// Fields

	private Integer vipLevelId;
	private String vipLevelName;
	private String vipLevelNote;
	private Set basVipDiscountRegulations = new HashSet(0);
	private Set basVipIntegralRegulations = new HashSet(0);
	private Set basVipInfors = new HashSet(0);
	private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasVipLevel() {
	}

	/** full constructor */
	public BasVipLevel(String vipLevelName, String vipLevelNote,
			Set basVipDiscountRegulations, Set basVipIntegralRegulations,
			Set basVipInfors) {
		this.vipLevelName = vipLevelName;
		this.vipLevelNote = vipLevelNote;
		this.basVipDiscountRegulations = basVipDiscountRegulations;
		this.basVipIntegralRegulations = basVipIntegralRegulations;
		this.basVipInfors = basVipInfors;
	}

	// Property accessors

	public Integer getVipLevelId() {
		return this.vipLevelId;
	}

	public void setVipLevelId(Integer vipLevelId) {
		this.vipLevelId = vipLevelId;
	}

	public String getVipLevelName() {
		return this.vipLevelName;
	}

	public void setVipLevelName(String vipLevelName) {
		this.vipLevelName = vipLevelName;
	}

	public String getVipLevelNote() {
		return this.vipLevelNote;
	}

	public void setVipLevelNote(String vipLevelNote) {
		this.vipLevelNote = vipLevelNote;
	}

	public Set getBasVipDiscountRegulations() {
		return this.basVipDiscountRegulations;
	}

	public void setBasVipDiscountRegulations(Set basVipDiscountRegulations) {
		this.basVipDiscountRegulations = basVipDiscountRegulations;
	}

	public Set getBasVipIntegralRegulations() {
		return this.basVipIntegralRegulations;
	}

	public void setBasVipIntegralRegulations(Set basVipIntegralRegulations) {
		this.basVipIntegralRegulations = basVipIntegralRegulations;
	}

	public Set getBasVipInfors() {
		return this.basVipInfors;
	}

	public void setBasVipInfors(Set basVipInfors) {
		this.basVipInfors = basVipInfors;
	}

}