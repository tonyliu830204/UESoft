package com.syuesoft.model;

/**
 * BasVipDiscountRegulation entity. @author MyEclipse Persistence Tools
 */

public class BasVipDiscountRegulation extends BaseBean{

	// Fields

	private Integer vipDisRegId;
	private BasVipLevel basVipLevel;
	private Integer reptypId;
	private Long workRegDiscount;
	private Long materialRegDiscount;
	private Long totalRegDiscount;
	private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }

	/** default constructor */
	public BasVipDiscountRegulation() {
	}

	/** full constructor */
	public BasVipDiscountRegulation(BasVipLevel basVipLevel, Integer reptypId,
			Long workRegDiscount, Long materialRegDiscount,
			Long totalRegDiscount) {
		this.basVipLevel = basVipLevel;
		this.reptypId = reptypId;
		this.workRegDiscount = workRegDiscount;
		this.materialRegDiscount = materialRegDiscount;
		this.totalRegDiscount = totalRegDiscount;
	}

	// Property accessors

	public Integer getVipDisRegId() {
		return this.vipDisRegId;
	}

	public void setVipDisRegId(Integer vipDisRegId) {
		this.vipDisRegId = vipDisRegId;
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

	public Long getWorkRegDiscount() {
		return this.workRegDiscount;
	}

	public void setWorkRegDiscount(Long workRegDiscount) {
		this.workRegDiscount = workRegDiscount;
	}

	public Long getMaterialRegDiscount() {
		return this.materialRegDiscount;
	}

	public void setMaterialRegDiscount(Long materialRegDiscount) {
		this.materialRegDiscount = materialRegDiscount;
	}

	public Long getTotalRegDiscount() {
		return this.totalRegDiscount;
	}

	public void setTotalRegDiscount(Long totalRegDiscount) {
		this.totalRegDiscount = totalRegDiscount;
	}

}