package com.syuesoft.model;
// default package

import java.sql.Timestamp;


/**
 * BasChilddictionary entity. @author MyEclipse Persistence Tools
 */

public class BasChilddictionary extends BaseBean{


    // Fields    

     private Long childId;
     private Long parentId;
     private Short stfId;
     private Timestamp createTime;
     private String dataKey;
     private String dataValue;
     // 以下为父级数据字典填充数据的字段
     private Long pparentId;
     private Short pstfId;
     private Timestamp pcreateTime;
     private String pdataKey;
     private String pdataValue;
     //以下为分页字段
     private String sort;
 	private String order;
 	private int rows;
 	private int page;
 	private String ptypeName;
    // Constructors
 	//查询用到的字段
 	private String querydataKey;
 	private String querydataValue;
 	
    public String getQuerydataKey() {
		return querydataKey;
	}

	public void setQuerydataKey(String querydataKey) {
		this.querydataKey = querydataKey;
	}

	public String getQuerydataValue() {
		return querydataValue;
	}

	public void setQuerydataValue(String querydataValue) {
		this.querydataValue = querydataValue;
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

	public String getPtypeName() {
		return ptypeName;
	}

	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
	}

	public Long getPparentId() {
		return pparentId;
	}

	public Short getPstfId() {
		return pstfId;
	}

	public void setPstfId(Short pstfId) {
		this.pstfId = pstfId;
	}
	public void setPstfId(String pstfId) {
		if(pstfId!=null&&(!"".equals(pstfId))&&pstfId.length()>0)
		this.pstfId =Short.parseShort(pstfId) ;
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

	public void setPparentId(Long pparentId) {
		this.pparentId = pparentId;
	}
	public void setPparentId(String pparentId) {
		if(pparentId!=null&&pparentId.length()>0)
		this.pparentId =Long.parseLong(pparentId) ;
	}
	
	/** default constructor */
    public BasChilddictionary() {
    }

	/** minimal constructor */
    public BasChilddictionary(Long childId) {
        this.childId = childId;
    }
    
    /** full constructor */
    public BasChilddictionary(Long childId, Long parentId, Short stfId, Timestamp createTime, String dataKey, String dataValue) {
        this.childId = childId;
        this.parentId = parentId;
        this.stfId = stfId;
        this.createTime = createTime;
        this.dataKey = dataKey;
        this.dataValue = dataValue;
    }

   
    // Property accessors

    public Long getChildId() {
        return this.childId;
    }
    
    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public void setParentId(String parentId) {
    	if(parentId!=null&&parentId.length()>0)
        this.parentId =Long.parseLong(parentId) ;
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