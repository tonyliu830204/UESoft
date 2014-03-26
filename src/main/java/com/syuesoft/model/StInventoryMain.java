package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StInventoryMain entity. @author MyEclipse Persistence Tools
 */

public class StInventoryMain extends BaseBean {

	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private String stinvmId;                        //盘点单号
	private Long stfId;                             //操作员
	private Short storeId;                          //仓别
	private Date stinvmTime;                        //盘点日期
	private Integer syinvmSumCount;                 //配件总数
	private Double stinvmSumAmount;                 //配件含税金额
	private Double stinvmSumCost;                   //配件未税金额
	private String stinvmState;                     //盘点状态
	private String stinvmRemark;                    //备注
	private Set<StInventoryDetail> stInventoryDetails = new HashSet<StInventoryDetail>(0);
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	// Constructors

	/** default constructor */
	public StInventoryMain() {
	}

	/** minimal constructor */
	public StInventoryMain(String stinvmId) {
		this.stinvmId = stinvmId;
	}

	/** full constructor */
	public StInventoryMain(String stinvmId, Long stfId, Date stinvmTime,
			Integer syinvmSumCount, Double stinvmSumAmount,
			Double stinvmSumCost, String stinvmState, String stinvmRemark,
			Set<StInventoryDetail> stInventoryDetails) {
		this.stinvmId = stinvmId;
		this.stfId = stfId;
		this.stinvmTime = stinvmTime;
		this.syinvmSumCount = syinvmSumCount;
		this.stinvmSumAmount = stinvmSumAmount;
		this.stinvmSumCost = stinvmSumCost;
		this.stinvmState = stinvmState;
		this.stinvmRemark = stinvmRemark;
		this.stInventoryDetails = stInventoryDetails;
	}

	// Property accessors

	public String getStinvmId() {
		return this.stinvmId;
	}

	public void setStinvmId(String stinvmId) {
		this.stinvmId = stinvmId;
	}

	public Long getStfId() {
		return this.stfId;
	}

	public void setStfId(Long stfId) {
		this.stfId = stfId;
	}

	public Date getStinvmTime() {
		return this.stinvmTime;
	}

	public void setStinvmTime(Date stinvmTime) {
		this.stinvmTime = stinvmTime;
	}

	public Integer getSyinvmSumCount() {
		return this.syinvmSumCount;
	}

	public void setSyinvmSumCount(Integer syinvmSumCount) {
		this.syinvmSumCount = syinvmSumCount;
	}

	public Double getStinvmSumAmount() {
		return this.stinvmSumAmount;
	}

	public void setStinvmSumAmount(Double stinvmSumAmount) {
		this.stinvmSumAmount = stinvmSumAmount;
	}

	public Double getStinvmSumCost() {
		return this.stinvmSumCost;
	}

	public void setStinvmSumCost(Double stinvmSumCost) {
		this.stinvmSumCost = stinvmSumCost;
	}

	public String getStinvmState() {
		return this.stinvmState;
	}

	public void setStinvmState(String stinvmState) {
		this.stinvmState = stinvmState;
	}

	public String getStinvmRemark() {
		return this.stinvmRemark;
	}

	public void setStinvmRemark(String stinvmRemark) {
		this.stinvmRemark = stinvmRemark;
	}

	public Set<StInventoryDetail> getStInventoryDetails() {
		return this.stInventoryDetails;
	}

	public void setStInventoryDetails(Set<StInventoryDetail> stInventoryDetails) {
		this.stInventoryDetails = stInventoryDetails;
	}

    public Short getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Short storeId)
    {
        this.storeId = storeId;
    }
     
}