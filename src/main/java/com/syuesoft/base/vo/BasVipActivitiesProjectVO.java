package com.syuesoft.base.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BasVipActivitiesProjectVO implements Serializable
{

    private Short vipActProId;

    private String vipActProName;

    private Date vipActProDate = new Date();

    private Short vipActJoinPcount;

    private String vipActProNote;

    private Integer vipActReward;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public BasVipActivitiesProjectVO()
    {

    }

    public BasVipActivitiesProjectVO(Short vipActProId, String vipActProName,
            Date vipActProDate, Short vipActJoinPcount, String vipActProNote,
            Integer vipActReward)
    {
        this.vipActProId = vipActProId;
        this.vipActProName = vipActProName;
        this.vipActProDate = vipActProDate;
        this.vipActJoinPcount = vipActJoinPcount;
        this.vipActProNote = vipActProNote;
        this.vipActReward = vipActReward;
    }

    public Short getVipActProId()
    {
        return vipActProId;
    }

    public void setVipActProId(Short vipActProId)
    {
        this.vipActProId = vipActProId;
    }

    public String getVipActProName()
    {
        return vipActProName;
    }

    public void setVipActProName(String vipActProName)
    {
        this.vipActProName = vipActProName;
    }

    public Date getVipActProDate()
    {
        return vipActProDate;
    }

    public void setVipActProDate(Date vipActProDate)
    {
        this.vipActProDate = vipActProDate;
    }

    public Short getVipActJoinPcount()
    {
        return vipActJoinPcount;
    }

    public void setVipActJoinPcount(Short vipActJoinPcount)
    {
        this.vipActJoinPcount = vipActJoinPcount;
    }

    public String getVipActProNote()
    {
        return vipActProNote;
    }

    public void setVipActProNote(String vipActProNote)
    {
        this.vipActProNote = vipActProNote;
    }

    public Integer getVipActReward()
    {
        return vipActReward;
    }

    public void setVipActReward(Integer vipActReward)
    {
        this.vipActReward = vipActReward;
    }

}
