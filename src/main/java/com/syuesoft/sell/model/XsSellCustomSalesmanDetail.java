package com.syuesoft.sell.model;

import java.util.Date;

/**
 * XsSellCustomSalesmanDetail entity. @author MyEclipse Persistence Tools
 */

public class XsSellCustomSalesmanDetail implements java.io.Serializable {

	// Fields

	private String xsCustomSalesmanDetailId;
	private XsSellCustomSalesman xsSellCustomSalesman;
	private Date inDate;
	private String outDate;
	private Integer manNum;
	private Integer womanNum;
	private String customName;
	private String telephone;
	private Integer firstOrmany;
	private Integer carModel;
	private Integer carBrand;
	private String talkContent;
	private Integer customLevel;
	private Integer messageChannel;
	private Integer talkWay;
	private Integer istestdrive;
	private Date loseDate;		//放弃日期
	private Integer testdriveModel;
	private Integer registerState;
	private String remark;
	private Integer stfId;
	private Integer xsCustomBargainState;

	// Constructors

	/** default constructor */
	public XsSellCustomSalesmanDetail() {
	}

	/** full constructor */
	public XsSellCustomSalesmanDetail(
			XsSellCustomSalesman xsSellCustomSalesman, Date inDate,
			String outDate, Integer manNum, Integer womanNum,
			String customName, String telephone, Integer firstOrmany,
			Integer carModel, String talkContent, Integer customLevel,
			Integer messageChannel, Integer talkWay, Integer istestdrive,
			Integer testdriveModel, Integer registerState, String remark,
			Integer stfId, Integer xsCustomBargainState) {
		this.xsSellCustomSalesman = xsSellCustomSalesman;
		this.inDate = inDate;
		this.outDate = outDate;
		this.manNum = manNum;
		this.womanNum = womanNum;
		this.customName = customName;
		this.telephone = telephone;
		this.firstOrmany = firstOrmany;
		this.carModel = carModel;
		this.talkContent = talkContent;
		this.customLevel = customLevel;
		this.messageChannel = messageChannel;
		this.talkWay = talkWay;
		this.istestdrive = istestdrive;
		this.testdriveModel = testdriveModel;
		this.registerState = registerState;
		this.remark = remark;
		this.stfId = stfId;
		this.xsCustomBargainState = xsCustomBargainState;
	}

	// Property accessors

	
	
	public String getXsCustomSalesmanDetailId() {
		return this.xsCustomSalesmanDetailId;
	}

	public Date getLoseDate() {
		return loseDate;
	}

	public void setLoseDate(Date loseDate) {
		this.loseDate = loseDate;
	}

	public Integer getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public void setXsCustomSalesmanDetailId(String xsCustomSalesmanDetailId) {
		this.xsCustomSalesmanDetailId = xsCustomSalesmanDetailId;
	}

	public XsSellCustomSalesman getXsSellCustomSalesman() {
		return this.xsSellCustomSalesman;
	}

	public void setXsSellCustomSalesman(
			XsSellCustomSalesman xsSellCustomSalesman) {
		this.xsSellCustomSalesman = xsSellCustomSalesman;
	}

	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return this.outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public Integer getManNum() {
		return this.manNum;
	}

	public void setManNum(Integer manNum) {
		this.manNum = manNum;
	}

	public Integer getWomanNum() {
		return this.womanNum;
	}

	public void setWomanNum(Integer womanNum) {
		this.womanNum = womanNum;
	}

	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getFirstOrmany() {
		return this.firstOrmany;
	}

	public void setFirstOrmany(Integer firstOrmany) {
		this.firstOrmany = firstOrmany;
	}

	public Integer getCarModel() {
		return this.carModel;
	}

	public void setCarModel(Integer carModel) {
		this.carModel = carModel;
	}

	public String getTalkContent() {
		return this.talkContent;
	}

	public void setTalkContent(String talkContent) {
		this.talkContent = talkContent;
	}

	public Integer getCustomLevel() {
		return this.customLevel;
	}

	public void setCustomLevel(Integer customLevel) {
		this.customLevel = customLevel;
	}

	public Integer getMessageChannel() {
		return this.messageChannel;
	}

	public void setMessageChannel(Integer messageChannel) {
		this.messageChannel = messageChannel;
	}

	public Integer getTalkWay() {
		return this.talkWay;
	}

	public void setTalkWay(Integer talkWay) {
		this.talkWay = talkWay;
	}

	public Integer getIstestdrive() {
		return this.istestdrive;
	}

	public void setIstestdrive(Integer istestdrive) {
		this.istestdrive = istestdrive;
	}

	public Integer getTestdriveModel() {
		return this.testdriveModel;
	}

	public void setTestdriveModel(Integer testdriveModel) {
		this.testdriveModel = testdriveModel;
	}

	public Integer getRegisterState() {
		return this.registerState;
	}

	public void setRegisterState(Integer registerState) {
		this.registerState = registerState;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStfId() {
		return this.stfId;
	}

	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}

	public Integer getXsCustomBargainState() {
		return this.xsCustomBargainState;
	}

	public void setXsCustomBargainState(Integer xsCustomBargainState) {
		this.xsCustomBargainState = xsCustomBargainState;
	}

}