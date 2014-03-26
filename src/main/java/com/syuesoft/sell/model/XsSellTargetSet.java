package com.syuesoft.sell.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.syuesoft.model.BaseBean;

public class XsSellTargetSet extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer brandId;
	private Timestamp data;
	private String brandType;
	private Integer sellNum;
	private Double sellMoney;
	private Double sellProfit;
	private Integer actualNum;
	private Integer stfId;
	private String remark;
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

}
