package com.syuesoft.base.vo;

/**
 * BasPartsUnit entity. @author MyEclipse Persistence Tools
 */

public class BasPartsUnitVo implements java.io.Serializable
{

    // Fields

    private Short punitId;

    private String punitName;

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
    public BasPartsUnitVo()
    {
    }

    /** minimal constructor */
    public BasPartsUnitVo(Short punitId)
    {
        this.punitId = punitId;
    }

    /** full constructor */
    public BasPartsUnitVo(Short punitId, String punitName, String remark)
    {
        this.punitId = punitId;
        this.punitName = punitName;
        this.remark = remark;
    }

    // Property accessors

    public Short getPunitId()
    {
        return this.punitId;
    }

    public void setPunitId(Short punitId)
    {
        this.punitId = punitId;
    }

    public String getPunitName()
    {
        return this.punitName;
    }

    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
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