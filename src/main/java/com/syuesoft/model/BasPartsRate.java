package com.syuesoft.model;

/**
 * BasPartsRate entity. @author MyEclipse Persistence Tools
 */

public class BasPartsRate extends BaseBean{

	// Fields

	private Short partsId;
	private String partsStyle;
	private Double partsStartamount;
	private Double partsEndamount;
	private Double partsRate;
	private String partsRemark;

	// Constructors

	/** default constructor */
	public BasPartsRate() {
	}

	/** full constructor */
	public BasPartsRate(String partsStyle, Double partsStartamount,
			Double partsEndamount, Double partsRate, String partsRemark) {
		this.partsStyle = partsStyle;
		this.partsStartamount = partsStartamount;
		this.partsEndamount = partsEndamount;
		this.partsRate = partsRate;
		this.partsRemark = partsRemark;
	}

	// Property accessors

	public Short getPartsId() {
		return this.partsId;
	}

	public void setPartsId(Short partsId) {
		this.partsId = partsId;
	}

	public String getPartsStyle() {
		return this.partsStyle;
	}

	public void setPartsStyle(String partsStyle) {
		this.partsStyle = partsStyle;
	}

	public Double getPartsStartamount() {
		return this.partsStartamount;
	}

	public void setPartsStartamount(Double partsStartamount) {
		this.partsStartamount = partsStartamount;
	}

	public Double getPartsEndamount() {
		return this.partsEndamount;
	}

	public void setPartsEndamount(Double partsEndamount) {
		this.partsEndamount = partsEndamount;
	}

	public Double getPartsRate() {
		return this.partsRate;
	}

	public void setPartsRate(Double partsRate) {
		this.partsRate = partsRate;
	}

	public String getPartsRemark() {
		return this.partsRemark;
	}

	public void setPartsRemark(String partsRemark) {
		this.partsRemark = partsRemark;
	}

}