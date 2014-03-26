package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * VipMeal entity. @author MyEclipse Persistence Tools
 */

public class VipMeal extends BaseBean {

	// Fields

	private Integer mealId;
	private String mealName;
	private String note;
	private Set vipcardMealRs = new HashSet(0);
	private Set vipMealDs = new HashSet(0);
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public VipMeal() {
	}

	/** full constructor */
	public VipMeal(String mealName, String note, Set vipcardMealRs,
			Set vipMealDs) {
		this.mealName = mealName;
		this.note = note;
		this.vipcardMealRs = vipcardMealRs;
		this.vipMealDs = vipMealDs;
	}

	// Property accessors

	public Integer getMealId() {
		return this.mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return this.mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getVipcardMealRs() {
		return this.vipcardMealRs;
	}

	public void setVipcardMealRs(Set vipcardMealRs) {
		this.vipcardMealRs = vipcardMealRs;
	}

	public Set getVipMealDs() {
		return this.vipMealDs;
	}

	public void setVipMealDs(Set vipMealDs) {
		this.vipMealDs = vipMealDs;
	}

}