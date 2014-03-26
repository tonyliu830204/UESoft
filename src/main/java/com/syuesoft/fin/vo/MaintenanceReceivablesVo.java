package com.syuesoft.fin.vo;

import java.util.Date;

public class MaintenanceReceivablesVo
{

    private String customId;

    private String customName;

    private String receptionId;

    private Date interDate;

    private String carId;

    private String carLicense;

    private String preclrId;

    private String preclrTime;

    private String preclrSumAmount;

    private String mrId;

    private Double cumulativeMoney;

    private Double mrReceivables;

    private Short mrInoiceType;

    private Short mrAllowArrearsAge;

    private Short mrReceivablesArrearsAge;

    private Short mrAllowFlag;

    private Double mrAllowArrearsAmount;

    private Short mrAllowArrearsNumber;

    private Short mrPrintFlag;

    private Double surPercharge;

    private String preclrNo;

    private Integer fcsId;

    private String stfId;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    private Short paymentId;

    private Double thisTimeCharge;

    private Double arrears;

    private Date fcsDate;

    private Short unfinished;

    private String reptName;

    private String stfName;

    private String preFlg;

    private String carBrand;

    private String q;

    private String carVin;

    private String sort;

    private String order;

    private int page;

    private int rows;

    private String resvRealTime;

    private String paidAmount;

    private String relcampName;

    private String claimantmId;

    private String claimantmAmount;

    private String claimantmInvoiceNo;

    private String crReceivables;

    private String cbrdName;

    private String receLicense;

    private String claimantmTime;

    private String chargeId;

    private String paidDate;

    private String totalAmount;

    private String cerNo;

    private String sellmmId;

    private String sellmmDate;

    private String sellmmSumAmount;

    private String billType;

    private String claimantmTimeStart;

    private String claimantmTimeEnd;

    private String resvRealTimeStart;

    private String resvRealTimeEnd;

    private String paidDateStart;

    private String paidDateEnd;

    private String sellmmDateStart;

    private String sellmmDateEnd;

    private String preclrTimeStart;

    private String preclrTimeEnd;

    private String preclrDateEnd;

    private String preclrDateStart;

    public String getPreclrDateEnd()
    {
        return preclrDateEnd;
    }

    public void setPreclrDateEnd(String preclrDateEnd)
    {
        this.preclrDateEnd = preclrDateEnd;
    }

    public String getPreclrDateStart()
    {
        return preclrDateStart;
    }

    public void setPreclrDateStart(String preclrDateStart)
    {
        this.preclrDateStart = preclrDateStart;
    }

