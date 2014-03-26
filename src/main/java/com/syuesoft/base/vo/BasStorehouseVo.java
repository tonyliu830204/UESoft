package com.syuesoft.base.vo;

/**
 * BasStorehouse entity. @author MyEclipse Persistence Tools
 */

public class BasStorehouseVo implements java.io.Serializable
{

    // Fields

    private Short storeId;

    private String storeName;

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
    public BasStorehouseVo()
    {
    }

    /** minimal constructor */
    public BasStorehouseVo(Short storeId)
    {
        this.storeId = storeId;
    }

    /** full constructor */
    public BasStorehouseVo(Short storeId, String storeName, String remark)
    {
        this.storeId = storeId;
        this.storeName = storeName;
        this.remark = remark;
    }

    // Property accessors

    public Short getStoreId()
    {
        return this.storeId;
    }

    public void setStoreId(Short storeId)
    {
        this.storeId = storeId;
    }

    public String getStoreName()
    {
        return this.storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
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