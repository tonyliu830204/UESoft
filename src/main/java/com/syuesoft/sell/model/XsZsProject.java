package com.syuesoft.sell.model;

public class XsZsProject implements java.io.Serializable {
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
}