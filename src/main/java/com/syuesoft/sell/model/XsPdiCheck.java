package com.syuesoft.sell.model;

import java.util.Date;

/**
 * XsPdiCheck entity. @author MyEclipse Persistence Tools
 */

public class XsPdiCheck implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer xsCarSelId;
	private Integer xsCarId;
	private Date checkDate;
	private Integer checkPerson;
	private Integer checkComtent;
	private Integer status;
	private String remark;

	// Constructors

	/** default constructor */
	public XsPdiCheck() {
	}

	/** full constructor */
	public XsPdiCheck(XsCarSellInfo xsCarSellInfo, Integer xsCarId,
			Date checkDate, Integer checkPerson, Integer checkComtent,
			Integer status, String remark) {
		this.xsCarSelId = xsCarSelId;
		this.xsCarId = xsCarId;
		this.checkDate = checkDate;
		this.checkPerson = checkPerson;
		this.checkComtent = checkComtent;
		this.status = status;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getXsCarSelId() {
		return xsCarSelId;
	}

	public void setXsCarSelId(Integer xsCarSelId) {
		this.xsCarSelId = xsCarSelId;
	}

	public Integer getXsCarId() {
		return this.xsCarId;
	}

	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getCheckPerson() {
		return this.checkPerson;
	}

	public void setCheckPerson(Integer checkPerson) {
		this.checkPerson = checkPerson;
	}

	public Integer getCheckComtent() {
		return this.checkComtent;
	}

	public void setCheckComtent(Integer checkComtent) {
		this.checkComtent = checkComtent;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}