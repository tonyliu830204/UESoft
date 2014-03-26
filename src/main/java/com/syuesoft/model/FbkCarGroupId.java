package com.syuesoft.model;

/**
 * FbkCarGroupId entity. @author MyEclipse Persistence Tools
 */

public class FbkCarGroupId extends BaseBean {

	// Fields

	private Integer GId;
	private String carId;

	// Constructors

	/** default constructor */
	public FbkCarGroupId() {
	}

	/** full constructor */
	public FbkCarGroupId(Integer GId, String carId) {
		this.GId = GId;
		this.carId = carId;
	}

	// Property accessors

	public Integer getGId() {
		return this.GId;
	}

	public void setGId(Integer GId) {
		this.GId = GId;
	}

	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FbkCarGroupId))
			return false;
		FbkCarGroupId castOther = (FbkCarGroupId) other;

		return ((this.getGId() == castOther.getGId()) || (this.getGId() != null
				&& castOther.getGId() != null && this.getGId().equals(
				castOther.getGId())))
				&& ((this.getCarId() == castOther.getCarId()) || (this
						.getCarId() != null
						&& castOther.getCarId() != null && this.getCarId()
						.equals(castOther.getCarId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGId() == null ? 0 : this.getGId().hashCode());
		result = 37 * result
				+ (getCarId() == null ? 0 : this.getCarId().hashCode());
		return result;
	}

}