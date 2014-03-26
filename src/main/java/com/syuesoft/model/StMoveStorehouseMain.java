package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * StMoveStorehouseMain entity. @author MyEclipse Persistence Tools
 */


public class StMoveStorehouseMain  extends BaseBean {


    // Fields    

     private String msdmId;
     private Date msdmDate;
     private Short msdmManager;
     private Double msdmSumCnt;
     private Double msdmSumAmont;
     private Short msdmInStorehouseId;
     private Short msdmOutStorehouseId;
     private String msdmRemark;
     private Short examineState;
     private Set stMoveStorehouseDetails = new HashSet(0);
     private Integer enterpriseId;

     public Integer getEnterpriseId() {
         return enterpriseId;
     }

     public void setEnterpriseId(Integer enterpriseId) {
         this.enterpriseId = enterpriseId;
     }

    // Constructors

    /** default constructor */
    public StMoveStorehouseMain() {
    }

	/** minimal constructor */
    public StMoveStorehouseMain(String msdmId) {
        this.msdmId = msdmId;
    }
    
    /** full constructor */
    public StMoveStorehouseMain(String msdmId, Date msdmDate, Short msdmManager, Double msdmSumCnt, Double msdmSumAmont, Short msdmInStorehouseId, Short msdmOutStorehouseId, String msdmRemark, Set stMoveStorehouseDetails) {
        this.msdmId = msdmId;
        this.msdmDate = msdmDate;
        this.msdmManager = msdmManager;
        this.msdmSumCnt = msdmSumCnt;
        this.msdmSumAmont = msdmSumAmont;
        this.msdmInStorehouseId = msdmInStorehouseId;
        this.msdmOutStorehouseId = msdmOutStorehouseId;
        this.msdmRemark = msdmRemark;
        this.stMoveStorehouseDetails = stMoveStorehouseDetails;
    }

   
    // Property accessors

    public String getMsdmId() {
        return this.msdmId;
    }
    
    public Short getExamineState() {
		return examineState;
	}

	public void setExamineState(Short examineState) {
		this.examineState = examineState;
	}

	public void setMsdmId(String msdmId) {
        this.msdmId = msdmId;
    }

    public Date getMsdmDate() {
        return this.msdmDate;
    }
    
    public void setMsdmDate(Date msdmDate) {
        this.msdmDate = msdmDate;
    }

    public Short getMsdmManager() {
        return this.msdmManager;
    }
    
    public void setMsdmManager(Short msdmManager) {
        this.msdmManager = msdmManager;
    }

    public Double getMsdmSumCnt() {
        return this.msdmSumCnt;
    }
    
    public void setMsdmSumCnt(Double msdmSumCnt) {
        this.msdmSumCnt = msdmSumCnt;
    }

    public Double getMsdmSumAmont() {
        return this.msdmSumAmont;
    }
    
    public void setMsdmSumAmont(Double msdmSumAmont) {
        this.msdmSumAmont = msdmSumAmont;
    }

    public Short getMsdmInStorehouseId() {
        return this.msdmInStorehouseId;
    }
    
    public void setMsdmInStorehouseId(Short msdmInStorehouseId) {
        this.msdmInStorehouseId = msdmInStorehouseId;
    }

    public Short getMsdmOutStorehouseId() {
        return this.msdmOutStorehouseId;
    }
    
    public void setMsdmOutStorehouseId(Short msdmOutStorehouseId) {
        this.msdmOutStorehouseId = msdmOutStorehouseId;
    }

    public String getMsdmRemark() {
        return this.msdmRemark;
    }
    
    public void setMsdmRemark(String msdmRemark) {
        this.msdmRemark = msdmRemark;
    }

    public Set getStMoveStorehouseDetails() {
        return this.stMoveStorehouseDetails;
    }
    
    public void setStMoveStorehouseDetails(Set stMoveStorehouseDetails) {
        this.stMoveStorehouseDetails = stMoveStorehouseDetails;
    }
   








}