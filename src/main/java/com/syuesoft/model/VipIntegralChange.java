package com.syuesoft.model;

import java.sql.Timestamp;

/**
 * VipIntegralChange entity. @author MyEclipse Persistence Tools
 */

public class VipIntegralChange extends BaseBean {

	// Fields

	private Short vipIntegralId;
	private VipInfor vipInfor;
	private Integer reduceIntegral;
	private Integer addIntegral;
	private String present;
	private Timestamp changeDate;

	// Constructors

	/** default constructor */
	public VipIntegralChange() {
	}

	/** minimal constructor */
	public VipIntegralChange(Short vipIntegralId) {
		this.vipIntegralId = vipIntegralId;
	}

	/** full constructor */
	public VipIntegralChange(Short vipIntegralId, VipInfor vipInfor,
			Integer reduceIntegral, Integer addIntegral, String present,
			Timestamp changeDate) {
		this.vipIntegralId = vipIntegralId;
		this.vipInfor = vipInfor;
		this.reduceIntegral = reduceIntegral;
		this.addIntegral = addIntegral;
		this.present = present;
		this.changeDate = changeDate;
	}

	// Property accessors

	public Short getVipIntegralId() {
		return this.vipIntegralId;
	}

	public void setVipIntegralId(Short vipIntegralId) {
		this.vipIntegralId = vipIntegralId;
	}

	public VipInfor getVipInfor() {
		return this.vipInfor;
	}

	public void setVipInfor(VipInfor vipInfor) {
		this.vipInfor = vipInfor;
	}

	public Integer getReduceIntegral() {
		return this.reduceIntegral;
	}

	public void setReduceIntegral(Integer reduceIntegral) {
		this.reduceIntegral = reduceIntegral;
	}

	public Integer getAddIntegral() {
		return this.addIntegral;
	}

	public void setAddIntegral(Integer addIntegral) {
		this.addIntegral = addIntegral;
	}

	public String getPresent() {
		return this.present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public Timestamp getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Timestamp changeDate) {
		this.changeDate = changeDate;
	}

}