package com.syuesoft.model;

/**
 * BasVipworkDiscount entity. @author MyEclipse Persistence Tools
 */

public class BasVipworkDiscount extends BaseBean {

	// Fields

	private Integer discountId;
	private String vipCardType;
	private String serviceType;
	private String discountRate;

	// Constructors

	/** default constructor */
	public BasVipworkDiscount() {
	}

	/** full constructor */
	public BasVipworkDiscount(String vipCardType, String serviceType,
			String discountRate) {
		this.vipCardType = vipCardType;
		this.serviceType = serviceType;
		this.discountRate = discountRate;
	}

	// Property accessors

	public Integer getDiscountId() {
		return this.discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getVipCardType() {
		return this.vipCardType;
	}

	public void setVipCardType(String vipCardType) {
		this.vipCardType = vipCardType;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

}