package com.syuesoft.sell.carAllocate.vo;

/**
 * 库存车辆信息
 * 
 */

public class CarBarnInfoVo {
	private Integer carId;
	private Integer carBrand;
	private String carBrandName;// 车辆品牌
	private Integer carColor;
	private String carColorName;// 车辆颜色名称
	private Integer carInteriorColor;
	private Integer supplierId;
	private Integer carModel;
	private String carModelName;// 车类型
	private Integer carOptional;
	private Integer carMortgageState;
	private Integer carCertificateState;
	private Integer carType;
	private String carCode;// 车辆编号
	private String carLicensePlate;
	private String carLicenseName;// 厂牌名称
	private String carVinNumber;
	private String carMotorNumber;// 发动机编号
	private String carOcn;
	private String carCertificate;
	private String carMakeData;
	private String carUnlockingKeyNumber;
	private Integer carRideLimitNumber;
	private String carTradeCheckBill;
	private String carProductionAddress;
	private String carAddress;
	private String carCopyData;
	private String carAssembleData;
	private String carEndCheckData;
	private String carFristInsuranceData;
	private String carForecastData;
	private Double carPrice;
	private Integer distributorId;
	private String distributorName;// 分销商
	private Integer carSellState;// 销售状态
	private String carSellStateName;// 销售状态名称
	private Integer carDistributState;// 分销状态
	private String carDistributStateName;// 分销状态名称
	private Double modelCostPrice;// 总成本价
	private Double modelSalesPrice;// 销售价
	private Double carInstorageAge;// 库龄
	private Integer num;// 数量
	private Integer houseid;// 库位编号
	private String house;// 库位
	private Double allocateAmount;// 调拨金额
	private Integer paymentState;// 付讫状态
	private String paymentStateName;// 付讫状态
	private Integer detailsId;// 入库明细编号
	private Double vehicleCost;
	private Integer allocateId;// 调拨编号
	private Integer backId;// 调退编号
	private Double allAmount;// 调拨金额
	private Integer allocatel_detail_id;
	private String queryDate;
	private Integer enterprise_id;

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public Integer getAllocatel_detail_id() {
		return allocatel_detail_id;
	}

	public void setAllocatel_detail_id(Integer allocatelDetailId) {
		allocatel_detail_id = allocatelDetailId;
	}

	public Double getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}

	public Integer getBackId() {
		return backId;
	}

	public void setBackId(Integer backId) {
		this.backId = backId;
	}

	private String sort;
	private String order;
	private int rows;
	private int page;

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

	public Integer getAllocateId() {
		return allocateId;
	}

	public void setAllocateId(Integer allocateId) {
		this.allocateId = allocateId;
	}

	public Double getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public Double getAllocateAmount() {
		return allocateAmount;
	}

	public void setAllocateAmount(Double allocateAmount) {
		this.allocateAmount = allocateAmount;
	}

	public Integer getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(Integer paymentState) {
		this.paymentState = paymentState;
	}

	public String getPaymentStateName() {
		return paymentStateName;
	}

	public void setPaymentStateName(String paymentStateName) {
		this.paymentStateName = paymentStateName;
	}

	public Integer getHouseid() {
		return houseid;
	}

	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getCarSellStateName() {
		return carSellStateName;
	}

	public void setCarSellStateName(String carSellStateName) {
		this.carSellStateName = carSellStateName;
	}

	public String getCarDistributStateName() {
		return carDistributStateName;
	}

	public void setCarDistributStateName(String carDistributStateName) {
		this.carDistributStateName = carDistributStateName;
	}

	public Double getModelCostPrice() {
		return modelCostPrice;
	}

	public void setModelCostPrice(Double modelCostPrice) {
		this.modelCostPrice = modelCostPrice;
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

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getCarModel() {
		return carModel;
	}

	public void setCarModel(Integer carModel) {
		this.carModel = carModel;
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

	public String getCarMakeData() {
		return carMakeData;
	}

	public void setCarMakeData(String carMakeData) {
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

	public String getCarCopyData() {
		return carCopyData;
	}

	public void setCarCopyData(String carCopyData) {
		this.carCopyData = carCopyData;
	}

	public String getCarAssembleData() {
		return carAssembleData;
	}

	public void setCarAssembleData(String carAssembleData) {
		this.carAssembleData = carAssembleData;
	}

	public String getCarEndCheckData() {
		return carEndCheckData;
	}

	public void setCarEndCheckData(String carEndCheckData) {
		this.carEndCheckData = carEndCheckData;
	}

	public String getCarFristInsuranceData() {
		return carFristInsuranceData;
	}

	public void setCarFristInsuranceData(String carFristInsuranceData) {
		this.carFristInsuranceData = carFristInsuranceData;
	}

	public String getCarForecastData() {
		return carForecastData;
	}

	public void setCarForecastData(String carForecastData) {
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

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public String getCarColorName() {
		return carColorName;
	}

	public void setCarColorName(String carColorName) {
		this.carColorName = carColorName;
	}
}
