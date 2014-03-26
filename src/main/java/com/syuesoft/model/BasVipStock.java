package com.syuesoft.model;

import java.util.Date;

/** 
 * @ClassName: BasVipStock 
 * @Description: TODO(会员维修、销售积分) 
 * @author HeXin 
 * @date 2013-12-11 上午11:05:24 
 * @version 1.0 
 */
public class BasVipStock extends BaseBean{

    private static final long serialVersionUID = 1L;
    private Integer stockId;                                     //日志编号
    private BasVipInfor basVipInfor;                             //会员
    private Double vipIntegral;                                  //会员积分
    private String stockType;                                    //积分分类（维修、销售）
    private String workId;                                       //维修单编号、销售单编号
    private String slipId;                                       //结算单编号
    private String operator;                                     //经办人
    private Date HandlingDate;                                   //经办日期
    private String stockRemark;                                  //备注
    private Integer enterpriseId;                                //经办企业
    private Integer parentEnterpriseId;                          //父企业序号
    
    public Integer getStockId()
    {
        return stockId;
    }
    public void setStockId(Integer stockId)
    {
        this.stockId = stockId;
    }
    public BasVipInfor getBasVipInfor()
    {
        return basVipInfor;
    }
    public void setBasVipInfor(BasVipInfor basVipInfor)
    {
        this.basVipInfor = basVipInfor;
    }
    public Double getVipIntegral()
    {
        return vipIntegral;
    }
    public void setVipIntegral(Double vipIntegral)
    {
        this.vipIntegral = vipIntegral;
    }
    public String getStockType()
    {
        return stockType;
    }
    public void setStockType(String stockType)
    {
        this.stockType = stockType;
    }
    public String getStockRemark()
    {
        return stockRemark;
    }
    public void setStockRemark(String stockRemark)
    {
        this.stockRemark = stockRemark;
    }
    public String getWorkId()
    {
        return workId;
    }
    public void setWorkId(String workId)
    {
        this.workId = workId;
    }
    public String getSlipId()
    {
        return slipId;
    }
    public void setSlipId(String slipId)
    {
        this.slipId = slipId;
    }
    public String getOperator()
    {
        return operator;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }
    public Date getHandlingDate()
    {
        return HandlingDate;
    }
    public void setHandlingDate(Date handlingDate)
    {
        HandlingDate = handlingDate;
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
}