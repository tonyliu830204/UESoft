package com.syuesoft.base.vo;

import java.sql.Timestamp;

public class BarChildrenAndParentVo
{

    private Long childId;

    private Long parentId;

    private Short stfId;

    private String createTime;

    private String dataKey;

    private String dataValue;

    private String parentName;

    private String stfName;

    // 以下为分页字段
    private String sort;

    private String order;

    private int rows;

    private int page;

    // Constructors
    // 查询用到的字段
    private String querydataKey;

    private String querydataValue;

    public BarChildrenAndParentVo()
    {
        // TODO Auto-generated constructor stub
    }

    public Long getChildId()
    {
        return childId;
    }

    public void setChildId(Long childId)
    {
        this.childId = childId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Short getStfId()
    {
        return stfId;
    }

    public void setStfId(Short stfId)
    {
        this.stfId = stfId;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getDataKey()
    {
        return dataKey;
    }

    public void setDataKey(String dataKey)
    {
        this.dataKey = dataKey;
    }

    public String getDataValue()
    {
        return dataValue;
    }

    public void setDataValue(String dataValue)
    {
        this.dataValue = dataValue;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public String getQuerydataKey()
    {
        return querydataKey;
    }

    public void setQuerydataKey(String querydataKey)
    {
        this.querydataKey = querydataKey;
    }

    public String getQuerydataValue()
    {
        return querydataValue;
    }

    public void setQuerydataValue(String querydataValue)
    {
        this.querydataValue = querydataValue;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

}
