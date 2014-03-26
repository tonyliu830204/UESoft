package com.syuesoft.sell.model;

import java.sql.Timestamp;

/**
 * XsSellCensus entity. @author MyEclipse Persistence Tools
 */

public class XsSellCensus implements java.io.Serializable {

	// Fields

	private Integer censusId;
	private XsSellCensusCollect xsSellCensusCollect;
	private String remark;
	private Integer censusSum;
	private Integer xsCarId;
	

	// Constructors

	/** default constructor */
	public XsSellCensus() {
	}


	/** full constructor */
	public XsSellCensus(XsSellCensusCollect xsSellCensusCollect,
			 String remark, Integer censusSum,
			Integer xsCarId) {
		this.xsSellCensusCollect = xsSellCensusCollect;
		this.remark = remark;
		this.censusSum = censusSum;
		this.xsCarId = xsCarId;
	}

	// Property accessors

	public Integer getCensusId() {
		return this.censusId;
	}

	public void setCensusId(Integer censusId) {
		this.censusId = censusId;
	}

	public XsSellCensusCollect getXsSellCensusCollect() {
		return this.xsSellCensusCollect;
	}

	public void setXsSellCensusCollect(XsSellCensusCollect xsSellCensusCollect) {
		this.xsSellCensusCollect = xsSellCensusCollect;
	}

	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCensusSum() {
		return this.censusSum;
	}

	public void setCensusSum(Integer censusSum) {
		this.censusSum = censusSum;
	}

	public Integer getXsCarId() {
		return this.xsCarId;
	}

	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}


}