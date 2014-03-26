package com.syuesoft.model;

/**
 * FbkDailyclientTracking entity. @author MyEclipse Persistence Tools
 */

public class FbkDailyclientTracking extends BaseBean {

	// Fields

	private Integer drId;
	private String txInfomation;
	private String memoInfomation;
	private String txDj;
	private String clStatus;
	private String carLicense;
	private String customName;

	// Constructors

	/** default constructor */
	public FbkDailyclientTracking() {
	}

	/** full constructor */
	public FbkDailyclientTracking(String txInfomation, String memoInfomation,
			String txDj, String clStatus, String carLicense, String customName) {
		this.txInfomation = txInfomation;
		this.memoInfomation = memoInfomation;
		this.txDj = txDj;
		this.clStatus = clStatus;
		this.carLicense = carLicense;
		this.customName = customName;
	}

	// Property accessors

	public Integer getDrId() {
		return this.drId;
	}

	public void setDrId(Integer drId) {
		this.drId = drId;
	}

	public String getTxInfomation() {
		return this.txInfomation;
	}

	public void setTxInfomation(String txInfomation) {
		this.txInfomation = txInfomation;
	}

	public String getMemoInfomation() {
		return this.memoInfomation;
	}

	public void setMemoInfomation(String memoInfomation) {
		this.memoInfomation = memoInfomation;
	}

	public String getTxDj() {
		return this.txDj;
	}

	public void setTxDj(String txDj) {
		this.txDj = txDj;
	}

	public String getClStatus() {
		return this.clStatus;
	}

	public void setClStatus(String clStatus) {
		this.clStatus = clStatus;
	}

	public String getCarLicense() {
		return this.carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

}