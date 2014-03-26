package com.syuesoft.model;

/**
 * BasVistContent entity. @author MyEclipse Persistence Tools
 */

public class BasVistContent extends BaseBean {

	// Fields

	private Integer vistId;
	private String vistName;
	private String returnType;
	private String carLicense;
	private String memo;

	// Constructors

	/** default constructor */
	public BasVistContent() {
	}

	/** full constructor */
	public BasVistContent(String vistName, String returnType,
			String carLicense, String memo) {
		this.vistName = vistName;
		this.returnType = returnType;
		this.carLicense = carLicense;
		this.memo = memo;
	}

	// Property accessors

	public Integer getVistId() {
		return this.vistId;
	}

	public void setVistId(Integer vistId) {
		this.vistId = vistId;
	}

	public String getVistName() {
		return this.vistName;
	}

	public void setVistName(String vistName) {
		this.vistName = vistName;
	}

	public String getReturnType() {
		return this.returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getCarLicense() {
		return this.carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}