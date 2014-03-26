package com.syuesoft.base.vo;

public class FrtCarAllowCarTypeVo implements java.io.Serializable
{

    private Short allowCarTypeId;

    private String allowCarTypeName;

    private String sort;

    private String order;

    private int rows;

    private int page;

    private Integer enterpriseId;

    public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
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

    public Short getAllowCarTypeId()
    {
        return allowCarTypeId;
    }

    public void setAllowCarTypeId(Short allowCarTypeId)
    {
        this.allowCarTypeId = allowCarTypeId;
    }

    public String getAllowCarTypeName()
    {
        return allowCarTypeName;
    }

    public void setAllowCarTypeName(String allowCarTypeName)
    {
        this.allowCarTypeName = allowCarTypeName;
    }

}
