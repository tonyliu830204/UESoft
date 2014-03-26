package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FrtWorkOrderClaimPartsVo extends BaseBeanVo
{
    private String partsCode;

    private String partsName;

    private Double partsCount;

    private Double claimantAmount;

    private Double claimantTaxCost;

    private Double claimantNoTaxCost;

    private String depotLocation;

    public FrtWorkOrderClaimPartsVo()
    {
        // TODO Auto-generated constructor stub
    }

    public String getPartsCode()
    {
        return partsCode;
    }

    public void setPartsCode(String partsCode)
    {
        this.partsCode = partsCode;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public Double getPartsCount()
    {
        return partsCount;
    }

    public void setPartsCount(Double partsCount)
    {
        this.partsCount = partsCount;
    }

    public Double getClaimantAmount()
    {
        return claimantAmount;
    }

    public void setClaimantAmount(Double claimantAmount)
    {
        this.claimantAmount = claimantAmount;
    }

    public Double getClaimantTaxCost()
    {
        return claimantTaxCost;
    }

    public void setClaimantTaxCost(Double claimantTaxCost)
    {
        this.claimantTaxCost = claimantTaxCost;
    }

    public Double getClaimantNoTaxCost()
    {
        return claimantNoTaxCost;
    }

    public void setClaimantNoTaxCost(Double claimantNoTaxCost)
    {
        this.claimantNoTaxCost = claimantNoTaxCost;
    }

    public String getDepotLocation()
    {
        return depotLocation;
    }

    public void setDepotLocation(String depotLocation)
    {
        this.depotLocation = depotLocation;
    }

}
