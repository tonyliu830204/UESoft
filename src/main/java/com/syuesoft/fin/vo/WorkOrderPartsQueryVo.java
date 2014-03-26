package com.syuesoft.fin.vo;

import com.syuesoft.base.vo.BaseBeanVo;

/**
 * 工单配件查询Vo
 * @author SuMing
 */
public class WorkOrderPartsQueryVo extends BaseBeanVo
{

    String q;
    short cbrd;
    private String receptionId;
    private String preclrTime;
    private String carLicense;
    private String ctypeName;
    private String stomId;
    private String stomDate;
    private String cerNo;
    private String partsId;
    private String partsName;
    private String punitName;
    private String partsLibrary;
    private String itemCount;
    private String itemPrice;
    private Double itemAmount;
    private String itemPriceAvage;
    private String taxCastamont;
    private String taxCastamontAvage;
    private String storeId;
    private String state;
    private String _parentId;
    private String preclrDateStart;
    private String preclrDateEnd;
    private String claimantpCount;
    private String claimantpAmonut;
    private String claimantpIndex;
    private String preclrTimeStart;
    private String preclrTimeEnd;
    private String preclrInoiceType;
    private String rcptBranch;
    private String customName;
    private String sortWay;
    private String claimsType;
    private String carBrandId;
    private String carTypeId;
    private String partsBrandId;
    private String reptId;
    private Integer enterpriseId;
    
   
	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getPreclrTimeStart()
    {
        return preclrTimeStart;
    }

    public void setPreclrTimeStart(String preclrTimeStart)
    {
        this.preclrTimeStart = preclrTimeStart;
    }

    public String getPreclrTimeEnd()
    {
        return preclrTimeEnd;
    }

    public void setPreclrTimeEnd(String preclrTimeEnd)
    {
        this.preclrTimeEnd = preclrTimeEnd;
    }

    public String getClaimantpIndex()
    {
        return claimantpIndex;
    }

    public void setClaimantpIndex(String claimantpIndex)
    {
        this.claimantpIndex = claimantpIndex;
    }

    public String getClaimantpCount()
    {
        return claimantpCount;
    }

    public void setClaimantpCount(String claimantpCount)
    {
        this.claimantpCount = claimantpCount;
    }

    public String getClaimantpAmonut()
    {
        return claimantpAmonut;
    }

    public void setClaimantpAmonut(String claimantpAmonut)
    {
        this.claimantpAmonut = claimantpAmonut;
    }

    public String getPreclrDateStart()
    {
        return preclrDateStart;
    }

    public void setPreclrDateStart(String preclrDateStart)
    {
        this.preclrDateStart = preclrDateStart;
    }

    public String getPreclrDateEnd()
    {
        return preclrDateEnd;
    }

    public void setPreclrDateEnd(String preclrDateEnd)
    {
        this.preclrDateEnd = preclrDateEnd;
    }

    public String get_parentId()
    {
        return _parentId;
    }

    public void set_parentId(String parentId)
    {
        _parentId = parentId;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getStomId()
    {
        return stomId;
    }

    public void setStomId(String stomId)
    {
        this.stomId = stomId;
    }

    public String getStomDate()
    {
        return stomDate;
    }

    public void setStomDate(String stomDate)
    {
        this.stomDate = stomDate;
    }

    public String getCerNo()
    {
        return cerNo;
    }

    public void setCerNo(String cerNo)
    {
        this.cerNo = cerNo;
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

    public String getPartsLibrary()
    {
        return partsLibrary;
    }

    public void setPartsLibrary(String partsLibrary)
    {
        this.partsLibrary = partsLibrary;
    }

    public String getItemCount()
    {
        return itemCount;
    }

    public void setItemCount(String itemCount)
    {
        this.itemCount = itemCount;
    }

    public String getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public String getItemPriceAvage()
    {
        return itemPriceAvage;
    }

    public void setItemPriceAvage(String itemPriceAvage)
    {
        this.itemPriceAvage = itemPriceAvage;
    }

    public String getTaxCastamont()
    {
        return taxCastamont;
    }

    public void setTaxCastamont(String taxCastamont)
    {
        this.taxCastamont = taxCastamont;
    }

    public String getTaxCastamontAvage()
    {
        return taxCastamontAvage;
    }

    public void setTaxCastamontAvage(String taxCastamontAvage)
    {
        this.taxCastamontAvage = taxCastamontAvage;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
    }

    public String getReceptionId()
    {
        return receptionId;
    }

    public void setReceptionId(String receptionId)
    {
        this.receptionId = receptionId;
    }

    public String getPreclrTime()
    {
        return preclrTime;
    }

    public void setPreclrTime(String preclrTime)
    {
        this.preclrTime = preclrTime;
    }

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
    }

    public String getCtypeName()
    {
        return ctypeName;
    }

    public void setCtypeName(String ctypeName)
    {
        this.ctypeName = ctypeName;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public short getCbrd()
    {
        return cbrd;
    }

    public void setCbrd(short cbrd)
    {
        this.cbrd = cbrd;
    }

	public String getPreclrInoiceType() {
		return preclrInoiceType;
	}

	public void setPreclrInoiceType(String preclrInoiceType) {
		this.preclrInoiceType = preclrInoiceType;
	}

	public String getRcptBranch() {
		return rcptBranch;
	}

	public void setRcptBranch(String rcptBranch) {
		this.rcptBranch = rcptBranch;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getSortWay() {
		return sortWay;
	}

	public void setSortWay(String sortWay) {
		this.sortWay = sortWay;
	}

	public String getClaimsType() {
		return claimsType;
	}

	public void setClaimsType(String claimsType) {
		this.claimsType = claimsType;
	}

	public String getCarBrandId() {
		return carBrandId;
	}

	public void setCarBrandId(String carBrandId) {
		this.carBrandId = carBrandId;
	}

	public String getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getPartsBrandId() {
		return partsBrandId;
	}

	public void setPartsBrandId(String partsBrandId) {
		this.partsBrandId = partsBrandId;
	}

	public String getReptId() {
		return reptId;
	}

	public void setReptId(String reptId) {
		this.reptId = reptId;
	}

	public Double getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}

	

}
