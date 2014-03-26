package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * InsuranceType entity. @author MyEclipse Persistence Tools
 */

public class InsuranceType extends BaseBean {

	// Fields

	private Integer id;
	private String intype;
	private String incount;
	private String infeelv;
	private String infee;
	private String memo;
	private Set centerCarinInties = new HashSet(0);
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public InsuranceType() {
	}

	/** minimal constructor */
	public InsuranceType(String intype) {
		this.intype = intype;
	}

	/** full constructor */
	public InsuranceType(String intype,String memo, String incount, String infeelv,
			String infee,  Set centerCarinInties) {
		this.intype = intype;
		this.incount = incount;
		this.infeelv = infeelv;
		this.infee = infee;
		this.memo = memo;
		this.centerCarinInties = centerCarinInties;
	}

	// Property accessors

	
	
	public Integer getId() {
		return this.id;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIntype() {
		return this.intype;
	}

	public void setIntype(String intype) {
		this.intype = intype;
	}

	public String getIncount() {
		return this.incount;
	}

	public void setIncount(String incount) {
		this.incount = incount;
	}

	public String getInfeelv() {
		return this.infeelv;
	}

	public void setInfeelv(String infeelv) {
		this.infeelv = infeelv;
	}

	public String getInfee() {
		return this.infee;
	}

	public void setInfee(String infee) {
		this.infee = infee;
	}

	public Set getCenterCarinInties() {
		return this.centerCarinInties;
	}

	public void setCenterCarinInties(Set centerCarinInties) {
		this.centerCarinInties = centerCarinInties;
	}

}