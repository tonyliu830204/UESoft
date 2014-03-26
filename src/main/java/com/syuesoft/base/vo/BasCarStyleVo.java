package com.syuesoft.base.vo;

public class BasCarStyleVo
{

    private String carCstlName;

    private String cstlName;

    private String ctypePrice;

    private String ctypeName;;

    private String ctypeId;

    private String q;

    private int rows;

    private int page;

    private String sort;

    private String order;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
    public BasCarStyleVo()
    {

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

    public BasCarStyleVo(short ctypeId, String ctypeName)
    {
        this.ctypeId = ctypeId + "";
        this.ctypeName = ctypeName;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public String getCarCstlName()
    {
        return carCstlName;
    }

    public void setCarCstlName(String carCstlName)
    {
        this.carCstlName = carCstlName;
    }

    public String getCstlName()
    {
        return cstlName;
    }

    public void setCstlName(String cstlName)
    {
        this.cstlName = cstlName;
    }

    public String getCtypePrice()
    {
        return ctypePrice;
    }

    public void setCtypePrice(String ctypePrice)
    {
        this.ctypePrice = ctypePrice;
    }

    public String getCtypeName()
    {
        return ctypeName;
    }

    public void setCtypeName(String ctypeName)
    {
        this.ctypeName = ctypeName;
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

    public String getCtypeId()
    {
        return ctypeId;
    }

    public void setCtypeId(String ctypeId)
    {
        this.ctypeId = ctypeId;
    }

}
