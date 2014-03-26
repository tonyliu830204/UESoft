package com.syuesoft.base.vo;

import java.io.File;

/**
 * 车辆品牌VO模型
 * 
 * @author Suming
 * 
 */
public class BasCarBrandVo extends BaseBeanVo
{

    private String cbrdId;

    private String cbrdName;

    private String cbrdPrice;

    private String cbrdLogo;
    
	private Integer cbrdDistance;
	
	private Integer cbrdDays;
	private Integer enterpriseId;
	
	private String stfId;
	
	private File file;

    public String getCbrdId()
    {
        return cbrdId;
    }

    public void setCbrdId(String cbrdId)
    {
        this.cbrdId = cbrdId;
    }

    public String getCbrdName()
    {
        return cbrdName;
    }

    public void setCbrdName(String cbrdName)
    {
        this.cbrdName = cbrdName;
    }

    public String getCbrdPrice()
    {
        return cbrdPrice;
    }

    public void setCbrdPrice(String cbrdPrice)
    {
        this.cbrdPrice = cbrdPrice;
    }

    public String getCbrdLogo()
    {
        return cbrdLogo;
    }

    public void setCbrdLogo(String cbrdLogo)
    {
        this.cbrdLogo = cbrdLogo;
    }

	public Integer getCbrdDistance() {
		return cbrdDistance;
	}

	public void setCbrdDistance(Integer cbrdDistance) {
		this.cbrdDistance = cbrdDistance;
	}

	public Integer getCbrdDays() {
		return cbrdDays;
	}

	public void setCbrdDays(Integer cbrdDays) {
		this.cbrdDays = cbrdDays;
	}

	public String getStfId() {
		return stfId;
	}

	public void setStfId(String stfId) {
		this.stfId = stfId;
	}

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
    
    
}