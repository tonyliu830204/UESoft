package com.syuesoft.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * VTrackRecordId entity. @author MyEclipse Persistence Tools
 */

public class VTrackRecordId extends BaseBean {

	// Fields

	private String carLicense;
	private String carCstlName;
	private String carVan;
	private Date carBuyDate;
	private String carRelationPerson;
	private String ctypeName;
	private String customName;
	private String customTel1;
	private String customAddr;
	private String customSex;
	private Integer receptionDistance;
	private String receptionId;
	private Timestamp interDate;
	private String propRepPer;
	private String propTel;
	private String receptor;
	private Timestamp preclrTime;
	private Double preclrRealAmount;
	private String pareaName;
	private String colorName;
	private String reptName;
	private String satisfaction;
	private Date returnVisitDate;
	private String handlePerson;
	private String returnVisitMembers;
	private String callSituation;
	private String reciptReturnvisit;
	private String handleResult;
	private String memo;
	private String complaintContent;
	private String handleProgram;
	private String complaintType;
	private String complaintDegree;

	// Constructors

	/** default constructor */
	public VTrackRecordId() {
	}

	/** minimal constructor */
	public VTrackRecordId(Integer receptionDistance, String receptionId) {
		this.receptionDistance = receptionDistance;
		this.receptionId = receptionId;
	}

	/** full constructor */
	public VTrackRecordId(String carLicense, String carCstlName, String carVan,
			Date carBuyDate, String carRelationPerson, String ctypeName,
			String customName, String customTel1, String customAddr,
			String customSex, Integer receptionDistance, String receptionId,
			Timestamp interDate, String propRepPer, String propTel,
			String receptor, Timestamp preclrTime, Double preclrRealAmount,
			String pareaName, String colorName, String reptName,
			String satisfaction, Date returnVisitDate, String handlePerson,
			String returnVisitMembers, String callSituation,
			String reciptReturnvisit, String handleResult, String memo,
			String complaintContent, String handleProgram,
			String complaintType, String complaintDegree) {
		this.carLicense = carLicense;
		this.carCstlName = carCstlName;
		this.carVan = carVan;
		this.carBuyDate = carBuyDate;
		this.carRelationPerson = carRelationPerson;
		this.ctypeName = ctypeName;
		this.customName = customName;
		this.customTel1 = customTel1;
		this.customAddr = customAddr;
		this.customSex = customSex;
		this.receptionDistance = receptionDistance;
		this.receptionId = receptionId;
		this.interDate = interDate;
		this.propRepPer = propRepPer;
		this.propTel = propTel;
		this.receptor = receptor;
		this.preclrTime = preclrTime;
		this.preclrRealAmount = preclrRealAmount;
		this.pareaName = pareaName;
		this.colorName = colorName;
		this.reptName = reptName;
		this.satisfaction = satisfaction;
		this.returnVisitDate = returnVisitDate;
		this.handlePerson = handlePerson;
		this.returnVisitMembers = returnVisitMembers;
		this.callSituation = callSituation;
		this.reciptReturnvisit = reciptReturnvisit;
		this.handleResult = handleResult;
		this.memo = memo;
		this.complaintContent = complaintContent;
		this.handleProgram = handleProgram;
		this.complaintType = complaintType;
		this.complaintDegree = complaintDegree;
	}

	// Property accessors

	public String getCarLicense() {
		return this.carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getCarCstlName() {
		return this.carCstlName;
	}

	public void setCarCstlName(String carCstlName) {
		this.carCstlName = carCstlName;
	}

	public String getCarVan() {
		return this.carVan;
	}

	public void setCarVan(String carVan) {
		this.carVan = carVan;
	}

	public Date getCarBuyDate() {
		return this.carBuyDate;
	}

	public void setCarBuyDate(Date carBuyDate) {
		this.carBuyDate = carBuyDate;
	}

	public String getCarRelationPerson() {
		return this.carRelationPerson;
	}

	public void setCarRelationPerson(String carRelationPerson) {
		this.carRelationPerson = carRelationPerson;
	}

	public String getCtypeName() {
		return this.ctypeName;
	}

	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}

	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomTel1() {
		return this.customTel1;
	}

	public void setCustomTel1(String customTel1) {
		this.customTel1 = customTel1;
	}

	public String getCustomAddr() {
		return this.customAddr;
	}

	public void setCustomAddr(String customAddr) {
		this.customAddr = customAddr;
	}

	public String getCustomSex() {
		return this.customSex;
	}

	public void setCustomSex(String customSex) {
		this.customSex = customSex;
	}

	public Integer getReceptionDistance() {
		return this.receptionDistance;
	}

	public void setReceptionDistance(Integer receptionDistance) {
		this.receptionDistance = receptionDistance;
	}

