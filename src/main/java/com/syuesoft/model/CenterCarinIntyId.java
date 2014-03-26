package com.syuesoft.model;

/**
 * CenterCarinIntyId entity. @author MyEclipse Persistence Tools
 */

public class CenterCarinIntyId extends BaseBean {

	// Fields

	private InsuranceType insuranceType;
	private CarInsuranceManage carInsuranceManage;

	// Constructors

	/** default constructor */
	public CenterCarinIntyId() {
	}

	/** full constructor */
	public CenterCarinIntyId(InsuranceType insuranceType,
			CarInsuranceManage carInsuranceManage) {
		this.insuranceType = insuranceType;
		this.carInsuranceManage = carInsuranceManage;
	}

	// Property accessors

	public InsuranceType getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	public CarInsuranceManage getCarInsuranceManage() {
		return this.carInsuranceManage;
	}

	public void setCarInsuranceManage(CarInsuranceManage carInsuranceManage) {
		this.carInsuranceManage = carInsuranceManage;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CenterCarinIntyId))
			return false;
		CenterCarinIntyId castOther = (CenterCarinIntyId) other;

		return ((this.getInsuranceType() == castOther.getInsuranceType()) || (this
				.getInsuranceType() != null
				&& castOther.getInsuranceType() != null && this
				.getInsuranceType().equals(castOther.getInsuranceType())))
				&& ((this.getCarInsuranceManage() == castOther
						.getCarInsuranceManage()) || (this
						.getCarInsuranceManage() != null
						&& castOther.getCarInsuranceManage() != null && this
						.getCarInsuranceManage().equals(
								castOther.getCarInsuranceManage())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInsuranceType() == null ? 0 : this.getInsuranceType()
						.hashCode());
		result = 37
				* result
				+ (getCarInsuranceManage() == null ? 0 : this
						.getCarInsuranceManage().hashCode());
		return result;
	}

}