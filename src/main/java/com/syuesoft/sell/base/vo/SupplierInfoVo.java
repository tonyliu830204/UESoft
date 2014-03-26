package com.syuesoft.sell.base.vo;

public class SupplierInfoVo implements java.io.Serializable{
	
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
	private String supplierBank;
	private Integer supplierNature;
	private String supplierRemark;

	private String supplierNatureName;
	private String supplierBankName;
	//以下为分页字段
    private String sort;
 	private String order;
 	private int rows;
 	private int page;
 	//查询所用
 	private String supName;
 	private String supTel;
 	private String supPerson;
 	
 	public String getSupplierBankName() {
		return supplierBankName;
	}
	public void setSupplierBankName(String supplierBankName) {
		this.supplierBankName = supplierBankName;
	}
	private Integer enterprise_id ;
 	
	public Integer getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getSupTel() {
		return supTel;
	}
	public void setSupTel(String supTel) {
		this.supTel = supTel;
	}

	public String getSupPerson() {
		return supPerson;
	}
	public void setSupPerson(String supPerson) {
		this.supPerson = supPerson;
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
	
	public String getSupplierBank() {
		return supplierBank;
	}
	public void setSupplierBank(String supplierBank) {
		this.supplierBank = supplierBank;
	}
	public Integer getSupplierNature() {
		return supplierNature;
	}
	public void setSupplierNature(Integer supplierNature) {
		this.supplierNature = supplierNature;
	}
	
	public String getSupplierNatureName() {
		return supplierNatureName;
	}
	public void setSupplierNatureName(String supplierNatureName) {
		this.supplierNatureName = supplierNatureName;
	}
	public String getSupplierRemark() {
		return supplierRemark;
	}
	public void setSupplierRemark(String supplierRemark) {
		this.supplierRemark = supplierRemark;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
 	
	
 	
}