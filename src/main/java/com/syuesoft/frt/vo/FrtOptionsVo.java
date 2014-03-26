package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FrtOptionsVo extends BaseBeanVo
{
    private String q;

    private String key;

    private Short cbrdId;

    private Short ctypeId;

    private String CustomId;

    private Short pbrdId;

    private String ptypeName;
    
    private Boolean flag;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public Short getCbrdId()
    {
        return cbrdId;
    }

    public void setCbrdId(Short cbrdId)
    {
        this.cbrdId = cbrdId;
    }

    public Short getCtypeId()
    {
        return ctypeId;
    }

    public void setCtypeId(Short ctypeId)
    {
        this.ctypeId = ctypeId;
    }

    public String getCustomId()
    {
        return CustomId;
    }

    public void setCustomId(String customId)
    {
        CustomId = customId;
    }

    public Short getPbrdId()
    {
        return pbrdId;
    }

    public void setPbrdId(Short pbrdId)
    {
        this.pbrdId = pbrdId;
    }

    public String getPtypeName()
    {
        return ptypeName;
    }

    public void setPtypeName(String ptypeName)
    {
        this.ptypeName = ptypeName;
    }

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
