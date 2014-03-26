package com.syuesoft.sell.base.vo;

public class CustomLevaVo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer levaId;
	private String levaCode;
	private String levaName;
	private Integer jianGe;
	private String remark;
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	// 查询所用字段
	private String levaC;
	private String levaN;
	private String q;
	private Integer enterpriseId;

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getLevaId() {
		return levaId;
	}

	public void setLevaId(Integer levaId) {
		this.levaId = levaId;
	}

	public String getLevaCode() {
		return levaCode;
	}

	public void setLevaCode(String levaCode) {
		this.levaCode = levaCode;
	}

	public String getLevaName() {
		return levaName;
	}

	public void setLevaName(String levaName) {
		this.levaName = levaName;
	}

	public Integer getJianGe() {
		return jianGe;
	}

	public void setJianGe(Integer jianGe) {
		this.jianGe = jianGe;
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

	public String getLevaC() {
		return levaC;
	}

	public void setLevaC(String levaC) {
		this.levaC = levaC;
	}

	public String getLevaN() {
		return levaN;
	}

	public void setLevaN(String levaN) {
		this.levaN = levaN;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

}