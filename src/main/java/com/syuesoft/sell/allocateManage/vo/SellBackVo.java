package com.syuesoft.sell.allocateManage.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.syuesoft.model.BaseBean;

/**
 * 调退单Vo
 * 
 * 
 */
public class SellBackVo extends BaseBean implements Serializable {

	private Integer backId;
	private String backCode;
	private Timestamp backDate;
	private Timestamp backDate2;

	private Integer xsDistributorId;
	private String xsDistributorName;// 分销商姓名
	private Integer backType;// 调退分类
	private String backTypeName;// 调退分类

	private Integer backPerson;// 经办人
	private String backPersonName;// 经办人姓名
	private Double handbackAllocateAmount;// 调退金额
	private Double allAmount;// 调退金额
	private Integer balanceState;// 对账状态
	private String remark;
	private Integer allocateId;// 调拨单编号
	private Integer number;
	private String instorehousedateGrid;
	private String detaildateGrid;// 明细表
	private Integer warehouse;
	private String warehouseN;
	private Integer detailsId;// 明细编号
	private Integer carId;
	private Integer carBrand;
	private String carBrandName;// 车辆品牌
	private Integer carColor;
	private String carColorName;// 车辆颜色名称
	private Integer carModel;
	private String carModelName;// 车类型
	private String carCode;// 车辆编号
	private String carLicenseName;// 厂牌名称
	private String carVinNumber;
	private String carOcn;
	private Double modelCostPrice;// 成本价
	private Double totalMoney;// 合计金额(入库表)
	private Integer num;// 数量
	private Integer examine;// 审核
	private String examineName;// 审核
	private Integer allocatel_detail_id;
	private Integer enterprise_id;
	private Boolean flag;

	private String inserted;
	private String deleted;
	private String updated;
	private String state;
	private String iconCls;
	private String balanceStateN;// 对账状态
	private String sort;
	private String order;
	private int rows;
	private int page;
	
	public String getCarOcn() {
		return carOcn;
	}

	public void setCarOcn(String carOcn) {
		this.carOcn = carOcn;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public Integer getAllocatel_detail_id() {
		return allocatel_detail_id;
	}

	public void setAllocatel_detail_id(Integer allocatelDetailId) {
		allocatel_detail_id = allocatelDetailId;
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

	public Double getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
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

	public void setBackDate(Timestamp backDate) {
		this.backDate = backDate;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}

	

	public String getBalanceStateN() {
		return balanceStateN;
	}

	public void setBalanceStateN(String balanceStateN) {
		this.balanceStateN = balanceStateN;
	}

	public String getWarehouseN() {
		return warehouseN;
	}

	public void setWarehouseN(String warehouseN) {
		this.warehouseN = warehouseN;
	}

	public Timestamp getBackDate2() {
		return backDate2;
	}

	public void setBackDate2(Timestamp backDate2) {
		this.backDate2 = backDate2;
	}

	public Integer getExamine() {
		return examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
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

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getAllocateId() {
		return allocateId;
	}

	public void setAllocateId(Integer allocateId) {
		this.allocateId = allocateId;
	}

	public Integer getBackId() {
		return backId;
	}

	public void setBackId(Integer backId) {
		this.backId = backId;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public Date getBackDate() {
		return backDate;
	}

	public Integer getXsDistributorId() {
		return xsDistributorId;
	}

	public void setXsDistributorId(Integer xsDistributorId) {
		this.xsDistributorId = xsDistributorId;
	}

	public Integer getBackType() {
		return backType;
	}

	public void setBackType(Integer backType) {
		this.backType = backType;
	}

	public Integer getBackPerson() {
		return backPerson;
	}

	public void setBackPerson(Integer backPerson) {
		this.backPerson = backPerson;
	}

	public Double getHandbackAllocateAmount() {
		return handbackAllocateAmount;
	}

	public void setHandbackAllocateAmount(Double handbackAllocateAmount) {
		this.handbackAllocateAmount = handbackAllocateAmount;
	}

	public Integer getBalanceState() {
		return balanceState;
	}

	public void setBalanceState(Integer balanceState) {
		this.balanceState = balanceState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getXsDistributorName() {
		return xsDistributorName;
	}

	public void setXsDistributorName(String xsDistributorName) {
		this.xsDistributorName = xsDistributorName;
	}

	public String getBackPersonName() {
		return backPersonName;
	}

	public void setBackPersonName(String backPersonName) {
		this.backPersonName = backPersonName;
	}

	public String getBackTypeName() {
		return backTypeName;
	}

	public void setBackTypeName(String backTypeName) {
		this.backTypeName = backTypeName;
	}

}
