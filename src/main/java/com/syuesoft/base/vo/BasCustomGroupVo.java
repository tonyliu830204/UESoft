package com.syuesoft.base.vo;

public class BasCustomGroupVo
{
    private String cstgId;

    private String cstgName;

    private String memo;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getCstgId()
    {
        return cstgId;
    }

    public void setCstgId(String cstgId)
    {
        this.cstgId = cstgId;
    }

    public String getCstgName()
    {
        return cstgName;
    }

    public void setCstgName(String cstgName)
    {
        this.cstgName = cstgName;
    }

}
