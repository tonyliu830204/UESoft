package com.syuesoft.sell.model;

/**
 * XsSellCarReserve entity. @author MyEclipse Persistence Tools
 */

public class XsSellCarReserve implements java.io.Serializable {

	// Fields

	private Integer enterpriseId;
	private Integer reserveId;
	private String reserveCode;
	private Integer customId;
	private Integer xsCarId;
	private Integer stfIdPerson;
	private String reserveDete;
	private Integer carBrand;
	private Integer carModel;
	private Integer carColor;
	private Integer carTrim;
	private Integer carOutputVolume;
	private Integer limitLoadNum;
	private String pactCode;
	private Integer customProperty;
	private Double paymentMoney;
	private Integer payWay;
	private Double sellingprice;
	private Integer examine;
	private Integer orderState;
	private String predictTakeDate;
	private Integer stfId;
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
	private Integer delState;
	private String remark;
	private String mobilephone;
	private Integer useNature;
	private XsChilddictionary xsChilddictionary;//carBrand
	private XsChilddictionary xsChilddictionary1;//carColor
	private XsChilddictionary xsChilddictionary2;//carTrim
	private XsChilddictionary xsChilddictionary3;//carOutputVolume
	private XsChilddictionary xsChilddictionary4;//custom_Property
	private XsChilddictionary xsChilddictionary5;//pay_Way
	private XsChilddictionary xsChilddictionary6;//examine
	private XsChilddictionary xsChilddictionary7;//orderState
	

	

	// Constructors

	public XsChilddictionary getXsChilddictionary() {
		return xsChilddictionary;
	}

	public void setXsChilddictionary(XsChilddictionary xsChilddictionary) {
		this.xsChilddictionary = xsChilddictionary;
	}

	public XsChilddictionary getXsChilddictionary1() {
		return xsChilddictionary1;
	}

	public void setXsChilddictionary1(XsChilddictionary xsChilddictionary1) {
		this.xsChilddictionary1 = xsChilddictionary1;
	}

	public XsChilddictionary getXsChilddictionary2() {
		return xsChilddictionary2;
	}

	public void setXsChilddictionary2(XsChilddictionary xsChilddictionary2) {
		this.xsChilddictionary2 = xsChilddictionary2;
	}

	public XsChilddictionary getXsChilddictionary3() {
		return xsChilddictionary3;
	}

	public void setXsChilddictionary3(XsChilddictionary xsChilddictionary3) {
		this.xsChilddictionary3 = xsChilddictionary3;
	}

	public XsChilddictionary getXsChilddictionary4() {
		return xsChilddictionary4;
	}

	public void setXsChilddictionary4(XsChilddictionary xsChilddictionary4) {
		this.xsChilddictionary4 = xsChilddictionary4;
	}

	public XsChilddictionary getXsChilddictionary5() {
		return xsChilddictionary5;
	}

	public void setXsChilddictionary5(XsChilddictionary xsChilddictionary5) {
		this.xsChilddictionary5 = xsChilddictionary5;
	}

	public XsChilddictionary getXsChilddictionary6() {
		return xsChilddictionary6;
	}

	public void setXsChilddictionary6(XsChilddictionary xsChilddictionary6) {
		this.xsChilddictionary6 = xsChilddictionary6;
	}

	public XsChilddictionary getXsChilddictionary7() {
		return xsChilddictionary7;
	}

	public void setXsChilddictionary7(XsChilddictionary xsChilddictionary7) {
		this.xsChilddictionary7 = xsChilddictionary7;
	}

	/** default constructor */
	public XsSellCarReserve() {
	}

	/** full constructor */
	public XsSellCarReserve(String reserveCode, Integer customId,
			Integer xsCarId, Integer stfIdPerson, String reserveDete,
			Integer carBrand, Integer carModel, Integer carColor,
			Integer carTrim, Integer carOutputVolume, Integer limitLoadNum,
			String pactCode, Integer customProperty, Double paymentMoney,
			Integer payWay, Double sellingprice, Integer examine,
			Integer orderState, String predictTakeDate, Integer stfId,
			String xsDistributorCode, Integer level, Double firstPayMoney,
			Integer loanBank, Double loanLimitMoney, Double loanLimitYear,
			String vinCode, String customOpinion, Double shingleMoney,
			Double decorateMoney, Integer allocateState, Integer delState,
			String remark, String mobilephone, Integer useNature) {
		this.reserveCode = reserveCode;
		this.customId = customId;
		this.xsCarId = xsCarId;
		this.stfIdPerson = stfIdPerson;
		this.reserveDete = reserveDete;
		this.carBrand = carBrand;
		this.carModel = carModel;
		this.carColor = carColor;
		this.carTrim = carTrim;
		this.carOutputVolume = carOutputVolume;
		this.limitLoadNum = limitLoadNum;
		this.pactCode = pactCode;
		this.customProperty = customProperty;
		this.paymentMoney = paymentMoney;
		this.payWay = payWay;
		this.sellingprice = sellingprice;
		this.examine = examine;
		this.orderState = orderState;
		this.predictTakeDate = predictTakeDate;
		this.stfId = stfId;
		this.xsDistributorCode = xsDistributorCode;
		this.level = level;
		this.firstPayMoney = firstPayMoney;
		this.loanBank = loanBank;
		this.loanLimitMoney = loanLimitMoney;
		this.loanLimitYear = loanLimitYear;
		this.vinCode = vinCode;
		this.customOpinion = customOpinion;
		this.shingleMoney = shingleMoney;
		this.decorateMoney = decorateMoney;
		this.delState = delState;
		this.remark = remark;
		this.mobilephone = mobilephone;
		this.useNature = useNature;
	}

