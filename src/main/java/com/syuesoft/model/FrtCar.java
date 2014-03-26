package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FrtCar entity. @author MyEclipse Persistence Tools
 */

public class FrtCar extends BaseBean {

	// Fields

	private String carId;
	private FrtCustom frtCustom;
	private BasCarStyle basCarStyle;
	private BasCarType basCarType;
	private BasCarColor basCarColor;
	private Short allowCarTypeId;
	private Short slsId;
	private Short relcampId;
	private Short busRelcampId;
	private String carLicense;
	private String carVin;
	private String carMotorId;
	private Date carBuyDate;
	private Date carLicenseDate;
	private String carRealationLisence;
	private Date carBasinsuranceDate;
	private Date carBusinsuranceDate;
	private Date carAnnualDate;
	private Date carExaminedDate;
	private Date twoDimensionDate;
	private Integer carMaintInterva;
	private Date carFstInsuranceDate;
	private Date createDate;
	private String carRecord;
	private String carRealationSex;
	private Date carBirthday;
	private String carRealationIdentifyCd;
	private String vipId;
	private Date carLastRepairDate;
	private Integer carLastRepairDistance;
	private Integer carRepairCnt;
	private Date carLastMaintDate;
	private Integer carLastMaintDistance;
	private Integer carMaintCnt;
	private Integer carNextMaintDistance;
	private Date carNextMaintDate;
	private Integer carDistancePerDay;
	private Integer notIntoTheStoreDays;
	private String carRemark;
	private String receptionRemark;
	private String carFlag;
	private String carRelationPerson;
	private String carRelationTel1;
	private String carRelationTel2;
	private String carRelationLevel;
	private String carRealationEmail;
	private String carClass;
	private String carPostalCode;
	private Short carAcceptBack;
	private Date carAcceptTime;
	private Set fbkCarDcnames = new HashSet(0);
	private Set basVipInfors = new HashSet(0);
	private Set fbkCollects = new HashSet(0);
	private Set stSellPercharges = new HashSet(0);
	private Set frtResvAdvices = new HashSet(0);
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public FrtCar() {
	}

	/** minimal constructor */
	public FrtCar(String carId) {
		this.carId = carId;
	}

