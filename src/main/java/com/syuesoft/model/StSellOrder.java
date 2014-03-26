package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StSellOrder entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class StSellOrder extends BaseBean {

	// Fields
	private String sellmmId;
	private Date sellmmDate;
	private String sellcustomId;
	private Double sellmmSumAmount;
	private Double sellmmSumCost;
	private Double sellmmCnt;
	private Short psellId;
	private String sellmmClearingStatus;
	private Short sellmmStfId;
	private String sellmmRemark;
	private String preclrInoiceType;
	private String carLicense;
	private String billType;
	private Short sellManager;
	private String preclrState;
	private Set stSellOrderitems = new HashSet(0);
	private String priceType;
	private Double priceQuotiety;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }

	/** default constructor */
	public StSellOrder() {
	}

	public Double getPriceQuotiety() {
		return priceQuotiety;
	}

	public void setPriceQuotiety(Double priceQuotiety) {
		this.priceQuotiety = priceQuotiety;
	}

	/** minimal constructor */
	public StSellOrder(String sellmmId) {
		this.sellmmId = sellmmId;
	}

	/** full constructor */
	public StSellOrder(String sellmmId, Date sellmmDate, String sellcustomId,
			Double sellmmSumAmount, Double sellmmSumCost, Double sellmmCnt,
			Short psellId, String sellmmClearingStatus, Short sellmmStfId,
			String sellmmRemark, String carLicense, String billType,
			Short sellManager, Set stSellOrderitems,String preclrInoiceType,String preclrState) {
		this.sellmmId = sellmmId;
		this.sellmmDate = sellmmDate;
		this.sellcustomId = sellcustomId;
		this.sellmmSumAmount = sellmmSumAmount;
		this.sellmmSumCost = sellmmSumCost;
		this.sellmmCnt = sellmmCnt;
		this.psellId = psellId;
		this.sellmmClearingStatus = sellmmClearingStatus;
		this.sellmmStfId = sellmmStfId;
		this.sellmmRemark = sellmmRemark;
		this.carLicense = carLicense;
		this.billType = billType;
		this.sellManager = sellManager;
		this.stSellOrderitems = stSellOrderitems;
		this.preclrInoiceType = preclrInoiceType;
		this.preclrState = preclrState;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getSellmmId() {
		return this.sellmmId;
	}

	public void setSellmmId(String sellmmId) {
		this.sellmmId = sellmmId;
	}

	public Date getSellmmDate() {
		return this.sellmmDate;
	}

	public void setSellmmDate(Date sellmmDate) {
		this.sellmmDate = sellmmDate;
	}
	
	public Double getSellmmSumAmount() {
		return this.sellmmSumAmount;
	}

	public void setSellmmSumAmount(Double sellmmSumAmount) {
		this.sellmmSumAmount = sellmmSumAmount;
	}

	public Double getSellmmSumCost() {
		return this.sellmmSumCost;
	}

	public void setSellmmSumCost(Double sellmmSumCost) {
		this.sellmmSumCost = sellmmSumCost;
	}

	public Double getSellmmCnt() {
		return this.sellmmCnt;
	}

	public void setSellmmCnt(Double sellmmCnt) {
		this.sellmmCnt = sellmmCnt;
	}

	public Short getPsellId() {
		return this.psellId;
	}

	public void setPsellId(Short psellId) {
		this.psellId = psellId;
	}

	public String getSellmmClearingStatus() {
		return this.sellmmClearingStatus;
	}

	public void setSellmmClearingStatus(String sellmmClearingStatus) {
		this.sellmmClearingStatus = sellmmClearingStatus;
	}

	public Short getSellmmStfId() {
		return this.sellmmStfId;
	}

	public void setSellmmStfId(Short sellmmStfId) {
		this.sellmmStfId = sellmmStfId;
	}

	public String getSellmmRemark() {
		return this.sellmmRemark;
	}

	public void setSellmmRemark(String sellmmRemark) {
		this.sellmmRemark = sellmmRemark;
	}

	public String getCarLicense() {
		return this.carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Short getSellManager() {
		return this.sellManager;
	}

	public void setSellManager(Short sellManager) {
		this.sellManager = sellManager;
	}

	public Set getStSellOrderitems() {
		return this.stSellOrderitems;
	}

	public void setStSellOrderitems(Set stSellOrderitems) {
		this.stSellOrderitems = stSellOrderitems;
	}

	public String getSellcustomId() {
		return sellcustomId;
	}

	public void setSellcustomId(String sellcustomId) {
		this.sellcustomId = sellcustomId;
	}

	public String getPreclrInoiceType() {
		return preclrInoiceType;
	}

	public void setPreclrInoiceType(String preclrInoiceType) {
		this.preclrInoiceType = preclrInoiceType;
	}

	public String getPreclrState() {
		return preclrState;
	}

	public void setPreclrState(String preclrState) {
		this.preclrState = preclrState;
	}

}