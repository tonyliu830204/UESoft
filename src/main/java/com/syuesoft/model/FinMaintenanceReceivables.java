package com.syuesoft.model;

/**
 * FinMaintenanceReceivables entity. @author MyEclipse Persistence Tools
 */

public class FinMaintenanceReceivables extends BaseBean {

	// Fields

	private String mrId;
	private String preclrId;
	private Double mrReceivables;
	private Double mrCumulative;
	private Short mrAllowArrearsAge;
	private Short mrReceivablesArrearsAge;
	private Boolean mrAllowFlag;
	private Double mrAllowArrearsAmount;
	private Short mrAllowArrearsNumber;
	private Boolean mrPrintFlag;
	private Short mrUnFinished;
	private Short mrSubstitute;
	private Double mrArrears;
	private Short mrBatchTag;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public FinMaintenanceReceivables() {
	}

	/** full constructor */
	public FinMaintenanceReceivables(String preclrId, Double mrReceivables,
			Short mrAllowArrearsAge, Short mrReceivablesArrearsAge,
			Boolean mrAllowFlag, Double mrAllowArrearsAmount,
			Short mrAllowArrearsNumber, Boolean mrPrintFlag) {
		this.preclrId = preclrId;
		this.mrReceivables = mrReceivables;
		this.mrAllowArrearsAge = mrAllowArrearsAge;
		this.mrReceivablesArrearsAge = mrReceivablesArrearsAge;
		this.mrAllowFlag = mrAllowFlag;
		this.mrAllowArrearsAmount = mrAllowArrearsAmount;
		this.mrAllowArrearsNumber = mrAllowArrearsNumber;
		this.mrPrintFlag = mrPrintFlag;
	}

	// Property accessors

	public String getMrId() {
		return this.mrId;
	}

	public void setMrId(String mrId) {
		this.mrId = mrId;
	}

	public String getPreclrId() {
		return this.preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public Double getMrReceivables() {
		return this.mrReceivables;
	}

	public void setMrReceivables(Double mrReceivables) {
		this.mrReceivables = mrReceivables;
	}

	public Short getMrAllowArrearsAge() {
		return this.mrAllowArrearsAge;
	}

	public void setMrAllowArrearsAge(Short mrAllowArrearsAge) {
		this.mrAllowArrearsAge = mrAllowArrearsAge;
	}

	public Short getMrReceivablesArrearsAge() {
		return this.mrReceivablesArrearsAge;
	}

	public void setMrReceivablesArrearsAge(Short mrReceivablesArrearsAge) {
		this.mrReceivablesArrearsAge = mrReceivablesArrearsAge;
	}

	public Boolean getMrAllowFlag() {
		return this.mrAllowFlag;
	}

	public void setMrAllowFlag(Boolean mrAllowFlag) {
		this.mrAllowFlag = mrAllowFlag;
	}

	public Double getMrAllowArrearsAmount() {
		return this.mrAllowArrearsAmount;
	}

	public void setMrAllowArrearsAmount(Double mrAllowArrearsAmount) {
		this.mrAllowArrearsAmount = mrAllowArrearsAmount;
	}

	public Short getMrAllowArrearsNumber() {
		return this.mrAllowArrearsNumber;
	}

	public void setMrAllowArrearsNumber(Short mrAllowArrearsNumber) {
		this.mrAllowArrearsNumber = mrAllowArrearsNumber;
	}

	public Boolean getMrPrintFlag() {
		return this.mrPrintFlag;
	}

	public void setMrPrintFlag(Boolean mrPrintFlag) {
		this.mrPrintFlag = mrPrintFlag;
	}

	public Double getMrCumulative() {
		return mrCumulative;
	}

	public void setMrCumulative(Double mrCumulative) {
		this.mrCumulative = mrCumulative;
	}
	public Short getMrUnFinished() {
		return mrUnFinished;
	}

	public void setMrUnFinished(Short mrUnFinished) {
		this.mrUnFinished = mrUnFinished;
	}

	public Short getMrSubstitute() {
		return mrSubstitute;
	}

	public void setMrSubstitute(Short mrSubstitute) {
		this.mrSubstitute = mrSubstitute;
	}

	public Double getMrArrears() {
		return mrArrears;
	}

	public void setMrArrears(Double mrArrears) {
		this.mrArrears = mrArrears;
	}

	public Short getMrBatchTag() {
		return mrBatchTag;
	}

	public void setMrBatchTag(Short mrBatchTag) {
		this.mrBatchTag = mrBatchTag;
	}
	
}