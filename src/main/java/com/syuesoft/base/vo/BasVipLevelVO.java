package com.syuesoft.base.vo;

import java.io.Serializable;

public class BasVipLevelVO implements Serializable
{

    private Integer vipLevelId;

    private String vipLevelName;

    private String vipLevelNote;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public BasVipLevelVO()
    {

    }

    public BasVipLevelVO(Integer vipLevelId, String vipLevelName,
            String vipLevelNote)
    {
        this.vipLevelId = vipLevelId;
        this.vipLevelName = vipLevelName;
        this.vipLevelNote = vipLevelNote;
    }

    public Integer getVipLevelId()
    {
        return vipLevelId;
    }

    public void setVipLevelId(Integer vipLevelId)
    {
        this.vipLevelId = vipLevelId;
    }

    public String getVipLevelName()
    {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName)
    {
        this.vipLevelName = vipLevelName;
    }

    public String getVipLevelNote()
    {
        return vipLevelNote;
    }

    public void setVipLevelNote(String vipLevelNote)
    {
        this.vipLevelNote = vipLevelNote;
    }
}
