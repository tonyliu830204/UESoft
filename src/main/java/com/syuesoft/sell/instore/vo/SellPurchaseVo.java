package com.syuesoft.sell.instore.vo;

import java.io.Serializable;
import java.util.Date;

public class SellPurchaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String purchaseId;
	private Integer carBrand;
	private Integer carModelId;
	private Integer carColor;
	private Integer planNumber;
	private Integer actualNumber;
	private String planPercent;
	private String remark;
	private Date sellDate;

	private String brandName;
	private String modelName;
	private String colorName;
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	// 查询所用字段
	private String queryPlanDate;// 计划月份
	private Integer queryBrand;// 车辆品牌
	private Integer queryModel;// 车辆型号
	private Integer queryColor;// 颜色
	private Integer enterprise_id;
	private String times;
	private String queryPlanDate2;// 计划月份
	
	public String getQueryPlanDate2() {
		return queryPlanDate2;
	}

	public void setQueryPlanDate2(String queryPlanDate2) {
		this.queryPlanDate2 = queryPlanDate2;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Integer getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}

	public Integer getCarColor() {
		return carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public Integer getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(Integer planNumber) {
		this.planNumber = planNumber;
	}

	public Integer getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(Integer actualNumber) {
		this.actualNumber = actualNumber;
	}

	public String getPlanPercent() {
		return planPercent;
	}

	public void setPlanPercent(String planPercent) {
		this.planPercent = planPercent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
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

	public String getQueryPlanDate() {
		return queryPlanDate;
	}

	public void setQueryPlanDate(String queryPlanDate) {
		this.queryPlanDate = queryPlanDate;
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

}
