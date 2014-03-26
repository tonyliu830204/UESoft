package com.syuesoft.sell.model;

/**
 * AbstractXsSellCarinstallDetail entity provides the base persistence
 * definition of the XsSellCarinstallDetail entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractXsSellCarinstallDetail implements
		java.io.Serializable {

	// Fields

	private Integer detailId;
	private XsSellCarinstall xsSellCarinstall;
	private Integer partsId;
	private Double paetsCaseMoney;
	private Double paetsSellMoney;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractXsSellCarinstallDetail() {
	}

	/** full constructor */
	public AbstractXsSellCarinstallDetail(XsSellCarinstall xsSellCarinstall,
			Integer partsId, Double paetsCaseMoney, Double paetsSellMoney,
			String remark) {
		this.xsSellCarinstall = xsSellCarinstall;
		this.partsId = partsId;
		this.paetsCaseMoney = paetsCaseMoney;
		this.paetsSellMoney = paetsSellMoney;
		this.remark = remark;
	}

	// Property accessors

	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public XsSellCarinstall getXsSellCarinstall() {
		return this.xsSellCarinstall;
	}

	public void setXsSellCarinstall(XsSellCarinstall xsSellCarinstall) {
		this.xsSellCarinstall = xsSellCarinstall;
	}

	public Integer getPartsId() {
		return this.partsId;
	}

	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}

	public Double getPaetsCaseMoney() {
		return this.paetsCaseMoney;
	}

	public void setPaetsCaseMoney(Double paetsCaseMoney) {
		this.paetsCaseMoney = paetsCaseMoney;
	}

	public Double getPaetsSellMoney() {
		return this.paetsSellMoney;
	}

	public void setPaetsSellMoney(Double paetsSellMoney) {
		this.paetsSellMoney = paetsSellMoney;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}