package com.syuesoft.st.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class StockPartsVo  extends BaseBeanVo
{
    private static final long serialVersionUID = 1L;

    private String partsId;                                                                        //配件单号

    private String partsName;                                                                      //配件名称
    
    private String partsModelId;                                                                   //配件型号编号
    
    private String partsModelName;                                                                 //配件型号名称
    
    private String storeId;                                                                        //仓库编号
    
    private String storeName;                                                                      //仓库名称
    
    private String storeModelId;                                                                   //仓库编号
    
    private String partsLibrary;                                                                   //库位
    
    private String stypeId;                                                                        //适合的车型
    
    private String stockType;                                                                      //库存类别
    
    private String typeWay;                                                                        //分类方式

    private String stomDateStart;                                                                  //开始日期

    private String stomDateEnd;                                                                    //结束日期 
    
    private String priorCount;                                                                     //上期库存量
    
    private String priorSellAmount;                                                                //上期库存销售金额
    
    private String priorCostAmount;                                                                //上期库存成本金额
    
    private String currentInCount;                                                                 //本期入库库存量
    
    private String currentInSellAmount;                                                            //本期入库库存销售金额
    
    private String currentInCostAmount;                                                            //本期入库库存成本金额
    
    private String currentOutCount;                                                                //本期工单出库库存量
    
    private String currentOutSellAmount;                                                           //本期工单出库库存销售金额
    
    private String currentOutCostAmount;                                                           //本期工单出库库存成本金额
    
    private String currentOutCount1;                                                               //本期销售单出库库存量
    
    private String currentOutSellAmount1;                                                          //本期销售单出库库存销售金额
    
    private String currentOutCostAmount1;                                                          //本期销售单出库库存成本金额
    
    private String currentOutCount2;                                                               //本期整车加装出库库存量
    
    private String currentOutSellAmount2;                                                          //本期整车加装出库库存销售金额
    
    private String currentOutCostAmount2;                                                          //本期整车加装出库库存成本金额
    
    private String currentCancelCount;                                                             //本期退货库存量
    
    private String currentCancelSellAmount;                                                        //本期退货库存销售金额
    
    private String currentCancelCostAmount;                                                        //本期退货库存成本金额
    
    private String currentMaterialCount;                                                           //本期工单退料库存量
    
    private String currentMaterialSellAmount;                                                      //本期工单退料存销售金额
    
    private String currentMaterialCostAmount;                                                      //本期工单退料存成本金额
    
    private String currentMaterialCount1;                                                          //本期销售单退料库存量
    
    private String currentMaterialSellAmount1;                                                     //本期销售单退料存销售金额
    
    private String currentMaterialCostAmount1;                                                     //本期销售单退料存成本金额
    
    private String currentCheckCount;                                                              //本期盘点量
    
    private String currentCheckSellAmount;                                                         //本期盘点销售金额
    
    private String currentCheckCostAmount;                                                         //本期盘点成本金额
    
    private String surplusCount;                                                                   //当前库存量
    
    private String surplusSellAmount;                                                              //当前库存销售金额
    
    private String surplusCostAmount;                                                              //当前库存成本金额
    
    private String state; // treegrid打开还是关闭

    private String iconCls; // treegrid图标样式
    
    private String childMenu;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public String getPartsModelId()
    {
        return partsModelId;
    }

    public void setPartsModelId(String partsModelId)
    {
        this.partsModelId = partsModelId;
    }

    public String getPartsModelName()
    {
        return partsModelName;
    }

    public void setPartsModelName(String partsModelName)
    {
        this.partsModelName = partsModelName;
    }
    
    public String getStoreModelId()
    {
        return storeModelId;
    }

    public void setStoreModelId(String storeModelId)
    {
        this.storeModelId = storeModelId;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getStockType()
    {
        return stockType;
    }

    public void setStockType(String stockType)
    {
        this.stockType = stockType;
    }

    public String getTypeWay()
    {
        return typeWay;
    }

    public void setTypeWay(String typeWay)
    {
        this.typeWay = typeWay;
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

    public String getPriorCount()
    {
        return priorCount;
    }

    public void setPriorCount(String priorCount)
    {
        this.priorCount = priorCount;
    }

    public String getPriorSellAmount()
    {
        return priorSellAmount;
    }

    public void setPriorSellAmount(String priorSellAmount)
    {
        this.priorSellAmount = priorSellAmount;
    }

    public String getPriorCostAmount()
    {
        return priorCostAmount;
    }

    public void setPriorCostAmount(String priorCostAmount)
    {
        this.priorCostAmount = priorCostAmount;
    }

    public String getCurrentInCount()
    {
        return currentInCount;
    }

    public void setCurrentInCount(String currentInCount)
    {
        this.currentInCount = currentInCount;
    }

    public String getCurrentInSellAmount()
    {
        return currentInSellAmount;
    }

    public void setCurrentInSellAmount(String currentInSellAmount)
    {
        this.currentInSellAmount = currentInSellAmount;
    }

    public String getCurrentInCostAmount()
    {
        return currentInCostAmount;
    }

    public void setCurrentInCostAmount(String currentInCostAmount)
    {
        this.currentInCostAmount = currentInCostAmount;
    }

    public String getCurrentOutCount()
    {
        return currentOutCount;
    }

    public void setCurrentOutCount(String currentOutCount)
    {
        this.currentOutCount = currentOutCount;
    }

    public String getCurrentOutSellAmount()
    {
        return currentOutSellAmount;
    }

    public void setCurrentOutSellAmount(String currentOutSellAmount)
    {
        this.currentOutSellAmount = currentOutSellAmount;
    }

    public String getCurrentOutCostAmount()
    {
        return currentOutCostAmount;
    }

    public void setCurrentOutCostAmount(String currentOutCostAmount)
    {
        this.currentOutCostAmount = currentOutCostAmount;
    }

    public String getCurrentOutCount1()
    {
        return currentOutCount1;
    }

    public void setCurrentOutCount1(String currentOutCount1)
    {
        this.currentOutCount1 = currentOutCount1;
    }

    public String getCurrentOutSellAmount1()
    {
        return currentOutSellAmount1;
    }

    public void setCurrentOutSellAmount1(String currentOutSellAmount1)
    {
        this.currentOutSellAmount1 = currentOutSellAmount1;
    }

    public String getCurrentOutCostAmount1()
    {
        return currentOutCostAmount1;
    }

    public void setCurrentOutCostAmount1(String currentOutCostAmount1)
    {
        this.currentOutCostAmount1 = currentOutCostAmount1;
    }

    public String getCurrentOutCount2()
    {
        return currentOutCount2;
    }

    public void setCurrentOutCount2(String currentOutCount2)
    {
        this.currentOutCount2 = currentOutCount2;
    }

    public String getCurrentOutSellAmount2()
    {
        return currentOutSellAmount2;
    }

    public void setCurrentOutSellAmount2(String currentOutSellAmount2)
    {
        this.currentOutSellAmount2 = currentOutSellAmount2;
    }

    public String getCurrentOutCostAmount2()
    {
        return currentOutCostAmount2;
    }

    public void setCurrentOutCostAmount2(String currentOutCostAmount2)
    {
        this.currentOutCostAmount2 = currentOutCostAmount2;
    }

    public String getCurrentCancelCount()
    {
        return currentCancelCount;
    }

    public void setCurrentCancelCount(String currentCancelCount)
    {
        this.currentCancelCount = currentCancelCount;
    }

    public String getCurrentCancelSellAmount()
    {
        return currentCancelSellAmount;
    }

    public void setCurrentCancelSellAmount(String currentCancelSellAmount)
    {
        this.currentCancelSellAmount = currentCancelSellAmount;
    }

    public String getCurrentCancelCostAmount()
    {
        return currentCancelCostAmount;
    }

    public void setCurrentCancelCostAmount(String currentCancelCostAmount)
    {
        this.currentCancelCostAmount = currentCancelCostAmount;
    }

    public String getCurrentMaterialCount()
    {
        return currentMaterialCount;
    }

    public void setCurrentMaterialCount(String currentMaterialCount)
    {
        this.currentMaterialCount = currentMaterialCount;
    }

    public String getCurrentMaterialSellAmount()
    {
        return currentMaterialSellAmount;
    }

    public void setCurrentMaterialSellAmount(String currentMaterialSellAmount)
    {
        this.currentMaterialSellAmount = currentMaterialSellAmount;
    }

    public String getCurrentMaterialCostAmount()
    {
        return currentMaterialCostAmount;
    }

    public void setCurrentMaterialCostAmount(String currentMaterialCostAmount)
    {
        this.currentMaterialCostAmount = currentMaterialCostAmount;
    }

    public String getCurrentMaterialCount1()
    {
        return currentMaterialCount1;
    }

    public void setCurrentMaterialCount1(String currentMaterialCount1)
    {
        this.currentMaterialCount1 = currentMaterialCount1;
    }

    public String getCurrentMaterialSellAmount1()
    {
        return currentMaterialSellAmount1;
    }

    public void setCurrentMaterialSellAmount1(String currentMaterialSellAmount1)
    {
        this.currentMaterialSellAmount1 = currentMaterialSellAmount1;
    }

    public String getCurrentMaterialCostAmount1()
    {
        return currentMaterialCostAmount1;
    }

    public void setCurrentMaterialCostAmount1(String currentMaterialCostAmount1)
    {
        this.currentMaterialCostAmount1 = currentMaterialCostAmount1;
    }

    public String getCurrentCheckCount()
    {
        return currentCheckCount;
    }

    public void setCurrentCheckCount(String currentCheckCount)
    {
        this.currentCheckCount = currentCheckCount;
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

    public String getSurplusCount()
    {
        return surplusCount;
    }

    public void setSurplusCount(String surplusCount)
    {
        this.surplusCount = surplusCount;
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

    public String getPartsLibrary()
    {
        return partsLibrary;
    }

    public void setPartsLibrary(String partsLibrary)
    {
        this.partsLibrary = partsLibrary;
    }

    public String getStypeId()
    {
        return stypeId;
    }

    public void setStypeId(String stypeId)
    {
        this.stypeId = stypeId;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String getChildMenu()
    {
        return childMenu;
    }

    public void setChildMenu(String childMenu)
    {
        this.childMenu = childMenu;
    }
}