package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FrtPartsVo extends BaseBeanVo
{

    private String partsId;

    private String partsId2;

    private String partsName;

    private String partsSimpleId;

    private String fitPtype;

    private String posName;

    private String punitName;

    private String stateName;

    private String partsLibrary;				//配件库位

    private String partsRemark;

    private Integer partsNeedPoint;

    private Boolean partsFlag;

    private String fitPtypeName;

    private Boolean isStock;

    private Boolean translationFlag;

    private Short ptypeId;

    private String ptypeName;

    private Short pbrdId;

    private String pbrdName;

    private String changedPartsId;

    private String storeId;

    private String q;

    private Integer changePriceId;

    private String posName2;

    private String punitName2;

    private String stateName2;

    private Double partsNowCount;

    private String storeName;

    private Double partsRepairPrice;

    private Double partsSellPrice;

    private Double partsPointPrice;

    private Double partsSpecialPrice;

    private Double partsClaimantPrice;

    private Double partsLatestTaxprice;

    private Double partsLatestNotaxprice;

    private Short punitId;

    private Double stockUpper;

    private Double stockLower;

    private Short relcampId;

    private String relcampName;
    
    
    private String partsProperty;				//配件属性
	private String partsAge;					//配件年份
	private String partsSpecs;					//配件规格
	
	private String importData;
    
    private String param;
    
    private Boolean flag;
    
    private String type;
    
    private String paramPartId;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public Short getRelcampId()
    {
        return relcampId;
    }

    public void setRelcampId(Short relcampId)
    {
        this.relcampId = relcampId;
    }

    public String getRelcampName()
    {
        return relcampName;
    }

    public void setRelcampName(String relcampName)
    {
        this.relcampName = relcampName;
    }

    public FrtPartsVo()
    {

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

    public String getPosName()
    {
        return posName;
    }

    public void setPosName(String posName)
    {
        this.posName = posName;
    }

    public String getPunitName()
    {
        return punitName;
    }

    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    public String getPartsLibrary()
    {
        return partsLibrary;
    }

    public void setPartsLibrary(String partsLibrary)
    {
        this.partsLibrary = partsLibrary;
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

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public Integer getPartsNeedPoint()
    {
        return partsNeedPoint;
    }

    public void setPartsNeedPoint(Integer partsNeedPoint)
    {
        this.partsNeedPoint = partsNeedPoint;
    }

    public String getPosName2()
    {
        return posName2;
    }

    public void setPosName2(String posName2)
    {
        this.posName2 = posName2;
    }

    public String getPunitName2()
    {
        return punitName2;
    }

    public void setPunitName2(String punitName2)
    {
        this.punitName2 = punitName2;
    }

    public String getStateName2()
    {
        return stateName2;
    }

    public void setStateName2(String stateName2)
    {
        this.stateName2 = stateName2;
    }

   

    public Boolean getPartsFlag() {
		return partsFlag;
	}

	public void setPartsFlag(Boolean partsFlag) {
		this.partsFlag = partsFlag;
	}

	public String getChangedPartsId()
    {
        return changedPartsId;
    }

    public void setChangedPartsId(String changedPartsId)
    {
        this.changedPartsId = changedPartsId;
    }

    public Double getPartsNowCount()
    {
        return partsNowCount;
    }

    public void setPartsNowCount(Double partsNowCount)
    {
        this.partsNowCount = partsNowCount;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public Double getPartsRepairPrice()
    {
        return partsRepairPrice;
    }

    public void setPartsRepairPrice(Double partsRepairPrice)
    {
        this.partsRepairPrice = partsRepairPrice;
    }

    public Double getPartsSellPrice()
    {
        return partsSellPrice;
    }

    public void setPartsSellPrice(Double partsSellPrice)
    {
        this.partsSellPrice = partsSellPrice;
    }

    public Double getPartsPointPrice()
    {
        return partsPointPrice;
    }

    public void setPartsPointPrice(Double partsPointPrice)
    {
        this.partsPointPrice = partsPointPrice;
    }

    public Double getPartsSpecialPrice()
    {
        return partsSpecialPrice;
    }

    public void setPartsSpecialPrice(Double partsSpecialPrice)
    {
        this.partsSpecialPrice = partsSpecialPrice;
    }

    public Double getPartsClaimantPrice()
    {
        return partsClaimantPrice;
    }

    public void setPartsClaimantPrice(Double partsClaimantPrice)
    {
        this.partsClaimantPrice = partsClaimantPrice;
    }

    public Double getPartsLatestTaxprice()
    {
        return partsLatestTaxprice;
    }

    public void setPartsLatestTaxprice(Double partsLatestTaxprice)
    {
        this.partsLatestTaxprice = partsLatestTaxprice;
    }

    public Double getPartsLatestNotaxprice()
    {
        return partsLatestNotaxprice;
    }

    public void setPartsLatestNotaxprice(Double partsLatestNotaxprice)
    {
        this.partsLatestNotaxprice = partsLatestNotaxprice;
    }

    public Double getStockUpper()
    {
        return stockUpper;
    }

    public void setStockUpper(Double stockUpper)
    {
        this.stockUpper = stockUpper;
    }

    public Double getStockLower()
    {
        return stockLower;
    }

    public void setStockLower(Double stockLower)
    {
        this.stockLower = stockLower;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
    }

    public Short getPunitId()
    {
        return punitId;
    }

    public void setPunitId(Short punitId)
    {
        this.punitId = punitId;
    }

    public Boolean getIsStock()
    {
        return isStock;
    }

    public void setIsStock(Boolean isStock)
    {
        this.isStock = isStock;
    }

    public String getFitPtypeName()
    {
        return fitPtypeName;
    }

    public void setFitPtypeName(String fitPtypeName)
    {
        this.fitPtypeName = fitPtypeName;
    }

    public Integer getChangePriceId()
    {
        return changePriceId;
    }

    public void setChangePriceId(Integer changePriceId)
    {
        this.changePriceId = changePriceId;
    }

    public Boolean getTranslationFlag()
    {
        return translationFlag;
    }

    public void setTranslationFlag(Boolean translationFlag)
    {
        this.translationFlag = translationFlag;
    }

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getPartsProperty() {
		return partsProperty;
	}

	public void setPartsProperty(String partsProperty) {
		this.partsProperty = partsProperty;
	}

	public String getPartsAge() {
		return partsAge;
	}

	public void setPartsAge(String partsAge) {
		this.partsAge = partsAge;
	}

	public String getPartsSpecs() {
		return partsSpecs;
	}

	public void setPartsSpecs(String partsSpecs) {
		this.partsSpecs = partsSpecs;
	}

	public String getImportData() {
		return importData;
	}

	public void setImportData(String importData) {
		this.importData = importData;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getParamPartId() {
		return paramPartId;
	}

	public void setParamPartId(String paramPartId) {
		this.paramPartId = paramPartId;
	}

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}