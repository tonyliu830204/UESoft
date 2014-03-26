package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

/**
 * 工单综合查询->工单-维修配件
 * 
 * @author Liujian
 * 
 */
public class FrtRcptPartsTreeGridVo extends BaseBeanVo
{

    private Integer rcptpartsIndex;

    private String partsId;

    private String partsName;

    private Integer rcptpartsCnt;

    private Short punitId;

    private Double rcptpartsPrice;

    private Double rcptpartsAmount;

    private Short charge;

    private Short claimsType;

    private String partsRemark;

    private String receptionId;

    private String id;

    private String state;

    private String iconCls;

    private String _parentId;

    private String propRepPer;

    private String propPhone;// 托修人手机

    private String propTel;// 托修人电话

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getRcptpartsIndex()
    {
        return rcptpartsIndex;
    }

    public void setRcptpartsIndex(Integer rcptpartsIndex)
    {
        this.rcptpartsIndex = rcptpartsIndex;
    }

    public String getPartsId()
    {
        return partsId;
    }

    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public Integer getRcptpartsCnt()
    {
        return rcptpartsCnt;
    }

    public void setRcptpartsCnt(Integer rcptpartsCnt)
    {
        this.rcptpartsCnt = rcptpartsCnt;
    }

    public Short getPunitId()
    {
        return punitId;
    }

    public void setPunitId(Short punitId)
    {
        this.punitId = punitId;
    }

    public Double getRcptpartsPrice()
    {
        return rcptpartsPrice;
    }

    public void setRcptpartsPrice(Double rcptpartsPrice)
    {
        this.rcptpartsPrice = rcptpartsPrice;
    }

    public Double getRcptpartsAmount()
    {
        return rcptpartsAmount;
    }

    public void setRcptpartsAmount(Double rcptpartsAmount)
    {
        this.rcptpartsAmount = rcptpartsAmount;
    }

    public Short getCharge()
    {
        return charge;
    }

    public void setCharge(Short charge)
    {
        this.charge = charge;
    }

    public Short getClaimsType()
    {
        return claimsType;
    }

    public void setClaimsType(Short claimsType)
    {
        this.claimsType = claimsType;
    }

    public String getPartsRemark()
    {
        return partsRemark;
    }

    public void setPartsRemark(String partsRemark)
    {
        this.partsRemark = partsRemark;
    }

    public String getReceptionId()
    {
        return receptionId;
    }

    public void setReceptionId(String receptionId)
    {
        this.receptionId = receptionId;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String get_parentId()
    {
        return _parentId;
    }

    public void set_parentId(String parentId)
    {
        _parentId = parentId;
    }

    public String getPropRepPer()
    {
        return propRepPer;
    }

    public void setPropRepPer(String propRepPer)
    {
        this.propRepPer = propRepPer;
    }

    public String getPropPhone()
    {
        return propPhone;
    }

    public void setPropPhone(String propPhone)
    {
        this.propPhone = propPhone;
    }

    public String getPropTel()
    {
        return propTel;
    }

    public void setPropTel(String propTel)
    {
        this.propTel = propTel;
    }

}
