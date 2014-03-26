package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StSellPreclrMain entity. @author MyEclipse Persistence Tools
 */

public class StSellPreclrMain extends BaseBean {


	private String preclrId;
	private Date preclrDate;
	private String cerNo;
	private String customId;
	private Integer preclrNum;
	private Double preclrAmount;
	private Double preclrCostAmount;
	private String classfication;
	private Short manager;
	private String remark;
	private Boolean state;
	private Set stSellPreclrDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public StSellPreclrMain() {
	}

	/** minimal constructor */
	public StSellPreclrMain(String preclrId) {
		this.preclrId = preclrId;
	}

	/** full constructor */
	public StSellPreclrMain(String preclrId, Date preclrDate,
			String cerNo, String customId, Integer preclrNum,
			Double preclrAmount, Double preclrCostAmount, String classfication,
			Short manager, String remark, Boolean state, Set stSellPreclrDetails) {
		this.preclrId = preclrId;
		this.preclrDate = preclrDate;
		this.cerNo = cerNo;
		this.customId = customId;
		this.preclrNum = preclrNum;
		this.preclrAmount = preclrAmount;
		this.preclrCostAmount = preclrCostAmount;
		this.classfication = classfication;
		this.manager = manager;
		this.remark = remark;
		this.state = state;
		this.stSellPreclrDetails = stSellPreclrDetails;
	}

	public String getPreclrId() {
		return this.preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public Date getPreclrDate() {
		return this.preclrDate;
	}

	public void setPreclrDate(Date preclrDate) {
		this.preclrDate = preclrDate;
	}

	public String getCerNo() {
		return this.cerNo;
	}

	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}

	public String getCustomId() {
		return this.customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public Integer getPreclrNum() {
		return this.preclrNum;
	}

	public void setPreclrNum(Integer preclrNum) {
		this.preclrNum = preclrNum;
	}

	public Double getPreclrAmount() {
		return this.preclrAmount;
	}

	public void setPreclrAmount(Double preclrAmount) {
		this.preclrAmount = preclrAmount;
	}

	public Double getPreclrCostAmount() {
		return this.preclrCostAmount;
	}

	public void setPreclrCostAmount(Double preclrCostAmount) {
		this.preclrCostAmount = preclrCostAmount;
	}

	public String getClassfication() {
		return this.classfication;
	}

	public void setClassfication(String classfication) {
		this.classfication = classfication;
	}

	public Short getManager() {
		return this.manager;
	}

	public void setManager(Short manager) {
		this.manager = manager;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Set getStSellPreclrDetails() {
		return this.stSellPreclrDetails;
	}

	public void setStSellPreclrDetails(Set stSellPreclrDetails) {
		this.stSellPreclrDetails = stSellPreclrDetails;
	}

}