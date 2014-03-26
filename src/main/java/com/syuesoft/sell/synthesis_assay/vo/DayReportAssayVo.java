package com.syuesoft.sell.synthesis_assay.vo;

import java.util.Date;

public class DayReportAssayVo {

	private String xsSellDate; // 销售日期
	private String xsSellDate2; // 销售日期
	private String retreatDate; // 出库日期
	private String retreatDate2; // 出库日期
	private String xsCarDistributState; // 分销状态
	private String xsDistributorId; // 分销商编号
	private String xsCustomName; // 客户名称
	private String xsCarVinNumber; // VIN编号
	private String consultDegree; // 满意度
	private String carBrand; // 车品牌
	private String carModel; // 车型号
	private String consultDegreeN;
	private String sort;
	private String order;
	private int rows;
	private int page;

	private String carBrandName;// 车辆品牌
	private Integer carColor;
	private String carColorName;// 车辆颜色名称
	private String carModelName;// 车类型
	private String carCode;// 车辆编号
	private String carLicenseName;// 厂牌名称
	private String carVinNumber;
	private String distributorName;// 分销商
	private Double modelSalesPrice;// 销售价
	private Double carInstorageAge;// 库龄
	private String carMotorNumber;
	private String SellDate;
	private Integer stfId;
	private String stfName;
	private String upData;
	private String upData2;
	private Integer upPer;
	private String upPerson;
	private String customName;
	private String customSex;
	private Integer sex;
	private String xsCustomTelephone; // 手机
	private String xsCustomPhone; // 固定电话
	private Integer xsCustomIncome; // 收入情况
	private Integer xsCustomProperty; // 客户性质
	private String xsCustomCompany; // 所在单位
	private Integer xsCustomDiploma; // 教育程度
	private Integer xsCustomTrade; // 所从事行业
	private Integer xsCustomArea; // 所在地区
	private String xsCustomAddress; // 地址
	private String xsCustomZipcode; // 邮编
	private String xsCustomDiplomaN; // 教育程度
	private String xsCustomIncomeN;
	private String xsCustomPropertyN;
	private String xsCustomTradeN;
	private String xsCustomAreaN;
	private String xsCustomBirthday; // 出生年月
	private String pact_code;
	private String invoice_code;
	private String consult_actual_date;
	private Integer carId;
	private Integer carType;
	private String noUp;

	private String carOcn;
	private Double xs_model_costPrice;
	private Double xs_car_price;
	private Double decorate_amount;
	private Double decorate_sell;
	private Double cost_price;
	private Double db_project_cost;
	private Double prime_cost;
	private Double s_sum;
	private Double invoice_parce;
	private String xs_culstom_credentials;// 身份证
	private String xs_car_certificate;// 合格证
	private Integer xs_car_certificate_state;// 合格证状态
	private String state;// 合格证状态
	private String xs_car_make_data;// 生产日期
	private String instorehouse_date;// 入库日期
	private String check_date;// 检测日期
	private String check_comtent;// 检测内容
	private String invoice_number;
	private Integer xs_Car_Sel_Type;// 出库分类

	private Boolean columnsTag; // 动态列查询标示
	private String assayType; // 分析类型
	private Integer enterprise_id;

	public String getUpData2() {
		return upData2;
	}

	public void setUpData2(String upData2) {
		this.upData2 = upData2;
	}

	public String getRetreatDate2() {
		return retreatDate2;
	}

	public void setRetreatDate2(String retreatDate2) {
		this.retreatDate2 = retreatDate2;
	}

	public String getXsSellDate2() {
		return xsSellDate2;
	}

