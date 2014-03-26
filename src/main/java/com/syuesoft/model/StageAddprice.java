package com.syuesoft.model;

/**
 * StageAddprice entity. @author MyEclipse Persistence Tools
 */
public class StageAddprice implements java.io.Serializable {

	// Fields

	private Short stageId;
	private Double startAmont;
	private Double endAmont;
	private Double repairRate;
	private Double sellRate;
	private Double pointRate;
	private Double specialRate;
	private Double claimRate;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	/** default constructor */
	public StageAddprice() {
	}

	/** full constructor */
	public StageAddprice(Double startAmont, Double endAmont, Double repairRate,
			Double sellRate, Double pointRate, Double specialRate, Double claimRate) {
		this.startAmont = startAmont;
		this.endAmont = endAmont;
		this.repairRate = repairRate;
		this.sellRate = sellRate;
		this.pointRate = pointRate;
		this.specialRate = specialRate;
		this.claimRate = claimRate;
	}

	public Short getStageId() {
		return stageId;
	}

	public void setStageId(Short stageId) {
		this.stageId = stageId;
	}

	public Double getStartAmont() {
		return this.startAmont;
	}

	public void setStartAmont(Double startAmont) {
		this.startAmont = startAmont;
	}

	public Double getEndAmont() {
		return this.endAmont;
	}

	public void setEndAmont(Double endAmont) {
		this.endAmont = endAmont;
	}

	public Double getRepairRate() {
		return this.repairRate;
	}

	public void setRepairRate(Double repairRate) {
		this.repairRate = repairRate;
	}

	public Double getSellRate() {
		return this.sellRate;
	}

	public void setSellRate(Double sellRate) {
		this.sellRate = sellRate;
	}

	public Double getPointRate() {
		return this.pointRate;
	}

	public void setPointRate(Double pointRate) {
		this.pointRate = pointRate;
	}

	public Double getSpecialRate() {
		return this.specialRate;
	}

	public void setSpecialRate(Double specialRate) {
		this.specialRate = specialRate;
	}

	public Double getClaimRate() {
		return claimRate;
	}

	public void setClaimRate(Double claimRate) {
		this.claimRate = claimRate;
	}

}