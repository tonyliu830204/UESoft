package com.syuesoft.sell.repayManage.vo;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.model.XsSellCarReserve;

public class SellCoverVo  extends BaseBean implements Serializable {
	 
		/**
		 * 售后回访
		 */
		private Boolean is3D; //是否3D显示
		private Boolean flag;
		private Integer enterpriseId;
		private Integer consultId;
		private Integer xsCarSelId;// 车辆销售编号
		
		private String consultActualDate;// 实际回访日期
		private String consultActualDate2;// 实际回访日期
		private String consultActualDate3;// 实际回访日期 单击行时所用属性名
		private Integer consulTRate;// 回访进度
		private String  consulTRateN;
		private Integer consultDegree;// 满意度
		private String consultDegreeType;// 满意度ming
		private String  consultCallStateN;
		private Integer consultCallState;// 通话情况
		private Integer consulTRate2;// 回访进度
		private Double travelCourse;// 行驶里程
		private String consultSuggest;// 领导意见
		private String disposeDate;// 处理日期
		private String disposeResult;// 处理结果
		private String remark;// 备注
		private String isinsurance;//是否办理保险
		private String q;
		private String selectedField;
		private Double rate;
		private Integer sumCount;
		private Integer planDate;//计划跟踪
		private Integer actualDate; //实际跟踪
		private Integer planIn;//计划内跟踪

		private Integer planOut;//计划外跟踪
		private Integer nottrack;//未跟踪
		private Double timely;//跟踪及时率
		
		private String consultPlanDate;// 计划回访日期
		private String verySatisfaction ; 	//很满意
		private String satisfaction ;		//满意
		private String ordinary;			//一般
		private String veryNotSatisfaction; //很不满意
		private String notSatisfaction;		//不满意
		private String not;					//无
		private String satisfactionName;	//满意度名称
		
		
		public String getSatisfactionName() {
			return satisfactionName;
		}
		public void setSatisfactionName(String satisfactionName) {
			this.satisfactionName = satisfactionName;
		}
		public String getConsultActualDate3() {
			return consultActualDate3;
		}
		public void setConsultActualDate3(String consultActualDate3) {
			this.consultActualDate3 = consultActualDate3;
		}
		public Boolean getIs3D() {
			return is3D;
		}
		public void setIs3D(Boolean is3d) {
			is3D = is3d;
		}
		public String getVerySatisfaction() {
			return verySatisfaction;
		}
		public void setVerySatisfaction(String verySatisfaction) {
			this.verySatisfaction = verySatisfaction;
		}
		public String getSatisfaction() {
			return satisfaction;
		}
		public void setSatisfaction(String satisfaction) {
			this.satisfaction = satisfaction;
		}
		public String getOrdinary() {
			return ordinary;
		}
		public void setOrdinary(String ordinary) {
			this.ordinary = ordinary;
		}
		public String getVeryNotSatisfaction() {
			return veryNotSatisfaction;
		}
		public void setVeryNotSatisfaction(String veryNotSatisfaction) {
			this.veryNotSatisfaction = veryNotSatisfaction;
		}
		public String getNotSatisfaction() {
			return notSatisfaction;
		}
		public void setNotSatisfaction(String notSatisfaction) {
			this.notSatisfaction = notSatisfaction;
		}
		public String getNot() {
			return not;
		}
		public void setNot(String not) {
			this.not = not;
		}
		public Boolean getFlag() {
			return flag;
		}
		public void setFlag(Boolean flag) {
			this.flag = flag;
		}
		/**
		 * 车辆预订/车辆调配表
		 */
		private Integer reserveId;
		private String reserveCode;
		private Integer customId;
		private Integer xsCarId;
		public String getSelectedField() {
			return selectedField;
		}
		public void setSelectedField(String selectedField) {
			this.selectedField = selectedField;
		}
		private Integer stfIdPerson;
		private String stfIdPersonName;
		private String reserveDete;
		private Integer carBrand;
		private String carBrandName;//车辆品牌
		private Integer carModel;
		private String carModelName;
		private Integer carColor;
		private String carColorName;
		private Integer carTrim;
		private Integer carOutputVolume;
		private Integer limitLoadNum;
		private String pactCode;//合同号
		private Integer customProperty;
		private Double paymentMoney;
		private Integer payWay;
		private Double sellingprice;
		private Integer examine;
		private Integer orderState;
		private String predictTakeDate;
		private Integer stfId;
		private String stfName;//业务员
		private String xsDistributorCode;
		private Integer level;
		private Double firstPayMoney;
		private Integer loanBank;
		private Double loanLimitMoney;
		private Double loanLimitYear;
		private String vinCode;
		private String customOpinion;
		private Double shingleMoney;
		private Double decorateMoney;
		private Integer allocateState;
		private Integer delState;
		private String remark1;
		private String carCode;
		
