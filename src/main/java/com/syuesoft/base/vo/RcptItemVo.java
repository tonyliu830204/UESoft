package com.syuesoft.base.vo;

import java.util.Date;

public class RcptItemVo implements java.io.Serializable
{
    private Integer rcptitemIndex;

    // private BasClaimsType basClaimsType;
    // private FrtReception frtReception;
    // private BasRepairType basRepairType;
    private String repitemId;

    private String repitemName;

    private Integer repitemNum;

    private Double rcptitemInnerTime;

    private Double repitemAmout;

    private Short stfId;

    private Date planStartTime;

    private Date planComplTime;

    private String receptionRemark;

    private Short claimsId;

    private String claimsName;

    private String memo;

    private Short reptypId;

    private String reptypName;

    private String rmemo;

    public RcptItemVo()
    {

    }

    public Integer getRcptitemIndex()
    {
        return rcptitemIndex;
    }

    public void setRcptitemIndex(Integer rcptitemIndex)
    {
        this.rcptitemIndex = rcptitemIndex;
    }

    public String getRepitemId()
    {
        return repitemId;
    }

    public void setRepitemId(String repitemId)
    {
        this.repitemId = repitemId;
    }

    public String getRepitemName()
    {
        return repitemName;
    }

    public void setRepitemName(String repitemName)
    {
        this.repitemName = repitemName;
    }

    public Integer getRepitemNum()
    {
        return repitemNum;
    }

    public void setRepitemNum(Integer repitemNum)
    {
        this.repitemNum = repitemNum;
    }

    public Double getRcptitemInnerTime()
    {
        return rcptitemInnerTime;
    }

    public void setRcptitemInnerTime(Double rcptitemInnerTime)
    {
        this.rcptitemInnerTime = rcptitemInnerTime;
    }

    public Double getRepitemAmout()
    {
        return repitemAmout;
    }

    public void setRepitemAmout(Double repitemAmout)
    {
        this.repitemAmout = repitemAmout;
    }

    public Short getStfId()
    {
        return stfId;
    }

    public void setStfId(Short stfId)
    {
        this.stfId = stfId;
    }

    public Date getPlanStartTime()
    {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime)
    {
        this.planStartTime = planStartTime;
    }

    public Date getPlanComplTime()
    {
        return planComplTime;
    }

    public void setPlanComplTime(Date planComplTime)
    {
        this.planComplTime = planComplTime;
    }

    public String getReceptionRemark()
    {
        return receptionRemark;
    }

    public void setReceptionRemark(String receptionRemark)
    {
        this.receptionRemark = receptionRemark;
    }

    public Short getClaimsId()
    {
        return claimsId;
    }

    public void setClaimsId(Short claimsId)
    {
        this.claimsId = claimsId;
    }

    public String getClaimsName()
    {
        return claimsName;
    }

    public void setClaimsName(String claimsName)
    {
        this.claimsName = claimsName;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public Short getReptypId()
    {
        return reptypId;
    }

    public void setReptypId(Short reptypId)
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

    public String getRmemo()
    {
        return rmemo;
    }

    public void setRmemo(String rmemo)
    {
        this.rmemo = rmemo;
    }

}
