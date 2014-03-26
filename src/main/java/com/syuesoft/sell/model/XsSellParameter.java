package com.syuesoft.sell.model;

import com.syuesoft.model.BaseBean;

/**
 * BasCompanyInformationSet entity. @author MyEclipse Persistence Tools
 */

public class XsSellParameter extends BaseBean {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String ciId;
	private String ciLable;
	private String ciName;
	private String ciValue;
	private String ciRemark;
	private String ciInputControl;
	private String ciType;
	private Integer enterprise_id;
    
	public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	// Constructors

	/** default constructor */
	public XsSellParameter() {
	}

	/** minimal constructor */
	public XsSellParameter(String ciId) {
		this.ciId = ciId;
	}

	/** full constructor */
	public XsSellParameter(String ciId, String ciName, String ciValue,
			String ciRemark, String ciInputControl, String ciType) {
		this.ciId = ciId;
		this.ciName = ciName;
		this.ciValue = ciValue;
		this.ciRemark = ciRemark;
		this.ciInputControl = ciInputControl;
		this.ciType = ciType;
	}

	// Property accessors

	public String getCiId() {
		return this.ciId;
	}

	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	public String getCiName() {
		return this.ciName;
	}

	public void setCiName(String ciName) {
		this.ciName = ciName;
	}

	public String getCiValue() {
		return this.ciValue;
	}

	public void setCiValue(String ciValue) {
		this.ciValue = ciValue;
	}

	public String getCiRemark() {
		return this.ciRemark;
	}

	public void setCiRemark(String ciRemark) {
		this.ciRemark = ciRemark;
	}

	public String getCiInputControl() {
		return this.ciInputControl;
	}

	public void setCiInputControl(String ciInputControl) {
		this.ciInputControl = ciInputControl;
	}

	public String getCiType() {
		return this.ciType;
	}

	public void setCiType(String ciType) {
		this.ciType = ciType;
	}

	public String getCiLable() {
		return ciLable;
	}

	public void setCiLable(String ciLable) {
		this.ciLable = ciLable;
	}
}