package com.syuesoft.sell.sellInsurance.vo;

import java.io.Serializable;

import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.model.XsCarSellInfo;

public class SellInsuranceListVo extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 车辆保单Vo
	 */
	
	private String formvalue;

	private Integer insuranceId; // 编号
	private Integer enterpriseId; // 企业编号
	
	private Integer xs_Car_Sel_Id ;
	private String insurancePolicy; // 保险单号
	private Integer insurer;
	private String insurerName; // 保险公司

	private String customName;
	private Integer customId; // 客户
	private Integer xsCustomApplication;//用途
	private String xsCustomApplicationN;//用途名称
	private String sell_code;

	private String customTelephone;
	private String customPhone;
	private String customAddress;
	private String contactsPerson;// 联系人
	private String contactsPhone;
	private String customCredentials;// 身份证号码
	private String customZipcode;// 邮政编码

	private String numbers; // 组织代码
	private Integer carColor; // 车辆颜色
	private String carColorName;

	private Integer carModel; // 车辆型号
	private Integer cardColor; // 号牌底色
	private String vin;              // 车架号
	private String engineNumber;     // 发动机号
	private String outprint;        // 排量/功率
	private Integer limitLoadNum;    // 座位/吨位
	private Integer useNature;       // 使用性质
	private String dafeDate; 
	private String dafeDate2; // 代保日期
	private String carLicenseName;
	private String carCode;

	private String insurerStartDate; // 投保日期
	private String insurerStartDate1; // 投保日期
	private String insurerEndDate;   // 投保到期日期
	private String insurerEndDate1;   // 投保到期日期
	private String safeType;         // 保单分类
	private String safeTypeN;         // 保单分类
	private String recordDate;       // 登记日期
	private String distance;         // 已行驶里程
	private String insuranceAgent;   // 保险员
	private Double businessCharge;   // 商业险出单保费
	private Double trafficCharge;    // 交强险出单保费
	private Double vehiclevesselTax;//车船使用税
	private Double safeCost;         // 保单保费
	private Double safeAmount;       // 保险费
	private String buysnessCost;     // 商业险返点
	private String inCost;            // 交强险返点
	private Double sum;             // 保费
	private Double customCost;       // 客户交保/客户付款
	private Double extract;           // 业务提成
	private String customReturncost; // 客户返利
	private Integer person;          // 经办人
	private String stfName;
	private Double profit; // 保险利润
	private Double primeCost; // 保费成本
	private String remark;
	private String carLicensePlate; // 车牌照
	private String detaildateGrid;// 保险险种表
	private String dateGridForm;// form表单

	// 保险险种明细
	private Integer detailId;
	private String insurancetype; // 险种编号
	private Double insurancemoney;
	private String insurancerate;
	private Double insurancecost;
	private String insuranceName;// 险种名称
	private Integer examine;  //审核
	private String examineName;  //审核
	private Integer invoiceReckoning;//是否转结算
	private String reckoningName;
	private Integer num;
	private String state;
	private String iconCls;
	
	private String sort;
	private String order;
	private int rows;
	private int page;
	private Integer childId;
	private String dataValue;
	private Integer carBrandId;
	private String carmodelN;
	private String carBrand;
	private String carMotorNumber;//发动机
	private String instorehouseDate;
	private String carSellData;
	private String carSellData2;
	private String insurerId;
	private String  insurerCodes;
	public String getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(String insurerId) {
		this.insurerId = insurerId;
	}

	public String getCarSellData2() {
		return carSellData2;
	}

	public void setCarSellData2(String carSellData2) {
		this.carSellData2 = carSellData2;
	}

	private Double sellMoney;
	private String  fristInsuranceData;//首保日期
	private String carMakeData;//生产日期
	private String audit_date;//上报日期
	private String accountCode;//结算单号
	private Boolean flag;
	
	
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCustomPhone() {
		return customPhone;
	}

	public void setCustomPhone(String customPhone) {
		this.customPhone = customPhone;
	}

	public String getAudit_date() {
		return audit_date;
	}

	public void setAudit_date(String auditDate) {
		audit_date = auditDate;
	}

	public String getCarMakeData() {
		return carMakeData;
	}

	public void setCarMakeData(String carMakeData) {
		this.carMakeData = carMakeData;
	}

	public Double getSellMoney() {
		return sellMoney;
	}

	public void setSellMoney(Double sellMoney) {
		this.sellMoney = sellMoney;
	}

	public String getFristInsuranceData() {
		return fristInsuranceData;
	}

	public void setFristInsuranceData(String fristInsuranceData) {
		this.fristInsuranceData = fristInsuranceData;
	}

	public String getInstorehouseDate() {
		return instorehouseDate;
	}

	public void setInstorehouseDate(String instorehouseDate) {
		this.instorehouseDate = instorehouseDate;
	}

	public String getCarSellData() {
		return carSellData;
	}

	public void setCarSellData(String carSellData) {
		this.carSellData = carSellData;
	}

	public String getCarMotorNumber() {
		return carMotorNumber;
	}

	public void setCarMotorNumber(String carMotorNumber) {
		this.carMotorNumber = carMotorNumber;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarmodelN() {
		return carmodelN;
	}

	public void setCarmodelN(String carmodelN) {
		this.carmodelN = carmodelN;
	}

	public Integer getCarBrandId() {
		return carBrandId;
	}

	public void setCarBrandId(Integer carBrandId) {
		this.carBrandId = carBrandId;
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getFormvalue() {
		return formvalue;
	}

	public void setFormvalue(String formvalue) {
		this.formvalue = formvalue;
	}

	public String getCarLicenseName() {
		return carLicenseName;
	}

	public void setCarLicenseName(String carLicenseName) {
		this.carLicenseName = carLicenseName;
	}

	public String getSafeTypeN() {
		return safeTypeN;
	}

	public void setSafeTypeN(String safeTypeN) {
		this.safeTypeN = safeTypeN;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getXsCustomApplication() {
		return xsCustomApplication;
	}

	public void setXsCustomApplication(Integer xsCustomApplication) {
		this.xsCustomApplication = xsCustomApplication;
	}

	public String getXsCustomApplicationN() {
		return xsCustomApplicationN;
	}

	public void setXsCustomApplicationN(String xsCustomApplicationN) {
		this.xsCustomApplicationN = xsCustomApplicationN;
	}



	public String getReckoningName() {
		return reckoningName;
	}

	public void setReckoningName(String reckoningName) {
		this.reckoningName = reckoningName;
	}

	public Integer getInvoiceReckoning() {
		return invoiceReckoning;
	}

	public void setInvoiceReckoning(Integer invoiceReckoning) {
		this.invoiceReckoning = invoiceReckoning;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}

	public Integer getExamine() {
		return examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}


	public Integer getXs_Car_Sel_Id() {
		return xs_Car_Sel_Id;
	}

	public void setXs_Car_Sel_Id(Integer xsCarSelId) {
		xs_Car_Sel_Id = xsCarSelId;
	}

	public String getDafeDate2() {
		return dafeDate2;
	}

	public void setDafeDate2(String dafeDate2) {
		this.dafeDate2 = dafeDate2;
	}

	public String getDetaildateGrid() {
		return detaildateGrid;
	}

	public void setDetaildateGrid(String detaildateGrid) {
		this.detaildateGrid = detaildateGrid;
	}

	public String getDateGridForm() {
		return dateGridForm;
	}

	public void setDateGridForm(String dateGridForm) {
		this.dateGridForm = dateGridForm;
	}

	public String getCarColorName() {
		return carColorName;
	}

	public void setCarColorName(String carColorName) {
		this.carColorName = carColorName;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public String getCustomTelephone() {
		return customTelephone;
	}

	public void setCustomTelephone(String customTelephone) {
		this.customTelephone = customTelephone;
	}

	public String getCustomAddress() {
		return customAddress;
	}

	public void setCustomAddress(String customAddress) {
		this.customAddress = customAddress;
	}

	public String getContactsPerson() {
		return contactsPerson;
	}

	public void setContactsPerson(String contactsPerson) {
		this.contactsPerson = contactsPerson;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getCustomCredentials() {
		return customCredentials;
	}

	public void setCustomCredentials(String customCredentials) {
		this.customCredentials = customCredentials;
	}

	public String getCustomZipcode() {
		return customZipcode;
	}

	public void setCustomZipcode(String customZipcode) {
		this.customZipcode = customZipcode;
	}

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}



	public String getInsurancetype() {
		return insurancetype;
	}

	public void setInsurancetype(String insurancetype) {
		this.insurancetype = insurancetype;
	}

	public Double getInsurancemoney() {
		return insurancemoney;
	}

	public void setInsurancemoney(Double insurancemoney) {
		this.insurancemoney = insurancemoney;
	}

	public String getInsurancerate() {
		return insurancerate;
	}

	public void setInsurancerate(String insurancerate) {
		this.insurancerate = insurancerate;
	}

	public Double getInsurancecost() {
		return insurancecost;
	}

	public void setInsurancecost(Double insurancecost) {
		this.insurancecost = insurancecost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCarLicensePlate() {
		return carLicensePlate;
	}

	public void setCarLicensePlate(String carLicensePlate) {
		this.carLicensePlate = carLicensePlate;
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

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}



	public String getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(String insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public Integer getInsurer() {
		return insurer;
	}

	public void setInsurer(Integer insurer) {
		this.insurer = insurer;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public Integer getCustomId() {
		return customId;
	}

	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public Integer getCarColor() {
		return carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public Integer getCarModel() {
		return carModel;
	}

	public void setCarModel(Integer carModel) {
		this.carModel = carModel;
	}

	public Integer getCardColor() {
		return cardColor;
	}

	public void setCardColor(Integer cardColor) {
		this.cardColor = cardColor;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getOutprint() {
		return outprint;
	}

	public void setOutprint(String outprint) {
		this.outprint = outprint;
	}

	public Integer getLimitLoadNum() {
		return limitLoadNum;
	}

	public void setLimitLoadNum(Integer limitLoadNum) {
		this.limitLoadNum = limitLoadNum;
	}

	public Integer getUseNature() {
		return useNature;
	}

	public void setUseNature(Integer useNature) {
		this.useNature = useNature;
	}

	public String getDafeDate() {
		return dafeDate;
	}

	public void setDafeDate(String dafeDate) {
		this.dafeDate = dafeDate;
	}

	public String getInsurerStartDate() {
		return insurerStartDate;
	}

	public void setInsurerStartDate(String insurerStartDate) {
		this.insurerStartDate = insurerStartDate;
	}

	public String getInsurerEndDate() {
		return insurerEndDate;
	}

	public void setInsurerEndDate(String insurerEndDate) {
		this.insurerEndDate = insurerEndDate;
	}

	public String getSafeType() {
		return safeType;
	}

	public void setSafeType(String safeType) {
		this.safeType = safeType;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getInsuranceAgent() {
		return insuranceAgent;
	}

	public void setInsuranceAgent(String insuranceAgent) {
		this.insuranceAgent = insuranceAgent;
	}

	public Double getBusinessCharge() {
		return businessCharge;
	}

	public void setBusinessCharge(Double businessCharge) {
		this.businessCharge = businessCharge;
	}

	public Double getTrafficCharge() {
		return trafficCharge;
	}

	public void setTrafficCharge(Double trafficCharge) {
		this.trafficCharge = trafficCharge;
	}

	public Double getVehiclevesselTax() {
		return vehiclevesselTax;
	}

	public void setVehiclevesselTax(Double vehiclevesselTax) {
		this.vehiclevesselTax = vehiclevesselTax;
	}

	public Double getSafeCost() {
		return safeCost;
	}

	public void setSafeCost(Double safeCost) {
		this.safeCost = safeCost;
	}

	public Double getSafeAmount() {
		return safeAmount;
	}

	public void setSafeAmount(Double safeAmount) {
		this.safeAmount = safeAmount;
	}

	public String getBuysnessCost() {
		return buysnessCost;
	}

	public void setBuysnessCost(String buysnessCost) {
		this.buysnessCost = buysnessCost;
	}



	public Double getCustomCost() {
		return customCost;
	}



	public String getInCost() {
		return inCost;
	}

	public void setInCost(String inCost) {
		this.inCost = inCost;
	}


	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public void setCustomCost(Double customCost) {
		this.customCost = customCost;
	}

	public Double getExtract() {
		return extract;
	}

	public void setExtract(Double extract) {
		this.extract = extract;
	}

	public String getCustomReturncost() {
		return customReturncost;
	}

	public void setCustomReturncost(String customReturncost) {
		this.customReturncost = customReturncost;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getPrimeCost() {
		return primeCost;
	}

	public void setPrimeCost(Double primeCost) {
		this.primeCost = primeCost;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSell_code() {
		return sell_code;
	}

	public void setSell_code(String sellCode) {
		sell_code = sellCode;
	}

	public String getInsurerCodes() {
		return insurerCodes;
	}

	public void setInsurerCodes(String insurerCodes) {
		this.insurerCodes = insurerCodes;
	}

	public String getInsurerStartDate1() {
		return insurerStartDate1;
	}

	public void setInsurerStartDate1(String insurerStartDate1) {
		this.insurerStartDate1 = insurerStartDate1;
	}

	public String getInsurerEndDate1() {
		return insurerEndDate1;
	}

	public void setInsurerEndDate1(String insurerEndDate1) {
		this.insurerEndDate1 = insurerEndDate1;
	}
    

}
