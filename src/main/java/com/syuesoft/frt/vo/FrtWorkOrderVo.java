package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;
import com.syuesoft.contstants.Contstants;

public class FrtWorkOrderVo extends BaseBeanVo
{
    private String receptionId;

    private String interDateBegin;

    private String interDateEnd;

    private String carVin;

    private String cbrdId;

    private String preclrInoiceType;

    private String carId;

    private String customId;

    private String preclrFlg;

    private String preclrTimeBegin;

    private String preclrTimeEnd;

    private Short repgrpId;

    private Short receivePerson;

    private Short servicePerson;

    private Short reptId;

    private Short claimsId;

    private Boolean showUnBalance;

    private String itemName;

    private String balanceClass = Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT;

    private String arrangeWise;

    private Short receptionTechnician;

    private String ptypeId;

    private Short bringPerson;

    private String partsName;

    private String claimantTimeBegin;

    private String claimantTimeEnd;

    private String BalanceTimeBegin;

    private String BalanceTimeEnd;

    private String partsCode;

    private String paymentThing;

    private String preclrTime;

    private Double sumCount;

    private Double preclrAmount;

    private String relcampName;

    private String reptName;

    private String repitemId;

    private String repitemName;

    private String stfName;

    private Double partsPrice;

    private String punitName;

    private String partsId;

    private String stomDateBegin;

    private String stomDateEnd;

    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    private String preclrId;

    private String claimantmId;

    private String state; // treegrid打开还是关闭

    private String iconCls;

    private String _parentId;

    private String q;

    private int page;

    private int rows;

    private String sort;

    private String order;

    public FrtWorkOrderVo()
    {

    }

    public FrtWorkOrderVo(int page, int rows, String sort, String order)
    {
        this.page = page;
        this.rows = rows;
        this.sort = sort;
        this.order = order;
    }

    public String getReceptionId()
    {
        return receptionId;
    }

    public void setReceptionId(String receptionId)
    {
        this.receptionId = receptionId;
    }

    public String getInterDateBegin()
    {
        return interDateBegin;
    }

    public void setInterDateBegin(String interDateBegin)
    {
        this.interDateBegin = interDateBegin;
    }

    public String getInterDateEnd()
    {
        return interDateEnd;
    }

    public void setInterDateEnd(String interDateEnd)
    {
        this.interDateEnd = interDateEnd;
    }

    public String getCarVin()
    {
        return carVin;
    }

    public void setCarVin(String carVin)
    {
        this.carVin = carVin;
    }

    public String getCbrdId()
    {
        return cbrdId;
    }

    public void setCbrdId(String cbrdId)
    {
        this.cbrdId = cbrdId;
    }

    public String getPreclrInoiceType()
    {
        return preclrInoiceType;
    }

    public void setPreclrInoiceType(String preclrInoiceType)
    {
        this.preclrInoiceType = preclrInoiceType;
    }

    public String getCarId()
    {
        return carId;
    }

    public void setCarId(String carId)
    {
        this.carId = carId;
    }

    public String getCustomId()
    {
        return customId;
    }

    public void setCustomId(String customId)
    {
        this.customId = customId;
    }

    public String getPreclrFlg()
    {
        return preclrFlg;
    }

