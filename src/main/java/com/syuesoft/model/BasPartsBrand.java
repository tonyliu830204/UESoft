package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasPartsBrand entity. @author MyEclipse Persistence Tools
 */

public class BasPartsBrand extends BaseBean{

	// Fields

	private Short pbrdId;
	private String pbrdName;
	private Double repairRate;
	private Double sellRate;
	private Double pointRate;
	private Double specialRate;
	private Set basPartsTypes = new HashSet(0);
	private String remark;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
	public BasPartsBrand() {
	}

	/** minimal constructor */
	public BasPartsBrand(Short pbrdId) {
		this.pbrdId = pbrdId;
	}

	/** full constructor */
	public BasPartsBrand(Short pbrdId, String pbrdName, Double repairRate,
			Double sellRate, Double pointRate, Double specialRate,
			Set basPartsTypes) {
		this.pbrdId = pbrdId;
		this.pbrdName = pbrdName;
		this.repairRate = repairRate;
		this.sellRate = sellRate;
		this.pointRate = pointRate;
		this.specialRate = specialRate;
		this.basPartsTypes = basPartsTypes;
	}

	// Property accessors

	public Short getPbrdId() {
		return this.pbrdId;
	}

	public void setPbrdId(Short pbrdId) {
		this.pbrdId = pbrdId;
	}

	public String getPbrdName() {
		return this.pbrdName;
	}

	public void setPbrdName(String pbrdName) {
		this.pbrdName = pbrdName;
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

	public Set getBasPartsTypes() {
		return this.basPartsTypes;
	}

	public void setBasPartsTypes(Set basPartsTypes) {
		this.basPartsTypes = basPartsTypes;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}