package com.syuesoft.model;

import java.util.Date;

/** 
 * @ClassName: BasVipInforAccountDetail 
 * @Description: TODO(会员对账单收款明细) 
 * @author HeXin 
 * @date 2013-12-19 上午10:43:38 
 * @version 1.0 
 */
public class BasVipInforAccountMoneyDetail extends BaseBean{
    private static final long serialVersionUID = 1L;
    private Integer moneyDetailId;                                   //明细编号
    private BasVipInforAccountDetail BasVipInforAccountDetail;       //会员对账单
    private Double receiptAccount;                                   //收款额
    private String accountWay;                                       //收款方式(刷卡、现金)
    private Date accountDate;                                        //收款经办日期
    private String accountPerson;                                    //收款经办人
    private String accountType;                                      //收款类型(支出、收入)
    private String remark;                                           //备注
    public Integer getMoneyDetailId()
    {
        return moneyDetailId;
    }
    public void setMoneyDetailId(Integer moneyDetailId)
    {
        this.moneyDetailId = moneyDetailId;
    }
    public BasVipInforAccountDetail getBasVipInforAccountDetail()
    {
        return BasVipInforAccountDetail;
    }
    public void setBasVipInforAccountDetail(
            BasVipInforAccountDetail basVipInforAccountDetail)
    {
        BasVipInforAccountDetail = basVipInforAccountDetail;
    }
    public Double getReceiptAccount()
    {
        return receiptAccount;
    }
    public void setReceiptAccount(Double receiptAccount)
    {
        this.receiptAccount = receiptAccount;
    }
    public Date getAccountDate()
    {
        return accountDate;
    }
    public void setAccountDate(Date accountDate)
    {
        this.accountDate = accountDate;
    }
    public String getAccountPerson()
    {
        return accountPerson;
    }
    public void setAccountPerson(String accountPerson)
    {
        this.accountPerson = accountPerson;
    }
    public String getAccountType()
    {
        return accountType;
    }
    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getAccountWay()
    {
        return accountWay;
    }
    public void setAccountWay(String accountWay)
    {
        this.accountWay = accountWay;
    }
}