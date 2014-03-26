package com.syuesoft.model;



/**
 * StUsePercharge entity. @author MyEclipse Persistence Tools
 */

public class StUsePercharge  extends BaseBean {


    // Fields    

     private Integer stUseId;
     private StRecharge stRecharge;
     private BasStuff basStuff;
     private Double useAmount;
     private Double backAmount;
     private String repcollId;
     private Integer enterpriseId;

     public Integer getEnterpriseId() {
         return enterpriseId;
     }

     public void setEnterpriseId(Integer enterpriseId) {
         this.enterpriseId = enterpriseId;
     }

    // Constructors

    /** default constructor */
    public StUsePercharge() {
    }

	/** minimal constructor */
    public StUsePercharge(Integer stUseId) {
        this.stUseId = stUseId;
    }
    
    /** full constructor */
    public StUsePercharge(Integer stUseId, StRecharge stRecharge, BasStuff basStuff, Double useAmount, Double backAmount, String repcollId) {
        this.stUseId = stUseId;
        this.stRecharge = stRecharge;
        this.basStuff = basStuff;
        this.useAmount = useAmount;
        this.backAmount = backAmount;
        this.repcollId = repcollId;
    }

   
    // Property accessors

    public Integer getStUseId() {
        return this.stUseId;
    }
    
    public void setStUseId(Integer stUseId) {
        this.stUseId = stUseId;
    }

    public StRecharge getStRecharge() {
        return this.stRecharge;
    }
    
    public void setStRecharge(StRecharge stRecharge) {
        this.stRecharge = stRecharge;
    }

    public BasStuff getBasStuff() {
        return this.basStuff;
    }
    
    public void setBasStuff(BasStuff basStuff) {
        this.basStuff = basStuff;
    }

    public Double getUseAmount() {
        return this.useAmount;
    }
    
    public void setUseAmount(Double useAmount) {
        this.useAmount = useAmount;
    }

    public Double getBackAmount() {
        return this.backAmount;
    }
    
    public void setBackAmount(Double backAmount) {
        this.backAmount = backAmount;
    }

    public String getRepcollId() {
        return this.repcollId;
    }
    
    public void setRepcollId(String repcollId) {
        this.repcollId = repcollId;
    }
   








}