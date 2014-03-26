package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasPartsType entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BasPartsType extends BaseBean{

	// Fields

	private Short ptypeId;
	private BasPartsBrand basPartsBrand;
	private String ptypeName;
	private Double repairRate;
	private Double sellRate;
	private Double pointRate;
	private Double specialRate;
	private Double claimRate;
	private Set frtPartses = new HashSet(0);
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
	public BasPartsType() {
	}

	/** minimal constructor */
	public BasPartsType(Short ptypeId) {
		this.ptypeId = ptypeId;
	}

	/** full constructor */
	public BasPartsType(Short ptypeId, BasPartsBrand basPartsBrand,
			String ptypeName, Double repairRate, Double sellRate,
			Double pointRate, Double specialRate, Set frtPartses) {
		this.ptypeId = ptypeId;
		this.basPartsBrand = basPartsBrand;
		this.ptypeName = ptypeName;
		this.repairRate = repairRate;
		this.sellRate = sellRate;
		this.pointRate = pointRate;
		this.specialRate = specialRate;
		this.frtPartses = frtPartses;
	}

	public Short getPtypeId() {
		return this.ptypeId;
	}

	public void setPtypeId(Short ptypeId) {
		this.ptypeId = ptypeId;
	}

	public BasPartsBrand getBasPartsBrand() {
		return this.basPartsBrand;
	}

	public void setBasPartsBrand(BasPartsBrand basPartsBrand) {
		this.basPartsBrand = basPartsBrand;
	}

	public String getPtypeName() {
		return this.ptypeName;
	}

	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
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

	public Set getFrtPartses() {
		return this.frtPartses;
	}

	public void setFrtPartses(Set frtPartses) {
		this.frtPartses = frtPartses;
	}

	public Double getClaimRate() {
		return claimRate;
	}

	public void setClaimRate(Double claimRate) {
		this.claimRate = claimRate;
	}

}