	// Property accessors

	
	
	public Integer getReserveId() {
		return this.reserveId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public void setReserveId(Integer reserveId) {
		this.reserveId = reserveId;
	}

	public String getReserveCode() {
		return this.reserveCode;
	}

	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}

	public Integer getCustomId() {
		return this.customId;
	}

	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	public Integer getXsCarId() {
		return this.xsCarId;
	}

	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}

	public Integer getStfIdPerson() {
		return this.stfIdPerson;
	}

	public void setStfIdPerson(Integer stfIdPerson) {
		this.stfIdPerson = stfIdPerson;
	}

	public String getReserveDete() {
		return this.reserveDete;
	}

	public void setReserveDete(String reserveDete) {
		this.reserveDete = reserveDete;
	}

	public Integer getCarBrand() {
		return this.carBrand;
	}

	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getCarModel() {
		return this.carModel;
	}

	public void setCarModel(Integer carModel) {
		this.carModel = carModel;
	}

	public Integer getCarColor() {
		return this.carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public Integer getCarTrim() {
		return this.carTrim;
	}

	public void setCarTrim(Integer carTrim) {
		this.carTrim = carTrim;
	}

	public Integer getCarOutputVolume() {
		return this.carOutputVolume;
	}

	public void setCarOutputVolume(Integer carOutputVolume) {
		this.carOutputVolume = carOutputVolume;
	}

	public Integer getLimitLoadNum() {
		return this.limitLoadNum;
	}

	public void setLimitLoadNum(Integer limitLoadNum) {
		this.limitLoadNum = limitLoadNum;
	}

	public String getPactCode() {
		return this.pactCode;
	}

	public void setPactCode(String pactCode) {
		this.pactCode = pactCode;
	}

	public Integer getCustomProperty() {
		return this.customProperty;
	}

	public void setCustomProperty(Integer customProperty) {
		this.customProperty = customProperty;
	}

	public Double getPaymentMoney() {
		return this.paymentMoney;
	}

	public void setPaymentMoney(Double paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public Integer getPayWay() {
		return this.payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public Double getSellingprice() {
		return this.sellingprice;
	}

	public void setSellingprice(Double sellingprice) {
		this.sellingprice = sellingprice;
	}

	public Integer getExamine() {
		return this.examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	public Integer getOrderState() {
		return this.orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public String getPredictTakeDate() {
		return this.predictTakeDate;
	}

	public void setPredictTakeDate(String predictTakeDate) {
		this.predictTakeDate = predictTakeDate;
	}

	public Integer getStfId() {
		return this.stfId;
	}

	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}

	public String getXsDistributorCode() {
		return this.xsDistributorCode;
	}

	public void setXsDistributorCode(String xsDistributorCode) {
		this.xsDistributorCode = xsDistributorCode;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getFirstPayMoney() {
		return this.firstPayMoney;
	}

	public void setFirstPayMoney(Double firstPayMoney) {
		this.firstPayMoney = firstPayMoney;
	}

	public Integer getLoanBank() {
		return this.loanBank;
	}

	public void setLoanBank(Integer loanBank) {
		this.loanBank = loanBank;
	}

	public Double getLoanLimitMoney() {
		return this.loanLimitMoney;
	}

	public void setLoanLimitMoney(Double loanLimitMoney) {
		this.loanLimitMoney = loanLimitMoney;
	}

	public Double getLoanLimitYear() {
		return this.loanLimitYear;
	}

	public void setLoanLimitYear(Double loanLimitYear) {
		this.loanLimitYear = loanLimitYear;
	}

	public String getVinCode() {
		return this.vinCode;
	}

	public void setVinCode(String vinCode) {
		this.vinCode = vinCode;
	}

	public String getCustomOpinion() {
		return this.customOpinion;
	}

	public void setCustomOpinion(String customOpinion) {
		this.customOpinion = customOpinion;
	}

	public Double getShingleMoney() {
		return this.shingleMoney;
	}

	public void setShingleMoney(Double shingleMoney) {
		this.shingleMoney = shingleMoney;
	}

	public Double getDecorateMoney() {
		return this.decorateMoney;
	}

	public void setDecorateMoney(Double decorateMoney) {
		this.decorateMoney = decorateMoney;
	}


	public Integer getDelState() {
		return this.delState;
	}

	public void setDelState(Integer delState) {
		this.delState = delState;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public Integer getUseNature() {
		return this.useNature;
	}

	public void setUseNature(Integer useNature) {
		this.useNature = useNature;
	}

}