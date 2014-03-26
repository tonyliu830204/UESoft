package com.syuesoft.sell.instore.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import com.syuesoft.base.vo.BaseBeanVo;

public class SellRetreatVo extends BaseBeanVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer retreatId;
	private Integer detailsId_;
	private String retreatCode;
	private Date retreatDate;

	private String context;
	private Date xsCarPdsData;
	private Integer xsCarPdsPerson;
	private String xsCarPdsResult2;
	private Integer examine;
	private String examineType;
	private Integer inInstorehouse;
	private Integer outInstorehouse;
	private Integer person;
	private String personName;
	private Integer xs_Car_Sel_Id;
	private String house;
	private Integer xsSupplierId;
	private String sellCode;

	private Integer instorehouseType;

	private String sellRetreat;// 退厂记录
	private String instoreDelGrid;// 入库单明细
	private String inserted;
	private String deleted;
	private String updated;
	// 入库单汇总
	private Integer instorehouseId;
	private String instorehouseCode;
	private Timestamp instorehouseDate;
	private Integer supplierId;
	private String supplier;
	private String receipt;
	private Integer warehouse;
	private Double sumTax;// 含税合计
	// 入库单明细信息
	private Integer detailsId;
	private Integer instorehouse_;
	private Integer carInfo_;
	private String carInstorageResult;
	private Integer carInstoragePerson;
	private Date carbackDate;
	private String carbackReason;
	private Date carOutData;
	private Date carPdsData;
	private Integer carPdsPerson;
	private String carPdsResult;
	private Double carInstorageAge;
	private Date arriveDate;
	private Double vehicleCost;
	private Double notax;
	private Integer carback;
	private Double tax;
	// 车辆档案信息
	private Integer carId;
	private String carCode;
	private Integer carModelId;
	private Integer carBrand;
	private Integer carColor;
	private String carVinNumber;
	private String carOcn;
	private Integer carSellState;
	private Integer carDistributState;
	private String carLicenseName;
	private String carMotorNumber;
	private Double modelSalesPrice;// 标准销价
	private String sellStateName;
	private String distributStateName;
	private String carBrandName;
	private String carModelName;
	private String colorName;
	// 以下为分页字段
	private String state;
	private String iconCls;
	private String carState;
	private Integer enterprise_id;
	
	

	// 查询字段
	private String retreatDateStart;// 开始日期
	private String retreatDateEnd;// 结束日期
	private Integer querySupplier;// 供应商
	private String queryRetreatCode;// 退车单编号
	private Integer queryExamine;// 审核状态
	private String queryVinNumber;// VIN编号
	private String instoreStart;
	private String instoreEnd;
	private String reDateStart;
	private String repyDateEnd;
	private Boolean times;
	private String querymodel;


	public String getQuerymodel() {
		return querymodel;
	}

	public void setQuerymodel(String querymodel) {
		this.querymodel = querymodel;
	}

	public Boolean getTimes() {
		return times;
	}

	public void setTimes(Boolean times) {
		this.times = times;
	}

	public String getReDateStart() {
		return reDateStart;
	}

	public void setReDateStart(String reDateStart) {
		this.reDateStart = reDateStart;
	}

	public String getRepyDateEnd() {
		return repyDateEnd;
	}

	public void setRepyDateEnd(String repyDateEnd) {
		this.repyDateEnd = repyDateEnd;
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

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getCarState() {
		return carState;
	}

	public void setCarState(String carState) {
		this.carState = carState;
	}
	public String getSellCode() {
		return sellCode;
	}

	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public Integer getXs_Car_Sel_Id() {
		return xs_Car_Sel_Id;
	}

	public void setXs_Car_Sel_Id(Integer xsCarSelId) {
		xs_Car_Sel_Id = xsCarSelId;
	}

	public String getExamineType() {
		return examineType;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setExamineType(String examineType) {
		this.examineType = examineType;
	}

	public Integer getRetreatId() {
		return retreatId;
	}

	public void setRetreatId(Integer retreatId) {
		this.retreatId = retreatId;
	}

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public String getRetreatCode() {
		return retreatCode;
	}

	public void setRetreatCode(String retreatCode) {
		this.retreatCode = retreatCode;
	}

	public Date getRetreatDate() {
		return retreatDate;
	}

	public void setRetreatDate(Date retreatDate) {
		this.retreatDate = retreatDate;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getXsCarPdsData() {
		return xsCarPdsData;
	}

	public void setXsCarPdsData(Date xsCarPdsData) {
		this.xsCarPdsData = xsCarPdsData;
	}

	public Integer getXsCarPdsPerson() {
		return xsCarPdsPerson;
	}

	public void setXsCarPdsPerson(Integer xsCarPdsPerson) {
		this.xsCarPdsPerson = xsCarPdsPerson;
	}

	public String getXsCarPdsResult2() {
		return xsCarPdsResult2;
	}

	public void setXsCarPdsResult2(String xsCarPdsResult2) {
		this.xsCarPdsResult2 = xsCarPdsResult2;
	}

	public Integer getExamine() {
		return examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	public String getSellRetreat() {
		return sellRetreat;
	}

	public void setSellRetreat(String sellRetreat) {
		this.sellRetreat = sellRetreat;
	}

	public String getInstoreDelGrid() {
		return instoreDelGrid;
	}

	public void setInstoreDelGrid(String instoreDelGrid) {
		this.instoreDelGrid = instoreDelGrid;
	}

	public Integer getDetailsId_() {
		return detailsId_;
	}

	public void setDetailsId_(Integer detailsId) {
		detailsId_ = detailsId;
	}

	public Integer getInstorehouse_() {
		return instorehouse_;
	}

	public void setInstorehouse_(Integer instorehouse) {
		instorehouse_ = instorehouse;
	}

	public Integer getCarInfo_() {
		return carInfo_;
	}

	public void setCarInfo_(Integer carInfo) {
		carInfo_ = carInfo;
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

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
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

	public Integer getCarback() {
		return carback;
	}

	public void setCarback(Integer carback) {
		this.carback = carback;
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

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
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

	public String getCarLicenseName() {
		return carLicenseName;
	}

	public void setCarLicenseName(String carLicenseName) {
		this.carLicenseName = carLicenseName;
	}

	public String getCarMotorNumber() {
		return carMotorNumber;
	}

	public void setCarMotorNumber(String carMotorNumber) {
		this.carMotorNumber = carMotorNumber;
	}

	public Double getModelSalesPrice() {
		return modelSalesPrice;
	}

	public void setModelSalesPrice(Double modelSalesPrice) {
		this.modelSalesPrice = modelSalesPrice;
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

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
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


	public String getRetreatDateStart() {
		return retreatDateStart;
	}

	public void setRetreatDateStart(String retreatDateStart) {
		this.retreatDateStart = retreatDateStart;
	}

	public String getRetreatDateEnd() {
		return retreatDateEnd;
	}

	public void setRetreatDateEnd(String retreatDateEnd) {
		this.retreatDateEnd = retreatDateEnd;
	}

	public Integer getQuerySupplier() {
		return querySupplier;
	}

	public void setQuerySupplier(Integer querySupplier) {
		this.querySupplier = querySupplier;
	}

	public String getQueryRetreatCode() {
		return queryRetreatCode;
	}

	public void setQueryRetreatCode(String queryRetreatCode) {
		this.queryRetreatCode = queryRetreatCode;
	}

	public Integer getQueryExamine() {
		return queryExamine;
	}

	public void setQueryExamine(Integer queryExamine) {
		this.queryExamine = queryExamine;
	}

	public String getQueryVinNumber() {
		return queryVinNumber;
	}

	public void setQueryVinNumber(String queryVinNumber) {
		this.queryVinNumber = queryVinNumber;
	}

	public Integer getInInstorehouse() {
		return inInstorehouse;
	}

	public void setInInstorehouse(Integer inInstorehouse) {
		this.inInstorehouse = inInstorehouse;
	}

	public Integer getOutInstorehouse() {
		return outInstorehouse;
	}

	public void setOutInstorehouse(Integer outInstorehouse) {
		this.outInstorehouse = outInstorehouse;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public Integer getInstorehouseType() {
		return instorehouseType;
	}

	public void setInstorehouseType(Integer instorehouseType) {
		this.instorehouseType = instorehouseType;
	}

	public Integer getInstorehouseId() {
		return instorehouseId;
	}

	public void setInstorehouseId(Integer instorehouseId) {
		this.instorehouseId = instorehouseId;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	public Double getSumTax() {
		return sumTax;
	}

	public void setSumTax(Double sumTax) {
		this.sumTax = sumTax;
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

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Integer getXsSupplierId() {
		return xsSupplierId;
	}

	public void setXsSupplierId(Integer xsSupplierId) {
		this.xsSupplierId = xsSupplierId;
	}
}
