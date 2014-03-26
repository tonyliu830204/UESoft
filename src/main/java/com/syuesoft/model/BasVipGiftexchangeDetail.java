package com.syuesoft.model;

/**
 * BasVipGiftexchangeDetail entity. @author MyEclipse Persistence Tools
 */

public class BasVipGiftexchangeDetail extends BaseBean{

	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer exchangeDetailId;                        //明显编号
	private BasVipGiftexchange basVipGiftexchange;          //兑换礼品汇总
	private String projectId;                               //礼品编号
	private Double exchangeQuantity;                        //兑换个数
	private Double exchangeAmount;                          //礼品单价
	private Double oneScore;                                //礼品积分
	private Double totalAmount;                             //礼品总价
    private Double totalScore;                              //礼品总积分
	private String exchangeMemo;                            //兑换备注
	
    public Integer getExchangeDetailId()
    {
        return exchangeDetailId;
    }
    public void setExchangeDetailId(Integer exchangeDetailId)
    {
        this.exchangeDetailId = exchangeDetailId;
    }
    public BasVipGiftexchange getBasVipGiftexchange()
    {
        return basVipGiftexchange;
    }
    public void setBasVipGiftexchange(BasVipGiftexchange basVipGiftexchange)
    {
        this.basVipGiftexchange = basVipGiftexchange;
    }
    public Double getExchangeQuantity()
    {
        return exchangeQuantity;
    }
    public void setExchangeQuantity(Double exchangeQuantity)
    {
        this.exchangeQuantity = exchangeQuantity;
    }
    public Double getExchangeAmount()
    {
        return exchangeAmount;
    }
    public void setExchangeAmount(Double exchangeAmount)
    {
        this.exchangeAmount = exchangeAmount;
    }
    
    public String getProjectId()
    {
        return projectId;
    }
    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }
    public Double getOneScore()
    {
        return oneScore;
    }
    public void setOneScore(Double oneScore)
    {
        this.oneScore = oneScore;
    }
    public String getExchangeMemo()
    {
        return exchangeMemo;
    }
    public void setExchangeMemo(String exchangeMemo)
    {
        this.exchangeMemo = exchangeMemo;
    }
    public Double getTotalAmount()
    {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount)
    {
        this.totalAmount = totalAmount;
    }
    public Double getTotalScore()
    {
        return totalScore;
    }
    public void setTotalScore(Double totalScore)
    {
        this.totalScore = totalScore;
    }
}