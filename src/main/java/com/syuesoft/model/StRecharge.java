package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * StRecharge entity. @author MyEclipse Persistence Tools
 */

public class StRecharge extends BaseBean {

	// Fields

	private Integer rechargeId;
	private FrtCar frtCar;
	private Double surplusMoney;
	private String perchargeType;
	private Set stSellPercharges = new HashSet(0);
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public StRecharge() {
	}

	/** full constructor */
	public StRecharge(FrtCar frtCar, Double surplusMoney, String perchargeType,
			Set stSellPercharges) {
		this.frtCar = frtCar;
		this.surplusMoney = surplusMoney;
		this.perchargeType = perchargeType;
		this.stSellPercharges = stSellPercharges;
	}

	// Property accessors

	public Integer getRechargeId() {
		return this.rechargeId;
	}

	public void setRechargeId(Integer rechargeId) {
		this.rechargeId = rechargeId;
	}

	public FrtCar getFrtCar() {
		return this.frtCar;
	}

	public void setFrtCar(FrtCar frtCar) {
		this.frtCar = frtCar;
	}

	public Double getSurplusMoney() {
		return this.surplusMoney;
	}

	public void setSurplusMoney(Double surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public String getPerchargeType() {
		return this.perchargeType;
	}

	public void setPerchargeType(String perchargeType) {
		this.perchargeType = perchargeType;
	}

	public Set getStSellPercharges() {
		return this.stSellPercharges;
	}

	public void setStSellPercharges(Set stSellPercharges) {
		this.stSellPercharges = stSellPercharges;
	}

}