package com.syuesoft.systemmanagement.vo;

import java.sql.Timestamp;

public class DataBackupVo {
		
	private int page;
	private int rows;
	private String sort;
	private String order;// 
	
	private String Id;
	private String data_Backup_Time;
	private String data_Back_Name;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
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
	public String getData_Backup_Time() {
		return data_Backup_Time;
	}
	public void setData_Backup_Time(String dataBackupTime) {
		data_Backup_Time = dataBackupTime;
	}
	public String getData_Back_Name() {
		return data_Back_Name;
	}
	public void setData_Back_Name(String dataBackName) {
		data_Back_Name = dataBackName;
	}
	

	
}
