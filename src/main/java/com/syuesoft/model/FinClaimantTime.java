package com.syuesoft.model;

/**
 * FinClaimantTime entity. @author MyEclipse Persistence Tools
 */

public class FinClaimantTime extends BaseBean {

	// Fields

	private Integer claimanttIndex;
	private FinClaimantMain finClaimantMain;
	private String repitemId;
	private String repitemName;
	private Double claimanttTime;
	private Double claimanttInnerTime;
	private Double claimanttAmount;
	private Short relcampId;
	private Short reptypId;
	private Short stfId;
	
	//项目金额
	private Double repitemAmount;
	//操作员名称
	private String stfName;
	

	// Constructors

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public Double getRepitemAmount() {
		return repitemAmount;
	}

	public void setRepitemAmount(Double repitemAmount) {
		this.repitemAmount = repitemAmount;
	}

	/** default constructor */
	public FinClaimantTime() {
	}

	/** minimal constructor */
	public FinClaimantTime(Integer claimanttIndex) {
		this.claimanttIndex = claimanttIndex;
	}

	/** full constructor */
	public FinClaimantTime(Integer claimanttIndex,
			FinClaimantMain finClaimantMain, String repitemId,
			String repitemName, Double claimanttTime, Double claimanttAmount,
			Short relcampId, Short reptypId, Short stfId) {
		this.claimanttIndex = claimanttIndex;
		this.finClaimantMain = finClaimantMain;
		this.repitemId = repitemId;
		this.repitemName = repitemName;
		this.claimanttTime = claimanttTime;
		this.claimanttAmount = claimanttAmount;
		this.relcampId = relcampId;
		this.reptypId = reptypId;
		this.stfId = stfId;
	}

	// Property accessors

	public Integer getClaimanttIndex() {
		return this.claimanttIndex;
	}

	public void setClaimanttIndex(Integer claimanttIndex) {
		this.claimanttIndex = claimanttIndex;
	}

	public FinClaimantMain getFinClaimantMain() {
		return this.finClaimantMain;
	}

	public void setFinClaimantMain(FinClaimantMain finClaimantMain) {
		this.finClaimantMain = finClaimantMain;
	}

	public String getRepitemId() {
		return this.repitemId;
	}

	public void setRepitemId(String repitemId) {
		this.repitemId = repitemId;
	}

	public String getRepitemName() {
		return this.repitemName;
	}

	public void setRepitemName(String repitemName) {
		this.repitemName = repitemName;
	}

	public Double getClaimanttTime() {
		return this.claimanttTime;
	}

	public void setClaimanttTime(Double claimanttTime) {
		this.claimanttTime = claimanttTime;
	}

	public Double getClaimanttAmount() {
		return this.claimanttAmount;
	}

	public void setClaimanttAmount(Double claimanttAmount) {
		this.claimanttAmount = claimanttAmount;
	}

	public Short getRelcampId() {
		return this.relcampId;
	}

	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}

	public Short getReptypId() {
		return this.reptypId;
	}

	public void setReptypId(Short reptypId) {
		this.reptypId = reptypId;
	}

	public Short getStfId() {
		return this.stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public Double getClaimanttInnerTime() {
		return claimanttInnerTime;
	}

	public void setClaimanttInnerTime(Double claimanttInnerTime) {
		this.claimanttInnerTime = claimanttInnerTime;
	}

}