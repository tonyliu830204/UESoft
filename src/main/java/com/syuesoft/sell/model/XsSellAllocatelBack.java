package com.syuesoft.sell.model;

public class XsSellAllocatelBack implements java.io.Serializable {
	private XsSellAllocatelBackId id;

	public XsSellAllocatelBackId getId() {
		return id;
	}

	public void setId(XsSellAllocatelBackId id) {
		this.id = id;
	}

	public XsSellAllocatelBack() {
	}
	
	public XsSellAllocatelBack( XsSellAllocatelBackId id) {
		this.id=id;
	}
}
