package com.syuesoft.model;

/**
 * BasWorkhourSort entity. @author MyEclipse Persistence Tools
 */

public class BasWorkhourSort extends BaseBean {

	// Fields

	private Short whSortId;
	private String whSortName;
	private String whMemo;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public BasWorkhourSort() {
	}

	/** full constructor */
	public BasWorkhourSort(String whSortName, String whMemo) {
		this.whSortName = whSortName;
		this.whMemo = whMemo;
	}

	// Property accessors

	public Short getWhSortId() {
		return this.whSortId;
	}

	public void setWhSortId(Short whSortId) {
		this.whSortId = whSortId;
	}

	public String getWhSortName() {
		return this.whSortName;
	}

	public void setWhSortName(String whSortName) {
		this.whSortName = whSortName;
	}

	public String getWhMemo() {
		return this.whMemo;
	}

	public void setWhMemo(String whMemo) {
		this.whMemo = whMemo;
	}

}