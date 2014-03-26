package com.syuesoft.model;

import java.util.Date;

/**
 * SystemLog entity. @author MyEclipse Persistence Tools
 */
public class SystemLog implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String systemName;
	private String userName;
	private String ipName;
	private Date startTime;
	private String moduleName;
	private String opertype;
	private String content;
	private String type;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }

	/** default constructor */
	public SystemLog() {
		
	}
	
	public Integer getId() {
		return this.id;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIpName() {
		return this.ipName;
	}

	public void setIpName(String ipName) {
		this.ipName = ipName;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
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
}