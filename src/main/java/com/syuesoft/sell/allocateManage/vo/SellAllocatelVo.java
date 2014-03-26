package com.syuesoft.sell.allocateManage.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.syuesoft.model.BaseBean;

/**
 * 调拨单Vo 
 * @author zhangBin
 * 
 */
public class SellAllocatelVo extends  BaseBean implements Serializable  {
	private Integer allocateId;// 调拨单编号
	private String allocatecode;// 调拨单单号
	private String allocateDate;// 调拨日期
	private String allocateDate2;// 调拨日期
	private Double allAmount;// 调拨金额
	private String carOcn;
	private String carSellStateName;//销售状态名称
	private String carProductionAddress;
	private Integer carDistributState;//分销状态
	private String carDistributStateName;//分销状态名称
	private String carCopyData;
	private String carMotorNumber;//发动机编号

	private Integer xsDistributorId;// 分销商编号
	private String xsDistributorName;// 分销商名称
	private Integer allocateType; // 调拨分类
	private String allocateTypeName; // 调拨分类名称
	private Integer allocatePerson;// 经办人
	private String allocatePersonName;// 经办人姓名
	private String consignee;// 提货人\取货人
	private Double costprice;// 调拨成本
	private Double allocateAmount;// 调拨金额
	private Integer paymentState;// 付讫状态
	private String paymentStateName;// 付讫状态
	private String remark;// 发票及备注
	private Integer detailsId;// 入库明细编号
	private Integer xsCarId;// 车辆档案编号
	private String xsCarVinNum;
	private Integer xs_sellAllocatelType;// 分销商调拨类型
	private Integer warehouse;// 仓库
	private Integer examine;//审核
	private String examineName;//审核
	private String instorehousedateGrid;
	private String detaildateGrid;// 明细表
	private String house;
	private String backCode;
	private String sort;
	private String order;
	private int rows;
	private int page;
	
	private String is_invoice;
	private String invoice;
	
	
	private Integer carId;
	private Integer carBrand;
	private String carBrandName;//车辆品牌
	private Integer carColor;
	private String carColorName;//车辆颜色名称
	private Integer carModel;
	private String carModelName;//车类型
	private String carCode;//车辆编号
	private String carLicenseName;//厂牌名称
	private String carVinNumber;
	private Double modelCostPrice;//成本价
	private Double totalMoney;//合计金额(入库表)
	
	private String inserted;
	private String deleted;
	private String updated;	
	private String distributorName;//分销商
	private Double modelSalesPrice;//销售价
	private Double carInstorageAge;//库龄
	private Double vehicleCost;
	private String state;
	private String iconCls;
	private String payment;
	 private Integer enterprise_id ;
	 private String times;
	 private Boolean flag;
	 
	 
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
	public Integer getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getIs_invoice() {
		return is_invoice;
	}

	public void setIs_invoice(String isInvoice) {
		is_invoice = isInvoice;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}


	
	public String getCarOcn() {
		return carOcn;
	}

	public void setCarOcn(String carOcn) {
		this.carOcn = carOcn;
	}

	public String getCarSellStateName() {
		return carSellStateName;
	}

	public void setCarSellStateName(String carSellStateName) {
		this.carSellStateName = carSellStateName;
	}

	public String getCarProductionAddress() {
		return carProductionAddress;
	}

	public void setCarProductionAddress(String carProductionAddress) {
		this.carProductionAddress = carProductionAddress;
	}

	public Integer getCarDistributState() {
		return carDistributState;
	}

	public void setCarDistributState(Integer carDistributState) {
		this.carDistributState = carDistributState;
	}

	public String getCarDistributStateName() {
		return carDistributStateName;
	}

	public void setCarDistributStateName(String carDistributStateName) {
		this.carDistributStateName = carDistributStateName;
	}

	public String getCarCopyData() {
		return carCopyData;
	}

	public void setCarCopyData(String carCopyData) {
		this.carCopyData = carCopyData;
	}

	public String getCarMotorNumber() {
		return carMotorNumber;
	}

	public void setCarMotorNumber(String carMotorNumber) {
		this.carMotorNumber = carMotorNumber;
	}

	public Double getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}


	public Double getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
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

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
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

	public Integer getExamine() {
		return examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	private Integer num;
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	public Integer getCarModel() {
		return carModel;
	}

	public void setCarModel(Integer carModel) {
		this.carModel = carModel;
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

	public Double getModelCostPrice() {
		return modelCostPrice;
	}

	public void setModelCostPrice(Double modelCostPrice) {
		this.modelCostPrice = modelCostPrice;
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




	public String getAllocateDate2() {
		return allocateDate2;
	}

	public void setAllocateDate2(String allocateDate2) {
		this.allocateDate2 = allocateDate2;
	}

	public void setAllocateDate(String string) {
		this.allocateDate = string;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	

	public Integer getXs_sellAllocatelType() {
		return xs_sellAllocatelType;
	}

	public void setXs_sellAllocatelType(Integer xsSellAllocatelType) {
		xs_sellAllocatelType = xsSellAllocatelType;
	}

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public Integer getXsCarId() {
		return xsCarId;
	}

	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}

	public String getXsCarVinNum() {
		return xsCarVinNum;
	}

	public void setXsCarVinNum(String xsCarVinNum) {
		this.xsCarVinNum = xsCarVinNum;
	}

	public String getXsDistributorName() {
		return xsDistributorName;
	}

	public void setXsDistributorName(String xsDistributorName) {
		this.xsDistributorName = xsDistributorName;
	}

	public String getAllocateTypeName() {
		return allocateTypeName;
	}

	public void setAllocateTypeName(String allocateTypeName) {
		this.allocateTypeName = allocateTypeName;
	}

	public String getAllocatePersonName() {
		return allocatePersonName;
	}

	public void setAllocatePersonName(String allocatePersonName) {
		this.allocatePersonName = allocatePersonName;
	}

	public String getPaymentStateName() {
		return paymentStateName;
	}

	public void setPaymentStateName(String paymentStateName) {
		this.paymentStateName = paymentStateName;
	}

	public Integer getAllocateId() {
		return allocateId;
	}

	public void setAllocateId(Integer allocateId) {
		this.allocateId = allocateId;
	}

	public String getAllocatecode() {
		return allocatecode;
	}

	public void setAllocatecode(String allocatecode) {
		this.allocatecode = allocatecode;
	}




	public String getAllocateDate() {
		return allocateDate;
	}

	public Integer getXsDistributorId() {
		return xsDistributorId;
	}

	public void setXsDistributorId(Integer xsDistributorId) {
		this.xsDistributorId = xsDistributorId;
	}

	public Integer getAllocateType() {
		return allocateType;
	}

	public void setAllocateType(Integer allocateType) {
		this.allocateType = allocateType;
	}

	public Integer getAllocatePerson() {
		return allocatePerson;
	}

	public void setAllocatePerson(Integer allocatePerson) {
		this.allocatePerson = allocatePerson;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public Double getCostprice() {
		return costprice;
	}

	public void setCostprice(Double costprice) {
		this.costprice = costprice;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
