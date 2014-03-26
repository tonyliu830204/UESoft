package com.syuesoft.model;

import java.util.Date;

/** 
 * @ClassName: BasVipInforDefray 
 * @Description: TODO(会员资金支出) 
 * @author HeXin 
 * @date 2013-12-19 上午09:38:01 
 * @version 1.0 
 */
public class BasVipInforDefray extends BaseBean{
    private static final long serialVersionUID = 1L;
    private Integer defrayId;                          //会员付款编号
    private BasVipInfor basVipInfor;                   //付款会员
    private Date defrayDate;                           //付款经办日期
    private String defrayPerson;                       //付款经办人
    private Integer enterpriseId;                      //付款经办企业
    private Integer parentEnterpriseId;                //父企业序号
    private String defrayType;                         //付款用途（维修、销售）
    private Double defrayAmount;                       //付款金额
    private String workId;                             //维修单编号、销售单编号
    private String slipId;                             //结算单编号
    private String remark;                             //备注
    
    
    public Integer getDefrayId()
    {
        return defrayId;
    }
    public void setDefrayId(Integer defrayId)
    {
        this.defrayId = defrayId;
    }
    public BasVipInfor getBasVipInfor()
    {
        return basVipInfor;
    }
    public void setBasVipInfor(BasVipInfor basVipInfor)
    {
        this.basVipInfor = basVipInfor;
    }
    public Date getDefrayDate()
    {
        return defrayDate;
    }
    public void setDefrayDate(Date defrayDate)
    {
        this.defrayDate = defrayDate;
    }
    public String getDefrayPerson()
    {
        return defrayPerson;
    }
    public void setDefrayPerson(String defrayPerson)
    {
        this.defrayPerson = defrayPerson;
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
    public String getDefrayType()
    {
        return defrayType;
    }
    public void setDefrayType(String defrayType)
    {
        this.defrayType = defrayType;
    }
    public Double getDefrayAmount()
    {
        return defrayAmount;
    }
    public void setDefrayAmount(Double defrayAmount)
    {
        this.defrayAmount = defrayAmount;
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
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}