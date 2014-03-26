package com.syuesoft.sell.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.syuesoft.model.BaseBean;


/**
 * XsSellAccount entity. @author MyEclipse Persistence Tools
 */

public class XsSellAccount extends BaseBean implements java.io.Serializable {


    // Fields    

     private Integer accountId;
     private XsCarSellInfo xsCarSellInfo;
     private String accountCode;
     private String accountTypeId;
     private Integer accountType;
     private Double accountMoney;
     private Date accountDate;
     private Integer accountPerson;
     private Integer accountGyration;
     private Integer accountState;
     private Integer enterpriseId;
     private String remark;


    // Constructors

    /** default constructor */
    public XsSellAccount() {
    }

    
    /** full constructor */
    public XsSellAccount(XsCarSellInfo xsCarSellInfo, String accountCode, String accountTypeId, Integer accountType, Double accountMoney, Date accountDate, Integer accountPerson, Integer accountState, String remark, Set xsSellCollectionses) {
        this.xsCarSellInfo = xsCarSellInfo;
        this.accountCode = accountCode;
        this.accountTypeId = accountTypeId;
        this.accountType = accountType;
        this.accountMoney = accountMoney;
        this.accountDate = accountDate;
        this.accountPerson = accountPerson;
        this.accountState = accountState;
        this.remark = remark;
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


	public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public XsCarSellInfo getXsCarSellInfo() {
        return this.xsCarSellInfo;
    }
    
    public void setXsCarSellInfo(XsCarSellInfo xsCarSellInfo) {
        this.xsCarSellInfo = xsCarSellInfo;
    }

    public String getAccountCode() {
        return this.accountCode;
    }
    
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountTypeId() {
        return this.accountTypeId;
    }
    
    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Integer getAccountType() {
        return this.accountType;
    }
    
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Double getAccountMoney() {
        return this.accountMoney;
    }
    
    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

 
    public Date getAccountDate() {
		return accountDate;
	}


	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}


	public Integer getAccountPerson() {
        return this.accountPerson;
    }
    
    public void setAccountPerson(Integer accountPerson) {
        this.accountPerson = accountPerson;
    }

    public Integer getAccountState() {
        return this.accountState;
    }
    
    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }


	public Integer getAccountGyration() {
		return accountGyration;
	}


	public void setAccountGyration(Integer accountGyration) {
		this.accountGyration = accountGyration;
	}

}