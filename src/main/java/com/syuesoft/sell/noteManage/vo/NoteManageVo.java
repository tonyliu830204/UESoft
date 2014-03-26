package com.syuesoft.sell.noteManage.vo;

import com.syuesoft.model.BaseBean;

public class NoteManageVo extends BaseBean {
	private Integer carId;
	private Integer carBrand;
	private String carBrandN;
	private Integer carColor;
	private Integer carInteriorColor;
	private Integer carModelId;
	private String carModelN;
	private Integer carType;
	private String carCode;
	private String carLicensePlate;
	private String carVinNumber;
	private String carAddress;
	private Double carPrice;
	private Integer distributorId;
	private Integer carSellState;
	private Integer carDistributState;


	
	private Integer customId;			//编号
	private String xsCustomCode;		//客户编号
	private Integer xsCustomArea;		//所在地区
	private String customArea;		//所在地区
	private Integer xsCustomDeal;		//成交状态
	private String customDeal;		//成交状态
	private Integer xsCustomSource; 	//来源
	private Integer xsCustomTrade;		//所从事行业
	private String custom_Name;		//客户名
	private Integer xsCustomHideLevel;	//潜在客户等级
	private String customLevel;	//潜在客户等级
	private String xsCustomAddress;		//地址
	private String xsCustomZipcode;		//邮编
	private String xsCustomBirthday;		//出生年月
	private String xsCustomBirthday2;		//出生年月
	private Integer stfId;				//业务员
	private String stfName;				//业务员

	private String stfPhone;				//业务员电话
	private String xsCustomCredentials;	//证件号
	private String telephone;	//手机
	private String xsCustomPhone;		//固定电话
	private Integer xsCustomApplication;	//用途
	private Integer xsCustomSex;			//性别
	private Integer xsCustomAfter;			//跟踪
	private String xsCustomCompany;			//所在单位
	private String carsellData;//销售日期
	private String insurerEndDate;//保险到期
	private String carsellData2;//销售日期
	private String insurerEndDate2;//保险到期
	private Integer enterprise_id ;
	
	private int rows;
	private int page;
 	
	public String getXsCustomBirthday2() {
		return xsCustomBirthday2;
	}
	public void setXsCustomBirthday2(String xsCustomBirthday2) {
		this.xsCustomBirthday2 = xsCustomBirthday2;
	}
	public String getCarsellData2() {
		return carsellData2;
	}
	public void setCarsellData2(String carsellData2) {
		this.carsellData2 = carsellData2;
	}
	public String getInsurerEndDate2() {
		return insurerEndDate2;
	}
	public void setInsurerEndDate2(String insurerEndDate2) {
		this.insurerEndDate2 = insurerEndDate2;
	}
	public Integer getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}
	public String getStfName() {
		return stfName;
	}
	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
	public String getInsurerEndDate() {
		return insurerEndDate;
	}
	public void setInsurerEndDate(String insurerEndDate) {
		this.insurerEndDate = insurerEndDate;
	}
	public String getCarsellData() {
		return carsellData;
	}
	public void setCarsellData(String carsellData) {
		this.carsellData = carsellData;
	}
	
	
	public String getCarBrandN() {
		return carBrandN;
	}
	public void setCarBrandN(String carBrandN) {
		this.carBrandN = carBrandN;
	}
	public String getCarModelN() {
		return carModelN;
	}
	public void setCarModelN(String carModelN) {
		this.carModelN = carModelN;
	}
	public String getStfPhone() {
		return stfPhone;
	}
	public void setStfPhone(String stfPhone) {
		this.stfPhone = stfPhone;
	}
	public String getCustomDeal() {
		return customDeal;
	}
	public void setCustomDeal(String customDeal) {
		this.customDeal = customDeal;
	}
	public String getCustomLevel() {
		return customLevel;
	}
	public void setCustomLevel(String customLevel) {
		this.customLevel = customLevel;
	}
	public String getCustomArea() {
		return customArea;
	}
	public void setCustomArea(String customArea) {
		this.customArea = customArea;
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
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public Integer getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}
	public Integer getCarColor() {
		return carColor;
	}
	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}
	public Integer getCarInteriorColor() {
		return carInteriorColor;
	}
	public void setCarInteriorColor(Integer carInteriorColor) {
		this.carInteriorColor = carInteriorColor;
	}
	public Integer getCarModelId() {
		return carModelId;
	}
	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getCarLicensePlate() {
		return carLicensePlate;
	}
	public void setCarLicensePlate(String carLicensePlate) {
		this.carLicensePlate = carLicensePlate;
	}
	public String getCarVinNumber() {
		return carVinNumber;
	}
	public void setCarVinNumber(String carVinNumber) {
		this.carVinNumber = carVinNumber;
	}
	public String getCarAddress() {
		return carAddress;
	}
	public void setCarAddress(String carAddress) {
		this.carAddress = carAddress;
	}
	public Double getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(Double carPrice) {
		this.carPrice = carPrice;
	}
	public Integer getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}
	public Integer getCarSellState() {
		return carSellState;
	}
	public void setCarSellState(Integer carSellState) {
		this.carSellState = carSellState;
	}
	public Integer getCarDistributState() {
		return carDistributState;
	}
	public void setCarDistributState(Integer carDistributState) {
		this.carDistributState = carDistributState;
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

	public Integer getXsCustomHideLevel() {
		return xsCustomHideLevel;
	}
	public void setXsCustomHideLevel(Integer xsCustomHideLevel) {
		this.xsCustomHideLevel = xsCustomHideLevel;
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
	public String  getXsCustomBirthday() {
		return xsCustomBirthday;
	}
	public void setXsCustomBirthday(String  xsCustomBirthday) {
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
	
	public String getCustom_Name() {
		return custom_Name;
	}
	public void setCustom_Name(String customName) {
		custom_Name = customName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getXsCustomPhone() {
		return xsCustomPhone;
	}
	public void setXsCustomPhone(String xsCustomPhone) {
		this.xsCustomPhone = xsCustomPhone;
	}
	public Integer getXsCustomApplication() {
		return xsCustomApplication;
	}
	public void setXsCustomApplication(Integer xsCustomApplication) {
		this.xsCustomApplication = xsCustomApplication;
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
	private Integer xsCustomDiploma;		//教育程度
	private String xsCustomCodeCard;		//代码证
	private Integer tracingState;			//跟踪状态
	private String xsCustomInputdata;		//建档日期
	private String xsContactsPerson;		//客户联系人
	private String xsContactsPhone;			//客户联系人电话

}
