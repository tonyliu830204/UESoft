package com.syuesoft.base.vo;

public class BasRepairPackageVo
{

    private String id;

    private String partsId;

    private String partsId2;

    private String partsName;

    private Short pbrdId;

    private String pbrdName;

    private Short ptypeId;

    private String ptypeName;

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

    private String partsSimpleId;

    private Integer partsNum;

    private Double partsAmount;

    private String partsRemark;

    private String repitemId;

    private String repitemName;

    private Double repitemAmount; // 工时单价

    private Double repitemCost; // 工时费

    private Integer repitemNum;

    private Short rpId;

    private String rpName;

    private Double itemTimeAmount;

    private Double rpAmount;

    private String applicableBrands;

    private String applicableBrandsName;

    private String fitCar;

    private Short repitemSeries;

    private String repitemSeriesName;

    private String fitCarName;

    private String repitemCode;

    private Double repitemTime;

    private Double internalTime;

    private Double claimTime;

    private String repitemRemark;

    private String parts;

    private String items;

    private String inserted;

    private String updated;

    private String deleted;

    private String q;

    private String sort;

    private String order;

    private int page;

    private int rows;

    public BasRepairPackageVo()
    {

    }

    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    // 维修套餐->选择计划用料
    public BasRepairPackageVo(String id, String partsId, String partsId2,
            String partsName, Short pbrdId, String pbrdName, Short ptypeId,
            String ptypeName, Short stateId, String stateName, Short posId,
            String posName, Short punitId, String punitName,
            Integer partsNowCount, Double partsRepairPrice, Short storeId,
            String storeName, String partsRemark)
    {
        this.id = id;
        this.partsId = partsId;
        this.partsId2 = partsId2;
        this.partsName = partsName;
        this.pbrdId = pbrdId;
        this.pbrdName = pbrdName;
        this.ptypeId = ptypeId;
        this.ptypeName = ptypeName;
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
        this.partsRemark = partsRemark;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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

    public Integer getPartsNum()
    {
        return partsNum;
    }

    public void setPartsNum(Integer partsNum)
    {
        this.partsNum = partsNum;
    }

    public Double getPartsAmount()
    {
        return partsAmount;
    }

    public void setPartsAmount(Double partsAmount)
    {
        this.partsAmount = partsAmount;
    }

    public String getRepitemId()
    {
        return repitemId;
    }

    public void setRepitemId(String repitemId)
    {
        this.repitemId = repitemId;
    }

    public String getRepitemName()
    {
        return repitemName;
    }

    public void setRepitemName(String repitemName)
    {
        this.repitemName = repitemName;
    }

    public Double getRepitemAmount()
    {
        return repitemAmount;
    }

    public void setRepitemAmount(Double repitemAmount)
    {
        this.repitemAmount = repitemAmount;
    }

    public Double getRepitemCost()
    {
        return repitemCost;
    }

    public void setRepitemCost(Double repitemCost)
    {
        this.repitemCost = repitemCost;
    }

    public String getPartsSimpleId()
    {
        return partsSimpleId;
    }

    public void setPartsSimpleId(String partsSimpleId)
    {
        this.partsSimpleId = partsSimpleId;
    }

    public String getInserted()
    {
        return inserted;
    }

    public void setInserted(String inserted)
    {
        this.inserted = inserted;
    }

    public String getUpdated()
    {
        return updated;
    }

    public void setUpdated(String updated)
    {
        this.updated = updated;
    }

    public String getDeleted()
    {
        return deleted;
    }

    public void setDeleted(String deleted)
    {
        this.deleted = deleted;
    }

    public Short getRpId()
    {
        return rpId;
    }

    public void setRpId(Short rpId)
    {
        this.rpId = rpId;
    }

    public String getRpName()
    {
        return rpName;
    }

    public void setRpName(String rpName)
    {
        this.rpName = rpName;
    }

    public Double getItemTimeAmount()
    {
        return itemTimeAmount;
    }

    public void setItemTimeAmount(Double itemTimeAmount)
    {
        this.itemTimeAmount = itemTimeAmount;
    }

    public Double getRpAmount()
    {
        return rpAmount;
    }

    public void setRpAmount(Double rpAmount)
    {
        this.rpAmount = rpAmount;
    }

    public String getApplicableBrands()
    {
        return applicableBrands;
    }

    public void setApplicableBrands(String applicableBrands)
    {
        this.applicableBrands = applicableBrands;
    }

    public Integer getRepitemNum()
    {
        return repitemNum;
    }

    public void setRepitemNum(Integer repitemNum)
    {
        this.repitemNum = repitemNum;
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

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public String getFitCar()
    {
        return fitCar;
    }

    public void setFitCar(String fitCar)
    {
        this.fitCar = fitCar;
    }

    public String getPartsRemark()
    {
        return partsRemark;
    }

    public void setPartsRemark(String partsRemark)
    {
        this.partsRemark = partsRemark;
    }

    public Short getRepitemSeries()
    {
        return repitemSeries;
    }

    public void setRepitemSeries(Short repitemSeries)
    {
        this.repitemSeries = repitemSeries;
    }

    public String getRepitemCode()
    {
        return repitemCode;
    }

    public void setRepitemCode(String repitemCode)
    {
        this.repitemCode = repitemCode;
    }

    public Double getRepitemTime()
    {
        return repitemTime;
    }

    public void setRepitemTime(Double repitemTime)
    {
        this.repitemTime = repitemTime;
    }

    public Double getInternalTime()
    {
        return internalTime;
    }

    public void setInternalTime(Double internalTime)
    {
        this.internalTime = internalTime;
    }

    public Double getClaimTime()
    {
        return claimTime;
    }

    public void setClaimTime(Double claimTime)
    {
        this.claimTime = claimTime;
    }

    public String getRepitemRemark()
    {
        return repitemRemark;
    }

    public void setRepitemRemark(String repitemRemark)
    {
        this.repitemRemark = repitemRemark;
    }

    public String getRepitemSeriesName()
    {
        return repitemSeriesName;
    }

    public void setRepitemSeriesName(String repitemSeriesName)
    {
        this.repitemSeriesName = repitemSeriesName;
    }

    public String getParts()
    {
        return parts;
    }

    public void setParts(String parts)
    {
        this.parts = parts;
    }

    public String getItems()
    {
        return items;
    }

    public void setItems(String items)
    {
        this.items = items;
    }

    public String getApplicableBrandsName()
    {
        return applicableBrandsName;
    }

    public void setApplicableBrandsName(String applicableBrandsName)
    {
        this.applicableBrandsName = applicableBrandsName;
    }

    public String getFitCarName()
    {
        return fitCarName;
    }

    public void setFitCarName(String fitCarName)
    {
        this.fitCarName = fitCarName;
    }

}
