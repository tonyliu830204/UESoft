package com.syuesoft.base.vo;

public class BasMountingsTypeInfo
{

    private Short ptypeId; // 配件型号编号

    private Short pbrdId; // 配件品牌ID

    private String pbrdName;

    private String ptypeName; // 配件型号名称

    private Double repairRate;

    private Double sellRate;

    private Double pointRate;

    private Double specialRate;

    private Double claimRate;
    
    private String sort;

    private String order;

    private int page;

    private int rows;

    private String q;
    
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
 	
    public BasMountingsTypeInfo()
    {

    }

    public BasMountingsTypeInfo(Short ptypeId, Short pbrdId, String pbrdName,
            String ptypeName, Double repairRate, Double sellRate,
            Double pointRate, Double specialRate,Double claimRate)
    {
        this.ptypeId = ptypeId;
        this.pbrdId = pbrdId;
        this.pbrdName = pbrdName;
        this.ptypeName = ptypeName;
        this.repairRate = repairRate;
        this.sellRate = sellRate;
        this.pointRate = pointRate;
        this.specialRate = specialRate;
        this.claimRate=claimRate;
    }

    public Double getClaimRate() {
		return claimRate;
	}

	public void setClaimRate(Double claimRate) {
		this.claimRate = claimRate;
	}

	public Short getPtypeId()
    {
        return ptypeId;
    }

    public void setPtypeId(Short ptypeId)
    {
        this.ptypeId = ptypeId;
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

    public Double getRepairRate()
    {
        return repairRate;
    }

    public void setRepairRate(Double repairRate)
    {
        this.repairRate = repairRate;
    }

    public Double getSellRate()
    {
        return sellRate;
    }

    public void setSellRate(Double sellRate)
    {
        this.sellRate = sellRate;
    }

    public Double getPointRate()
    {
        return pointRate;
    }

    public void setPointRate(Double pointRate)
    {
        this.pointRate = pointRate;
    }

    public Double getSpecialRate()
    {
        return specialRate;
    }

    public void setSpecialRate(Double specialRate)
    {
        this.specialRate = specialRate;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public String getPbrdName()
    {
        return pbrdName;
    }

    public void setPbrdName(String pbrdName)
    {
        this.pbrdName = pbrdName;
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
