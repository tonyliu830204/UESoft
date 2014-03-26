package com.syuesoft.sell.model;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellServicing extends BaseBean implements   Serializable {
	private Integer servicingId;		//维护序号
	private XsSellInstorehouseDel  detailsId;		//入库明细
	private Integer servicingProject;		//维护项目
	private Integer servicingPerson;		//维护人
	private  Date servicingDate;		//维护日期
	private String servicingRemark;		//维护结果
	private Date servicingNextdate;		//预计下次维护日期
	public Integer getServicingId() {
		return servicingId;
	}
	public void setServicingId(Integer servicingId) {
		this.servicingId = servicingId;
	}
	public XsSellInstorehouseDel getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(XsSellInstorehouseDel detailsId) {
		this.detailsId = detailsId;
	}
	public Integer getServicingProject() {
		return servicingProject;
	}
	public void setServicingProject(Integer servicingProject) {
		this.servicingProject = servicingProject;
	}
	public Integer getServicingPerson() {
		return servicingPerson;
	}
	public void setServicingPerson(Integer servicingPerson) {
		this.servicingPerson = servicingPerson;
	}
	public Date getServicingDate() {
		return servicingDate;
	}
	public void setServicingDate(Date servicingDate) {
		this.servicingDate = servicingDate;
	}
	public String getServicingRemark() {
		return servicingRemark;
	}
	public void setServicingRemark(String servicingRemark) {
		this.servicingRemark = servicingRemark;
	}
	public Date getServicingNextdate() {
		return servicingNextdate;
	}
	public void setServicingNextdate(Date servicingNextdate) {
		this.servicingNextdate = servicingNextdate;
	}
	
	
}
