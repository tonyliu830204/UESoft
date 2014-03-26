package com.syuesoft.base.vo;

/**
 * BasCarSellers entity. @author MyEclipse Persistence Tools
 */

public class BasCarSellersVo implements java.io.Serializable
{

    // Fields

    private Short slsId;

    private String slsName;

    private String remark;

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
    // Constructors

    /** default constructor */
    public BasCarSellersVo()
    {
    }

    /** minimal constructor */
    public BasCarSellersVo(Short slsId)
    {
        this.slsId = slsId;
    }

    /** full constructor */
    public BasCarSellersVo(Short slsId, String slsName, String remark)
    {
        this.slsId = slsId;
        this.slsName = slsName;
        this.remark = remark;
    }

    // Property accessors

    public Short getSlsId()
    {
        return this.slsId;
    }

    public void setSlsId(Short slsId)
    {
        this.slsId = slsId;
    }

    public String getSlsName()
    {
        return this.slsName;
    }

    public void setSlsName(String slsName)
    {
        this.slsName = slsName;
    }

    public String getRemark()
    {
        return this.remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
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

}