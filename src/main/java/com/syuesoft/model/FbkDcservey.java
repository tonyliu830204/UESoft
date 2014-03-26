package com.syuesoft.model;

/**
 * FbkDcservey entity. @author MyEclipse Persistence Tools
 */

public class FbkDcservey extends BaseBean {

	// Fields

	private Integer dcId;
	private FrtCar frtCar;
	private FbkDcserveyName fbkDcserveyName;

	// Constructors

	/** default constructor */
	public FbkDcservey() {
	}

	/** full constructor */
	public FbkDcservey(FrtCar frtCar, FbkDcserveyName fbkDcserveyName) {
		this.frtCar = frtCar;
		this.fbkDcserveyName = fbkDcserveyName;
	}

	// Property accessors

	public Integer getDcId() {
		return this.dcId;
	}

	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}

	public FrtCar getFrtCar() {
		return this.frtCar;
	}

	public void setFrtCar(FrtCar frtCar) {
		this.frtCar = frtCar;
	}

	public FbkDcserveyName getFbkDcserveyName() {
		return this.fbkDcserveyName;
	}

	public void setFbkDcserveyName(FbkDcserveyName fbkDcserveyName) {
		this.fbkDcserveyName = fbkDcserveyName;
	}

}