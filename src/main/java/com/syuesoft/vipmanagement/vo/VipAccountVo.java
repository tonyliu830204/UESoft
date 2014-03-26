package com.syuesoft.vipmanagement.vo;

public class VipAccountVo
{
    private int page;
    private int rows;
    private String sort;
    private String order;
    
    private String accountId;                      //对账编号
    private String enterpriseId;                   //经办企业
    private String parentEnterpriseId;             //经办父企业
    private String openterpriseId;                 //对账企业
    private String openterpriseName;               //对账企业名称
    private String vipId;                          //付款会员
    private String accountStartDate;               //对账开始日期
    private String accountEndDate;                 //对账结束日期
    private String storedValueAmount;              //总储值额
    private String expenditureAmount;              //总消费额
    private String refundAmount;                   //应退款额
    private String receiptAccount;                 //应收款额
    private String refundedAmount;                 //已退款额
    private String receiptedAccount;               //已收款额
    private String incomeAmount;                   //总收入
    private String defrayAmount;                   //总支出
    private String accountDate;                    //对账经办日期
    private String accountDate2;                   //对账经办日期
    private String accountPerson;                  //对账经办人
    private String accountPersonName;              //对账经办人
    private String finalStage;                     //是否终结
    private String finalStageValue;                //是否终结
    private String recAuditOper;                   //审核人
    private String recAuditOperName;               //审核人
    private String recAuditDate;                   //审核日期
    private String recAuditStatus;                 //审核状态
    private String recAuditStatusName;             //审核状态
    private String remark;                         //备注
    private String logout;                         //注销
    private String logoutName;                     //注销
    
    private String carLicense;                     //车牌照
    private String carVin;                         //车架号
    private String vipNumber;                      //会员卡号
    private String vipName;                        //会员名
    
