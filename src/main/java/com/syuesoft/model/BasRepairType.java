package com.syuesoft.model;

/**
 * BasRepairType entity. @author MyEclipse Persistence Tools
 */

public class BasRepairType extends BaseBean
{

    // Fields

    private Short reptypId;

    private String reptypName;

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
    public BasRepairType()
    {
    }

    /** minimal constructor */
    public BasRepairType(Short reptypId)
    {
        this.reptypId = reptypId;
    }

    /** full constructor */
    public BasRepairType(Short reptypId, String reptypName)
    {
        this.reptypId = reptypId;
        this.reptypName = reptypName;
    }

    // Property accessors

    public Short getReptypId()
    {
        return this.reptypId;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public void setReptypId(Short reptypId)
    {
        this.reptypId = reptypId;
    }

    public String getReptypName()
    {
        return this.reptypName;
    }

    public void setReptypName(String reptypName)
    {
        this.reptypName = reptypName;
    }

}