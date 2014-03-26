package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FrtResevation entity. @author MyEclipse Persistence Tools
 */

public class FrtResevation extends BaseBean {

	// Fields

	private String resvId;
	private FrtCustom frtCustom;
	private FrtCar frtCar;
	private Date resvEnterTime;
	private Short reptId;
	private Short repwkId;
	private String resvStatus;
	private Integer resvDistance;
	private Date resvRealTime;
	private Short stfId;
	private String resvFixpel;
	private String resvFixtel;
	private String resvFixphone;
	private Short repgrpId;
	private String resvType;
	private String resvRemark;
	private Short resvToRcpt;
	private Short frtresvFlg;
	private String resevationRepPer;
	private Short bespeakPrizeTag;
    private Integer enterpriseId;
	private FrtRushToRepair frtRushToRepair;
	private Set frtResvVehicleStructures = new HashSet(0);
	private Set frtResvItems = new HashSet(0);
	private Set frtResvPartses = new HashSet(0);

	  public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Property accessors

	public String getResvId() {
		return this.resvId;
	}

	public void setResvId(String resvId) {
		this.resvId = resvId;
	}

	public FrtCustom getFrtCustom() {
		return this.frtCustom;
	}

	public void setFrtCustom(FrtCustom frtCustom) {
		this.frtCustom = frtCustom;
	}

	public FrtCar getFrtCar() {
		return this.frtCar;
	}

	public void setFrtCar(FrtCar frtCar) {
		this.frtCar = frtCar;
	}

	
	public Date getResvEnterTime() {
		return this.resvEnterTime;
	}

	public void setResvEnterTime(Date resvEnterTime) {
		this.resvEnterTime = resvEnterTime;
	}

	public Short getReptId() {
		return this.reptId;
	}

	public void setReptId(Short reptId) {
		this.reptId = reptId;
	}

	public Short getRepwkId() {
		return this.repwkId;
	}

	public void setRepwkId(Short repwkId) {
		this.repwkId = repwkId;
	}

	public String getResvStatus() {
		return this.resvStatus;
	}

	public void setResvStatus(String resvStatus) {
		this.resvStatus = resvStatus;
	}

	public Integer getResvDistance() {
		return this.resvDistance;
	}

	public void setResvDistance(Integer resvDistance) {
		this.resvDistance = resvDistance;
	}

	public Date getResvRealTime() {
		return this.resvRealTime;
	}

	public void setResvRealTime(Date resvRealTime) {
		this.resvRealTime = resvRealTime;
	}

	public Short getStfId() {
		return this.stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public String getResvFixpel() {
		return this.resvFixpel;
	}

	public void setResvFixpel(String resvFixpel) {
		this.resvFixpel = resvFixpel;
	}

	public String getResvFixtel() {
		return this.resvFixtel;
	}

	public void setResvFixtel(String resvFixtel) {
		this.resvFixtel = resvFixtel;
	}

	public String getResvFixphone() {
		return this.resvFixphone;
	}

	public void setResvFixphone(String resvFixphone) {
		this.resvFixphone = resvFixphone;
	}

	public Short getRepgrpId() {
		return this.repgrpId;
	}

	public void setRepgrpId(Short repgrpId) {
		this.repgrpId = repgrpId;
	}

	public String getResvType() {
		return this.resvType;
	}

	public void setResvType(String resvType) {
		this.resvType = resvType;
	}

	public String getResvRemark() {
		return this.resvRemark;
	}

	public void setResvRemark(String resvRemark) {
		this.resvRemark = resvRemark;
	}

	public FrtRushToRepair getFrtRushToRepair() {
		return this.frtRushToRepair;
	}

	public void setFrtRushToRepair(FrtRushToRepair frtRushToRepair) {
		this.frtRushToRepair = frtRushToRepair;
	}

	public Set getFrtResvVehicleStructures() {
		return this.frtResvVehicleStructures;
	}

	public void setFrtResvVehicleStructures(Set frtResvVehicleStructures) {
		this.frtResvVehicleStructures = frtResvVehicleStructures;
	}

	public Set getFrtResvItems() {
		return this.frtResvItems;
	}

	public void setFrtResvItems(Set frtResvItems) {
		this.frtResvItems = frtResvItems;
	}

	public Set getFrtResvPartses() {
		return this.frtResvPartses;
	}

	public void setFrtResvPartses(Set frtResvPartses) {
		this.frtResvPartses = frtResvPartses;
	}

	public Short getResvToRcpt() {
		return resvToRcpt;
	}

	public void setResvToRcpt(Short resvToRcpt) {
		this.resvToRcpt = resvToRcpt;
	}

	public Short getFrtresvFlg() {
		return frtresvFlg;
	}

	public void setFrtresvFlg(Short frtresvFlg) {
		this.frtresvFlg = frtresvFlg;
	}

	public String getResevationRepPer() {
		return resevationRepPer;
	}

	public void setResevationRepPer(String resevationRepPer) {
		this.resevationRepPer = resevationRepPer;
	}

	public Short getBespeakPrizeTag() {
		return bespeakPrizeTag;
	}

	public void setBespeakPrizeTag(Short bespeakPrizeTag) {
		this.bespeakPrizeTag = bespeakPrizeTag;
	}

}