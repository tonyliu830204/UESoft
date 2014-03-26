package com.syuesoft.sell.allocateManage.vo;

import com.syuesoft.model.BaseBean;

import com.syuesoft.sell.model.XsSellAllocatel;

public class InstorehouseVo extends BaseBean {

	// 入库单明细
	private Integer detailsId;
	private String instorehouseId;
	private String xsCarId;
	private XsSellAllocatel sellAllocatel;
	private String carInstorageResult;
	private Integer carInstoragePerson;
	private String carbackDate;
	private String carbackReason;
	private String carOutData;
	private String carPdsData;
	private Integer carPdsPerson;
	private String pdsPerson;
	private String carPdsResult;
	private Integer carInstorageAge;// 库龄
	private Integer carInstorageAge1;// 库龄
	private Double vehicleCost;
	private Double notax;
	private Double tax;
	private Integer sellAllocatelType;

	private String instorehouseDate;
	private String instorehouseDate2;
	private Integer warehouse;
	private String warehouseN;
	private Integer customId;
	private String customName;
	private Integer xsSupplierId;
	private String xsSupplierName;
	private String sort;
	private String order;
	private int rows;
	private int page;
	private String carAge;
	// c车辆档案
	private Integer carId;
	private Integer carBrand;
	private String carBrandName;
	private Integer carColor;
	private String carColorName;
	private Integer carInteriorColor;
	private String carInteriorColorName;
	private Integer carModelId;
	private String carModelName;
	private Integer carOptional;
	private Integer carMortgageState;
	private Integer carCertificateState;
	private String carCertificateStateN;// 合格证状态
	private Integer carType;
	private String carCode;
	private String carLicensePlate;
	private String carLicenseName;
	private String carVinNumber;
	private String carMotorNumber;
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
	private String distributorName;
	private Integer carSellState;
	private String carSellStateN;// 销售状态
	private Integer carDistributState;
	private Integer fixStatus;
	private String servicingNextdate;
	private Double xsModelSalesPrice;// 销售价
	private Double xsModelCostPrice;// 成本价
	private String reserveDete;
	private String reserveCode;
	private Integer num;
	private Double salesPrice;
	private Double costPrice;
	private Integer orders;
	private Boolean columnsTag; // 动态列查询标识
	private Integer stfId;
	private String tabType;
	private Integer enterprise_id;
	private String times;
	
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getTabType() {
		return tabType;
	}

	public void setTabType(String tabType) {
		this.tabType = tabType;
	}

