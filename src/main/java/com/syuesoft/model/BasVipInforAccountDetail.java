package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** 
 * @ClassName: BasVipInforAccountDetail 
 * @Description: TODO(对账单明细) 
 * @author HeXin 
 * @date 2013-12-20 下午03:50:08 
 * @version 1.0 
 */
public class BasVipInforAccountDetail extends BaseBean{

    private static final long serialVersionUID = 1L;
    private Integer detailId;                      //明显编号
    private BasVipInfor basVipInfor;               //对账会员
    private BasVipInforAccount BasVipInforAccount; //对账单
    private Double storedValueAmount;              //总储值额
    private Double expenditureAmount;              //总消费额
    private Double refundAmount;                   //应退款额
    private Double receiptAccount;                 //应收款额
    private Double refundedAmount;                 //已退款额
    private Double receiptedAccount;               //已收款额   
    private String tradeBill;                      //交易单号
    private Date tradeDate;                        //交易日期
    private String finalStage;                     //是否终结
    private String recAuditOper;                   //审核人
    private Date recAuditDate;                     //审核日期
    private String recAuditStatus;                 //审核状态
    private Integer enterpriseId;                  //经办企业
    private Set<BasVipInforAccountMoneyDetail> basVipInforAccountMoneyDetails = new HashSet<BasVipInforAccountMoneyDetail>();
    
    public Integer getDetailId()
    {
        return detailId;
    }
    public void setDetailId(Integer detailId)
    {
        this.detailId = detailId;
    }
    public BasVipInfor getBasVipInfor()
    {
        return basVipInfor;
    }
    public void setBasVipInfor(BasVipInfor basVipInfor)
    {
        this.basVipInfor = basVipInfor;
    }
    public BasVipInforAccount getBasVipInforAccount()
    {
        return BasVipInforAccount;
    }
    public void setBasVipInforAccount(BasVipInforAccount basVipInforAccount)
    {
        BasVipInforAccount = basVipInforAccount;
    }
    public Double getStoredValueAmount()
    {
        return storedValueAmount;
    }
    public void setStoredValueAmount(Double storedValueAmount)
    {
        this.storedValueAmount = storedValueAmount;
    }
    public Double getExpenditureAmount()
    {
        return expenditureAmount;
    }
    public void setExpenditureAmount(Double expenditureAmount)
    {
        this.expenditureAmount = expenditureAmount;
    }
    public Double getRefundAmount()
    {
        return refundAmount;
    }
    public void setRefundAmount(Double refundAmount)
    {
        this.refundAmount = refundAmount;
    }
    public Double getReceiptAccount()
    {
        return receiptAccount;
    }
    public void setReceiptAccount(Double receiptAccount)
    {
        this.receiptAccount = receiptAccount;
    }
    public Double getRefundedAmount()
    {
        return refundedAmount;
    }
    public void setRefundedAmount(Double refundedAmount)
    {
        this.refundedAmount = refundedAmount;
    }
    public Double getReceiptedAccount()
    {
        return receiptedAccount;
    }
    public void setReceiptedAccount(Double receiptedAccount)
    {
        this.receiptedAccount = receiptedAccount;
    }
    public String getFinalStage()
    {
        return finalStage;
    }
    public void setFinalStage(String finalStage)
    {
        this.finalStage = finalStage;
    }
    public Set<BasVipInforAccountMoneyDetail> getBasVipInforAccountMoneyDetails()
    {
        return basVipInforAccountMoneyDetails;
    }
    public void setBasVipInforAccountMoneyDetails(
            Set<BasVipInforAccountMoneyDetail> basVipInforAccountMoneyDetails)
    {
        this.basVipInforAccountMoneyDetails = basVipInforAccountMoneyDetails;
    }
    public String getRecAuditOper()
    {
        return recAuditOper;
    }
    public void setRecAuditOper(String recAuditOper)
    {
        this.recAuditOper = recAuditOper;
    }
    public Date getRecAuditDate()
    {
        return recAuditDate;
    }
    public void setRecAuditDate(Date recAuditDate)
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
    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }
    public void setEnterpriseId(Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }
    public String getTradeBill()
    {
        return tradeBill;
    }
    public void setTradeBill(String tradeBill)
    {
        this.tradeBill = tradeBill;
    }
    public Date getTradeDate()
    {
        return tradeDate;
    }
    public void setTradeDate(Date tradeDate)
    {
        this.tradeDate = tradeDate;
    }
}