package com.syuesoft.model;

/**
 * FrtPreClearingCost entity. @author MyEclipse Persistence Tools
 */

public class FrtPreClearingCost extends BaseBean {

	// Fields

	private Short costId;
	private FrtPreClearing frtPreClearing;
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
	public FrtPreClearingCost() {
	}

	/** minimal constructor */
	public FrtPreClearingCost(Short costId) {
		this.costId = costId;
	}

	/** full constructor */
	public FrtPreClearingCost(Short costId, FrtPreClearing frtPreClearing, String costItem, Double costAmount, String costExplain) {
		this.costId = costId;
		this.frtPreClearing = frtPreClearing;
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

	public FrtPreClearing getFrtPreClearing() {
		return this.frtPreClearing;
	}

	public void setFrtPreClearing(FrtPreClearing frtPreClearing) {
		this.frtPreClearing = frtPreClearing;
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