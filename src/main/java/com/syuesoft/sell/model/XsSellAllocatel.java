package com.syuesoft.sell.model;

import java.io.Serializable;
import java.util.Date;
import com.syuesoft.model.BaseBean;

public class XsSellAllocatel extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer allocateId;// 调拨单编号
	private String allocatecode;// 调拨单单号
	private String allocateDate;// 调拨日期
	private Integer xsDistributorId;// 分销商编号
	private Integer allocateType; // 调拨分类
	private Integer allocatePerson;// 经办人
	private String consignee;// 提货人\取货人
	private Double costprice;// 总成本
	private Double allocateAmount;// 调拨金额
	private Integer paymentState;// 付讫状态
	private String remark;
	private Integer warehouse;// 仓库
	private Integer examine;// 审核
	private Integer enterprise_id;

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public Integer getExamine() {
		return examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	public XsSellAllocatel() {
		super();
	}

	public Integer getAllocateId() {
		return allocateId;
	}

	public void setAllocateId(Integer allocateId) {
		this.allocateId = allocateId;
	}

	public String getAllocatecode() {
		return allocatecode;
	}

	public void setAllocatecode(String allocatecode) {
		this.allocatecode = allocatecode;
	}

	public String getAllocateDate() {
		return allocateDate;
	}

	public void setAllocateDate(String allocateDate) {
		this.allocateDate = allocateDate;
	}

	public Integer getXsDistributorId() {
		return xsDistributorId;
	}

	public void setXsDistributorId(Integer xsDistributorId) {
		this.xsDistributorId = xsDistributorId;
	}

	public Integer getAllocateType() {
		return allocateType;
	}

	public void setAllocateType(Integer allocateType) {
		this.allocateType = allocateType;
	}

	public Integer getAllocatePerson() {
		return allocatePerson;
	}

	public void setAllocatePerson(Integer allocatePerson) {
		this.allocatePerson = allocatePerson;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public Double getCostprice() {
		return costprice;
	}

	public void setCostprice(Double costprice) {
		this.costprice = costprice;
	}

	public Double getAllocateAmount() {
		return allocateAmount;
	}

	public void setAllocateAmount(Double allocateAmount) {
		this.allocateAmount = allocateAmount;
	}

	public Integer getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(Integer paymentState) {
		this.paymentState = paymentState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}