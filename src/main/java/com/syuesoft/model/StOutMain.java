package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StOutMain entity. @author MyEclipse Persistence Tools
 */

public class StOutMain extends BaseBean {

	// Fields

	private String stomId;
	private String cerNo;
	private Date stomDate;
	private Short pickingMember;
	private Double totalNum;
	private Double stomSumAmount;          //销售额
	private String stomRemark;
	private Double notaxCastamont;         //未税成本额
	private Double taxCastamont;           //含税成本额
	private Double reducepriceCoefficient;
	private Short manager;
	private Set stOutItems = new HashSet(0);
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public StOutMain() {
	}

	/** minimal constructor */
	public StOutMain(String stomId) {
		this.stomId = stomId;
	}

	public StOutMain(String stomId, String cerNo, Date stomDate,
			Short pickingMember, Double totalNum,
			Double stomSumAmount, String stomRemark, Double notaxCastamont,
			Double taxCastamont, Double reducepriceCoefficient) {
		super();
		this.stomId = stomId;
		this.cerNo = cerNo;
		this.stomDate = stomDate;
		this.pickingMember = pickingMember;
		this.totalNum = totalNum;
		this.stomSumAmount = stomSumAmount;
		this.stomRemark = stomRemark;
		this.notaxCastamont = notaxCastamont;
		this.taxCastamont = taxCastamont;
		this.reducepriceCoefficient = reducepriceCoefficient;
	}

	/** full constructor */
	public StOutMain(String stomId, String cerNo, Date stomDate,
			Short pickingMember, Short stomRelationStf, Double totalNum,
			Double stomSumAmount, String stomRemark,
			Double notaxCastamont, Double taxCastamont,
			Double reducepriceCoefficient, Set stOutItems) {
		this.stomId = stomId;
		this.cerNo = cerNo;
		this.stomDate = stomDate;
		this.pickingMember = pickingMember;
		this.totalNum = totalNum;
		this.stomSumAmount = stomSumAmount;
		this.stomRemark = stomRemark;
		this.notaxCastamont = notaxCastamont;
		this.taxCastamont = taxCastamont;
		this.reducepriceCoefficient = reducepriceCoefficient;
		this.stOutItems = stOutItems;
	}

	// Property accessors

	public String getStomId() {
		return this.stomId;
	}

	public Short getManager() {
		return manager;
	}

	public void setManager(Short manager) {
		this.manager = manager;
	}

	public void setStomId(String stomId) {
		this.stomId = stomId;
	}

	public String getCerNo() {
		return this.cerNo;
	}

	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}

	public Date getStomDate() {
		return this.stomDate;
	}

	public void setStomDate(Date stomDate) {
		this.stomDate = stomDate;
	}

	public Short getPickingMember() {
		return this.pickingMember;
	}

	public void setPickingMember(Short pickingMember) {
		this.pickingMember = pickingMember;
	}

	public Double getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Double totalNum) {
		this.totalNum = totalNum;
	}

	public Double getStomSumAmount() {
		return this.stomSumAmount;
	}

	public void setStomSumAmount(Double stomSumAmount) {
		this.stomSumAmount = stomSumAmount;
	}

	public String getStomRemark() {
		return this.stomRemark;
	}

	public void setStomRemark(String stomRemark) {
		this.stomRemark = stomRemark;
	}

	public Double getNotaxCastamont() {
		return this.notaxCastamont;
	}

	public void setNotaxCastamont(Double notaxCastamont) {
		this.notaxCastamont = notaxCastamont;
	}

	public Double getTaxCastamont() {
		return this.taxCastamont;
	}

	public void setTaxCastamont(Double taxCastamont) {
		this.taxCastamont = taxCastamont;
	}

	public Double getReducepriceCoefficient() {
		return this.reducepriceCoefficient;
	}

	public void setReducepriceCoefficient(Double reducepriceCoefficient) {
		this.reducepriceCoefficient = reducepriceCoefficient;
	}

	public Set getStOutItems() {
		return this.stOutItems;
	}

	public void setStOutItems(Set stOutItems) {
		this.stOutItems = stOutItems;
	}

}