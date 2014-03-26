package com.syuesoft.sell.model;

import java.io.Serializable;

import com.syuesoft.model.BaseBean;

public class XsSellInsurdetail extends BaseBean implements Serializable{
	//保险险种明细
	private Integer detailId ;
	private Integer  insuranceId ;
	private String  insurancetype ;
	private Double  insurancemoney;
	private String  insurancerate;
	private Double  insurancecost;
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public Integer getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
	public  String  getInsurancetype() {
		return insurancetype;
	}
	public void setInsurancetype( String  insurancetype) {
		this.insurancetype = insurancetype;
	}
	public Double getInsurancemoney() {
		return insurancemoney;
	}
	public void setInsurancemoney(Double insurancemoney) {
		this.insurancemoney = insurancemoney;
	}
	public String getInsurancerate() {
		return insurancerate;
	}
	public void setInsurancerate(String insurancerate) {
		this.insurancerate = insurancerate;
	}
	public Double getInsurancecost() {
		return insurancecost;
	}
	public void setInsurancecost(Double insurancecost) {
		this.insurancecost = insurancecost;
	}

}
