package com.syuesoft.sell.instore.vo;

import java.util.Date;

public class SellMonthlyStatementVo {

	private int page;
	private int rows;
	private String detailsId;
	private String instorehouseId;
	private String carId;
	private String carVinNumber;
	private String carBrand;
	private String carCode;
	private String carColor;
	private String carModel;
	private String allocateId;// 调拨单编号
	private String carInstorageResult;
	private String carInstoragePerson;
	private String carbackDate;
	private String carbackReason;
	private String carOutData;
	private String carPdsData;
	private String carPdsPerson;
	private String xsCensus; // 是否结转
	private String carPdsResult;
	private String carInstorageAge;
	private String vehicleCost;
	private String sellAllocatelType;
	private String instorageDate;// 进库日期(只适合导入)

	private String instorehouseCode;
	private String instorehouseDate;
	private String invoiceType;
	private String stfId;
	private String warehouse;
	private String purchaser;
	private String receipt;
	private String state;
	private String number;
	private String notax;
	private String tax;
	private String transportVehicles;
	private String examineState;
	private String remark;
	private String supplierId;
	private String source;// 库存来源

	private String censusId;
	private String censusSum;
	private String censusSdate;
	private String censusEdate;
	private String censusSdate2;
	private String censusEdate2;
	public String getCensusSdate2() {
		return censusSdate2;
	}

	public void setCensusSdate2(String censusSdate2) {
		this.censusSdate2 = censusSdate2;
	}

	public String getCensusEdate2() {
		return censusEdate2;
	}

	public void setCensusEdate2(String censusEdate2) {
		this.censusEdate2 = censusEdate2;
	}

	private String censusRdate;
	private Integer enterprise_id;
	private String collectId;		//月结汇总编号

	
	
	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getCensusId() {
		return censusId;
	}

	public void setCensusId(String censusId) {
		this.censusId = censusId;
	}

	public String getCensusSum() {
		return censusSum;
	}

	public void setCensusSum(String censusSum) {
		this.censusSum = censusSum;
	}

	public String getCensusSdate() {
		return censusSdate;
	}

	public void setCensusSdate(String censusSdate) {
		this.censusSdate = censusSdate;
	}

	public String getCensusEdate() {
		return censusEdate;
	}

	public void setCensusEdate(String censusEdate) {
		this.censusEdate = censusEdate;
	}

	public String getCensusRdate() {
		return censusRdate;
	}

	public void setCensusRdate(String censusRdate) {
		this.censusRdate = censusRdate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getCarVinNumber() {
		return carVinNumber;
	}

	public void setCarVinNumber(String carVinNumber) {
		this.carVinNumber = carVinNumber;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getInstorehouseCode() {
		return instorehouseCode;
	}

	public void setInstorehouseCode(String instorehouseCode) {
		this.instorehouseCode = instorehouseCode;
	}

	public String getInstorehouseDate() {
		return instorehouseDate;
	}

	public void setInstorehouseDate(String instorehouseDate) {
		this.instorehouseDate = instorehouseDate;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getStfId() {
		return stfId;
	}

	public void setStfId(String stfId) {
		this.stfId = stfId;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTransportVehicles() {
		return transportVehicles;
	}

	public void setTransportVehicles(String transportVehicles) {
		this.transportVehicles = transportVehicles;
	}

	public String getExamineState() {
		return examineState;
	}

	public void setExamineState(String examineState) {
		this.examineState = examineState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	public String getInstorehouseId() {
		return instorehouseId;
	}

	public void setInstorehouseId(String instorehouseId) {
		this.instorehouseId = instorehouseId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getAllocateId() {
		return allocateId;
	}

	public void setAllocateId(String allocateId) {
		this.allocateId = allocateId;
	}

	public String getCarInstorageResult() {
		return carInstorageResult;
	}

	public void setCarInstorageResult(String carInstorageResult) {
		this.carInstorageResult = carInstorageResult;
	}

	public String getCarInstoragePerson() {
		return carInstoragePerson;
	}

	public void setCarInstoragePerson(String carInstoragePerson) {
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

	public String getCarPdsPerson() {
		return carPdsPerson;
	}

	public void setCarPdsPerson(String carPdsPerson) {
		this.carPdsPerson = carPdsPerson;
	}

	public String getXsCensus() {
		return xsCensus;
	}

	public void setXsCensus(String xsCensus) {
		this.xsCensus = xsCensus;
	}

	public String getCarPdsResult() {
		return carPdsResult;
	}

	public void setCarPdsResult(String carPdsResult) {
		this.carPdsResult = carPdsResult;
	}

	public String getCarInstorageAge() {
		return carInstorageAge;
	}

	public void setCarInstorageAge(String carInstorageAge) {
		this.carInstorageAge = carInstorageAge;
	}

	public String getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(String vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

	public String getNotax() {
		return notax;
	}

	public void setNotax(String notax) {
		this.notax = notax;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getSellAllocatelType() {
		return sellAllocatelType;
	}

	public void setSellAllocatelType(String sellAllocatelType) {
		this.sellAllocatelType = sellAllocatelType;
	}

	public String getInstorageDate() {
		return instorageDate;
	}

	public void setInstorageDate(String instorageDate) {
		this.instorageDate = instorageDate;
	}

}
