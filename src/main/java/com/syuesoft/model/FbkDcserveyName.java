package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * FbkDcserveyName entity. @author MyEclipse Persistence Tools
 */

public class FbkDcserveyName extends BaseBean {

	// Fields

	private Integer dcNameId;
	private String serveyName;
	private String memo;
	private Set fbkCarDcnames = new HashSet(0);
	private Set fbkProgramTracks = new HashSet(0);
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }

	/** default constructor */
	public FbkDcserveyName() {
		
	}

	/** full constructor */
	public FbkDcserveyName(String serveyName, String memo,Set fbkCarDcnames,
			Set fbkProgramTracks) {
		this.serveyName = serveyName;
		this.memo = memo;
		this.fbkCarDcnames = fbkCarDcnames;
		this.fbkProgramTracks = fbkProgramTracks;
	}
	
	public Integer getDcNameId() {
		return this.dcNameId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setDcNameId(Integer dcNameId) {
		this.dcNameId = dcNameId;
	}

	public String getServeyName() {
		return this.serveyName;
	}

	public void setServeyName(String serveyName) {
		this.serveyName = serveyName;
	}

	public Set getFbkCarDcnames() {
		return this.fbkCarDcnames;
	}

	public void setFbkCarDcnames(Set fbkCarDcnames) {
		this.fbkCarDcnames = fbkCarDcnames;
	}

	public Set getFbkProgramTracks() {
		return this.fbkProgramTracks;
	}

	public void setFbkProgramTracks(Set fbkProgramTracks) {
		this.fbkProgramTracks = fbkProgramTracks;
	}

}