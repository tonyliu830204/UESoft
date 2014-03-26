package com.syuesoft.base.vo;

/**
 * BasCarPhonetrackerresult entity. @author MyEclipse Persistence Tools
 */

public class BasCarPhonetrackerresultVo implements java.io.Serializable {

	// Fields

	private Short carPhonetrackerresultId;
	private String carPhonetrackerresultName;
	private String carPhonetrackerresultRemark;
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
    public BasCarPhonetrackerresultVo()
    {
    }

	/** minimal constructor */
	public BasCarPhonetrackerresultVo(Short carPhonetrackerresultId) {
		this.carPhonetrackerresultId = carPhonetrackerresultId;
	}

	/** full constructor */
	public BasCarPhonetrackerresultVo(Short carPhonetrackerresultId,
			String carPhonetrackerresultName, String carPhonetrackerresultRemark) {
		this.carPhonetrackerresultId = carPhonetrackerresultId;
		this.carPhonetrackerresultName = carPhonetrackerresultName;
		this.carPhonetrackerresultRemark = carPhonetrackerresultRemark;
	}

	// Property accessors

	public Short getCarPhonetrackerresultId() {
		return this.carPhonetrackerresultId;
	}

	public void setCarPhonetrackerresultId(Short carPhonetrackerresultId) {
		this.carPhonetrackerresultId = carPhonetrackerresultId;
	}

	public String getCarPhonetrackerresultName() {
		return this.carPhonetrackerresultName;
	}

	public void setCarPhonetrackerresultName(String carPhonetrackerresultName) {
		this.carPhonetrackerresultName = carPhonetrackerresultName;
	}

	public String getCarPhonetrackerresultRemark() {
		return this.carPhonetrackerresultRemark;
	}

	public void setCarPhonetrackerresultRemark(
			String carPhonetrackerresultRemark) {
		this.carPhonetrackerresultRemark = carPhonetrackerresultRemark;
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