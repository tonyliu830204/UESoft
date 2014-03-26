package com.syuesoft.sell.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.syuesoft.model.BaseBean;

/**
 * XsCustomInfo entity. @author MyEclipse Persistence Tools
 */

public class XsCustomInfo extends BaseBean implements java.io.Serializable {

	// Fields

	private Integer customId; // 编号
	private String xsCustomCode; // 客户编号
	private Integer xsCustomArea; // 所在地区
	private Integer xsCustomDeal; // 成交状态
	private Integer xsCustomSource; // 来源
	private Integer xsCustomTrade; // 所从事行业
	private String xsCustomName; // 客户名
	private String xsCustomNumber; // 自编号
	private Integer xsCustomHideLevel; // 潜在客户等级
	private String xsCustomOther; // 其他
	private String xsCustomAddress; // 地址
	private String xsCustomZipcode; // 邮编
	private Date xsCustomBirthday; // 出生年月
	private Integer stfId; // 业务员
	private String xsCustomCredentials; // 证件号
	private String xsCustomTelephone; // 手机
	private String xsCustomPhone; // 固定电话
	private Integer xsCustomIncome; // 收入情况
	private Integer xsCustomApplication; // 用途
	private Integer xsCustomContrastModel; // 对比车型
	private Integer xsCustomSex; // 性别
	private Integer xsCustomAfter; // 跟踪
	private Integer xsCustomOccupation; // 从事职业
	private Integer xsCustomOtherType; // 其他分类
	private Integer xsCustomReason; // 选择理由
	private Integer xsCustomProperty; // 客户性质
	private String xsCustomCompany; // 所在单位
	private Integer xsCustomDiploma; // 教育程度
	private String xsCustomCodeCard; // 代码证
	private Integer tracingState; // 跟踪状态
	private String xsCustomInputdata; // 建档日期
	private String xsContactsPerson; // 客户联系人
	private String xsContactsPhone; // 客户联系人电话

	private Integer enterpriseId;

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	private Set xsSellReceiverelations = new HashSet(0);

	public Integer getCustomId() {
		return customId;
	}

	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	public String getXsCustomCode() {
		return xsCustomCode;
	}

	public void setXsCustomCode(String xsCustomCode) {
		this.xsCustomCode = xsCustomCode;
	}

	public Integer getXsCustomArea() {
		return xsCustomArea;
	}

	public void setXsCustomArea(Integer xsCustomArea) {
		this.xsCustomArea = xsCustomArea;
	}

	public Integer getXsCustomDeal() {
		return xsCustomDeal;
	}

	public void setXsCustomDeal(Integer xsCustomDeal) {
		this.xsCustomDeal = xsCustomDeal;
	}

	public Integer getXsCustomSource() {
		return xsCustomSource;
	}

	public void setXsCustomSource(Integer xsCustomSource) {
		this.xsCustomSource = xsCustomSource;
	}

	public Integer getXsCustomTrade() {
		return xsCustomTrade;
	}

	public void setXsCustomTrade(Integer xsCustomTrade) {
		this.xsCustomTrade = xsCustomTrade;
	}

	public String getXsCustomName() {
		return xsCustomName;
	}

	public void setXsCustomName(String xsCustomName) {
		this.xsCustomName = xsCustomName;
	}

	public String getXsCustomNumber() {
		return xsCustomNumber;
	}

	public void setXsCustomNumber(String xsCustomNumber) {
		this.xsCustomNumber = xsCustomNumber;
	}

	public Integer getXsCustomHideLevel() {
		return xsCustomHideLevel;
	}

	public void setXsCustomHideLevel(Integer xsCustomHideLevel) {
		this.xsCustomHideLevel = xsCustomHideLevel;
	}

	public String getXsCustomOther() {
		return xsCustomOther;
	}

	public void setXsCustomOther(String xsCustomOther) {
		this.xsCustomOther = xsCustomOther;
	}

	public String getXsCustomAddress() {
		return xsCustomAddress;
	}

	public void setXsCustomAddress(String xsCustomAddress) {
		this.xsCustomAddress = xsCustomAddress;
	}

	public String getXsCustomZipcode() {
		return xsCustomZipcode;
	}

	public void setXsCustomZipcode(String xsCustomZipcode) {
		this.xsCustomZipcode = xsCustomZipcode;
	}

	public Date getXsCustomBirthday() {
		return xsCustomBirthday;
	}

