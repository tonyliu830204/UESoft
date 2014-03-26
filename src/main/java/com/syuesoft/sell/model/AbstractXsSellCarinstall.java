package com.syuesoft.sell.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractXsSellCarinstall entity provides the base persistence definition of
 * the XsSellCarinstall entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractXsSellCarinstall implements java.io.Serializable {

	// Fields

	private Integer installId;
	private XsCarInfo xsCarInfo;
	private String installCode;
	private Double sunMoney;
	private Integer finishState;
	private Integer examine;
	private String remark;
	private Set xsSellCarinstallDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractXsSellCarinstall() {
	}

	/** full constructor */
	public AbstractXsSellCarinstall(XsCarInfo xsCarInfo, String installCode,
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

}