	/** full constructor */
	public FrtCar(String carId, FrtCustom frtCustom, BasCarStyle basCarStyle, BasCarType basCarType, BasCarColor basCarColor, Short allowCarTypeId, Short slsId, Short relcampId, String carLicense,
			String carVin, String carMotorId, Date carBuyDate, Date carLicenseDate, String carRealationLisence, Date carBasinsuranceDate, Date carBusinsuranceDate, Date carAnnualDate,
			Date carExaminedDate, Date twoDimensionDate, Integer carMaintInterva, Date carFstInsuranceDate, Date createDate, String carRecord, String carRealationSex, Date carBirthday,
			String carRealationIdentifyCd, String vipId, Date carLastRepairDate, Integer carLastRepairDistance, Integer carRepairCnt, Date carLastMaintDate, Integer carLastMaintDistance,
			Integer carMaintCnt, Integer carNextMaintDistance, Date carNextMaintDate, Integer carDistancePerDay, Integer notIntoTheStoreDays, String carRemark, String receptionRemark, String carFlag,
			String carRelationPerson, String carRelationTel1, String carRelationTel2, String carRelationLevel, String carRealationEmail, Set fbkCarDcnames, Set basVipInfors, Set fbkCollects,
			Set stSellPercharges) {
		this.carId = carId;
		this.frtCustom = frtCustom;
		this.basCarStyle = basCarStyle;
		this.basCarType = basCarType;
		this.basCarColor = basCarColor;
		this.allowCarTypeId = allowCarTypeId;
		this.slsId = slsId;
		this.relcampId = relcampId;
		this.carLicense = carLicense;
		this.carVin = carVin;
		this.carMotorId = carMotorId;
		this.carBuyDate = carBuyDate;
		this.carLicenseDate = carLicenseDate;
		this.carRealationLisence = carRealationLisence;
		this.carBasinsuranceDate = carBasinsuranceDate;
		this.carBusinsuranceDate = carBusinsuranceDate;
		this.carAnnualDate = carAnnualDate;
		this.carExaminedDate = carExaminedDate;
		this.twoDimensionDate = twoDimensionDate;
		this.carMaintInterva = carMaintInterva;
		this.carFstInsuranceDate = carFstInsuranceDate;
		this.createDate = createDate;
		this.carRecord = carRecord;
		this.carRealationSex = carRealationSex;
		this.carBirthday = carBirthday;
		this.carRealationIdentifyCd = carRealationIdentifyCd;
		this.vipId = vipId;
		this.carLastRepairDate = carLastRepairDate;
		this.carLastRepairDistance = carLastRepairDistance;
		this.carRepairCnt = carRepairCnt;
		this.carLastMaintDate = carLastMaintDate;
		this.carLastMaintDistance = carLastMaintDistance;
		this.carMaintCnt = carMaintCnt;
		this.carNextMaintDistance = carNextMaintDistance;
		this.carNextMaintDate = carNextMaintDate;
		this.carDistancePerDay = carDistancePerDay;
		this.notIntoTheStoreDays = notIntoTheStoreDays;
		this.carRemark = carRemark;
		this.receptionRemark = receptionRemark;
		this.carFlag = carFlag;
		this.carRelationPerson = carRelationPerson;
		this.carRelationTel1 = carRelationTel1;
		this.carRelationTel2 = carRelationTel2;
		this.carRelationLevel = carRelationLevel;
		this.carRealationEmail = carRealationEmail;
		this.fbkCarDcnames = fbkCarDcnames;
		this.basVipInfors = basVipInfors;
		this.fbkCollects = fbkCollects;
		this.stSellPercharges = stSellPercharges;
	}

	// Property accessors
     
