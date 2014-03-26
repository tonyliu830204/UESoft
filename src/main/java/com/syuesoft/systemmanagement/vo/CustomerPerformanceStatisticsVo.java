package com.syuesoft.systemmanagement.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class CustomerPerformanceStatisticsVo extends BaseBeanVo{
	private Short  stfId;
	private String stfName;
	private String serviceGroupName;
	private String receptionId;
	private String customName;
	private String carLicense;
	private String carTypeName;
	private Double itemTime;
	private Double itemAmount;
	private Double partsAmount;
	private Double itemRebateAmount;
	private Double itemFactAmount;
	private Double sumAmount;
	private Double taxAmount;
	private Double noTaxAmount;
	private String itemName;
	private String receivePerson;
	
	private String reptName;
	private String preclrTime;
	
	private Double partsRebateAmount;
	
	private String claimantmTime;
	private String claimantmId;
	
	
	private String preclrTimeBegin;
	private String preclrTimeEnd;
	private String serviceGroupId;
	private String reptId;
	private String carBrandId;
	private String carTypeId;
	private String claimsTerm;
	private String rcptBranch;
	private Boolean weaveWay;
	
	private String claimantmTimeBegin;
	private String claimantmTimeEnd;
	private String finComId;
	private Boolean balanceFlag;
	
	private String state;                                                     //treegrid打开还是关闭
	private String iconCls; 
	private String _parentId;
	private Integer enterpriseId;

	public Integer getEnterpriseId() {
	   return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
	    this.enterpriseId = enterpriseId;
	}
	
	public Short getStfId() {
		return stfId;
	}
	public void setStfId(Short stfId) {
		this.stfId = stfId;
	}
	public String getStfName() {
		return stfName;
	}
	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
	public String getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
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
	public String getCarTypeName() {
		return carTypeName;
	}
	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}
	public Double getItemTime() {
		return itemTime;
	}
	public void setItemTime(Double itemTime) {
		this.itemTime = itemTime;
	}
	public Double getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}
	public Double getPartsAmount() {
		return partsAmount;
	}
	public void setPartsAmount(Double partsAmount) {
		this.partsAmount = partsAmount;
	}
	public Double getItemRebateAmount() {
		return itemRebateAmount;
	}
	public void setItemRebateAmount(Double itemRebateAmount) {
		this.itemRebateAmount = itemRebateAmount;
	}
	public Double getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Double getNoTaxAmount() {
		return noTaxAmount;
	}
	public void setNoTaxAmount(Double noTaxAmount) {
		this.noTaxAmount = noTaxAmount;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getReceivePerson() {
		return receivePerson;
	}
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
	}
	public String getServiceGroupName() {
		return serviceGroupName;
	}
	public void setServiceGroupName(String serviceGroupName) {
		this.serviceGroupName = serviceGroupName;
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
	public String getServiceGroupId() {
		return serviceGroupId;
	}
	public void setServiceGroupId(String serviceGroupId) {
		this.serviceGroupId = serviceGroupId;
	}
	public String getReptId() {
		return reptId;
	}
	public void setReptId(String reptId) {
		this.reptId = reptId;
	}
	public String getCarBrandId() {
		return carBrandId;
	}
	public void setCarBrandId(String carBrandId) {
		this.carBrandId = carBrandId;
	}
	public String getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}
	
	public String getClaimsTerm() {
		return claimsTerm;
	}
	public void setClaimsTerm(String claimsTerm) {
		this.claimsTerm = claimsTerm;
	}
	public String getRcptBranch() {
		return rcptBranch;
	}
	public void setRcptBranch(String rcptBranch) {
		this.rcptBranch = rcptBranch;
	}
	
	public Boolean getWeaveWay() {
		return weaveWay;
	}
	public void setWeaveWay(Boolean weaveWay) {
		this.weaveWay = weaveWay;
	}
	public String getReptName() {
		return reptName;
	}
	public void setReptName(String reptName) {
		this.reptName = reptName;
	}
	public String getPreclrTime() {
		return preclrTime;
	}
	public void setPreclrTime(String preclrTime) {
		this.preclrTime = preclrTime;
	}
	public Double getPartsRebateAmount() {
		return partsRebateAmount;
	}
	public void setPartsRebateAmount(Double partsRebateAmount) {
		this.partsRebateAmount = partsRebateAmount;
	}
	public Double getItemFactAmount() {
		return itemFactAmount;
	}
	public void setItemFactAmount(Double itemFactAmount) {
		this.itemFactAmount = itemFactAmount;
	}
	public String getClaimantmTime() {
		return claimantmTime;
	}
	public void setClaimantmTime(String claimantmTime) {
		this.claimantmTime = claimantmTime;
	}
	public String getClaimantmId() {
		return claimantmId;
	}
	public void setClaimantmId(String claimantmId) {
		this.claimantmId = claimantmId;
	}
	public String getClaimantmTimeBegin() {
		return claimantmTimeBegin;
	}
	public void setClaimantmTimeBegin(String claimantmTimeBegin) {
		this.claimantmTimeBegin = claimantmTimeBegin;
	}
	public String getClaimantmTimeEnd() {
		return claimantmTimeEnd;
	}
	public void setClaimantmTimeEnd(String claimantmTimeEnd) {
		this.claimantmTimeEnd = claimantmTimeEnd;
	}
	public String getFinComId() {
		return finComId;
	}
	public void setFinComId(String finComId) {
		this.finComId = finComId;
	}
	public Boolean getBalanceFlag() {
		return balanceFlag;
	}
	public void setBalanceFlag(Boolean balanceFlag) {
		this.balanceFlag = balanceFlag;
	}
	
}
