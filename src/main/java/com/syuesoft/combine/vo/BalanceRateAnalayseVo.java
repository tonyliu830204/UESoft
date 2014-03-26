package com.syuesoft.combine.vo;

import com.syuesoft.base.vo.BaseBeanVo;
/**
 * 结算费用分析视图对象
 * @author LWJ
 * */
public class BalanceRateAnalayseVo extends BaseBeanVo {
	private String enterpriseId;			//企业序号
	private String enterpriseName;			//企业名称
	private Double preclrTimeAmount;		//结算工时费用
	private Double preclrPartsAmount;		//结算材料费用
	private Double preclrOtherAmount;		//结算其他费用
	private Double preclrTaxCost;			//结算含税成本
	private Double preclrNoTaxCost;			//结算未税成本
	private Double preclrSumAmount;			//结算总金额
	private String partsCompareTime;		//料工比
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public Double getPreclrTimeAmount() {
		return preclrTimeAmount;
	}
	public void setPreclrTimeAmount(Double preclrTimeAmount) {
		this.preclrTimeAmount = preclrTimeAmount;
	}
	public Double getPreclrPartsAmount() {
		return preclrPartsAmount;
	}
	public void setPreclrPartsAmount(Double preclrPartsAmount) {
		this.preclrPartsAmount = preclrPartsAmount;
	}
	public Double getPreclrOtherAmount() {
		return preclrOtherAmount;
	}
	public void setPreclrOtherAmount(Double preclrOtherAmount) {
		this.preclrOtherAmount = preclrOtherAmount;
	}
	public Double getPreclrTaxCost() {
		return preclrTaxCost;
	}
	public void setPreclrTaxCost(Double preclrTaxCost) {
		this.preclrTaxCost = preclrTaxCost;
	}
	public Double getPreclrNoTaxCost() {
		return preclrNoTaxCost;
	}
	public void setPreclrNoTaxCost(Double preclrNoTaxCost) {
		this.preclrNoTaxCost = preclrNoTaxCost;
	}
	public Double getPreclrSumAmount() {
		return preclrSumAmount;
	}
	public void setPreclrSumAmount(Double preclrSumAmount) {
		this.preclrSumAmount = preclrSumAmount;
	}
	public String getPartsCompareTime() {
		return partsCompareTime;
	}
	public void setPartsCompareTime(String partsCompareTime) {
		this.partsCompareTime = partsCompareTime;
	}

	
}
