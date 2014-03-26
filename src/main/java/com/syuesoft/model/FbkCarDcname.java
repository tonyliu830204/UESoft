package com.syuesoft.model;

/**
 * FbkCarDcname entity. @author MyEclipse Persistence Tools
 */

public class FbkCarDcname extends BaseBean {

	// Fields
	private Integer id;
	private FbkDcserveyName fbkDcserveyName;
	private FbkCollect fbkCollect;
	private String evaluate;
	private String score;
	private String note;
	 private Integer enterpriseId;
	
	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }
	
	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }


	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FbkDcserveyName getFbkDcserveyName() {
		return fbkDcserveyName;
	}

	public void setFbkDcserveyName(FbkDcserveyName fbkDcserveyName) {
		this.fbkDcserveyName = fbkDcserveyName;
	}

	public FbkCollect getFbkCollect() {
		return fbkCollect;
	}

	public void setFbkCollect(FbkCollect fbkCollect) {
		this.fbkCollect = fbkCollect;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

}