package com.syuesoft.base.vo;

import java.util.Set;

public class ReptypeVo
{
    private String reptId;

    private String reptName;

    private String workCreditsRate;

    private String partCreditsRate;

    private String sumCreditsRate;

    private Short reptClass;

    private String memo;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
    public ReptypeVo()
    {
    }

    public ReptypeVo(String reptName, String workCreditsRate,
            String partCreditsRate, String sumCreditsRate, String memo)
    {
        this.reptName = reptName;
        this.workCreditsRate = workCreditsRate;
        this.partCreditsRate = partCreditsRate;
        this.sumCreditsRate = sumCreditsRate;
        this.memo = memo;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getReptId()
    {
        return reptId;
    }

    public void setReptId(String reptId)
    {
        this.reptId = reptId;
    }

    public String getReptName()
    {
        return reptName;
    }

    public void setReptName(String reptName)
    {
        this.reptName = reptName;
    }

    public String getWorkCreditsRate()
    {
        return workCreditsRate;
    }

    public void setWorkCreditsRate(String workCreditsRate)
    {
        this.workCreditsRate = workCreditsRate;
    }

    public String getPartCreditsRate()
    {
        return partCreditsRate;
    }

    public void setPartCreditsRate(String partCreditsRate)
    {
        this.partCreditsRate = partCreditsRate;
    }

    public String getSumCreditsRate()
    {
        return sumCreditsRate;
    }

    public void setSumCreditsRate(String sumCreditsRate)
    {
        this.sumCreditsRate = sumCreditsRate;
    }

    public Short getReptClass()
    {
        return reptClass;
    }

    public void setReptClass(Short reptClass)
    {
        this.reptClass = reptClass;
    }

}
