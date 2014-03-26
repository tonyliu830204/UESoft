package com.syuesoft.model;

/**
 * BasStuffJob entity. @author MyEclipse Persistence Tools
 */

public class BasStuffJob extends BaseBean{

	// Fields

	private BasStuffJobId id;

	// Constructors

	/** default constructor */
	public BasStuffJob() {
	}

	/** full constructor */
	public BasStuffJob(BasStuffJobId id) {
		this.id = id;
	}

	// Property accessors

	public BasStuffJobId getId() {
		return this.id;
	}

	public void setId(BasStuffJobId id) {
		this.id = id;
	}

}