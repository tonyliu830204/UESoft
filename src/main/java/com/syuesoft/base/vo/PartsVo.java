package com.syuesoft.base.vo;

import com.syuesoft.model.FrtPreClearing;

public class PartsVo
{

    private String id;

    private String partsId;

    private String partsName;

    private Short punitId;

    private String punitName;

    private Double partsNum;

    private Double partsRepairPrice;

    private Double partsAmount;

    private Short storeId;

    private String storeName;

    private Short progress;// 出料进度

    // 前台接车
    private Short reptypId;

    private String reptypName;

    private Short claimsId;

    private String claimsName;

    private String partsRemark;

    // 交车结算
    private Integer partsIndex;

    private Double partsRate;

    private Double partsRateAmount;

    private Double partsCount;

    private Double stinvdPrice;

    private Double partsPrice;

    private Short relcampId;

    private Double settlementDiscount;
    
    //索赔
    private Double partsTaxCost;
    
	private Double partsNoTaxCost;

    public PartsVo()
    {

    }

    /**
     * 维修套餐-查询已选配件
     * 
     * @param id
     * @param partsId
     * @param partsName
     * @param punitId
     * @param punitName
     * @param partsNum
     * @param partsRepairPrice
     * @param partsAmount
     * @param storeId
     * @param storeName
     */
    public PartsVo(String id, String partsId, String partsName, Short punitId,
            String punitName, Double partsNum, Double partsRepairPrice,
            Double partsAmount, Short storeId, String storeName)
    {
        this.id = id;
        this.partsId = partsId;
        this.partsName = partsName;
        this.punitId = punitId;
        this.punitName = punitName;
        this.partsNum = partsNum;
        this.partsRepairPrice = partsRepairPrice;
        this.partsAmount = partsAmount;
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public PartsVo(String id, String partsId, String partsName, Short punitId,
            String punitName, Double partsNum, Double partsRepairPrice,
            Double partsAmount, Short storeId, String storeName,
            Short reptypeId, String reptypeName, Short claimsId,
            String claimsName, String partsRemark)
    {
        this.id = id;
        this.partsId = partsId;
        this.partsName = partsName;
        this.punitId = punitId;
        this.punitName = punitName;
        this.partsNum = partsNum;
        this.partsRepairPrice = partsRepairPrice;
        this.partsAmount = partsAmount;
        this.storeId = storeId;
        this.storeName = storeName;
        this.reptypId = reptypId;
        this.reptypName = reptypName;
        this.claimsId = claimsId;
        this.claimsName = claimsName;
        this.partsRemark = partsRemark;
    }

    public String getPartsId()
    {
        return partsId;
    }

    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }

    public Short getPunitId()
    {
        return punitId;
    }

    public void setPunitId(Short punitId)
    {
        this.punitId = punitId;
    }

    public void setPunitId(String punitId)
    {
        if (punitId != null && (!"".equals(punitId)))
            this.punitId = Short.parseShort(punitId);
    }

    public Double getPartsNum()
    {
        return partsNum;
    }

    public void setPartsNum(Double partsNum)
    {
        this.partsNum = partsNum;
    }

    public Double getPartsRepairPrice()
    {
        return partsRepairPrice;
    }

    public void setPartsRepairPrice(Double partsRepairPrice)
    {
        this.partsRepairPrice = partsRepairPrice;
    }

    public Double getPartsAmount()
    {
        return partsAmount;
    }

    public void setPartsAmount(Double partsAmount)
    {
        this.partsAmount = partsAmount;
    }

    public Short getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Short storeId)
    {
        this.storeId = storeId;
    }

    public void setStoreId(String storeId)
    {
        if (storeId != null && (!"".equals(storeId)))
            this.storeId = Short.parseShort(storeId);
    }

    public String getPunitName()
    {
        return punitName;
    }

    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Short getReptypId()
    {
        return reptypId;
    }

    public void setReptypId(Short reptypId)
    {
        this.reptypId = reptypId;
    }

    public void setReptypId(String reptypId)
    {
        if (reptypId != null && (!"".equals(reptypId)))
            this.reptypId = Short.parseShort(reptypId);
    }

    public String getReptypName()
    {
        return reptypName;
    }

    public void setReptypName(String reptypName)
    {
        this.reptypName = reptypName;
    }

    public Short getClaimsId()
    {
        return claimsId;
    }

    public void setClaimsId(Short claimsId)
    {
        this.claimsId = claimsId;
    }

    public void setClaimsId(String claimsId)
    {
        if (claimsId != null && (!"".equals(claimsId)))
            this.claimsId = Short.parseShort(claimsId);
    }

    public String getClaimsName()
    {
        return claimsName;
    }

    public void setClaimsName(String claimsName)
    {
        this.claimsName = claimsName;
    }

    public String getPartsRemark()
    {
        return partsRemark;
    }

    public void setPartsRemark(String partsRemark)
    {
        this.partsRemark = partsRemark;
    }

    public Double getPartsCount()
    {
        return partsCount;
    }

    public void setPartsCount(Double partsCount)
    {
        this.partsCount = partsCount;
    }

    public Double getStinvdPrice()
    {
        return stinvdPrice;
    }

    public void setStinvdPrice(Double stinvdPrice)
    {
        this.stinvdPrice = stinvdPrice;
    }

    public Double getPartsPrice()
    {
        return partsPrice;
    }

    public void setPartsPrice(Double partsPrice)
    {
        this.partsPrice = partsPrice;
    }

    public Short getRelcampId()
    {
        return relcampId;
    }

    public void setRelcampId(Short relcampId)
    {
        this.relcampId = relcampId;
    }

    public Double getSettlementDiscount()
    {
        return settlementDiscount;
    }

    public void setSettlementDiscount(Double settlementDiscount)
    {
        this.settlementDiscount = settlementDiscount;
    }

    public Short getProgress()
    {
        return progress;
    }

    public void setProgress(Short progress)
    {
        this.progress = progress;
    }

    public Double getPartsRate()
    {
        return partsRate;
    }

    public void setPartsRate(Double partsRate)
    {
        this.partsRate = partsRate;
    }

    public Double getPartsRateAmount()
    {
        return partsRateAmount;
    }

    public void setPartsRateAmount(Double partsRateAmount)
    {
        this.partsRateAmount = partsRateAmount;
    }

    public Integer getPartsIndex()
    {
        return partsIndex;
    }

    public void setPartsIndex(Integer partsIndex)
    {
        this.partsIndex = partsIndex;
    }

	public Double getPartsTaxCost() {
		return partsTaxCost;
	}

	public void setPartsTaxCost(Double partsTaxCost) {
		this.partsTaxCost = partsTaxCost;
	}

	public Double getPartsNoTaxCost() {
		return partsNoTaxCost;
	}

	public void setPartsNoTaxCost(Double partsNoTaxCost) {
		this.partsNoTaxCost = partsNoTaxCost;
	}

}