	public void setXsCustomBirthday(Date xsCustomBirthday) {
		this.xsCustomBirthday = xsCustomBirthday;
	}

	public Integer getStfId() {
		return stfId;
	}

	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}

	public String getXsCustomCredentials() {
		return xsCustomCredentials;
	}

	public void setXsCustomCredentials(String xsCustomCredentials) {
		this.xsCustomCredentials = xsCustomCredentials;
	}

	public String getXsCustomTelephone() {
		return xsCustomTelephone;
	}

	public void setXsCustomTelephone(String xsCustomTelephone) {
		this.xsCustomTelephone = xsCustomTelephone;
	}

	public String getXsCustomPhone() {
		return xsCustomPhone;
	}

	public void setXsCustomPhone(String xsCustomPhone) {
		this.xsCustomPhone = xsCustomPhone;
	}

	public Integer getXsCustomIncome() {
		return xsCustomIncome;
	}

	public void setXsCustomIncome(Integer xsCustomIncome) {
		this.xsCustomIncome = xsCustomIncome;
	}

	public Integer getXsCustomApplication() {
		return xsCustomApplication;
	}

	public void setXsCustomApplication(Integer xsCustomApplication) {
		this.xsCustomApplication = xsCustomApplication;
	}

	public Integer getXsCustomContrastModel() {
		return xsCustomContrastModel;
	}

	public void setXsCustomContrastModel(Integer xsCustomContrastModel) {
		this.xsCustomContrastModel = xsCustomContrastModel;
	}

	public Integer getXsCustomSex() {
		return xsCustomSex;
	}

	public void setXsCustomSex(Integer xsCustomSex) {
		this.xsCustomSex = xsCustomSex;
	}

	public Integer getXsCustomAfter() {
		return xsCustomAfter;
	}

	public void setXsCustomAfter(Integer xsCustomAfter) {
		this.xsCustomAfter = xsCustomAfter;
	}

	public Integer getXsCustomOccupation() {
		return xsCustomOccupation;
	}

	public void setXsCustomOccupation(Integer xsCustomOccupation) {
		this.xsCustomOccupation = xsCustomOccupation;
	}

	public Integer getXsCustomOtherType() {
		return xsCustomOtherType;
	}

	public void setXsCustomOtherType(Integer xsCustomOtherType) {
		this.xsCustomOtherType = xsCustomOtherType;
	}

	public Integer getXsCustomReason() {
		return xsCustomReason;
	}

	public void setXsCustomReason(Integer xsCustomReason) {
		this.xsCustomReason = xsCustomReason;
	}

	public Integer getXsCustomProperty() {
		return xsCustomProperty;
	}

	public void setXsCustomProperty(Integer xsCustomProperty) {
		this.xsCustomProperty = xsCustomProperty;
	}

	public String getXsCustomCompany() {
		return xsCustomCompany;
	}

	public void setXsCustomCompany(String xsCustomCompany) {
		this.xsCustomCompany = xsCustomCompany;
	}

	public Integer getXsCustomDiploma() {
		return xsCustomDiploma;
	}

	public void setXsCustomDiploma(Integer xsCustomDiploma) {
		this.xsCustomDiploma = xsCustomDiploma;
	}

	public String getXsCustomCodeCard() {
		return xsCustomCodeCard;
	}

	public void setXsCustomCodeCard(String xsCustomCodeCard) {
		this.xsCustomCodeCard = xsCustomCodeCard;
	}

	public Integer getTracingState() {
		return tracingState;
	}

	public void setTracingState(Integer tracingState) {
		this.tracingState = tracingState;
	}

	public String getXsCustomInputdata() {
		return xsCustomInputdata;
	}

	public void setXsCustomInputdata(String xsCustomInputdata) {
		this.xsCustomInputdata = xsCustomInputdata;
	}

	public String getXsContactsPerson() {
		return xsContactsPerson;
	}

	public void setXsContactsPerson(String xsContactsPerson) {
		this.xsContactsPerson = xsContactsPerson;
	}

	public String getXsContactsPhone() {
		return xsContactsPhone;
	}

	public void setXsContactsPhone(String xsContactsPhone) {
		this.xsContactsPhone = xsContactsPhone;
	}

	public Set getXsSellReceiverelations() {
		return xsSellReceiverelations;
	}

	public void setXsSellReceiverelations(Set xsSellReceiverelations) {
		this.xsSellReceiverelations = xsSellReceiverelations;
	}

}