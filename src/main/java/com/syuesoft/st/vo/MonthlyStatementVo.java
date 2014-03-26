package com.syuesoft.st.vo;

public class MonthlyStatementVo {
	
    private String msId;
    private String msNumber;
    private String stfId;
    private String stfName;
    private String msStarttime;
    private String msEndtime;
    private String operDate;
    private String msRemindtime;
    private String msNexttime;
    private String msRemark;
    
    private String indexId;                           //编号
    private String partsId;                           //配件编码
    private String partsName;                         //配件编码
    private String stinvdCount;                       //个数
    private String stinvdPrice;                       //未税价格
    private String stinvdCost;                        //未税金额
    private String stinvdPrice1;                      //含税价格
    private String stinvdCost1;                       //含税金额
    private String storeId;                           //仓别
    private String storeName;                         //仓别
    
    private String operStartDate;
    private String operEndDate;
    private String msStartRemindtime;
    private String msEndRemindtime;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    private String sort;
 	private String order;
 	private int rows;
 	private int page;
    public String getMsId()
    {
        return msId;
    }
    public void setMsId(String msId)
    {
        this.msId = msId;
    }
    public String getMsNumber()
    {
        return msNumber;
    }
    public void setMsNumber(String msNumber)
    {
        this.msNumber = msNumber;
    }
    public String getStfId()
    {
        return stfId;
    }
    public void setStfId(String stfId)
    {
        this.stfId = stfId;
    }
    public String getStfName()
    {
        return stfName;
    }
    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }
    public String getMsStarttime()
    {
        return msStarttime;
    }
    public void setMsStarttime(String msStarttime)
    {
        this.msStarttime = msStarttime;
    }
    public String getMsEndtime()
    {
        return msEndtime;
    }
    public void setMsEndtime(String msEndtime)
    {
        this.msEndtime = msEndtime;
    }
    public String getOperDate()
    {
        return operDate;
    }
    public void setOperDate(String operDate)
    {
        this.operDate = operDate;
    }
    public String getMsRemindtime()
    {
        return msRemindtime;
    }
    public void setMsRemindtime(String msRemindtime)
    {
        this.msRemindtime = msRemindtime;
    }
    public String getMsNexttime()
    {
        return msNexttime;
    }
    public void setMsNexttime(String msNexttime)
    {
        this.msNexttime = msNexttime;
    }
    public String getMsRemark()
    {
        return msRemark;
    }
    public void setMsRemark(String msRemark)
    {
        this.msRemark = msRemark;
    }
    public String getIndexId()
    {
        return indexId;
    }
    public void setIndexId(String indexId)
    {
        this.indexId = indexId;
    }
    public String getPartsId()
    {
        return partsId;
    }
    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }
    public String getStinvdCount()
    {
        return stinvdCount;
    }
    public void setStinvdCount(String stinvdCount)
    {
        this.stinvdCount = stinvdCount;
    }
    public String getStinvdPrice()
    {
        return stinvdPrice;
    }
    public void setStinvdPrice(String stinvdPrice)
    {
        this.stinvdPrice = stinvdPrice;
    }
    public String getStinvdCost()
    {
        return stinvdCost;
    }
    public void setStinvdCost(String stinvdCost)
    {
        this.stinvdCost = stinvdCost;
    }
    public String getStinvdPrice1()
    {
        return stinvdPrice1;
    }
    public void setStinvdPrice1(String stinvdPrice1)
    {
        this.stinvdPrice1 = stinvdPrice1;
    }
    public String getStinvdCost1()
    {
        return stinvdCost1;
    }
    public void setStinvdCost1(String stinvdCost1)
    {
        this.stinvdCost1 = stinvdCost1;
    }
    public String getStoreId()
    {
        return storeId;
    }
    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
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
    public String getPartsName()
    {
        return partsName;
    }
    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }
    public String getStoreName()
    {
        return storeName;
    }
    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }
    public String getOperStartDate()
    {
        return operStartDate;
    }
    public void setOperStartDate(String operStartDate)
    {
        this.operStartDate = operStartDate;
    }
    public String getOperEndDate()
    {
        return operEndDate;
    }
    public void setOperEndDate(String operEndDate)
    {
        this.operEndDate = operEndDate;
    }
    public String getMsStartRemindtime()
    {
        return msStartRemindtime;
    }
    public void setMsStartRemindtime(String msStartRemindtime)
    {
        this.msStartRemindtime = msStartRemindtime;
    }
    public String getMsEndRemindtime()
    {
        return msEndRemindtime;
    }
    public void setMsEndRemindtime(String msEndRemindtime)
    {
        this.msEndRemindtime = msEndRemindtime;
    }
}