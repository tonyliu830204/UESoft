package com.syuesoft.sell.base.vo;

import java.io.Serializable;

public class InsurerInfoVo  implements Serializable{
	private Integer insurerId;
	private String insurerCode;
	private String insurerName;
	private String insurerAddress;
	private String insurerPhone;
	private String insurerTelephone;
	private String insurerFax;
	private String insurerPerson;
	private String insurerBeak;
	private Integer insurerProperty;
	private String insurerBeaknumber;
	private String insurerTaxnumber;
	private String insurerInvoicetelephone;
	private String insurerBusinessinsurer;
	private String insurerStronginsurer;
	private String insurerRemark;
	//以下为分页字段
    private String sort;
 	private String order;
 	private int rows;
 	private int page;
 	//查询所用字段
 	private String insName;
 	 private Integer enterprise_id ;
  	
 	public Integer getEnterprise_id() {
 		return enterprise_id;
 	}
 	public void setEnterprise_id(Integer enterpriseId) {
 		enterprise_id = enterpriseId;
 	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public Integer getInsurerId() {
		return insurerId;
	}
	public void setInsurerId(Integer insurerId) {
		this.insurerId = insurerId;
	}
	public String getInsurerCode() {
		return insurerCode;
	}
	public void setInsurerCode(String insurerCode) {
		this.insurerCode = insurerCode;
	}
	public String getInsurerName() {
		return insurerName;
	}
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	public String getInsurerAddress() {
		return insurerAddress;
	}
	public void setInsurerAddress(String insurerAddress) {
		this.insurerAddress = insurerAddress;
	}
	public String getInsurerPhone() {
		return insurerPhone;
	}
	public void setInsurerPhone(String insurerPhone) {
		this.insurerPhone = insurerPhone;
	}
	public String getInsurerTelephone() {
		return insurerTelephone;
	}
	public void setInsurerTelephone(String insurerTelephone) {
		this.insurerTelephone = insurerTelephone;
	}
	public String getInsurerFax() {
		return insurerFax;
	}
	public void setInsurerFax(String insurerFax) {
		this.insurerFax = insurerFax;
	}
	public String getInsurerPerson() {
		return insurerPerson;
	}
	public void setInsurerPerson(String insurerPerson) {
		this.insurerPerson = insurerPerson;
	}
	
	public String getInsurerBeak() {
		return insurerBeak;
	}
	public void setInsurerBeak(String insurerBeak) {
		this.insurerBeak = insurerBeak;
	}
	public Integer getInsurerProperty() {
		return insurerProperty;
	}
	public void setInsurerProperty(Integer insurerProperty) {
		this.insurerProperty = insurerProperty;
	}
	public String getInsurerBeaknumber() {
		return insurerBeaknumber;
	}
	public void setInsurerBeaknumber(String insurerBeaknumber) {
		this.insurerBeaknumber = insurerBeaknumber;
	}
	public String getInsurerTaxnumber() {
		return insurerTaxnumber;
	}
	public void setInsurerTaxnumber(String insurerTaxnumber) {
		this.insurerTaxnumber = insurerTaxnumber;
	}
	public String getInsurerInvoicetelephone() {
		return insurerInvoicetelephone;
	}
	public void setInsurerInvoicetelephone(String insurerInvoicetelephone) {
		this.insurerInvoicetelephone = insurerInvoicetelephone;
	}
	public String getInsurerBusinessinsurer() {
		return insurerBusinessinsurer;
	}
	public void setInsurerBusinessinsurer(String insurerBusinessinsurer) {
		this.insurerBusinessinsurer = insurerBusinessinsurer;
	}
	public String getInsurerStronginsurer() {
		return insurerStronginsurer;
	}
	public void setInsurerStronginsurer(String insurerStronginsurer) {
		this.insurerStronginsurer = insurerStronginsurer;
	}
	public String getInsurerRemark() {
		return insurerRemark;
	}
	public void setInsurerRemark(String insurerRemark) {
		this.insurerRemark = insurerRemark;
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