		private String carMotorNumber;//发动机号
		
		private String carLicensePlate;//车牌照
		private String carSellState;//车辆销售状态
		private String carSellStateN;//车辆销售状态名称
		
		
		
		/**
		 * 车辆销售信息（附表一）
		 */
		private String xsCarSelData;// 销售日期
		private String xsCarSelData2;// 销售日期
		private Double xsCarSelTransactionMoney;// 销售价格
		private String xsCarGiveUp;
		private String xsCustomReceiptor;// 提车人
		private Integer xsCarSelType;
		private Double applySum;
		private Double costSum;
		private String xsCarSelRemark;
		private Integer audit;
		private Integer outId;
		private Integer auditPerson;
		private String auditDate;// 上报日期
		private String auditDate2;
		private String sell_code;
	
		
//客户信息
		private String xsCustomCode;
		private Integer xsCustomArea;
		private Integer xsCustomDeal;
		private Integer xsCustomSource;
		private Integer xsCustomTrade;//所从事行业
		private String customTrade;
		public String getCustomTrade() {
			return customTrade;
		}
		public void setCustomTrade(String customTrade) {
			this.customTrade = customTrade;
		}
		private String xsCustomName;
		private String xsCustomNumber;
		private Integer xsCustomHideLevel;
		private String xsCustomOther;
		private String xsCustomAddress;
		private String xsCustomZipcode;
		private Date xsCustomBirthday;
		
		private String xsCustomCredentials;
		private String xsCustomTelephone;
		private String xsCustomPhone;//固话
		private Integer xsCustomIncome;
		private String xsCustomIncomeN;//收入
		private Integer xsCustomApplication;//用途
		private String xsCustomApplicationN;//用途名称
		private Integer xsCustomContrastModel;
		private Integer xsCustomSex;
		private String  sexName;
		private Integer xsCustomAfter;
		private Integer xsCustomOccupation;//从事职业
		private String  xsCustomOccupationN;//从事职业名称
		private Integer xsCustomOtherType;
		private Integer xsCustomReason;
		private Integer xsCustomProperty;
		private String xsCustomCompany;
		private String customPropertyN;
		public String getCustomPropertyN() {
			return customPropertyN;
		}
		public void setCustomPropertyN(String customPropertyN) {
			this.customPropertyN = customPropertyN;
		}
		private Integer xsCustomDiploma;//教育程度
		private String  xsCustomDiplomaN;
		private Integer tracingState;
		
		private Integer deptId;
		
		
		
		
		
