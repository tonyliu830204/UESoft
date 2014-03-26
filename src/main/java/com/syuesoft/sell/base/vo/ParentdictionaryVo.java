package com.syuesoft.sell.base.vo;

// default package

import java.sql.Timestamp;

/**
 * BasParentdictionary entity. @author MyEclipse Persistence Tools
 */

public class ParentdictionaryVo implements java.io.Serializable {

	// Fields

	private Integer parentId;
	private Long stfId;
	private String stfName;
	private Timestamp createTime;
	private String dataKey;
	private String dataValue;
	private String remark;
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	// 查询用到的字段
	private String pqueryKey;
	private String pqueryValue;
	private Integer enterpriseId;

	

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public String getPqueryKey() {
		return pqueryKey;
	}

	public void setPqueryKey(String pqueryKey) {
		this.pqueryKey = pqueryKey;
	}

	public String getPqueryValue() {
		return pqueryValue;
	}

	public void setPqueryValue(String pqueryValue) {
		this.pqueryValue = pqueryValue;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Long getStfId() {
		return this.stfId;
	}

	public void setStfId(Long stfId) {
		this.stfId = stfId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDataKey() {
		return this.dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public String getDataValue() {
		return this.dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
}