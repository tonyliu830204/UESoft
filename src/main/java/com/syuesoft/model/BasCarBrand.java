package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;
import java.sql.Blob;

/**
 * BasCarBrand entity. @author MyEclipse Persistence Tools
 */

public class BasCarBrand implements java.io.Serializable {

	// Fields

	private Short cbrdId;
	private String cbrdName;
	private Double cbrdPrice;
	private Blob cbrdLogo;
	private Integer cbrdDistance;
	private Integer cbrdDays;
	private Integer enterpriseId;
	private Set basCarTypes = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasCarBrand() {
	}

	public Short getCbrdId() {
		return cbrdId;
	}

	public void setCbrdId(Short cbrdId) {
		this.cbrdId = cbrdId;
	}

	public String getCbrdName() {
		return cbrdName;
	}

	public void setCbrdName(String cbrdName) {
		this.cbrdName = cbrdName;
	}

	public Double getCbrdPrice() {
		return cbrdPrice;
	}

	public void setCbrdPrice(Double cbrdPrice) {
		this.cbrdPrice = cbrdPrice;
	}

	public Blob getCbrdLogo() {
		return cbrdLogo;
	}

	public void setCbrdLogo(Blob cbrdLogo) {
		this.cbrdLogo = cbrdLogo;
	}

	public Integer getCbrdDistance() {
		return cbrdDistance;
	}

	public void setCbrdDistance(Integer cbrdDistance) {
		this.cbrdDistance = cbrdDistance;
	}

	public Integer getCbrdDays() {
		return cbrdDays;
	}

	public void setCbrdDays(Integer cbrdDays) {
		this.cbrdDays = cbrdDays;
	}

	public Set getBasCarTypes() {
		return basCarTypes;
	}

	public void setBasCarTypes(Set basCarTypes) {
		this.basCarTypes = basCarTypes;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}