package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasVipGiftexchange entity. @author MyEclipse Persistence Tools
 */

public class BasVipGiftexchange extends BaseBean{
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer exchangeId;                  //兑换编号
	private String vipId;                       //会员编号
	private Date exchangeDate;                  //兑换日期
	private String exchangeUser;                //兑换客户名
	private String operator;                    //经办人
    private Double amount;                      //兑换总金额
    private Double total_score;                 //兑换总积分
    private Date auditDate;                     //审核日期
    private String auditSituation;              //审核状态
    private String auditManager;                //审核人
    private String enterprise_id;               //兑换企业编号
	private Set<BasVipGiftexchangeDetail> basVipGiftexchangeDetails = new HashSet<BasVipGiftexchangeDetail>(0);
	
    public Integer getExchangeId()
    {
        return exchangeId;
    }
    public void setExchangeId(Integer exchangeId)
    {
        this.exchangeId = exchangeId;
    }
    public String getVipId()
    {
        return vipId;
    }
    public void setVipId(String vipId)
    {
        this.vipId = vipId;
    }
    public Date getExchangeDate()
    {
        return exchangeDate;
    }
    public void setExchangeDate(Date exchangeDate)
    {
        this.exchangeDate = exchangeDate;
    }
    public Date getAuditDate()
    {
        return auditDate;
    }
    public void setAuditDate(Date auditDate)
    {
        this.auditDate = auditDate;
    }
    public String getAuditSituation()
    {
        return auditSituation;
    }
    public void setAuditSituation(String auditSituation)
    {
        this.auditSituation = auditSituation;
    }
    public String getAuditManager()
    {
        return auditManager;
    }
    public void setAuditManager(String auditManager)
    {
        this.auditManager = auditManager;
    }
    public String getExchangeUser()
    {
        return exchangeUser;
    }
    public void setExchangeUser(String exchangeUser)
    {
        this.exchangeUser = exchangeUser;
    }
    public String getOperator()
    {
        return operator;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }
    public Double getAmount()
    {
        return amount;
    }
    public void setAmount(Double amount)
    {
        this.amount = amount;
    }
    public Double getTotal_score()
    {
        return total_score;
    }
    public void setTotal_score(Double totalScore)
    {
        total_score = totalScore;
    }
    public String getEnterprise_id()
    {
        return enterprise_id;
    }
    public void setEnterprise_id(String enterpriseId)
    {
        enterprise_id = enterpriseId;
    }
    public Set<BasVipGiftexchangeDetail> getBasVipGiftexchangeDetails()
    {
        return basVipGiftexchangeDetails;
    }
    public void setBasVipGiftexchangeDetails(Set<BasVipGiftexchangeDetail> basVipGiftexchangeDetails)
    {
        this.basVipGiftexchangeDetails = basVipGiftexchangeDetails;
    }
}