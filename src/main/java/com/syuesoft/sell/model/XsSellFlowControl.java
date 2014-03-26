package com.syuesoft.sell.model;

/**
 * XsSellFlowControl entity. @author MyEclipse Persistence Tools
 */

public class XsSellFlowControl  implements java.io.Serializable {

	// Fields

	private Integer xsfcontrolId;
	private Double xsfcontrolCode;
	private String xsfcontrolDocument;
	private Integer xsfcontrolCarId;

	// Constructors

	/** default constructor */
	public XsSellFlowControl() {
	}

	/** minimal constructor */
	public XsSellFlowControl(Integer xsfcontrolId) {
		this.xsfcontrolId = xsfcontrolId;
	}

	/** full constructor */
	public XsSellFlowControl(Integer xsfcontrolId, Double xsfcontrolCode,
			String xsfcontrolDocument, Integer xsfcontrolCarId) {
		this.xsfcontrolId = xsfcontrolId;
		this.xsfcontrolCode = xsfcontrolCode;
		this.xsfcontrolDocument = xsfcontrolDocument;
		this.xsfcontrolCarId = xsfcontrolCarId;
	}

	// Property accessors

	public Integer getXsfcontrolId() {
		return this.xsfcontrolId;
	}

	public void setXsfcontrolId(Integer xsfcontrolId) {
		this.xsfcontrolId = xsfcontrolId;
	}

	public Double getXsfcontrolCode() {
		return this.xsfcontrolCode;
	}

	public void setXsfcontrolCode(Double xsfcontrolCode) {
		this.xsfcontrolCode = xsfcontrolCode;
	}

	public String getXsfcontrolDocument() {
		return this.xsfcontrolDocument;
	}

	public void setXsfcontrolDocument(String xsfcontrolDocument) {
		this.xsfcontrolDocument = xsfcontrolDocument;
	}

	public Integer getXsfcontrolCarId() {
		return this.xsfcontrolCarId;
	}

	public void setXsfcontrolCarId(Integer xsfcontrolCarId) {
		this.xsfcontrolCarId = xsfcontrolCarId;
	}

}