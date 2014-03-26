package com.syuesoft.model;

/**
 * FrtPrePartsAttach entity. @author MyEclipse Persistence Tools
 */

public class FrtPrePartsAttach extends BaseBean {

	// Fields

	private String preclrId;
	private FrtPreClearingAttach frtPreClearingAttach;
	private Integer partsIndex;
	private String partsId;
	private Short partsCount;
	private Double stinvdPrice;
	private Double partsPrice;
	private Double partsAmount;
	private Short relcampId;
	private Short reptypId;
	private Double settlementDiscount;

	// Constructors

	/** default constructor */
	public FrtPrePartsAttach() {
	}

	/** minimal constructor */
	public FrtPrePartsAttach(String preclrId,
			FrtPreClearingAttach frtPreClearingAttach) {
		this.preclrId = preclrId;
		this.frtPreClearingAttach = frtPreClearingAttach;
	}

	/** full constructor */
	public FrtPrePartsAttach(String preclrId,
			FrtPreClearingAttach frtPreClearingAttach, Integer partsIndex,
			String partsId, Short partsCount, Double stinvdPrice,
			Double partsPrice, Double partsAmount, Short relcampId,
			Short reptypId, Double settlementDiscount) {
		this.preclrId = preclrId;
		this.frtPreClearingAttach = frtPreClearingAttach;
		this.partsIndex = partsIndex;
		this.partsId = partsId;
		this.partsCount = partsCount;
		this.stinvdPrice = stinvdPrice;
		this.partsPrice = partsPrice;
		this.partsAmount = partsAmount;
		this.relcampId = relcampId;
		this.reptypId = reptypId;
		this.settlementDiscount = settlementDiscount;
	}

	// Property accessors

	public String getPreclrId() {
		return this.preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public FrtPreClearingAttach getFrtPreClearingAttach() {
		return this.frtPreClearingAttach;
	}

	public void setFrtPreClearingAttach(
			FrtPreClearingAttach frtPreClearingAttach) {
		this.frtPreClearingAttach = frtPreClearingAttach;
	}

	public Integer getPartsIndex() {
		return this.partsIndex;
	}

	public void setPartsIndex(Integer partsIndex) {
		this.partsIndex = partsIndex;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Short getPartsCount() {
		return this.partsCount;
	}

	public void setPartsCount(Short partsCount) {
		this.partsCount = partsCount;
	}

	public Double getStinvdPrice() {
		return this.stinvdPrice;
	}

	public void setStinvdPrice(Double stinvdPrice) {
		this.stinvdPrice = stinvdPrice;
	}

	public Double getPartsPrice() {
		return this.partsPrice;
	}

	public void setPartsPrice(Double partsPrice) {
		this.partsPrice = partsPrice;
	}

	public Double getPartsAmount() {
		return this.partsAmount;
	}

	public void setPartsAmount(Double partsAmount) {
		this.partsAmount = partsAmount;
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

	public Double getSettlementDiscount() {
		return this.settlementDiscount;
	}

	public void setSettlementDiscount(Double settlementDiscount) {
		this.settlementDiscount = settlementDiscount;
	}

}