package com.syuesoft.sell.model;

/**
 * XsSellCustomTracing entity. @author MyEclipse Persistence Tools
 */

public class XsSellCustomTracing implements java.io.Serializable {

	// Fields

	private Integer tracingId;
	private Integer customId;
	private String tracingCode;
	private String tracingDate;
	private String interviewDate;
	private Integer tracingDayNumber;
	private String tracingAddress;
	private Integer ambience;
	private Integer tracingWay;
	private Integer steerTrial;
	private Integer carModel;
	private String hinderContent;
	private String talkTitle;
	private String tracingSummary;
	private String nextInterviewDate;
	private String examineOpinion;
	private String examineFlag;
	private String examineDate;
	private Double priceNeed;
	private String useNeed;
	private String capabilityNeed;
	private Integer colourNeed;
	private Integer caiModelNeed;
	private Integer paymentWay;
	private String predictBuyDate;
	private String buyProbability;
	private Integer obstacle;
	private String bespeakDate;
	private Integer needState;
	private Integer needType;
	private String remark;

	// Constructors

	/** default constructor */
	public XsSellCustomTracing() {
	}

	/** minimal constructor */
	public XsSellCustomTracing(Integer customId) {
		this.customId = customId;
	}

	/** full constructor */
	public XsSellCustomTracing(Integer customId, String tracingCode,
			String tracingDate, String interviewDate, Integer tracingDayNumber,
			String tracingAddress, Integer ambience, Integer tracingWay,
			Integer steerTrial, Integer carModel, String hinderContent,
			String talkTitle, String tracingSummary, String nextInterviewDate,
			String examineOpinion, String examineFlag, String examineDate,
			Double priceNeed, String useNeed, String capabilityNeed,
			Integer colourNeed, Integer caiModelNeed, Integer paymentWay,
			String predictBuyDate, String buyProbability, Integer obstacle,
			String bespeakDate, Integer needState, Integer needType,
			String remark) {
		this.customId = customId;
		this.tracingCode = tracingCode;
		this.tracingDate = tracingDate;
		this.interviewDate = interviewDate;
		this.tracingDayNumber = tracingDayNumber;
		this.tracingAddress = tracingAddress;
		this.ambience = ambience;
		this.tracingWay = tracingWay;
		this.steerTrial = steerTrial;
		this.carModel = carModel;
		this.hinderContent = hinderContent;
		this.talkTitle = talkTitle;
		this.tracingSummary = tracingSummary;
		this.nextInterviewDate = nextInterviewDate;
		this.examineOpinion = examineOpinion;
		this.examineFlag = examineFlag;
		this.examineDate = examineDate;
		this.priceNeed = priceNeed;
		this.useNeed = useNeed;
		this.capabilityNeed = capabilityNeed;
		this.colourNeed = colourNeed;
		this.caiModelNeed = caiModelNeed;
		this.paymentWay = paymentWay;
		this.predictBuyDate = predictBuyDate;
		this.buyProbability = buyProbability;
		this.obstacle = obstacle;
		this.bespeakDate = bespeakDate;
		this.needState = needState;
		this.needType = needType;
		this.remark = remark;
	}

	// Property accessors

	public Integer getTracingId() {
		return this.tracingId;
	}

	public void setTracingId(Integer tracingId) {
		this.tracingId = tracingId;
	}

	public Integer getCustomId() {
		return this.customId;
	}

	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	public String getTracingCode() {
		return this.tracingCode;
	}

	public void setTracingCode(String tracingCode) {
		this.tracingCode = tracingCode;
	}

	public String getTracingDate() {
		return this.tracingDate;
	}

	public void setTracingDate(String tracingDate) {
		this.tracingDate = tracingDate;
	}

