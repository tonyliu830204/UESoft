package com.syuesoft.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * BasVipActivitiesProject entity. @author MyEclipse Persistence Tools
 */

public class BasVipActivitiesProject  extends BaseBean{

	// Fields

	private Short vipActProId;
	private String vipActProName;
	private Timestamp vipActProDate;
	private Short vipActJoinPcount;
	private String vipActProNote;
	private Integer vipActReward;
	private Set basVipRelationship15s = new HashSet(0);
	private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasVipActivitiesProject() {
	}

	/** full constructor */
	public BasVipActivitiesProject(String vipActProName,
			Timestamp vipActProDate, Short vipActJoinPcount,
			String vipActProNote, Integer vipActReward,
			Set basVipRelationship15s) {
		this.vipActProName = vipActProName;
		this.vipActProDate = vipActProDate;
		this.vipActJoinPcount = vipActJoinPcount;
		this.vipActProNote = vipActProNote;
		this.vipActReward = vipActReward;
		this.basVipRelationship15s = basVipRelationship15s;
	}

	// Property accessors

	public Short getVipActProId() {
		return this.vipActProId;
	}

	public void setVipActProId(Short vipActProId) {
		this.vipActProId = vipActProId;
	}

	public String getVipActProName() {
		return this.vipActProName;
	}

	public void setVipActProName(String vipActProName) {
		this.vipActProName = vipActProName;
	}

	public Timestamp getVipActProDate() {
		return this.vipActProDate;
	}

	public void setVipActProDate(Timestamp vipActProDate) {
		this.vipActProDate = vipActProDate;
	}

	public Short getVipActJoinPcount() {
		return this.vipActJoinPcount;
	}

	public void setVipActJoinPcount(Short vipActJoinPcount) {
		this.vipActJoinPcount = vipActJoinPcount;
	}

	public String getVipActProNote() {
		return this.vipActProNote;
	}

	public void setVipActProNote(String vipActProNote) {
		this.vipActProNote = vipActProNote;
	}

	public Integer getVipActReward() {
		return this.vipActReward;
	}

	public void setVipActReward(Integer vipActReward) {
		this.vipActReward = vipActReward;
	}

	public Set getBasVipRelationship15s() {
		return this.basVipRelationship15s;
	}

	public void setBasVipRelationship15s(Set basVipRelationship15s) {
		this.basVipRelationship15s = basVipRelationship15s;
	}

}