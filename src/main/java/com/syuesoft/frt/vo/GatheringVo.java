package com.syuesoft.frt.vo;

import java.util.Date;

import com.syuesoft.base.vo.BaseBeanVo;
import com.syuesoft.contstants.Contstants;

public class GatheringVo extends BaseBeanVo
{
    private String preclrId;

    private String preclrTime;

    private String customId;

    private String carId;

    private String preclrInoiceType;

    private String preclrInvoiceTime;

    private String preclrNo;

    private Double preclrAmount;                        //应收金额

    private Double cumulativeAmount;
                   
    private Double preclrRealAmount;                    //实收金额
    
    private Double preclrPayAmount;

    private Short reptId;

    private Date gatheringBeginTime;

    private Date gatheringEndTime;

    private String balanceId;

    private String invoiceType;

    private String invoiceNo;

    private Date invoiceBeginTime;

    private Date invoiceEndTime;

    private String carVin;

    private String linkMan;

    private String linkTel;

    /*************/
    private String preclearingClass = Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT;

    /*************/
    private String carMotorId;

    private String carVinName;

    private String carMotorIdName;

    private String cbrdName;

    private String ctypeName;

    private String colorName;

    private String customName;

    private String customTel;

    private String customAddr;

    private String reptName;

    private String invoiceTypeName;

    private String carLicense;

    private String preclrInoiceTypeName;

    private String memberId;

    private String memberClass;

    private String memberGrade;

    private String gatheringId;

    private Date gatheringTime;

    private Short unAchieve;

    private String GatheringWise;

    private Integer hiveId;

    private Double hiveBalance;

    private Double hiveUse;                    //储备金抵扣金额

    private Integer balanceIntegral;

    private Integer sumIntegral;

    private Integer useIntegral;

    private String gatheringRemark;

    private Double otherBalance;                 //找零

    private Short stfId;

    private String stfName;

    private Double payableAmount;

    private Double paymentAmount;

    private Double arrearsAmount;

    private String paymentTime;

    private Short substitute;

    private String substituteCustomId;

    private Double substituteMoney;

    private String bbgId;

    private Double sumAmount;

    private String sspId;

    private String createTime;

    private Short differenceBalance;

    private String tempPreclrIds;

    private String substituteClaimantId;

    private String tagId;

    private String preclrIds;

    private String reptNames;

    private Boolean tag;

    private Boolean flag;

    private String tempCustomId;
    
    private Double fcsPayment;
    
    // 预收款
    private String beforeId;

    private Double beforeMoney;     //预收款余额
    
    private Double beforeMoney1;    //预收款抵扣额额
    
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

    public String getPreclrId()
    {
        return preclrId;
    }

    public void setPreclrId(String preclrId)
    {
        this.preclrId = preclrId;
    }

    public String getPreclrTime()
    {
        return preclrTime;
    }

    public void setPreclrTime(String preclrTime)
    {
        this.preclrTime = preclrTime;
    }

    public String getCustomId()
    {
        return customId;
    }

    public void setCustomId(String customId)
    {
        this.customId = customId;
    }

    public String getPreclrInoiceType()
    {
        return preclrInoiceType;
    }

    public void setPreclrInoiceType(String preclrInoiceType)
    {
        this.preclrInoiceType = preclrInoiceType;
    }

    public String getPreclrInvoiceTime()
    {
        return preclrInvoiceTime;
    }

    public void setPreclrInvoiceTime(String preclrInvoiceTime)
    {
        this.preclrInvoiceTime = preclrInvoiceTime;
    }

    public String getPreclrNo()
    {
        return preclrNo;
    }

    public void setPreclrNo(String preclrNo)
    {
        this.preclrNo = preclrNo;
    }

    public Double getPreclrRealAmount()
    {
        return preclrRealAmount;
    }