	public String getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

	public Timestamp getInterDate() {
		return this.interDate;
	}

	public void setInterDate(Timestamp interDate) {
		this.interDate = interDate;
	}

	public String getPropRepPer() {
		return this.propRepPer;
	}

	public void setPropRepPer(String propRepPer) {
		this.propRepPer = propRepPer;
	}

	public String getPropTel() {
		return this.propTel;
	}

	public void setPropTel(String propTel) {
		this.propTel = propTel;
	}

	public String getReceptor() {
		return this.receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public Timestamp getPreclrTime() {
		return this.preclrTime;
	}

	public void setPreclrTime(Timestamp preclrTime) {
		this.preclrTime = preclrTime;
	}

	public Double getPreclrRealAmount() {
		return this.preclrRealAmount;
	}

	public void setPreclrRealAmount(Double preclrRealAmount) {
		this.preclrRealAmount = preclrRealAmount;
	}

	public String getPareaName() {
		return this.pareaName;
	}

	public void setPareaName(String pareaName) {
		this.pareaName = pareaName;
	}

	public String getColorName() {
		return this.colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getReptName() {
		return this.reptName;
	}

	public void setReptName(String reptName) {
		this.reptName = reptName;
	}

	public String getSatisfaction() {
		return this.satisfaction;
	}

	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Date getReturnVisitDate() {
		return this.returnVisitDate;
	}

	public void setReturnVisitDate(Date returnVisitDate) {
		this.returnVisitDate = returnVisitDate;
	}

	public String getHandlePerson() {
		return this.handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getReturnVisitMembers() {
		return this.returnVisitMembers;
	}

	public void setReturnVisitMembers(String returnVisitMembers) {
		this.returnVisitMembers = returnVisitMembers;
	}

	public String getCallSituation() {
		return this.callSituation;
	}

	public void setCallSituation(String callSituation) {
		this.callSituation = callSituation;
	}

	public String getReciptReturnvisit() {
		return this.reciptReturnvisit;
	}

	public void setReciptReturnvisit(String reciptReturnvisit) {
		this.reciptReturnvisit = reciptReturnvisit;
	}

	public String getHandleResult() {
		return this.handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getComplaintContent() {
		return this.complaintContent;
	}

	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent;
	}

	public String getHandleProgram() {
		return this.handleProgram;
	}

	public void setHandleProgram(String handleProgram) {
		this.handleProgram = handleProgram;
	}

	public String getComplaintType() {
		return this.complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	public String getComplaintDegree() {
		return this.complaintDegree;
	}

	public void setComplaintDegree(String complaintDegree) {
		this.complaintDegree = complaintDegree;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VTrackRecordId))
			return false;
		VTrackRecordId castOther = (VTrackRecordId) other;

		return ((this.getCarLicense() == castOther.getCarLicense()) || (this
				.getCarLicense() != null
				&& castOther.getCarLicense() != null && this.getCarLicense()
				.equals(castOther.getCarLicense())))
				&& ((this.getCarCstlName() == castOther.getCarCstlName()) || (this
						.getCarCstlName() != null
						&& castOther.getCarCstlName() != null && this
						.getCarCstlName().equals(castOther.getCarCstlName())))
				&& ((this.getCarVan() == castOther.getCarVan()) || (this
						.getCarVan() != null
						&& castOther.getCarVan() != null && this.getCarVan()
						.equals(castOther.getCarVan())))
				&& ((this.getCarBuyDate() == castOther.getCarBuyDate()) || (this
						.getCarBuyDate() != null
						&& castOther.getCarBuyDate() != null && this
						.getCarBuyDate().equals(castOther.getCarBuyDate())))
				&& ((this.getCarRelationPerson() == castOther
						.getCarRelationPerson()) || (this
						.getCarRelationPerson() != null
						&& castOther.getCarRelationPerson() != null && this
						.getCarRelationPerson().equals(
								castOther.getCarRelationPerson())))
				&& ((this.getCtypeName() == castOther.getCtypeName()) || (this
						.getCtypeName() != null
						&& castOther.getCtypeName() != null && this
						.getCtypeName().equals(castOther.getCtypeName())))
				&& ((this.getCustomName() == castOther.getCustomName()) || (this
						.getCustomName() != null
						&& castOther.getCustomName() != null && this
						.getCustomName().equals(castOther.getCustomName())))
				&& ((this.getCustomTel1() == castOther.getCustomTel1()) || (this
						.getCustomTel1() != null
						&& castOther.getCustomTel1() != null && this
						.getCustomTel1().equals(castOther.getCustomTel1())))
				&& ((this.getCustomAddr() == castOther.getCustomAddr()) || (this
						.getCustomAddr() != null
						&& castOther.getCustomAddr() != null && this
						.getCustomAddr().equals(castOther.getCustomAddr())))
				&& ((this.getCustomSex() == castOther.getCustomSex()) || (this
						.getCustomSex() != null
						&& castOther.getCustomSex() != null && this
						.getCustomSex().equals(castOther.getCustomSex())))
				&& ((this.getReceptionDistance() == castOther
						.getReceptionDistance()) || (this
						.getReceptionDistance() != null
						&& castOther.getReceptionDistance() != null && this
						.getReceptionDistance().equals(
								castOther.getReceptionDistance())))
				&& ((this.getReceptionId() == castOther.getReceptionId()) || (this
						.getReceptionId() != null
						&& castOther.getReceptionId() != null && this
						.getReceptionId().equals(castOther.getReceptionId())))
				&& ((this.getInterDate() == castOther.getInterDate()) || (this
						.getInterDate() != null
						&& castOther.getInterDate() != null && this
						.getInterDate().equals(castOther.getInterDate())))
				&& ((this.getPropRepPer() == castOther.getPropRepPer()) || (this
						.getPropRepPer() != null
						&& castOther.getPropRepPer() != null && this
						.getPropRepPer().equals(castOther.getPropRepPer())))
				&& ((this.getPropTel() == castOther.getPropTel()) || (this
						.getPropTel() != null
						&& castOther.getPropTel() != null && this.getPropTel()
						.equals(castOther.getPropTel())))
				&& ((this.getReceptor() == castOther.getReceptor()) || (this
						.getReceptor() != null
						&& castOther.getReceptor() != null && this
						.getReceptor().equals(castOther.getReceptor())))
				&& ((this.getPreclrTime() == castOther.getPreclrTime()) || (this
						.getPreclrTime() != null
						&& castOther.getPreclrTime() != null && this
						.getPreclrTime().equals(castOther.getPreclrTime())))
				&& ((this.getPreclrRealAmount() == castOther
						.getPreclrRealAmount()) || (this.getPreclrRealAmount() != null
						&& castOther.getPreclrRealAmount() != null && this
						.getPreclrRealAmount().equals(
								castOther.getPreclrRealAmount())))
				&& ((this.getPareaName() == castOther.getPareaName()) || (this
						.getPareaName() != null
						&& castOther.getPareaName() != null && this
						.getPareaName().equals(castOther.getPareaName())))
				&& ((this.getColorName() == castOther.getColorName()) || (this
						.getColorName() != null
						&& castOther.getColorName() != null && this
						.getColorName().equals(castOther.getColorName())))
				&& ((this.getReptName() == castOther.getReptName()) || (this
						.getReptName() != null
						&& castOther.getReptName() != null && this
						.getReptName().equals(castOther.getReptName())))
				&& ((this.getSatisfaction() == castOther.getSatisfaction()) || (this
						.getSatisfaction() != null
						&& castOther.getSatisfaction() != null && this
						.getSatisfaction().equals(castOther.getSatisfaction())))
				&& ((this.getReturnVisitDate() == castOther
						.getReturnVisitDate()) || (this.getReturnVisitDate() != null
						&& castOther.getReturnVisitDate() != null && this
						.getReturnVisitDate().equals(
								castOther.getReturnVisitDate())))
				&& ((this.getHandlePerson() == castOther.getHandlePerson()) || (this
						.getHandlePerson() != null
						&& castOther.getHandlePerson() != null && this
						.getHandlePerson().equals(castOther.getHandlePerson())))
				&& ((this.getReturnVisitMembers() == castOther
						.getReturnVisitMembers()) || (this
						.getReturnVisitMembers() != null
						&& castOther.getReturnVisitMembers() != null && this
						.getReturnVisitMembers().equals(
								castOther.getReturnVisitMembers())))
				&& ((this.getCallSituation() == castOther.getCallSituation()) || (this
						.getCallSituation() != null
						&& castOther.getCallSituation() != null && this
						.getCallSituation()
						.equals(castOther.getCallSituation())))
				&& ((this.getReciptReturnvisit() == castOther
						.getReciptReturnvisit()) || (this
						.getReciptReturnvisit() != null
						&& castOther.getReciptReturnvisit() != null && this
						.getReciptReturnvisit().equals(
								castOther.getReciptReturnvisit())))
				&& ((this.getHandleResult() == castOther.getHandleResult()) || (this
						.getHandleResult() != null
						&& castOther.getHandleResult() != null && this
						.getHandleResult().equals(castOther.getHandleResult())))
				&& ((this.getMemo() == castOther.getMemo()) || (this.getMemo() != null
						&& castOther.getMemo() != null && this.getMemo()
						.equals(castOther.getMemo())))
				&& ((this.getComplaintContent() == castOther
						.getComplaintContent()) || (this.getComplaintContent() != null
						&& castOther.getComplaintContent() != null && this
						.getComplaintContent().equals(
								castOther.getComplaintContent())))
				&& ((this.getHandleProgram() == castOther.getHandleProgram()) || (this
						.getHandleProgram() != null
						&& castOther.getHandleProgram() != null && this
						.getHandleProgram()
						.equals(castOther.getHandleProgram())))
				&& ((this.getComplaintType() == castOther.getComplaintType()) || (this
						.getComplaintType() != null
						&& castOther.getComplaintType() != null && this
						.getComplaintType()
						.equals(castOther.getComplaintType())))
				&& ((this.getComplaintDegree() == castOther
						.getComplaintDegree()) || (this.getComplaintDegree() != null
						&& castOther.getComplaintDegree() != null && this
						.getComplaintDegree().equals(
								castOther.getComplaintDegree())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCarLicense() == null ? 0 : this.getCarLicense()
						.hashCode());
		result = 37
				* result
				+ (getCarCstlName() == null ? 0 : this.getCarCstlName()
						.hashCode());
		result = 37 * result
				+ (getCarVan() == null ? 0 : this.getCarVan().hashCode());
		result = 37
				* result
				+ (getCarBuyDate() == null ? 0 : this.getCarBuyDate()
						.hashCode());
		result = 37
				* result
				+ (getCarRelationPerson() == null ? 0 : this
						.getCarRelationPerson().hashCode());
		result = 37 * result
				+ (getCtypeName() == null ? 0 : this.getCtypeName().hashCode());
		result = 37
				* result
				+ (getCustomName() == null ? 0 : this.getCustomName()
						.hashCode());
		result = 37
				* result
				+ (getCustomTel1() == null ? 0 : this.getCustomTel1()
						.hashCode());
		result = 37
				* result
				+ (getCustomAddr() == null ? 0 : this.getCustomAddr()
						.hashCode());
		result = 37 * result
				+ (getCustomSex() == null ? 0 : this.getCustomSex().hashCode());
		result = 37
				* result
				+ (getReceptionDistance() == null ? 0 : this
						.getReceptionDistance().hashCode());
		result = 37
				* result
				+ (getReceptionId() == null ? 0 : this.getReceptionId()
						.hashCode());
		result = 37 * result
				+ (getInterDate() == null ? 0 : this.getInterDate().hashCode());
		result = 37
				* result
				+ (getPropRepPer() == null ? 0 : this.getPropRepPer()
						.hashCode());
		result = 37 * result
				+ (getPropTel() == null ? 0 : this.getPropTel().hashCode());
		result = 37 * result
				+ (getReceptor() == null ? 0 : this.getReceptor().hashCode());
		result = 37
				* result
				+ (getPreclrTime() == null ? 0 : this.getPreclrTime()
						.hashCode());
		result = 37
				* result
				+ (getPreclrRealAmount() == null ? 0 : this
						.getPreclrRealAmount().hashCode());
		result = 37 * result
				+ (getPareaName() == null ? 0 : this.getPareaName().hashCode());
		result = 37 * result
				+ (getColorName() == null ? 0 : this.getColorName().hashCode());
		result = 37 * result
				+ (getReptName() == null ? 0 : this.getReptName().hashCode());
		result = 37
				* result
				+ (getSatisfaction() == null ? 0 : this.getSatisfaction()
						.hashCode());
		result = 37
				* result
				+ (getReturnVisitDate() == null ? 0 : this.getReturnVisitDate()
						.hashCode());
		result = 37
				* result
				+ (getHandlePerson() == null ? 0 : this.getHandlePerson()
						.hashCode());
		result = 37
				* result
				+ (getReturnVisitMembers() == null ? 0 : this
						.getReturnVisitMembers().hashCode());
		result = 37
				* result
				+ (getCallSituation() == null ? 0 : this.getCallSituation()
						.hashCode());
		result = 37
				* result
				+ (getReciptReturnvisit() == null ? 0 : this
						.getReciptReturnvisit().hashCode());
		result = 37
				* result
				+ (getHandleResult() == null ? 0 : this.getHandleResult()
						.hashCode());
		result = 37 * result
				+ (getMemo() == null ? 0 : this.getMemo().hashCode());
		result = 37
				* result
				+ (getComplaintContent() == null ? 0 : this
						.getComplaintContent().hashCode());
		result = 37
				* result
				+ (getHandleProgram() == null ? 0 : this.getHandleProgram()
						.hashCode());
		result = 37
				* result
				+ (getComplaintType() == null ? 0 : this.getComplaintType()
						.hashCode());
		result = 37
				* result
				+ (getComplaintDegree() == null ? 0 : this.getComplaintDegree()
						.hashCode());
		return result;
	}

}