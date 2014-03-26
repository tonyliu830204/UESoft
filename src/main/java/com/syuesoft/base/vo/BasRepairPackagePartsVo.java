package com.syuesoft.base.vo;

public class BasRepairPackagePartsVo
{

    private String partsId;

    private String partsId2;

    private String partsName;

    private String partsSimpleId;

    private String fitPtype;

    private String partsRemark;

    private Short ptypeId;

    private String ptypeName;

    private Short pbrdId;

    private String pbrdName;

    // 维修套餐->配件选择
    private String id;

    private Short stateId;

    private String stateName;

    private Short posId;

    private String posName;

    private Short punitId;

    private String punitName;

    private Integer partsNowCount;

    private Double partsRepairPrice;

    private Short storeId;

    private String storeName;

    // 维修套餐->选择计划用料
    public BasRepairPackagePartsVo(String id, String partsId, String partsId2,
            String partsName, Short pbrdId, String pbrdName, Short ptypeId,
            String ptypeName, String fitPtype, String partsRemark,
            Short stateId, String stateName, Short posId, String posName,
            Short punitId, String punitName, Integer partsNowCount,
            Double partsRepairPrice, Short storeId, String storeName)
    {
        this.id = id;
        this.partsId = partsId;
        this.partsId2 = partsId2;
        this.partsName = partsName;
        this.pbrdId = pbrdId;
        this.pbrdName = pbrdName;
        this.ptypeId = ptypeId;
        this.ptypeName = ptypeName;
        this.fitPtype = fitPtype;
        this.partsRemark = partsRemark;
        this.stateId = stateId;
        this.stateName = stateName;
        this.posId = posId;
        this.posName = posName;
        this.punitId = punitId;
        this.punitName = punitName;
        this.partsNowCount = partsNowCount;
        this.partsRepairPrice = partsRepairPrice;
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public String getPartsId()
    {
        return partsId;
    }

    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }

    public String getPartsId2()
    {
        return partsId2;
    }

    public void setPartsId2(String partsId2)
    {
        this.partsId2 = partsId2;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public String getPartsSimpleId()
    {
        return partsSimpleId;
    }

    public void setPartsSimpleId(String partsSimpleId)
    {
        this.partsSimpleId = partsSimpleId;
    }

    public String getFitPtype()
    {
        return fitPtype;
    }

    public void setFitPtype(String fitPtype)
    {
        this.fitPtype = fitPtype;
    }

    public String getPartsRemark()
    {
        return partsRemark;
    }

    public void setPartsRemark(String partsRemark)
    {
        this.partsRemark = partsRemark;
    }

    public Short getPtypeId()
    {
        return ptypeId;
    }

    public void setPtypeId(Short ptypeId)
    {
        this.ptypeId = ptypeId;
    }

    public String getPtypeName()
    {
        return ptypeName;
    }

    public void setPtypeName(String ptypeName)
    {
        this.ptypeName = ptypeName;
    }

    public Short getPbrdId()
    {
        return pbrdId;
    }

    public void setPbrdId(Short pbrdId)
    {
        this.pbrdId = pbrdId;
    }

    public String getPbrdName()
    {
        return pbrdName;
    }

    public void setPbrdName(String pbrdName)
    {
        this.pbrdName = pbrdName;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Short getStateId()
    {
        return stateId;
    }

    public void setStateId(Short stateId)
    {
        this.stateId = stateId;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    public Short getPosId()
    {
        return posId;
    }

    public void setPosId(Short posId)
    {
        this.posId = posId;
    }

    public String getPosName()
    {
        return posName;
    }

    public void setPosName(String posName)
    {
        this.posName = posName;
    }

    public Short getPunitId()
    {
        return punitId;
    }

    public void setPunitId(Short punitId)
    {
        this.punitId = punitId;
    }

    public String getPunitName()
    {
        return punitName;
    }

    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
    }

    public Integer getPartsNowCount()
    {
        return partsNowCount;
    }

    public void setPartsNowCount(Integer partsNowCount)
    {
        this.partsNowCount = partsNowCount;
    }

    public Double getPartsRepairPrice()
    {
        return partsRepairPrice;
    }

    public void setPartsRepairPrice(Double partsRepairPrice)
    {
        this.partsRepairPrice = partsRepairPrice;
    }

    public Short getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Short storeId)
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

}
