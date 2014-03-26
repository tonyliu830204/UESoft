package com.syuesoft.base.vo;

import java.io.Serializable;

public class BasVipGiveIntegralProjectVO implements Serializable
{
    // 赠送积分项目编号
    private Integer givInteProId;

    // 赠送积分项目名称
    private String givInteProName;

    // 赠送积分数
    private Double givInteNum;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public BasVipGiveIntegralProjectVO()
    {
    }

    public BasVipGiveIntegralProjectVO(Integer givInteProId,
            String givInteProName, Double givInteNum,Integer enterpriseId)
    {
        this.givInteProId = givInteProId;
        this.givInteProName = givInteProName;
        this.givInteNum = givInteNum;
        this.enterpriseId=enterpriseId;
    }

    public Integer getGivInteProId()
    {
        return givInteProId;
    }

    public void setGivInteProId(Integer givInteProId)
    {
        this.givInteProId = givInteProId;
    }

    public String getGivInteProName()
    {
        return givInteProName;
    }

    public void setGivInteProName(String givInteProName)
    {
        this.givInteProName = givInteProName;
    }

    public Double getGivInteNum()
    {
        return givInteNum;
    }

    public void setGivInteNum(Double givInteNum)
    {
        this.givInteNum = givInteNum;
    }
}
