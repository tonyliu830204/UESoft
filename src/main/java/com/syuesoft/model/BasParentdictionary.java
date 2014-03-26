package com.syuesoft.model;
// default package

import java.sql.Timestamp;


/**
 * BasParentdictionary entity. @author MyEclipse Persistence Tools
 */

public class BasParentdictionary  extends BaseBean{


    // Fields    

     private Long parentId;
     private Short stfId;
     private Timestamp createTime;
     private String dataKey;
     private String dataValue;
   //以下为分页字段
     private String sort;
 	private String order;
 	private int rows;
 	private int page;
 	private String ptypeName;

    // Constructors

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

	public String getPtypeName() {
		return ptypeName;
	}

	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
	}

	/** default constructor */
    public BasParentdictionary() {
    }

	/** minimal constructor */
    public BasParentdictionary(Long parentId) {
        this.parentId = parentId;
    }
    
    /** full constructor */
    public BasParentdictionary(Long parentId, Short stfId, Timestamp createTime, String dataKey, String dataValue) {
        this.parentId = parentId;
        this.stfId = stfId;
        this.createTime = createTime;
        this.dataKey = dataKey;
        this.dataValue = dataValue;
    }

   
    // Property accessors

    public Long getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Short getStfId() {
        return this.stfId;
    }
    
    public void setStfId(Short stfId) {
        this.stfId = stfId;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDataKey() {
        return this.dataKey;
    }
    
    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return this.dataValue;
    }
    
    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
   








}