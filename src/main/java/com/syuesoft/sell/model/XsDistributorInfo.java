package com.syuesoft.sell.model;

import java.io.Serializable;

public class XsDistributorInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer distributorId;
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
	private String distributorRemark;
	private Integer enterprise_id;

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
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

	public void setDistributorInvoicetelephone(
			String distributorInvoicetelephone) {
		this.distributorInvoicetelephone = distributorInvoicetelephone;
	}

	public String getDistributorRemark() {
		return distributorRemark;
	}

	public void setDistributorRemark(String distributorRemark) {
		this.distributorRemark = distributorRemark;
	}

}
