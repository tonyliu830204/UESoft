package com.syuesoft.model;

/**
 * BasVipRelationship17Id entity. @author MyEclipse Persistence Tools
 */

public class BasVipRelationship17Id extends BaseBean {

	// Fields

	private BasVipResentExchange basVipResentExchange;
	private BasVipResent basVipResent;

	// Constructors

	/** default constructor */
	public BasVipRelationship17Id() {
	}

	/** full constructor */
	public BasVipRelationship17Id(BasVipResentExchange basVipResentExchange,
			BasVipResent basVipResent) {
		this.basVipResentExchange = basVipResentExchange;
		this.basVipResent = basVipResent;
	}

	// Property accessors

	public BasVipResentExchange getBasVipResentExchange() {
		return this.basVipResentExchange;
	}

	public void setBasVipResentExchange(
			BasVipResentExchange basVipResentExchange) {
		this.basVipResentExchange = basVipResentExchange;
	}

	public BasVipResent getBasVipResent() {
		return this.basVipResent;
	}

	public void setBasVipResent(BasVipResent basVipResent) {
		this.basVipResent = basVipResent;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BasVipRelationship17Id))
			return false;
		BasVipRelationship17Id castOther = (BasVipRelationship17Id) other;

		return ((this.getBasVipResentExchange() == castOther
				.getBasVipResentExchange()) || (this.getBasVipResentExchange() != null
				&& castOther.getBasVipResentExchange() != null && this
				.getBasVipResentExchange().equals(
						castOther.getBasVipResentExchange())))
				&& ((this.getBasVipResent() == castOther.getBasVipResent()) || (this
						.getBasVipResent() != null
						&& castOther.getBasVipResent() != null && this
						.getBasVipResent().equals(castOther.getBasVipResent())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBasVipResentExchange() == null ? 0 : this
						.getBasVipResentExchange().hashCode());
		result = 37
				* result
				+ (getBasVipResent() == null ? 0 : this.getBasVipResent()
						.hashCode());
		return result;
	}

}