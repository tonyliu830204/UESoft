package com.syuesoft.base.vo;

/**
 * BasCustomerType entity. @author MyEclipse Persistence Tools
 */

public class BasCustomTypeVo implements java.io.Serializable
{

    // Fields

    private Short cstId;

    private String cstName;

    private String memo;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public Short getCstId()
    {
        return cstId;
    }

    public void setCstId(Short cstId)
    {
        this.cstId = cstId;
    }

    public String getCstName()
    {
        return cstName;
    }

    public void setCstName(String cstName)
    {
        this.cstName = cstName;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

}