	public void setXsSellDate2(String xsSellDate2) {
		this.xsSellDate2 = xsSellDate2;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getAssayType() {
		return assayType;
	}

	public void setAssayType(String assayType) {
		this.assayType = assayType;
	}

	public Integer getXs_Car_Sel_Type() {
		return xs_Car_Sel_Type;
	}

	public void setXs_Car_Sel_Type(Integer xsCarSelType) {
		xs_Car_Sel_Type = xsCarSelType;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoiceNumber) {
		invoice_number = invoiceNumber;
	}

	public String getCarOcn() {
		return carOcn;
	}

	public void setCarOcn(String carOcn) {
		this.carOcn = carOcn;
	}

	public Double getXs_model_costPrice() {
		return xs_model_costPrice;
	}

	public void setXs_model_costPrice(Double xsModelCostPrice) {
		xs_model_costPrice = xsModelCostPrice;
	}

	public Double getXs_car_price() {
		return xs_car_price;
	}

	public void setXs_car_price(Double xsCarPrice) {
		xs_car_price = xsCarPrice;
	}

	public Double getDecorate_amount() {
		return decorate_amount;
	}

	public void setDecorate_amount(Double decorateAmount) {
		decorate_amount = decorateAmount;
	}

	public Double getDecorate_sell() {
		return decorate_sell;
	}

	public void setDecorate_sell(Double decorateSell) {
		decorate_sell = decorateSell;
	}

	public Double getCost_price() {
		return cost_price;
	}

	public void setCost_price(Double costPrice) {
		cost_price = costPrice;
	}

	public Double getDb_project_cost() {
		return db_project_cost;
	}

	public void setDb_project_cost(Double dbProjectCost) {
		db_project_cost = dbProjectCost;
	}

	public Double getPrime_cost() {
		return prime_cost;
	}

	public void setPrime_cost(Double primeCost) {
		prime_cost = primeCost;
	}

	public Double getS_sum() {
		return s_sum;
	}

	public void setS_sum(Double sSum) {
		s_sum = sSum;
	}

	public Double getInvoice_parce() {
		return invoice_parce;
	}

	public void setInvoice_parce(Double invoiceParce) {
		invoice_parce = invoiceParce;
	}

	public String getXs_culstom_credentials() {
		return xs_culstom_credentials;
	}

	public void setXs_culstom_credentials(String xsCulstomCredentials) {
		xs_culstom_credentials = xsCulstomCredentials;
	}

	public String getXs_car_certificate() {
		return xs_car_certificate;
	}

	public void setXs_car_certificate(String xsCarCertificate) {
		xs_car_certificate = xsCarCertificate;
	}

	public Integer getXs_car_certificate_state() {
		return xs_car_certificate_state;
	}

	public void setXs_car_certificate_state(Integer xsCarCertificateState) {
		xs_car_certificate_state = xsCarCertificateState;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getXs_car_make_data() {
		return xs_car_make_data;
	}

	public void setXs_car_make_data(String xsCarMakeData) {
		xs_car_make_data = xsCarMakeData;
	}

	public String getInstorehouse_date() {
		return instorehouse_date;
	}

	public void setInstorehouse_date(String instorehouseDate) {
		instorehouse_date = instorehouseDate;
	}

	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String checkDate) {
		check_date = checkDate;
	}

	public String getCheck_comtent() {
		return check_comtent;
	}

	public void setCheck_comtent(String checkComtent) {
		check_comtent = checkComtent;
	}

	public String getNoUp() {
		return noUp;
	}

	public void setNoUp(String noUp) {
		this.noUp = noUp;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getConsultDegreeN() {
		return consultDegreeN;
	}

	public void setConsultDegreeN(String consultDegreeN) {
		this.consultDegreeN = consultDegreeN;
	}

	public String getConsult_actual_date() {
		return consult_actual_date;
	}

	public void setConsult_actual_date(String consultActualDate) {
		consult_actual_date = consultActualDate;
	}

	public String getPact_code() {
		return pact_code;
	}

	public void setPact_code(String pactCode) {
		pact_code = pactCode;
	}

	public String getInvoice_code() {
		return invoice_code;
	}

	public void setInvoice_code(String invoiceCode) {
		invoice_code = invoiceCode;
	}

	public String getXsCustomBirthday() {
		return xsCustomBirthday;
	}

	public void setXsCustomBirthday(String xsCustomBirthday) {
		this.xsCustomBirthday = xsCustomBirthday;
	}

	public String getXsCustomDiplomaN() {
		return xsCustomDiplomaN;
	}

	public void setXsCustomDiplomaN(String xsCustomDiplomaN) {
		this.xsCustomDiplomaN = xsCustomDiplomaN;
	}

	public String getXsCustomIncomeN() {
		return xsCustomIncomeN;
	}

	public void setXsCustomIncomeN(String xsCustomIncomeN) {
		this.xsCustomIncomeN = xsCustomIncomeN;
	}

	public String getXsCustomPropertyN() {
		return xsCustomPropertyN;
	}

	public void setXsCustomPropertyN(String xsCustomPropertyN) {
		this.xsCustomPropertyN = xsCustomPropertyN;
	}

	public String getXsCustomTradeN() {
		return xsCustomTradeN;
	}

	public void setXsCustomTradeN(String xsCustomTradeN) {
		this.xsCustomTradeN = xsCustomTradeN;
	}

	public String getXsCustomAreaN() {
		return xsCustomAreaN;
	}

	public void setXsCustomAreaN(String xsCustomAreaN) {
		this.xsCustomAreaN = xsCustomAreaN;
	}

	public String getCarMotorNumber() {
		return carMotorNumber;
	}

	public void setCarMotorNumber(String carMotorNumber) {
		this.carMotorNumber = carMotorNumber;
	}

	public String getSellDate() {
		return SellDate;
	}

	public void setSellDate(String sellDate) {
		SellDate = sellDate;
	}

	public Integer getStfId() {
		return stfId;
	}

	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public String getUpData() {
		return upData;
	}

	public void setUpData(String upData) {
		this.upData = upData;
	}

	public Integer getUpPer() {
		return upPer;
	}

	public void setUpPer(Integer upPer) {
		this.upPer = upPer;
	}

	public String getUpPerson() {
		return upPerson;
	}

	public void setUpPerson(String upPerson) {
		this.upPerson = upPerson;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomSex() {
		return customSex;
	}

	public void setCustomSex(String customSex) {
		this.customSex = customSex;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public Integer getXsCustomTrade() {
		return xsCustomTrade;
	}

	public void setXsCustomTrade(Integer xsCustomTrade) {
		this.xsCustomTrade = xsCustomTrade;
	}

	public Integer getXsCustomArea() {
		return xsCustomArea;
	}

	public void setXsCustomArea(Integer xsCustomArea) {
		this.xsCustomArea = xsCustomArea;
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

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public Integer getCarColor() {
		return carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public String getCarColorName() {
		return carColorName;
	}

	public void setCarColorName(String carColorName) {
		this.carColorName = carColorName;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarLicenseName() {
		return carLicenseName;
	}

	public void setCarLicenseName(String carLicenseName) {
		this.carLicenseName = carLicenseName;
	}

	public String getCarVinNumber() {
		return carVinNumber;
	}

	public void setCarVinNumber(String carVinNumber) {
		this.carVinNumber = carVinNumber;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public Double getModelSalesPrice() {
		return modelSalesPrice;
	}

	public void setModelSalesPrice(Double modelSalesPrice) {
		this.modelSalesPrice = modelSalesPrice;
	}

	public Double getCarInstorageAge() {
		return carInstorageAge;
	}

	public void setCarInstorageAge(Double carInstorageAge) {
		this.carInstorageAge = carInstorageAge;
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

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getConsultDegree() {
		return consultDegree;
	}

	public void setConsultDegree(String consultDegree) {
		this.consultDegree = consultDegree;
	}

	public String getRetreatDate() {
		return retreatDate;
	}

	public void setRetreatDate(String retreatDate) {
		this.retreatDate = retreatDate;
	}

	public String getXsCarDistributState() {
		return xsCarDistributState;
	}

	public void setXsCarDistributState(String xsCarDistributState) {
		this.xsCarDistributState = xsCarDistributState;
	}

	public String getXsDistributorId() {
		return xsDistributorId;
	}

	public void setXsDistributorId(String xsDistributorId) {
		this.xsDistributorId = xsDistributorId;
	}

	public String getXsCustomName() {
		return xsCustomName;
	}

	public void setXsCustomName(String xsCustomName) {
		this.xsCustomName = xsCustomName;
	}

	public String getXsCarVinNumber() {
		return xsCarVinNumber;
	}

	public void setXsCarVinNumber(String xsCarVinNumber) {
		this.xsCarVinNumber = xsCarVinNumber;
	}

	public String getXsSellDate() {
		return xsSellDate;
	}

	public void setXsSellDate(String xsSellDate) {
		this.xsSellDate = xsSellDate;
	}

	public Boolean getColumnsTag() {
		return columnsTag;
	}

	public void setColumnsTag(Boolean columnsTag) {
		this.columnsTag = columnsTag;
	}

}
