package com.syuesoft.base.vo;

import java.util.Date;

public class ToolsManagerVo
{

    private Short toolsId;

    private Date recordDate;

    private String toolsName;

    private String toolsType;

    private Short toolsUnit;

    private Short toolsState;

    private Double procurementPrice;

    private Short supplier;

    private String linkman;

    private String telphone;

    private Short buyer;

    private String punitName;

    private String relcampName;

    private String stfName;

    private String sort;

    private String order;

    private int page;

    private int rows;

    public ToolsManagerVo()
    {

    }

    public ToolsManagerVo(Short toolsId, Date recordDate, String toolsName,
            String toolsType, Short toolsUnit, Short toolsState,
            Double procurementPrice, Short supplier, String linkman,
            String telphone, Short buyer, String punitName, String relcampName,
            String stfName)
    {
        this.toolsId = toolsId;
        this.recordDate = recordDate;
        this.toolsName = toolsName;
        this.toolsType = toolsType;
        this.toolsUnit = toolsUnit;
        this.toolsState = toolsState;
        this.procurementPrice = procurementPrice;
        this.supplier = supplier;
        this.linkman = linkman;
        this.telphone = telphone;
        this.buyer = buyer;
        this.punitName = punitName;
        this.relcampName = relcampName;
        this.stfName = stfName;
    }

    public Short getToolsId()
    {
        return toolsId;
    }

    public void setToolsId(Short toolsId)
    {
        this.toolsId = toolsId;
    }

    public Date getRecordDate()
    {
        return recordDate;
    }

    public void setRecordDate(Date recordDate)
    {
        this.recordDate = recordDate;
    }

    public String getToolsName()
    {
        return toolsName;
    }

    public void setToolsName(String toolsName)
    {
        this.toolsName = toolsName;
    }

    public String getToolsType()
    {
        return toolsType;
    }

    public void setToolsType(String toolsType)
    {
        this.toolsType = toolsType;
    }

    public Short getToolsUnit()
    {
        return toolsUnit;
    }

    public void setToolsUnit(Short toolsUnit)
    {
        this.toolsUnit = toolsUnit;
    }

    public Short getToolsState()
    {
        return toolsState;
    }

    public void setToolsState(Short toolsState)
    {
        this.toolsState = toolsState;
    }

    public Double getProcurementPrice()
    {
        return procurementPrice;
    }

    public void setProcurementPrice(Double procurementPrice)
    {
        this.procurementPrice = procurementPrice;
    }

    public Short getSupplier()
    {
        return supplier;
    }

    public void setSupplier(Short supplier)
    {
        this.supplier = supplier;
    }

    public String getLinkman()
    {
        return linkman;
    }

    public void setLinkman(String linkman)
    {
        this.linkman = linkman;
    }

    public String getTelphone()
    {
        return telphone;
    }

    public void setTelphone(String telphone)
    {
        this.telphone = telphone;
    }

    public Short getBuyer()
    {
        return buyer;
    }

    public void setBuyer(Short buyer)
    {
        this.buyer = buyer;
    }

    public String getPunitName()
    {
        return punitName;
    }

    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
    }

    public String getRelcampName()
    {
        return relcampName;
    }

    public void setRelcampName(String relcampName)
    {
        this.relcampName = relcampName;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
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

}