    private String detailId;                                 //明显编号
    private String detailMoneyId;                            //收款明细
    private String detailgatheringAccount;                   //收款额
    private String gatheringAccount;                         //收款额
    private String detailaccountDate;                        //收款经办日期
    private String detailaccountPerson;                      //收款经办人
    private String detailaccountPersonName;                  //收款经办人
    private String detailaccountType;                        //收款类型(支出、收入)
    private String detailaccountTypeName;                    //收款类型(支出、收入)
    private String detailaccountWay;                         //收款方式(刷卡、现金)
    private String detailaccountWayName;                     //收款方式(刷卡、现金)
    private String detailremark;                             //备注
    private String detailreceiptAccount;                     //应收款额
    private String detailrefundedAmount;                     //应退款额
    
    
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
    public String getAccountId()
    {
        return accountId;
    }
    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }
    public String getEnterpriseId()
    {
        return enterpriseId;
    }
    public void setEnterpriseId(String enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }
    public String getParentEnterpriseId()
    {
        return parentEnterpriseId;
    }
    public void setParentEnterpriseId(String parentEnterpriseId)
    {
        this.parentEnterpriseId = parentEnterpriseId;
    }
    public String getVipId()
    {
        return vipId;
    }
    public void setVipId(String vipId)
    {
        this.vipId = vipId;
    }
    public String getAccountStartDate()
    {
        return accountStartDate;
    }
    public void setAccountStartDate(String accountStartDate)
    {
        this.accountStartDate = accountStartDate;
    }
    public String getAccountEndDate()
    {
        return accountEndDate;
    }
    public void setAccountEndDate(String accountEndDate)
    {
        this.accountEndDate = accountEndDate;
    }
    public String getStoredValueAmount()
    {
        return storedValueAmount;
    }
    public void setStoredValueAmount(String storedValueAmount)
    {
        this.storedValueAmount = storedValueAmount;
    }
    public String getExpenditureAmount()
    {
        return expenditureAmount;
    }
    public void setExpenditureAmount(String expenditureAmount)
    {
        this.expenditureAmount = expenditureAmount;
    }
    public String getRefundAmount()
    {
        return refundAmount;
    }
    public void setRefundAmount(String refundAmount)
    {
        this.refundAmount = refundAmount;
    }
    public String getReceiptAccount()
    {
        return receiptAccount;
    }
    public void setReceiptAccount(String receiptAccount)
    {
        this.receiptAccount = receiptAccount;
    }
    public String getRefundedAmount()
    {
        return refundedAmount;
    }
    public void setRefundedAmount(String refundedAmount)
    {
        this.refundedAmount = refundedAmount;
    }
    public String getReceiptedAccount()
    {
        return receiptedAccount;
    }
    public void setReceiptedAccount(String receiptedAccount)
    {
        this.receiptedAccount = receiptedAccount;
    }
    public String getIncomeAmount()
    {
        return incomeAmount;
    }
    public void setIncomeAmount(String incomeAmount)
    {
        this.incomeAmount = incomeAmount;
    }
    public String getDefrayAmount()
    {
        return defrayAmount;
    }
    public void setDefrayAmount(String defrayAmount)
    {
        this.defrayAmount = defrayAmount;
    }
    public String getAccountDate()
    {
        return accountDate;
    }
    public void setAccountDate(String accountDate)
    {
        this.accountDate = accountDate;
    }
    
    public String getAccountDate2()
    {
        return accountDate2;
    }
    public void setAccountDate2(String accountDate2)
    {
        this.accountDate2 = accountDate2;
    }
    public String getAccountPerson()
    {
        return accountPerson;
    }
    public void setAccountPerson(String accountPerson)
    {
        this.accountPerson = accountPerson;
    }
    public String getAccountPersonName()
    {
        return accountPersonName;
    }
    public void setAccountPersonName(String accountPersonName)
    {
        this.accountPersonName = accountPersonName;
    }
    public String getFinalStage()
    {
        return finalStage;
    }
    public void setFinalStage(String finalStage)
    {
        this.finalStage = finalStage;
    }
    public String getRecAuditOper()
    {
        return recAuditOper;
    }
    public void setRecAuditOper(String recAuditOper)
    {
        this.recAuditOper = recAuditOper;
    }
    public String getRecAuditOperName()
    {
        return recAuditOperName;
    }
    public void setRecAuditOperName(String recAuditOperName)
    {
        this.recAuditOperName = recAuditOperName;
    }
    public String getRecAuditDate()
    {
        return recAuditDate;
    }
    public void setRecAuditDate(String recAuditDate)
    {
        this.recAuditDate = recAuditDate;
    }
    public String getRecAuditStatus()
    {
        return recAuditStatus;
    }
    public void setRecAuditStatus(String recAuditStatus)
    {
        this.recAuditStatus = recAuditStatus;
    }
    public String getRecAuditStatusName()
    {
        return recAuditStatusName;
    }
    public void setRecAuditStatusName(String recAuditStatusName)
    {
        this.recAuditStatusName = recAuditStatusName;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
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
    public String getVipNumber()
    {
        return vipNumber;
    }
    public void setVipNumber(String vipNumber)
    {
        this.vipNumber = vipNumber;
    }
    public String getDetailId()
    {
        return detailId;
    }
    public void setDetailId(String detailId)
    {
        this.detailId = detailId;
    }
    public String getDetailMoneyId()
    {
        return detailMoneyId;
    }
    public void setDetailMoneyId(String detailMoneyId)
    {
        this.detailMoneyId = detailMoneyId;
    }
    public String getDetailreceiptAccount()
    {
        return detailreceiptAccount;
    }
    public void setDetailreceiptAccount(String detailreceiptAccount)
    {
        this.detailreceiptAccount = detailreceiptAccount;
    }
    public String getDetailaccountDate()
    {
        return detailaccountDate;
    }
    public void setDetailaccountDate(String detailaccountDate)
    {
        this.detailaccountDate = detailaccountDate;
    }
    public String getDetailaccountPerson()
    {
        return detailaccountPerson;
    }
    public void setDetailaccountPerson(String detailaccountPerson)
    {
        this.detailaccountPerson = detailaccountPerson;
    }
    public String getDetailaccountPersonName()
    {
        return detailaccountPersonName;
    }
    public void setDetailaccountPersonName(String detailaccountPersonName)
    {
        this.detailaccountPersonName = detailaccountPersonName;
    }
    public String getDetailaccountType()
    {
        return detailaccountType;
    }
    public void setDetailaccountType(String detailaccountType)
    {
        this.detailaccountType = detailaccountType;
    }
    public String getDetailaccountTypeName()
    {
        return detailaccountTypeName;
    }
    public void setDetailaccountTypeName(String detailaccountTypeName)
    {
        this.detailaccountTypeName = detailaccountTypeName;
    }
    public String getDetailremark()
    {
        return detailremark;
    }
    public void setDetailremark(String detailremark)
    {
        this.detailremark = detailremark;
    }
    public String getVipName()
    {
        return vipName;
    }
    public void setVipName(String vipName)
    {
        this.vipName = vipName;
    }
    public String getDetailgatheringAccount()
    {
        return detailgatheringAccount;
    }
    public void setDetailgatheringAccount(String detailgatheringAccount)
    {
        this.detailgatheringAccount = detailgatheringAccount;
    }
    public String getDetailrefundedAmount()
    {
        return detailrefundedAmount;
    }
    public void setDetailrefundedAmount(String detailrefundedAmount)
    {
        this.detailrefundedAmount = detailrefundedAmount;
    }
    public String getFinalStageValue()
    {
        return finalStageValue;
    }
    public void setFinalStageValue(String finalStageValue)
    {
        this.finalStageValue = finalStageValue;
    }
    public String getLogout()
    {
        return logout;
    }
    public void setLogout(String logout)
    {
        this.logout = logout;
    }
    public String getLogoutName()
    {
        return logoutName;
    }
    public void setLogoutName(String logoutName)
    {
        this.logoutName = logoutName;
    }
    public String getDetailaccountWay()
    {
        return detailaccountWay;
    }
    public void setDetailaccountWay(String detailaccountWay)
    {
        this.detailaccountWay = detailaccountWay;
    }
    public String getDetailaccountWayName()
    {
        return detailaccountWayName;
    }
    public void setDetailaccountWayName(String detailaccountWayName)
    {
        this.detailaccountWayName = detailaccountWayName;
    }
    public String getOpenterpriseId()
    {
        return openterpriseId;
    }
    public void setOpenterpriseId(String openterpriseId)
    {
        this.openterpriseId = openterpriseId;
    }
    public String getOpenterpriseName()
    {
        return openterpriseName;
    }
    public void setOpenterpriseName(String openterpriseName)
    {
        this.openterpriseName = openterpriseName;
    }
    public String getGatheringAccount()
    {
        return gatheringAccount;
    }
    public void setGatheringAccount(String gatheringAccount)
    {
        this.gatheringAccount = gatheringAccount;
    }
}