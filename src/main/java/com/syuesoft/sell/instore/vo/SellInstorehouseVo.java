package com.syuesoft.sell.instore.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SellInstorehouseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 入库单汇总信息
	private Integer instorehouseId;
	private String instorehouseCode;
	private Timestamp instorehouseDate;
	private Integer invoiceType;
	private Long stfId;
	private Integer warehouse;
	private Integer purchaser;
	private String receipt;
	private String transportVehicles;
	private Integer examineState;
	private String remark;
	private Integer supplierId;
	private Integer source;// 库存来源
	private Integer state1;
	private Integer number;
	private Double totalNotax;// 未税合计
	private Double totalTax;// 税额
	private Double sumTax;// 含税合计
	private String supplier;
	private Integer enterprise_id;
	private String instorehouseCodes;
	

	private String instorehousedateGrid;
	private String detaildateGrid;
	private String inserted;
	private String deleted;
	private String updated;

	private String invoiceName;// 发票类型名称
	private String stfName;// 经办人姓名
	private String purchaserName;// 采购员姓名
	private String examineName;// 审核名称
	private String instorehouseName;// 库存类型名称
	private String warehouseName;// 仓库名称
	private String stateName;// 付讫状态名称
	// 入库单明细
	private Integer detailsId;
	private Integer instorehouse_;
	private String carInstorageResult;
	private Integer carInstoragePerson;
	private Date carbackDate;
	private String carbackReason;
	private Date carOutData;
	private Date carPdsData;
	private Integer carPdsPerson;
	private String carPdsResult;
	private Double carInstorageAge;
	private Double vehicleCost;
	private Double notax;
	private Double tax;
	private Integer sellAllocatelType;
	private String allocatelName;
	private String orderNumber;// 订货单号(只适合导入)
	private Date instorageDate;// 进库日期(只适合导入)
	private Double changeMoney;// 改装费
	private Double freightMoney;// 运费
	// 车辆档案信息
	private Integer carId;
	private String carCode;
	private Integer carModelId;
	private Integer carBrand;
	private Integer carColor;
	private Integer carInteriorColor;
	private String carVinNumber;
	private String carOcn;
	private String carMotorNumber;
	private String carProductionAddress;
	private Date carMakeData;
	private Double modelSalesPrice;// 标准销价
	private String carBrandName;
	private String carModelName;
	private String colorName;
	private String interiorName;
	private String carLicenseName;
	private Integer sellState;
	private String sellStateName;
	private String carTradeCheckBill;
	private String carCertificate;
	private String carLicensePlate;
	private String distributorName;
	// 客户信息
	private String customName;// 客户名称
	private String customAddress;// 地址
	private String customPhone;// 固定电话
	private String customTelephone;// 手机
	private String customInputdata;// 建档日期

	// 销售信息
	private String carSelData;// 销售日期
	private String carSelData2;// 销售日期
	private Double selTransactionMoney;// 销售价
	// 保险信息
	private String insurerEndDate;// 保险到期日期
	private Integer insurerId;// 保险公司id
	private String insurerName;// 保险公司名称
	private String  insuranceAgent;//保险员
	private String  insurancePolicy;//保险单号
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	private String state;
	private String iconCls;
	// 查询字段
	private String queryInstoreDate;
	private String queryInstoreDate2;
	private String instoreStart;
	private String instoreEnd;
	private String copyDateStart;
	private String copyDateEnd;
	private String queryInstoreCode;
	private String queryVinNumber;
	private Integer queryBrand;
	private Integer queryColor;
	private Integer queryExamine;
	private Integer queryCarModel;
	private Integer querySupplier;
	private Integer personId;
	private String key;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getQueryInstoreDate2() {
		return queryInstoreDate2;
	}

	public String getInstorehouseCodes() {
		return instorehouseCodes;
	}
	
	public void setInstorehouseCodes(String instorehouseCodes) {
		this.instorehouseCodes = instorehouseCodes;
	}
	
	public Integer getEnterprise_id() {
		return enterprise_id;
	}
	
	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}
	
	public String getSupplier() {
		return supplier;
	}
	
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public void setQueryInstoreDate2(String queryInstoreDate2) {
		this.queryInstoreDate2 = queryInstoreDate2;
	}

	public String getCarSelData2() {
		return carSelData2;
	}

	public void setCarSelData2(String carSelData2) {
		this.carSelData2 = carSelData2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(Double changeMoney) {
		this.changeMoney = changeMoney;
	}

	public Double getFreightMoney() {
		return freightMoney;
	}

	public void setFreightMoney(Double freightMoney) {
		this.freightMoney = freightMoney;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public Integer getInstorehouseId() {
		return instorehouseId;
	}

	public void setInstorehouseId(Integer instorehouseId) {
		this.instorehouseId = instorehouseId;
	}

	public String getInstorehouseCode() {
		return instorehouseCode;
	}

	public void setInstorehouseCode(String instorehouseCode) {
		this.instorehouseCode = instorehouseCode;
	}

	public Timestamp getInstorehouseDate() {
		return instorehouseDate;
	}

	public void setInstorehouseDate(Timestamp instorehouseDate) {
		this.instorehouseDate = instorehouseDate;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Long getStfId() {
		return stfId;
	}

	public void setStfId(Long stfId) {
		this.stfId = stfId;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	public Integer getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(Integer purchaser) {
		this.purchaser = purchaser;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Integer getState1() {
		return state1;
	}

	public void setState1(Integer state1) {
		this.state1 = state1;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getTotalNotax() {
		return totalNotax;
	}

	public void setTotalNotax(Double totalNotax) {
		this.totalNotax = totalNotax;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public String getTransportVehicles() {
		return transportVehicles;
	}

	public void setTransportVehicles(String transportVehicles) {
		this.transportVehicles = transportVehicles;
	}

	public Integer getExamineState() {
		return examineState;
	}

	public void setExamineState(Integer examineState) {
		this.examineState = examineState;
	}

	public Integer getSellAllocatelType() {
		return sellAllocatelType;
	}

	public void setSellAllocatelType(Integer sellAllocatelType) {
		this.sellAllocatelType = sellAllocatelType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInstorehousedateGrid() {
		return instorehousedateGrid;
	}

	public void setInstorehousedateGrid(String instorehousedateGrid) {
		this.instorehousedateGrid = instorehousedateGrid;
	}

	public String getDetaildateGrid() {
		return detaildateGrid;
	}

	public void setDetaildateGrid(String detaildateGrid) {
		this.detaildateGrid = detaildateGrid;
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

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public Integer getInstorehouse_() {
		return instorehouse_;
	}

	public void setInstorehouse_(Integer instorehouse) {
		instorehouse_ = instorehouse;
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

	public Date getCarbackDate() {
		return carbackDate;
	}

	public void setCarbackDate(Date carbackDate) {
		this.carbackDate = carbackDate;
	}

	public String getCarbackReason() {
		return carbackReason;
	}

	public void setCarbackReason(String carbackReason) {
		this.carbackReason = carbackReason;
	}

	public Date getCarOutData() {
		return carOutData;
	}

	public void setCarOutData(Date carOutData) {
		this.carOutData = carOutData;
	}

	public Date getCarPdsData() {
		return carPdsData;
	}

	public void setCarPdsData(Date carPdsData) {
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

	public Double getCarInstorageAge() {
		return carInstorageAge;
	}

	public void setCarInstorageAge(Double carInstorageAge) {
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

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
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

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public Double getModelSalesPrice() {
		return modelSalesPrice;
	}

	public void setModelSalesPrice(Double modelSalesPrice) {
		this.modelSalesPrice = modelSalesPrice;
	}

	public String getCarMotorNumber() {
		return carMotorNumber;
	}

	public void setCarMotorNumber(String carMotorNumber) {
		this.carMotorNumber = carMotorNumber;
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

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getQueryInstoreDate() {
		return queryInstoreDate;
	}

	public void setQueryInstoreDate(String queryInstoreDate) {
		this.queryInstoreDate = queryInstoreDate;
	}

	public String getQueryInstoreCode() {
		return queryInstoreCode;
	}

	public void setQueryInstoreCode(String queryInstoreCode) {
		this.queryInstoreCode = queryInstoreCode;
	}

	public String getQueryVinNumber() {
		return queryVinNumber;
	}

	public void setQueryVinNumber(String queryVinNumber) {
		this.queryVinNumber = queryVinNumber;
	}

	public Integer getQueryExamine() {
		return queryExamine;
	}

	public void setQueryExamine(Integer queryExamine) {
		this.queryExamine = queryExamine;
	}

	public Integer getQueryCarModel() {
		return queryCarModel;
	}

	public void setQueryCarModel(Integer queryCarModel) {
		this.queryCarModel = queryCarModel;
	}

	public String getInserted() {
		return inserted;
	}

	public void setInserted(String inserted) {
		this.inserted = inserted;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public Integer getQuerySupplier() {
		return querySupplier;
	}

	public void setQuerySupplier(Integer querySupplier) {
		this.querySupplier = querySupplier;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}

	public String getInstorehouseName() {
		return instorehouseName;
	}

	public void setInstorehouseName(String instorehouseName) {
		this.instorehouseName = instorehouseName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Integer getQueryBrand() {
		return queryBrand;
	}

	public void setQueryBrand(Integer queryBrand) {
		this.queryBrand = queryBrand;
	}

	public Integer getQueryColor() {
		return queryColor;
	}

	public void setQueryColor(Integer queryColor) {
		this.queryColor = queryColor;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getInstorageDate() {
		return instorageDate;
	}

	public void setInstorageDate(Date instorageDate) {
		this.instorageDate = instorageDate;
	}

	public Integer getCarInteriorColor() {
		return carInteriorColor;
	}

	public void setCarInteriorColor(Integer carInteriorColor) {
		this.carInteriorColor = carInteriorColor;
	}

	public String getCarProductionAddress() {
		return carProductionAddress;
	}

	public void setCarProductionAddress(String carProductionAddress) {
		this.carProductionAddress = carProductionAddress;
	}

	public Date getCarMakeData() {
		return carMakeData;
	}

	public void setCarMakeData(Date carMakeData) {
		this.carMakeData = carMakeData;
	}

	public String getInteriorName() {
		return interiorName;
	}

	public void setInteriorName(String interiorName) {
		this.interiorName = interiorName;
	}

	public String getCarLicenseName() {
		return carLicenseName;
	}

	public void setCarLicenseName(String carLicenseName) {
		this.carLicenseName = carLicenseName;
	}

	public Double getSumTax() {
		return sumTax;
	}

	public void setSumTax(Double sumTax) {
		this.sumTax = sumTax;
	}

	public String getInstoreStart() {
		return instoreStart;
	}

	public void setInstoreStart(String instoreStart) {
		this.instoreStart = instoreStart;
	}

	public String getInstoreEnd() {
		return instoreEnd;
	}

	public void setInstoreEnd(String instoreEnd) {
		this.instoreEnd = instoreEnd;
	}

	public String getCopyDateStart() {
		return copyDateStart;
	}

	public void setCopyDateStart(String copyDateStart) {
		this.copyDateStart = copyDateStart;
	}

	public String getCopyDateEnd() {
		return copyDateEnd;
	}

	public void setCopyDateEnd(String copyDateEnd) {
		this.copyDateEnd = copyDateEnd;
	}

	public Integer getSellState() {
		return sellState;
	}

	public void setSellState(Integer sellState) {
		this.sellState = sellState;
	}

	public String getSellStateName() {
		return sellStateName;
	}

	public void setSellStateName(String sellStateName) {
		this.sellStateName = sellStateName;
	}

	public String getCarSelData() {
		return carSelData;
	}

	public void setCarSelData(String carSelData) {
		this.carSelData = carSelData;
	}

	public Double getSelTransactionMoney() {
		return selTransactionMoney;
	}

	public void setSelTransactionMoney(Double selTransactionMoney) {
		this.selTransactionMoney = selTransactionMoney;
	}

	public String getAllocatelName() {
		return allocatelName;
	}

	public void setAllocatelName(String allocatelName) {
		this.allocatelName = allocatelName;
	}

	public String getInsurerEndDate() {
		return insurerEndDate;
	}

	public void setInsurerEndDate(String insurerEndDate) {
		this.insurerEndDate = insurerEndDate;
	}

	public Integer getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Integer insurerId) {
		this.insurerId = insurerId;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public String getCarTradeCheckBill() {
		return carTradeCheckBill;
	}

	public void setCarTradeCheckBill(String carTradeCheckBill) {
		this.carTradeCheckBill = carTradeCheckBill;
	}

	public String getCustomAddress() {
		return customAddress;
	}

	public void setCustomAddress(String customAddress) {
		this.customAddress = customAddress;
	}

	public String getCustomPhone() {
		return customPhone;
	}

	public void setCustomPhone(String customPhone) {
		this.customPhone = customPhone;
	}

	public String getCustomTelephone() {
		return customTelephone;
	}

	public void setCustomTelephone(String customTelephone) {
		this.customTelephone = customTelephone;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getCarCertificate() {
		return carCertificate;
	}

	public void setCarCertificate(String carCertificate) {
		this.carCertificate = carCertificate;
	}
	public String getCustomInputdata() {
		return customInputdata;
	}

	public void setCustomInputdata(String customInputdata) {
		this.customInputdata = customInputdata;
	}

	public String getCarLicensePlate() {
		return carLicensePlate;
	}

	public void setCarLicensePlate(String carLicensePlate) {
		this.carLicensePlate = carLicensePlate;
	}

	public String getInsuranceAgent() {
		return insuranceAgent;
	}

	public void setInsuranceAgent(String insuranceAgent) {
		this.insuranceAgent = insuranceAgent;
	}

	public String getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(String insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	

}