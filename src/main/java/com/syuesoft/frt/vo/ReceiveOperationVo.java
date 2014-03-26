package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class ReceiveOperationVo extends BaseBeanVo{
	private String stfId;
	private String stfName;
	private Integer count;
	private Double partsAmount;
	private Double itemsAmount;
	private Double sumAmount;
	private Double noTaxCost;
	private Double taxCost;
	private Double rate;
	
	private String customName;
	private String carLicense;
	private String interDate;
	private String preclrTime;
	private String preclrType;
	private Double otherAmount;
	private Double realAmount;
	
	private String preclrTimeBegin;
	private String preclrTimeEnd;
	private String receptionId;
	
	private String rcptBranch;
	private String reptId;
	
	private String partsCompareTime;		//料工比
	
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	
	public ReceiveOperationVo() {
		// TODO Auto-generated constructor stub
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getPartsAmount() {
		return partsAmount;
	}
	public void setPartsAmount(Double partsAmount) {
		this.partsAmount = partsAmount;
	}
	public Double getItemsAmount() {
		return itemsAmount;
	}
	public void setItemsAmount(Double itemsAmount) {
		this.itemsAmount = itemsAmount;
	}
	public Double getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}
	public Double getNoTaxCost() {
		return noTaxCost;
	}
	public void setNoTaxCost(Double noTaxCost) {
		this.noTaxCost = noTaxCost;
	}
	public Double getTaxCost() {
		return taxCost;
	}
	public void setTaxCost(Double taxCost) {
		this.taxCost = taxCost;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getPreclrTimeBegin() {
		return preclrTimeBegin;
	}
	public void setPreclrTimeBegin(String preclrTimeBegin) {
		this.preclrTimeBegin = preclrTimeBegin;
	}
	public String getPreclrTimeEnd() {
		return preclrTimeEnd;
	}
	public void setPreclrTimeEnd(String preclrTimeEnd) {
		this.preclrTimeEnd = preclrTimeEnd;
	}
	public String getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}
	public String getPreclrTime() {
		return preclrTime;
	}
	public void setPreclrTime(String preclrTime) {
		this.preclrTime = preclrTime;
	}
	public String getPreclrType() {
		return preclrType;
	}
	public void setPreclrType(String preclrType) {
		this.preclrType = preclrType;
	}
	public Double getOtherAmount() {
		return otherAmount;
	}
	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}
	public Double getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public String getInterDate() {
		return interDate;
	}
	public void setInterDate(String interDate) {
		this.interDate = interDate;
	}
	public String getRcptBranch() {
		return rcptBranch;
	}
	public void setRcptBranch(String rcptBranch) {
		this.rcptBranch = rcptBranch;
	}
	public String getReptId() {
		return reptId;
	}
	public void setReptId(String reptId) {
		this.reptId = reptId;
	}

	public String getPartsCompareTime() {
		return partsCompareTime;
	}

	public void setPartsCompareTime(String partsCompareTime) {
		this.partsCompareTime = partsCompareTime;
	}

}
