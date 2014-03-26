package com.syuesoft.model;

/**
 * FrtResvItem entity. @author MyEclipse Persistence Tools
 */

public class FrtResvItem extends BaseBean {

	// Fields

	private Integer resvIndex;
	private FrtResevation frtResevation;
	private String repitemId;
	private String repitemName;
	private Double repitemNum;
	private Double repitemInnerNum;
	private Double repitemAmount;
	private Short stfId;
	private Short reptitemFlg;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }

	// Constructors

	/** default constructor */
	public FrtResvItem() {
	}

	/** minimal constructor */
	public FrtResvItem(Integer resvIndex, Short stfId) {
		this.resvIndex = resvIndex;
		this.stfId = stfId;
	}

	/** full constructor */
	public FrtResvItem(Integer resvIndex, FrtResevation frtResevation, String repitemId, String repitemName, Double repitemNum, Double repitemAmount, Short stfId) {
		this.resvIndex = resvIndex;
		this.frtResevation = frtResevation;
		this.repitemId = repitemId;
		this.repitemName = repitemName;
		this.repitemNum = repitemNum;
		this.repitemAmount = repitemAmount;
		this.stfId = stfId;
	}

	// Property accessors

	public Integer getResvIndex() {
		return this.resvIndex;
	}

	public void setResvIndex(Integer resvIndex) {
		this.resvIndex = resvIndex;
	}

	public FrtResevation getFrtResevation() {
		return this.frtResevation;
	}

	public void setFrtResevation(FrtResevation frtResevation) {
		this.frtResevation = frtResevation;
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

	public Double getRepitemAmount() {
		return this.repitemAmount;
	}

	public void setRepitemAmount(Double repitemAmount) {
		this.repitemAmount = repitemAmount;
	}

	public Short getStfId() {
		return this.stfId;
	}

	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}

	public Short getReptitemFlg() {
		return reptitemFlg;
	}

	public void setReptitemFlg(Short reptitemFlg) {
		this.reptitemFlg = reptitemFlg;
	}

	public Double getRepitemInnerNum() {
		return repitemInnerNum;
	}

	public void setRepitemInnerNum(Double repitemInnerNum) {
		this.repitemInnerNum = repitemInnerNum;
	}

}