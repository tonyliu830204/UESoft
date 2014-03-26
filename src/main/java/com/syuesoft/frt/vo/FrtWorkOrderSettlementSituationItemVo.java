package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FrtWorkOrderSettlementSituationItemVo extends BaseBeanVo
{
    private String repitemId;

    private String repitemName;

    private Double wktimeNum;

    private String stfName;

    private String claimsName;

    private String receptionRemark;

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

    public Double getWktimeNum()
    {
        return wktimeNum;
    }

    public void setWktimeNum(Double wktimeNum)
    {
        this.wktimeNum = wktimeNum;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public String getClaimsName()
    {
        return claimsName;
    }

    public void setClaimsName(String claimsName)
    {
        this.claimsName = claimsName;
    }

    public String getReceptionRemark()
    {
        return receptionRemark;
    }

    public void setReceptionRemark(String receptionRemark)
    {
        this.receptionRemark = receptionRemark;
    }

}
