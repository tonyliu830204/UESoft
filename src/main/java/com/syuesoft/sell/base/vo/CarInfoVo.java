package com.syuesoft.sell.base.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.syuesoft.base.vo.BaseBeanVo;

public class CarInfoVo extends BaseBeanVo implements Serializable {
	private Integer carId;
	private Integer carBrand;
	private Integer carColor;
	private Integer carInteriorColor;
	private Integer carModelId;
	private Integer carOptional;
	private Integer carMortgageState;
	private Integer carCertificateState;
	private Integer carType;
	private String carCode;
	private String carLicensePlate;
	private String carLicenseName;
	private String carVinNumber;
	private String carMotorNumber;
	private String carOcn;
	private String carCertificate;
	private Timestamp carMakeData;
	private String carUnlockingKeyNumber;
	private Integer carRideLimitNumber;
	private String carTradeCheckBill;
	private String carProductionAddress;
	private String carAddress;
	private Timestamp carCopyData;
	private Timestamp carAssembleData;
	private Timestamp carEndCheckData;
	private Timestamp carFristInsuranceData;
	private Timestamp carForecastData;
	private Double carPrice;
	private Integer distributorId;
	private Integer carSellState;
	private Integer carDistributState;
	private Integer fixStatus;
	private Double vehicleCost;

	private String interiorColorName;
	private String distributorName;
	private String sellStateName;
	private String distributStateName;
	private String carBrandName;
	private String carModelName;
	private String colorName;
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	// 查询所用字段
	private String vinNumber;
	private Integer interiorColor;
	private Timestamp copyDataStart;
	private Timestamp copyDataEnd;
	private String ocnNumber;
	private Integer distributor;
	private Integer mortgageState;
	private Integer distributState;
	private Integer sellState;
	private Integer queryBrand;
	private Integer queryModel;
	private Integer queryColor;
	private String id;

	private String sellData;
	private String sellData2;
	private String sellCode;
	private String retreat_date;
	private String retreat_code;
	private String upData;
	private Integer upPerson;
	private String customName;
	private String tel;
	private Integer supplierId;
	private String supplierName;
	private Integer outId;
	private String upPersonName;
	private String upType;

	private Integer dyCount;
	private Integer yhCount;
	private Integer attachment_no;
	private Integer count1;
	private Integer count2;
	private Integer operator_persion;
	private String person;
	private String operator_date;//经办日期
	private String operator_date2;//经办日期
	private String send_persion;
	private String receive_persion;
	private Integer operator_type;
	private String types;
	private String remark;
	private Integer norms;// 规格
	private Integer toolCase;// 工具包
	private String proveFile;// 证明文件
	private String rzBook;// 认证书
	private Integer footd;// 脚垫
	private String footdN;
	private String normsN;
	private String toolCaseN;
	private Integer enterpriseId;
	private String times;
	private Boolean flag;
	
	public String getSellData2() {
		return sellData2;
	}

	public void setSellData2(String sellData2) {
		this.sellData2 = sellData2;
	}

	public String getOperator_date2() {
		return operator_date2;
	}

