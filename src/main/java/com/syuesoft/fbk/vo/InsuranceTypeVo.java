package com.syuesoft.fbk.vo;

public class InsuranceTypeVo {
	private String id;
	private int page;
	private int rows;
	private Integer enterpriseId;
	private Integer nowEnterpriseId;
   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }   
	public Integer getNowEnterpriseId() {
		return nowEnterpriseId;
	}

	public void setNowEnterpriseId(Integer nowEnterpriseId) {
		this.nowEnterpriseId = nowEnterpriseId;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getId() {
		return id;
	}

    public void setId(String id)
    {
        this.id = id;
    }
	public String getIntype() {
		return intype;
	}
	public void setIntype(String intype) {
		this.intype = intype;
	}
	public String getIncount() {
		return incount;
	}
	public void setIncount(String incount) {
		this.incount = incount;
	}
	public String getInfeelv() {
		return infeelv;
	}
	public void setInfeelv(String infeelv) {
		this.infeelv = infeelv;
	}
	public String getInfee() {
		return infee;
	}
	public void setInfee(String infee) {
		this.infee = infee;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	private String intype;
	private String incount;
	private String infeelv;
	private String infee;
	private String memo;
}
