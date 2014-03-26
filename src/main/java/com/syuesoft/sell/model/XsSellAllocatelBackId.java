package com.syuesoft.sell.model;

import com.syuesoft.model.BaseBean;


public class XsSellAllocatelBackId extends BaseBean implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer backId;
	private Integer allocatelDetailId;
	public Integer getBackId() {
		return backId;
	}
	public void setBackId(Integer backId) {
		this.backId = backId;
	}
	public Integer getAllocatelDetailId() {
		return allocatelDetailId;
	}
	public void setAllocatelDetailId(Integer allocatelDetailId) {
		this.allocatelDetailId = allocatelDetailId;
	}
	public XsSellAllocatelBackId(){}
	public XsSellAllocatelBackId(Integer backId,Integer allocatelDetailId){
		this.backId=backId;
		this.allocatelDetailId=allocatelDetailId;
	}
	

}
