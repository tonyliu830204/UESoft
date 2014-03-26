package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasCustomType entity. @author MyEclipse Persistence Tools
 */

public class BasCustomType extends BaseBean{

	// Fields

	private Short cstId;
	private String cstName;
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
	public BasCustomType() {
	}

	/** full constructor */
	public BasCustomType(String cstName, String memo, Set frtCustoms) {
		this.cstName = cstName;
		this.memo = memo;
		this.frtCustoms = frtCustoms;
	}

	// Property accessors

	public Short getCstId() {
		return this.cstId;
	}

	public void setCstId(Short cstId) {
		this.cstId = cstId;
	}

	public String getCstName() {
		return this.cstName;
	}

	public void setCstName(String cstName) {
		this.cstName = cstName;
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