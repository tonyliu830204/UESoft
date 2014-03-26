package com.syuesoft.sell.base.vo;

public class ProvidebankVo implements java.io.Serializable{
	
	private Integer providebankId;
	private String providebankCode;
	private String providebankName;
	private String providebankBank;
	private String providebankNumber;
	private String providebankAddr;
	private String providebankTaxnumber;
	private String providebankPhone;
	private String providebankTelephone;
	private String providebankInvtelephone;
	private String providebankFax;
	private String providebankPerson;
	private Double providebankLoanrate;
	private Double providebankLimit;
	private String providebankRemark;
	//以下为分页字段
    private String sort;
 	private String order;
 	private int rows;
 	private int page;
 	//查询所用
 	private String bankName;
 	 private Integer enterprise_id ;
  	
 	public Integer getEnterprise_id() {
 		return enterprise_id;
 	}
 	public void setEnterprise_id(Integer enterpriseId) {
 		enterprise_id = enterpriseId;
 	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Integer getProvidebankId() {
		return providebankId;
	}
	public void setProvidebankId(Integer providebankId) {
		this.providebankId = providebankId;
	}
	public String getProvidebankCode() {
		return providebankCode;
	}
	public void setProvidebankCode(String providebankCode) {
		this.providebankCode = providebankCode;
	}
	public String getProvidebankName() {
		return providebankName;
	}
	public void setProvidebankName(String providebankName) {
		this.providebankName = providebankName;
	}

	public String getProvidebankBank() {
		return providebankBank;
	}
	public void setProvidebankBank(String providebankBank) {
		this.providebankBank = providebankBank;
	}
	public String getProvidebankNumber() {
		return providebankNumber;
	}
	public void setProvidebankNumber(String providebankNumber) {
		this.providebankNumber = providebankNumber;
	}
	public String getProvidebankAddr() {
		return providebankAddr;
	}
	public void setProvidebankAddr(String providebankAddr) {
		this.providebankAddr = providebankAddr;
	}
	public String getProvidebankTaxnumber() {
		return providebankTaxnumber;
	}
	public void setProvidebankTaxnumber(String providebankTaxnumber) {
		this.providebankTaxnumber = providebankTaxnumber;
	}
	public String getProvidebankPhone() {
		return providebankPhone;
	}
	public void setProvidebankPhone(String providebankPhone) {
		this.providebankPhone = providebankPhone;
	}
	public String getProvidebankTelephone() {
		return providebankTelephone;
	}
	public void setProvidebankTelephone(String providebankTelephone) {
		this.providebankTelephone = providebankTelephone;
	}
	public String getProvidebankInvtelephone() {
		return providebankInvtelephone;
	}
	public void setProvidebankInvtelephone(String providebankInvtelephone) {
		this.providebankInvtelephone = providebankInvtelephone;
	}
	public String getProvidebankFax() {
		return providebankFax;
	}
	public void setProvidebankFax(String providebankFax) {
		this.providebankFax = providebankFax;
	}
	public String getProvidebankPerson() {
		return providebankPerson;
	}
	public void setProvidebankPerson(String providebankPerson) {
		this.providebankPerson = providebankPerson;
	}
	public Double getProvidebankLoanrate() {
		return providebankLoanrate;
	}
	public void setProvidebankLoanrate(Double providebankLoanrate) {
		this.providebankLoanrate = providebankLoanrate;
	}
	public Double getProvidebankLimit() {
		return providebankLimit;
	}
	public void setProvidebankLimit(Double providebankLimit) {
		this.providebankLimit = providebankLimit;
	}
	public String getProvidebankRemark() {
		return providebankRemark;
	}
	public void setProvidebankRemark(String providebankRemark) {
		this.providebankRemark = providebankRemark;
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