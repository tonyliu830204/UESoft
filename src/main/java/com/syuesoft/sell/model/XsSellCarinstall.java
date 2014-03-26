package com.syuesoft.sell.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * XsSellCarinstall entity. @author MyEclipse Persistence Tools
 */



public class XsSellCarinstall implements java.io.Serializable {

	// Fields
	
	private Integer enterpriseId;           //企业编号
	private Integer installId;              //加装序号
	private XsCarInfo xsCarInfo;            //车辆档案信息
	private String installCode;             //加装单号
	private Integer installApplyPerson;     //加装申请人
	private Date installApplyDate;          //加装申请日期
	private Integer installPerson;          //加装人
	private Date installDate;               //加装日期
	private Integer outPerson;              //出库人
    private Date outDate;                   //出库日期
	private Double sunMoney;                //计划总金额
	private Double factSunMoney;            //实际总金额
	private Integer finishState;            //完工状态
	private Integer examine;                //审核状态
	private String remark;                  //备注
	private Set xsSellCarinstallDetails = new HashSet(0);
	private XsChilddictionary sellCarinstallExamine;//审核状态
	private XsChilddictionary sellCarinstallFinishState; //完工状态

	
	// Constructors

	public XsChilddictionary getSellCarinstallExamine() {
		return sellCarinstallExamine;
	}

	public void setSellCarinstallExamine(XsChilddictionary sellCarinstallExamine) {
		this.sellCarinstallExamine = sellCarinstallExamine;
	}

	public XsChilddictionary getSellCarinstallFinishState() {
		return sellCarinstallFinishState;
	}

	public void setSellCarinstallFinishState(
			XsChilddictionary sellCarinstallFinishState) {
		this.sellCarinstallFinishState = sellCarinstallFinishState;
	}

	/** default constructor */
	public XsSellCarinstall() {
	}

	/** full constructor */
	public XsSellCarinstall(XsCarInfo xsCarInfo, String installCode,
			Double sunMoney, Integer finishState, Integer examine,
			String remark, Set xsSellCarinstallDetails) {
		this.xsCarInfo = xsCarInfo;
		this.installCode = installCode;
		this.sunMoney = sunMoney;
		this.finishState = finishState;
		this.examine = examine;
		this.remark = remark;
		this.xsSellCarinstallDetails = xsSellCarinstallDetails;
	}

	// Property accessors

	
	
	public Integer getInstallId() {
		return this.installId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public void setInstallId(Integer installId) {
		this.installId = installId;
	}

	public XsCarInfo getXsCarInfo() {
		return this.xsCarInfo;
	}

	public void setXsCarInfo(XsCarInfo xsCarInfo) {
		this.xsCarInfo = xsCarInfo;
	}

	public String getInstallCode() {
		return this.installCode;
	}

	public void setInstallCode(String installCode) {
		this.installCode = installCode;
	}

	public Double getSunMoney() {
		return this.sunMoney;
	}

	public void setSunMoney(Double sunMoney) {
		this.sunMoney = sunMoney;
	}

	public Integer getFinishState() {
		return this.finishState;
	}

	public void setFinishState(Integer finishState) {
		this.finishState = finishState;
	}

	public Integer getExamine() {
		return this.examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getXsSellCarinstallDetails() {
		return this.xsSellCarinstallDetails;
	}

	public void setXsSellCarinstallDetails(Set xsSellCarinstallDetails) {
		this.xsSellCarinstallDetails = xsSellCarinstallDetails;
	}

    public Integer getInstallApplyPerson()
    {
        return installApplyPerson;
    }

    public void setInstallApplyPerson(Integer installApplyPerson)
    {
        this.installApplyPerson = installApplyPerson;
    }

    public Date getInstallApplyDate()
    {
        return installApplyDate;
    }

    public void setInstallApplyDate(Date installApplyDate)
    {
        this.installApplyDate = installApplyDate;
    }

    public Integer getInstallPerson()
    {
        return installPerson;
    }

    public void setInstallPerson(Integer installPerson)
    {
        this.installPerson = installPerson;
    }

    public Date getInstallDate()
    {
        return installDate;
    }

    public void setInstallDate(Date installDate)
    {
        this.installDate = installDate;
    }

    public Integer getOutPerson()
    {
        return outPerson;
    }

    public void setOutPerson(Integer outPerson)
    {
        this.outPerson = outPerson;
    }

    public Date getOutDate()
    {
        return outDate;
    }

    public void setOutDate(Date outDate)
    {
        this.outDate = outDate;
    }

    public Double getFactSunMoney()
    {
        return factSunMoney;
    }

    public void setFactSunMoney(Double factSunMoney)
    {
        this.factSunMoney = factSunMoney;
    }
}