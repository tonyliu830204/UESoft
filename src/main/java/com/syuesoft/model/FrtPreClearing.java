package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FrtPreClearing entity. @author MyEclipse Persistence Tools
 */

public class FrtPreClearing extends BaseBean {

	// Fields

	private String preclrId;
	private String receptionId;
	private Date preclrTime;
	private String preclrInoiceType;
	private Date preclrInvoiceTime;
	private String preclrNo;
	private Double preMprMatAmount;
	private Double preclrMaterialRate;
	private Double preclrWktimeAmount;
	private Double preclrWktimeRate;
	private Double preclrOtherAmount;
	private Double preclrSumAmount;
	private Double preclrSumRate;
	private Double preclrRealAmount;
	private Double preclrNoTaxCost;
	private Double preclrTaxCost;
	private String preclrInstr;
	private String preclrRemark;
	private Short preclrHasUnrealData;
	private Double preclrManagementFee;
	private Short preFlg;
	private Short stfId;
	private Short preclrToMoney;
	/*预留列*/
	private String preclrTag1;
	private String preclrTag2;
	private Short preclrTag3;
	private Short preclrTag4;
	/****/
	private Set frtPreClearingCosts = new HashSet(0);
	private Set frtPreWktimes = new HashSet(0);
	private Set frtPrePartses = new HashSet(0);
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public FrtPreClearing() {
	}

	/** minimal constructor */
	public FrtPreClearing(String preclrId) {
		this.preclrId = preclrId;
	}

	/** full constructor */
	public FrtPreClearing(String preclrId, String receptionId, Date preclrTime, String preclrInoiceType, Date preclrInvoiceTime, String preclrNo, Double preMprMatAmount,
			Double preclrMaterialRate, Double preclrWktimeAmount, Double preclrWktimeRate, Double preclrOtherAmount, Double preclrSumAmount, Double preclrSumRate, Double preclrRealAmount,
			Double preclrNoTaxCost,String preclrInstr, String preclrRemark, Short preclrHasUnrealData, Double preclrManagementFee, Set frtPreClearingCosts, Set frtPreWktimes,
			Set frtPrePartses) {
		this.preclrId = preclrId;
		this.receptionId = receptionId;
		this.preclrTime = preclrTime;
		this.preclrInoiceType = preclrInoiceType;
		this.preclrInvoiceTime = preclrInvoiceTime;
		this.preclrNo = preclrNo;
		this.preMprMatAmount = preMprMatAmount;
		this.preclrMaterialRate = preclrMaterialRate;
		this.preclrWktimeAmount = preclrWktimeAmount;
		this.preclrWktimeRate = preclrWktimeRate;
		this.preclrOtherAmount = preclrOtherAmount;
		this.preclrSumAmount = preclrSumAmount;
		this.preclrSumRate = preclrSumRate;
		this.preclrRealAmount = preclrRealAmount;
		this.preclrNoTaxCost = preclrNoTaxCost;
		this.preclrInstr = preclrInstr;
		this.preclrRemark = preclrRemark;
		this.preclrHasUnrealData = preclrHasUnrealData;
		this.preclrManagementFee = preclrManagementFee;
		this.frtPreClearingCosts = frtPreClearingCosts;
		this.frtPreWktimes = frtPreWktimes;
		this.frtPrePartses = frtPrePartses;
	}

	// Property accessors

