package com.syuesoft.combine.vo;

import com.syuesoft.base.vo.BaseBeanVo;

/**
 * 集团分析查询视图对象
 * @author LWJ
 * */
public class CombineAnalyseVo extends BaseBeanVo {
	private String enterpriseId;			//企业序号
	private String parentEnterpriseId;		//父级企业序号
	private String entepriseName;			//企业名称
	private String preclrBeginTime;			//开始时间
	private String preclrEndTime;			//结束时间
	private Boolean flag;					//datagrid与data标示
	private String rowsData;				//前台传后台数据
	private Boolean is3D;					//是否显示3D图形,true为显示
	
	public String getPreclrBeginTime() {
		return preclrBeginTime;
	}
	public void setPreclrBeginTime(String preclrBeginTime) {
		this.preclrBeginTime = preclrBeginTime;
	}
	
	public String getPreclrEndTime() {
		return preclrEndTime;
	}
	public void setPreclrEndTime(String preclrEndTime) {
		this.preclrEndTime = preclrEndTime;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getEntepriseName() {
		return entepriseName;
	}
	public void setEntepriseName(String entepriseName) {
		this.entepriseName = entepriseName;
	}
	public String getRowsData() {
		return rowsData;
	}
	public void setRowsData(String rowsData) {
		this.rowsData = rowsData;
	}
	public Boolean getIs3D() {
		return is3D;
	}
	public void setIs3D(Boolean is3d) {
		is3D = is3d;
	}
	public String getParentEnterpriseId() {
		return parentEnterpriseId;
	}
	public void setParentEnterpriseId(String parentEnterpriseId) {
		this.parentEnterpriseId = parentEnterpriseId;
	}
	
}
