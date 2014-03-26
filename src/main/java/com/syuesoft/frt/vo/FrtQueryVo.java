package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FrtQueryVo extends BaseBeanVo{
	private String enrolTime;
	private Integer sumCount=0;
	private Integer successCount=0;
	private Integer failCount=0;
	private Integer continueCount=0;
	private Double successRate;
	
	/********/
	private String stfId;
	private String stfName;
	private Boolean flag;
	
	private String insurePerson;
	private Integer preclrCount;
	private Double partsAmount;
	private Double itemAmount;
	private Double sumAmount;
	private Double taxCost;
	private Double noTaxCost;
	private String preclrId;
	private String preclrTime;
	private String receptionId;
	private String carLicense;
	private String customName;
	
	private String selectedField;
	private String beginTime;
	private String endTime;
	private String resvType;
	private Boolean is3D;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	public FrtQueryVo() {
		// TODO Auto-generated constructor stub
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEnrolTime() {
		return enrolTime;
	}

	public void setEnrolTime(String enrolTime) {
		this.enrolTime = enrolTime;
	}

	public Integer getSumCount() {
		return sumCount;
	}

	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public Double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}

	public Boolean getIs3D() {
		return is3D;
	}

	public void setIs3D(Boolean is3d) {
		is3D = is3d;
	}

	public String getSelectedField() {
		return selectedField;
	}

	public void setSelectedField(String selectedField) {
		this.selectedField = selectedField;
	}

	public Integer getContinueCount() {
		return continueCount;
	}

	public void setContinueCount(Integer continueCount) {
		this.continueCount = continueCount;
	}

	public String getResvType() {
		return resvType;
	}

	public void setResvType(String resvType) {
		this.resvType = resvType;
	}

	public String getStfId() {
		return stfId;
	}

	public void setStfId(String stfId) {
		this.stfId = stfId;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getInsurePerson() {
		return insurePerson;
	}

	public void setInsurePerson(String insurePerson) {
		this.insurePerson = insurePerson;
	}

	public Integer getPreclrCount() {
		return preclrCount;
	}

	public void setPreclrCount(Integer preclrCount) {
		this.preclrCount = preclrCount;
	}

	public Double getPartsAmount() {
		return partsAmount;
	}

	public void setPartsAmount(Double partsAmount) {
		this.partsAmount = partsAmount;
	}

	public Double getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}

	public Double getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}

	public Double getTaxCost() {
		return taxCost;
	}

	public void setTaxCost(Double taxCost) {
		this.taxCost = taxCost;
	}

	public Double getNoTaxCost() {
		return noTaxCost;
	}

	public void setNoTaxCost(Double noTaxCost) {
		this.noTaxCost = noTaxCost;
	}

	public String getPreclrId() {
		return preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public String getPreclrTime() {
		return preclrTime;
	}

	public void setPreclrTime(String preclrTime) {
		this.preclrTime = preclrTime;
	}

	public String getReceptionId() {
		return receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

	public String getCarLicense() {
		return carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
}
