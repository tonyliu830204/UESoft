package com.syuesoft.model;

/**
 * StRtPartsDetail entity. @author MyEclipse Persistence Tools
 */


public class StRtPartsDetail extends BaseBean {

	private Integer strtpdIndex;
	private StRtPartsMain stRtPartsMain;
	private String partsId;
	private double strtpdCnt;
	private Short storeId;
	private String strtpdRemark;
	private Integer indexId;
	


	/** default constructor */
	public StRtPartsDetail() {
	}

	/** minimal constructor */
	public StRtPartsDetail(Integer strtpdIndex) {
		this.strtpdIndex = strtpdIndex;
	}

	/** full constructor */
	public StRtPartsDetail(Integer strtpdIndex, StRtPartsMain stRtPartsMain,
			String partsId, double strtpdCnt, Short storeId,Integer indexId,
			String strtpdRemark) {
		this.strtpdIndex = strtpdIndex;
		this.stRtPartsMain = stRtPartsMain;
		this.partsId = partsId;
		this.strtpdCnt = strtpdCnt;
		this.storeId = storeId;
		this.strtpdRemark = strtpdRemark;
		this.indexId=indexId;
	}

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Integer getStrtpdIndex() {
		return this.strtpdIndex;
	}

	public void setStrtpdIndex(Integer strtpdIndex) {
		this.strtpdIndex = strtpdIndex;
	}

	public StRtPartsMain getStRtPartsMain() {
		return this.stRtPartsMain;
	}

	public void setStRtPartsMain(StRtPartsMain stRtPartsMain) {
		this.stRtPartsMain = stRtPartsMain;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public double getStrtpdCnt() {
		return this.strtpdCnt;
	}

	public void setStrtpdCnt(double strtpdCnt) {
		this.strtpdCnt = strtpdCnt;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public String getStrtpdRemark() {
		return this.strtpdRemark;
	}

	public void setStrtpdRemark(String strtpdRemark) {
		this.strtpdRemark = strtpdRemark;
	}

}