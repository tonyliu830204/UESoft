package com.syuesoft.frt.vo;

import java.util.Date;

import com.syuesoft.base.vo.BaseBeanVo;

/**
 * 结算单->工时清单
 * 
 * @author Liujian
 * 
 */
public class FrtPreItemTreeGridVo extends BaseBeanVo
{

    private String preclrId;

    private Integer wktimeIndex;

    private String repitemId;

    private String repitemName;

    private Double wktime;

    private Double innerWktime;

    private Double wktimeAmount;

    private Short relcampId;

    private Short reptypId;

    private Short stfId;

    private Double settlementDiscount;

    private String id;

    private String state;

    private String iconCls;

    private String _parentId;

    private Date preclrTime;

    private Short preclrFlg;

    public String getPreclrId()
    {
        return preclrId;
    }

    public void setPreclrId(String preclrId)
    {
        this.preclrId = preclrId;
    }

    public Integer getWktimeIndex()
    {
        return wktimeIndex;
    }

    public void setWktimeIndex(Integer wktimeIndex)
    {
        this.wktimeIndex = wktimeIndex;
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

    public Double getWktime()
    {
        return wktime;
    }

    public void setWktime(Double wktime)
    {
        this.wktime = wktime;
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

    public Short getStfId()
    {
        return stfId;
    }

    public void setStfId(Short stfId)
    {
        this.stfId = stfId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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

    public Date getPreclrTime()
    {
        return preclrTime;
    }

    public void setPreclrTime(Date preclrTime)
    {
        this.preclrTime = preclrTime;
    }

    public Short getPreclrFlg()
    {
        return preclrFlg;
    }

    public void setPreclrFlg(Short preclrFlg)
    {
        this.preclrFlg = preclrFlg;
    }

    public Short getRelcampId()
    {
        return relcampId;
    }

    public void setRelcampId(Short relcampId)
    {
        this.relcampId = relcampId;
    }

    public Short getReptypId()
    {
        return reptypId;
    }

    public void setReptypId(Short reptypId)
    {
        this.reptypId = reptypId;
    }

    public Double getSettlementDiscount()
    {
        return settlementDiscount;
    }

    public void setSettlementDiscount(Double settlementDiscount)
    {
        this.settlementDiscount = settlementDiscount;
    }
}
