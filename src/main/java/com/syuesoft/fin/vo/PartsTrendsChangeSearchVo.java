package com.syuesoft.fin.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class PartsTrendsChangeSearchVo extends BaseBeanVo
{

    private static final long serialVersionUID = 1L;

    private String q;

    private String indexId;                //序号

    private String partsId;                //配件单号

    private String partsName;              //配件名称

    private String partsLibrary;           //库位

    private String punitName;              //配件单位名

    private String itemCount;              //销量

    private String amount;                 //销售金额

    private String avagePrice;             //平均单价

    private String castAmount;             //成本合计

    private String avageCastAmount;        //平均成本
    
    private String storeId;                //仓库编号
    
    private String storeName;              //仓库名称

    private String stomDate;               //出库退料日期
    
    private String cbrdId;                 //车辆品牌
    
    private String cbrdName;               //车辆品牌
    
    private String stypeId;                //车型
    
    private String stypeName;              //车型

    private String stomDateStart;          //开始日期

    private String stomDateEnd;            //结束日期

    private String strtpmId;               //消退单号  
    
    private String priorPeriodCount;       //上期库存量
    
    private String priorPeriodSellAmount;  //上期库存销售金额
    
    private String priorPeriodCostAmount;  //上期库存成本金额
    
    private String currentPeriodInCount;   //本期入库库存量
    
    private String currentPeriodInSellAmount;  //本期入库库存销售金额
    
    private String currentPeriodInCostAmount;  //本期入库库存成本金额
    
    private String currentPeriodOutCount;      //本期出库库存量
    
    private String currentPeriodOutSellAmount; //本期出库库存销售金额
    
    private String currentPeriodOutCostAmount; //本期出库库存成本金额
    
    private String currentCheckCount;          //本期盘点量
    
    private String currentCheckSellAmount;     //本期盘点销售金额
    
    private String currentCheckCostAmount;     //本期盘点成本金额
    
    private String surplusCount;               //结余库存量
    
    private String surplusSellAmount;          //结余库存销售金额
    
    private String surplusCostAmount;          //结余库存成本金额
    
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    public String getStrtpmId()
    {
        return strtpmId;
    }

    public void setStrtpmId(String strtpmId)
    {
        this.strtpmId = strtpmId;
    }

    public String getStomDateStart()
    {
        return stomDateStart;
    }

    public void setStomDateStart(String stomDateStart)
    {
        this.stomDateStart = stomDateStart;
    }

    public String getStomDateEnd()
    {
        return stomDateEnd;
    }

    public void setStomDateEnd(String stomDateEnd)
    {
        this.stomDateEnd = stomDateEnd;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
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

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public String getPartsLibrary()
    {
        return partsLibrary;
    }

    public void setPartsLibrary(String partsLibrary)
    {
        this.partsLibrary = partsLibrary;
    }

    public String getPunitName()
    {
        return punitName;
    }

    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
    }

    public String getItemCount()
    {
        return itemCount;
    }

    public void setItemCount(String itemCount)
    {
        this.itemCount = itemCount;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getAvagePrice()
    {
        return avagePrice;
    }

    public void setAvagePrice(String avagePrice)
    {
        this.avagePrice = avagePrice;
    }

    public String getCastAmount()
    {
        return castAmount;
    }

    public void setCastAmount(String castAmount)
    {
        this.castAmount = castAmount;
    }

    public String getAvageCastAmount()
    {
        return avageCastAmount;
    }

    public void setAvageCastAmount(String avageCastAmount)
    {
        this.avageCastAmount = avageCastAmount;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getStomDate()
    {
        return stomDate;
    }

    public void setStomDate(String stomDate)
    {
        this.stomDate = stomDate;
    }

    public String getCbrdName()
    {
        return cbrdName;
    }

    public void setCbrdName(String cbrdName)
    {
        this.cbrdName = cbrdName;
    }

    public String getStypeName()
    {
        return stypeName;
    }

    public void setStypeName(String stypeName)
    {
        this.stypeName = stypeName;
    }

    public String getPriorPeriodCount()
    {
        return priorPeriodCount;
    }

    public void setPriorPeriodCount(String priorPeriodCount)
    {
        this.priorPeriodCount = priorPeriodCount;
    }

    public String getCurrentPeriodInCount()
    {
        return currentPeriodInCount;
    }

    public void setCurrentPeriodInCount(String currentPeriodInCount)
    {
        this.currentPeriodInCount = currentPeriodInCount;
    }

    public String getCurrentPeriodOutCount()
    {
        return currentPeriodOutCount;
    }

    public void setCurrentPeriodOutCount(String currentPeriodOutCount)
    {
        this.currentPeriodOutCount = currentPeriodOutCount;
    }

    public String getCurrentCheckCount()
    {
        return currentCheckCount;
    }

    public void setCurrentCheckCount(String currentCheckCount)
    {
        this.currentCheckCount = currentCheckCount;
    }

    public String getSurplusCount()
    {
        return surplusCount;
    }

    public void setSurplusCount(String surplusCount)
    {
        this.surplusCount = surplusCount;
    }

    public String getPriorPeriodSellAmount()
    {
        return priorPeriodSellAmount;
    }

    public void setPriorPeriodSellAmount(String priorPeriodSellAmount)
    {
        this.priorPeriodSellAmount = priorPeriodSellAmount;
    }

    public String getPriorPeriodCostAmount()
    {
        return priorPeriodCostAmount;
    }

    public void setPriorPeriodCostAmount(String priorPeriodCostAmount)
    {
        this.priorPeriodCostAmount = priorPeriodCostAmount;
    }

    public String getCurrentPeriodInSellAmount()
    {
        return currentPeriodInSellAmount;
    }

    public void setCurrentPeriodInSellAmount(String currentPeriodInSellAmount)
    {
        this.currentPeriodInSellAmount = currentPeriodInSellAmount;
    }

    public String getCurrentPeriodInCostAmount()
    {
        return currentPeriodInCostAmount;
    }

    public void setCurrentPeriodInCostAmount(String currentPeriodInCostAmount)
    {
        this.currentPeriodInCostAmount = currentPeriodInCostAmount;
    }

    public String getCurrentPeriodOutSellAmount()
    {
        return currentPeriodOutSellAmount;
    }

    public void setCurrentPeriodOutSellAmount(String currentPeriodOutSellAmount)
    {
        this.currentPeriodOutSellAmount = currentPeriodOutSellAmount;
    }

    public String getCurrentPeriodOutCostAmount()
    {
        return currentPeriodOutCostAmount;
    }

    public void setCurrentPeriodOutCostAmount(String currentPeriodOutCostAmount)
    {
        this.currentPeriodOutCostAmount = currentPeriodOutCostAmount;
    }

    public String getCurrentCheckSellAmount()
    {
        return currentCheckSellAmount;
    }

    public void setCurrentCheckSellAmount(String currentCheckSellAmount)
    {
        this.currentCheckSellAmount = currentCheckSellAmount;
    }

    public String getCurrentCheckCostAmount()
    {
        return currentCheckCostAmount;
    }

    public void setCurrentCheckCostAmount(String currentCheckCostAmount)
    {
        this.currentCheckCostAmount = currentCheckCostAmount;
    }

    public String getSurplusSellAmount()
    {
        return surplusSellAmount;
    }

    public void setSurplusSellAmount(String surplusSellAmount)
    {
        this.surplusSellAmount = surplusSellAmount;
    }

    public String getSurplusCostAmount()
    {
        return surplusCostAmount;
    }

    public void setSurplusCostAmount(String surplusCostAmount)
    {
        this.surplusCostAmount = surplusCostAmount;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
    }

    public String getCbrdId()
    {
        return cbrdId;
    }

    public void setCbrdId(String cbrdId)
    {
        this.cbrdId = cbrdId;
    }

    public String getStypeId()
    {
        return stypeId;
    }

    public void setStypeId(String stypeId)
    {
        this.stypeId = stypeId;
    }
}