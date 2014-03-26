package com.syuesoft.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * BasVipResentExchange entity. @author MyEclipse Persistence Tools
 */

public class BasVipResentExchange extends BaseBean {

	// Fields

	private Integer preExgId;
	private BasVipInfor basVipInfor;
	private Timestamp preExgDate;
	private Integer preExgInte;
	private String preExgNote;
	private String operator;
	private Set basVipRelationship17s = new HashSet(0);

	// Constructors

	/** default constructor */
	public BasVipResentExchange() {
	}

	/** full constructor */
	public BasVipResentExchange(BasVipInfor basVipInfor, Timestamp preExgDate,
			Integer preExgInte, String preExgNote, String operator,
			Set basVipRelationship17s) {
		this.basVipInfor = basVipInfor;
		this.preExgDate = preExgDate;
		this.preExgInte = preExgInte;
		this.preExgNote = preExgNote;
		this.operator = operator;
		this.basVipRelationship17s = basVipRelationship17s;
	}

	// Property accessors

	public Integer getPreExgId() {
		return this.preExgId;
	}

	public void setPreExgId(Integer preExgId) {
		this.preExgId = preExgId;
	}

	public BasVipInfor getBasVipInfor() {
		return this.basVipInfor;
	}

	public void setBasVipInfor(BasVipInfor basVipInfor) {
		this.basVipInfor = basVipInfor;
	}

	public Timestamp getPreExgDate() {
		return this.preExgDate;
	}

	public void setPreExgDate(Timestamp preExgDate) {
		this.preExgDate = preExgDate;
	}

	public Integer getPreExgInte() {
		return this.preExgInte;
	}

	public void setPreExgInte(Integer preExgInte) {
		this.preExgInte = preExgInte;
	}

	public String getPreExgNote() {
		return this.preExgNote;
	}

	public void setPreExgNote(String preExgNote) {
		this.preExgNote = preExgNote;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Set getBasVipRelationship17s() {
		return this.basVipRelationship17s;
	}

	public void setBasVipRelationship17s(Set basVipRelationship17s) {
		this.basVipRelationship17s = basVipRelationship17s;
	}

}