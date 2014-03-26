package com.syuesoft.sell.model;

public class XsCustomLeva implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer levaId;
	private String levaCode;
	private String levaName;
	private Integer jianGe;
	private String remark;
	
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
}