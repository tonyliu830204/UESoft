package com.syuesoft.sell.synthesis_assay.vo;

public class PurchaseandsaleDailysheetVo {

	private String state;
	private String iconCls;

	private String recordDate; // 统计日期
	private String carBrand;
	private String carBrandName;
	private String carModel;
	private String carModelName;
	private String topMonthNum; // 月初库存
	private String thisImput; // 本期入库
	private String thisOutImput; // 本期出库
	private String thisExitCar; // 本期退车
	private String thisExitPart; // 本期退厂
	private String surplus; // 结余
	private Integer enterprise_id;

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
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

	public String getTopMonthNum() {
		return topMonthNum;
	}

	public void setTopMonthNum(String topMonthNum) {
		this.topMonthNum = topMonthNum;
	}

	public String getThisImput() {
		return thisImput;
	}

	public void setThisImput(String thisImput) {
		this.thisImput = thisImput;
	}

	public String getThisOutImput() {
		return thisOutImput;
	}

	public void setThisOutImput(String thisOutImput) {
		this.thisOutImput = thisOutImput;
	}

	public String getThisExitCar() {
		return thisExitCar;
	}

	public void setThisExitCar(String thisExitCar) {
		this.thisExitCar = thisExitCar;
	}

	public String getThisExitPart() {
		return thisExitPart;
	}

	public void setThisExitPart(String thisExitPart) {
		this.thisExitPart = thisExitPart;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

}
