package com.syuesoft.model;

/**
 * FbkProgramTrack entity. @author MyEclipse Persistence Tools
 */

public class FbkProgramTrack extends BaseBean {

	// Fields

	private Integer fptId;
	private FbkDcserveyName fbkDcserveyName;
	private String pingjia;

	// Constructors

	/** default constructor */
	public FbkProgramTrack() {
	}

	/** full constructor */
	public FbkProgramTrack(FbkDcserveyName fbkDcserveyName, String pingjia) {
		this.fbkDcserveyName = fbkDcserveyName;
		this.pingjia = pingjia;
	}

	// Property accessors

	public Integer getFptId() {
		return this.fptId;
	}

	public void setFptId(Integer fptId) {
		this.fptId = fptId;
	}

	public FbkDcserveyName getFbkDcserveyName() {
		return this.fbkDcserveyName;
	}

	public void setFbkDcserveyName(FbkDcserveyName fbkDcserveyName) {
		this.fbkDcserveyName = fbkDcserveyName;
	}

	public String getPingjia() {
		return this.pingjia;
	}

	public void setPingjia(String pingjia) {
		this.pingjia = pingjia;
	}

}