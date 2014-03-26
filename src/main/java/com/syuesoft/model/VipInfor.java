package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * VipInfor entity. @author MyEclipse Persistence Tools
 */

public class VipInfor extends BaseBean {

	// Fields

	private Short vipId;
	private FrtCar frtCar;
	private VipType vipType;
	private String vipNumber;
	private Integer vipIntegral;
	private String vipHobby;
	private String vipTel;
	private Date joinTime;
	private Date endTime;
	private Set vipIntegralChanges = new HashSet(0);

	// Constructors

	/** default constructor */
	public VipInfor() {
	}

	/** minimal constructor */
	public VipInfor(Short vipId) {
		this.vipId = vipId;
	}

	/** full constructor */
	public VipInfor(Short vipId, FrtCar frtCar, VipType vipType,
			String vipNumber, Integer vipIntegral, String vipHobby,
			String vipTel, Date joinTime, Date endTime, Set vipIntegralChanges) {
		this.vipId = vipId;
		this.frtCar = frtCar;
		this.vipType = vipType;
		this.vipNumber = vipNumber;
		this.vipIntegral = vipIntegral;
		this.vipHobby = vipHobby;
		this.vipTel = vipTel;
		this.joinTime = joinTime;
		this.endTime = endTime;
		this.vipIntegralChanges = vipIntegralChanges;
	}

	// Property accessors

	public Short getVipId() {
		return this.vipId;
	}

	public void setVipId(Short vipId) {
		this.vipId = vipId;
	}

	public FrtCar getFrtCar() {
		return this.frtCar;
	}

	public void setFrtCar(FrtCar frtCar) {
		this.frtCar = frtCar;
	}

	public VipType getVipType() {
		return this.vipType;
	}

	public void setVipType(VipType vipType) {
		this.vipType = vipType;
	}

	public String getVipNumber() {
		return this.vipNumber;
	}

	public void setVipNumber(String vipNumber) {
		this.vipNumber = vipNumber;
	}

	public Integer getVipIntegral() {
		return this.vipIntegral;
	}

	public void setVipIntegral(Integer vipIntegral) {
		this.vipIntegral = vipIntegral;
	}

	public String getVipHobby() {
		return this.vipHobby;
	}

	public void setVipHobby(String vipHobby) {
		this.vipHobby = vipHobby;
	}

	public String getVipTel() {
		return this.vipTel;
	}

	public void setVipTel(String vipTel) {
		this.vipTel = vipTel;
	}

	public Date getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Set getVipIntegralChanges() {
		return this.vipIntegralChanges;
	}

	public void setVipIntegralChanges(Set vipIntegralChanges) {
		this.vipIntegralChanges = vipIntegralChanges;
	}

}