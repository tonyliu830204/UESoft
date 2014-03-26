package com.syuesoft.model;

/**
 * FrtPreWktimeAttach entity. @author MyEclipse Persistence Tools
 */

public class FrtPreWktimeAttach extends BaseBean {

	// Fields

	private String preclrId;
	private FrtPreClearingAttach frtPreClearingAttach;
	private Integer wktimeIndex;
	private String repitemId;
	private String repitemName;
	private Double wktime;
	private Double innerWktime;
	private Double wktimeAmount;
	private Short relcampId;
	private Short reptypId;
	private Short stfId;
	private Double settlementDiscount;

	// Constructors

	/** default constructor */
	public FrtPreWktimeAttach() {
	}

	/** minimal constructor */
	public FrtPreWktimeAttach(String preclrId,
			FrtPreClearingAttach frtPreClearingAttach) {
		this.preclrId = preclrId;
		this.frtPreClearingAttach = frtPreClearingAttach;
	}

	/** full constructor */
	public FrtPreWktimeAttach(String preclrId,
			FrtPreClearingAttach frtPreClearingAttach, Integer wktimeIndex,
			String repitemId, String repitemName, Double wktime,
			Double innerWktime, Double wktimeAmount, Short relcampId,
			Short reptypId, Short stfId, Double settlementDiscount) {
		this.preclrId = preclrId;
		this.frtPreClearingAttach = frtPreClearingAttach;
		this.wktimeIndex = wktimeIndex;
		this.repitemId = repitemId;
		this.repitemName = repitemName;
		this.wktime = wktime;
		this.innerWktime = innerWktime;
		this.wktimeAmount = wktimeAmount;
		this.relcampId = relcampId;
		this.reptypId = reptypId;
		this.stfId = stfId;
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

	public Integer getWktimeIndex() {
		return this.wktimeIndex;
	}

	public void setWktimeIndex(Integer wktimeIndex) {
		this.wktimeIndex = wktimeIndex;
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

	public Double getWktime() {
		return this.wktime;
	}

	public void setWktime(Double wktime) {
		this.wktime = wktime;
	}

	public Double getInnerWktime() {
		return this.innerWktime;
	}

	public void setInnerWktime(Double innerWktime) {
		this.innerWktime = innerWktime;
	}

	public Double getWktimeAmount() {
		return this.wktimeAmount;
	}

	public void setWktimeAmount(Double wktimeAmount) {
		this.wktimeAmount = wktimeAmount;
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

	public Double getSettlementDiscount() {
		return this.settlementDiscount;
	}

	public void setSettlementDiscount(Double settlementDiscount) {
		this.settlementDiscount = settlementDiscount;
	}

}