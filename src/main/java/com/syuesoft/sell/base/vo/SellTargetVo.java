package com.syuesoft.sell.base.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class SellTargetVo implements Serializable {
	private Integer brandId;
	private Timestamp data;
	private String brandType;
	private Integer sellNum;
	private Double sellMoney;
	private Double sellProfit;
	private Integer actualNum;
	private Integer stfId;
	private String remark;

	private String brandName;
	private String stfName;
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	private Integer enterprise_id;

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public Integer getSellNum() {
		return sellNum;
	}

	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	public Double getSellMoney() {
		return sellMoney;
	}

	public void setSellMoney(Double sellMoney) {
		this.sellMoney = sellMoney;
	}

	public Double getSellProfit() {
		return sellProfit;
	}

	public void setSellProfit(Double sellProfit) {
		this.sellProfit = sellProfit;
	}

	public Integer getActualNum() {
		return actualNum;
	}

	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}

	public Integer getStfId() {
		return stfId;
	}

	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

}
