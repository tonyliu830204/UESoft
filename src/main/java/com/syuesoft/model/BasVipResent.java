package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasVipResent entity. @author MyEclipse Persistence Tools
 */

public class BasVipResent extends BaseBean {

	// Fields

	private Integer pstId;
	private String pstName;
	private Integer pstCount;
	private String pstUnit;
	private Integer pstInte;
	private Set basVipRelationship17s = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasVipResent() {
	}

	/** full constructor */
	public BasVipResent(String pstName, Integer pstCount, String pstUnit,
			Set basVipRelationship17s,Integer pstInte) {
		this.pstName = pstName;
		this.pstCount = pstCount;
		this.pstUnit = pstUnit;
		this.pstInte = pstInte;
		this.basVipRelationship17s = basVipRelationship17s;
	}

	// Property accessors

	public Integer getPstId() {
		return this.pstId;
	}

	public void setPstId(Integer pstId) {
		this.pstId = pstId;
	}

	public String getPstName() {
		return this.pstName;
	}

	public void setPstName(String pstName) {
		this.pstName = pstName;
	}

	public Integer getPstCount() {
		return this.pstCount;
	}

	public void setPstCount(Integer pstCount) {
		this.pstCount = pstCount;
	}

	public String getPstUnit() {
		return this.pstUnit;
	}

	public void setPstUnit(String pstUnit) {
		this.pstUnit = pstUnit;
	}

	public Set getBasVipRelationship17s() {
		return this.basVipRelationship17s;
	}

	public void setBasVipRelationship17s(Set basVipRelationship17s) {
		this.basVipRelationship17s = basVipRelationship17s;
	}
	
	public Integer getPstInte() {
		return pstInte;
	}

	public void setPstInte(Integer pstInte) {
		this.pstInte = pstInte;
	}
}