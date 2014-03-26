package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** 
 * @ClassName: BasVipInforAccount 
 * @Description: TODO(会员对账单) 
 * @author HeXin 
 * @date 2013-12-19 上午10:33:14 
 * @version 1.0 
 */
public class BasVipInforAccount extends BaseBean{
    private static final long serialVersionUID = 1L;
    private Integer accountId;                     //对账编号
    private Integer enterpriseId;                  //对账企业
    private Integer parentEnterpriseId;            //对账企业
    private Date accountStartDate;                 //对账开始日期
    private Date accountEndDate;                   //对账结束日期
    private Date accountDate;                      //对账经办日期
    private String accountPerson;                  //对账经办人
    private Double incomeAmount;                   //总收入
    private Double defrayAmount;                   //总支出
    private String logout;                         //注销
    private String remark;                         //备注
    private Set<BasVipInforAccountDetail> basVipInforAccountDetails = new HashSet<BasVipInforAccountDetail>();
    
    public BasVipInforAccount()
    {
        super();
    }
    
    public BasVipInforAccount(Date accountEndDate)
    {
        super();
        this.accountEndDate = accountEndDate;
    }

    public Integer getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Integer accountId)
    {
        this.accountId = accountId;
    }

    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }

    public Integer getParentEnterpriseId()
    {
        return parentEnterpriseId;
    }

    public void setParentEnterpriseId(Integer parentEnterpriseId)
    {
        this.parentEnterpriseId = parentEnterpriseId;
    }

    public Date getAccountStartDate()
    {
        return accountStartDate;
    }

    public void setAccountStartDate(Date accountStartDate)
    {
        this.accountStartDate = accountStartDate;
    }

    public Date getAccountEndDate()
    {
        return accountEndDate;
    }

    public void setAccountEndDate(Date accountEndDate)
    {
        this.accountEndDate = accountEndDate;
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

    public Double getIncomeAmount()
    {
        return incomeAmount;
    }

    public void setIncomeAmount(Double incomeAmount)
    {
        this.incomeAmount = incomeAmount;
    }

    public Double getDefrayAmount()
    {
        return defrayAmount;
    }

    public void setDefrayAmount(Double defrayAmount)
    {
        this.defrayAmount = defrayAmount;
    }
    
    public String getLogout()
    {
        return logout;
    }

    public void setLogout(String logout)
    {
        this.logout = logout;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Set<BasVipInforAccountDetail> getBasVipInforAccountDetails()
    {
        return basVipInforAccountDetails;
    }

    public void setBasVipInforAccountDetails(
            Set<BasVipInforAccountDetail> basVipInforAccountDetails)
    {
        this.basVipInforAccountDetails = basVipInforAccountDetails;
    }
}