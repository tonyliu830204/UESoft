package com.syuesoft.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StSellPercharge entity. @author MyEclipse Persistence Tools
 */

public class StSellPercharge extends BaseBean {

	// Fields

	private String perchargeId;
	private StRecharge stRecharge;
	private BasStuff basStuff;
	private BasChilddictionary basChilddictionary;
	private Date perchargeDate;
	private Double curPercharge;
	private String preclrInoiceType;
	private String preclrNo;
	private Date preclrInvoiceTime;
	private String chargeRemark;
	private Integer flag;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	private Set finUseRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public StSellPercharge() {
	}

	/** minimal constructor */
	public StSellPercharge(String perchargeId) {
		this.perchargeId = perchargeId;
	}

	/** full constructor */
	public StSellPercharge(String perchargeId, StRecharge stRecharge,
			BasStuff basStuff, BasChilddictionary basChilddictionary,
			Date perchargeDate, Double curPercharge, String preclrInoiceType,
			String preclrNo, Date preclrInvoiceTime, String chargeRemark,
			Set finUseRecords,Integer flag) {
		this.perchargeId = perchargeId;
		this.stRecharge = stRecharge;
		this.basStuff = basStuff;
		this.basChilddictionary = basChilddictionary;
		this.perchargeDate = perchargeDate;
		this.curPercharge = curPercharge;
		this.preclrInoiceType = preclrInoiceType;
		this.preclrNo = preclrNo;
		this.preclrInvoiceTime = preclrInvoiceTime;
		this.chargeRemark = chargeRemark;
		this.finUseRecords = finUseRecords;
		this.flag=flag;
	}

	// Property accessors

	public String getPerchargeId() {
		return this.perchargeId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public void setPerchargeId(String perchargeId) {
		this.perchargeId = perchargeId;
	}

	public StRecharge getStRecharge() {
		return this.stRecharge;
	}

	public void setStRecharge(StRecharge stRecharge) {
		this.stRecharge = stRecharge;
	}

	public BasStuff getBasStuff() {
		return this.basStuff;
	}

	public void setBasStuff(BasStuff basStuff) {
		this.basStuff = basStuff;
	}

	public BasChilddictionary getBasChilddictionary() {
		return this.basChilddictionary;
	}

	public void setBasChilddictionary(BasChilddictionary basChilddictionary) {
		this.basChilddictionary = basChilddictionary;
	}

	public Date getPerchargeDate() {
		return this.perchargeDate;
	}

	public void setPerchargeDate(Date perchargeDate) {
		this.perchargeDate = perchargeDate;
	}

	public Double getCurPercharge() {
		return this.curPercharge;
	}

	public void setCurPercharge(Double curPercharge) {
		this.curPercharge = curPercharge;
	}

	public String getPreclrInoiceType() {
		return this.preclrInoiceType;
	}

	public void setPreclrInoiceType(String preclrInoiceType) {
		this.preclrInoiceType = preclrInoiceType;
	}

	public String getPreclrNo() {
		return this.preclrNo;
	}

	public void setPreclrNo(String preclrNo) {
		this.preclrNo = preclrNo;
	}

	public Date getPreclrInvoiceTime() {
		return this.preclrInvoiceTime;
	}

	public void setPreclrInvoiceTime(Date preclrInvoiceTime) {
		this.preclrInvoiceTime = preclrInvoiceTime;
	}

	public String getChargeRemark() {
		return this.chargeRemark;
	}

	public void setChargeRemark(String chargeRemark) {
		this.chargeRemark = chargeRemark;
	}

	public Set getFinUseRecords() {
		return this.finUseRecords;
	}

	public void setFinUseRecords(Set finUseRecords) {
		this.finUseRecords = finUseRecords;
	}

}