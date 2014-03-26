package com.syuesoft.sell.model;

/**
 * XsCarCwInfo entity. @author MyEclipse Persistence Tools
 */

public class XsCarCwInfo implements java.io.Serializable {

	// Fields

	private Integer xsCarCwInfoId; //车辆财务信息编号
	private XsCarSellInfo xsCarSellInfo;//
	private Double AAmount;           //按揭金额
	private Double aplyAmount;        //预收款金额
	private Double insuranceAmount;   //保险金额
	private Double projectAmount;    // 代办项目金额
	private Double ornamentAmount;    //装潢金额
	private String JClassify;          //结算分类
	private String examine;             //审核
	private String remark;              //备注

	// Constructors

	/** default constructor */
	public XsCarCwInfo() {
	}

	/** full constructor */
	public XsCarCwInfo(XsCarSellInfo xsCarSellInfo, Double AAmount,
			Double aplyAmount, Double insuranceAmount, Double projectAmount,
			Double ornamentAmount, String JClassify, String examine,
			String remark) {
		this.xsCarSellInfo = xsCarSellInfo;
		this.AAmount = AAmount;
		this.aplyAmount = aplyAmount;
		this.insuranceAmount = insuranceAmount;
		this.projectAmount = projectAmount;
		this.ornamentAmount = ornamentAmount;
		this.JClassify = JClassify;
		this.examine = examine;
		this.remark = remark;
	}

	// Property accessors

	public Integer getXsCarCwInfoId() {
		return this.xsCarCwInfoId;
	}

	public void setXsCarCwInfoId(Integer xsCarCwInfoId) {
		this.xsCarCwInfoId = xsCarCwInfoId;
	}

	public XsCarSellInfo getXsCarSellInfo() {
		return this.xsCarSellInfo;
	}

	public void setXsCarSellInfo(XsCarSellInfo xsCarSellInfo) {
		this.xsCarSellInfo = xsCarSellInfo;
	}

	public Double getAAmount() {
		return this.AAmount;
	}

	public void setAAmount(Double AAmount) {
		this.AAmount = AAmount;
	}

	public Double getAplyAmount() {
		return this.aplyAmount;
	}

	public void setAplyAmount(Double aplyAmount) {
		this.aplyAmount = aplyAmount;
	}

	public Double getInsuranceAmount() {
		return this.insuranceAmount;
	}

	public void setInsuranceAmount(Double insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public Double getProjectAmount() {
		return this.projectAmount;
	}

	public void setProjectAmount(Double projectAmount) {
		this.projectAmount = projectAmount;
	}

	public Double getOrnamentAmount() {
		return this.ornamentAmount;
	}

	public void setOrnamentAmount(Double ornamentAmount) {
		this.ornamentAmount = ornamentAmount;
	}

	public String getJClassify() {
		return this.JClassify;
	}

	public void setJClassify(String JClassify) {
		this.JClassify = JClassify;
	}

	public String getExamine() {
		return this.examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}