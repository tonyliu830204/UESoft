package com.syuesoft.model;

import java.util.Date;

/**
 * FinRepCollection entity. @author MyEclipse Persistence Tools
 */


public class FinRepCollection extends BaseBean {

	// Fields
	private String repcollId;
	private FrtPreClearing frtPreClearing;
	private FinClaimantMain finClaimantMain;
	private Boolean repcollType;
	private Date repcollTime;
	private String repcollFlg;
	private Double repcollAmount;
	private Boolean repcollKind;
	private Short repcollPayment;
	private String repcollRemakr;
    private double discountAmont;
	// Constructors

	/** default constructor */
	public FinRepCollection() {
	}

	/** minimal constructor */
	public FinRepCollection(String repcollId, FrtPreClearing frtPreClearing) {
		this.repcollId = repcollId;
		this.frtPreClearing = frtPreClearing;
	}

	/** full constructor */
	public FinRepCollection(String repcollId, FrtPreClearing frtPreClearing,
			FinClaimantMain finClaimantMain, Boolean repcollType,
			Date repcollTime, String repcollFlg, Double repcollAmount,
			Boolean repcollKind, Short repcollPayment, String repcollRemakr,double discountAmont) {
		this.repcollId = repcollId;
		this.frtPreClearing = frtPreClearing;
		this.finClaimantMain = finClaimantMain;
		this.repcollType = repcollType;
		this.repcollTime = repcollTime;
		this.repcollFlg = repcollFlg;
		this.repcollAmount = repcollAmount;
		this.repcollKind = repcollKind;
		this.repcollPayment = repcollPayment;
		this.repcollRemakr = repcollRemakr;
		this.discountAmont=discountAmont;
	}

	public String getRepcollId() {
		return this.repcollId;
	}

	public double getDiscountAmont() {
		return discountAmont;
	}

	public void setDiscountAmont(double discountAmont) {
		this.discountAmont = discountAmont;
	}

	public void setRepcollId(String repcollId) {
		this.repcollId = repcollId;
	}

	public FrtPreClearing getFrtPreClearing() {
		return this.frtPreClearing;
	}

	public void setFrtPreClearing(FrtPreClearing frtPreClearing) {
		this.frtPreClearing = frtPreClearing;
	}

	public FinClaimantMain getFinClaimantMain() {
		return this.finClaimantMain;
	}

	public void setFinClaimantMain(FinClaimantMain finClaimantMain) {
		this.finClaimantMain = finClaimantMain;
	}

	public Boolean getRepcollType() {
		return this.repcollType;
	}

	public void setRepcollType(Boolean repcollType) {
		this.repcollType = repcollType;
	}

	public Date getRepcollTime() {
		return this.repcollTime;
	}

	public void setRepcollTime(Date repcollTime) {
		this.repcollTime = repcollTime;
	}

	public String getRepcollFlg() {
		return this.repcollFlg;
	}

	public void setRepcollFlg(String repcollFlg) {
		this.repcollFlg = repcollFlg;
	}

	public Double getRepcollAmount() {
		return this.repcollAmount;
	}

	public void setRepcollAmount(Double repcollAmount) {
		this.repcollAmount = repcollAmount;
	}

	public Boolean getRepcollKind() {
		return this.repcollKind;
	}

	public void setRepcollKind(Boolean repcollKind) {
		this.repcollKind = repcollKind;
	}

	public Short getRepcollPayment() {
		return this.repcollPayment;
	}

	public void setRepcollPayment(Short repcollPayment) {
		this.repcollPayment = repcollPayment;
	}

	public String getRepcollRemakr() {
		return this.repcollRemakr;
	}

	public void setRepcollRemakr(String repcollRemakr) {
		this.repcollRemakr = repcollRemakr;
	}

}