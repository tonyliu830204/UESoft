package com.syuesoft.model;

import java.util.Date;

/**
 * FbkTxGroup entity. @author MyEclipse Persistence Tools
 */

public class FbkTxGroup implements java.io.Serializable {

	// Fields

	private Integer GId;
	private String groupName;
	private Date returnVisitDate;
	private Date txReturnVisitDate;
	private String visitContent;
	private String txResault;
	private String statusName;
	private String returnVisitResault;
	private Integer txStatus;
	private String carLost;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }

	// Constructors

	/** default constructor */
	public FbkTxGroup() {
	}

	/** full constructor */
	public FbkTxGroup(String groupName, Date returnVisitDate,
			Date txReturnVisitDate, String visitContent, String txResault,
			String statusName, String returnVisitResault, Integer txStatus,
			String carLost) {
		this.groupName = groupName;
		this.returnVisitDate = returnVisitDate;
		this.txReturnVisitDate = txReturnVisitDate;
		this.visitContent = visitContent;
		this.txResault = txResault;
		this.statusName = statusName;
		this.returnVisitResault = returnVisitResault;
		this.txStatus = txStatus;
		this.carLost = carLost;
	}

	// Property accessors

	public Integer getGId() {
		return this.GId;
	}

	public void setGId(Integer GId) {
		this.GId = GId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getReturnVisitDate() {
		return this.returnVisitDate;
	}

	public void setReturnVisitDate(Date returnVisitDate) {
		this.returnVisitDate = returnVisitDate;
	}

	public Date getTxReturnVisitDate() {
		return this.txReturnVisitDate;
	}

	public void setTxReturnVisitDate(Date txReturnVisitDate) {
		this.txReturnVisitDate = txReturnVisitDate;
	}

	public String getVisitContent() {
		return this.visitContent;
	}

	public void setVisitContent(String visitContent) {
		this.visitContent = visitContent;
	}

	public String getTxResault() {
		return this.txResault;
	}

	public void setTxResault(String txResault) {
		this.txResault = txResault;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getReturnVisitResault() {
		return this.returnVisitResault;
	}

	public void setReturnVisitResault(String returnVisitResault) {
		this.returnVisitResault = returnVisitResault;
	}

	public Integer getTxStatus() {
		return this.txStatus;
	}

	public void setTxStatus(Integer txStatus) {
		this.txStatus = txStatus;
	}

	public String getCarLost() {
		return this.carLost;
	}

	public void setCarLost(String carLost) {
		this.carLost = carLost;
	}

}