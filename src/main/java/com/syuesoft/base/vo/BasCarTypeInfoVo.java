package com.syuesoft.base.vo;

public class BasCarTypeInfoVo
{

    private Short ctypeId; // 车型号编号

    private String ctypeName; // 车型号名称

    private Short cbrdId; // 车品牌编号

    private String cbrdName;

    private Double ctypePrice; // 车型号工时单价

    private String q;

    private String sort;

    private String order;

    private int page;

    private int rows;

    private Integer enterpriseId;

    public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
    
    public BasCarTypeInfoVo()
    {

    }

    public BasCarTypeInfoVo(Short ctypeId, String ctypeName, Short cbrdId,
            String cbrdName, Double ctypePrice)
    {
        this.ctypeId = ctypeId;
        this.cbrdName = cbrdName;
        this.ctypeName = ctypeName;
        this.cbrdId = cbrdId;
        this.ctypePrice = ctypePrice;
    }

    public Short getCtypeId()
    {
        return ctypeId;
    }

    public void setCtypeId(Short ctypeId)
    {
        this.ctypeId = ctypeId;
    }

    public String getCtypeName()
    {
        return ctypeName;
    }

    public void setCtypeName(String ctypeName)
    {
        this.ctypeName = ctypeName;
    }

    public Double getCtypePrice()
    {
        return ctypePrice;
    }

    public void setCtypePrice(Double ctypePrice)
    {
        this.ctypePrice = ctypePrice;
    }

    public Short getCbrdId()
    {
        return cbrdId;
    }

    public void setCbrdId(Short cbrdId)
    {
        this.cbrdId = cbrdId;
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

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public String getCbrdName()
    {
        return cbrdName;
    }

    public void setCbrdName(String cbrdName)
    {
        this.cbrdName = cbrdName;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

}