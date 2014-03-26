package com.syuesoft.sell.allocateManage.vo;
/**
 * 分销商Vo
 * @author mulangtao
 *
 */
public class DistributorInfoVo {
	private  Integer distributorId;
	private String distributorCode;
	private String distributorName;
	private String distributorMark;
	private String distributorAddr;
	private String distributorTelephone;
	private String distributorPhone;
	private String distributorFax;
	private String distributorPerson;
	private String distributorBeak;
	private String distributorBeaknumber;
	private String distributorTaxnumber;
	private String distributorInvoicetelephone;
	private Double distributorSaleaccount;
	private Integer distributorRank;
	private String distributorRemark;
	
	
	//以下为分页字段
    private String sort;
 	private String order;
 	private int rows;
 	private int page;
 	
 	 private Integer enterprise_id ;
  	
 	public Integer getEnterprise_id() {
 		return enterprise_id;
 	}
 	public void setEnterprise_id(Integer enterpriseId) {
 		enterprise_id = enterpriseId;
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
	public Integer getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getDistributorMark() {
		return distributorMark;
	}
	public void setDistributorMark(String distributorMark) {
		this.distributorMark = distributorMark;
	}
	public String getDistributorAddr() {
		return distributorAddr;
	}
	public void setDistributorAddr(String distributorAddr) {
		this.distributorAddr = distributorAddr;
	}
	public String getDistributorTelephone() {
		return distributorTelephone;
	}
	public void setDistributorTelephone(String distributorTelephone) {
		this.distributorTelephone = distributorTelephone;
	}
	public String getDistributorPhone() {
		return distributorPhone;
	}
	public void setDistributorPhone(String distributorPhone) {
		this.distributorPhone = distributorPhone;
	}
	public String getDistributorFax() {
		return distributorFax;
	}
	public void setDistributorFax(String distributorFax) {
		this.distributorFax = distributorFax;
	}
	public String getDistributorPerson() {
		return distributorPerson;
	}
	public void setDistributorPerson(String distributorPerson) {
		this.distributorPerson = distributorPerson;
	}
	public String getDistributorBeak() {
		return distributorBeak;
	}
	public void setDistributorBeak(String distributorBeak) {
		this.distributorBeak = distributorBeak;
	}
	public String getDistributorBeaknumber() {
		return distributorBeaknumber;
	}
	public void setDistributorBeaknumber(String distributorBeaknumber) {
		this.distributorBeaknumber = distributorBeaknumber;
	}
	public String getDistributorTaxnumber() {
		return distributorTaxnumber;
	}
	public void setDistributorTaxnumber(String distributorTaxnumber) {
		this.distributorTaxnumber = distributorTaxnumber;
	}
	public String getDistributorInvoicetelephone() {
		return distributorInvoicetelephone;
	}
	public void setDistributorInvoicetelephone(String distributorInvoicetelephone) {
		this.distributorInvoicetelephone = distributorInvoicetelephone;
	}
	public Double getDistributorSaleaccount() {
		return distributorSaleaccount;
	}
	public void setDistributorSaleaccount(Double distributorSaleaccount) {
		this.distributorSaleaccount = distributorSaleaccount;
	}
	public Integer getDistributorRank() {
		return distributorRank;
	}
	public void setDistributorRank(Integer distributorRank) {
		this.distributorRank = distributorRank;
	}
	public String getDistributorRemark() {
		return distributorRemark;
	}
	public void setDistributorRemark(String distributorRemark) {
		this.distributorRemark = distributorRemark;
	}

}
