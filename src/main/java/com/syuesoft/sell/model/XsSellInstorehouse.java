package com.syuesoft.sell.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellInstorehouse extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer instorehouseId;
	private String instorehouseCode;
	private Timestamp instorehouseDate;
	private Integer invoiceType;
	private Long stfId;
	private Integer warehouse;
	private Integer purchaser;
	private String receipt;
	private Integer state1;
	private Integer number;
	private Double notax;
	private Double tax;
	private String transportVehicles;
	private Integer examineState;
	private String remark;
	private Integer supplierId;
	private Integer source;// 库存来源
	private Integer enterprise_id;

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public Integer getInstorehouseId() {
		return instorehouseId;
	}

	public void setInstorehouseId(Integer instorehouseId) {
		this.instorehouseId = instorehouseId;
	}

	public String getInstorehouseCode() {
		return instorehouseCode;
	}

	public void setInstorehouseCode(String instorehouseCode) {
		this.instorehouseCode = instorehouseCode;
	}

	public Timestamp getInstorehouseDate() {
		return instorehouseDate;
	}

	public void setInstorehouseDate(Timestamp instorehouseDate) {
		this.instorehouseDate = instorehouseDate;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Long getStfId() {
		return stfId;
	}

	public void setStfId(Long stfId) {
		this.stfId = stfId;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	public Integer getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(Integer purchaser) {
		this.purchaser = purchaser;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Integer getState1() {
		return state1;
	}

	public void setState1(Integer state1) {
		this.state1 = state1;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getNotax() {
		return notax;
	}

	public void setNotax(Double notax) {
		this.notax = notax;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getTransportVehicles() {
		return transportVehicles;
	}

	public void setTransportVehicles(String transportVehicles) {
		this.transportVehicles = transportVehicles;
	}

	public Integer getExamineState() {
		return examineState;
	}

	public void setExamineState(Integer examineState) {
		this.examineState = examineState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

}