    public String getPaidAmount()
    {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount)
    {
        this.paidAmount = paidAmount;
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

    public String getPaidDateStart()
    {
        return paidDateStart;
    }

    public void setPaidDateStart(String paidDateStart)
    {
        this.paidDateStart = paidDateStart;
    }

    public String getPaidDateEnd()
    {
        return paidDateEnd;
    }

    public void setPaidDateEnd(String paidDateEnd)
    {
        this.paidDateEnd = paidDateEnd;
    }

    public String getSellmmDateStart()
    {
        return sellmmDateStart;
    }

    public void setSellmmDateStart(String sellmmDateStart)
    {
        this.sellmmDateStart = sellmmDateStart;
    }

    public String getSellmmDateEnd()
    {
        return sellmmDateEnd;
    }

    public void setSellmmDateEnd(String sellmmDateEnd)
    {
        this.sellmmDateEnd = sellmmDateEnd;
    }

    public String getClaimantmTimeStart()
    {
        return claimantmTimeStart;
    }

    public void setClaimantmTimeStart(String claimantmTimeStart)
    {
        this.claimantmTimeStart = claimantmTimeStart;
    }

    public String getClaimantmTimeEnd()
    {
        return claimantmTimeEnd;
    }

    public void setClaimantmTimeEnd(String claimantmTimeEnd)
    {
        this.claimantmTimeEnd = claimantmTimeEnd;
    }

    public String getResvRealTimeStart()
    {
        return resvRealTimeStart;
    }

    public void setResvRealTimeStart(String resvRealTimeStart)
    {
        this.resvRealTimeStart = resvRealTimeStart;
    }

    public String getResvRealTimeEnd()
    {
        return resvRealTimeEnd;
    }

    public void setResvRealTimeEnd(String resvRealTimeEnd)
    {
        this.resvRealTimeEnd = resvRealTimeEnd;
    }

    public MaintenanceReceivablesVo()
    {

    }

    public MaintenanceReceivablesVo(String customId, String customName,
            String receptionId, Date interDate, String carId,
            String carLicense, String preclrId, String preclrTime, String mrId,
            String preclrSumAmount, Double cumulativeMoney, Double mrReceivables)
    {
        this.customId = customId;
        this.customName = customName;
        this.receptionId = receptionId;
        this.interDate = interDate;
        this.carId = carId;
        this.carLicense = carLicense;
        this.preclrId = preclrId;
        this.preclrTime = preclrTime;
        this.preclrSumAmount = preclrSumAmount;
        this.mrId = mrId;
        this.cumulativeMoney = cumulativeMoney;
        this.mrReceivables = mrReceivables;
        // this.mrAllowArrearsAge = mrAllowArrearsAge;
        // this.mrReceivablesArrearsAge = mrReceivablesArrearsAge;
        // this.mrAllowFlag = mrAllowFlag;
        // this.mrAllowArrearsAmount = mrAllowArrearsAmount;
        // this.mrAllowArrearsNumber = mrAllowArrearsNumber;
        // this.mrPrintFlag = mrPrintFlag;
    }

    /**
     * 维修收款->维修收款明细datagrid
     * 
     * @param customId
     * @param carLicense
     * @param preclrId
     * @param preclrSumAmount
     * @param mrCumulativeMoney
     * @param mrInoiceType
     * @param fcsId
     * @param stfId
     * @param paymentId
     * @param thisTimeCharge
     * @param arrears
     * @param fcsDate
     * @param unfinished
     */
    public MaintenanceReceivablesVo(String customName, String carLicense,
            String preclrId, String preclrSumAmount, Double cumulativeMoney,
            Double mrReceivables, Short mrInoiceType, Integer fcsId,
            String stfId, Short paymentId, Double thisTimeCharge,
            Double arrears, Date fcsDate, Short unfinished, String stfName)
    {
        this.customName = customName;
        this.carLicense = carLicense;
        this.preclrId = preclrId;
        this.preclrSumAmount = preclrSumAmount;
        this.cumulativeMoney = cumulativeMoney;
        this.mrReceivables = mrReceivables;
        this.mrInoiceType = mrInoiceType;
        this.fcsId = fcsId;
        this.stfId = stfId;
        this.paymentId = paymentId;
        this.thisTimeCharge = thisTimeCharge;
        this.arrears = arrears;
        this.fcsDate = fcsDate;
        this.unfinished = unfinished;
        this.stfName = stfName;
    }

    public String getSellmmSumAmount()
    {
        return sellmmSumAmount;
    }

    public void setSellmmSumAmount(String sellmmSumAmount)
    {
        this.sellmmSumAmount = sellmmSumAmount;
    }

    public String getSellmmDate()
    {
        return sellmmDate;
    }

    public void setSellmmDate(String sellmmDate)
    {
        this.sellmmDate = sellmmDate;
    }

    public String getChargeId()
    {
        return chargeId;
    }

    public void setChargeId(String chargeId)
    {
        this.chargeId = chargeId;
    }

    public String getPaidDate()
    {
        return paidDate;
    }

    public void setPaidDate(String paidDate)
    {
        this.paidDate = paidDate;
    }

    public String getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getCerNo()
    {
        return cerNo;
    }

    public void setCerNo(String cerNo)
    {
        this.cerNo = cerNo;
    }

    public String getSellmmId()
    {
        return sellmmId;
    }

    public void setSellmmId(String sellmmId)
    {
        this.sellmmId = sellmmId;
    }

    public String getBillType()
    {
        return billType;
    }

    public void setBillType(String billType)
    {
        this.billType = billType;
    }

    public String getRelcampName()
    {
        return relcampName;
    }

    public void setRelcampName(String relcampName)
    {
        this.relcampName = relcampName;
    }

    public String getClaimantmId()
    {
        return claimantmId;
    }

    public void setClaimantmId(String claimantmId)
    {
        this.claimantmId = claimantmId;
    }

    public String getClaimantmAmount()
    {
        return claimantmAmount;
    }

    public void setClaimantmAmount(String claimantmAmount)
    {
        this.claimantmAmount = claimantmAmount;
    }

    public String getClaimantmInvoiceNo()
    {
        return claimantmInvoiceNo;
    }

    public void setClaimantmInvoiceNo(String claimantmInvoiceNo)
    {
        this.claimantmInvoiceNo = claimantmInvoiceNo;
    }

    public String getCrReceivables()
    {
        return crReceivables;
    }

    public void setCrReceivables(String crReceivables)
    {
        this.crReceivables = crReceivables;
    }

    public String getCbrdName()
    {
        return cbrdName;
    }

    public void setCbrdName(String cbrdName)
    {
        this.cbrdName = cbrdName;
    }

    public String getReceLicense()
    {
        return receLicense;
    }

    public void setReceLicense(String receLicense)
    {
        this.receLicense = receLicense;
    }

    public String getCarBrand()
    {
        return carBrand;
    }

    public void setCarBrand(String carBrand)
    {
        this.carBrand = carBrand;
    }

    public String getCarVin()
    {
        return carVin;
    }

    public void setCarVin(String carVin)
    {
        this.carVin = carVin;
    }

    public String getPreFlg()
    {
        return preFlg;
    }

    public void setPreFlg(String preFlg)
    {
        this.preFlg = preFlg;
    }

    public String getReptName()
    {
        return reptName;
    }

    public void setReptName(String reptName)
    {
        this.reptName = reptName;
    }

    public String getPreclrNo()
    {
        return preclrNo;
    }

    public void setPreclrNo(String preclrNo)
    {
        this.preclrNo = preclrNo;
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

    public String getMrId()
    {
        return mrId;
    }

    public void setMrId(String mrId)
    {
        this.mrId = mrId;
    }

    public Double getCumulativeMoney()
    {
        return cumulativeMoney;
    }

    public void setCumulativeMoney(Double cumulativeMoney)
    {
        this.cumulativeMoney = cumulativeMoney;
    }

    public Double getMrReceivables()
    {
        return mrReceivables;
    }

    public void setMrReceivables(Double mrReceivables)
    {
        this.mrReceivables = mrReceivables;
    }

    public Short getMrAllowArrearsAge()
    {
        return mrAllowArrearsAge;
    }

    public void setMrAllowArrearsAge(Short mrAllowArrearsAge)
    {
        this.mrAllowArrearsAge = mrAllowArrearsAge;
    }

    public Short getMrReceivablesArrearsAge()
    {
        return mrReceivablesArrearsAge;
    }

    public void setMrReceivablesArrearsAge(Short mrReceivablesArrearsAge)
    {
        this.mrReceivablesArrearsAge = mrReceivablesArrearsAge;
    }

    public Short getMrAllowFlag()
    {
        return mrAllowFlag;
    }

    public void setMrAllowFlag(Short mrAllowFlag)
    {
        this.mrAllowFlag = mrAllowFlag;
    }

    public Double getMrAllowArrearsAmount()
    {
        return mrAllowArrearsAmount;
    }

    public void setMrAllowArrearsAmount(Double mrAllowArrearsAmount)
    {
        this.mrAllowArrearsAmount = mrAllowArrearsAmount;
    }

    public Short getMrAllowArrearsNumber()
    {
        return mrAllowArrearsNumber;
    }

    public void setMrAllowArrearsNumber(Short mrAllowArrearsNumber)
    {
        this.mrAllowArrearsNumber = mrAllowArrearsNumber;
    }

    public Short getMrPrintFlag()
    {
        return mrPrintFlag;
    }

    public void setMrPrintFlag(Short mrPrintFlag)
    {
        this.mrPrintFlag = mrPrintFlag;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getReceptionId()
    {
        return receptionId;
    }

    public void setReceptionId(String receptionId)
    {
        this.receptionId = receptionId;
    }

    public Date getInterDate()
    {
        return interDate;
    }

    public void setInterDate(Date interDate)
    {
        this.interDate = interDate;
    }

    public String getCustomId()
    {
        return customId;
    }

    public void setCustomId(String customId)
    {
        this.customId = customId;
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

    public String getPreclrSumAmount()
    {
        return preclrSumAmount;
    }

    public void setPreclrSumAmount(String preclrSumAmount)
    {
        this.preclrSumAmount = preclrSumAmount;
    }

    public Double getSurPercharge()
    {
        return surPercharge;
    }

    public void setSurPercharge(Double surPercharge)
    {
        this.surPercharge = surPercharge;
    }

    public Short getMrInoiceType()
    {
        return mrInoiceType;
    }

    public void setMrInoiceType(Short mrInoiceType)
    {
        this.mrInoiceType = mrInoiceType;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public Integer getFcsId()
    {
        return fcsId;
    }

    public void setFcsId(Integer fcsId)
    {
        this.fcsId = fcsId;
    }

    public String getStfId()
    {
        return stfId;
    }

    public void setStfId(String stfId)
    {
        this.stfId = stfId;
    }

    public Short getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId(Short paymentId)
    {
        this.paymentId = paymentId;
    }

    public Double getThisTimeCharge()
    {
        return thisTimeCharge;
    }

    public void setThisTimeCharge(Double thisTimeCharge)
    {
        this.thisTimeCharge = thisTimeCharge;
    }

    public Double getArrears()
    {
        return arrears;
    }

    public void setArrears(Double arrears)
    {
        this.arrears = arrears;
    }

    public Date getFcsDate()
    {
        return fcsDate;
    }

    public void setFcsDate(Date fcsDate)
    {
        this.fcsDate = fcsDate;
    }

    public Short getUnfinished()
    {
        return unfinished;
    }

    public void setUnfinished(Short unfinished)
    {
        this.unfinished = unfinished;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public String getClaimantmTime()
    {
        return claimantmTime;
    }

    public void setClaimantmTime(String claimantmTime)
    {
        this.claimantmTime = claimantmTime;
    }

    public String getResvRealTime()
    {
        return resvRealTime;
    }

    public void setResvRealTime(String resvRealTime)
    {
        this.resvRealTime = resvRealTime;
    }

}
