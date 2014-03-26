package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StGoodsTune entity. @author MyEclipse Persistence Tools
 */

public class StGoodsTune extends BaseBean {

	// Fields

	private String tuneId;
	private Date tuneDate;
	private Short stockOut;
	private Short stockOutPer;
	private Short stockIn;
	private Short stockInPer;
	private Short shipPer;
	private Integer totalNum;
	private Double totalAmount;
	private Integer damageNum;
	private Double damageMoney;
	private Set stTuneItems = new HashSet(0);

	// Constructors

	/** default constructor */
	public StGoodsTune() {
	}

	/** minimal constructor */
	public StGoodsTune(String tuneId) {
		this.tuneId = tuneId;
	}

	/** full constructor */
	public StGoodsTune(String tuneId, Date tuneDate, Short stockOut,
			Short stockOutPer, Short stockIn, Short stockInPer, Short shipPer,
			Integer totalNum, Double totalAmount, Integer damageNum,
			Double damageMoney, Set stTuneItems) {
		this.tuneId = tuneId;
		this.tuneDate = tuneDate;
		this.stockOut = stockOut;
		this.stockOutPer = stockOutPer;
		this.stockIn = stockIn;
		this.stockInPer = stockInPer;
		this.shipPer = shipPer;
		this.totalNum = totalNum;
		this.totalAmount = totalAmount;
		this.damageNum = damageNum;
		this.damageMoney = damageMoney;
		this.stTuneItems = stTuneItems;
	}

	// Property accessors

	public String getTuneId() {
		return this.tuneId;
	}

	public void setTuneId(String tuneId) {
		this.tuneId = tuneId;
	}

	public Date getTuneDate() {
		return this.tuneDate;
	}

	public void setTuneDate(Date tuneDate) {
		this.tuneDate = tuneDate;
	}

	public Short getStockOut() {
		return this.stockOut;
	}

	public void setStockOut(Short stockOut) {
		this.stockOut = stockOut;
	}

	public Short getStockOutPer() {
		return this.stockOutPer;
	}

	public void setStockOutPer(Short stockOutPer) {
		this.stockOutPer = stockOutPer;
	}

	public Short getStockIn() {
		return this.stockIn;
	}

	public void setStockIn(Short stockIn) {
		this.stockIn = stockIn;
	}

	public Short getStockInPer() {
		return this.stockInPer;
	}

	public void setStockInPer(Short stockInPer) {
		this.stockInPer = stockInPer;
	}

	public Short getShipPer() {
		return this.shipPer;
	}

	public void setShipPer(Short shipPer) {
		this.shipPer = shipPer;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getDamageNum() {
		return this.damageNum;
	}

	public void setDamageNum(Integer damageNum) {
		this.damageNum = damageNum;
	}

	public Double getDamageMoney() {
		return this.damageMoney;
	}

	public void setDamageMoney(Double damageMoney) {
		this.damageMoney = damageMoney;
	}

	public Set getStTuneItems() {
		return this.stTuneItems;
	}

	public void setStTuneItems(Set stTuneItems) {
		this.stTuneItems = stTuneItems;
	}

}