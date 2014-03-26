package com.syuesoft.base.vo;

/**
 * BasCarColor entity. @author MyEclipse Persistence Tools
 */

public class BasCarColorVo implements java.io.Serializable
{

    // Fields

    private Short color;

    private String colorName;

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
    public BasCarColorVo()
    {
    }

    /** full constructor */
    public BasCarColorVo(String colorName, String remark)
    {
        this.colorName = colorName;
        this.remark = remark;
    }

    // Property accessors

    public Short getColor()
    {
        return this.color;
    }

    public void setColor(Short color)
    {
        this.color = color;
    }

    public String getColorName()
    {
        return this.colorName;
    }

    public void setColorName(String colorName)
    {
        this.colorName = colorName;
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