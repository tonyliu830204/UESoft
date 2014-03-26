package com.syuesoft.model;

/**
 * StSellPreclrDetail entity. @author MyEclipse Persistence Tools
 */

public class StSellPreclrDetail extends BaseBean {

	// Fields

	private Integer preclrItemId;
	private StSellPreclrMain stSellPreclrMain;
	private String partId;
	private Double price;
	private Integer partsnum;
	private Double partamount;
	private Double costprice;
	private Double costamount;

	// Constructors

	/** default constructor */
	public StSellPreclrDetail() {
	}

	/** minimal constructor */
	public StSellPreclrDetail(Integer preclrItemId) {
		this.preclrItemId = preclrItemId;
	}

	/** full constructor */
	public StSellPreclrDetail(Integer preclrItemId,
			StSellPreclrMain stSellPreclrMain, String partId, Double price,
			Integer partsnum, Double partamount, Double costprice,
			Double costamount) {
		this.preclrItemId = preclrItemId;
		this.stSellPreclrMain = stSellPreclrMain;
		this.partId = partId;
		this.price = price;
		this.partsnum = partsnum;
		this.partamount = partamount;
		this.costprice = costprice;
		this.costamount = costamount;
	}

	// Property accessors

	public Integer getPreclrItemId() {
		return this.preclrItemId;
	}

	public void setPreclrItemId(Integer preclrItemId) {
		this.preclrItemId = preclrItemId;
	}

	public StSellPreclrMain getStSellPreclrMain() {
		return this.stSellPreclrMain;
	}

	public void setStSellPreclrMain(StSellPreclrMain stSellPreclrMain) {
		this.stSellPreclrMain = stSellPreclrMain;
	}

	public String getPartId() {
		return this.partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPartsnum() {
		return this.partsnum;
	}

	public void setPartsnum(Integer partsnum) {
		this.partsnum = partsnum;
	}

	public Double getPartamount() {
		return this.partamount;
	}

	public void setPartamount(Double partamount) {
		this.partamount = partamount;
	}

	public Double getCostprice() {
		return this.costprice;
	}

	public void setCostprice(Double costprice) {
		this.costprice = costprice;
	}

	public Double getCostamount() {
		return this.costamount;
	}

	public void setCostamount(Double costamount) {
		this.costamount = costamount;
	}

}