	public Integer getStfId() {
		return stfId;
	}

	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}

	public Boolean getColumnsTag() {
		return columnsTag;
	}

	public void setColumnsTag(Boolean columnsTag) {
		this.columnsTag = columnsTag;
	}

	public String getInstorehouseDate2() {
		return instorehouseDate2;
	}

	public void setInstorehouseDate2(String instorehouseDate2) {
		this.instorehouseDate2 = instorehouseDate2;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public String getCarAge() {
		return carAge;
	}

	public void setCarAge(String carAge) {
		this.carAge = carAge;
	}

	public Integer getCarInstorageAge1() {
		return carInstorageAge1;
	}

	public void setCarInstorageAge1(Integer carInstorageAge1) {
		this.carInstorageAge1 = carInstorageAge1;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public String getReserveCode() {
		return reserveCode;
	}

	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}

	public String getWarehouseN() {
		return warehouseN;
	}

	public void setWarehouseN(String warehouseN) {
		this.warehouseN = warehouseN;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	public Integer getXsSupplierId() {
		return xsSupplierId;
	}

	public void setXsSupplierId(Integer xsSupplierId) {
		this.xsSupplierId = xsSupplierId;
	}

	public String getXsSupplierName() {
		return xsSupplierName;
	}

	public void setXsSupplierName(String xsSupplierName) {
		this.xsSupplierName = xsSupplierName;
	}

	public String getPdsPerson() {
		return pdsPerson;
	}

	public void setPdsPerson(String pdsPerson) {
		this.pdsPerson = pdsPerson;
	}

	public String getReserveDete() {
		return reserveDete;
	}

	public void setReserveDete(String reserveDete) {
		this.reserveDete = reserveDete;
	}

	public Double getXsModelCostPrice() {
		return xsModelCostPrice;
	}

	public void setXsModelCostPrice(Double xsModelCostPrice) {
		this.xsModelCostPrice = xsModelCostPrice;
	}

	public Integer getCustomId() {
		return customId;
	}

	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getCarSellStateN() {
		return carSellStateN;
	}

	public void setCarSellStateN(String carSellStateN) {
		this.carSellStateN = carSellStateN;
	}

	public String getCarCertificateStateN() {
		return carCertificateStateN;
	}

	public void setCarCertificateStateN(String carCertificateStateN) {
		this.carCertificateStateN = carCertificateStateN;
	}

	public Double getXsModelSalesPrice() {
		return xsModelSalesPrice;
	}

	public void setXsModelSalesPrice(Double xsModelSalesPrice) {
		this.xsModelSalesPrice = xsModelSalesPrice;
	}

	public String getInstorehouseDate() {
		return instorehouseDate;
	}

	public void setInstorehouseDate(String instorehouseDate) {
		this.instorehouseDate = instorehouseDate;
	}

	public String getInstorehouseId() {
		return instorehouseId;
	}

	public void setInstorehouseId(String instorehouseId) {
		this.instorehouseId = instorehouseId;
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

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public String getXsCarId() {
		return xsCarId;
	}

	public void setXsCarId(String xsCarId) {
		this.xsCarId = xsCarId;
	}

	public XsSellAllocatel getSellAllocatel() {
		return sellAllocatel;
	}

	public void setSellAllocatel(XsSellAllocatel sellAllocatel) {
		this.sellAllocatel = sellAllocatel;
	}

	public String getCarInstorageResult() {
		return carInstorageResult;
	}

	public void setCarInstorageResult(String carInstorageResult) {
		this.carInstorageResult = carInstorageResult;
	}

	public Integer getCarInstoragePerson() {
		return carInstoragePerson;
	}

	public void setCarInstoragePerson(Integer carInstoragePerson) {
		this.carInstoragePerson = carInstoragePerson;
	}

	public String getCarbackDate() {
		return carbackDate;
	}

	public void setCarbackDate(String carbackDate) {
		this.carbackDate = carbackDate;
	}

	public String getCarbackReason() {
		return carbackReason;
	}

	public void setCarbackReason(String carbackReason) {
		this.carbackReason = carbackReason;
	}

	public String getCarOutData() {
		return carOutData;
	}

	public void setCarOutData(String carOutData) {
		this.carOutData = carOutData;
	}

	public String getCarPdsData() {
		return carPdsData;
	}

	public void setCarPdsData(String carPdsData) {
		this.carPdsData = carPdsData;
	}

	public Integer getCarPdsPerson() {
		return carPdsPerson;
	}

	public void setCarPdsPerson(Integer carPdsPerson) {
		this.carPdsPerson = carPdsPerson;
	}

	public String getCarPdsResult() {
		return carPdsResult;
	}

	public void setCarPdsResult(String carPdsResult) {
		this.carPdsResult = carPdsResult;
	}

	public Integer getCarInstorageAge() {
		return carInstorageAge;
	}

	public void setCarInstorageAge(Integer carInstorageAge) {
		this.carInstorageAge = carInstorageAge;
	}

	public Double getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

	public Double getNotax() {
		return notax;
	}

	public void setNotax(Double notax) {
		this.notax = notax;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Integer getSellAllocatelType() {
		return sellAllocatelType;
	}

	public void setSellAllocatelType(Integer sellAllocatelType) {
		this.sellAllocatelType = sellAllocatelType;
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

	public String getCarInteriorColorName() {
		return carInteriorColorName;
	}

	public void setCarInteriorColorName(String carInteriorColorName) {
		this.carInteriorColorName = carInteriorColorName;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
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

	public Integer getFixStatus() {
		return fixStatus;
	}

	public void setFixStatus(Integer fixStatus) {
		this.fixStatus = fixStatus;
	}

	public String getServicingNextdate() {
		return servicingNextdate;
	}

	public void setServicingNextdate(String servicingNextdate) {
		this.servicingNextdate = servicingNextdate;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

}
