package com.syuesoft.model;

/**
 * BasVipIntegralRegulation entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BasVipIntegralRegulation extends BaseBean{

	private Integer vipInteReg;
	private BasVipLevel basVipLevel;
	private Integer reptypId;
	private double beginAmount;
	private Integer jfAmont;
	private Integer score;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
    
	/** full constructor */
	public BasVipIntegralRegulation(){
		
	}
	
	/** full constructor */
	public BasVipIntegralRegulation(BasVipLevel basVipLevel, Integer reptypId,
			double beginAmount, Integer jfAmont, Integer score) {
		this.basVipLevel = basVipLevel;
		this.reptypId = reptypId;
		this.beginAmount = beginAmount;
		this.jfAmont = jfAmont;
		this.score = score;
	}

	public Integer getJfAmont() {
		return jfAmont;
	}

	public void setJfAmont(Integer jfAmont) {
		this.jfAmont = jfAmont;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getVipInteReg() {
		return this.vipInteReg;
	}

	public void setVipInteReg(Integer vipInteReg) {
		this.vipInteReg = vipInteReg;
	}

	public BasVipLevel getBasVipLevel() {
		return this.basVipLevel;
	}

	public void setBasVipLevel(BasVipLevel basVipLevel) {
		this.basVipLevel = basVipLevel;
	}

	public Integer getReptypId() {
		return this.reptypId;
	}

	public void setReptypId(Integer reptypId) {
		this.reptypId = reptypId;
	}

	public double getBeginAmount() {
		return this.beginAmount;
	}

	public void setBeginAmount(double beginAmount) {
		this.beginAmount = beginAmount;
	}


}