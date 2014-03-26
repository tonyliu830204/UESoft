package com.syuesoft.base.vo;

import java.io.Serializable;

public class BasVipDiscountRegulationVO implements Serializable
{
    private Integer vipDisRegId; // 会员折扣编号

    private Integer vipLevelId; // 会员等级编号

    private String vipLevelName; // 会员等级名称

    private Integer reptypId; // 维修分类编号

    private String reptypName; // 维修分类名称

    private Long workRegDiscount; // 工时折扣

    private Long materialRegDiscount; // 材料折扣

    private Long totalRegDiscount; // 合计折扣

    private String q;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public BasVipDiscountRegulationVO()
    {

    }

    public BasVipDiscountRegulationVO(Integer vipDisRegId, Integer vipLevelId,
            String vipLevelName, Integer reptypId, String reptypName,
            Long workRegDiscount, Long materialRegDiscount,
            Long totalRegDiscount)
    {
        this.vipDisRegId = vipDisRegId;
        this.vipLevelId = vipLevelId;
        this.vipLevelName = vipLevelName;
        this.reptypId = reptypId;
        this.reptypName = reptypName;
        this.workRegDiscount = workRegDiscount;
        this.materialRegDiscount = materialRegDiscount;
        this.totalRegDiscount = totalRegDiscount;
    }

    public Integer getVipDisRegId()
    {
        return vipDisRegId;
    }

    public void setVipDisRegId(Integer vipDisRegId)
    {
        this.vipDisRegId = vipDisRegId;
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

    public Integer getReptypId()
    {
        return reptypId;
    }

    public void setReptypId(Integer reptypId)
    {
        this.reptypId = reptypId;
    }

    public String getReptypName()
    {
        return reptypName;
    }

    public void setReptypName(String reptypName)
    {
        this.reptypName = reptypName;
    }

    public Long getWorkRegDiscount()
    {
        return workRegDiscount;
    }

    public void setWorkRegDiscount(Long workRegDiscount)
    {
        this.workRegDiscount = workRegDiscount;
    }

    public Long getMaterialRegDiscount()
    {
        return materialRegDiscount;
    }

    public void setMaterialRegDiscount(Long materialRegDiscount)
    {
        this.materialRegDiscount = materialRegDiscount;
    }

    public Long getTotalRegDiscount()
    {
        return totalRegDiscount;
    }

    public void setTotalRegDiscount(Long totalRegDiscount)
    {
        this.totalRegDiscount = totalRegDiscount;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }
}
