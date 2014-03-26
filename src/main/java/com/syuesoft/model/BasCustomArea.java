package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasCustomArea entity. @author MyEclipse Persistence Tools
 */

public class BasCustomArea extends BaseBean{

	// Fields

	private Short pareaId;
	private String pareaName;
	private String pareaZip;
	private Set frtCustoms = new HashSet(0);
	  private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasCustomArea() {
	}

	/** minimal constructor */
	public BasCustomArea(String pareaName) {
		this.pareaName = pareaName;
	}

	/** full constructor */
	public BasCustomArea(String pareaName, String pareaZip, Set frtCustoms) {
		this.pareaName = pareaName;
		this.pareaZip = pareaZip;
		this.frtCustoms = frtCustoms;
	}

	// Property accessors

	public Short getPareaId() {
		return this.pareaId;
	}

	public void setPareaId(Short pareaId) {
		this.pareaId = pareaId;
	}

	public String getPareaName() {
		return this.pareaName;
	}

	public void setPareaName(String pareaName) {
		this.pareaName = pareaName;
	}

	public String getPareaZip() {
		return this.pareaZip;
	}

	public void setPareaZip(String pareaZip) {
		this.pareaZip = pareaZip;
	}

	public Set getFrtCustoms() {
		return this.frtCustoms;
	}

	public void setFrtCustoms(Set frtCustoms) {
		this.frtCustoms = frtCustoms;
	}

}