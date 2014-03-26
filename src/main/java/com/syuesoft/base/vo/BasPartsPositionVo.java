package com.syuesoft.base.vo;

/**
 * BasPartsPosition entity. @author MyEclipse Persistence Tools
 */

public class BasPartsPositionVo implements java.io.Serializable
{

    // Fields

    private Short posId;

    private String posName;

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
    public BasPartsPositionVo()
    {
    }

    /** minimal constructor */
    public BasPartsPositionVo(Short posId)
    {
        this.posId = posId;
    }

    /** full constructor */
    public BasPartsPositionVo(Short posId, String posName, String remark)
    {
        this.posId = posId;
        this.posName = posName;
        this.remark = remark;
    }

    // Property accessors

    public Short getPosId()
    {
        return this.posId;
    }

    public void setPosId(Short posId)
    {
        this.posId = posId;
    }

    public String getPosName()
    {
        return this.posName;
    }

    public void setPosName(String posName)
    {
        this.posName = posName;
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