package com.syuesoft.model;

public class SubstituteBalance extends BaseBean {
	private Integer sbId;
	private String sspId;
	private String strikeId;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	public SubstituteBalance() {
		// TODO Auto-generated constructor stub
	}

	public Integer getSbId() {
		return sbId;
	}

	public void setSbId(Integer sbId) {
		this.sbId = sbId;
	}

	public String getSspId() {
		return sspId;
	}

	public void setSspId(String sspId) {
		this.sspId = sspId;
	}

	public String getStrikeId() {
		return strikeId;
	}

	public void setStrikeId(String strikeId) {
		this.strikeId = strikeId;
	}

}
