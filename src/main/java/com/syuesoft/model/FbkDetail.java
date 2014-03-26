package com.syuesoft.model;

/**
 * FbkDetail entity. @author MyEclipse Persistence Tools
 */

public class FbkDetail extends BaseBean {

	// Fields

	private Integer detailId;
	private FbkCollect fbkCollect;
	private String serviceProposal; //维修建议
	private String complaintType;  //投诉类型
	private String complaintDegree; //投诉程度
	private String complaintContent; //投诉内容或客户意见及建议
	private String handleResult;   //处理结果
	private String handleProgram;  //处理方案
	private String memo;
	private Integer complaintQK; //投诉情况      complaintQK
	// Constructors

   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	/** default constructor */
	public FbkDetail() {
	}

	/** full constructor */
	public FbkDetail(FbkCollect fbkCollect, String serviceProposal,
			String complaintType, String complaintDegree,
			String complaintContent, String handleResult, String handleProgram,
			String memo,Integer complaintQK) {
		this.fbkCollect = fbkCollect;
		this.serviceProposal = serviceProposal;
		this.complaintType = complaintType;
		this.complaintDegree = complaintDegree;
		this.complaintContent = complaintContent;
		this.handleResult = handleResult;
		this.handleProgram = handleProgram;
		this.memo = memo;
		this.complaintQK = complaintQK;
	}

	// Property accessors

	public Integer getDetailId() {
		return this.detailId;
	}

	public Integer getComplaintQK() {
		return complaintQK;
	}

	public void setComplaintQK(Integer complaintQK) {
		this.complaintQK = complaintQK;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public FbkCollect getFbkCollect() {
		return this.fbkCollect;
	}

	public void setFbkCollect(FbkCollect fbkCollect) {
		this.fbkCollect = fbkCollect;
	}

	public String getServiceProposal() {
		return this.serviceProposal;
	}

	public void setServiceProposal(String serviceProposal) {
		this.serviceProposal = serviceProposal;
	}

	public String getComplaintType() {
		return this.complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	public String getComplaintDegree() {
		return this.complaintDegree;
	}

	public void setComplaintDegree(String complaintDegree) {
		this.complaintDegree = complaintDegree;
	}

	public String getComplaintContent() {
		return this.complaintContent;
	}

	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent;
	}

	public String getHandleResult() {
		return this.handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getHandleProgram() {
		return this.handleProgram;
	}

	public void setHandleProgram(String handleProgram) {
		this.handleProgram = handleProgram;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}