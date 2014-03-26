package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * VipType entity. @author MyEclipse Persistence Tools
 */

public class VipType extends BaseBean {

	// Fields

	private Short vipTypeId;
	private String vipName;
	private Short integralPartDiscount;
	private Short integralDiscount;
	private Double integralProprotion;
	private Set vipInfors = new HashSet(0);

	// Constructors

	/** default constructor */
	public VipType() {
	}

	/** minimal constructor */
	public VipType(Short vipTypeId) {
		this.vipTypeId = vipTypeId;
	}

	/** full constructor */
	public VipType(Short vipTypeId, String vipName, Short integralPartDiscount,
			Short integralDiscount, Double integralProprotion, Set vipInfors) {
		this.vipTypeId = vipTypeId;
		this.vipName = vipName;
		this.integralPartDiscount = integralPartDiscount;
		this.integralDiscount = integralDiscount;
		this.integralProprotion = integralProprotion;
		this.vipInfors = vipInfors;
	}

	// Property accessors

	public Short getVipTypeId() {
		return this.vipTypeId;
	}

	public void setVipTypeId(Short vipTypeId) {
		this.vipTypeId = vipTypeId;
	}

	public String getVipName() {
		return this.vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public Short getIntegralPartDiscount() {
		return this.integralPartDiscount;
	}

	public void setIntegralPartDiscount(Short integralPartDiscount) {
		this.integralPartDiscount = integralPartDiscount;
	}

	public Short getIntegralDiscount() {
		return this.integralDiscount;
	}

	public void setIntegralDiscount(Short integralDiscount) {
		this.integralDiscount = integralDiscount;
	}

	public Double getIntegralProprotion() {
		return this.integralProprotion;
	}

	public void setIntegralProprotion(Double integralProprotion) {
		this.integralProprotion = integralProprotion;
	}

	public Set getVipInfors() {
		return this.vipInfors;
	}

	public void setVipInfors(Set vipInfors) {
		this.vipInfors = vipInfors;
	}

}