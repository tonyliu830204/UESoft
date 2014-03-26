package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FrtWorkOrderSettlementSituationPartsVo extends BaseBeanVo
{
    private String carPartName;

    private String fitPtype;

    private String partsId;

    private String partsName;

    private String punitName;

    private Double partsCount;

    private Double partsPrice;

    private Double partsAmount;

    private Boolean partsFlag;

    private String claimsName;

    public String getCarPartName()
    {
        return carPartName;
    }

    public void setCarPartName(String carPartName)
    {
        this.carPartName = carPartName;
    }

    public String getFitPtype()
    {
        return fitPtype;
    }

    public void setFitPtype(String fitPtype)
    {
        this.fitPtype = fitPtype;
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

    public String getPunitName()
    {
        return punitName;
    }

    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
    }

    public Double getPartsCount()
    {
        return partsCount;
    }

    public void setPartsCount(Double partsCount)
    {
        this.partsCount = partsCount;
    }

    public Double getPartsPrice()
    {
        return partsPrice;
    }

    public void setPartsPrice(Double partsPrice)
    {
        this.partsPrice = partsPrice;
    }

    public Double getPartsAmount()
    {
        return partsAmount;
    }

    public void setPartsAmount(Double partsAmount)
    {
        this.partsAmount = partsAmount;
    }

    public Boolean getPartsFlag() {
		return partsFlag;
	}

	public void setPartsFlag(Boolean partsFlag) {
		this.partsFlag = partsFlag;
	}

	public String getClaimsName()
    {
        return claimsName;
    }

    public void setClaimsName(String claimsName)
    {
        this.claimsName = claimsName;
    }

}
