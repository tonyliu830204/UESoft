package com.syuesoft.model;

/**
 * StOutInItemId entity. @author MyEclipse Persistence Tools
 */

public class StOutInItemId implements java.io.Serializable {

	// Fields

	private Integer indexId;
	private Integer itemIndex;

	// Constructors

	/** default constructor */
	public StOutInItemId() {
	}

	/** full constructor */
	public StOutInItemId(Integer indexId, Integer itemIndex) {
		this.indexId = indexId;
		this.itemIndex = itemIndex;
	}

	// Property accessors

	public Integer getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Integer getItemIndex() {
		return this.itemIndex;
	}

	public void setItemIndex(Integer itemIndex) {
		this.itemIndex = itemIndex;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StOutInItemId))
			return false;
		StOutInItemId castOther = (StOutInItemId) other;

		return ((this.getIndexId() == castOther.getIndexId()) || (this
				.getIndexId() != null
				&& castOther.getIndexId() != null && this.getIndexId().equals(
				castOther.getIndexId())))
				&& ((this.getItemIndex() == castOther.getItemIndex()) || (this
						.getItemIndex() != null
						&& castOther.getItemIndex() != null && this
						.getItemIndex().equals(castOther.getItemIndex())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIndexId() == null ? 0 : this.getIndexId().hashCode());
		result = 37 * result
				+ (getItemIndex() == null ? 0 : this.getItemIndex().hashCode());
		return result;
	}

}