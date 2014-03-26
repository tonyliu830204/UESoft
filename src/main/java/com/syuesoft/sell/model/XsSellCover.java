package com.syuesoft.sell.model;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellCover extends BaseBean  implements Serializable{
	/**
	 * 售后回访
	 */
	private Integer  consultId;
	private Integer  xsCarSelId;//车辆销售编号
	private Date  consultPlanDate;//计划回访日期
	private Date   consultActualDate;//实际回访日期
	private Integer   consultRate ;//回访进度
	private Integer  consultDegree ;//满意度
	private Integer   consultCallState;//通话情况
	private Double travelCourse;//行驶里程
	private String consultSuggest;//领导意见
	private Date    disposeDate ;//处理日期
	private String disposeResult;//处理结果
	private String remark;//备注
	private Integer enterpriseId;
	private XsChilddictionary xsSellCoverCallState;//通话情况
	private XsChilddictionary xsSellCoverDegree;//满意度
	
	
	public XsChilddictionary getXsSellCoverCallState() {
		return xsSellCoverCallState;
	}

	public void setXsSellCoverCallState(XsChilddictionary xsSellCoverCallState) {
		this.xsSellCoverCallState = xsSellCoverCallState;
	}

	public XsChilddictionary getXsSellCoverDegree() {
		return xsSellCoverDegree;
	}

	public void setXsSellCoverDegree(XsChilddictionary xsSellCoverDegree) {
		this.xsSellCoverDegree = xsSellCoverDegree;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	public Date getConsultPlanDate() {
		return consultPlanDate;
	}
	public void setConsultPlanDate(Date consultPlanDate) {
		this.consultPlanDate = consultPlanDate;
	}
	public Date getConsultActualDate() {
		return consultActualDate;
	}
	public void setConsultActualDate(Date consultActualDate) {
		this.consultActualDate = consultActualDate;
	}

	public Integer getConsultRate() {
		return consultRate;
	}
	public void setConsultRate(Integer consultRate) {
		this.consultRate = consultRate;
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
	public Date getDisposeDate() {
		return disposeDate;
	}
	public void setDisposeDate(Date disposeDate) {
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
	

}
