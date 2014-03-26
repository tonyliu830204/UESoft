package com.syuesoft.model;

/**
 * BasCustomerComplaints entity. @author MyEclipse Persistence Tools
 */

public class BasCustomerComplaints extends BaseBean{

	// Fields

	private Integer complaintsId;
	private String complaintsType;
	private String complaintsContent;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasCustomerComplaints() {
	}

	/** minimal constructor */
	public BasCustomerComplaints(Integer complaintsId) {
		this.complaintsId = complaintsId;
	}

	/** full constructor */
	public BasCustomerComplaints(Integer complaintsId, String complaintsType,
			String complaintsContent) {
		this.complaintsId = complaintsId;
		this.complaintsType = complaintsType;
		this.complaintsContent = complaintsContent;
	}

	// Property accessors

	public Integer getComplaintsId() {
		return this.complaintsId;
	}

	public void setComplaintsId(Integer complaintsId) {
		this.complaintsId = complaintsId;
	}

	public String getComplaintsType() {
		return this.complaintsType;
	}

	public void setComplaintsType(String complaintsType) {
		this.complaintsType = complaintsType;
	}

	public String getComplaintsContent() {
		return this.complaintsContent;
	}

	public void setComplaintsContent(String complaintsContent) {
		this.complaintsContent = complaintsContent;
	}

}