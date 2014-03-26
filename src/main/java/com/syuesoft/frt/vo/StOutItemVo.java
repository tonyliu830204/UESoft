package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class StOutItemVo extends BaseBeanVo
{

    private Integer indexId;

    private String stomId;

    private String partsId;

    private Double itemPrice;

    private Integer itemCount;

    private Short itemCharge;

    private Short discount;

    private Short storeId;

    private Short claimsType;

    private String outItemRemark;

    private Double notaxCastamont;

    private Double taxCastamont;

    private Double taxCast;

    private Double notaxCast;

    private Double amount;

    private String partsName;

    public StOutItemVo(Integer indexId, String stomId, String partsId,
            Double itemPrice, Integer itemCount, Short itemCharge,
            Short discount, Short storeId, Short claimsType,
            String outItemRemark, Double notaxCastamont, Double taxCastamont,
            Double taxCast, Double notaxCast, Double amount, String partsName)
    {
        this.indexId = indexId;
        this.stomId = stomId;
        this.partsId = partsId;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.itemCharge = itemCharge;
        this.discount = discount;
        this.storeId = storeId;
        this.claimsType = claimsType;
        this.outItemRemark = outItemRemark;
        this.notaxCastamont = notaxCastamont;
        this.taxCastamont = taxCastamont;
        this.taxCast = taxCast;
        this.notaxCast = notaxCast;
        this.amount = amount;
        this.partsName = partsName;
    }

    public Integer getIndexId()
    {
        return indexId;
    }

    public void setIndexId(Integer indexId)
    {
        this.indexId = indexId;
    }

    public String getStomId()
    {
        return stomId;
    }

    public void setStomId(String stomId)
    {
        this.stomId = stomId;
    }

    public String getPartsId()
    {
        return partsId;
    }

    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }

    public Double getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public Integer getItemCount()
    {
        return itemCount;
    }

    public void setItemCount(Integer itemCount)
    {
        this.itemCount = itemCount;
    }

    public Short getItemCharge()
    {
        return itemCharge;
    }

    public void setItemCharge(Short itemCharge)
    {
        this.itemCharge = itemCharge;
    }

    public Short getDiscount()
    {
        return discount;
    }

    public void setDiscount(Short discount)
    {
        this.discount = discount;
    }

    public Short getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Short storeId)
    {
        this.storeId = storeId;
    }

    public Short getClaimsType()
    {
        return claimsType;
    }

    public void setClaimsType(Short claimsType)
    {
        this.claimsType = claimsType;
    }

    public String getOutItemRemark()
    {
        return outItemRemark;
    }

    public void setOutItemRemark(String outItemRemark)
    {
        this.outItemRemark = outItemRemark;
    }

    public Double getNotaxCastamont()
    {
        return notaxCastamont;
    }

    public void setNotaxCastamont(Double notaxCastamont)
    {
        this.notaxCastamont = notaxCastamont;
    }

    public Double getTaxCastamont()
    {
        return taxCastamont;
    }

    public void setTaxCastamont(Double taxCastamont)
    {
        this.taxCastamont = taxCastamont;
    }

    public Double getTaxCast()
    {
        return taxCast;
    }

    public void setTaxCast(Double taxCast)
    {
        this.taxCast = taxCast;
    }

    public Double getNotaxCast()
    {
        return notaxCast;
    }

    public void setNotaxCast(Double notaxCast)
    {
        this.notaxCast = notaxCast;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

}
