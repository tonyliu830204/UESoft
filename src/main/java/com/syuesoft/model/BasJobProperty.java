package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasJobProperty entity. @author MyEclipse Persistence Tools
 */

public class BasJobProperty extends BaseBean{

	// Fields

	private Short jobProId;
	private Integer enterpriseId;						//企业信息序号
	private String jobProName;
	private String sysType;
	private String jobProNote;
	private Set basStuffJobs = new HashSet(0);
	// Constructors

	/** default constructor */
	public BasJobProperty() {
	}

	/** minimal constructor */
	public BasJobProperty(Short jobProId) {
		this.jobProId = jobProId;
	}

	/** full constructor */
	public BasJobProperty(Short jobProId, String jobProName, String jobProNote,Set basStuffJobs) {
		this.jobProId = jobProId;
		this.jobProName = jobProName;
		this.jobProNote = jobProNote;
		this.basStuffJobs = basStuffJobs;
	}

	// Property accessors

	public Short getJobProId() {
		return this.jobProId;
	}

	public void setJobProId(Short jobProId) {
		this.jobProId = jobProId;
	}

	public String getJobProName() {
		return this.jobProName;
	}

	public void setJobProName(String jobProName) {
		this.jobProName = jobProName;
	}
    
	public String getSysType()
    {
        return sysType;
    }

    public void setSysType(String sysType)
    {
        this.sysType = sysType;
    }

    public Set getBasStuffJobs() {
		return this.basStuffJobs;
	}

	public void setBasStuffJobs(Set basStuffJobs) {
		this.basStuffJobs = basStuffJobs;
	}

	public String getJobProNote() {
		return jobProNote;
	}

	public void setJobProNote(String jobProNote) {
		this.jobProNote = jobProNote;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	
}