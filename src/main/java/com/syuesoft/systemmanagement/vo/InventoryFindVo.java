package com.syuesoft.systemmanagement.vo;

public class InventoryFindVo {
	private int page;
	private int rows;
	private String sort;
	private String order;// 
	
	private String parts_Id;  //配件编号
	private String parts_Name; //配件名称
	private String punit_Name; //单位
	private String syinvm_Sum_Count; //盘点数量
	private String syinvm_Sum_Amount; //盘点合计金额
	private String stinvm_Id;  //盘点单号
	private String stinvm_Time; //盘点时间
	private String stinvd_Count; //配件数量
	private String stinvd_Price; // 单价
	private String stinvd_Cost;   //小计金额
	private Integer enterpriseId;
	private String state;
    private String iconCls; 


	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getParts_Name() {
		return parts_Name;
	}
	public void setParts_Name(String partsName) {
		parts_Name = partsName;
	}
	public String getPunit_Name() {
		return punit_Name;
	}
	public void setPunit_Name(String punitName) {
		punit_Name = punitName;
	}
	public String getSyinvm_Sum_Count() {
		return syinvm_Sum_Count;
	}
	public void setSyinvm_Sum_Count(String syinvmSumCount) {
		syinvm_Sum_Count = syinvmSumCount;
	}
	public String getSyinvm_Sum_Amount() {
		return syinvm_Sum_Amount;
	}
	public void setSyinvm_Sum_Amount(String syinvmSumAmount) {
		syinvm_Sum_Amount = syinvmSumAmount;
	}
	public String getStinvm_Id() {
		return stinvm_Id;
	}
	public void setStinvm_Id(String stinvmId) {
		stinvm_Id = stinvmId;
	}
	public String getStinvm_Time() {
		return stinvm_Time;
	}
	public void setStinvm_Time(String stinvmTime) {
		stinvm_Time = stinvmTime;
	}
	public String getStinvd_Count() {
		return stinvd_Count;
	}
	public void setStinvd_Count(String stinvdCount) {
		stinvd_Count = stinvdCount;
	}
	public String getStinvd_Price() {
		return stinvd_Price;
	}
	public void setStinvd_Price(String stinvdPrice) {
		stinvd_Price = stinvdPrice;
	}
	public String getStinvd_Cost() {
		return stinvd_Cost;
	}
	public void setStinvd_Cost(String stinvdCost) {
		stinvd_Cost = stinvdCost;
	}
	public String getParts_Id() {
		return parts_Id;
	}
	public void setParts_Id(String partsId) {
		parts_Id = partsId;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }
    public void setEnterpriseId(Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }
    
    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }
    
    public String getIconCls()
    {
        return this.iconCls;
    }
}