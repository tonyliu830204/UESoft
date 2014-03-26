package com.syuesoft.sell.model;

public class XsDbProject implements java.io.Serializable {
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
	private Integer enterpriseId;

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
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
}