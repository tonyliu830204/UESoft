package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasVipGruop entity. @author MyEclipse Persistence Tools
 */

public class BasVipGruop extends BaseBean{

	// Fields

	private Integer vipGruopId;
	private String vipGruopName;
	private String vipGruopNote;
	private Set basVipInfors = new HashSet(0);
	private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public BasVipGruop() {
	}

	/** full constructor */
	public BasVipGruop(String vipGruopName, String vipGruopNote,
			Set basVipInfors) {
		this.vipGruopName = vipGruopName;
		this.vipGruopNote = vipGruopNote;
		this.basVipInfors = basVipInfors;
	}

	// Property accessors

	public Integer getVipGruopId() {
		return this.vipGruopId;
	}

	public void setVipGruopId(Integer vipGruopId) {
		this.vipGruopId = vipGruopId;
	}

	public String getVipGruopName() {
		return this.vipGruopName;
	}

	public void setVipGruopName(String vipGruopName) {
		this.vipGruopName = vipGruopName;
	}

	public String getVipGruopNote() {
		return this.vipGruopNote;
	}

	public void setVipGruopNote(String vipGruopNote) {
		this.vipGruopNote = vipGruopNote;
	}

	public Set getBasVipInfors() {
		return this.basVipInfors;
	}

	public void setBasVipInfors(Set basVipInfors) {
		this.basVipInfors = basVipInfors;
	}

}