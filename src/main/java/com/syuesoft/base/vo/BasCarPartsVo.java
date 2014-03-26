package com.syuesoft.base.vo;

/**
 * BasCarParts entity. @author MyEclipse Persistence Tools
 */

public class BasCarPartsVo implements java.io.Serializable
{

    // Fields

    private Short carPartId;

    private String carPartName;//

    private String carPartRemark;

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
    public BasCarPartsVo()
    {
    }

    /** minimal constructor */
    public BasCarPartsVo(Short carPartId)
    {
        this.carPartId = carPartId;
    }

    /** full constructor */
    public BasCarPartsVo(Short carPartId, String carPartName,
            String carPartRemark)
    {
        this.carPartId = carPartId;
        this.carPartName = carPartName;
        this.carPartRemark = carPartRemark;
    }

    // Property accessors

    public Short getCarPartId()
    {
        return this.carPartId;
    }

    public void setCarPartId(Short carPartId)
    {
        this.carPartId = carPartId;
    }

    public String getCarPartName()
    {
        return this.carPartName;
    }

    public void setCarPartName(String carPartName)
    {
        this.carPartName = carPartName;
    }

    public String getCarPartRemark()
    {
        return this.carPartRemark;
    }

    public void setCarPartRemark(String carPartRemark)
    {
        this.carPartRemark = carPartRemark;
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