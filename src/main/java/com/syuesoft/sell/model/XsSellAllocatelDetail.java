package com.syuesoft.sell.model;

import com.syuesoft.model.BaseBean;

public class XsSellAllocatelDetail extends BaseBean implements java.io.Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer allocatelDetailId;
private Integer detailsId; 
private Integer allocateId;
private Double allocateAmount;//调拨金额
public Integer getAllocatelDetailId() {
	return allocatelDetailId;
}
public void setAllocatelDetailId(Integer allocatelDetailId) {
	this.allocatelDetailId = allocatelDetailId;
}
public Integer getDetailsId() {
	return detailsId;
}
public void setDetailsId(Integer detailsId) {
	this.detailsId = detailsId;
}
public Integer getAllocateId() {
	return allocateId;
}
public void setAllocateId(Integer allocateId) {
	this.allocateId = allocateId;
}
public Double getAllocateAmount() {
	return allocateAmount;
}
public void setAllocateAmount(Double allocateAmount) {
	this.allocateAmount = allocateAmount;
} 

}
