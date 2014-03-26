package com.syuesoft.base.vo;

/**
 * BasCarBodysStatus entity. @author MyEclipse Persistence Tools
 */


public class BasCarBodysStatusVo implements java.io.Serializable {

	// Fields

	private String bodyNum;
	private String bodyId;
	private String bodyName;
	private String bodyRemark;
	private String sort;
	private String order;
    private int rows;

    private int page;
	// Constructors

	/** default constructor */
	public BasCarBodysStatusVo() {
	}

	/** minimal constructor */
	public BasCarBodysStatusVo(String bodyId) {
		this.bodyId = bodyId;
	}

	/** full constructor */
	public BasCarBodysStatusVo(String bodyId, String bodyName, String bodyRemark) {
		this.bodyId = bodyId;
		this.bodyName = bodyName;
		this.bodyRemark = bodyRemark;
	}
	
	// Property accessors

	public String getBodyNum() {
		return bodyNum;
	}

	public void setBodyNum(String bodyNum) {
		this.bodyNum = bodyNum;
	}

	public String getBodyId() {
		return this.bodyId;
	}

	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}

	public String getBodyName() {
		return this.bodyName;
	}

	public void setBodyName(String bodyName) {
		this.bodyName = bodyName;
	}

	public String getBodyRemark() {
		return this.bodyRemark;
	}

	public void setBodyRemark(String bodyRemark) {
		this.bodyRemark = bodyRemark;
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