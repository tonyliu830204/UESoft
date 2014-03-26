package com.syuesoft.base.vo;

import java.io.Serializable;

public class BasVipGruopVO implements Serializable
{
    private Integer vipGruopId; // 会员分组编号

    private String vipGruopName; // 会员分组名称

    private String vipGruopNote; // 备注
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public BasVipGruopVO()
    {

    }

    public BasVipGruopVO(Integer vipGruopId, String vipGruopName,
            String vipGruopNote)
    {
        this.vipGruopId = vipGruopId;
        this.vipGruopName = vipGruopName;
        this.vipGruopNote = vipGruopNote;
    }

    public Integer getVipGruopId()
    {
        return vipGruopId;
    }

    public void setVipGruopId(Integer vipGruopId)
    {
        this.vipGruopId = vipGruopId;
    }

    public String getVipGruopName()
    {
        return vipGruopName;
    }

    public void setVipGruopName(String vipGruopName)
    {
        this.vipGruopName = vipGruopName;
    }

    public String getVipGruopNote()
    {
        return vipGruopNote;
    }

    public void setVipGruopNote(String vipGruopNote)
    {
        this.vipGruopNote = vipGruopNote;
    }

}
