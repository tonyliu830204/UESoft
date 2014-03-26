package com.syuesoft.sell.model;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellBack extends BaseBean implements Serializable {
	private Integer backId;
	private String backCode;
	private String backDate;
	private Integer xsDistributorId;
	private Integer backType;
	private Integer backPerson;// 经办人
	private Double handbackAllocateAmount;// 调退金额
	private Integer balanceState;// 对账状态
	private String remark;
	private Integer examine;// 审核
	private Integer enterprise_id;
	
	private XsChilddictionary xsChilddictionary;
	private XsChilddictionary xsChilddictionary1;
	private XsChilddictionary xsChilddictionary2;
	
	

	
	public XsChilddictionary getXsChilddictionary1() {
		return xsChilddictionary1;
	}

	public void setXsChilddictionary1(XsChilddictionary xsChilddictionary1) {
		this.xsChilddictionary1 = xsChilddictionary1;
	}

	public XsChilddictionary getXsChilddictionary2() {
		return xsChilddictionary2;
	}

	public void setXsChilddictionary2(XsChilddictionary xsChilddictionary2) {
		this.xsChilddictionary2 = xsChilddictionary2;
	}

	public XsChilddictionary getXsChilddictionary() {
		return xsChilddictionary;
	}

	public void setXsChilddictionary(XsChilddictionary xsChilddictionary) {
		this.xsChilddictionary = xsChilddictionary;
	}
	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public Integer getExamine() {
		return examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	public Integer getBackId() {
		return backId;
	}

	public void setBackId(Integer backId) {
		this.backId = backId;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public Integer getXsDistributorId() {
		return xsDistributorId;
	}

	public void setXsDistributorId(Integer xsDistributorId) {
		this.xsDistributorId = xsDistributorId;
	}

	public Integer getBackType() {
		return backType;
	}

	public void setBackType(Integer backType) {
		this.backType = backType;
	}

	public Integer getBackPerson() {
		return backPerson;
	}

	public void setBackPerson(Integer backPerson) {
		this.backPerson = backPerson;
	}

	public Double getHandbackAllocateAmount() {
		return handbackAllocateAmount;
	}

	public void setHandbackAllocateAmount(Double handbackAllocateAmount) {
		this.handbackAllocateAmount = handbackAllocateAmount;
	}

	public Integer getBalanceState() {
		return balanceState;
	}

	public void setBalanceState(Integer balanceState) {
		this.balanceState = balanceState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
