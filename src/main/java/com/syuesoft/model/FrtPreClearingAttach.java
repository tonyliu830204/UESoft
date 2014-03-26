package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FrtPreClearingAttach entity. @author MyEclipse Persistence Tools
 */

public class FrtPreClearingAttach extends BaseBean {

	// Fields

	private String preclrId;
	private Date preclrTime;
	private Short preclrInoiceType;
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
	private Double preclrCost;
	private Short preclrFlg;
	private String preclrInstr;
	private String preclrRemark;
	private Double managementFee;
	private Set frtPreWktimeAttachs = new HashSet(0);
	private Set frtPrePartsAttachs = new HashSet(0);

	// Constructors

	/** default constructor */
	public FrtPreClearingAttach() {
	}

	/** full constructor */
	public FrtPreClearingAttach(Date preclrTime, Short preclrInoiceType,
			Date preclrInvoiceTime, String preclrNo,
			Double preMprMatAmount, Double preclrMaterialRate,
			Double preclrWktimeAmount, Double preclrWktimeRate,
			Double preclrOtherAmount, Double preclrSumAmount,
			Double preclrSumRate, Double preclrRealAmount, Double preclrCost,
			Short preclrFlg, String preclrInstr, String preclrRemark,
			Double managementFee, Set frtPreWktimeAttachs,
			Set frtPrePartsAttachs) {
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
		this.preclrCost = preclrCost;
		this.preclrFlg = preclrFlg;
		this.preclrInstr = preclrInstr;
		this.preclrRemark = preclrRemark;
		this.managementFee = managementFee;
		this.frtPreWktimeAttachs = frtPreWktimeAttachs;
		this.frtPrePartsAttachs = frtPrePartsAttachs;
	}

	// Property accessors

	public String getPreclrId() {
		return this.preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public Date getPreclrTime() {
		return this.preclrTime;
	}

	public void setPreclrTime(Date preclrTime) {
		this.preclrTime = preclrTime;
	}

	public Short getPreclrInoiceType() {
		return this.preclrInoiceType;
	}

	public void setPreclrInoiceType(Short preclrInoiceType) {
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

	public Double getPreclrCost() {
		return this.preclrCost;
	}

	public void setPreclrCost(Double preclrCost) {
		this.preclrCost = preclrCost;
	}

	public Short getPreclrFlg() {
		return this.preclrFlg;
	}

	public void setPreclrFlg(Short preclrFlg) {
		this.preclrFlg = preclrFlg;
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

	public Double getManagementFee() {
		return this.managementFee;
	}

	public void setManagementFee(Double managementFee) {
		this.managementFee = managementFee;
	}

	public Set getFrtPreWktimeAttachs() {
		return this.frtPreWktimeAttachs;
	}

	public void setFrtPreWktimeAttachs(Set frtPreWktimeAttachs) {
		this.frtPreWktimeAttachs = frtPreWktimeAttachs;
	}

	public Set getFrtPrePartsAttachs() {
		return this.frtPrePartsAttachs;
	}

	public void setFrtPrePartsAttachs(Set frtPrePartsAttachs) {
		this.frtPrePartsAttachs = frtPrePartsAttachs;
	}

}