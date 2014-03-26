package com.syuesoft.model;

/**
 * FinClaimantMainCost entity. @author MyEclipse Persistence Tools
 */

public class FinClaimantMainCost extends BaseBean {

	// Fields

	private Short costId;
	private FinClaimantMain finClaimantMain;
	private String costName;
	private Double costAmount;
	private String costExplain;

	// Constructors

	/** default constructor */
	public FinClaimantMainCost() {
	}

	/** minimal constructor */
	public FinClaimantMainCost(Short costId) {
		this.costId = costId;
	}

	public FinClaimantMainCost(Short costId, String costName,
			Double costAmount, String costExplain) {
		this.costId = costId;
		this.costName = costName;
		this.costAmount = costAmount;
		this.costExplain = costExplain;
	}

	/** full constructor */
	public FinClaimantMainCost(Short costId, FinClaimantMain finClaimantMain,
			String costName, Double costAmount, String costExplain) {
		this.costId = costId;
		this.finClaimantMain = finClaimantMain;
		this.costName = costName;
		this.costAmount = costAmount;
		this.costExplain = costExplain;
	}

	// Property accessors

	public Short getCostId() {
		return this.costId;
	}

	public void setCostId(Short costId) {
		this.costId = costId;
	}

	public FinClaimantMain getFinClaimantMain() {
		return this.finClaimantMain;
	}

	public void setFinClaimantMain(FinClaimantMain finClaimantMain) {
		this.finClaimantMain = finClaimantMain;
	}

	public String getCostName() {
		return this.costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public Double getCostAmount() {
		return this.costAmount;
	}

	public void setCostAmount(Double costAmount) {
		this.costAmount = costAmount;
	}

	public String getCostExplain() {
		return this.costExplain;
	}

	public void setCostExplain(String costExplain) {
		this.costExplain = costExplain;
	}

}