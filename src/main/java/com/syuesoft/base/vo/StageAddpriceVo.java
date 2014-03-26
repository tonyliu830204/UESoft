package com.syuesoft.base.vo;

public class StageAddpriceVo
{

    private String stageId;

    private String startAmont;

    private String endAmont;

    private String repairRate;

    private String sellRate;

    private String pointRate;

    private String specialRate;
    
    private String claimRate;

    private String sort;

    private String order;

    private int rows;

    private int page;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
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

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public String getStageId()
    {
        return stageId;
    }

    public void setStageId(String stageId)
    {
        this.stageId = stageId;
    }

    public String getStartAmont()
    {
        return startAmont;
    }

    public void setStartAmont(String startAmont)
    {
        this.startAmont = startAmont;
    }

    public String getEndAmont()
    {
        return endAmont;
    }

    public void setEndAmont(String endAmont)
    {
        this.endAmont = endAmont;
    }

    public String getRepairRate()
    {
        return repairRate;
    }

    public void setRepairRate(String repairRate)
    {
        this.repairRate = repairRate;
    }

    public String getSellRate()
    {
        return sellRate;
    }

    public void setSellRate(String sellRate)
    {
        this.sellRate = sellRate;
    }

    public String getPointRate()
    {
        return pointRate;
    }

    public void setPointRate(String pointRate)
    {
        this.pointRate = pointRate;
    }

    public String getSpecialRate()
    {
        return specialRate;
    }

    public void setSpecialRate(String specialRate)
    {
        this.specialRate = specialRate;
    }

	public String getClaimRate() {
		return claimRate;
	}

	public void setClaimRate(String claimRate) {
		this.claimRate = claimRate;
	}

}
