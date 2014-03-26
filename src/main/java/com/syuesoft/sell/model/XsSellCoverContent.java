package com.syuesoft.sell.model;

import java.io.Serializable;

import com.syuesoft.model.BaseBean;

public class XsSellCoverContent extends BaseBean implements Serializable{
	/**
	 * 回访调查表
	 */
	private Integer contentId;
	private Integer consultId;
	private Integer projectId;
	private Integer projectEvaluate;
	private Integer projectScore;
	private String remark;
	private XsChilddictionary xsSellCoverContentEvaluate;
	
	public XsChilddictionary getXsSellCoverContentEvaluate() {
		return xsSellCoverContentEvaluate;
	}
	public void setXsSellCoverContentEvaluate(
			XsChilddictionary xsSellCoverContentEvaluate) {
		this.xsSellCoverContentEvaluate = xsSellCoverContentEvaluate;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public Integer getConsultId() {
		return consultId;
	}
	public void setConsultId(Integer consultId) {
		this.consultId = consultId;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	


}
