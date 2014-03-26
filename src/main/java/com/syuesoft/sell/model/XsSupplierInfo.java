package com.syuesoft.sell.model;

import java.io.Serializable;

public class XsSupplierInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer supplierId;
	private String supplierNumber;
	private String supplierName;
	private String supplierCode;
	private String supplierAddress;
	private String supplierTelephone;
	private String supplierPhone;
	private String supplierFax;
	private String supplierPerson;
	private String supplierRevenue;
	private String supplierBankCode;
	private String supplierMakeInvphone;
	private Double supplierMoney;
	private Integer supplierBuyerCredit;
	private Integer supplierBank;
	private Integer supplierNature;
	private String supplierRemark;
	private Integer enterprise_id;
	private XsChilddictionary xsSupplierInfoBank;
	private XsChilddictionary xsChilddictionary;

	
	public XsChilddictionary getXsSupplierInfoBank() {
		return xsSupplierInfoBank;
	}

	public void setXsSupplierInfoBank(XsChilddictionary xsSupplierInfoBank) {
		this.xsSupplierInfoBank = xsSupplierInfoBank;
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

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierTelephone() {
		return supplierTelephone;
	}

	public void setSupplierTelephone(String supplierTelephone) {
		this.supplierTelephone = supplierTelephone;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierFax() {
		return supplierFax;
	}

	public void setSupplierFax(String supplierFax) {
		this.supplierFax = supplierFax;
	}

	public String getSupplierPerson() {
		return supplierPerson;
	}

	public void setSupplierPerson(String supplierPerson) {
		this.supplierPerson = supplierPerson;
	}

	public String getSupplierRevenue() {
		return supplierRevenue;
	}

	public void setSupplierRevenue(String supplierRevenue) {
		this.supplierRevenue = supplierRevenue;
	}

	public String getSupplierBankCode() {
		return supplierBankCode;
	}

	public void setSupplierBankCode(String supplierBankCode) {
		this.supplierBankCode = supplierBankCode;
	}

	public String getSupplierMakeInvphone() {
		return supplierMakeInvphone;
	}

	public void setSupplierMakeInvphone(String supplierMakeInvphone) {
		this.supplierMakeInvphone = supplierMakeInvphone;
	}

	public Double getSupplierMoney() {
		return supplierMoney;
	}

	public void setSupplierMoney(Double supplierMoney) {
		this.supplierMoney = supplierMoney;
	}

	public Integer getSupplierBuyerCredit() {
		return supplierBuyerCredit;
	}

	public void setSupplierBuyerCredit(Integer supplierBuyerCredit) {
		this.supplierBuyerCredit = supplierBuyerCredit;
	}

	public Integer getSupplierBank() {
		return supplierBank;
	}

	public void setSupplierBank(Integer supplierBank) {
		this.supplierBank = supplierBank;
	}

	public Integer getSupplierNature() {
		return supplierNature;
	}

	public void setSupplierNature(Integer supplierNature) {
		this.supplierNature = supplierNature;
	}

	public String getSupplierRemark() {
		return supplierRemark;
	}

	public void setSupplierRemark(String supplierRemark) {
		this.supplierRemark = supplierRemark;
	}

}