	public void setOperator_date2(String operatorDate2) {
		operator_date2 = operatorDate2;
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

	public String getFootdN() {
		return footdN;
	}

	public void setFootdN(String footdN) {
		this.footdN = footdN;
	}

	public String getNormsN() {
		return normsN;
	}

	public void setNormsN(String normsN) {
		this.normsN = normsN;
	}

	public String getToolCaseN() {
		return toolCaseN;
	}

	public void setToolCaseN(String toolCaseN) {
		this.toolCaseN = toolCaseN;
	}

	public Integer getNorms() {
		return norms;
	}

	public void setNorms(Integer norms) {
		this.norms = norms;
	}

	public Integer getToolCase() {
		return toolCase;
	}

	public void setToolCase(Integer toolCase) {
		this.toolCase = toolCase;
	}

	public String getProveFile() {
		return proveFile;
	}

	public void setProveFile(String proveFile) {
		this.proveFile = proveFile;
	}

	public String getRzBook() {
		return rzBook;
	}

	public void setRzBook(String rzBook) {
		this.rzBook = rzBook;
	}

	public Integer getFootd() {
		return footd;
	}

	public void setFootd(Integer footd) {
		this.footd = footd;
	}

	public Integer getDyCount() {
		return dyCount;
	}

	public void setDyCount(Integer dyCount) {
		this.dyCount = dyCount;
	}

	public Integer getYhCount() {
		return yhCount;
	}

	public void setYhCount(Integer yhCount) {
		this.yhCount = yhCount;
	}

	public Integer getAttachment_no() {
		return attachment_no;
	}

	public void setAttachment_no(Integer attachmentNo) {
		attachment_no = attachmentNo;
	}

	public Integer getCount1() {
		return count1;
	}

	public void setCount1(Integer count1) {
		this.count1 = count1;
	}

	public Integer getCount2() {
		return count2;
	}

	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	public Integer getOperator_persion() {
		return operator_persion;
	}

	public void setOperator_persion(Integer operatorPersion) {
		operator_persion = operatorPersion;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getOperator_date() {
		return operator_date;
	}

	public void setOperator_date(String operatorDate) {
		operator_date = operatorDate;
	}

	public String getSend_persion() {
		return send_persion;
	}

	public void setSend_persion(String sendPersion) {
		send_persion = sendPersion;
	}

	public String getReceive_persion() {
		return receive_persion;
	}

	public void setReceive_persion(String receivePersion) {
		receive_persion = receivePersion;
	}

	public Integer getOperator_type() {
		return operator_type;
	}

	public void setOperator_type(Integer operatorType) {
		operator_type = operatorType;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpType() {
		return upType;
	}

	public void setUpType(String upType) {
		this.upType = upType;
	}

	public String getUpPersonName() {
		return upPersonName;
	}

	public void setUpPersonName(String upPersonName) {
		this.upPersonName = upPersonName;
	}

	public String getUpData() {
		return upData;
	}

	public void setUpData(String upData) {
		this.upData = upData;
	}

	public Integer getUpPerson() {
		return upPerson;
	}

	public void setUpPerson(Integer upPerson) {
		this.upPerson = upPerson;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getOutId() {
		return outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public String getSellData() {
		return sellData;
	}

	public void setSellData(String sellData) {
		this.sellData = sellData;
	}

	public String getSellCode() {
		return sellCode;
	}

	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}

	public String getRetreat_date() {
		return retreat_date;
	}

	public void setRetreat_date(String retreatDate) {
		retreat_date = retreatDate;
	}

	public String getRetreat_code() {
		return retreat_code;
	}

	public void setRetreat_code(String retreatCode) {
		retreat_code = retreatCode;
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

	public Integer getCarOptional() {
		return carOptional;
	}

	public void setCarOptional(Integer carOptional) {
		this.carOptional = carOptional;
	}

	public Integer getCarMortgageState() {
		return carMortgageState;
	}

	public void setCarMortgageState(Integer carMortgageState) {
		this.carMortgageState = carMortgageState;
	}

	public Integer getCarCertificateState() {
		return carCertificateState;
	}

	public void setCarCertificateState(Integer carCertificateState) {
		this.carCertificateState = carCertificateState;
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

	public String getCarMotorNumber() {
		return carMotorNumber;
	}

	public void setCarMotorNumber(String carMotorNumber) {
		this.carMotorNumber = carMotorNumber;
	}

	public String getCarOcn() {
		return carOcn;
	}

	public void setCarOcn(String carOcn) {
		this.carOcn = carOcn;
	}

	public String getCarCertificate() {
		return carCertificate;
	}

	public void setCarCertificate(String carCertificate) {
		this.carCertificate = carCertificate;
	}

	public Timestamp getCarMakeData() {
		return carMakeData;
	}

	public void setCarMakeData(Timestamp carMakeData) {
		this.carMakeData = carMakeData;
	}

	public String getCarUnlockingKeyNumber() {
		return carUnlockingKeyNumber;
	}

	public void setCarUnlockingKeyNumber(String carUnlockingKeyNumber) {
		this.carUnlockingKeyNumber = carUnlockingKeyNumber;
	}

	public Integer getCarRideLimitNumber() {
		return carRideLimitNumber;
	}

	public void setCarRideLimitNumber(Integer carRideLimitNumber) {
		this.carRideLimitNumber = carRideLimitNumber;
	}

	public String getCarTradeCheckBill() {
		return carTradeCheckBill;
	}

	public void setCarTradeCheckBill(String carTradeCheckBill) {
		this.carTradeCheckBill = carTradeCheckBill;
	}

	public String getCarProductionAddress() {
		return carProductionAddress;
	}

	public void setCarProductionAddress(String carProductionAddress) {
		this.carProductionAddress = carProductionAddress;
	}

	public String getCarAddress() {
		return carAddress;
	}

	public void setCarAddress(String carAddress) {
		this.carAddress = carAddress;
	}

	public Timestamp getCarCopyData() {
		return carCopyData;
	}

	public void setCarCopyData(Timestamp carCopyData) {
		this.carCopyData = carCopyData;
	}

	public Timestamp getCarAssembleData() {
		return carAssembleData;
	}

	public void setCarAssembleData(Timestamp carAssembleData) {
		this.carAssembleData = carAssembleData;
	}

	public Timestamp getCarEndCheckData() {
		return carEndCheckData;
	}

	public void setCarEndCheckData(Timestamp carEndCheckData) {
		this.carEndCheckData = carEndCheckData;
	}

	public Timestamp getCarFristInsuranceData() {
		return carFristInsuranceData;
	}

	public void setCarFristInsuranceData(Timestamp carFristInsuranceData) {
		this.carFristInsuranceData = carFristInsuranceData;
	}

	public Timestamp getCarForecastData() {
		return carForecastData;
	}

	public void setCarForecastData(Timestamp carForecastData) {
		this.carForecastData = carForecastData;
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

	public String getInteriorColorName() {
		return interiorColorName;
	}

	public void setInteriorColorName(String interiorColorName) {
		this.interiorColorName = interiorColorName;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getSellStateName() {
		return sellStateName;
	}

	public void setSellStateName(String sellStateName) {
		this.sellStateName = sellStateName;
	}

	public String getDistributStateName() {
		return distributStateName;
	}

	public void setDistributStateName(String distributStateName) {
		this.distributStateName = distributStateName;
	}

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
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

	public String getVinNumber() {
		return vinNumber;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	public Integer getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(Integer interiorColor) {
		this.interiorColor = interiorColor;
	}

	public Timestamp getCopyDataStart() {
		return copyDataStart;
	}

	public void setCopyDataStart(Timestamp copyDataStart) {
		this.copyDataStart = copyDataStart;
	}

	public Timestamp getCopyDataEnd() {
		return copyDataEnd;
	}

	public void setCopyDataEnd(Timestamp copyDataEnd) {
		this.copyDataEnd = copyDataEnd;
	}

	public String getOcnNumber() {
		return ocnNumber;
	}

	public void setOcnNumber(String ocnNumber) {
		this.ocnNumber = ocnNumber;
	}

	public Integer getDistributor() {
		return distributor;
	}

	public void setDistributor(Integer distributor) {
		this.distributor = distributor;
	}

	public Integer getMortgageState() {
		return mortgageState;
	}

	public void setMortgageState(Integer mortgageState) {
		this.mortgageState = mortgageState;
	}

	public Integer getDistributState() {
		return distributState;
	}

	public void setDistributState(Integer distributState) {
		this.distributState = distributState;
	}

	public Integer getSellState() {
		return sellState;
	}

	public void setSellState(Integer sellState) {
		this.sellState = sellState;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Integer getQueryBrand() {
		return queryBrand;
	}

	public void setQueryBrand(Integer queryBrand) {
		this.queryBrand = queryBrand;
	}

	public Integer getQueryModel() {
		return queryModel;
	}

	public void setQueryModel(Integer queryModel) {
		this.queryModel = queryModel;
	}

	public Integer getQueryColor() {
		return queryColor;
	}

	public void setQueryColor(Integer queryColor) {
		this.queryColor = queryColor;
	}

	public Integer getFixStatus() {
		return fixStatus;
	}

	public void setFixStatus(Integer fixStatus) {
		this.fixStatus = fixStatus;
	}

	public Double getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
