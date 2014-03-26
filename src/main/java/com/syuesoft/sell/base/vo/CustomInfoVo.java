package com.syuesoft.sell.base.vo;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class CustomInfoVo extends BaseBean implements Serializable {
	private Integer customId;
	private String xsCustomCode;
	private Integer xsCustomArea;
	private Integer xsCustomDeal;
	private Integer xsCustomSource;
	private Integer xsCustomTrade;
	private String xsCustomName;
	private String xsCustomNumber;
	private Integer xsCustomHideLevel;
	private String xsCustomOther;
	private String xsCustomAddress;
	private String xsCustomZipcode;
	private Date xsCustomBirthday;
	private Integer stfId;
	private String xsCustomCredentials;
	private String xsCustomTelephone;
	private String xsCustomPhone;
	private Integer xsCustomIncome;
	private Integer xsCustomApplication;
	private Integer xsCustomContrastModel;
	private Integer xsCustomSex;
	private Integer xsCustomAfter;
	private Integer xsCustomOccupation;
	private Integer xsCustomOtherType;
	private Integer xsCustomReason;
	private Integer xsCustomProperty;
	private String xsCustomCompany;
	private Integer xsCustomDiploma;
	private String xsCustomPhone2;
	private String xsCustomCodeCard;
	private Integer tracingState;
	private String xsCustomInputdata;
	private String xsCustomInputdata2;
	private String xsContactsPerson;
	private String xsContactsPhone;
	private Integer xsCarId;// 查档案id
	// 下拉框 textField所用字段
	private String sexName;
	private String incomeName;
	private String occupationName;
	private String diplomaName;
	private String sourceName;
	private String areaName;
	private String stfName;
	private String propertyName;
	private String tradeName;
	private String afterName;
	private String typeName;
	private String hideLevelName;
	private String contrastModelName;
	private String reasonName;
	private String applicationName;
	private String otherTypeName;
	private String customDealName;
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	// 查询所用字段
	private String code;
	private String name;
	private String phone;
	private String receiptor;
	private Integer stf;
	private Integer dealState;
	private String inputdataStart;
	private String inputdataEnd;
	private String carVinNumber;
	private String carOcn;
	private Integer enterpriseId;
	private String times;
	private Boolean flag;
	
	
	public String getXsCustomInputdata2() {
		return xsCustomInputdata2;
	}

	public void setXsCustomInputdata2(String xsCustomInputdata2) {
		this.xsCustomInputdata2 = xsCustomInputdata2;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

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

	public String getXsCustomPhone2() {
		return xsCustomPhone2;
	}

	public void setXsCustomPhone2(String xsCustomPhone2) {
		this.xsCustomPhone2 = xsCustomPhone2;
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

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}

	public String getDiplomaName() {
		return diplomaName;
	}

	public void setDiplomaName(String diplomaName) {
		this.diplomaName = diplomaName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getAfterName() {
		return afterName;
	}

	public void setAfterName(String afterName) {
		this.afterName = afterName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getHideLevelName() {
		return hideLevelName;
	}

	public void setHideLevelName(String hideLevelName) {
		this.hideLevelName = hideLevelName;
	}

	public String getContrastModelName() {
		return contrastModelName;
	}

	public void setContrastModelName(String contrastModelName) {
		this.contrastModelName = contrastModelName;
	}

	public String getReasonName() {
		return reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getOtherTypeName() {
		return otherTypeName;
	}

	public void setOtherTypeName(String otherTypeName) {
		this.otherTypeName = otherTypeName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceiptor() {
		return receiptor;
	}

	public void setReceiptor(String receiptor) {
		this.receiptor = receiptor;
	}

	public Integer getStf() {
		return stf;
	}

	public void setStf(Integer stf) {
		this.stf = stf;
	}

	public Integer getDealState() {
		return dealState;
	}

	public void setDealState(Integer dealState) {
		this.dealState = dealState;
	}

	public String getInputdataStart() {
		return inputdataStart;
	}

	public void setInputdataStart(String inputdataStart) {
		this.inputdataStart = inputdataStart;
	}

	public String getInputdataEnd() {
		return inputdataEnd;
	}

	public void setInputdataEnd(String inputdataEnd) {
		this.inputdataEnd = inputdataEnd;
	}

	public String getCustomDealName() {
		return customDealName;
	}

	public void setCustomDealName(String customDealName) {
		this.customDealName = customDealName;
	}

	public String getCarVinNumber() {
		return carVinNumber;
	}

	public void setCarVinNumber(String carVinNumber) {
		this.carVinNumber = carVinNumber;
	}

	public String getCarOcn() {
		return carOcn;
	}

	public void setCarOcn(String carOcn) {
		this.carOcn = carOcn;
	}

	public Integer getXsCarId() {
		return xsCarId;
	}

	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}

}
