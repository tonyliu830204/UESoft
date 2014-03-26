package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasRepairPackage entity. @author MyEclipse Persistence Tools
 */

public class BasRepairPackage extends BaseBean{

	// Fields

	private Short rpId;
	private String rpName;
	private Double itemTimeAmount;
	private Double partsAmount;
	private Double rpAmount;
	private String applicableBrands;
	private Set basRepairPackagePartses = new HashSet(0);
	private Set basRepairPackageItems = new HashSet(0);
	private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasRepairPackage() {
	}

	/** full constructor */
	public BasRepairPackage(String rpName, Double itemTimeAmount, Double partsAmount, Double rpAmount, String applicableBrands, Set basRepairPackagePartses, Set basRepairPackageItems) {
		this.rpName = rpName;
		this.itemTimeAmount = itemTimeAmount;
		this.partsAmount = partsAmount;
		this.rpAmount = rpAmount;
		this.applicableBrands = applicableBrands;
		this.basRepairPackagePartses = basRepairPackagePartses;
		this.basRepairPackageItems = basRepairPackageItems;
	}

	// Property accessors

	public Short getRpId() {
		return this.rpId;
	}

	public void setRpId(Short rpId) {
		this.rpId = rpId;
	}

	public String getRpName() {
		return this.rpName;
	}

	public void setRpName(String rpName) {
		this.rpName = rpName;
	}

	public Double getItemTimeAmount() {
		return this.itemTimeAmount;
	}

	public void setItemTimeAmount(Double itemTimeAmount) {
		this.itemTimeAmount = itemTimeAmount;
	}

	public Double getPartsAmount() {
		return this.partsAmount;
	}

	public void setPartsAmount(Double partsAmount) {
		this.partsAmount = partsAmount;
	}

	public Double getRpAmount() {
		return this.rpAmount;
	}

	public void setRpAmount(Double rpAmount) {
		this.rpAmount = rpAmount;
	}

	public String getApplicableBrands() {
		return this.applicableBrands;
	}

	public void setApplicableBrands(String applicableBrands) {
		this.applicableBrands = applicableBrands;
	}

	public Set getBasRepairPackagePartses() {
		return this.basRepairPackagePartses;
	}

	public void setBasRepairPackagePartses(Set basRepairPackagePartses) {
		this.basRepairPackagePartses = basRepairPackagePartses;
	}

	public Set getBasRepairPackageItems() {
		return this.basRepairPackageItems;
	}

	public void setBasRepairPackageItems(Set basRepairPackageItems) {
		this.basRepairPackageItems = basRepairPackageItems;
	}

}