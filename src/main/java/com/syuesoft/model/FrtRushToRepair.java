package com.syuesoft.model;

import java.util.Date;

/**
 * FrtRushToRepair entity. @author MyEclipse Persistence Tools
 */

public class FrtRushToRepair extends BaseBean {

	// Fields

	private String rtrId;
	private FrtResevation frtResevation;
	private String rtrServices;
	private String rtrSatisfaction;
	private Date rtrReportTime;
	private String rtrIdea;
	private Date rtrReplyTime;
	private String rtrIsCome;
	private String rtrReason;
	private Date rtrInTime;
	private Date rtrOutTime;
	private String rtrCsr;
	private Date rtrReturnVisitTime;
	private String rtrRemarke;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   } 
	// Constructors

	/** default constructor */
	public FrtRushToRepair() {
	}

	/** minimal constructor */
	public FrtRushToRepair(FrtResevation frtResevation) {
		this.frtResevation = frtResevation;
	}

	/** full constructor */
	public FrtRushToRepair(FrtResevation frtResevation, String rtrServices, String rtrSatisfaction, Date rtrReportTime, String rtrIdea, Date rtrReplyTime, String rtrIsCome,
			String rtrReason, Date rtrInTime, Date rtrOutTime, String rtrCsr, Date rtrReturnVisitTime, String rtrRemarke) {
		this.frtResevation = frtResevation;
		this.rtrServices = rtrServices;
		this.rtrSatisfaction = rtrSatisfaction;
		this.rtrReportTime = rtrReportTime;
		this.rtrIdea = rtrIdea;
		this.rtrReplyTime = rtrReplyTime;
		this.rtrIsCome = rtrIsCome;
		this.rtrReason = rtrReason;
		this.rtrInTime = rtrInTime;
		this.rtrOutTime = rtrOutTime;
		this.rtrCsr = rtrCsr;
		this.rtrReturnVisitTime = rtrReturnVisitTime;
		this.rtrRemarke = rtrRemarke;
	}

	// Property accessors

	public String getRtrId() {
		return this.rtrId;
	}

	public void setRtrId(String rtrId) {
		this.rtrId = rtrId;
	}

	public FrtResevation getFrtResevation() {
		return this.frtResevation;
	}

	public void setFrtResevation(FrtResevation frtResevation) {
		this.frtResevation = frtResevation;
	}

	public String getRtrServices() {
		return this.rtrServices;
	}

	public void setRtrServices(String rtrServices) {
		this.rtrServices = rtrServices;
	}

	public String getRtrSatisfaction() {
		return this.rtrSatisfaction;
	}

	public void setRtrSatisfaction(String rtrSatisfaction) {
		this.rtrSatisfaction = rtrSatisfaction;
	}

	public Date getRtrReportTime() {
		return this.rtrReportTime;
	}

	public void setRtrReportTime(Date rtrReportTime) {
		this.rtrReportTime = rtrReportTime;
	}

	public String getRtrIdea() {
		return this.rtrIdea;
	}

	public void setRtrIdea(String rtrIdea) {
		this.rtrIdea = rtrIdea;
	}

	public Date getRtrReplyTime() {
		return this.rtrReplyTime;
	}

	public void setRtrReplyTime(Date rtrReplyTime) {
		this.rtrReplyTime = rtrReplyTime;
	}

	public String getRtrIsCome() {
		return this.rtrIsCome;
	}

	public void setRtrIsCome(String rtrIsCome) {
		this.rtrIsCome = rtrIsCome;
	}

	public String getRtrReason() {
		return this.rtrReason;
	}

	public void setRtrReason(String rtrReason) {
		this.rtrReason = rtrReason;
	}

	public Date getRtrInTime() {
		return this.rtrInTime;
	}

	public void setRtrInTime(Date rtrInTime) {
		this.rtrInTime = rtrInTime;
	}

	public Date getRtrOutTime() {
		return this.rtrOutTime;
	}

	public void setRtrOutTime(Date rtrOutTime) {
		this.rtrOutTime = rtrOutTime;
	}

	public String getRtrCsr() {
		return this.rtrCsr;
	}

	public void setRtrCsr(String rtrCsr) {
		this.rtrCsr = rtrCsr;
	}

	public Date getRtrReturnVisitTime() {
		return this.rtrReturnVisitTime;
	}

	public void setRtrReturnVisitTime(Date rtrReturnVisitTime) {
		this.rtrReturnVisitTime = rtrReturnVisitTime;
	}

	public String getRtrRemarke() {
		return this.rtrRemarke;
	}

	public void setRtrRemarke(String rtrRemarke) {
		this.rtrRemarke = rtrRemarke;
	}

}