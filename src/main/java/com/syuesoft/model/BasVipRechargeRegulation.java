package com.syuesoft.model;

/**
 * BasVipRechargeRegulation entity. @author MyEclipse Persistence Tools
 */

public class BasVipRechargeRegulation extends BaseBean {

	// Fields

	private Integer recRulId;
	private Integer recRulName;
	private Long givInteRatio;
	private Long givAmountRatio;
	private Integer recRulNameEnd;
	private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasVipRechargeRegulation() {
	}

	/** full constructor */
	public BasVipRechargeRegulation(Integer recRulName, Long givInteRatio,
			Long givAmountRatio,Integer recRulNameEnd) {
		this.recRulName = recRulName;
		this.givInteRatio = givInteRatio;
		this.givAmountRatio = givAmountRatio;
		this.recRulNameEnd = recRulNameEnd;
	}

	// Property accessors

	public Integer getRecRulId() {
		return this.recRulId;
	}

	public void setRecRulId(Integer recRulId) {
		this.recRulId = recRulId;
	}

	public Integer getRecRulName() {
		return this.recRulName;
	}

	public void setRecRulName(Integer recRulName) {
		this.recRulName = recRulName;
	}

	public Long getGivInteRatio() {
		return this.givInteRatio;
	}

	public void setGivInteRatio(Long givInteRatio) {
		this.givInteRatio = givInteRatio;
	}

	public Long getGivAmountRatio() {
		return this.givAmountRatio;
	}

	public void setGivAmountRatio(Long givAmountRatio) {
		this.givAmountRatio = givAmountRatio;
	}

	public Integer getRecRulNameEnd() {
		return recRulNameEnd;
	}

	public void setRecRulNameEnd(Integer recRulNameEnd) {
		this.recRulNameEnd = recRulNameEnd;
	}
}