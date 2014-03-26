package com.syuesoft.model;

/**
 * BasRepairGroup entity. @author MyEclipse Persistence Tools
 */

public class BasRepairGroup extends BaseBean{

	// Fields

	private Short repgrpId;
	private String repgrpName;
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
	public BasRepairGroup() {
	}

	/** minimal constructor */
	public BasRepairGroup(Short repgrpId) {
		this.repgrpId = repgrpId;
	}

	/** full constructor */
	public BasRepairGroup(Short repgrpId, String repgrpName,String memo) {
		this.repgrpId = repgrpId;
		this.repgrpName = repgrpName;
		this.memo = memo;
	}

	// Property accessors

	
	public Short getRepgrpId() {
		return this.repgrpId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setRepgrpId(Short repgrpId) {
		this.repgrpId = repgrpId;
	}

	public String getRepgrpName() {
		return this.repgrpName;
	}

	public void setRepgrpName(String repgrpName) {
		this.repgrpName = repgrpName;
	}

}