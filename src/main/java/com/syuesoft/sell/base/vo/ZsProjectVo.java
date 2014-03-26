package com.syuesoft.sell.base.vo;

public class ZsProjectVo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer zsprojectId;
	private String projectCode;
	private String projectName;
	private Double projectCostamount;
	private Double projectSellamount;
	private String projectRemark;
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	// 查询所用字段  
	private String zsProCode;
	private String zsProName;
	private Integer enterpriseId;

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getZsprojectId() {
		return zsprojectId;
	}

	public void setZsprojectId(Integer zsprojectId) {
		this.zsprojectId = zsprojectId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getProjectCostamount() {
		return projectCostamount;
	}

	public void setProjectCostamount(Double projectCostamount) {
		this.projectCostamount = projectCostamount;
	}

	public Double getProjectSellamount() {
		return projectSellamount;
	}

	public void setProjectSellamount(Double projectSellamount) {
		this.projectSellamount = projectSellamount;
	}

	public String getProjectRemark() {
		return projectRemark;
	}

	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
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

	public String getZsProCode() {
		return zsProCode;
	}

	public void setZsProCode(String zsProCode) {
		this.zsProCode = zsProCode;
	}

	public String getZsProName() {
		return zsProName;
	}

	public void setZsProName(String zsProName) {
		this.zsProName = zsProName;
	}

}