	public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId;
	}

	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public FrtCustom getFrtCustom() {
		return this.frtCustom;
	}

	public void setFrtCustom(FrtCustom frtCustom) {
		this.frtCustom = frtCustom;
	}

	public BasCarStyle getBasCarStyle() {
		return this.basCarStyle;
	}

	public void setBasCarStyle(BasCarStyle basCarStyle) {
		this.basCarStyle = basCarStyle;
	}

	public BasCarType getBasCarType() {
		return this.basCarType;
	}

	public void setBasCarType(BasCarType basCarType) {
		this.basCarType = basCarType;
	}

	public BasCarColor getBasCarColor() {
		return this.basCarColor;
	}

	public void setBasCarColor(BasCarColor basCarColor) {
		this.basCarColor = basCarColor;
	}

	public Short getAllowCarTypeId() {
		return this.allowCarTypeId;
	}

	public void setAllowCarTypeId(Short allowCarTypeId) {
		this.allowCarTypeId = allowCarTypeId;
	}

	public Short getSlsId() {
		return this.slsId;
	}

	public void setSlsId(Short slsId) {
		this.slsId = slsId;
	}

	public Short getRelcampId() {
		return this.relcampId;
	}

	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}

	public String getCarLicense() {
		return this.carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getCarVin() {
		return this.carVin;
	}

	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}

	public String getCarMotorId() {
		return this.carMotorId;
	}

	public void setCarMotorId(String carMotorId) {
		this.carMotorId = carMotorId;
	}

	public Date getCarBuyDate() {
		return this.carBuyDate;
	}

	public void setCarBuyDate(Date carBuyDate) {
		this.carBuyDate = carBuyDate;
	}

	public Date getCarLicenseDate() {
		return this.carLicenseDate;
	}

	public void setCarLicenseDate(Date carLicenseDate) {
		this.carLicenseDate = carLicenseDate;
	}

	public String getCarRealationLisence() {
		return this.carRealationLisence;
	}

	public void setCarRealationLisence(String carRealationLisence) {
		this.carRealationLisence = carRealationLisence;
	}

	public Date getCarBasinsuranceDate() {
		return this.carBasinsuranceDate;
	}

	public void setCarBasinsuranceDate(Date carBasinsuranceDate) {
		this.carBasinsuranceDate = carBasinsuranceDate;
	}

	public Date getCarBusinsuranceDate() {
		return this.carBusinsuranceDate;
	}

	public void setCarBusinsuranceDate(Date carBusinsuranceDate) {
		this.carBusinsuranceDate = carBusinsuranceDate;
	}

	public Date getCarAnnualDate() {
		return this.carAnnualDate;
	}

	public void setCarAnnualDate(Date carAnnualDate) {
		this.carAnnualDate = carAnnualDate;
	}

	public Date getCarExaminedDate() {
		return this.carExaminedDate;
	}

	public void setCarExaminedDate(Date carExaminedDate) {
		this.carExaminedDate = carExaminedDate;
	}

	public Date getTwoDimensionDate() {
		return this.twoDimensionDate;
	}

	public void setTwoDimensionDate(Date twoDimensionDate) {
		this.twoDimensionDate = twoDimensionDate;
	}

	public Integer getCarMaintInterva() {
		return this.carMaintInterva;
	}

	public void setCarMaintInterva(Integer carMaintInterva) {
		this.carMaintInterva = carMaintInterva;
	}

	public Date getCarFstInsuranceDate() {
		return this.carFstInsuranceDate;
	}

	public void setCarFstInsuranceDate(Date carFstInsuranceDate) {
		this.carFstInsuranceDate = carFstInsuranceDate;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCarRecord() {
		return this.carRecord;
	}

	public void setCarRecord(String carRecord) {
		this.carRecord = carRecord;
	}

	public String getCarRealationSex() {
		return this.carRealationSex;
	}

	public void setCarRealationSex(String carRealationSex) {
		this.carRealationSex = carRealationSex;
	}

	public Date getCarBirthday() {
		return this.carBirthday;
	}

	public void setCarBirthday(Date carBirthday) {
		this.carBirthday = carBirthday;
	}

	public String getCarRealationIdentifyCd() {
		return this.carRealationIdentifyCd;
	}

	public void setCarRealationIdentifyCd(String carRealationIdentifyCd) {
		this.carRealationIdentifyCd = carRealationIdentifyCd;
	}

	

	public Date getCarLastRepairDate() {
		return this.carLastRepairDate;
	}

	public void setCarLastRepairDate(Date carLastRepairDate) {
		this.carLastRepairDate = carLastRepairDate;
	}

	public Integer getCarLastRepairDistance() {
		return this.carLastRepairDistance;
	}

	public void setCarLastRepairDistance(Integer carLastRepairDistance) {
		this.carLastRepairDistance = carLastRepairDistance;
	}

	public Integer getCarRepairCnt() {
		return this.carRepairCnt;
	}

	public void setCarRepairCnt(Integer carRepairCnt) {
		this.carRepairCnt = carRepairCnt;
	}

	public Date getCarLastMaintDate() {
		return this.carLastMaintDate;
	}

	public void setCarLastMaintDate(Date carLastMaintDate) {
		this.carLastMaintDate = carLastMaintDate;
	}

	public Integer getCarLastMaintDistance() {
		return this.carLastMaintDistance;
	}

	public void setCarLastMaintDistance(Integer carLastMaintDistance) {
		this.carLastMaintDistance = carLastMaintDistance;
	}

	public Integer getCarMaintCnt() {
		return this.carMaintCnt;
	}

	public void setCarMaintCnt(Integer carMaintCnt) {
		this.carMaintCnt = carMaintCnt;
	}

	public Integer getCarNextMaintDistance() {
		return this.carNextMaintDistance;
	}

	public void setCarNextMaintDistance(Integer carNextMaintDistance) {
		this.carNextMaintDistance = carNextMaintDistance;
	}

	public Date getCarNextMaintDate() {
		return this.carNextMaintDate;
	}

	public void setCarNextMaintDate(Date carNextMaintDate) {
		this.carNextMaintDate = carNextMaintDate;
	}

	public Integer getCarDistancePerDay() {
		return this.carDistancePerDay;
	}

	public void setCarDistancePerDay(Integer carDistancePerDay) {
		this.carDistancePerDay = carDistancePerDay;
	}

	public Integer getNotIntoTheStoreDays() {
		return this.notIntoTheStoreDays;
	}

	public void setNotIntoTheStoreDays(Integer notIntoTheStoreDays) {
		this.notIntoTheStoreDays = notIntoTheStoreDays;
	}

	public String getCarRemark() {
		return this.carRemark;
	}

	public void setCarRemark(String carRemark) {
		this.carRemark = carRemark;
	}

	public String getReceptionRemark() {
		return this.receptionRemark;
	}

	public void setReceptionRemark(String receptionRemark) {
		this.receptionRemark = receptionRemark;
	}

	public String getCarFlag() {
		return this.carFlag;
	}

	public void setCarFlag(String carFlag) {
		this.carFlag = carFlag;
	}

	public String getCarRelationPerson() {
		return this.carRelationPerson;
	}

	public void setCarRelationPerson(String carRelationPerson) {
		this.carRelationPerson = carRelationPerson;
	}

	public String getCarRelationTel1() {
		return this.carRelationTel1;
	}

	public void setCarRelationTel1(String carRelationTel1) {
		this.carRelationTel1 = carRelationTel1;
	}

	public String getCarRelationTel2() {
		return this.carRelationTel2;
	}

	public void setCarRelationTel2(String carRelationTel2) {
		this.carRelationTel2 = carRelationTel2;
	}

	public String getCarRelationLevel() {
		return this.carRelationLevel;
	}

	public void setCarRelationLevel(String carRelationLevel) {
		this.carRelationLevel = carRelationLevel;
	}

	public String getCarRealationEmail() {
		return this.carRealationEmail;
	}

	public void setCarRealationEmail(String carRealationEmail) {
		this.carRealationEmail = carRealationEmail;
	}

	public Set getFbkCarDcnames() {
		return this.fbkCarDcnames;
	}

	public void setFbkCarDcnames(Set fbkCarDcnames) {
		this.fbkCarDcnames = fbkCarDcnames;
	}

	public Set getBasVipInfors() {
		return this.basVipInfors;
	}

	public void setBasVipInfors(Set basVipInfors) {
		this.basVipInfors = basVipInfors;
	}

	public Set getFbkCollects() {
		return this.fbkCollects;
	}

	public void setFbkCollects(Set fbkCollects) {
		this.fbkCollects = fbkCollects;
	}

	public Set getStSellPercharges() {
		return this.stSellPercharges;
	}

	public void setStSellPercharges(Set stSellPercharges) {
		this.stSellPercharges = stSellPercharges;
	}

	public Set getFrtResvAdvices() {
		return frtResvAdvices;
	}

	public void setFrtResvAdvices(Set frtResvAdvices) {
		this.frtResvAdvices = frtResvAdvices;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public String getCarPostalCode() {
		return carPostalCode;
	}

	public void setCarPostalCode(String carPostalCode) {
		this.carPostalCode = carPostalCode;
	}

	public Short getCarAcceptBack() {
		return carAcceptBack;
	}

	public void setCarAcceptBack(Short carAcceptBack) {
		this.carAcceptBack = carAcceptBack;
	}

	public Date getCarAcceptTime() {
		return carAcceptTime;
	}

	public void setCarAcceptTime(Date carAcceptTime) {
		this.carAcceptTime = carAcceptTime;
	}

	public Short getBusRelcampId() {
		return busRelcampId;
	}

	public void setBusRelcampId(Short busRelcampId) {
		this.busRelcampId = busRelcampId;
	}

	
}