	public String getPreclrId() {
		return this.preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public String getReceptionId() {
		return receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

	public Date getPreclrTime() {
		return this.preclrTime;
	}

	public void setPreclrTime(Date preclrTime) {
		this.preclrTime = preclrTime;
	}

	public String getPreclrInoiceType() {
		return this.preclrInoiceType;
	}

	public void setPreclrInoiceType(String preclrInoiceType) {
		this.preclrInoiceType = preclrInoiceType;
	}

	public Date getPreclrInvoiceTime() {
		return this.preclrInvoiceTime;
	}

	public void setPreclrInvoiceTime(Date preclrInvoiceTime) {
		this.preclrInvoiceTime = preclrInvoiceTime;
	}

	public String getPreclrNo() {
		return this.preclrNo;
	}

	public void setPreclrNo(String preclrNo) {
		this.preclrNo = preclrNo;
	}

	public Double getPreMprMatAmount() {
		return this.preMprMatAmount;
	}

	public void setPreMprMatAmount(Double preMprMatAmount) {
		this.preMprMatAmount = preMprMatAmount;
	}

	public Double getPreclrMaterialRate() {
		return this.preclrMaterialRate;
	}

	public void setPreclrMaterialRate(Double preclrMaterialRate) {
		this.preclrMaterialRate = preclrMaterialRate;
	}

	public Double getPreclrWktimeAmount() {
		return this.preclrWktimeAmount;
	}

	public void setPreclrWktimeAmount(Double preclrWktimeAmount) {
		this.preclrWktimeAmount = preclrWktimeAmount;
	}

	public Double getPreclrWktimeRate() {
		return this.preclrWktimeRate;
	}

	public void setPreclrWktimeRate(Double preclrWktimeRate) {
		this.preclrWktimeRate = preclrWktimeRate;
	}

	public Double getPreclrOtherAmount() {
		return this.preclrOtherAmount;
	}

	public void setPreclrOtherAmount(Double preclrOtherAmount) {
		this.preclrOtherAmount = preclrOtherAmount;
	}

	public Double getPreclrSumAmount() {
		return this.preclrSumAmount;
	}

	public void setPreclrSumAmount(Double preclrSumAmount) {
		this.preclrSumAmount = preclrSumAmount;
	}

	public Double getPreclrSumRate() {
		return this.preclrSumRate;
	}

	public void setPreclrSumRate(Double preclrSumRate) {
		this.preclrSumRate = preclrSumRate;
	}

	public Double getPreclrRealAmount() {
		return this.preclrRealAmount;
	}

	public void setPreclrRealAmount(Double preclrRealAmount) {
		this.preclrRealAmount = preclrRealAmount;
	}

	public Double getPreclrNoTaxCost() {
		return this.preclrNoTaxCost;
	}

	public void setPreclrNoTaxCost(Double preclrNoTaxCost) {
		this.preclrNoTaxCost = preclrNoTaxCost;
	}

	public String getPreclrInstr() {
		return this.preclrInstr;
	}

	public void setPreclrInstr(String preclrInstr) {
		this.preclrInstr = preclrInstr;
	}

	public String getPreclrRemark() {
		return this.preclrRemark;
	}

	public void setPreclrRemark(String preclrRemark) {
		this.preclrRemark = preclrRemark;
	}

	public Short getPreclrHasUnrealData() {
		return this.preclrHasUnrealData;
	}

	public void setPreclrHasUnrealData(Short preclrHasUnrealData) {
		this.preclrHasUnrealData = preclrHasUnrealData;
	}

	

	public Double getPreclrManagementFee() {
		return preclrManagementFee;
	}

	public void setPreclrManagementFee(Double preclrManagementFee) {
		this.preclrManagementFee = preclrManagementFee;
	}

	public Set getFrtPreClearingCosts() {
		return this.frtPreClearingCosts;
	}

	public void setFrtPreClearingCosts(Set frtPreClearingCosts) {
		this.frtPreClearingCosts = frtPreClearingCosts;
	}

	public Set getFrtPreWktimes() {
		return this.frtPreWktimes;
	}

	public void setFrtPreWktimes(Set frtPreWktimes) {
		this.frtPreWktimes = frtPreWktimes;
	}

	public Set getFrtPrePartses() {
		return this.frtPrePartses;
	}

	public void setFrtPrePartses(Set frtPrePartses) {
		this.frtPrePartses = frtPrePartses;
	}

	public Short getPreFlg() {
		return preFlg;
	}

	public void setPreFlg(Short preFlg) {
		this.preFlg = preFlg;
	}

	public String getPreclrTag1() {
		return preclrTag1;
	}

	public void setPreclrTag1(String preclrTag1) {
		this.preclrTag1 = preclrTag1;
	}

	public String getPreclrTag2() {
		return preclrTag2;
	}

	public void setPreclrTag2(String preclrTag2) {
		this.preclrTag2 = preclrTag2;
	}

	public Short getPreclrTag3() {
		return preclrTag3;
	}

	public void setPreclrTag3(Short preclrTag3) {
		this.preclrTag3 = preclrTag3;
	}

	public Short getPreclrTag4() {
		return preclrTag4;
	}

	public void setPreclrTag4(Short preclrTag4) {
		this.preclrTag4 = preclrTag4;
	}

	public Short getStfId() {
		return stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public Short getPreclrToMoney() {
		return preclrToMoney;
	}

	public void setPreclrToMoney(Short preclrToMoney) {
		this.preclrToMoney = preclrToMoney;
	}

	public Double getPreclrTaxCost() {
		return preclrTaxCost;
	}

	public void setPreclrTaxCost(Double preclrTaxCost) {
		this.preclrTaxCost = preclrTaxCost;
	}

}