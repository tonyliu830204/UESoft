package com.syuesoft.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * StSellCharge entity. @author MyEclipse Persistence Tools
 */

public class StSellCharge  extends BaseBean {


    // Fields    

     private String chargeId;
     private String preclrId;
     private Boolean stockOut;
     private Double paidAmount;
     private Double totalAmount;
     private Double paidBalance;
     private String verifyState;
     private Date verifyDate;
     private String remark;
     private Boolean state;
     private Set stSellChargeitems = new HashSet(0);
     private Short chargeUnFinished;
     private Short chargeAllowArrearsAge;
     private Short chargeReceivablesArrearsAge;
     private Short chargeAllowFlag;
     private Double chargeAllowArrearsAmount;
     private Short chargeAllowArrearsNumber;
     private Boolean chargePrintFlag;
     private Short chargeSubstitute;
     private Short chargeBatchTag;
     private Integer enterpriseId;

     public Integer getEnterpriseId() {
         return enterpriseId;
     }

     public void setEnterpriseId(Integer enterpriseId) {
         this.enterpriseId = enterpriseId;
     }


    // Constructors

    /** default constructor */
    public StSellCharge() {
    }

	/** minimal constructor */
    public StSellCharge(String chargeId) {
        this.chargeId = chargeId;
    }
    
    /** full constructor */
    public StSellCharge(String chargeId, String preclrId, Boolean stockOut, Double paidAmount, Double totalAmount, Double paidBalance, String verifyState, Date verifyDate, String remark,Boolean state, Set stSellChargeitems) {
        this.chargeId = chargeId;
        this.preclrId = preclrId;
        this.stockOut = stockOut;
        this.paidAmount = paidAmount;
        this.totalAmount = totalAmount;
        this.paidBalance = paidBalance;
        this.verifyState = verifyState;
        this.verifyDate = verifyDate;
        this.remark = remark;
        this.state=state;
        this.stSellChargeitems = stSellChargeitems;
    }

   
    // Property accessors

    public String getChargeId() {
        return this.chargeId;
    }
    
    public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getPreclrId() {
        return this.preclrId;
    }
    
    public void setPreclrId(String preclrId) {
        this.preclrId = preclrId;
    }

    public Boolean getStockOut() {
        return this.stockOut;
    }
    
    public void setStockOut(Boolean stockOut) {
        this.stockOut = stockOut;
    }

    public Double getPaidAmount() {
        return this.paidAmount;
    }
    
    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPaidBalance() {
        return this.paidBalance;
    }
    
    public void setPaidBalance(Double paidBalance) {
        this.paidBalance = paidBalance;
    }

    public String getVerifyState() {
        return this.verifyState;
    }
    
    public void setVerifyState(String verifyState) {
        this.verifyState = verifyState;
    }

    public Date getVerifyDate() {
        return this.verifyDate;
    }
    
    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getStSellChargeitems() {
        return this.stSellChargeitems;
    }
    
    public void setStSellChargeitems(Set stSellChargeitems) {
        this.stSellChargeitems = stSellChargeitems;
    }

	public Short getChargeUnFinished() {
		return chargeUnFinished;
	}

	public void setChargeUnFinished(Short chargeUnFinished) {
		this.chargeUnFinished = chargeUnFinished;
	}

	public Short getChargeAllowArrearsAge() {
		return chargeAllowArrearsAge;
	}

	public void setChargeAllowArrearsAge(Short chargeAllowArrearsAge) {
		this.chargeAllowArrearsAge = chargeAllowArrearsAge;
	}

	public Short getChargeReceivablesArrearsAge() {
		return chargeReceivablesArrearsAge;
	}

	public void setChargeReceivablesArrearsAge(Short chargeReceivablesArrearsAge) {
		this.chargeReceivablesArrearsAge = chargeReceivablesArrearsAge;
	}

	public Short getChargeAllowFlag() {
		return chargeAllowFlag;
	}

	public void setChargeAllowFlag(Short chargeAllowFlag) {
		this.chargeAllowFlag = chargeAllowFlag;
	}

	public Double getChargeAllowArrearsAmount() {
		return chargeAllowArrearsAmount;
	}

	public void setChargeAllowArrearsAmount(Double chargeAllowArrearsAmount) {
		this.chargeAllowArrearsAmount = chargeAllowArrearsAmount;
	}

	public Short getChargeAllowArrearsNumber() {
		return chargeAllowArrearsNumber;
	}

	public void setChargeAllowArrearsNumber(Short chargeAllowArrearsNumber) {
		this.chargeAllowArrearsNumber = chargeAllowArrearsNumber;
	}

	public Boolean getChargePrintFlag() {
		return chargePrintFlag;
	}

	public void setChargePrintFlag(Boolean chargePrintFlag) {
		this.chargePrintFlag = chargePrintFlag;
	}

	public Short getChargeSubstitute() {
		return chargeSubstitute;
	}

	public void setChargeSubstitute(Short chargeSubstitute) {
		this.chargeSubstitute = chargeSubstitute;
	}

	public Short getChargeBatchTag() {
		return chargeBatchTag;
	}

	public void setChargeBatchTag(Short chargeBatchTag) {
		this.chargeBatchTag = chargeBatchTag;
	}
   
}