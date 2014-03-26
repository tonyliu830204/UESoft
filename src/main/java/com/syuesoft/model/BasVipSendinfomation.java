package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BasVipSendinfomation extends BaseBean {
    private static final long serialVersionUID = 1L;
    private String infoId;
	private String sendNumber;
	private String sendContent;
	private Date nowSendDate;
	private Date otherSendDate;
	private Integer enterpriseId;
	private Set<BasVipSendinfomations> BasVipSendinfomations = new HashSet<BasVipSendinfomations>();
	
    public String getInfoId()
    {
        return infoId;
    }
    public void setInfoId(String infoId)
    {
        this.infoId = infoId;
    }
    public String getSendNumber()
    {
        return sendNumber;
    }
    public void setSendNumber(String sendNumber)
    {
        this.sendNumber = sendNumber;
    }
    public String getSendContent()
    {
        return sendContent;
    }
    public void setSendContent(String sendContent)
    {
        this.sendContent = sendContent;
    }
    public Date getNowSendDate()
    {
        return nowSendDate;
    }
    public void setNowSendDate(Date nowSendDate)
    {
        this.nowSendDate = nowSendDate;
    }
    public Date getOtherSendDate()
    {
        return otherSendDate;
    }
    public void setOtherSendDate(Date otherSendDate)
    {
        this.otherSendDate = otherSendDate;
    }
    public Set<BasVipSendinfomations> getBasVipSendinfomations()
    {
        return BasVipSendinfomations;
    }
    public void setBasVipSendinfomations(
            Set<BasVipSendinfomations> basVipSendinfomations)
    {
        BasVipSendinfomations = basVipSendinfomations;
    }
    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }
    public void setEnterpriseId(Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }
}