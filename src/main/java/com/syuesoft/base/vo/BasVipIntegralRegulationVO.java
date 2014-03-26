package com.syuesoft.base.vo;

import java.io.Serializable;

public class BasVipIntegralRegulationVO implements Serializable
{

    private Integer vipInteReg; // 会员积分规则编号

    private Integer vipLevelId; // 会员等级编号

    private String vipLevelName; // 会员等级名称

    private Integer reptypId; // 维修分类编号

    private String reptypName; // 维修分类名称

    private double beginAmount; // 起始金额

    private Integer jfAmont; // 积分金额

    private Integer score; // 积分数
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public BasVipIntegralRegulationVO()
    {

    }

    public BasVipIntegralRegulationVO(Integer vipInteReg, Integer vipLevelId,
            String vipLevelName, Integer reptypId, String reptypName,
            double beginAmount, Integer jfAmont, Integer score)
    {
        this.vipInteReg = vipInteReg;
        this.vipLevelId = vipLevelId;
        this.vipLevelName = vipLevelName;
        this.reptypId = reptypId;
        this.reptypName = reptypName;
        this.beginAmount = beginAmount;
        this.jfAmont = jfAmont;
        this.score = score;
        this.jfAmont = jfAmont;
        this.score = score;
    }

    public Integer getVipInteReg()
    {
        return vipInteReg;
    }

    public void setVipInteReg(Integer vipInteReg)
    {
        this.vipInteReg = vipInteReg;
    }

    public Integer getVipLevelId()
    {
        return vipLevelId;
    }

    public void setVipLevelId(Integer vipLevelId)
    {
        this.vipLevelId = vipLevelId;
    }

    public String getVipLevelName()
    {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName)
    {
        this.vipLevelName = vipLevelName;
    }

    public Integer getReptypId()
    {
        return reptypId;
    }

    public void setReptypId(Integer reptypId)
    {
        this.reptypId = reptypId;
    }

    public String getReptypName()
    {
        return reptypName;
    }

    public void setReptypName(String reptypName)
    {
        this.reptypName = reptypName;
    }

    public double getBeginAmount()
    {
        return beginAmount;
    }

    public void setBeginAmount(double beginAmount)
    {
        this.beginAmount = beginAmount;
    }

    public Integer getJfAmont()
    {
        return jfAmont;
    }

    public void setJfAmont(Integer jfAmont)
    {
        this.jfAmont = jfAmont;
    }

    public Integer getScore()
    {
        return score;
    }

    public void setScore(Integer score)
    {
        this.score = score;
    }

}
