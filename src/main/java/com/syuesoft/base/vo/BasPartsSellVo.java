package com.syuesoft.base.vo;

/**
 * BasPartsSell entity. @author MyEclipse Persistence Tools
 */

public class BasPartsSellVo implements java.io.Serializable
{

    // Fields

    private Short psellId;

    private String psellName;

    private Short psellPoint;

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
    public BasPartsSellVo()
    {
    }

    /** minimal constructor */
    public BasPartsSellVo(Short psellId)
    {
        this.psellId = psellId;
    }

    /** full constructor */
    public BasPartsSellVo(Short psellId, String psellName, Short psellPoint,
            String remark)
    {
        this.psellId = psellId;
        this.psellName = psellName;
        this.psellPoint = psellPoint;
        this.remark = remark;
    }

    // Property accessors

    public Short getPsellId()
    {
        return this.psellId;
    }

    public void setPsellId(Short psellId)
    {
        this.psellId = psellId;
    }

    public String getPsellName()
    {
        return this.psellName;
    }

    public void setPsellName(String psellName)
    {
        this.psellName = psellName;
    }

    public Short getPsellPoint()
    {
        return this.psellPoint;
    }

    public void setPsellPoint(Short psellPoint)
    {
        this.psellPoint = psellPoint;
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