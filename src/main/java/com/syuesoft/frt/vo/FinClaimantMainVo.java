package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FinClaimantMainVo extends BaseBeanVo
{

    private String claimantmId;

    private String preclrId;

    private String claimantmTime;

    private String receptionId;

    private String claimantmInvoiceType;

    private String claimantmInvoiceTime;

    private String claimantmInvoiceNo;

    private Short claimantmClrStfId;

    private Double claimantmPartsAmount;

    private Double claimantmTimeAmount;

    private Double claimantmManagementFee;

    private Double claimantmOtherAmount;

    private Double claimantmAmount;
    
    private Double claimantmNoTaxCost;
    
	private Double claimantmTaxCost;

	/*******索赔成本分析*****/
	private Double preclrNoTaxCost;
    
	private Double preclrTaxCost;
	
	private Double differenceNoTaxCost;
    
	private Double differenceTaxCost;
	/************/
	
    private String claimantmStatus;

    private Short claimantmCheckStfId;

    private String claimantmRemark;

    private Short claimantmToMoney;
    
    private String moneyStatusName;
    
    private String claimantmInvoiceTypeName;

    private String claimantmStatusName;

    private String claimantmCheckStfName;

    private String claimantmClrStfName;

    private Integer claimantpIndex;

    private String partsId;

    private String partsName;

    private Double claimantpCount;

    private Double claimantpPrice;

    private Double claimantpAmount;

    private Integer claimanttIndex;

    private String repitemId;

    private String repitemName;

    private Double claimanttTime;

    private Double claimanttAmount;

    private Short relcampId;

    private Short reptypId;

    private Short stfId;

    private Short costId;

    private String costName;

    private Double costAmount;

    private String costExplain;

    private String claimantmTimeBegin;

    private String claimantmTimeEnd;

    private String claimantmInvoiceTimeBegin;

    private String claimantmInvoiceTimeEnd;

    private String parts;

    private String items;

    private String others;

    // 未确认索理赔结算单
    private String preclrTime;

    private String carId;

    private String carLicense;

    private String carVin;

    private String carMotorId;

    private String customName;

    private String relcampName;

    private Integer receptionDistance;

    private String stfName;

    private Double claimantMainCost;

    private Boolean flag;

    private String q;

    private String sort;

    private String order;

    private int page;

    private int rows;
    
    private String state;                                                     //treegrid打开还是关闭
	private String iconCls; 
	private String _parentId;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
    public FinClaimantMainVo()
    {

    }

    public FinClaimantMainVo(String claimantmId, Integer claimantpIndex,
            String partsId, String partsName, Double claimantpCount,
            Short relcampId, Double claimantpPrice, Double claimantpAmount,
            Short reptypId)
    {
        this.claimantmId = claimantmId;
        this.claimantpIndex = claimantpIndex;
        this.partsId = partsId;
        this.partsName = partsName;
        this.claimantpCount = claimantpCount;
        this.relcampId = relcampId;
        this.claimantpPrice = claimantpPrice;
        this.claimantpAmount = claimantpAmount;
        this.reptypId = reptypId;
    }

    public FinClaimantMainVo(String claimantmId, Integer claimanttIndex,
            String repitemId, String repitemName, Double claimanttTime,
            Double claimanttAmount, Short relcampId, Short reptypId, Short stfId)
    {
        this.claimantmId = claimantmId;
        this.claimanttIndex = claimanttIndex;
        this.repitemId = repitemId;
        this.repitemName = repitemName;
        this.claimanttTime = claimanttTime;
        this.claimanttAmount = claimanttAmount;
        this.relcampId = relcampId;
        this.reptypId = reptypId;
        this.stfId = stfId;
    }

    public FinClaimantMainVo(String claimantmId, String claimantmTime,
            String receptionId, String carLicense, String carVin,
            String carMotorId, String customName, String claimantmInvoiceType,
            String claimantmInvoiceTime, String claimantmInvoiceNo,
            Short claimantmClrStfId, Double claimantmPartsAmount,
            Double claimantmTimeAmount, Double claimantmAmount,
            String claimantmStatus, Short claimantmCheckStfId,
            String claimantmRemark)
    {
        this.claimantmId = claimantmId;
        this.claimantmTime = claimantmTime;
        this.receptionId = receptionId;
        this.carLicense = carLicense;
        this.carVin = carVin;
        this.carMotorId = carMotorId;
        this.customName = customName;
        this.claimantmInvoiceType = claimantmInvoiceType;
        this.claimantmInvoiceTime = claimantmInvoiceTime;
        this.claimantmInvoiceNo = claimantmInvoiceNo;
        this.claimantmClrStfId = claimantmClrStfId;
        this.claimantmPartsAmount = claimantmPartsAmount;
        this.claimantmTimeAmount = claimantmTimeAmount;
        this.claimantmAmount = claimantmAmount;
        this.claimantmStatus = claimantmStatus;
        this.claimantmCheckStfId = claimantmCheckStfId;
        this.claimantmRemark = claimantmRemark;
    }

    public String getClaimantmId()
    {
        return claimantmId;
    }

    public void setClaimantmId(String claimantmId)
    {
        this.claimantmId = claimantmId;
    }

    public String getPreclrId()
    {
        return preclrId;
    }

    public void setPreclrId(String preclrId)
    {
        this.preclrId = preclrId;
    }

    public String getClaimantmTime()
    {
        return claimantmTime;
    }

    public void setClaimantmTime(String claimantmTime)
    {
        this.claimantmTime = claimantmTime;
    }

    public String getReceptionId()
    {
        return receptionId;
    }

    public void setReceptionId(String receptionId)
    {
        this.receptionId = receptionId;
    }

    public String getClaimantmInvoiceType()
    {
        return claimantmInvoiceType;
    }

    public void setClaimantmInvoiceType(String claimantmInvoiceType)
    {
        this.claimantmInvoiceType = claimantmInvoiceType;
    }

    public String getClaimantmInvoiceTime()
    {
        return claimantmInvoiceTime;
    }

    public void setClaimantmInvoiceTime(String claimantmInvoiceTime)
    {
        this.claimantmInvoiceTime = claimantmInvoiceTime;
    }

    public String getClaimantmInvoiceNo()
    {
        return claimantmInvoiceNo;
    }

    public void setClaimantmInvoiceNo(String claimantmInvoiceNo)
    {
        this.claimantmInvoiceNo = claimantmInvoiceNo;
    }

    public Short getClaimantmClrStfId()
    {
        return claimantmClrStfId;
    }

    public void setClaimantmClrStfId(Short claimantmClrStfId)
    {
        this.claimantmClrStfId = claimantmClrStfId;
    }

    public Double getClaimantmPartsAmount()
    {
        return claimantmPartsAmount;
    }

    public void setClaimantmPartsAmount(Double claimantmPartsAmount)
    {
        this.claimantmPartsAmount = claimantmPartsAmount;
    }

    public Double getClaimantmTimeAmount()
    {
        return claimantmTimeAmount;
    }

    public void setClaimantmTimeAmount(Double claimantmTimeAmount)
    {
        this.claimantmTimeAmount = claimantmTimeAmount;
    }

    public Double getClaimantmAmount()
    {
        return claimantmAmount;
    }

    public void setClaimantmAmount(Double claimantmAmount)
    {
        this.claimantmAmount = claimantmAmount;
    }

    public String getClaimantmStatus()
    {
        return claimantmStatus;
    }

    public void setClaimantmStatus(String claimantmStatus)
    {
        this.claimantmStatus = claimantmStatus;
    }

    public Short getClaimantmCheckStfId()
    {
        return claimantmCheckStfId;
    }

    public void setClaimantmCheckStfId(Short claimantmCheckStfId)
    {
        this.claimantmCheckStfId = claimantmCheckStfId;
    }

    public String getClaimantmRemark()
    {
        return claimantmRemark;
    }

    public void setClaimantmRemark(String claimantmRemark)
    {
        this.claimantmRemark = claimantmRemark;
    }

    public Short getClaimantmToMoney()
    {
        return claimantmToMoney;
    }

    public void setClaimantmToMoney(Short claimantmToMoney)
    {
        this.claimantmToMoney = claimantmToMoney;
    }

    public String getClaimantmInvoiceTypeName()
    {
        return claimantmInvoiceTypeName;
    }

    public void setClaimantmInvoiceTypeName(String claimantmInvoiceTypeName)
    {
        this.claimantmInvoiceTypeName = claimantmInvoiceTypeName;
    }
    
    public String getMoneyStatusName()
    {
        return moneyStatusName;
    }

    public void setMoneyStatusName(String moneyStatusName)
    {
        this.moneyStatusName = moneyStatusName;
    }

    public String getClaimantmStatusName()
    {
        return claimantmStatusName;
    }

    public void setClaimantmStatusName(String claimantmStatusName)
    {
        this.claimantmStatusName = claimantmStatusName;
    }

    public String getClaimantmCheckStfName()
    {
        return claimantmCheckStfName;
    }

    public void setClaimantmCheckStfName(String claimantmCheckStfName)
    {
        this.claimantmCheckStfName = claimantmCheckStfName;
    }

    public String getClaimantmClrStfName()
    {
        return claimantmClrStfName;
    }

    public void setClaimantmClrStfName(String claimantmClrStfName)
    {
        this.claimantmClrStfName = claimantmClrStfName;
    }

    public Integer getClaimantpIndex()
    {
        return claimantpIndex;
    }

    public void setClaimantpIndex(Integer claimantpIndex)
    {
        this.claimantpIndex = claimantpIndex;
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

    public Double getClaimantpCount()
    {
        return claimantpCount;
    }

    public void setClaimantpCount(Double claimantpCount)
    {
        this.claimantpCount = claimantpCount;
    }

    public Double getClaimantpPrice()
    {
        return claimantpPrice;
    }

    public void setClaimantpPrice(Double claimantpPrice)
    {
        this.claimantpPrice = claimantpPrice;
    }

    public Double getClaimantpAmount()
    {
        return claimantpAmount;
    }

    public void setClaimantpAmount(Double claimantpAmount)
    {
        this.claimantpAmount = claimantpAmount;
    }

    public Integer getClaimanttIndex()
    {
        return claimanttIndex;
    }

    public void setClaimanttIndex(Integer claimanttIndex)
    {
        this.claimanttIndex = claimanttIndex;
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

    public Double getClaimanttTime()
    {
        return claimanttTime;
    }

    public void setClaimanttTime(Double claimanttTime)
    {
        this.claimanttTime = claimanttTime;
    }

    public Double getClaimanttAmount()
    {
        return claimanttAmount;
    }

    public void setClaimanttAmount(Double claimanttAmount)
    {
        this.claimanttAmount = claimanttAmount;
    }

    public Short getRelcampId()
    {
        return relcampId;
    }

    public void setRelcampId(Short relcampId)
    {
        this.relcampId = relcampId;
    }

    public Short getReptypId()
    {
        return reptypId;
    }

    public void setReptypId(Short reptypId)
    {
        this.reptypId = reptypId;
    }

    public Short getStfId()
    {
        return stfId;
    }

    public void setStfId(Short stfId)
    {
        this.stfId = stfId;
    }

    public Short getCostId()
    {
        return costId;
    }

    public void setCostId(Short costId)
    {
        this.costId = costId;
    }

    public String getCostName()
    {
        return costName;
    }

    public void setCostName(String costName)
    {
        this.costName = costName;
    }

    public Double getCostAmount()
    {
        return costAmount;
    }

    public void setCostAmount(Double costAmount)
    {
        this.costAmount = costAmount;
    }

    public String getCostExplain()
    {
        return costExplain;
    }

    public void setCostExplain(String costExplain)
    {
        this.costExplain = costExplain;
    }

    public String getClaimantmTimeBegin()
    {
        return claimantmTimeBegin;
    }

    public void setClaimantmTimeBegin(String claimantmTimeBegin)
    {
        this.claimantmTimeBegin = claimantmTimeBegin;
    }

    public String getClaimantmTimeEnd()
    {
        return claimantmTimeEnd;
    }

    public void setClaimantmTimeEnd(String claimantmTimeEnd)
    {
        this.claimantmTimeEnd = claimantmTimeEnd;
    }

    public String getClaimantmInvoiceTimeBegin()
    {
        return claimantmInvoiceTimeBegin;
    }

    public void setClaimantmInvoiceTimeBegin(String claimantmInvoiceTimeBegin)
    {
        this.claimantmInvoiceTimeBegin = claimantmInvoiceTimeBegin;
    }

    public String getClaimantmInvoiceTimeEnd()
    {
        return claimantmInvoiceTimeEnd;
    }

    public void setClaimantmInvoiceTimeEnd(String claimantmInvoiceTimeEnd)
    {
        this.claimantmInvoiceTimeEnd = claimantmInvoiceTimeEnd;
    }

    public String getPreclrTime()
    {
        return preclrTime;
    }

    public void setPreclrTime(String preclrTime)
    {
        this.preclrTime = preclrTime;
    }

    public String getCarId()
    {
        return carId;
    }

    public void setCarId(String carId)
    {
        this.carId = carId;
    }

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
    }

    public String getCarVin()
    {
        return carVin;
    }

    public void setCarVin(String carVin)
    {
        this.carVin = carVin;
    }

    public String getCarMotorId()
    {
        return carMotorId;
    }

    public void setCarMotorId(String carMotorId)
    {
        this.carMotorId = carMotorId;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getRelcampName()
    {
        return relcampName;
    }

    public void setRelcampName(String relcampName)
    {
        this.relcampName = relcampName;
    }

    public Integer getReceptionDistance()
    {
        return receptionDistance;
    }

    public void setReceptionDistance(Integer receptionDistance)
    {
        this.receptionDistance = receptionDistance;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public Double getClaimantMainCost()
    {
        return claimantMainCost;
    }

    public void setClaimantMainCost(Double claimantMainCost)
    {
        this.claimantMainCost = claimantMainCost;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
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

    public Double getClaimantmManagementFee()
    {
        return claimantmManagementFee;
    }

    public void setClaimantmManagementFee(Double claimantmManagementFee)
    {
        this.claimantmManagementFee = claimantmManagementFee;
    }

    public Double getClaimantmOtherAmount()
    {
        return claimantmOtherAmount;
    }

    public void setClaimantmOtherAmount(Double claimantmOtherAmount)
    {
        this.claimantmOtherAmount = claimantmOtherAmount;
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

    public String getOthers()
    {
        return others;
    }

    public void setOthers(String others)
    {
        this.others = others;
    }

    public Boolean getFlag()
    {
        return flag;
    }

    public void setFlag(Boolean flag)
    {
        this.flag = flag;
    }

	public Double getClaimantmNoTaxCost() {
		return claimantmNoTaxCost;
	}

	public void setClaimantmNoTaxCost(Double claimantmNoTaxCost) {
		this.claimantmNoTaxCost = claimantmNoTaxCost;
	}

	public Double getClaimantmTaxCost() {
		return claimantmTaxCost;
	}

	public void setClaimantmTaxCost(Double claimantmTaxCost) {
		this.claimantmTaxCost = claimantmTaxCost;
	}

	public Double getPreclrNoTaxCost() {
		return preclrNoTaxCost;
	}

	public void setPreclrNoTaxCost(Double preclrNoTaxCost) {
		this.preclrNoTaxCost = preclrNoTaxCost;
	}

	public Double getPreclrTaxCost() {
		return preclrTaxCost;
	}

	public void setPreclrTaxCost(Double preclrTaxCost) {
		this.preclrTaxCost = preclrTaxCost;
	}

	public Double getDifferenceNoTaxCost() {
		return differenceNoTaxCost;
	}

	public void setDifferenceNoTaxCost(Double differenceNoTaxCost) {
		this.differenceNoTaxCost = differenceNoTaxCost;
	}

	public Double getDifferenceTaxCost() {
		return differenceTaxCost;
	}

	public void setDifferenceTaxCost(Double differenceTaxCost) {
		this.differenceTaxCost = differenceTaxCost;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String parentId) {
		_parentId = parentId;
	}

}
