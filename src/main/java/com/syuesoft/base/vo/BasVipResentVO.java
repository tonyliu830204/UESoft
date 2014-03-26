package com.syuesoft.base.vo;

import java.io.Serializable;

public class BasVipResentVO implements Serializable
{

    private Integer pstId;

    private String pstName;

    private Integer pstCount;

    private String pstUnit;

    private Integer pstInte;

    public BasVipResentVO()
    {

    }

    public Integer getPstId()
    {
        return pstId;
    }

    public void setPstId(Integer pstId)
    {
        this.pstId = pstId;
    }

    public String getPstName()
    {
        return pstName;
    }

    public void setPstName(String pstName)
    {
        this.pstName = pstName;
    }

    public Integer getPstCount()
    {
        return pstCount;
    }

    public void setPstCount(Integer pstCount)
    {
        this.pstCount = pstCount;
    }

    public String getPstUnit()
    {
        return pstUnit;
    }

    public void setPstUnit(String pstUnit)
    {
        this.pstUnit = pstUnit;
    }

    public Integer getPstInte()
    {
        return pstInte;
    }

    public void setPstInte(Integer pstInte)
    {
        this.pstInte = pstInte;
    }

    public BasVipResentVO(Integer pstId, String pstName, Integer pstCount,
            String pstUnit, Integer pstInte)
    {
        this.pstId = pstId;
        this.pstName = pstName;
        this.pstCount = pstCount;
        this.pstUnit = pstUnit;
        this.pstInte = pstInte;
    }
}
