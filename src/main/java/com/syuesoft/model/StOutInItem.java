package com.syuesoft.model;

/**
 * StOutInItem entity. @author MyEclipse Persistence Tools
 */

public class StOutInItem implements java.io.Serializable {

	private StOutInItemId id;
	private StOutItem stOutItem;
	private StStorageItem stStorageItem;
	private double outNumber;

	/** default constructor */
	public StOutInItem() {
	}

	/** minimal constructor */
	public StOutInItem(StOutInItemId id, StOutItem stOutItem,
			StStorageItem stStorageItem) {
		this.id = id;
		this.stOutItem = stOutItem;
		this.stStorageItem = stStorageItem;
	}

	/** full constructor */
	public StOutInItem(StOutInItemId id, StOutItem stOutItem,
			StStorageItem stStorageItem, double outNumber) {
		this.id = id;
		this.stOutItem = stOutItem;
		this.stStorageItem = stStorageItem;
		this.outNumber = outNumber;
	}

	// Property accessors

	public StOutInItemId getId() {
		return this.id;
	}

	public void setId(StOutInItemId id) {
		this.id = id;
	}

	public StOutItem getStOutItem() {
		return this.stOutItem;
	}

	public void setStOutItem(StOutItem stOutItem) {
		this.stOutItem = stOutItem;
	}

	public StStorageItem getStStorageItem() {
		return this.stStorageItem;
	}

	public void setStStorageItem(StStorageItem stStorageItem) {
		this.stStorageItem = stStorageItem;
	}

	public double getOutNumber() {
		return this.outNumber;
	}

	public void setOutNumber(double outNumber) {
		this.outNumber = outNumber;
	}

}