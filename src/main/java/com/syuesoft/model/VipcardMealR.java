package com.syuesoft.model;

import java.util.Date;

/**
 * VipcardMealR entity. @author MyEclipse Persistence Tools
 */

public class VipcardMealR extends BaseBean {
    private static final long serialVersionUID = 1L;
    private Integer RId;
	private VipMeal vipMeal;
	private BasVipInfor basVipInfor;
	private Integer number;
	private String mealName;
	private String mealContext;
	private String person;
	private Date createTime;


	public VipcardMealR() {
	}

	public Integer getRId() {
		return this.RId;
	}

	public void setRId(Integer RId) {
		this.RId = RId;
	}

	public VipMeal getVipMeal() {
		return this.vipMeal;
	}

	public void setVipMeal(VipMeal vipMeal) {
		this.vipMeal = vipMeal;
	}

	public BasVipInfor getBasVipInfor() {
		return this.basVipInfor;
	}

	public void setBasVipInfor(BasVipInfor basVipInfor) {
		this.basVipInfor = basVipInfor;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getMealName() {
		return this.mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getMealContext() {
		return this.mealContext;
	}

	public void setMealContext(String mealContext) {
		this.mealContext = mealContext;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}