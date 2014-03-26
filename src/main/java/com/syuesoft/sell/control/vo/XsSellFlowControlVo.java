package com.syuesoft.sell.control.vo;

import com.syuesoft.base.vo.BaseBeanVo;

/**
 * XsSellFlowControl entity. @author MyEclipse Persistence Tools
 */

public class XsSellFlowControlVo extends BaseBeanVo {

	// Fields

	private Integer xsfcontrolId;
	private Double xsfcontrolCode;
	private String xsfcontrolDocument;
	private Integer xsfcontrolCarId;

	// Constructors

	/** default constructor */
	public XsSellFlowControlVo() {
	}

	/** minimal constructor */
	public XsSellFlowControlVo(Integer xsfcontrolId) {
		this.xsfcontrolId = xsfcontrolId;
	}

	/** full constructor */
	public XsSellFlowControlVo(Integer xsfcontrolId, Double xsfcontrolCode,
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