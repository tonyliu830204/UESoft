package com.syuesoft.systemmanagement.vo;

import com.syuesoft.base.vo.BaseBeanVo;


public class SystemLogVo extends BaseBeanVo{
	
//	private String user_Name;
//	private String ip_Name;
//	private String start_Time;
//	private String do_Module_Name;
//	private String do_Opertype;
//	private String do_Content;
//	private String do_Type;
//	private String id;
	
	
	private String id;
	private String systemName;
	private String userName;
	private String ipName;
	private String startTime;
	private String moduleName;
	private String opertype;
	private String content;
	private String type;
	
	private String beginTime;
	private String endTime;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIpName() {
		return ipName;
	}
	public void setIpName(String ipName) {
		this.ipName = ipName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getOpertype() {
		return opertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
}
