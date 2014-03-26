package com.syuesoft.sell.model;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellReceipt  extends  BaseBean implements  Serializable{
	
	private Integer receiptId;
	private String receiptCode;
	private Integer receiptBank;
	private Double receiptMoney;
	private Date receiptStartDate;
	private Date receiptEndDate;
	private Integer receiptState;
	private String remark;
	private Integer enterpriseId;

	
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	public String getReceiptCode() {
		return receiptCode;
	}
	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}

	public Integer getReceiptBank() {
		return receiptBank;
	}
	public void setReceiptBank(Integer receiptBank) {
		this.receiptBank = receiptBank;
	}
	public Double getReceiptMoney() {
		return receiptMoney;
	}
	public void setReceiptMoney(Double receiptMoney) {
		this.receiptMoney = receiptMoney;
	}
	public Date getReceiptStartDate() {
		return receiptStartDate;
	}
	public void setReceiptStartDate(Date receiptStartDate) {
		this.receiptStartDate = receiptStartDate;
	}
	public Date getReceiptEndDate() {
		return receiptEndDate;
	}
	public void setReceiptEndDate(Date receiptEndDate) {
		this.receiptEndDate = receiptEndDate;
	}
	public Integer getReceiptState() {
		return receiptState;
	}
	public void setReceiptState(Integer receiptState) {
		this.receiptState = receiptState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
