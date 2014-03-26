package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasVipUpgrade entity. @author MyEclipse Persistence Tools
 */

public class BasVipUpgrade extends BaseBean {
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private String upgradeId;                                                               //升级、降级单号
	private Date upgradeDate;                                                               //经办日期
	private String auditSituation;                                                          //审核状态
	private Date auditDate;                                                                 //审核日期
	private String auditManagers;                                                           //审核人
	private String managers;                                                                //经办人
	private String enterpriseId;                                                            //经办企业
	private String memo;                                                                    //备注
	private Set<BasVipUpInfo> basInfoUps = new HashSet<BasVipUpInfo>(0);
	
    public String getUpgradeId()
    {
        return upgradeId;
    }
    public void setUpgradeId(String upgradeId)
    {
        this.upgradeId = upgradeId;
    }
    public Date getUpgradeDate()
    {
        return upgradeDate;
    }
    public void setUpgradeDate(Date upgradeDate)
    {
        this.upgradeDate = upgradeDate;
    }
    public String getAuditSituation()
    {
        return auditSituation;
    }
    public void setAuditSituation(String auditSituation)
    {
        this.auditSituation = auditSituation;
    }
    public Date getAuditDate()
    {
        return auditDate;
    }
    public void setAuditDate(Date auditDate)
    {
        this.auditDate = auditDate;
    }
    public String getAuditManagers()
    {
        return auditManagers;
    }
    public void setAuditManagers(String auditManagers)
    {
        this.auditManagers = auditManagers;
    }
    public String getManagers()
    {
        return managers;
    }
    public void setManagers(String managers)
    {
        this.managers = managers;
    }
    public String getMemo()
    {
        return memo;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public Set<BasVipUpInfo> getBasInfoUps()
    {
        return basInfoUps;
    }
    public void setBasInfoUps(Set<BasVipUpInfo> basInfoUps)
    {
        this.basInfoUps = basInfoUps;
    }
    public String getEnterpriseId()
    {
        return enterpriseId;
    }
    public void setEnterpriseId(String enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }
}