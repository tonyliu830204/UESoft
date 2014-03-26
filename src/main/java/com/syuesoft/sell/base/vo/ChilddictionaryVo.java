package com.syuesoft.sell.base.vo;
// default package

import java.sql.Timestamp;

/**
 * BasChilddictionary entity. @author MyEclipse Persistence Tools
 */

public class ChilddictionaryVo  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer enterpriseId;
	private Integer childId;
     private Integer parentId;
     private Long stfId;
     private String stfName;
     public String getStfName() {
		return stfName;
	}
	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
	private Timestamp createTime;
     private String dataKey;
     private String dataValue;
     private String remark;
     // 以下为父级数据字典填充数据的字段
     private Integer pparentId;
     private Long pstfId;
     private Timestamp pcreateTime;
     private String pdataKey;
     private String pdataValue;
     private String premark;
     //以下为分页字段
    private String sort;
 	private String order;
 	private int rows;
 	private int page;
    // Constructors
 	//查询用到的字段
 	private String cquerydataKey;
 	private String cquerydataValue;
	private String pqueryKey;
 	private String pqueryValue;
 	private String baseKey;

 	private String lable;//页面表格dataKey列的列名
 	
 	private String q;
 	private String types;
 	
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public Integer getChildId() {
		return childId;
	}
	public void setChildId(Integer childId) {
		this.childId = childId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Long getStfId() {
		return stfId;
	}
	public void setStfId(Long stfId) {
		this.stfId = stfId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getDataKey() {
		return dataKey;
	}
	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}
	public String getDataValue() {
		return dataValue;
	}
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPparentId() {
		return pparentId;
	}
	public void setPparentId(Integer pparentId) {
		this.pparentId = pparentId;
	}
	public Long getPstfId() {
		return pstfId;
	}
	public void setPstfId(Long pstfId) {
		this.pstfId = pstfId;
	}
	public Timestamp getPcreateTime() {
		return pcreateTime;
	}
	public void setPcreateTime(Timestamp pcreateTime) {
		this.pcreateTime = pcreateTime;
	}
	public String getPdataKey() {
		return pdataKey;
	}
	public void setPdataKey(String pdataKey) {
		this.pdataKey = pdataKey;
	}
	public String getPdataValue() {
		return pdataValue;
	}
	public void setPdataValue(String pdataValue) {
		this.pdataValue = pdataValue;
	}
	public String getPremark() {
		return premark;
	}
	public void setPremark(String premark) {
		this.premark = premark;
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
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getCquerydataKey() {
		return cquerydataKey;
	}
	public void setCquerydataKey(String cquerydataKey) {
		this.cquerydataKey = cquerydataKey;
	}
	public String getCquerydataValue() {
		return cquerydataValue;
	}
	public void setCquerydataValue(String cquerydataValue) {
		this.cquerydataValue = cquerydataValue;
	}
	public String getPqueryKey() {
		return pqueryKey;
	}
	public void setPqueryKey(String pqueryKey) {
		this.pqueryKey = pqueryKey;
	}
	public String getPqueryValue() {
		return pqueryValue;
	}
	public void setPqueryValue(String pqueryValue) {
		this.pqueryValue = pqueryValue;
	}
	public String getBaseKey() {
		return baseKey;
	}
	public void setBaseKey(String baseKey) {
		this.baseKey = baseKey;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
}