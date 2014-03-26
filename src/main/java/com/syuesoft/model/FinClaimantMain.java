package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FinClaimantMain entity. @author MyEclipse Persistence Tools
 */

public class FinClaimantMain extends BaseBean {

	// Fields

	private String claimantmId;
	private Date claimantmTime;
	private String preclrId;
	private String claimantmInvoiceType;
	private Date claimantmInvoiceTime;
	private String claimantmInvoiceNo;
	private Short claimantmClrStfId;
	private Double claimantmPartsAmount;
	private Double claimantmTimeAmount;
	private Double claimantmAmount;
	private String claimantmStatus;
	private Short claimantmCheckStfId;
	private String claimantmRemark;
	private Short claimantmTag;
	private Short claimantmToMoney;
	private Double claimantmManagementFee;
	private Double claimantmOtherAmount;
	private Double claimantmNoTaxCost;
	private Double claimantmTaxCost;
	
	private Set finClaimantTimes = new HashSet(0);
	private Set finClaimantPartses = new HashSet(0);
	private Set finClaimantMainCosts = new HashSet(0);
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public FinClaimantMain() {
	}

	/** minimal constructor */
	public FinClaimantMain(String claimantmId) {
		this.claimantmId = claimantmId;
	}

	/** full constructor */
	public FinClaimantMain(String claimantmId, Date claimantmTime,
			String preclrId, String claimantmInvoiceType,
			Date claimantmInvoiceTime, String claimantmInvoiceNo,
			Short claimantmClrStfId, Double claimantmPartsAmount,
			Double claimantmTimeAmount, Double claimantmAmount,
			 String claimantmStatus,
			Short claimantmCheckStfId, String claimantmRemark,
			Set finClaimantTimes, Set finClaimantPartses,
			Set finClaimantMainCosts) {
		this.claimantmId = claimantmId;
		this.claimantmTime = claimantmTime;
		this.preclrId = preclrId;
		this.claimantmInvoiceType = claimantmInvoiceType;
		this.claimantmInvoiceTime = claimantmInvoiceTime;
		this.claimantmInvoiceNo = claimantmInvoiceNo;
		this.claimantmClrStfId = claimantmClrStfId;
		this.claimantmPartsAmount = claimantmPartsAmount;
		this.claimantmTimeAmount = claimantmTimeAmount;
		this.claimantmAmount = claimantmAmount;
		this.claimantmStatus = claimantmStatus;
		this.claimantmCheckStfId = claimantmCheckStfId;
		this.claimantmRemark = claimantmRemark;
		this.finClaimantTimes = finClaimantTimes;
		this.finClaimantPartses = finClaimantPartses;
		this.finClaimantMainCosts = finClaimantMainCosts;
	}

	// Property accessors
	public String getClaimantmId() {
		return claimantmId;
	}

	public void setClaimantmId(String claimantmId) {
		this.claimantmId = claimantmId;
	}

	public Date getClaimantmTime() {
		return claimantmTime;
	}

	public void setClaimantmTime(Date claimantmTime) {
		this.claimantmTime = claimantmTime;
	}


	public String getPreclrId() {
		return preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public String getClaimantmInvoiceType() {
		return claimantmInvoiceType;
	}

	public void setClaimantmInvoiceType(String claimantmInvoiceType) {
		this.claimantmInvoiceType = claimantmInvoiceType;
	}

	public Date getClaimantmInvoiceTime() {
		return claimantmInvoiceTime;
	}

	public void setClaimantmInvoiceTime(Date claimantmInvoiceTime) {
		this.claimantmInvoiceTime = claimantmInvoiceTime;
	}

	public String getClaimantmInvoiceNo() {
		return claimantmInvoiceNo;
	}

	public void setClaimantmInvoiceNo(String claimantmInvoiceNo) {
		this.claimantmInvoiceNo = claimantmInvoiceNo;
	}

	public Short getClaimantmClrStfId() {
		return claimantmClrStfId;
	}

	public void setClaimantmClrStfId(Short claimantmClrStfId) {
		this.claimantmClrStfId = claimantmClrStfId;
	}

	public Double getClaimantmPartsAmount() {
		return claimantmPartsAmount;
	}

	public void setClaimantmPartsAmount(Double claimantmPartsAmount) {
		this.claimantmPartsAmount = claimantmPartsAmount;
	}

	public Double getClaimantmTimeAmount() {
		return claimantmTimeAmount;
	}

	public void setClaimantmTimeAmount(Double claimantmTimeAmount) {
		this.claimantmTimeAmount = claimantmTimeAmount;
	}

	public Double getClaimantmAmount() {
		return claimantmAmount;
	}

	public void setClaimantmAmount(Double claimantmAmount) {
		this.claimantmAmount = claimantmAmount;
	}


	public String getClaimantmStatus() {
		return claimantmStatus;
	}

	public void setClaimantmStatus(String claimantmStatus) {
		this.claimantmStatus = claimantmStatus;
	}

	public Short getClaimantmCheckStfId() {
		return claimantmCheckStfId;
	}

	public void setClaimantmCheckStfId(Short claimantmCheckStfId) {
		this.claimantmCheckStfId = claimantmCheckStfId;
	}

	public String getClaimantmRemark() {
		return claimantmRemark;
	}

	public void setClaimantmRemark(String claimantmRemark) {
		this.claimantmRemark = claimantmRemark;
	}

	public Set getFinClaimantTimes() {
		return finClaimantTimes;
	}

	public void setFinClaimantTimes(Set finClaimantTimes) {
		this.finClaimantTimes = finClaimantTimes;
	}

	public Set getFinClaimantPartses() {
		return finClaimantPartses;
	}

	public void setFinClaimantPartses(Set finClaimantPartses) {
		this.finClaimantPartses = finClaimantPartses;
	}

	public Set getFinClaimantMainCosts() {
		return finClaimantMainCosts;
	}

	public void setFinClaimantMainCosts(Set finClaimantMainCosts) {
		this.finClaimantMainCosts = finClaimantMainCosts;
	}

	public Short getClaimantmTag() {
		return claimantmTag;
	}

	public void setClaimantmTag(Short claimantmTag) {
		this.claimantmTag = claimantmTag;
	}

	public Short getClaimantmToMoney() {
		return claimantmToMoney;
	}

	public void setClaimantmToMoney(Short claimantmToMoney) {
		this.claimantmToMoney = claimantmToMoney;
	}

	public Double getClaimantmManagementFee() {
		return claimantmManagementFee;
	}

	public void setClaimantmManagementFee(Double claimantmManagementFee) {
		this.claimantmManagementFee = claimantmManagementFee;
	}

	public Double getClaimantmOtherAmount() {
		return claimantmOtherAmount;
	}

	public void setClaimantmOtherAmount(Double claimantmOtherAmount) {
		this.claimantmOtherAmount = claimantmOtherAmount;
	}

	public Double getClaimantmNoTaxCost() {
		return claimantmNoTaxCost;
	}

	public void setClaimantmNoTaxCost(Double claimantmNoTaxCost) {
		this.claimantmNoTaxCost = claimantmNoTaxCost;
	}

	public Double getClaimantmTaxCost() {
		return claimantmTaxCost;
	}

	public void setClaimantmTaxCost(Double claimantmTaxCost) {
		this.claimantmTaxCost = claimantmTaxCost;
	}
	
}