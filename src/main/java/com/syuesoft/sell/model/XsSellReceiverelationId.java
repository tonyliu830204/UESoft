package com.syuesoft.sell.model;

/**
 * XsSellReceiverelationId entity. @author MyEclipse Persistence Tools
 */

public class XsSellReceiverelationId implements java.io.Serializable {

	// Fields

	private XsCustomInfo xsCustomInfo;
	private String xsCustomSalesmanDetailId;

	// Constructors

	/** default constructor */
	public XsSellReceiverelationId() {
	}

	/** full constructor */
	public XsSellReceiverelationId(XsCustomInfo xsCustomInfo,
			String xsCustomSalesmanDetailId) {
		this.xsCustomInfo = xsCustomInfo;
		this.xsCustomSalesmanDetailId = xsCustomSalesmanDetailId;
	}

	// Property accessors

	public XsCustomInfo getXsCustomInfo() {
		return this.xsCustomInfo;
	}

	public void setXsCustomInfo(XsCustomInfo xsCustomInfo) {
		this.xsCustomInfo = xsCustomInfo;
	}

	public String getXsCustomSalesmanDetailId() {
		return this.xsCustomSalesmanDetailId;
	}

	public void setXsCustomSalesmanDetailId(String xsCustomSalesmanDetailId) {
		this.xsCustomSalesmanDetailId = xsCustomSalesmanDetailId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof XsSellReceiverelationId))
			return false;
		XsSellReceiverelationId castOther = (XsSellReceiverelationId) other;

		return ((this.getXsCustomInfo() == castOther.getXsCustomInfo()) || (this
				.getXsCustomInfo() != null
				&& castOther.getXsCustomInfo() != null && this
				.getXsCustomInfo().equals(castOther.getXsCustomInfo())))
				&& ((this.getXsCustomSalesmanDetailId() == castOther
						.getXsCustomSalesmanDetailId()) || (this
						.getXsCustomSalesmanDetailId() != null
						&& castOther.getXsCustomSalesmanDetailId() != null && this
						.getXsCustomSalesmanDetailId().equals(
								castOther.getXsCustomSalesmanDetailId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getXsCustomInfo() == null ? 0 : this.getXsCustomInfo()
						.hashCode());
		result = 37
				* result
				+ (getXsCustomSalesmanDetailId() == null ? 0 : this
						.getXsCustomSalesmanDetailId().hashCode());
		return result;
	}

}