package com.syuesoft.model;

/**
 * BasVipActivitiesDiscount entity. @author MyEclipse Persistence Tools
 */

public class BasVipActivitiesDiscount extends BaseBean{

	// Fields

	private Integer vipActDisId;
	private String vipActDisNane;
	private Integer workDis;
	private Integer materialDis;
	private Integer totalDis;
	private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasVipActivitiesDiscount() {
	}

	/** full constructor */
	public BasVipActivitiesDiscount(String vipActDisNane, Integer workDis,
			Integer materialDis, Integer totalDis) {
		this.vipActDisNane = vipActDisNane;
		this.workDis = workDis;
		this.materialDis = materialDis;
		this.totalDis = totalDis;
	}

	// Property accessors

	public Integer getVipActDisId() {
		return this.vipActDisId;
	}

	public void setVipActDisId(Integer vipActDisId) {
		this.vipActDisId = vipActDisId;
	}

	public String getVipActDisNane() {
		return this.vipActDisNane;
	}

	public void setVipActDisNane(String vipActDisNane) {
		this.vipActDisNane = vipActDisNane;
	}

	public Integer getWorkDis() {
		return this.workDis;
	}

	public void setWorkDis(Integer workDis) {
		this.workDis = workDis;
	}

	public Integer getMaterialDis() {
		return this.materialDis;
	}

	public void setMaterialDis(Integer materialDis) {
		this.materialDis = materialDis;
	}

	public Integer getTotalDis() {
		return this.totalDis;
	}

	public void setTotalDis(Integer totalDis) {
		this.totalDis = totalDis;
	}

}