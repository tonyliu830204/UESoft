package com.syuesoft.sell.model;

import java.sql.Timestamp;

/**
 * XsSellZhProject entity. @author MyEclipse Persistence Tools
 */

public class XsSellZhProject implements java.io.Serializable {

	// Fields

	private Integer id;
	private XsChilddictionary xsChilddictionaryByAudit;
	private XsChilddictionary xsChilddictionaryByZhProjectReckoning;
	private XsCarSellInfo xsCarSellInfo;
	private String zhProjectCode;
	private Integer zhProject;
	private Integer unitNum;
	private Double costPrice;
	private Double selLPrice;
	private Double preferentialPrice;
	private Double decorateSell;
	private Double decorateAmount;
	private Integer zhProjectPerson;
	private String zhProjectDate;
	private Integer remark;

	// Constructors

	/** default constructor */
	public XsSellZhProject() {
	}

	/** full constructor */
	public XsSellZhProject(XsChilddictionary xsChilddictionaryByAudit,
			XsChilddictionary xsChilddictionaryByZhProjectReckoning,
			XsCarSellInfo xsCarSellInfo, String zhProjectCode,
			Integer zhProject, Integer unitNum, Double costPrice,
			Double selLPrice, Double preferentialPrice, Double decorateSell,
			Double decorateAmount, Integer zhProjectPerson,
			String zhProjectDate, Integer remark) {
		this.xsChilddictionaryByAudit = xsChilddictionaryByAudit;
		this.xsChilddictionaryByZhProjectReckoning = xsChilddictionaryByZhProjectReckoning;
		this.xsCarSellInfo = xsCarSellInfo;
		this.zhProjectCode = zhProjectCode;
		this.zhProject = zhProject;
		this.unitNum = unitNum;
		this.costPrice = costPrice;
		this.selLPrice = selLPrice;
		this.preferentialPrice = preferentialPrice;
		this.decorateSell = decorateSell;
		this.decorateAmount = decorateAmount;
		this.zhProjectPerson = zhProjectPerson;
		this.zhProjectDate = zhProjectDate;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public XsChilddictionary getXsChilddictionaryByAudit() {
		return this.xsChilddictionaryByAudit;
	}

	public void setXsChilddictionaryByAudit(
			XsChilddictionary xsChilddictionaryByAudit) {
		this.xsChilddictionaryByAudit = xsChilddictionaryByAudit;
	}

	public XsChilddictionary getXsChilddictionaryByZhProjectReckoning() {
		return this.xsChilddictionaryByZhProjectReckoning;
	}

	public void setXsChilddictionaryByZhProjectReckoning(
			XsChilddictionary xsChilddictionaryByZhProjectReckoning) {
		this.xsChilddictionaryByZhProjectReckoning = xsChilddictionaryByZhProjectReckoning;
	}

	public XsCarSellInfo getXsCarSellInfo() {
		return this.xsCarSellInfo;
	}

	public void setXsCarSellInfo(XsCarSellInfo xsCarSellInfo) {
		this.xsCarSellInfo = xsCarSellInfo;
	}

	public String getZhProjectCode() {
		return this.zhProjectCode;
	}

	public void setZhProjectCode(String zhProjectCode) {
		this.zhProjectCode = zhProjectCode;
	}

	public Integer getZhProject() {
		return this.zhProject;
	}

	public void setZhProject(Integer zhProject) {
		this.zhProject = zhProject;
	}

	public Integer getUnitNum() {
		return this.unitNum;
	}

	public void setUnitNum(Integer unitNum) {
		this.unitNum = unitNum;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSelLPrice() {
		return this.selLPrice;
	}

	public void setSelLPrice(Double selLPrice) {
		this.selLPrice = selLPrice;
	}

	public Double getPreferentialPrice() {
		return this.preferentialPrice;
	}

	public void setPreferentialPrice(Double preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public Double getDecorateSell() {
		return this.decorateSell;
	}

	public void setDecorateSell(Double decorateSell) {
		this.decorateSell = decorateSell;
	}

	public Double getDecorateAmount() {
		return this.decorateAmount;
	}

	public void setDecorateAmount(Double decorateAmount) {
		this.decorateAmount = decorateAmount;
	}

	public Integer getZhProjectPerson() {
		return this.zhProjectPerson;
	}

	public void setZhProjectPerson(Integer zhProjectPerson) {
		this.zhProjectPerson = zhProjectPerson;
	}

	public String getZhProjectDate() {
		return this.zhProjectDate;
	}

	public void setZhProjectDate(String zhProjectDate) {
		this.zhProjectDate = zhProjectDate;
	}

	public Integer getRemark() {
		return this.remark;
	}

	public void setRemark(Integer remark) {
		this.remark = remark;
	}

}