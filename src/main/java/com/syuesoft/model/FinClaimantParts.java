package com.syuesoft.model;

/**
 * FinClaimantParts entity. @author MyEclipse Persistence Tools
 */

public class FinClaimantParts extends BaseBean {

	// Fields

	private Integer claimantpIndex;
	private FinClaimantMain finClaimantMain;
	private String partsId;
	private String partsName;
	private Double claimantpCount;
	private Short relcampId;
	private Double claimantpPrice;
	private Double claimantpAmount;
	private Short reptypId;
	private Short storeId;
	private Double claimantpTaxCost;
	private Double claimantpNoTaxCost;

	// Constructors

	/** default constructor */
	public FinClaimantParts() {
	}

	/** full constructor */
	public FinClaimantParts(FinClaimantMain finClaimantMain, String partsId,
			String partsName, Double claimantpCount, Short relcampId,
			Double claimantpPrice, Double claimantpAmount, Short reptypId) {
		this.finClaimantMain = finClaimantMain;
		this.partsId = partsId;
		this.partsName = partsName;
		this.claimantpCount = claimantpCount;
		this.relcampId = relcampId;
		this.claimantpPrice = claimantpPrice;
		this.claimantpAmount = claimantpAmount;
		this.reptypId = reptypId;
	}

	// Property accessors

	public Integer getClaimantpIndex() {
		return this.claimantpIndex;
	}

	public void setClaimantpIndex(Integer claimantpIndex) {
		this.claimantpIndex = claimantpIndex;
	}

	public FinClaimantMain getFinClaimantMain() {
		return this.finClaimantMain;
	}

	public void setFinClaimantMain(FinClaimantMain finClaimantMain) {
		this.finClaimantMain = finClaimantMain;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public String getPartsName() {
		return this.partsName;
	}

	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}

	public Double getClaimantpCount() {
		return this.claimantpCount;
	}

	public void setClaimantpCount(Double claimantpCount) {
		this.claimantpCount = claimantpCount;
	}

	public Short getRelcampId() {
		return this.relcampId;
	}

	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}

	public Double getClaimantpPrice() {
		return this.claimantpPrice;
	}

	public void setClaimantpPrice(Double claimantpPrice) {
		this.claimantpPrice = claimantpPrice;
	}

	public Double getClaimantpAmount() {
		return this.claimantpAmount;
	}

	public void setClaimantpAmount(Double claimantpAmount) {
		this.claimantpAmount = claimantpAmount;
	}

	public Short getReptypId() {
		return this.reptypId;
	}

	public void setReptypId(Short reptypId) {
		this.reptypId = reptypId;
	}

	public Short getStoreId() {
		return storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public Double getClaimantpTaxCost() {
		return claimantpTaxCost;
	}

	public void setClaimantpTaxCost(Double claimantpTaxCost) {
		this.claimantpTaxCost = claimantpTaxCost;
	}

	public Double getClaimantpNoTaxCost() {
		return claimantpNoTaxCost;
	}

	public void setClaimantpNoTaxCost(Double claimantpNoTaxCost) {
		this.claimantpNoTaxCost = claimantpNoTaxCost;
	}

}