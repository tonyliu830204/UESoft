package com.syuesoft.model;

/**
 * FbkCarGroup entity. @author MyEclipse Persistence Tools
 */

public class FbkCarGroup extends BaseBean {

	// Fields

	private FbkCarGroupId id;
	private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public FbkCarGroup() {
	}

	/** full constructor */
	public FbkCarGroup(FbkCarGroupId id) {
		this.id = id;
	}

	// Property accessors

	public FbkCarGroupId getId() {
		return this.id;
	}

	public void setId(FbkCarGroupId id) {
		this.id = id;
	}

}