package com.syuesoft.sell.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * XsSuppliertraderAccount entity. @author MyEclipse Persistence Tools
 */

public class XsSuppliertraderAccount implements java.io.Serializable {

	// Fields

	private Integer enterpriseId;
	private Integer accountId;
	private String accountCode;
	private String remark;
	private Integer instorehouseId;
	private Integer accountPerson;
	private Date accountDate;
	private Double accountSun;
	private Double accountBalance;
	private Double accountMoney;
	private Integer accountType;
	private Set xsSupplierAccountlogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public XsSuppliertraderAccount() {
	}

	/** full constructor */
	public XsSuppliertraderAccount(String accountCode, Integer instorehouseId,
			Integer accountPerson, Timestamp accountDate, Double accountSun,
			Double accountBalance, Double accountMoney, Integer accountType,
			Set xsSupplierAccountlogs) {
		this.accountCode = accountCode;
		this.instorehouseId = instorehouseId;
		this.accountPerson = accountPerson;
		this.accountDate = accountDate;
		this.accountSun = accountSun;
		this.accountBalance = accountBalance;
		this.accountMoney = accountMoney;
		this.accountType = accountType;
		this.xsSupplierAccountlogs = xsSupplierAccountlogs;
	}

	// Property accessors

	
	
	public Integer getAccountId() {
		return this.accountId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public Integer getInstorehouseId() {
		return this.instorehouseId;
	}

	public void setInstorehouseId(Integer instorehouseId) {
		this.instorehouseId = instorehouseId;
	}

	public Integer getAccountPerson() {
		return this.accountPerson;
	}

	public void setAccountPerson(Integer accountPerson) {
		this.accountPerson = accountPerson;
	}

	public Date getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	public Double getAccountSun() {
		return this.accountSun;
	}

	public void setAccountSun(Double accountSun) {
		this.accountSun = accountSun;
	}

	public Double getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Double getAccountMoney() {
		return this.accountMoney;
	}

	public void setAccountMoney(Double accountMoney) {
		this.accountMoney = accountMoney;
	}

	public Integer getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Set getXsSupplierAccountlogs() {
		return this.xsSupplierAccountlogs;
	}

	public void setXsSupplierAccountlogs(Set xsSupplierAccountlogs) {
		this.xsSupplierAccountlogs = xsSupplierAccountlogs;
	}

}