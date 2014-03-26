package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FrtPreClearingVo extends BaseBeanVo
{

    private String preclrId;

    private Integer partsIndex;

    private String partsId;

    private Double partsCount;

    private Double stinvdPrice;

    private Double partsPrice;

    private Double partsAmount;

    private Short relcampId;

    private Short reptypId;

    private Double settlementDiscount;

    private String partsName;

    private Short preclrToMoney;

    private Integer wktimeIndex;

    private String repitemId;

    private String repitemName;

    private Double wktimeNum;

    private Double innerWktime;

    private Double wktimeAmount;

    private Short stfId;

    private String receptionId;

    private String preclrTime;

    private String preclrInoiceType;

    private String preclrInvoiceTime;

    private String preclrNo;

    private Double preMprMatAmount;

    private Double preclrMaterialRate;

    private Double preclrWktimeAmount;

    private Double preclrWktimeRate;

    private Double preclrOtherAmount;

    private Double preclrSumAmount;

    private Double preclrSumRate;

    private Double preclrRealAmount;

    private Double preclrNoTaxCost;

    private Double preclrTaxCost;

    private String preclrFlg;

    private String preclrInstr;

    private String preclrRemark;

    private Short preclrHasUnrealData;

    private Double preclrManagementFee;

    private Double preMprMatAmountOld;

    private Short finComId;

    private Short finTag;

    private Short finAllTag;

    private String carVin;

    private String carRelationTel1;

    private String reptId;
    
    private String reptName;

    private Short receptor;

    private Short costId;

    private String costItem;

    private Double costAmount;

    private String costExplain;

    private String carLicense;

    private String carMotorId;

    private String customName;

    private String preclrTimeBegin;

    private String preclrTimeEnd;

    private String preclrInvoiceTimeBegin;

    private String preclrInvoiceTimeEnd;

    private String stfName;

    private String tempName1;

    private String receptionStatus;
    
    private String preClrState;

    private String carId;

    private String q;

    private String parts;

    private String id;

    private String sort;

    private String order;

    private int page;

    private int rows;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public FrtPreClearingVo()
    {

    }

    public FrtPreClearingVo(String preclrId, Short costId, String costItem,
            Double costAmount, String costExplain)
    {
        this.preclrId = preclrId;
        this.costId = costId;
        this.costItem = costItem;
        this.costAmount = costAmount;
        this.costExplain = costExplain;
    }

    public FrtPreClearingVo(Short relcampId, Short reptypId,
            Double settlementDiscount, Integer wktimeIndex, String repitemId,
            String repitemName, Double wktimeNum, Double innerWktime,
            Double wktimeAmount, Short stfId, String preclrId)
    {
        this.relcampId = relcampId;
        this.reptypId = reptypId;
        this.settlementDiscount = settlementDiscount;
        this.wktimeIndex = wktimeIndex;
        this.repitemId = repitemId;
        this.repitemName = repitemName;
        this.wktimeNum = wktimeNum;
        this.innerWktime = innerWktime;
        this.wktimeAmount = wktimeAmount;
        this.stfId = stfId;
        this.preclrId = preclrId;
    }

    public FrtPreClearingVo(String preclrId, Integer partsIndex,
            String partsId, Double partsCount, Double stinvdPrice,
            Double partsPrice, Double partsAmount, Short relcampId,
            Short reptypId, Double settlementDiscount, String partsName)
    {
        this.preclrId = preclrId;
        this.partsIndex = partsIndex;
        this.partsId = partsId;
        this.partsCount = partsCount;
        this.stinvdPrice = stinvdPrice;
        this.partsPrice = partsPrice;
        this.partsAmount = partsAmount;
        this.relcampId = relcampId;
        this.reptypId = reptypId;
        this.settlementDiscount = settlementDiscount;
        this.partsName = partsName;
    }

    public FrtPreClearingVo(String preclrId, String receptionId,
            String carLicense, String carMotorId, String customName,
            String preclrTime, String preclrInoiceType,
            String preclrInvoiceTime, String preclrNo, Double preMprMatAmount,
            Double preclrMaterialRate, Double preclrWktimeAmount,
            Double preclrWktimeRate, Double preclrOtherAmount,
            Double preclrSumAmount, Double preclrSumRate,
            Double preclrRealAmount, Double preclrNoTaxCost, String preclrFlg,
            String preclrInstr, String preclrRemark, Short preclrHasUnrealData,
            Double preclrManagementFee)
    {
        this.preclrId = preclrId;
        this.receptionId = receptionId;
        this.carLicense = carLicense;
        this.carMotorId = carMotorId;
        this.customName = customName;
        this.preclrTime = preclrTime;
        this.preclrInoiceType = preclrInoiceType;
        this.preclrInvoiceTime = preclrInvoiceTime;
        this.preclrNo = preclrNo;
        this.preMprMatAmount = preMprMatAmount;
        this.preclrMaterialRate = preclrMaterialRate;
        this.preclrWktimeAmount = preclrWktimeAmount;
        this.preclrWktimeRate = preclrWktimeRate;
        this.preclrOtherAmount = preclrOtherAmount;
        this.preclrSumAmount = preclrSumAmount;
        this.preclrSumRate = preclrSumRate;
        this.preclrRealAmount = preclrRealAmount;
        this.preclrNoTaxCost = preclrNoTaxCost;
        this.preclrFlg = preclrFlg;
        this.preclrInstr = preclrInstr;
        this.preclrRemark = preclrRemark;
        this.preclrHasUnrealData = preclrHasUnrealData;
        this.preclrManagementFee = preclrManagementFee;
    }

    public String getPreclrTime()
    {
        return preclrTime;
    }

    public void setPreclrTime(String preclrTime)
    {
        this.preclrTime = preclrTime;
    }

    public String getPreclrInvoiceTime()
    {
        return preclrInvoiceTime;
    }

    public void setPreclrInvoiceTime(String preclrInvoiceTime)
    {
        this.preclrInvoiceTime = preclrInvoiceTime;
    }

    public String getPreclrInoiceType()
    {
        return preclrInoiceType;
    }

    public void setPreclrInoiceType(String preclrInoiceType)
    {
        this.preclrInoiceType = preclrInoiceType;
    }

    public String getPreclrNo()
    {
        return preclrNo;
    }

    public void setPreclrNo(String preclrNo)
    {
        this.preclrNo = preclrNo;
    }

    public Double getPreMprMatAmount()
    {
        return preMprMatAmount;
    }

    public void setPreMprMatAmount(Double preMprMatAmount)
    {
        this.preMprMatAmount = preMprMatAmount;
    }

    public Double getPreclrMaterialRate()
    {
        return preclrMaterialRate;
    }

    public void setPreclrMaterialRate(Double preclrMaterialRate)
    {
        this.preclrMaterialRate = preclrMaterialRate;
    }

    public Double getPreclrWktimeAmount()
    {
        return preclrWktimeAmount;
    }

    public void setPreclrWktimeAmount(Double preclrWktimeAmount)
    {
        this.preclrWktimeAmount = preclrWktimeAmount;
    }

    public Double getPreclrWktimeRate()
    {
        return preclrWktimeRate;
    }

    public void setPreclrWktimeRate(Double preclrWktimeRate)
    {
        this.preclrWktimeRate = preclrWktimeRate;
    }

    public Double getPreclrOtherAmount()
    {
        return preclrOtherAmount;
    }

    public void setPreclrOtherAmount(Double preclrOtherAmount)
    {
        this.preclrOtherAmount = preclrOtherAmount;
    }

    public Double getPreclrSumAmount()
    {
        return preclrSumAmount;
    }

    public void setPreclrSumAmount(Double preclrSumAmount)
    {
        this.preclrSumAmount = preclrSumAmount;
    }

    public Double getPreclrSumRate()
    {
        return preclrSumRate;
    }

    public void setPreclrSumRate(Double preclrSumRate)
    {
        this.preclrSumRate = preclrSumRate;
    }

    public Double getPreclrRealAmount()
    {
        return preclrRealAmount;
    }

    public void setPreclrRealAmount(Double preclrRealAmount)
    {
        this.preclrRealAmount = preclrRealAmount;
    }

    public Double getPreclrNoTaxCost()
    {
        return preclrNoTaxCost;
    }

    public void setPreclrNoTaxCost(Double preclrNoTaxCost)
    {
        this.preclrNoTaxCost = preclrNoTaxCost;
    }

    public Double getPreclrTaxCost()
    {
        return preclrTaxCost;
    }

    public void setPreclrTaxCost(Double preclrTaxCost)
    {
        this.preclrTaxCost = preclrTaxCost;
    }

    public String getPreclrFlg()
    {
        return preclrFlg;
    }

    public void setPreclrFlg(String preclrFlg)
    {
        this.preclrFlg = preclrFlg;
    }

    public String getPreclrInstr()
    {
        return preclrInstr;
    }

    public void setPreclrInstr(String preclrInstr)
    {
        this.preclrInstr = preclrInstr;
    }

    public String getPreclrRemark()
    {
        return preclrRemark;
    }

    public void setPreclrRemark(String preclrRemark)
    {
        this.preclrRemark = preclrRemark;
    }

    public Short getPreclrHasUnrealData()
    {
        return preclrHasUnrealData;
    }

    public void setPreclrHasUnrealData(Short preclrHasUnrealData)
    {
        this.preclrHasUnrealData = preclrHasUnrealData;
    }

    public Double getPreclrManagementFee()
    {
        return preclrManagementFee;
    }

    public void setPreclrManagementFee(Double preclrManagementFee)
    {
        this.preclrManagementFee = preclrManagementFee;
    }

    public String getPreclrId()
    {
        return preclrId;
    }

    public void setPreclrId(String preclrId)
    {
        this.preclrId = preclrId;
    }

    public Integer getPartsIndex()
    {
        return partsIndex;
    }

    public void setPartsIndex(Integer partsIndex)
    {
        this.partsIndex = partsIndex;
    }

    public String getPartsId()
    {
        return partsId;
    }

    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
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

    public Double getPartsAmount()
    {
        return partsAmount;
    }

    public void setPartsAmount(Double partsAmount)
    {
        this.partsAmount = partsAmount;
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

    public Double getSettlementDiscount()
    {
        return settlementDiscount;
    }

    public void setSettlementDiscount(Double settlementDiscount)
    {
        this.settlementDiscount = settlementDiscount;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public Integer getWktimeIndex()
    {
        return wktimeIndex;
    }

    public void setWktimeIndex(Integer wktimeIndex)
    {
        this.wktimeIndex = wktimeIndex;
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

    public Double getWktimeNum()
    {
        return wktimeNum;
    }

    public void setWktimeNum(Double wktimeNum)
    {
        this.wktimeNum = wktimeNum;
    }

    public Double getInnerWktime()
    {
        return innerWktime;
    }

    public void setInnerWktime(Double innerWktime)
    {
        this.innerWktime = innerWktime;
    }

    public Double getWktimeAmount()
    {
        return wktimeAmount;
    }

    public void setWktimeAmount(Double wktimeAmount)
    {
        this.wktimeAmount = wktimeAmount;
    }

    public Short getStfId()
    {
        return stfId;
    }

    public void setStfId(Short stfId)
    {
        this.stfId = stfId;
    }

    public String getReceptionId()
    {
        return receptionId;
    }

    public void setReceptionId(String receptionId)
    {
        this.receptionId = receptionId;
    }

    public Short getCostId()
    {
        return costId;
    }

    public void setCostId(Short costId)
    {
        this.costId = costId;
    }

    public String getCostItem()
    {
        return costItem;
    }

    public void setCostItem(String costItem)
    {
        this.costItem = costItem;
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

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
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

    public String getPreclrTimeBegin()
    {
        return preclrTimeBegin;
    }

    public void setPreclrTimeBegin(String preclrTimeBegin)
    {
        this.preclrTimeBegin = preclrTimeBegin;
    }

    public String getPreclrTimeEnd()
    {
        return preclrTimeEnd;
    }

    public void setPreclrTimeEnd(String preclrTimeEnd)
    {
        this.preclrTimeEnd = preclrTimeEnd;
    }

    public String getPreclrInvoiceTimeBegin()
    {
        return preclrInvoiceTimeBegin;
    }

    public void setPreclrInvoiceTimeBegin(String preclrInvoiceTimeBegin)
    {
        this.preclrInvoiceTimeBegin = preclrInvoiceTimeBegin;
    }

    public String getPreclrInvoiceTimeEnd()
    {
        return preclrInvoiceTimeEnd;
    }

    public void setPreclrInvoiceTimeEnd(String preclrInvoiceTimeEnd)
    {
        this.preclrInvoiceTimeEnd = preclrInvoiceTimeEnd;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public String getCarVin()
    {
        return carVin;
    }

    public void setCarVin(String carVin)
    {
        this.carVin = carVin;
    }

    public String getCarRelationTel1()
    {
        return carRelationTel1;
    }

    public void setCarRelationTel1(String carRelationTel1)
    {
        this.carRelationTel1 = carRelationTel1;
    }

    public Short getReceptor()
    {
        return receptor;
    }

    public void setReceptor(Short receptor)
    {
        this.receptor = receptor;
    }

    public String getReptId()
    {
        return reptId;
    }

    public void setReptId(String reptId)
    {
        this.reptId = reptId;
    }

    public Short getFinTag()
    {
        return finTag;
    }

    public void setFinTag(Short finTag)
    {
        this.finTag = finTag;
    }

    public Short getFinAllTag()
    {
        return finAllTag;
    }

    public void setFinAllTag(Short finAllTag)
    {
        this.finAllTag = finAllTag;
    }

    public Short getFinComId()
    {
        return finComId;
    }

    public void setFinComId(Short finComId)
    {
        this.finComId = finComId;
    }

    public String getTempName1()
    {
        return tempName1;
    }

    public void setTempName1(String tempName1)
    {
        this.tempName1 = tempName1;
    }

    public Short getPreclrToMoney()
    {
        return preclrToMoney;
    }

    public void setPreclrToMoney(Short preclrToMoney)
    {
        this.preclrToMoney = preclrToMoney;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public Double getPreMprMatAmountOld()
    {
        return preMprMatAmountOld;
    }

    public void setPreMprMatAmountOld(Double preMprMatAmountOld)
    {
        this.preMprMatAmountOld = preMprMatAmountOld;
    }

    public String getReceptionStatus()
    {
        return receptionStatus;
    }

    public void setReceptionStatus(String receptionStatus)
    {
        this.receptionStatus = receptionStatus;
    }

    public String getCarId()
    {
        return carId;
    }

    public void setCarId(String carId)
    {
        this.carId = carId;
    }

    public String getParts()
    {
        return parts;
    }

    public void setParts(String parts)
    {
        this.parts = parts;
    }

	public String getReptName() {
		return reptName;
	}

	public void setReptName(String reptName) {
		this.reptName = reptName;
	}

	public String getPreClrState() {
		return preClrState;
	}

	public void setPreClrState(String preClrState) {
		this.preClrState = preClrState;
	}

}
