package com.syuesoft.base.vo;

public class BasCompanyInformationSetVo extends BaseBeanVo
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
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
    
    private String ciPutType;

    private String[] ciCiIds;

    private String[] ciNames;

    private String[] ciValues;

    private String[] ciCheckCiIds;

    private String[] ciCheckNames;

    private String[] ciCheckValues;
    
    private String[] ciPutTypes;
    
    
    private String softType;
    
    private String employee;
    
    private String storeHouse;
    private Integer enterpriseId;
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    public String getCiId()
    {
        return ciId;
    }

    public void setCiId(String ciId)
    {
        this.ciId = ciId;
    }

    public String getCiLable()
    {
        return ciLable;
    }

    public void setCiLable(String ciLable)
    {
        this.ciLable = ciLable;
    }

    public String getCiName()
    {
        return ciName;
    }

    public void setCiName(String ciName)
    {
        this.ciName = ciName;
    }

    public String getCiValue()
    {
        return ciValue;
    }

    public void setCiValue(String ciValue)
    {
        this.ciValue = ciValue;
    }

    public String getCiRemark()
    {
        return ciRemark;
    }

    public void setCiRemark(String ciRemark)
    {
        this.ciRemark = ciRemark;
    }

    public String getCiInputControl()
    {
        return ciInputControl;
    }

    public void setCiInputControl(String ciInputControl)
    {
        this.ciInputControl = ciInputControl;
    }

    public String getCiType()
    {
        return ciType;
    }

    public void setCiType(String ciType)
    {
        this.ciType = ciType;
    }

    public String[] getCiCiIds()
    {
        return ciCiIds;
    }

    public void setCiCiIds(String[] ciCiIds)
    {
        this.ciCiIds = ciCiIds;
    }

    public String[] getCiNames()
    {
        return ciNames;
    }

    public void setCiNames(String[] ciNames)
    {
        this.ciNames = ciNames;
    }

    public String[] getCiValues()
    {
        return ciValues;
    }

    public void setCiValues(String[] ciValues)
    {
        this.ciValues = ciValues;
    }

    public String[] getCiCheckCiIds()
    {
        return ciCheckCiIds;
    }

    public void setCiCheckCiIds(String[] ciCheckCiIds)
    {
        this.ciCheckCiIds = ciCheckCiIds;
    }

    public String[] getCiCheckNames()
    {
        return ciCheckNames;
    }

    public void setCiCheckNames(String[] ciCheckNames)
    {
        this.ciCheckNames = ciCheckNames;
    }

    public String[] getCiCheckValues()
    {
        return ciCheckValues;
    }

    public void setCiCheckValues(String[] ciCheckValues)
    {
        this.ciCheckValues = ciCheckValues;
    }

    public String getSoftType()
    {
        return softType;
    }

    public void setSoftType(String softType)
    {
        this.softType = softType;
    }

    public String getEmployee()
    {
        return employee;
    }

    public void setEmployee(String employee)
    {
        this.employee = employee;
    }

    public String getStoreHouse()
    {
        return storeHouse;
    }

    public void setStoreHouse(String storeHouse)
    {
        this.storeHouse = storeHouse;
    }

    public String getCiPutType()
    {
        return ciPutType;
    }

    public void setCiPutType(String ciPutType)
    {
        this.ciPutType = ciPutType;
    }

    public String[] getCiPutTypes()
    {
        return ciPutTypes;
    }

    public void setCiPutTypes(String[] ciPutTypes)
    {
        this.ciPutTypes = ciPutTypes;
    }
}