	public String getInterviewDate() {
		return this.interviewDate;
	}

	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}

	public Integer getTracingDayNumber() {
		return this.tracingDayNumber;
	}

	public void setTracingDayNumber(Integer tracingDayNumber) {
		this.tracingDayNumber = tracingDayNumber;
	}

	public String getTracingAddress() {
		return this.tracingAddress;
	}

	public void setTracingAddress(String tracingAddress) {
		this.tracingAddress = tracingAddress;
	}

	public Integer getAmbience() {
		return this.ambience;
	}

	public void setAmbience(Integer ambience) {
		this.ambience = ambience;
	}

	public Integer getTracingWay() {
		return this.tracingWay;
	}

	public void setTracingWay(Integer tracingWay) {
		this.tracingWay = tracingWay;
	}

	public Integer getSteerTrial() {
		return this.steerTrial;
	}

	public void setSteerTrial(Integer steerTrial) {
		this.steerTrial = steerTrial;
	}

	public Integer getCarModel() {
		return this.carModel;
	}

	public void setCarModel(Integer carModel) {
		this.carModel = carModel;
	}

	public String getHinderContent() {
		return this.hinderContent;
	}

	public void setHinderContent(String hinderContent) {
		this.hinderContent = hinderContent;
	}

	public String getTalkTitle() {
		return this.talkTitle;
	}

	public void setTalkTitle(String talkTitle) {
		this.talkTitle = talkTitle;
	}

	public String getTracingSummary() {
		return this.tracingSummary;
	}

	public void setTracingSummary(String tracingSummary) {
		this.tracingSummary = tracingSummary;
	}

	public String getNextInterviewDate() {
		return this.nextInterviewDate;
	}

	public void setNextInterviewDate(String nextInterviewDate) {
		this.nextInterviewDate = nextInterviewDate;
	}

	public String getExamineOpinion() {
		return this.examineOpinion;
	}

	public void setExamineOpinion(String examineOpinion) {
		this.examineOpinion = examineOpinion;
	}

	public String getExamineFlag() {
		return this.examineFlag;
	}

	public void setExamineFlag(String examineFlag) {
		this.examineFlag = examineFlag;
	}

	public String getExamineDate() {
		return this.examineDate;
	}

	public void setExamineDate(String examineDate) {
		this.examineDate = examineDate;
	}

	public Double getPriceNeed() {
		return this.priceNeed;
	}

	public void setPriceNeed(Double priceNeed) {
		this.priceNeed = priceNeed;
	}

	public String getUseNeed() {
		return this.useNeed;
	}

	public void setUseNeed(String useNeed) {
		this.useNeed = useNeed;
	}

	public String getCapabilityNeed() {
		return this.capabilityNeed;
	}

	public void setCapabilityNeed(String capabilityNeed) {
		this.capabilityNeed = capabilityNeed;
	}

	public Integer getColourNeed() {
		return this.colourNeed;
	}

	public void setColourNeed(Integer colourNeed) {
		this.colourNeed = colourNeed;
	}

	public Integer getCaiModelNeed() {
		return this.caiModelNeed;
	}

	public void setCaiModelNeed(Integer caiModelNeed) {
		this.caiModelNeed = caiModelNeed;
	}

	public Integer getPaymentWay() {
		return this.paymentWay;
	}

	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getPredictBuyDate() {
		return this.predictBuyDate;
	}

	public void setPredictBuyDate(String predictBuyDate) {
		this.predictBuyDate = predictBuyDate;
	}

	public String getBuyProbability() {
		return this.buyProbability;
	}

	public void setBuyProbability(String buyProbability) {
		this.buyProbability = buyProbability;
	}

	public Integer getObstacle() {
		return this.obstacle;
	}

	public void setObstacle(Integer obstacle) {
		this.obstacle = obstacle;
	}

	public String getBespeakDate() {
		return this.bespeakDate;
	}

	public void setBespeakDate(String bespeakDate) {
		this.bespeakDate = bespeakDate;
	}

	public Integer getNeedState() {
		return this.needState;
	}

	public void setNeedState(Integer needState) {
		this.needState = needState;
	}

	public Integer getNeedType() {
		return this.needType;
	}

	public void setNeedType(Integer needType) {
		this.needType = needType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}