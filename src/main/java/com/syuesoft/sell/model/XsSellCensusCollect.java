package com.syuesoft.sell.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * XsSellCensusCollect entity. @author MyEclipse Persistence Tools
 */

public class XsSellCensusCollect implements java.io.Serializable {

	// Fields

	private Integer collectId;
	private Date censusStartdate;
	private Date censusEnddate;
	private Date censusBackdate;
	private Integer censusSum;
	private String remark;
	private Integer enterpriseId;
	private Set xsSellCensuses = new HashSet(0);


	// Property accessors

	public Integer getCollectId() {
		return this.collectId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public Date getCensusStartdate() {
		return this.censusStartdate;
	}

	public void setCensusStartdate(Date censusStartdate) {
		this.censusStartdate = censusStartdate;
	}

	public Date getCensusEnddate() {
		return this.censusEnddate;
	}

	public void setCensusEnddate(Date censusEnddate) {
		this.censusEnddate = censusEnddate;
	}

	public Date getCensusBackdate() {
		return this.censusBackdate;
	}

	public void setCensusBackdate(Date censusBackdate) {
		this.censusBackdate = censusBackdate;
	}

	public Integer getCensusSum() {
		return this.censusSum;
	}

	public void setCensusSum(Integer censusSum) {
		this.censusSum = censusSum;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getXsSellCensuses() {
		return this.xsSellCensuses;
	}

	public void setXsSellCensuses(Set xsSellCensuses) {
		this.xsSellCensuses = xsSellCensuses;
	}

}