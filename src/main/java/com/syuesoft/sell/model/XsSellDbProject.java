package com.syuesoft.sell.model;

import java.util.Date;

import com.syuesoft.model.BaseBean;

/**
 * XsSellDbProject entity. @author MyEclipse Persistence Tools
 */

public class XsSellDbProject extends BaseBean implements java.io.Serializable {

	// Fields
	

	private Integer id;						//编号
	private XsCarSellInfo xsCarSellInfo;
	private Integer dbProjectId;			//代办项目编号
	private Double dbProjectCost;			//代办费用
	private Double costPrice;				//成本金额
	private Integer dbProjectPerson;        //代办人
	private Date dbProjectDate;          	//代办日期
	private String dbProjectCode;			//代办单号
	private Integer dbExamin;					//审核状态
	private Integer dbProjectReckoning;			//是否转结算
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public XsCarSellInfo getXsCarSellInfo() {
		return this.xsCarSellInfo;
	}

	public void setXsCarSellInfo(XsCarSellInfo xsCarSellInfo) {
		this.xsCarSellInfo = xsCarSellInfo;
	}

	public Integer getDbProjectId() {
		return this.dbProjectId;
	}

	public void setDbProjectId(Integer dbProjectId) {
		this.dbProjectId = dbProjectId;
	}

	public Double getDbProjectCost() {
		return this.dbProjectCost;
	}

	public void setDbProjectCost(Double dbProjectCost) {
		this.dbProjectCost = dbProjectCost;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}


	public Integer getDbProjectPerson() {
		return dbProjectPerson;
	}

	public void setDbProjectPerson(Integer dbProjectPerson) {
		this.dbProjectPerson = dbProjectPerson;
	}

	public Date getDbProjectDate() {
		return dbProjectDate;
	}

	public void setDbProjectDate(Date dbProjectDate) {
		this.dbProjectDate = dbProjectDate;
	}

	public String getDbProjectCode() {
		return dbProjectCode;
	}

	public void setDbProjectCode(String dbProjectCode) {
		this.dbProjectCode = dbProjectCode;
	}

	public Integer getDbExamin() {
		return dbExamin;
	}

	public void setDbExamin(Integer dbExamin) {
		this.dbExamin = dbExamin;
	}

	public Integer getDbProjectReckoning() {
		return dbProjectReckoning;
	}

	public void setDbProjectReckoning(Integer dbProjectReckoning) {
		this.dbProjectReckoning = dbProjectReckoning;
	}
	

}