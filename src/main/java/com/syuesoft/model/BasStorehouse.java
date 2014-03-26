package com.syuesoft.model;

/**
 * BasStorehouse entity. @author MyEclipse Persistence Tools
 */

public class BasStorehouse extends BaseBean{

	// Fields

	private Short storeId;
	private String storeName;
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
	public BasStorehouse() {
	}

	/** minimal constructor */
	public BasStorehouse(Short storeId) {
		this.storeId = storeId;
	}

	/** full constructor */
	public BasStorehouse(Short storeId, String storeName, String remark) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.remark = remark;
	}

	// Property accessors

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}