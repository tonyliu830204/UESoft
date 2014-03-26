package com.syuesoft.model;

/**
 * FrtReceptionCost entity. @author MyEclipse Persistence Tools
 */

public class FrtReceptionCost extends BaseBean {

	// Fields

	private Short costId;
	private FrtReception frtReception;
	private String costItem;
	private Double costAmount;
	private String costExplain;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public FrtReceptionCost() {
	}

	/** full constructor */
	public FrtReceptionCost(FrtReception frtReception, String costItem,
			Double costAmount, String costExplain) {
		this.frtReception = frtReception;
		this.costItem = costItem;
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

	public FrtReception getFrtReception() {
		return this.frtReception;
	}

	public void setFrtReception(FrtReception frtReception) {
		this.frtReception = frtReception;
	}

	public String getCostItem() {
		return this.costItem;
	}

	public void setCostItem(String costItem) {
		this.costItem = costItem;
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