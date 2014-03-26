package com.syuesoft.model;

/**
 * FinClaimsReceivables entity. @author MyEclipse Persistence Tools
 */

public class FinClaimsReceivables extends BaseBean {

	// Fields

	private String crId;
	private String claimantmId;
	private Double crReceivables;
	private Double crCumulative;
	private Short crAllowArrearsAge;
	private Short crReceivablesArrearsAge;
	private Short crAllowFlag;
	private Double crAllowArrearsAmount;
	private Short crAllowArrearsNumber;
	private Boolean crPrintFlag;
	private Short crUnFinished;
	private Short crSubstitute;
	private Double crArrears;
	private Short crBatchTag;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public FinClaimsReceivables() {
	}

	/** minimal constructor */
	public FinClaimsReceivables(String crId) {
		this.crId = crId;
	}


	// Property accessors

	public String getCrId() {
		return this.crId;
	}

	public void setCrId(String crId) {
		this.crId = crId;
	}

	public String getClaimantmId() {
		return this.claimantmId;
	}

	public void setClaimantmId(String claimantmId) {
		this.claimantmId = claimantmId;
	}

	public Double getCrReceivables() {
		return this.crReceivables;
	}

	public void setCrReceivables(Double crReceivables) {
		this.crReceivables = crReceivables;
	}
	
	public Double getCrCumulative() {
		return crCumulative;
	}

	public void setCrCumulative(Double crCumulative) {
		this.crCumulative = crCumulative;
	}

	public Short getCrAllowArrearsAge() {
		return crAllowArrearsAge;
	}

	public void setCrAllowArrearsAge(Short crAllowArrearsAge) {
		this.crAllowArrearsAge = crAllowArrearsAge;
	}

	public Double getCrAllowArrearsAmount() {
		return crAllowArrearsAmount;
	}

	public void setCrAllowArrearsAmount(Double crAllowArrearsAmount) {
		this.crAllowArrearsAmount = crAllowArrearsAmount;
	}

	public Short getCrAllowArrearsNumber() {
		return crAllowArrearsNumber;
	}

	public void setCrAllowArrearsNumber(Short crAllowArrearsNumber) {
		this.crAllowArrearsNumber = crAllowArrearsNumber;
	}

	public Short getCrAllowFlag() {
		return this.crAllowFlag;
	}

	public void setCrAllowFlag(Short crAllowFlag) {
		this.crAllowFlag = crAllowFlag;
	}

	public Short getCrReceivablesArrearsAge() {
		return this.crReceivablesArrearsAge;
	}

	public void setCrReceivablesArrearsAge(Short crReceivablesArrearsAge) {
		this.crReceivablesArrearsAge = crReceivablesArrearsAge;
	}

	public Boolean getCrPrintFlag() {
		return this.crPrintFlag;
	}

	public void setCrPrintFlag(Boolean crPrintFlag) {
		this.crPrintFlag = crPrintFlag;
	}

	public Short getCrUnFinished() {
		return crUnFinished;
	}

	public void setCrUnFinished(Short crUnFinished) {
		this.crUnFinished = crUnFinished;
	}

	public Short getCrSubstitute() {
		return crSubstitute;
	}

	public void setCrSubstitute(Short crSubstitute) {
		this.crSubstitute = crSubstitute;
	}

	public Double getCrArrears() {
		return crArrears;
	}

	public void setCrArrears(Double crArrears) {
		this.crArrears = crArrears;
	}

	public Short getCrBatchTag() {
		return crBatchTag;
	}

	public void setCrBatchTag(Short crBatchTag) {
		this.crBatchTag = crBatchTag;
	}
	
}