		public String getConsultActualDate2() {
			return consultActualDate2;
		}
		public void setConsultActualDate2(String consultActualDate2) {
			this.consultActualDate2 = consultActualDate2;
		}
		public Integer getDeptId() {
			return deptId;
		}
		public void setDeptId(Integer deptId) {
			this.deptId = deptId;
		}
		private String sort;
		private String order;
		private int rows;
		private int page;
		//回访调查表
		private Integer contentId;
		private Integer projectId;
		private String projectName;
		private Integer projectEvaluate;
		private String projectEvaluateN;
		private Integer projectScore;
		private Float projectScoreN;
		private String remarks;
		private String addResearchList;
		private String veryGood;
		private String good;
		private String yiBan;
		private String notGood;
		private String VeryNotGood;
		
		
		
		
		public Integer getEnterpriseId() {
			return enterpriseId;
		}
		public void setEnterpriseId(Integer enterpriseId) {
			this.enterpriseId = enterpriseId;
		}
		public String getVeryGood() {
			return veryGood;
		}
		public void setVeryGood(String veryGood) {
			this.veryGood = veryGood;
		}
		public String getGood() {
			return good;
		}
		public void setGood(String good) {
			this.good = good;
		}
		public String getYiBan() {
			return yiBan;
		}
		public void setYiBan(String yiBan) {
			this.yiBan = yiBan;
		}
		public String getNotGood() {
			return notGood;
		}
		public void setNotGood(String notGood) {
			this.notGood = notGood;
		}
		public String getVeryNotGood() {
			return VeryNotGood;
		}
		public void setVeryNotGood(String veryNotGood) {
			VeryNotGood = veryNotGood;
		}
		public String getQ() {
			return q;
		}
		public void setQ(String q) {
			this.q = q;
		}
		public Integer getConsulTRate2() {
			return consulTRate2;
		}
		public void setConsulTRate2(Integer consulTRate2) {
			this.consulTRate2 = consulTRate2;
		}
		public String getSell_code() {
			return sell_code;
		}
		public void setSell_code(String sellCode) {
			sell_code = sellCode;
		}
		public String getIsinsurance() {
			return isinsurance;
		}
		public void setIsinsurance(String isinsurance) {
			this.isinsurance = isinsurance;
		}
		public String getProjectEvaluateN() {
			return projectEvaluateN;
		}
		public void setProjectEvaluateN(String projectEvaluateN) {
			this.projectEvaluateN = projectEvaluateN;
		}
		public Float getProjectScoreN() {
			return projectScoreN;
		}
		public void setProjectScoreN(Float projectScoreN) {
			this.projectScoreN = projectScoreN;
		}
		
		
		public String getAddResearchList() {
			return addResearchList;
		}
		public void setAddResearchList(String addResearchList) {
			this.addResearchList = addResearchList;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public Integer getContentId() {
			return contentId;
		}
		public void setContentId(Integer contentId) {
			this.contentId = contentId;
		}
		public Integer getProjectId() {
			return projectId;
		}
		public void setProjectId(Integer projectId) {
			this.projectId = projectId;
		}
		public Integer getProjectEvaluate() {
			return projectEvaluate;
		}
		public void setProjectEvaluate(Integer projectEvaluate) {
			this.projectEvaluate = projectEvaluate;
		}
		public Integer getProjectScore() {
			return projectScore;
		}
		public void setProjectScore(Integer projectScore) {
			this.projectScore = projectScore;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getConsultCallStateN() {
			return consultCallStateN;
		}
		public void setConsultCallStateN(String consultCallStateN) {
			this.consultCallStateN = consultCallStateN;
		}
		public String getAuditDate2() {
			return auditDate2;
		}
		public void setAuditDate2(String auditDate2) {
			this.auditDate2 = auditDate2;
		}
	
		public String getXsCarSelData2() {
			return xsCarSelData2;
		}
		public void setXsCarSelData2(String xsCarSelData2) {
			this.xsCarSelData2 = xsCarSelData2;
		}
		public String getConsultDegreeType() {
			return consultDegreeType;
		}
		public void setConsultDegreeType(String consultDegreeType) {
			this.consultDegreeType = consultDegreeType;
		}
		public String getConsulTRateN() {
			return consulTRateN;
		}
		public void setConsulTRateN(String consulTRateN) {
			this.consulTRateN = consulTRateN;
		}
	
		public String getCarSellState() {
			return carSellState;
		}
		public void setCarSellState(String carSellState) {
			this.carSellState = carSellState;
		}
		public String getCarSellStateN() {
			return carSellStateN;
		}
		public void setCarSellStateN(String carSellStateN) {
			this.carSellStateN = carSellStateN;
		}
		public String getCarLicensePlate() {
			return carLicensePlate;
		}
		public void setCarLicensePlate(String carLicensePlate) {
			this.carLicensePlate = carLicensePlate;
		}
		public String getXsCustomIncomeN() {
			return xsCustomIncomeN;
		}
		public void setXsCustomIncomeN(String xsCustomIncomeN) {
			this.xsCustomIncomeN = xsCustomIncomeN;
		}
		public String getSexName() {
			return sexName;
		}
		public void setSexName(String sexName) {
			this.sexName = sexName;
		}
		public String getXsCustomDiplomaN() {
			return xsCustomDiplomaN;
		}
		public void setXsCustomDiplomaN(String xsCustomDiplomaN) {
			this.xsCustomDiplomaN = xsCustomDiplomaN;
		}
		public String getXsCustomOccupationN() {
			return xsCustomOccupationN;
		}
		public void setXsCustomOccupationN(String xsCustomOccupationN) {
			this.xsCustomOccupationN = xsCustomOccupationN;
		}
		private String xsCustomCodeCard;
		public String getXsCustomCode() {
			return xsCustomCode;
		}
		public String getXsCustomApplicationN() {
			return xsCustomApplicationN;
		}
		public void setXsCustomApplicationN(String xsCustomApplicationN) {
			this.xsCustomApplicationN = xsCustomApplicationN;
		}
		public void setXsCustomCode(String xsCustomCode) {
			this.xsCustomCode = xsCustomCode;
		}
		public Integer getXsCustomArea() {
			return xsCustomArea;
		}
		public void setXsCustomArea(Integer xsCustomArea) {
			this.xsCustomArea = xsCustomArea;
		}
		public Integer getXsCustomDeal() {
			return xsCustomDeal;
		}
		public void setXsCustomDeal(Integer xsCustomDeal) {
			this.xsCustomDeal = xsCustomDeal;
		}
		public Integer getXsCustomSource() {
			return xsCustomSource;
		}
		public void setXsCustomSource(Integer xsCustomSource) {
			this.xsCustomSource = xsCustomSource;
		}
		public Integer getXsCustomTrade() {
			return xsCustomTrade;
		}
		public void setXsCustomTrade(Integer xsCustomTrade) {
			this.xsCustomTrade = xsCustomTrade;
		}
		public String getXsCustomName() {
			return xsCustomName;
		}
		public void setXsCustomName(String xsCustomName) {
			this.xsCustomName = xsCustomName;
		}
		public String getXsCustomNumber() {
			return xsCustomNumber;
		}
		public void setXsCustomNumber(String xsCustomNumber) {
			this.xsCustomNumber = xsCustomNumber;
		}
		public Integer getXsCustomHideLevel() {
			return xsCustomHideLevel;
		}
		public void setXsCustomHideLevel(Integer xsCustomHideLevel) {
			this.xsCustomHideLevel = xsCustomHideLevel;
		}
		public String getXsCustomOther() {
			return xsCustomOther;
		}
		public void setXsCustomOther(String xsCustomOther) {
			this.xsCustomOther = xsCustomOther;
		}
		public String getXsCustomAddress() {
			return xsCustomAddress;
		}
		public void setXsCustomAddress(String xsCustomAddress) {
			this.xsCustomAddress = xsCustomAddress;
		}
		public String getXsCustomZipcode() {
			return xsCustomZipcode;
		}
		public void setXsCustomZipcode(String xsCustomZipcode) {
			this.xsCustomZipcode = xsCustomZipcode;
		}
		public Date getXsCustomBirthday() {
			return xsCustomBirthday;
		}
		public void setXsCustomBirthday(Date xsCustomBirthday) {
			this.xsCustomBirthday = xsCustomBirthday;
		}
		public String getXsCustomCredentials() {
			return xsCustomCredentials;
		}
		public void setXsCustomCredentials(String xsCustomCredentials) {
			this.xsCustomCredentials = xsCustomCredentials;
		}
		public String getXsCustomTelephone() {
			return xsCustomTelephone;
		}
		public void setXsCustomTelephone(String xsCustomTelephone) {
			this.xsCustomTelephone = xsCustomTelephone;
		}
		public String getXsCustomPhone() {
			return xsCustomPhone;
		}
		public void setXsCustomPhone(String xsCustomPhone) {
			this.xsCustomPhone = xsCustomPhone;
		}
		public Integer getXsCustomIncome() {
			return xsCustomIncome;
		}
		public void setXsCustomIncome(Integer xsCustomIncome) {
			this.xsCustomIncome = xsCustomIncome;
		}
		public Integer getXsCustomApplication() {
			return xsCustomApplication;
		}
		public void setXsCustomApplication(Integer xsCustomApplication) {
			this.xsCustomApplication = xsCustomApplication;
		}
		public Integer getXsCustomContrastModel() {
			return xsCustomContrastModel;
		}
		public void setXsCustomContrastModel(Integer xsCustomContrastModel) {
			this.xsCustomContrastModel = xsCustomContrastModel;
		}
		public Integer getXsCustomSex() {
			return xsCustomSex;
		}
		public void setXsCustomSex(Integer xsCustomSex) {
			this.xsCustomSex = xsCustomSex;
		}
		public Integer getXsCustomAfter() {
			return xsCustomAfter;
		}
		public void setXsCustomAfter(Integer xsCustomAfter) {
			this.xsCustomAfter = xsCustomAfter;
		}
		public Integer getXsCustomOccupation() {
			return xsCustomOccupation;
		}
		public void setXsCustomOccupation(Integer xsCustomOccupation) {
			this.xsCustomOccupation = xsCustomOccupation;
		}
		public Integer getXsCustomOtherType() {
			return xsCustomOtherType;
		}
		public void setXsCustomOtherType(Integer xsCustomOtherType) {
			this.xsCustomOtherType = xsCustomOtherType;
		}
		public Integer getXsCustomReason() {
			return xsCustomReason;
		}
		public void setXsCustomReason(Integer xsCustomReason) {
			this.xsCustomReason = xsCustomReason;
		}
		public Integer getXsCustomProperty() {
			return xsCustomProperty;
		}
		public void setXsCustomProperty(Integer xsCustomProperty) {
			this.xsCustomProperty = xsCustomProperty;
		}
		public String getXsCustomCompany() {
			return xsCustomCompany;
		}
		public void setXsCustomCompany(String xsCustomCompany) {
			this.xsCustomCompany = xsCustomCompany;
		}
		public Integer getXsCustomDiploma() {
			return xsCustomDiploma;
		}
		public void setXsCustomDiploma(Integer xsCustomDiploma) {
			this.xsCustomDiploma = xsCustomDiploma;
		}
		public String getXsCustomCodeCard() {
			return xsCustomCodeCard;
		}
		public void setXsCustomCodeCard(String xsCustomCodeCard) {
			this.xsCustomCodeCard = xsCustomCodeCard;
		}
		public Integer getTracingState() {
			return tracingState;
		}
		public void setTracingState(Integer tracingState) {
			this.tracingState = tracingState;
		}
	
		
	
		public Integer getConsultId() {
			return consultId;
		}
		public void setConsultId(Integer consultId) {
			this.consultId = consultId;
		}
		public Integer getXsCarSelId() {
			return xsCarSelId;
		}
		public void setXsCarSelId(Integer xsCarSelId) {
			this.xsCarSelId = xsCarSelId;
		}
		public String getConsultPlanDate() {
			return consultPlanDate;
		}
		public void setConsultPlanDate(String consultPlanDate) {
			this.consultPlanDate = consultPlanDate;
		}
		public String getConsultActualDate() {
			return consultActualDate;
		}
		public void setConsultActualDate(String consultActualDate) {
			this.consultActualDate = consultActualDate;
		}
		public Integer getConsulTRate() {
			return consulTRate;
		}
		public void setConsulTRate(Integer consulTRate) {
			this.consulTRate = consulTRate;
		}
		public Integer getConsultDegree() {
			return consultDegree;
		}
		public void setConsultDegree(Integer consultDegree) {
			this.consultDegree = consultDegree;
		}
		public Integer getConsultCallState() {
			return consultCallState;
		}
		public void setConsultCallState(Integer consultCallState) {
			this.consultCallState = consultCallState;
		}
		public Double getTravelCourse() {
			return travelCourse;
		}
		public void setTravelCourse(Double travelCourse) {
			this.travelCourse = travelCourse;
		}
		public String getConsultSuggest() {
			return consultSuggest;
		}
		public void setConsultSuggest(String consultSuggest) {
			this.consultSuggest = consultSuggest;
		}
		public String getDisposeDate() {
			return disposeDate;
		}
		public void setDisposeDate(String disposeDate) {
			this.disposeDate = disposeDate;
		}
		public String getDisposeResult() {
			return disposeResult;
		}
		public void setDisposeResult(String disposeResult) {
			this.disposeResult = disposeResult;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Integer getReserveId() {
			return reserveId;
		}
		public void setReserveId(Integer reserveId) {
			this.reserveId = reserveId;
		}
		public String getReserveCode() {
			return reserveCode;
		}
		public void setReserveCode(String reserveCode) {
			this.reserveCode = reserveCode;
		}
		public Integer getCustomId() {
			return customId;
		}
		public void setCustomId(Integer customId) {
			this.customId = customId;
		}
	
		public Integer getXsCarId() {
			return xsCarId;
		}
		public void setXsCarId(Integer xsCarId) {
			this.xsCarId = xsCarId;
		}
		public Integer getStfIdPerson() {
			return stfIdPerson;
		}
		public void setStfIdPerson(Integer stfIdPerson) {
			this.stfIdPerson = stfIdPerson;
		}
		public String getStfIdPersonName() {
			return stfIdPersonName;
		}
		public void setStfIdPersonName(String stfIdPersonName) {
			this.stfIdPersonName = stfIdPersonName;
		}
		public String getReserveDete() {
			return reserveDete;
		}
		public void setReserveDete(String reserveDete) {
			this.reserveDete = reserveDete;
		}
		public Integer getCarBrand() {
			return carBrand;
		}
		public void setCarBrand(Integer carBrand) {
			this.carBrand = carBrand;
		}
		public String getCarBrandName() {
			return carBrandName;
		}
		public void setCarBrandName(String carBrandName) {
			this.carBrandName = carBrandName;
		}
		public Integer getCarModel() {
			return carModel;
		}
		public void setCarModel(Integer carModel) {
			this.carModel = carModel;
		}
		public String getCarModelName() {
			return carModelName;
		}
		public void setCarModelName(String carModelName) {
			this.carModelName = carModelName;
		}
		public Integer getCarColor() {
			return carColor;
		}
		public void setCarColor(Integer carColor) {
			this.carColor = carColor;
		}
		public String getCarColorName() {
			return carColorName;
		}
		public void setCarColorName(String carColorName) {
			this.carColorName = carColorName;
		}
		public Integer getCarTrim() {
			return carTrim;
		}
		public void setCarTrim(Integer carTrim) {
			this.carTrim = carTrim;
		}
		public Integer getCarOutputVolume() {
			return carOutputVolume;
		}
		public void setCarOutputVolume(Integer carOutputVolume) {
			this.carOutputVolume = carOutputVolume;
		}
		public Integer getLimitLoadNum() {
			return limitLoadNum;
		}
		public void setLimitLoadNum(Integer limitLoadNum) {
			this.limitLoadNum = limitLoadNum;
		}
		public String getPactCode() {
			return pactCode;
		}
		public void setPactCode(String pactCode) {
			this.pactCode = pactCode;
		}
		public Integer getCustomProperty() {
			return customProperty;
		}
		public void setCustomProperty(Integer customProperty) {
			this.customProperty = customProperty;
		}
		public Double getPaymentMoney() {
			return paymentMoney;
		}
		public void setPaymentMoney(Double paymentMoney) {
			this.paymentMoney = paymentMoney;
		}
		public Integer getPayWay() {
			return payWay;
		}
		public void setPayWay(Integer payWay) {
			this.payWay = payWay;
		}
		public Double getSellingprice() {
			return sellingprice;
		}
		public void setSellingprice(Double sellingprice) {
			this.sellingprice = sellingprice;
		}
		public Integer getExamine() {
			return examine;
		}
		public void setExamine(Integer examine) {
			this.examine = examine;
		}
		public Integer getOrderState() {
			return orderState;
		}
		public void setOrderState(Integer orderState) {
			this.orderState = orderState;
		}
		public String getPredictTakeDate() {
			return predictTakeDate;
		}
		public void setPredictTakeDate(String predictTakeDate) {
			this.predictTakeDate = predictTakeDate;
		}
		public Integer getStfId() {
			return stfId;
		}
		public void setStfId(Integer stfId) {
			this.stfId = stfId;
		}
		public String getStfName() {
			return stfName;
		}
		public void setStfName(String stfName) {
			this.stfName = stfName;
		}
		public String getXsDistributorCode() {
			return xsDistributorCode;
		}
		public void setXsDistributorCode(String xsDistributorCode) {
			this.xsDistributorCode = xsDistributorCode;
		}
		public Integer getLevel() {
			return level;
		}
		public void setLevel(Integer level) {
			this.level = level;
		}
		public Double getFirstPayMoney() {
			return firstPayMoney;
		}
		public void setFirstPayMoney(Double firstPayMoney) {
			this.firstPayMoney = firstPayMoney;
		}
		public Integer getLoanBank() {
			return loanBank;
		}
		public void setLoanBank(Integer loanBank) {
			this.loanBank = loanBank;
		}
		public Double getLoanLimitMoney() {
			return loanLimitMoney;
		}
		public void setLoanLimitMoney(Double loanLimitMoney) {
			this.loanLimitMoney = loanLimitMoney;
		}
		public Double getLoanLimitYear() {
			return loanLimitYear;
		}
		public void setLoanLimitYear(Double loanLimitYear) {
			this.loanLimitYear = loanLimitYear;
		}
		public String getVinCode() {
			return vinCode;
		}
		public void setVinCode(String vinCode) {
			this.vinCode = vinCode;
		}
		public String getCustomOpinion() {
			return customOpinion;
		}
		public void setCustomOpinion(String customOpinion) {
			this.customOpinion = customOpinion;
		}
		public Double getShingleMoney() {
			return shingleMoney;
		}
		public void setShingleMoney(Double shingleMoney) {
			this.shingleMoney = shingleMoney;
		}
		public Double getDecorateMoney() {
			return decorateMoney;
		}
		public void setDecorateMoney(Double decorateMoney) {
			this.decorateMoney = decorateMoney;
		}
		public Integer getAllocateState() {
			return allocateState;
		}
		public void setAllocateState(Integer allocateState) {
			this.allocateState = allocateState;
		}
		public Integer getDelState() {
			return delState;
		}
		public void setDelState(Integer delState) {
			this.delState = delState;
		}
		public String getRemark1() {
			return remark1;
		}
		public void setRemark1(String remark1) {
			this.remark1 = remark1;
		}
		public String getCarCode() {
			return carCode;
		}
		public void setCarCode(String carCode) {
			this.carCode = carCode;
		}

		public String getXsCarSelData() {
			return xsCarSelData;
		}
		public void setXsCarSelData(String xsCarSelData) {
			this.xsCarSelData = xsCarSelData;
		}
		public Double getXsCarSelTransactionMoney() {
			return xsCarSelTransactionMoney;
		}
		public void setXsCarSelTransactionMoney(Double xsCarSelTransactionMoney) {
			this.xsCarSelTransactionMoney = xsCarSelTransactionMoney;
		}
		public String getXsCarGiveUp() {
			return xsCarGiveUp;
		}
		public void setXsCarGiveUp(String xsCarGiveUp) {
			this.xsCarGiveUp = xsCarGiveUp;
		}
		public String getXsCustomReceiptor() {
			return xsCustomReceiptor;
		}
		public void setXsCustomReceiptor(String xsCustomReceiptor) {
			this.xsCustomReceiptor = xsCustomReceiptor;
		}
		public Integer getXsCarSelType() {
			return xsCarSelType;
		}
		public void setXsCarSelType(Integer xsCarSelType) {
			this.xsCarSelType = xsCarSelType;
		}
		public Double getApplySum() {
			return applySum;
		}
		public void setApplySum(Double applySum) {
			this.applySum = applySum;
		}
		public Double getCostSum() {
			return costSum;
		}
		public void setCostSum(Double costSum) {
			this.costSum = costSum;
		}
		public String getXsCarSelRemark() {
			return xsCarSelRemark;
		}
		public void setXsCarSelRemark(String xsCarSelRemark) {
			this.xsCarSelRemark = xsCarSelRemark;
		}
		public Integer getAudit() {
			return audit;
		}
		public void setAudit(Integer audit) {
			this.audit = audit;
		}
		public Integer getOutId() {
			return outId;
		}
		public void setOutId(Integer outId) {
			this.outId = outId;
		}
		public Integer getAuditPerson() {
			return auditPerson;
		}
		public void setAuditPerson(Integer auditPerson) {
			this.auditPerson = auditPerson;
		}
		public String getAuditDate() {
			return auditDate;
		}
		public void setAuditDate(String auditDate) {
			this.auditDate = auditDate;
		}
		public String getSort() {
			return sort;
		}
		public void setSort(String sort) {
			this.sort = sort;
		}
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		public int getRows() {
			return rows;
		}
		public void setRows(int rows) {
			this.rows = rows;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public String getCarMotorNumber() {
			return carMotorNumber;
		}
		public void setCarMotorNumber(String carMotorNumber) {
			this.carMotorNumber = carMotorNumber;
		}
		public Double getRate() {
			return rate;
		}
		public void setRate(Double rate) {
			this.rate = rate;
		}
		public Integer getSumCount() {
			return sumCount;
		}
		public void setSumCount(Integer sumCount) {
			this.sumCount = sumCount;
		}
		public Integer getPlanDate() {
			return planDate;
		}
		public void setPlanDate(Integer planDate) {
			this.planDate = planDate;
		}
		public Integer getActualDate() {
			return actualDate;
		}
		public void setActualDate(Integer actualDate) {
			this.actualDate = actualDate;
		}
		public Integer getPlanIn() {
			return planIn;
		}
		public void setPlanIn(Integer planIn) {
			this.planIn = planIn;
		}
		public Integer getPlanOut() {
			return planOut;
		}
		public void setPlanOut(Integer planOut) {
			this.planOut = planOut;
		}
		public Integer getNottrack() {
			return nottrack;
		}
		public void setNottrack(Integer nottrack) {
			this.nottrack = nottrack;
		}
		public Double getTimely() {
			return timely;
		}
		public void setTimely(Double timely) {
			this.timely = timely;
		}
}
