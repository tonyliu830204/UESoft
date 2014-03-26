package com.syuesoft.sell.model;
// default package

import java.sql.Timestamp;


/**
 * BasParentdictionary entity. @author MyEclipse Persistence Tools
 */

public class XsParentdictionary  implements java.io.Serializable {


    // Fields    

     private Integer parentId;
     private Long stfId;
     private String stfName;
     private Timestamp createTime;
     private String dataKey;
     private String dataValue;
     private String remark;

    public Integer getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Long getStfId() {
        return this.stfId;
    }
    
    public void setStfId(Long stfId) {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
}