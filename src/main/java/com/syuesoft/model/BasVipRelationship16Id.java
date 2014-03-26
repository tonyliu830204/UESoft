package com.syuesoft.model;

/**
 * BasVipRelationship16Id entity. @author MyEclipse Persistence Tools
 */

public class BasVipRelationship16Id extends BaseBean {

	// Fields

	private BasVipGiveIntegral basVipGiveIntegral;                          //赠送积分
	private BasVipGiveIntegralProject basVipGiveIntegralProject;            //赠送项目

	// Constructors

	/** default constructor */
	public BasVipRelationship16Id() {
	}

	/** full constructor */
	public BasVipRelationship16Id(BasVipGiveIntegral basVipGiveIntegral,
			BasVipGiveIntegralProject basVipGiveIntegralProject) {
		this.basVipGiveIntegral = basVipGiveIntegral;
		this.basVipGiveIntegralProject = basVipGiveIntegralProject;
	}

	// Property accessors

	public BasVipGiveIntegral getBasVipGiveIntegral() {
		return this.basVipGiveIntegral;
	}

	public void setBasVipGiveIntegral(BasVipGiveIntegral basVipGiveIntegral) {
		this.basVipGiveIntegral = basVipGiveIntegral;
	}

	public BasVipGiveIntegralProject getBasVipGiveIntegralProject() {
		return this.basVipGiveIntegralProject;
	}

	public void setBasVipGiveIntegralProject(
			BasVipGiveIntegralProject basVipGiveIntegralProject) {
		this.basVipGiveIntegralProject = basVipGiveIntegralProject;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BasVipRelationship16Id))
			return false;
		BasVipRelationship16Id castOther = (BasVipRelationship16Id) other;

		return ((this.getBasVipGiveIntegral() == castOther
				.getBasVipGiveIntegral()) || (this.getBasVipGiveIntegral() != null
				&& castOther.getBasVipGiveIntegral() != null && this
				.getBasVipGiveIntegral().equals(
						castOther.getBasVipGiveIntegral())))
				&& ((this.getBasVipGiveIntegralProject() == castOther
						.getBasVipGiveIntegralProject()) || (this
						.getBasVipGiveIntegralProject() != null
						&& castOther.getBasVipGiveIntegralProject() != null && this
						.getBasVipGiveIntegralProject().equals(
								castOther.getBasVipGiveIntegralProject())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBasVipGiveIntegral() == null ? 0 : this
						.getBasVipGiveIntegral().hashCode());
		result = 37
				* result
				+ (getBasVipGiveIntegralProject() == null ? 0 : this
						.getBasVipGiveIntegralProject().hashCode());
		return result;
	}

}