    public void setPreclrFlg(String preclrFlg)
    {
        this.preclrFlg = preclrFlg;
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

    public Short getRepgrpId()
    {
        return repgrpId;
    }

    public void setRepgrpId(Short repgrpId)
    {
        this.repgrpId = repgrpId;
    }

    public Short getReceivePerson()
    {
        return receivePerson;
    }

    public void setReceivePerson(Short receivePerson)
    {
        this.receivePerson = receivePerson;
    }

    public Short getServicePerson()
    {
        return servicePerson;
    }

    public void setServicePerson(Short servicePerson)
    {
        this.servicePerson = servicePerson;
    }

    public Short getReptId()
    {
        return reptId;
    }

    public void setReptId(Short reptId)
    {
        this.reptId = reptId;
    }

    public Short getClaimsId()
    {
        return claimsId;
    }

    public void setClaimsId(Short claimsId)
    {
        this.claimsId = claimsId;
    }

    public Boolean getShowUnBalance()
    {
        return showUnBalance;
    }

    public void setShowUnBalance(Boolean showUnBalance)
    {
        this.showUnBalance = showUnBalance;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getBalanceClass()
    {
        return balanceClass;
    }

    public void setBalanceClass(String balanceClass)
    {
        this.balanceClass = balanceClass;
    }

    public String getArrangeWise()
    {
        return arrangeWise;
    }

    public void setArrangeWise(String arrangeWise)
    {
        this.arrangeWise = arrangeWise;
    }

    public Short getReceptionTechnician()
    {
        return receptionTechnician;
    }

    public void setReceptionTechnician(Short receptionTechnician)
    {
        this.receptionTechnician = receptionTechnician;
    }

    public String getPtypeId()
    {
        return ptypeId;
    }

    public void setPtypeId(String ptypeId)
    {
        this.ptypeId = ptypeId;
    }

    public Short getBringPerson()
    {
        return bringPerson;
    }

    public void setBringPerson(Short bringPerson)
    {
        this.bringPerson = bringPerson;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String get_parentId()
    {
        return _parentId;
    }

    public void set_parentId(String parentId)
    {
        _parentId = parentId;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
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

    public String getPreclrId()
    {
        return preclrId;
    }

    public void setPreclrId(String preclrId)
    {
        this.preclrId = preclrId;
    }

    public String getClaimantmId()
    {
        return claimantmId;
    }

    public void setClaimantmId(String claimantmId)
    {
        this.claimantmId = claimantmId;
    }

    public String getClaimantTimeBegin()
    {
        return claimantTimeBegin;
    }

    public void setClaimantTimeBegin(String claimantTimeBegin)
    {
        this.claimantTimeBegin = claimantTimeBegin;
    }

    public String getClaimantTimeEnd()
    {
        return claimantTimeEnd;
    }

    public void setClaimantTimeEnd(String claimantTimeEnd)
    {
        this.claimantTimeEnd = claimantTimeEnd;
    }

    public String getBalanceTimeBegin()
    {
        return BalanceTimeBegin;
    }

    public void setBalanceTimeBegin(String balanceTimeBegin)
    {
        BalanceTimeBegin = balanceTimeBegin;
    }

    public String getBalanceTimeEnd()
    {
        return BalanceTimeEnd;
    }

    public void setBalanceTimeEnd(String balanceTimeEnd)
    {
        BalanceTimeEnd = balanceTimeEnd;
    }

    public String getPartsCode()
    {
        return partsCode;
    }

    public void setPartsCode(String partsCode)
    {
        this.partsCode = partsCode;
    }

    public String getPaymentThing()
    {
        return paymentThing;
    }

    public void setPaymentThing(String paymentThing)
    {
        this.paymentThing = paymentThing;
    }

    public String getPreclrTime()
    {
        return preclrTime;
    }

    public void setPreclrTime(String preclrTime)
    {
        this.preclrTime = preclrTime;
    }

    public Double getPreclrAmount()
    {
        return preclrAmount;
    }

    public void setPreclrAmount(Double preclrAmount)
    {
        this.preclrAmount = preclrAmount;
    }

    public String getRelcampName()
    {
        return relcampName;
    }

    public void setRelcampName(String relcampName)
    {
        this.relcampName = relcampName;
    }

    public String getReptName()
    {
        return reptName;
    }

    public void setReptName(String reptName)
    {
        this.reptName = reptName;
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

    public Double getSumCount()
    {
        return sumCount;
    }

    public void setSumCount(Double sumCount)
    {
        this.sumCount = sumCount;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public Double getPartsPrice()
    {
        return partsPrice;
    }

    public void setPartsPrice(Double partsPrice)
    {
        this.partsPrice = partsPrice;
    }

    public String getPunitName()
    {
        return punitName;
    }

    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
    }

    public String getPartsId()
    {
        return partsId;
    }

    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }

    public String getStomDateBegin()
    {
        return stomDateBegin;
    }

    public void setStomDateBegin(String stomDateBegin)
    {
        this.stomDateBegin = stomDateBegin;
    }

    public String getStomDateEnd()
    {
        return stomDateEnd;
    }

    public void setStomDateEnd(String stomDateEnd)
    {
        this.stomDateEnd = stomDateEnd;
    }

}
