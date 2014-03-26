package com.syuesoft.model;



/**
 * StMoveStorehouseDetail entity. @author MyEclipse Persistence Tools
 */

public class StMoveStorehouseDetail  extends BaseBean {


    // Fields    

     private Integer msdIndex;
     private StMoveStorehouseMain stMoveStorehouseMain;
     private String msdPartsid;
     private double msdCnt;
     private Double msdNocast;
     private Double msdNocastAmont;
     private String msdRemark;


    // Constructors

    /** default constructor */
    public StMoveStorehouseDetail() {
    }

	/** minimal constructor */
    public StMoveStorehouseDetail(Integer msdIndex) {
        this.msdIndex = msdIndex;
    }
    
    /** full constructor */
    public StMoveStorehouseDetail(Integer msdIndex, StMoveStorehouseMain stMoveStorehouseMain, String msdPartsid, double msdCnt, Double msdNocast, Double msdNocastAmont, String msdRemark) {
        this.msdIndex = msdIndex;
        this.stMoveStorehouseMain = stMoveStorehouseMain;
        this.msdPartsid = msdPartsid;
        this.msdCnt = msdCnt;
        this.msdNocast = msdNocast;
        this.msdNocastAmont = msdNocastAmont;
        this.msdRemark = msdRemark;
    }

   
    // Property accessors

    public Integer getMsdIndex() {
        return this.msdIndex;
    }
    
    public void setMsdIndex(Integer msdIndex) {
        this.msdIndex = msdIndex;
    }

    public StMoveStorehouseMain getStMoveStorehouseMain() {
        return this.stMoveStorehouseMain;
    }
    
    public void setStMoveStorehouseMain(StMoveStorehouseMain stMoveStorehouseMain) {
        this.stMoveStorehouseMain = stMoveStorehouseMain;
    }

    public String getMsdPartsid() {
        return this.msdPartsid;
    }
    
    public void setMsdPartsid(String msdPartsid) {
        this.msdPartsid = msdPartsid;
    }

    public double getMsdCnt() {
        return this.msdCnt;
    }
    
    public void setMsdCnt(double msdCnt) {
        this.msdCnt = msdCnt;
    }

    public Double getMsdNocast() {
        return this.msdNocast;
    }
    
    public void setMsdNocast(Double msdNocast) {
        this.msdNocast = msdNocast;
    }

    public Double getMsdNocastAmont() {
        return this.msdNocastAmont;
    }
    
    public void setMsdNocastAmont(Double msdNocastAmont) {
        this.msdNocastAmont = msdNocastAmont;
    }

    public String getMsdRemark() {
        return this.msdRemark;
    }
    
    public void setMsdRemark(String msdRemark) {
        this.msdRemark = msdRemark;
    }
   








}