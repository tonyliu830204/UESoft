package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StGoodsStorage entity. @author MyEclipse Persistence Tools
 */

public class StGoodsStorage implements java.io.Serializable {

	// Fields

	private String storageId;
	private String cerNo;
	private Date storageDate;
	private Short storeId;
	private Double totalNum;
	private Double totalAmount;
	private Short relcampId;
	private String paid;
	private Double taxRate;
	private String identifyman;
	private String bill;
	private String remark;
	private Double taxcount;
	private Double notaxtotalamont;
	private Short manager;
	private String addpriceRate;
	private Set stStorageItems = new HashSet(0);
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public StGoodsStorage() {
	}

	/** minimal constructor */
	public StGoodsStorage(String storageId) {
		this.storageId = storageId;
	}

	/** full constructor */
	public StGoodsStorage(String storageId, String cerNo,
			Date storageDate, Short storeId, Double totalNum,
			Double totalAmount, Short relcampId, String paid, Double taxRate,
			String identifyman, String bill, String remark, Double taxcount,
			Double notaxtotalamont, Short manager, String addpriceRate,
			Set stStorageItems) {
		this.storageId = storageId;
		this.cerNo = cerNo;
		this.storageDate = storageDate;
		this.storeId = storeId;
		this.totalNum = totalNum;
		this.totalAmount = totalAmount;
		this.relcampId = relcampId;
		this.paid = paid;
		this.taxRate = taxRate;
		this.identifyman = identifyman;
		this.bill = bill;
		this.remark = remark;
		this.taxcount = taxcount;
		this.notaxtotalamont = notaxtotalamont;
		this.manager = manager;
		this.addpriceRate = addpriceRate;
		this.stStorageItems = stStorageItems;
	}

	// Property accessors

	public String getStorageId() {
		return this.storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getCerNo() {
		return this.cerNo;
	}

	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}

	public Date getStorageDate() {
		return this.storageDate;
	}

	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public Double getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Double totalNum) {
		this.totalNum = totalNum;
	}

	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Short getRelcampId() {
		return this.relcampId;
	}

	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}

	public String getPaid() {
		return this.paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public Double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public String getIdentifyman() {
		return this.identifyman;
	}

	public void setIdentifyman(String identifyman) {
		this.identifyman = identifyman;
	}

	public String getBill() {
		return this.bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getTaxcount() {
		return this.taxcount;
	}

	public void setTaxcount(Double taxcount) {
		this.taxcount = taxcount;
	}

	public Double getNotaxtotalamont() {
		return this.notaxtotalamont;
	}

	public void setNotaxtotalamont(Double notaxtotalamont) {
		this.notaxtotalamont = notaxtotalamont;
	}

	public Short getManager() {
		return this.manager;
	}

	public void setManager(Short manager) {
		this.manager = manager;
	}

	public String getAddpriceRate() {
		return this.addpriceRate;
	}

	public void setAddpriceRate(String addpriceRate) {
		this.addpriceRate = addpriceRate;
	}

	public Set getStStorageItems() {
		return this.stStorageItems;
	}

	public void setStStorageItems(Set stStorageItems) {
		this.stStorageItems = stStorageItems;
	}

}