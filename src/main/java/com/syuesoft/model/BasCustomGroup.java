package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasCustomGroup entity. @author MyEclipse Persistence Tools
 */

public class BasCustomGroup extends BaseBean{

	// Fields

	private Short cstgId;
	private String cstgName;
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
	public BasCustomGroup() {
	}

	/** full constructor */
	public BasCustomGroup(String cstgName, String memo, Set frtCustoms) {
		this.cstgName = cstgName;
		this.memo = memo;
		this.frtCustoms = frtCustoms;
	}

	// Property accessors

	public Short getCstgId() {
		return this.cstgId;
	}

	public void setCstgId(Short cstgId) {
		this.cstgId = cstgId;
	}

	public String getCstgName() {
		return this.cstgName;
	}

	public void setCstgName(String cstgName) {
		this.cstgName = cstgName;
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