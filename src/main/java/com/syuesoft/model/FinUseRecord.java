package com.syuesoft.model;

/**
 * FinUseRecord entity. @author MyEclipse Persistence Tools
 */

public class FinUseRecord extends BaseBean {

	// Fields

	private Integer urId;
	private StSellPercharge stSellPercharge;
	private Double urSettlementAmount;
	private Double urDeductibleAmount;
	private String preclrId;
	private String receptionId;

	// Constructors

	/** default constructor */
	public FinUseRecord() {
	}

	/** full constructor */
	public FinUseRecord(StSellPercharge stSellPercharge, Double urSettlementAmount, Double urDeductibleAmount, String preclrId, String receptionId) {
		this.stSellPercharge = stSellPercharge;
		this.urSettlementAmount = urSettlementAmount;
		this.urDeductibleAmount = urDeductibleAmount;
		this.preclrId = preclrId;
		this.receptionId = receptionId;
	}

	// Property accessors

	public Integer getUrId() {
		return this.urId;
	}

	public void setUrId(Integer urId) {
		this.urId = urId;
	}

	public StSellPercharge getStSellPercharge() {
		return this.stSellPercharge;
	}

	public void setStSellPercharge(StSellPercharge stSellPercharge) {
		this.stSellPercharge = stSellPercharge;
	}

	public Double getUrSettlementAmount() {
		return this.urSettlementAmount;
	}

	public void setUrSettlementAmount(Double urSettlementAmount) {
		this.urSettlementAmount = urSettlementAmount;
	}

	public Double getUrDeductibleAmount() {
		return this.urDeductibleAmount;
	}

	public void setUrDeductibleAmount(Double urDeductibleAmount) {
		this.urDeductibleAmount = urDeductibleAmount;
	}

	public String getPreclrId() {
		return this.preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public String getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

}