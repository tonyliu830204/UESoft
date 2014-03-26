package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasCustomNature entity. @author MyEclipse Persistence Tools
 */

public class BasCustomNature extends BaseBean {

	// Fields

	private Short natureId;
	private String natureName;
	private String memo;
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
	public BasCustomNature() {
	}

	/** full constructor */
	public BasCustomNature(String natureName, String memo, Set frtCustoms) {
		this.natureName = natureName;
		this.memo = memo;
		this.frtCustoms = frtCustoms;
	}

	// Property accessors

	public Short getNatureId() {
		return this.natureId;
	}

	public void setNatureId(Short natureId) {
		this.natureId = natureId;
	}

	public String getNatureName() {
		return this.natureName;
	}

	public void setNatureName(String natureName) {
		this.natureName = natureName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set getFrtCustoms() {
		return this.frtCustoms;
	}

	public void setFrtCustoms(Set frtCustoms) {
		this.frtCustoms = frtCustoms;
	}

}