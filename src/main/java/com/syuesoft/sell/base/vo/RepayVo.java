package com.syuesoft.sell.base.vo;

public class RepayVo implements java.io.Serializable{
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
	//以下为分页字段
    private String sort;
 	private String order;
 	private int rows;
 	private int page;
 	//查询所用字段
 	private String repayC;
 	private String repayN;
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
	public String getRepayC() {
		return repayC;
	}
	public void setRepayC(String repayC) {
		this.repayC = repayC;
	}
	public String getRepayN() {
		return repayN;
	}
	public void setRepayN(String repayN) {
		this.repayN = repayN;
	}
}