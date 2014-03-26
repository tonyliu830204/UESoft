package com.syuesoft.st.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class BasPartsBrandVo implements Serializable{

	private Short pbrdId;
	private String pbrdName;
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
	public Short getPbrdId() {
		return pbrdId;
	}

	public void setPbrdId(Short pbrdId) {
		this.pbrdId = pbrdId;
	}

	public String getPbrdName() {
		return pbrdName;
	}

	public void setPbrdName(String pbrdName) {
		this.pbrdName = pbrdName;
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

}
