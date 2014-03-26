package com.syuesoft.model;

/**
 * BasRepairWork entity. @author MyEclipse Persistence Tools
 */

public class BasRepairWork extends BaseBean{

	// Fields

	private Short repwkId;
	private String repwkName;
	private String memo;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public BasRepairWork() {
	}

	/** minimal constructor */
	public BasRepairWork(Short repwkId) {
		this.repwkId = repwkId;
	}

	/** full constructor */
	public BasRepairWork(Short repwkId, String repwkName,String memo) {
		this.repwkId = repwkId;
		this.repwkName = repwkName;
		this.memo = memo;
	}

	// Property accessors

	
	public Short getRepwkId() {
		return this.repwkId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setRepwkId(Short repwkId) {
		this.repwkId = repwkId;
	}

	public String getRepwkName() {
		return this.repwkName;
	}

	public void setRepwkName(String repwkName) {
		this.repwkName = repwkName;
	}

}