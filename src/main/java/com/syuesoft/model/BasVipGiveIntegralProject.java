package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasVipGiveIntegralProject entity. @author MyEclipse Persistence Tools
 */

public class BasVipGiveIntegralProject extends BaseBean{

	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer giveInteProId;             //赠送项目编号
	private String giveInteProName;            //赠送项目名称
	private Double giveInteNum;               //赠送项目积分
	private Set basVipRelationship16s = new HashSet(0);
	private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }

	/** default constructor */
	public BasVipGiveIntegralProject() {
	}

	public Integer getGiveInteProId() {
		return this.giveInteProId;
	}

	public void setGiveInteProId(Integer giveInteProId) {
		this.giveInteProId = giveInteProId;
	}

	public String getGiveInteProName() {
		return this.giveInteProName;
	}

	public void setGiveInteProName(String giveInteProName) {
		this.giveInteProName = giveInteProName;
	}

	public Double getGiveInteNum() {
		return this.giveInteNum;
	}

	public void setGiveInteNum(Double giveInteNum) {
		this.giveInteNum = giveInteNum;
	}

	public Set getBasVipRelationship16s() {
		return this.basVipRelationship16s;
	}

	public void setBasVipRelationship16s(Set basVipRelationship16s) {
		this.basVipRelationship16s = basVipRelationship16s;
	}

}