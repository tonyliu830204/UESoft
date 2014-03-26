package com.syuesoft.model;

public class BasVipUpInfo extends BaseBean{
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer upgradeDetailId;                                             //明细编号
    private BasVipUpgrade basVipUpgrade;                                         //汇总
	private BasVipInfor basVipInfor;                                             //会员
	private String oldVipLevelId;                                                //会员原级别
    private String newVipLevelId;                                                //会员新级别
    private Double deductionIntegration;                                         //扣除积分
    private Double giftPoints;                                                   //赠送积分
    
    public Integer getUpgradeDetailId()
    {
        return upgradeDetailId;
    }
    public void setUpgradeDetailId(Integer upgradeDetailId)
    {
        this.upgradeDetailId = upgradeDetailId;
    }
    public BasVipUpgrade getBasVipUpgrade()
    {
        return basVipUpgrade;
    }
    public void setBasVipUpgrade(BasVipUpgrade basVipUpgrade)
    {
        this.basVipUpgrade = basVipUpgrade;
    }
    public BasVipInfor getBasVipInfor()
    {
        return basVipInfor;
    }
    public void setBasVipInfor(BasVipInfor basVipInfor)
    {
        this.basVipInfor = basVipInfor;
    }
    public String getOldVipLevelId()
    {
        return oldVipLevelId;
    }
    public void setOldVipLevelId(String oldVipLevelId)
    {
        this.oldVipLevelId = oldVipLevelId;
    }
    public String getNewVipLevelId()
    {
        return newVipLevelId;
    }
    public void setNewVipLevelId(String newVipLevelId)
    {
        this.newVipLevelId = newVipLevelId;
    }
    public Double getDeductionIntegration()
    {
        return deductionIntegration;
    }
    public void setDeductionIntegration(Double deductionIntegration)
    {
        this.deductionIntegration = deductionIntegration;
    }
    public Double getGiftPoints()
    {
        return giftPoints;
    }
    public void setGiftPoints(Double giftPoints)
    {
        this.giftPoints = giftPoints;
    }
}