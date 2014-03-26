package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FrtReception entity. @author MyEclipse Persistence Tools
 */


public class FrtReception extends BaseBean {

	// Fields

	private String receptionId;
	private FrtCustom frtCustom;
	private FrtCar frtCar;
	private Reptype reptype;
	private String resvId;
	private Integer receptionDistance;
	private String receptionMaintFlg;
	private String receptionStatus;
	private Short repgrpId;
	private Short repwkId;
	private Date receptionEndTime;
	private Short receptionTechnician;
	private Short receptionInsurPer;
	private String receptionRepPer;
	private String propRepPer;
	private String propPhone;
	private String propTel;
	private String valuables;
	private String fuelSituation;
	private String oldPieces;
	private Short finComId;
	private String finStatus;
	private Date expDelCarTime;
	private String problemDesc;
	private Short rcptFlg;
	private Short finTag;
	private Short finAllTag;
	private String receptionRemark;
	private Date interDate;
	private Short receptor;
	private Short correction;
	private Double managementFee;
	private String rcptBranch;
	private Date receptionFactTime;
	
	private Set frtReceptionCosts = new HashSet(0);
	private Set frtRcptPartses = new HashSet(0);
	private Set frtRcptItems = new HashSet(0);
	
	private Set frtReceptionVehicleStructures = new HashSet(0);	
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }

	public Set getFrtReceptionVehicleStructures() {
		return frtReceptionVehicleStructures;
	}

	public void setFrtReceptionVehicleStructures(Set frtReceptionVehicleStructures) {
		this.frtReceptionVehicleStructures = frtReceptionVehicleStructures;
	}

	// Property accessors

	public String getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
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

	public Reptype getReptype() {
		return this.reptype;
	}

	public void setReptype(Reptype reptype) {
		this.reptype = reptype;
	}

	public String getResvId() {
		return this.resvId;
	}

	public void setResvId(String resvId) {
		this.resvId = resvId;
	}

	public Integer getReceptionDistance() {
		return this.receptionDistance;
	}

	public void setReceptionDistance(Integer receptionDistance) {
		this.receptionDistance = receptionDistance;
	}

	public String getReceptionMaintFlg() {
		return this.receptionMaintFlg;
	}

	public void setReceptionMaintFlg(String receptionMaintFlg) {
		this.receptionMaintFlg = receptionMaintFlg;
	}

	public String getReceptionStatus() {
		return this.receptionStatus;
	}

	public void setReceptionStatus(String receptionStatus) {
		this.receptionStatus = receptionStatus;
	}

	public Short getRepgrpId() {
		return this.repgrpId;
	}

	public void setRepgrpId(Short repgrpId) {
		this.repgrpId = repgrpId;
	}

	public Short getRepwkId() {
		return this.repwkId;
	}

	public void setRepwkId(Short repwkId) {
		this.repwkId = repwkId;
	}

	public Date getReceptionEndTime() {
		return this.receptionEndTime;
	}

	public void setReceptionEndTime(Date receptionEndTime) {
		this.receptionEndTime = receptionEndTime;
	}

	public Short getReceptionTechnician() {
		return this.receptionTechnician;
	}

	public void setReceptionTechnician(Short receptionTechnician) {
		this.receptionTechnician = receptionTechnician;
	}

	public Short getReceptionInsurPer() {
		return this.receptionInsurPer;
	}

	public void setReceptionInsurPer(Short receptionInsurPer) {
		this.receptionInsurPer = receptionInsurPer;
	}

	public String getReceptionRepPer() {
		return this.receptionRepPer;
	}

	public void setReceptionRepPer(String receptionRepPer) {
		this.receptionRepPer = receptionRepPer;
	}

	public String getPropRepPer() {
		return this.propRepPer;
	}

	public void setPropRepPer(String propRepPer) {
		this.propRepPer = propRepPer;
	}

	public String getPropPhone() {
		return this.propPhone;
	}

	public void setPropPhone(String propPhone) {
		this.propPhone = propPhone;
	}

	public String getPropTel() {
		return this.propTel;
	}

	public void setPropTel(String propTel) {
		this.propTel = propTel;
	}

	public String getValuables() {
		return this.valuables;
	}

	public void setValuables(String valuables) {
		this.valuables = valuables;
	}

	public String getFuelSituation() {
		return this.fuelSituation;
	}

	public void setFuelSituation(String fuelSituation) {
		this.fuelSituation = fuelSituation;
	}

	public String getOldPieces() {
		return this.oldPieces;
	}

	public void setOldPieces(String oldPieces) {
		this.oldPieces = oldPieces;
	}

	public Short getFinComId() {
		return this.finComId;
	}

	public void setFinComId(Short finComId) {
		this.finComId = finComId;
	}

	public String getFinStatus() {
		return this.finStatus;
	}

	public void setFinStatus(String finStatus) {
		this.finStatus = finStatus;
	}

	public Date getExpDelCarTime() {
		return this.expDelCarTime;
	}

	public void setExpDelCarTime(Date expDelCarTime) {
		this.expDelCarTime = expDelCarTime;
	}

	public String getProblemDesc() {
		return this.problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public String getReceptionRemark() {
		return this.receptionRemark;
	}

	public void setReceptionRemark(String receptionRemark) {
		this.receptionRemark = receptionRemark;
	}

	public Date getInterDate() {
		return this.interDate;
	}

	public void setInterDate(Date interDate) {
		this.interDate = interDate;
	}

	public Short getReceptor() {
		return this.receptor;
	}

	public void setReceptor(Short receptor) {
		this.receptor = receptor;
	}

	public Double getManagementFee() {
		return this.managementFee;
	}

	public void setManagementFee(Double managementFee) {
		this.managementFee = managementFee;
	}

	public Set getFrtReceptionCosts() {
		return this.frtReceptionCosts;
	}

	public void setFrtReceptionCosts(Set frtReceptionCosts) {
		this.frtReceptionCosts = frtReceptionCosts;
	}

	public Set getFrtRcptPartses() {
		return this.frtRcptPartses;
	}

	public void setFrtRcptPartses(Set frtRcptPartses) {
		this.frtRcptPartses = frtRcptPartses;
	}

	public Set getFrtRcptItems() {
		return this.frtRcptItems;
	}

	public void setFrtRcptItems(Set frtRcptItems) {
		this.frtRcptItems = frtRcptItems;
	}


	public Short getCorrection() {
		return correction;
	}

	public void setCorrection(Short correction) {
		this.correction = correction;
	}

	public Short getRcptFlg() {
		return rcptFlg;
	}

	public void setRcptFlg(Short rcptFlg) {
		this.rcptFlg = rcptFlg;
	}

	public Short getFinTag() {
		return finTag;
	}

	public void setFinTag(Short finTag) {
		this.finTag = finTag;
	}

	public Short getFinAllTag() {
		return finAllTag;
	}

	public void setFinAllTag(Short finAllTag) {
		this.finAllTag = finAllTag;
	}

	public String getRcptBranch() {
		return rcptBranch;
	}

	public void setRcptBranch(String rcptBranch) {
		this.rcptBranch = rcptBranch;
	}

	public Date getReceptionFactTime() {
		return receptionFactTime;
	}

	public void setReceptionFactTime(Date receptionFactTime) {
		this.receptionFactTime = receptionFactTime;
	}

}