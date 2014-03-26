package com.syuesoft.sell.model;

/**
 * XsSellReceiverelation entity. @author MyEclipse Persistence Tools
 */

public class XsSellReceiverelation implements java.io.Serializable {

	// Fields

	private XsSellReceiverelationId id;

	// Constructors

	/** default constructor */
	public XsSellReceiverelation() {
	}

	/** full constructor */
	public XsSellReceiverelation(XsSellReceiverelationId id) {
		this.id = id;
	}

	// Property accessors

	public XsSellReceiverelationId getId() {
		return this.id;
	}

	public void setId(XsSellReceiverelationId id) {
		this.id = id;
	}

}