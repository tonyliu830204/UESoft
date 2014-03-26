package com.syuesoft.base.vo;

/**
 * BasCarStatus entity. @author MyEclipse Persistence Tools
 */


public class BasCarStatusVo implements java.io.Serializable {

	// Fields

	private Short statusId;
	private String statusName;
	private String remark;
	private String sort;
	private String order;
	private int rows;
	private int page;
	 private Integer enterpriseId;

     public Integer getEnterpriseId() {
 		return enterpriseId;
 	}

 	public void setEnterpriseId(Integer enterpriseId) {
 		this.enterpriseId = enterpriseId;
 	}
	// Constructors

	/** default constructor */
    public BasCarStatusVo()
    {
    }

	public BasCarStatusVo(String statusReason, String statusName, String remark) {
		this.statusName = statusName;
		this.remark = remark;
	}

	// Property accessors

	public Short getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Short statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRemark() {
		return this.remark;
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

}