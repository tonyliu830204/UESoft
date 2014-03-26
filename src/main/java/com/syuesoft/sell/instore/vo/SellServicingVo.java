package com.syuesoft.sell.instore.vo;

import java.io.Serializable;

public class SellServicingVo implements Serializable {
	private Integer xsCarId; // 车辆档案id
	private String xsCarVinNumber; // VIN编号
	private String xsCarMotorNumber;
	private String xsCarOcn;
	private Integer xsCarModelId; // 车辆型号id
	private String xsModelName; // 车辆型号名称
	private Integer xsbrandId;
	private String brandName;
	private Integer xscolorId;
	private String colorName;
	private Integer xsCarSellState; // 销售状态
	private String carSellStateName; // 销售状态名称
	private String xsCarCode; // 车辆编号
	private Integer servicingId; // 维护序号
	private Integer detailsId; // 入库明细
	private Integer servicingProject; // 维护项目
	private String servicingProjectName; // 维护内容
	private Integer servicingPerson; // 维护人
	private String servicingDate; // 维护日期
	private String servicingNextdate; // 预计下次维护日期
	private String servicingNextdate2; // 预计下次维护日期
	private String servicingRemark; // 维护结果
	private String xsCarModelName; // 车辆型号名称
	private String instorehouseDate; // 入库日期
	// 分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	private Integer enterprise_id;
	private String times;
	
	public String getServicingNextdate2() {
		return servicingNextdate2;
	}

	public void setServicingNextdate2(String servicingNextdate2) {
		this.servicingNextdate2 = servicingNextdate2;
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

	public String getXsCarOcn() {
		return xsCarOcn;
	}

	public void setXsCarOcn(String xsCarOcn) {
		this.xsCarOcn = xsCarOcn;
	}

	public String getXsCarMotorNumber() {
		return xsCarMotorNumber;
	}

	public void setXsCarMotorNumber(String xsCarMotorNumber) {
		this.xsCarMotorNumber = xsCarMotorNumber;
	}

	public Integer getXscolorId() {
		return xscolorId;
	}

	public void setXscolorId(Integer xscolorId) {
		this.xscolorId = xscolorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Integer getXsbrandId() {
		return xsbrandId;
	}

	public void setXsbrandId(Integer xsbrandId) {
		this.xsbrandId = xsbrandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getXsCarId() {
		return xsCarId;
	}

	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}

	public String getXsCarVinNumber() {
		return xsCarVinNumber;
	}

	public void setXsCarVinNumber(String xsCarVinNumber) {
		this.xsCarVinNumber = xsCarVinNumber;
	}

	public Integer getXsCarModelId() {
		return xsCarModelId;
	}

	public void setXsCarModelId(Integer xsCarModelId) {
		this.xsCarModelId = xsCarModelId;
	}

	public String getXsModelName() {
		return xsModelName;
	}

	public void setXsModelName(String xsModelName) {
		this.xsModelName = xsModelName;
	}

	public Integer getXsCarSellState() {
		return xsCarSellState;
	}

	public void setXsCarSellState(Integer xsCarSellState) {
		this.xsCarSellState = xsCarSellState;
	}

	public String getCarSellStateName() {
		return carSellStateName;
	}

	public void setCarSellStateName(String carSellStateName) {
		this.carSellStateName = carSellStateName;
	}

	public String getXsCarCode() {
		return xsCarCode;
	}

	public void setXsCarCode(String xsCarCode) {
		this.xsCarCode = xsCarCode;
	}

	public Integer getServicingId() {
		return servicingId;
	}

	public void setServicingId(Integer servicingId) {
		this.servicingId = servicingId;
	}

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public Integer getServicingProject() {
		return servicingProject;
	}

	public void setServicingProject(Integer servicingProject) {
		this.servicingProject = servicingProject;
	}

	public Integer getServicingPerson() {
		return servicingPerson;
	}

	public void setServicingPerson(Integer servicingPerson) {
		this.servicingPerson = servicingPerson;
	}

	public String getServicingDate() {
		return servicingDate;
	}

	public void setServicingDate(String servicingDate) {
		this.servicingDate = servicingDate;
	}

	public String getServicingNextdate() {
		return servicingNextdate;
	}

	public void setServicingNextdate(String servicingNextdate) {
		this.servicingNextdate = servicingNextdate;
	}

	public String getServicingRemark() {
		return servicingRemark;
	}

	public void setServicingRemark(String servicingRemark) {
		this.servicingRemark = servicingRemark;
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

	public String getServicingProjectName() {
		return servicingProjectName;
	}

	public void setServicingProjectName(String servicingProjectName) {
		this.servicingProjectName = servicingProjectName;
	}

	public String getXsCarModelName() {
		return xsCarModelName;
	}

	public void setXsCarModelName(String xsCarModelName) {
		this.xsCarModelName = xsCarModelName;
	}

	public String getInstorehouseDate() {
		return instorehouseDate;
	}

	public void setInstorehouseDate(String instorehouseDate) {
		this.instorehouseDate = instorehouseDate;
	}

}
