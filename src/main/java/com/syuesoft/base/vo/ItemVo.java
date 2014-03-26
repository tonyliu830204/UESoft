package com.syuesoft.base.vo;

import java.util.Date;

import com.syuesoft.model.FrtPreClearing;

public class ItemVo
{

    private String repitemId;

    private String repitemName;

    private Double repitemNum;

    private Double repitemTime;// 维修工时

    private Double internalTime;// 内部工时

    private Double repitemPrice;

    private Double repitemAmount;

    private String repitemRemark;

    private Short rpId;

    private Short stfId;

    private String stfName;

    private Short claimsId;

    private String claimsName;

    private Short reptypId;

    private String reptypName;

    private Double rcptitemInnerTime;

    private Date planStartTime;

    private Date planComplTime;

    private String receptionRemark;

    // 交车结算
    private FrtPreClearing frtPreClearing;

    private Double wktimeNum;

    private Double innerWktime;

    private Double wktimeAmount;

    private Double settlementDiscount;

    public ItemVo()
    {

    }

    public ItemVo(String repitemId, String repitemName, Double repitemNum,
            Double repitemPrice, Double repitemAmount)
    {
        this.repitemId = repitemId;
        this.repitemName = repitemName;
        this.repitemNum = repitemNum;
        this.repitemPrice = repitemPrice;
        this.repitemAmount = repitemAmount;
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

    public Double getRepitemNum()
    {
        return repitemNum;
    }

    public void setRepitemNum(Double repitemNum)
    {
        this.repitemNum = repitemNum;
    }

    public Double getRepitemAmount()
    {
        return repitemAmount;
    }

    public void setRepitemAmount(Double repitemAmount)
    {
        this.repitemAmount = repitemAmount;
    }

    public Short getRpId()
    {
        return rpId;
    }

    public void setRpId(Short rpId)
    {
        this.rpId = rpId;
    }

    public void setRpId(String rpId)
    {
        if (rpId != null && (!"".equals(rpId)))
            this.rpId = Short.parseShort(rpId);
    }

    public Double getRepitemPrice()
    {
        return repitemPrice;
    }

    public void setRepitemPrice(Double repitemPrice)
    {
        this.repitemPrice = repitemPrice;
    }

    public Short getStfId()
    {
        return stfId;
    }

    public void setStfId(Short stfId)
    {
        this.stfId = stfId;
    }

    public void setStfId(String stfId)
    {
        if (stfId != null && (!"".equals(stfId)))
            this.stfId = Short.parseShort(stfId);
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public Short getClaimsId()
    {
        return claimsId;
    }

    public void setClaimsId(Short claimsId)
    {
        this.claimsId = claimsId;
    }

    public void setClaimsId(String claimsId)
    {
        if (claimsId != null && (!"".equals(claimsId)))
            this.claimsId = Short.parseShort(claimsId);
    }

    public String getClaimsName()
    {
        return claimsName;
    }

    public void setClaimsName(String claimsName)
    {
        this.claimsName = claimsName;
    }

    public Short getReptypId()
    {
        return reptypId;
    }

    public void setReptypId(Short reptypId)
    {
        this.reptypId = reptypId;
    }

    public void setReptypId(String reptypId)
    {
        if (reptypId != null && (!"".equals(reptypId)))
            this.reptypId = Short.parseShort(reptypId);
    }

    public String getReptypName()
    {
        return reptypName;
    }

    public void setReptypName(String reptypName)
    {
        this.reptypName = reptypName;
    }

    public Double getRcptitemInnerTime()
    {
        return rcptitemInnerTime;
    }

    public void setRcptitemInnerTime(Double rcptitemInnerTime)
    {
        this.rcptitemInnerTime = rcptitemInnerTime;
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

    public FrtPreClearing getFrtPreClearing()
    {
        return frtPreClearing;
    }

    public void setFrtPreClearing(FrtPreClearing frtPreClearing)
    {
        this.frtPreClearing = frtPreClearing;
    }

    public Double getWktimeNum()
    {
        return wktimeNum;
    }

    public void setWktimeNum(Double wktimeNum)
    {
        this.wktimeNum = wktimeNum;
    }

    public Double getInnerWktime()
    {
        return innerWktime;
    }

    public void setInnerWktime(Double innerWktime)
    {
        this.innerWktime = innerWktime;
    }

    public Double getWktimeAmount()
    {
        return wktimeAmount;
    }

    public void setWktimeAmount(Double wktimeAmount)
    {
        this.wktimeAmount = wktimeAmount;
    }

    public Double getSettlementDiscount()
    {
        return settlementDiscount;
    }

    public void setSettlementDiscount(Double settlementDiscount)
    {
        this.settlementDiscount = settlementDiscount;
    }

    public Double getInternalTime()
    {
        return internalTime;
    }

    public void setInternalTime(Double internalTime)
    {
        this.internalTime = internalTime;
    }

    public Double getRepitemTime()
    {
        return repitemTime;
    }

    public void setRepitemTime(Double repitemTime)
    {
        this.repitemTime = repitemTime;
    }

    public String getRepitemRemark()
    {
        return repitemRemark;
    }

    public void setRepitemRemark(String repitemRemark)
    {
        this.repitemRemark = repitemRemark;
    }

}