    public void setPreclrRealAmount(Double preclrRealAmount)
    {
        this.preclrRealAmount = preclrRealAmount;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
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

    public String getPreclrInoiceTypeName()
    {
        return preclrInoiceTypeName;
    }

    public void setPreclrInoiceTypeName(String preclrInoiceTypeName)
    {
        this.preclrInoiceTypeName = preclrInoiceTypeName;
    }

    public String getPreclearingClass()
    {
        return preclearingClass;
    }

    public void setPreclearingClass(String preclearingClass)
    {
        this.preclearingClass = preclearingClass;
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

    public Short getReptId()
    {
        return reptId;
    }

    public void setReptId(Short reptId)
    {
        this.reptId = reptId;
    }

    public Date getGatheringBeginTime()
    {
        return gatheringBeginTime;
    }

    public void setGatheringBeginTime(Date gatheringBeginTime)
    {
        this.gatheringBeginTime = gatheringBeginTime;
    }

    public Date getGatheringEndTime()
    {
        return gatheringEndTime;
    }

    public void setGatheringEndTime(Date gatheringEndTime)
    {
        this.gatheringEndTime = gatheringEndTime;
    }

    public String getBalanceId()
    {
        return balanceId;
    }

    public void setBalanceId(String balanceId)
    {
        this.balanceId = balanceId;
    }

    public String getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceNo()
    {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo)
    {
        this.invoiceNo = invoiceNo;
    }

    public Date getInvoiceBeginTime()
    {
        return invoiceBeginTime;
    }

    public void setInvoiceBeginTime(Date invoiceBeginTime)
    {
        this.invoiceBeginTime = invoiceBeginTime;
    }

    public Date getInvoiceEndTime()
    {
        return invoiceEndTime;
    }

    public void setInvoiceEndTime(Date invoiceEndTime)
    {
        this.invoiceEndTime = invoiceEndTime;
    }

    public String getCarVin()
    {
        return carVin;
    }

    public void setCarVin(String carVin)
    {
        this.carVin = carVin;
    }

    public String getLinkMan()
    {
        return linkMan;
    }

    public void setLinkMan(String linkMan)
    {
        this.linkMan = linkMan;
    }

    public String getLinkTel()
    {
        return linkTel;
    }

    public void setLinkTel(String linkTel)
    {
        this.linkTel = linkTel;
    }

    public String getCarMotorId()
    {
        return carMotorId;
    }

    public void setCarMotorId(String carMotorId)
    {
        this.carMotorId = carMotorId;
    }

    public String getCarVinName()
    {
        return carVinName;
    }

    public void setCarVinName(String carVinName)
    {
        this.carVinName = carVinName;
    }

    public String getCarMotorIdName()
    {
        return carMotorIdName;
    }

    public void setCarMotorIdName(String carMotorIdName)
    {
        this.carMotorIdName = carMotorIdName;
    }

    public String getCbrdName()
    {
        return cbrdName;
    }

    public void setCbrdName(String cbrdName)
    {
        this.cbrdName = cbrdName;
    }

    public String getCtypeName()
    {
        return ctypeName;
    }

    public void setCtypeName(String ctypeName)
    {
        this.ctypeName = ctypeName;
    }

    public String getColorName()
    {
        return colorName;
    }

    public void setColorName(String colorName)
    {
        this.colorName = colorName;
    }

    public String getCustomTel()
    {
        return customTel;
    }

    public void setCustomTel(String customTel)
    {
        this.customTel = customTel;
    }

    public String getCustomAddr()
    {
        return customAddr;
    }

    public void setCustomAddr(String customAddr)
    {
        this.customAddr = customAddr;
    }

    public String getReptName()
    {
        return reptName;
    }

    public void setReptName(String reptName)
    {
        this.reptName = reptName;
    }

    public String getInvoiceTypeName()
    {
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName)
    {
        this.invoiceTypeName = invoiceTypeName;
    }

    public String getPreclrIds()
    {
        return preclrIds;
    }

    public void setPreclrIds(String preclrIds)
    {
        this.preclrIds = preclrIds;
    }

    public String getReptNames()
    {
        return reptNames;
    }

    public void setReptNames(String reptNames)
    {
        this.reptNames = reptNames;
    }

    public Double getPreclrAmount()
    {
        return preclrAmount;
    }

    public void setPreclrAmount(Double preclrAmount)
    {
        this.preclrAmount = preclrAmount;
    }

    public String getGatheringId()
    {
        return gatheringId;
    }

    public void setGatheringId(String gatheringId)
    {
        this.gatheringId = gatheringId;
    }

    public Date getGatheringTime()
    {
        return gatheringTime;
    }

    public void setGatheringTime(Date gatheringTime)
    {
        this.gatheringTime = gatheringTime;
    }

    public Short getUnAchieve()
    {
        return unAchieve;
    }

    public void setUnAchieve(Short unAchieve)
    {
        this.unAchieve = unAchieve;
    }

    public String getGatheringWise()
    {
        return GatheringWise;
    }

    public void setGatheringWise(String gatheringWise)
    {
        GatheringWise = gatheringWise;
    }

    public Double getHiveBalance()
    {
        return hiveBalance;
    }

    public void setHiveBalance(Double hiveBalance)
    {
        this.hiveBalance = hiveBalance;
    }

    public Double getHiveUse()
    {
        return hiveUse;
    }

    public void setHiveUse(Double hiveUse)
    {
        this.hiveUse = hiveUse;
    }

    public Integer getBalanceIntegral()
    {
        return balanceIntegral;
    }

    public void setBalanceIntegral(Integer balanceIntegral)
    {
        this.balanceIntegral = balanceIntegral;
    }

    public Integer getSumIntegral()
    {
        return sumIntegral;
    }

    public void setSumIntegral(Integer sumIntegral)
    {
        this.sumIntegral = sumIntegral;
    }

    public Integer getUseIntegral()
    {
        return useIntegral;
    }

    public void setUseIntegral(Integer useIntegral)
    {
        this.useIntegral = useIntegral;
    }

    public String getGatheringRemark()
    {
        return gatheringRemark;
    }

    public void setGatheringRemark(String gatheringRemark)
    {
        this.gatheringRemark = gatheringRemark;
    }

    public Short getDifferenceBalance()
    {
        return differenceBalance;
    }

    public void setDifferenceBalance(Short differenceBalance)
    {
        this.differenceBalance = differenceBalance;
    }

    public String getMemberId()
    {
        return memberId;
    }

    public String getMemberClass()
    {
        return memberClass;
    }

    public void setMemberClass(String memberClass)
    {
        this.memberClass = memberClass;
    }

    public String getMemberGrade()
    {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade)
    {
        this.memberGrade = memberGrade;
    }

    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }

    public Double getOtherBalance()
    {
        return otherBalance;
    }

    public void setOtherBalance(Double otherBalance)
    {
        this.otherBalance = otherBalance;
    }

    public Short getStfId()
    {
        return stfId;
    }

    public void setStfId(Short stfId)
    {
        this.stfId = stfId;
    }

    public String getTempPreclrIds()
    {
        return tempPreclrIds;
    }

    public void setTempPreclrIds(String tempPreclrIds)
    {
        this.tempPreclrIds = tempPreclrIds;
    }

    public Integer getHiveId()
    {
        return hiveId;
    }

    public void setHiveId(Integer hiveId)
    {
        this.hiveId = hiveId;
    }

    public Double getCumulativeAmount()
    {
        return cumulativeAmount;
    }

    public void setCumulativeAmount(Double cumulativeAmount)
    {
        this.cumulativeAmount = cumulativeAmount;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public Double getArrearsAmount()
    {
        return arrearsAmount;
    }

    public void setArrearsAmount(Double arrearsAmount)
    {
        this.arrearsAmount = arrearsAmount;
    }

    public Double getPayableAmount()
    {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount)
    {
        this.payableAmount = payableAmount;
    }

    public Double getPaymentAmount()
    {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount)
    {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentTime()
    {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime)
    {
        this.paymentTime = paymentTime;
    }

    public Short getSubstitute()
    {
        return substitute;
    }

    public void setSubstitute(Short substitute)
    {
        this.substitute = substitute;
    }

    public String getSubstituteCustomId()
    {
        return substituteCustomId;
    }

    public void setSubstituteCustomId(String substituteCustomId)
    {
        this.substituteCustomId = substituteCustomId;
    }

    public Double getSubstituteMoney()
    {
        return substituteMoney;
    }

    public void setSubstituteMoney(Double substituteMoney)
    {
        this.substituteMoney = substituteMoney;
    }

    public String getBbgId()
    {
        return bbgId;
    }

    public void setBbgId(String bbgId)
    {
        this.bbgId = bbgId;
    }

    public Double getSumAmount()
    {
        return sumAmount;
    }

    public void setSumAmount(Double sumAmount)
    {
        this.sumAmount = sumAmount;
    }

    public Boolean getTag()
    {
        return tag;
    }

    public void setTag(Boolean tag)
    {
        this.tag = tag;
    }

    public String getSspId()
    {
        return sspId;
    }

    public void setSspId(String sspId)
    {
        this.sspId = sspId;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getTagId()
    {
        return tagId;
    }

    public void setTagId(String tagId)
    {
        this.tagId = tagId;
    }

    public String getSubstituteClaimantId()
    {
        return substituteClaimantId;
    }

    public void setSubstituteClaimantId(String substituteClaimantId)
    {
        this.substituteClaimantId = substituteClaimantId;
    }

    public String getBeforeId()
    {
        return beforeId;
    }

    public void setBeforeId(String beforeId)
    {
        this.beforeId = beforeId;
    }

    public Double getBeforeMoney()
    {
        return beforeMoney;
    }

    public void setBeforeMoney(Double beforeMoney)
    {
        this.beforeMoney = beforeMoney;
    }

    public Boolean getFlag()
    {
        return flag;
    }

    public void setFlag(Boolean flag)
    {
        this.flag = flag;
    }

	public String getTempCustomId() {
		return tempCustomId;
	}

	public void setTempCustomId(String tempCustomId) {
		this.tempCustomId = tempCustomId;
	}

	public Double getFcsPayment() {
		return fcsPayment;
	}

	public void setFcsPayment(Double fcsPayment) {
		this.fcsPayment = fcsPayment;
	}

    public Double getBeforeMoney1()
    {
        return beforeMoney1;
    }

    public void setBeforeMoney1(Double beforeMoney1)
    {
        this.beforeMoney1 = beforeMoney1;
    }

    public Double getPreclrPayAmount()
    {
        return preclrPayAmount;
    }

    public void setPreclrPayAmount(Double preclrPayAmount)
    {
        this.preclrPayAmount = preclrPayAmount;
    }
}