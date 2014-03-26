package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Reptype entity. @author MyEclipse Persistence Tools
 */

public class Reptype extends BaseBean {

	// Fields

	private Short reptId;
	private String reptName;
	private Double workCreditsRate;//工时积分
	private Double partCreditsRate;//配件积分比例
	private Double sumCreditsRate;//合计积分比例
	private Short reptClass;//区分保养或维修
	private String memo;
	private Set frtReceptions = new HashSet(0);
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public Reptype() {
	}

	/** full constructor */
	public Reptype(String reptName, Double workCreditsRate,
			Double partCreditsRate, Double sumCreditsRate, String memo,
			Set frtReceptions) {
		this.reptName = reptName;
		this.workCreditsRate = workCreditsRate;
		this.partCreditsRate = partCreditsRate;
		this.sumCreditsRate = sumCreditsRate;
		this.memo = memo;
		this.frtReceptions = frtReceptions;
	}

	// Property accessors

	public Short getReptId() {
		return this.reptId;
	}

	public void setReptId(Short reptId) {
		this.reptId = reptId;
	}

	public String getReptName() {
		return this.reptName;
	}

	public void setReptName(String reptName) {
		this.reptName = reptName;
	}

	public Double getWorkCreditsRate() {
		return this.workCreditsRate;
	}

	public void setWorkCreditsRate(Double workCreditsRate) {
		this.workCreditsRate = workCreditsRate;
	}

	public Double getPartCreditsRate() {
		return this.partCreditsRate;
	}

	public void setPartCreditsRate(Double partCreditsRate) {
		this.partCreditsRate = partCreditsRate;
	}

	public Double getSumCreditsRate() {
		return this.sumCreditsRate;
	}

	public void setSumCreditsRate(Double sumCreditsRate) {
		this.sumCreditsRate = sumCreditsRate;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set getFrtReceptions() {
		return this.frtReceptions;
	}

	public void setFrtReceptions(Set frtReceptions) {
		this.frtReceptions = frtReceptions;
	}

	public Short getReptClass() {
		return reptClass;
	}

	public void setReptClass(Short reptClass) {
		this.reptClass = reptClass;
	}

}