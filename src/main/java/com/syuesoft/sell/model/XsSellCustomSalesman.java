package com.syuesoft.sell.model;

import java.util.HashSet;
import java.util.Set;

/**
 * XsSellCustomSalesman entity. @author MyEclipse Persistence Tools
 */

public class XsSellCustomSalesman implements java.io.Serializable {

	// Fields

	private String xsCustomSalesmanId;
	private String exitDate;
	private String registerDate;
	private Integer stfId;
	private Integer weather;
	private Integer sumNumber;
	private Integer enterpriseId;
	private String remark;
	private Set xsSellCustomSalesmanDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public XsSellCustomSalesman() {
	}

	/** full constructor */
	public XsSellCustomSalesman(String exitDate, String registerDate,
			Integer stfId, Integer weather, Integer sumNumber, String remark,
			Set xsSellCustomSalesmanDetails) {
		this.exitDate = exitDate;
		this.registerDate = registerDate;
		this.stfId = stfId;
		this.weather = weather;
		this.sumNumber = sumNumber;
		this.remark = remark;
		this.xsSellCustomSalesmanDetails = xsSellCustomSalesmanDetails;
	}

	// Property accessors

	public String getXsCustomSalesmanId() {
		return this.xsCustomSalesmanId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public void setXsCustomSalesmanId(String xsCustomSalesmanId) {
		this.xsCustomSalesmanId = xsCustomSalesmanId;
	}

	public String getExitDate() {
		return this.exitDate;
	}

	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}

	public String getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public Integer getStfId() {
		return this.stfId;
	}

	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}

	public Integer getWeather() {
		return this.weather;
	}

	public void setWeather(Integer weather) {
		this.weather = weather;
	}

	public Integer getSumNumber() {
		return this.sumNumber;
	}

	public void setSumNumber(Integer sumNumber) {
		this.sumNumber = sumNumber;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getXsSellCustomSalesmanDetails() {
		return this.xsSellCustomSalesmanDetails;
	}

	public void setXsSellCustomSalesmanDetails(Set xsSellCustomSalesmanDetails) {
		this.xsSellCustomSalesmanDetails = xsSellCustomSalesmanDetails;
	}

}