package com.syuesoft.sell.model;

import com.syuesoft.model.BaseBean;

public class XsRepay extends BaseBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer repayId;
	private String repayCode;
	private String repayName;
	private Integer repayDay;
	private String repayContent;
	private String repayRemark;
	private Integer enterpriseId;

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getRepayId() {
		return repayId;
	}

	public void setRepayId(Integer repayId) {
		this.repayId = repayId;
	}

	public String getRepayCode() {
		return repayCode;
	}

	public void setRepayCode(String repayCode) {
		this.repayCode = repayCode;
	}

	public String getRepayName() {
		return repayName;
	}

	public void setRepayName(String repayName) {
		this.repayName = repayName;
	}

	public Integer getRepayDay() {
		return repayDay;
	}

	public void setRepayDay(Integer repayDay) {
		this.repayDay = repayDay;
	}

	public String getRepayContent() {
		return repayContent;
	}

	public void setRepayContent(String repayContent) {
		this.repayContent = repayContent;
	}

	public String getRepayRemark() {
		return repayRemark;
	}

	public void setRepayRemark(String repayRemark) {
		this.repayRemark = repayRemark;
	}
}