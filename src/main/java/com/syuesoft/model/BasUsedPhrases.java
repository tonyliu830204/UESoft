package com.syuesoft.model;



/**
 * BasUsedPhrases entity. @author MyEclipse Persistence Tools
 */

public class BasUsedPhrases extends BaseBean{


    // Fields    

     private Integer phrasesId;
     private String phrassesName;
     private String memo;
     private Integer enterpriseId;

     public Integer getEnterpriseId() {
         return enterpriseId;
     }

     public void setEnterpriseId(Integer enterpriseId) {
         this.enterpriseId = enterpriseId;
     }


    // Constructors

    /** default constructor */
    public BasUsedPhrases() {
    }

    
    /** full constructor */
    public BasUsedPhrases(String phrassesName, String memo) {
        this.phrassesName = phrassesName;
        this.memo = memo;
    }

   
    // Property accessors

    public Integer getPhrasesId() {
        return this.phrasesId;
    }
    
    public void setPhrasesId(Integer phrasesId) {
        this.phrasesId = phrasesId;
    }

    public String getPhrassesName() {
        return this.phrassesName;
    }
    
    public void setPhrassesName(String phrassesName) {
        this.phrassesName = phrassesName;
    }

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
   








}