package com.syuesoft.model;

/**
 * VTrackRecord entity. @author MyEclipse Persistence Tools
 */

public class VTrackRecord extends BaseBean {

	// Fields

	private VTrackRecordId id;

	// Constructors

	/** default constructor */
	public VTrackRecord() {
	}

	/** full constructor */
	public VTrackRecord(VTrackRecordId id) {
		this.id = id;
	}

	// Property accessors

	public VTrackRecordId getId() {
		return this.id;
	}

	public void setId(VTrackRecordId id) {
		this.id = id;
	}

}