package com.syuesoft.model;

/**
 * VipMealD entity. @author MyEclipse Persistence Tools
 */

public class VipMealD extends BaseBean {

	// Fields

	private Integer DId;
	private VipMeal vipMeal;
	private String mealContext;
	private String memo;

	// Constructors

	/** default constructor */
	public VipMealD() {
	}

	/** full constructor */
	public VipMealD(VipMeal vipMeal, String mealContext, String memo) {
		this.vipMeal = vipMeal;
		this.mealContext = mealContext;
		this.memo = memo;
	}

	// Property accessors

	public Integer getDId() {
		return this.DId;
	}

	public void setDId(Integer DId) {
		this.DId = DId;
	}

	public VipMeal getVipMeal() {
		return this.vipMeal;
	}

	public void setVipMeal(VipMeal vipMeal) {
		this.vipMeal = vipMeal;
	}

	public String getMealContext() {
		return this.mealContext;
	}

	public void setMealContext(String mealContext) {
		this.mealContext = mealContext;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}