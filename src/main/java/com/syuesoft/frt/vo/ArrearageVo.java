package com.syuesoft.frt.vo;

public class ArrearageVo {

	private String receptionId;
	private String preclrId;
	private String preclrTime;
	private Double receivables;
	private Double arrears;
	public ArrearageVo() {
		// TODO Auto-generated constructor stub
	}
	public String getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}
	public String getPreclrId() {
		return preclrId;
	}
	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}
	public String getPreclrTime() {
		return preclrTime;
	}
	public void setPreclrTime(String preclrTime) {
		this.preclrTime = preclrTime;
	}
	public Double getReceivables() {
		return receivables;
	}
	public void setReceivables(Double receivables) {
		this.receivables = receivables;
	}
	public Double getArrears() {
		return arrears;
	}
	public void setArrears(Double arrears) {
		this.arrears = arrears;
	}

}
