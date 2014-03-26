package com.syuesoft.sell.base.vo;

import com.syuesoft.model.BaseBean;

public class DbProjectVo extends BaseBean implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer projectId;
	private String projectCode;
	private String projectName;
	private Double projectMomay;
	private Double projectAmount;
	private String projectDept;
	private String projectRemark;
	//以下为分页字段
    private String sort;
 	private String order;
 	private int rows;
 	private int page;
 	//查询所用字段
 	private  String proCode;
 	private String proName;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

 	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
	public Double getProjectMomay() {
		return projectMomay;
	}
	public void setProjectMomay(Double projectMomay) {
		this.projectMomay = projectMomay;
	}
	public Double getProjectAmount() {
		return projectAmount;
	}
	public void setProjectAmount(Double projectAmount) {
		this.projectAmount = projectAmount;
	}

	public String getProjectDept() {
		return projectDept;
	}
	public void setProjectDept(String projectDept) {
		this.projectDept = projectDept;
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

}