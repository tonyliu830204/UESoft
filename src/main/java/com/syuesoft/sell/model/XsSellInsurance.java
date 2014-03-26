package com.syuesoft.sell.model;

import com.syuesoft.model.BaseBean;

/**
 * XsSellInsurance entity. @author MyEclipse Persistence Tools
 */

public class XsSellInsurance extends BaseBean implements java.io.Serializable {

	// Fields

	private Integer insuranceId;			//编号
	private XsCarSellInfo xsCarSellInfo;
	private String insurancePolicy;			//保险单号
	private Integer insurer;				//保险公司
	private String numbers;					//组织代码
	private String dafeDate;				//代保日期
	private String insurerStartDate;		//投保日期	
	private String insurerEndDate;			//投保到期日期
	private String safeType;				//保单分类
	private String recordDate;				//登记日期
	private String distance;				//已行驶里程
	private String insuranceAgent;			//保险员
	private Double businessCharge;			//商业险出单保费
	private Double trafficCharge;			//交强险出单保费
	private Double vehiclevesselTax;
	private Double safeCost;				//保单保费
	private Double safeAmount;				//保险费
	private String buysnessCost;			//商业险返点
	private String qCost;					//交强险返点
	private Double sum;					//保费
	private Double customCost;				//客户交保/客户付款	
	private Double extract;					//业务提成
	private String customReturncost;		//客户返利
	private Integer person;					//经办人
	private Double profit;					//保险利润
	private Double primeCost;				//保费成本
	private String remark;					//备注
	private Integer examine;  //审核
	private Integer invoiceReckoning;

	// Constructors

	public Integer getInvoiceReckoning() {
		return invoiceReckoning;
	}

	public void setInvoiceReckoning(Integer invoiceReckoning) {
		this.invoiceReckoning = invoiceReckoning;
	}

	public Integer getExamine() {
		return examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	/** default constructor */
	public XsSellInsurance() {
	}

	/** full constructor */
	public XsSellInsurance(XsCarSellInfo xsCarSellInfo, String insurancePolicy,
			Integer insurer, String passiveSafeperson, String numbers,
			String idNumbers, String zipCode, Integer linkman,
			Integer carColor, Integer carModel, Integer cardColor, String vin,
			String engineNumber, Integer outprint, Integer weight,
			Integer useNature, String dafeDate, String insurerStartDate,
			String insurerEndDate, String safeType, String recordDate,
			String distance, String insuranceAgent, Double businessCharge,
			Double trafficCharge, Double vehiclevesselTax, Double safeCost,
			Double safeAmount, String buysnessCost, String qCost, Double sum,
			Double customCost, Double extract, String customReturncost,
			Integer person, Double profit, Double primeCost, String remark) {
		this.xsCarSellInfo = xsCarSellInfo;
		this.insurancePolicy = insurancePolicy;
		this.insurer = insurer;
		this.numbers = numbers;
		this.dafeDate = dafeDate;
		this.insurerStartDate = insurerStartDate;
		this.insurerEndDate = insurerEndDate;
		this.safeType = safeType;
		this.recordDate = recordDate;
		this.distance = distance;
		this.insuranceAgent = insuranceAgent;
		this.businessCharge = businessCharge;
		this.trafficCharge = trafficCharge;
		this.vehiclevesselTax = vehiclevesselTax;
		this.safeCost = safeCost;
		this.safeAmount = safeAmount;
		this.buysnessCost = buysnessCost;
		this.qCost = qCost;
		this.sum = sum;
		this.customCost = customCost;
		this.extract = extract;
		this.customReturncost = customReturncost;
		this.person = person;
		this.profit = profit;
		this.primeCost = primeCost;
		this.remark = remark;
	}

	// Property accessors

	public Integer getInsuranceId() {
		return this.insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public XsCarSellInfo getXsCarSellInfo() {
		return this.xsCarSellInfo;
	}

	public void setXsCarSellInfo(XsCarSellInfo xsCarSellInfo) {
		this.xsCarSellInfo = xsCarSellInfo;
	}

	public String getInsurancePolicy() {
		return this.insurancePolicy;
	}

	public void setInsurancePolicy(String insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public Integer getInsurer() {
		return this.insurer;
	}

	public void setInsurer(Integer insurer) {
		this.insurer = insurer;
	}

	

	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	

	

	

	public String getDafeDate() {
		return this.dafeDate;
	}

	public void setDafeDate(String dafeDate) {
		this.dafeDate = dafeDate;
	}

	public String getInsurerStartDate() {
		return this.insurerStartDate;
	}

	public void setInsurerStartDate(String insurerStartDate) {
		this.insurerStartDate = insurerStartDate;
	}

	public String getInsurerEndDate() {
		return this.insurerEndDate;
	}

	public void setInsurerEndDate(String insurerEndDate) {
		this.insurerEndDate = insurerEndDate;
	}

	public String getSafeType() {
		return this.safeType;
	}

	public void setSafeType(String safeType) {
		this.safeType = safeType;
	}

	public String getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getDistance() {
		return this.distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getInsuranceAgent() {
		return this.insuranceAgent;
	}

	public void setInsuranceAgent(String insuranceAgent) {
		this.insuranceAgent = insuranceAgent;
	}

	public Double getBusinessCharge() {
		return this.businessCharge;
	}

	public void setBusinessCharge(Double businessCharge) {
		this.businessCharge = businessCharge;
	}

	public Double getTrafficCharge() {
		return this.trafficCharge;
	}

	public void setTrafficCharge(Double trafficCharge) {
		this.trafficCharge = trafficCharge;
	}

	public Double getVehiclevesselTax() {
		return this.vehiclevesselTax;
	}

	public void setVehiclevesselTax(Double vehiclevesselTax) {
		this.vehiclevesselTax = vehiclevesselTax;
	}

	public Double getSafeCost() {
		return this.safeCost;
	}

	public void setSafeCost(Double safeCost) {
		this.safeCost = safeCost;
	}

	public Double getSafeAmount() {
		return this.safeAmount;
	}

	public void setSafeAmount(Double safeAmount) {
		this.safeAmount = safeAmount;
	}

	public String getBuysnessCost() {
		return this.buysnessCost;
	}

	public void setBuysnessCost(String buysnessCost) {
		this.buysnessCost = buysnessCost;
	}


	public String getqCost() {
		return qCost;
	}

	public void setqCost(String qCost) {
		this.qCost = qCost;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Double getCustomCost() {
		return this.customCost;
	}

	public void setCustomCost(Double customCost) {
		this.customCost = customCost;
	}

	public Double getExtract() {
		return this.extract;
	}

	public void setExtract(Double extract) {
		this.extract = extract;
	}

	public String getCustomReturncost() {
		return this.customReturncost;
	}

	public void setCustomReturncost(String customReturncost) {
		this.customReturncost = customReturncost;
	}

	public Integer getPerson() {
		return this.person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public Double getProfit() {
		return this.profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getPrimeCost() {
		return this.primeCost;
	}

	public void setPrimeCost(Double primeCost) {
		this.primeCost = primeCost;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}