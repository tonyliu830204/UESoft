package com.syuesoft.model;

/**
 * BasCompanyInformationSet entity. @author MyEclipse Persistence Tools
 */

public class BasCompanyInformationSet extends BaseBean{

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String ciId;
	private String ciLable;
	private String ciName;
	private String ciValue;
	private String ciRemark;
	private String ciInputControl;
	private String ciType;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	
	
	// Constructors

	/** default constructor */
	public BasCompanyInformationSet() {
	}

	/** minimal constructor */
	public BasCompanyInformationSet(String ciId) {
		this.ciId = ciId;
	}

	/** full constructor */
	public BasCompanyInformationSet(String ciId, String ciName, String ciValue,
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

	public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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