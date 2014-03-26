package com.syuesoft.model;

/**
 * BasCarBodysStatus entity. @author MyEclipse Persistence Tools
 */


public class BasCarBodysStatus extends BaseBean {


    // Fields    

     private Short bodyNum;
     private String bodyId;
     private String bodyName;
     private String bodyRemark;

    // Constructors

    /** default constructor */
    public BasCarBodysStatus() {
    }

	/** minimal constructor */
    public BasCarBodysStatus(String bodyId, String bodyName) {
        this.bodyId = bodyId;
        this.bodyName = bodyName;
    }
    
    /** full constructor */
    public BasCarBodysStatus(String bodyId, String bodyName, String bodyRemark) {
        this.bodyId = bodyId;
        this.bodyName = bodyName;
        this.bodyRemark = bodyRemark;
    }

   
    // Property accessors

    public Short getBodyNum() {
        return this.bodyNum;
    }
    
    public void setBodyNum(Short bodyNum) {
        this.bodyNum = bodyNum;
    }

    public String getBodyId() {
        return this.bodyId;
    }
    
    public void setBodyId(String bodyId) {
        this.bodyId = bodyId;
    }

    public String getBodyName() {
        return this.bodyName;
    }
    
    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public String getBodyRemark() {
        return this.bodyRemark;
    }
    
    public void setBodyRemark(String bodyRemark) {
        this.bodyRemark = bodyRemark;
    }


}