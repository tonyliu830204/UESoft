package com.syuesoft.base.vo;

/**
 * BasPartsReportpriceclassfication entity. @author MyEclipse Persistence Tools
 */

public class BasPartsReportpriceclassficationVo implements java.io.Serializable
{

    // Fields

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private Short partsId;

    private String partsName;

    private String partsBrand;

    private String partsType;

    private String partsInviteBid;

    private String partsSuccessfulBid;

    private String partsUnitMeasurement;

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
    public BasPartsReportpriceclassficationVo()
    {
    }

    /** minimal constructor */
    public BasPartsReportpriceclassficationVo(Short partsId)
    {
        this.partsId = partsId;
    }

    /** full constructor */
    public BasPartsReportpriceclassficationVo(Short partsId, String partsName,
            String partsBrand, String partsType, String partsInviteBid,
            String partsSuccessfulBid, String partsUnitMeasurement)
    {
        this.partsId = partsId;
        this.partsName = partsName;
        this.partsBrand = partsBrand;
        this.partsType = partsType;
        this.partsInviteBid = partsInviteBid;
        this.partsSuccessfulBid = partsSuccessfulBid;
        this.partsUnitMeasurement = partsUnitMeasurement;
    }

    // Property accessors

    public Short getPartsId()
    {
        return this.partsId;
    }

    public void setPartsId(Short partsId)
    {
        this.partsId = partsId;
    }

    public String getPartsName()
    {
        return this.partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public String getPartsBrand()
    {
        return this.partsBrand;
    }

    public void setPartsBrand(String partsBrand)
    {
        this.partsBrand = partsBrand;
    }

    public String getPartsType()
    {
        return this.partsType;
    }

    public void setPartsType(String partsType)
    {
        this.partsType = partsType;
    }

    public String getPartsInviteBid()
    {
        return this.partsInviteBid;
    }

    public void setPartsInviteBid(String partsInviteBid)
    {
        this.partsInviteBid = partsInviteBid;
    }

    public String getPartsSuccessfulBid()
    {
        return this.partsSuccessfulBid;
    }

    public void setPartsSuccessfulBid(String partsSuccessfulBid)
    {
        this.partsSuccessfulBid = partsSuccessfulBid;
    }

    public String getPartsUnitMeasurement()
    {
        return this.partsUnitMeasurement;
    }

    public void setPartsUnitMeasurement(String partsUnitMeasurement)
    {
        this.partsUnitMeasurement = partsUnitMeasurement;
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