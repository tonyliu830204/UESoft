package com.syuesoft.model;

/**
 * BasClaimsType entity. @author MyEclipse Persistence Tools
 */


public class BasClaimsType extends BaseBean{

	// Fields

	private Short claimsId;
	private String claimsName;
	private Short claimsFlg;
	private Short claimsToMoney;
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
	public BasClaimsType() {
	}

	/** minimal constructor */
	public BasClaimsType(Short claimsId) {
		this.claimsId = claimsId;
	}

	/** full constructor */
	public BasClaimsType(Short claimsId, String claimsName,String memo) {
		this.claimsId = claimsId;
		this.claimsName = claimsName;
		this.memo = memo;
	}

	// Property accessors

	
	public Short getClaimsId() {
		return this.claimsId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setClaimsId(Short claimsId) {
		this.claimsId = claimsId;
	}

	public String getClaimsName() {
		return this.claimsName;
	}

	public void setClaimsName(String claimsName) {
		this.claimsName = claimsName;
	}

	public Short getClaimsFlg() {
		return claimsFlg;
	}

	public void setClaimsFlg(Short claimsFlg) {
		this.claimsFlg = claimsFlg;
	}

	public Short getClaimsToMoney() {
		return claimsToMoney;
	}

	public void setClaimsToMoney(Short claimsToMoney) {
		this.claimsToMoney = claimsToMoney;
	}

}