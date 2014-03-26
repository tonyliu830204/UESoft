package com.syuesoft.model;

/**
 * BasVipRelationship15Id entity. @author MyEclipse Persistence Tools
 */

public class BasVipRelationship15Id extends BaseBean {

	// Fields

	private BasVipInfor basVipInfor;
	private BasVipActivitiesProject basVipActivitiesProject;

	// Constructors

	/** default constructor */
	public BasVipRelationship15Id() {
	}

	/** full constructor */
	public BasVipRelationship15Id(BasVipInfor basVipInfor,
			BasVipActivitiesProject basVipActivitiesProject) {
		this.basVipInfor = basVipInfor;
		this.basVipActivitiesProject = basVipActivitiesProject;
	}

	// Property accessors

	public BasVipInfor getBasVipInfor() {
		return this.basVipInfor;
	}

	public void setBasVipInfor(BasVipInfor basVipInfor) {
		this.basVipInfor = basVipInfor;
	}

	public BasVipActivitiesProject getBasVipActivitiesProject() {
		return this.basVipActivitiesProject;
	}

	public void setBasVipActivitiesProject(
			BasVipActivitiesProject basVipActivitiesProject) {
		this.basVipActivitiesProject = basVipActivitiesProject;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BasVipRelationship15Id))
			return false;
		BasVipRelationship15Id castOther = (BasVipRelationship15Id) other;

		return ((this.getBasVipInfor() == castOther.getBasVipInfor()) || (this
				.getBasVipInfor() != null
				&& castOther.getBasVipInfor() != null && this.getBasVipInfor()
				.equals(castOther.getBasVipInfor())))
				&& ((this.getBasVipActivitiesProject() == castOther
						.getBasVipActivitiesProject()) || (this
						.getBasVipActivitiesProject() != null
						&& castOther.getBasVipActivitiesProject() != null && this
						.getBasVipActivitiesProject().equals(
								castOther.getBasVipActivitiesProject())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBasVipInfor() == null ? 0 : this.getBasVipInfor()
						.hashCode());
		result = 37
				* result
				+ (getBasVipActivitiesProject() == null ? 0 : this
						.getBasVipActivitiesProject().hashCode());
		return result;
	}

}