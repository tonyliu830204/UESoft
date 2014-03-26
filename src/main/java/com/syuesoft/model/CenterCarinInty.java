package com.syuesoft.model;

/**
 * CenterCarinInty entity. @author MyEclipse Persistence Tools
 */

public class CenterCarinInty extends BaseBean {

	// Fields

	private CenterCarinIntyId id;

	// Constructors

	/** default constructor */
	public CenterCarinInty() {
	}

	/** full constructor */
	public CenterCarinInty(CenterCarinIntyId id) {
		this.id = id;
	}

	// Property accessors

	public CenterCarinIntyId getId() {
		return this.id;
	}

	public void setId(CenterCarinIntyId id) {
		this.id = id;
	}

}