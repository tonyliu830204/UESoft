package com.syuesoft.base.vo;

import java.io.Serializable;

public class BaseBeanVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String sort;

    private String order;

    private int rows = 0;

    private int page = 0;
    
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
}