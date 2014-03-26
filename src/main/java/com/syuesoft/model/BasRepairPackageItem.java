package com.syuesoft.model;

/**
 * BasRepairPackageItem entity. @author MyEclipse Persistence Tools
 */

public class BasRepairPackageItem extends BaseBean{

	// Fields

	private Short id;
	private BasRepairPackage basRepairPackage;
	private String repitemId;
	private String repitemName;
	private Double repitemNum;
	private Double repitemPrice;
	private Double repitemAmount;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public BasRepairPackageItem() {
	}

	/** full constructor */
	public BasRepairPackageItem(BasRepairPackage basRepairPackage, String repitemId, String repitemName, Double repitemNum, Double repitemPrice, Double repitemAmount) {
		this.basRepairPackage = basRepairPackage;
		this.repitemId = repitemId;
		this.repitemName = repitemName;
		this.repitemNum = repitemNum;
		this.repitemPrice = repitemPrice;
		this.repitemAmount = repitemAmount;
	}

	// Property accessors

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public BasRepairPackage getBasRepairPackage() {
		return this.basRepairPackage;
	}

	public void setBasRepairPackage(BasRepairPackage basRepairPackage) {
		this.basRepairPackage = basRepairPackage;
	}

	public String getRepitemId() {
		return this.repitemId;
	}

	public void setRepitemId(String repitemId) {
		this.repitemId = repitemId;
	}

	public String getRepitemName() {
		return this.repitemName;
	}

	public void setRepitemName(String repitemName) {
		this.repitemName = repitemName;
	}

	public Double getRepitemNum() {
		return this.repitemNum;
	}

	public void setRepitemNum(Double repitemNum) {
		this.repitemNum = repitemNum;
	}

	public Double getRepitemPrice() {
		return this.repitemPrice;
	}

	public void setRepitemPrice(Double repitemPrice) {
		this.repitemPrice = repitemPrice;
	}

	public Double getRepitemAmount() {
		return this.repitemAmount;
	}

	public void setRepitemAmount(Double repitemAmount) {
		this.repitemAmount = repitemAmount;
	}

}