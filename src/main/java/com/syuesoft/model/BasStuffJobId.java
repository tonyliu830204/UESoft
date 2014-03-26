package com.syuesoft.model;

/**
 * BasStuffJobId entity. @author MyEclipse Persistence Tools
 */

public class BasStuffJobId extends BaseBean {

	// Fields

	private BasStuff basStuff;
	private BasJobProperty basJobProperty;

	// Constructors

	/** default constructor */
	public BasStuffJobId() {
	}

	/** full constructor */
	public BasStuffJobId(BasStuff basStuff, BasJobProperty basJobProperty) {
		this.basStuff = basStuff;
		this.basJobProperty = basJobProperty;
	}

	// Property accessors

	public BasStuff getBasStuff() {
		return this.basStuff;
	}

	public void setBasStuff(BasStuff basStuff) {
		this.basStuff = basStuff;
	}

	public BasJobProperty getBasJobProperty() {
		return this.basJobProperty;
	}

	public void setBasJobProperty(BasJobProperty basJobProperty) {
		this.basJobProperty = basJobProperty;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BasStuffJobId))
			return false;
		BasStuffJobId castOther = (BasStuffJobId) other;

		return ((this.getBasStuff() == castOther.getBasStuff()) || (this
				.getBasStuff() != null
				&& castOther.getBasStuff() != null && this.getBasStuff()
				.equals(castOther.getBasStuff())))
				&& ((this.getBasJobProperty() == castOther.getBasJobProperty()) || (this
						.getBasJobProperty() != null
						&& castOther.getBasJobProperty() != null && this
						.getBasJobProperty().equals(
								castOther.getBasJobProperty())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBasStuff() == null ? 0 : this.getBasStuff().hashCode());
		result = 37
				* result
				+ (getBasJobProperty() == null ? 0 : this.getBasJobProperty()
						.hashCode());
		return result;
	}

}