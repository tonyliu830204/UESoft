package com.syuesoft.model;

/**
 * StInventoryDetail entity. @author MyEclipse Persistence Tools
 */

public class StInventoryDetail extends BaseBean {

	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer indexId;
	private StInventoryMain stInventoryMain;
	private String partsId;
	private Integer stinvdCount;
	private Double stinvdPrice;
	private Double stinvdCost;
	private String punitName;
	private Double stinvdPrice1;
    private Double stinvdCost1;
	// Constructors

	/** default constructor */
	public StInventoryDetail() {
	}

	// Property accessors

	public Integer getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public StInventoryMain getStInventoryMain() {
		return this.stInventoryMain;
	}

	public void setStInventoryMain(StInventoryMain stInventoryMain) {
		this.stInventoryMain = stInventoryMain;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Integer getStinvdCount() {
		return this.stinvdCount;
	}

	public void setStinvdCount(Integer stinvdCount) {
		this.stinvdCount = stinvdCount;
	}

	public Double getStinvdPrice() {
		return this.stinvdPrice;
	}

	public void setStinvdPrice(Double stinvdPrice) {
		this.stinvdPrice = stinvdPrice;
	}

	public Double getStinvdCost() {
		return this.stinvdCost;
	}

	public void setStinvdCost(Double stinvdCost) {
		this.stinvdCost = stinvdCost;
	}

	public String getPunitName() {
		return this.punitName;
	}

	public void setPunitName(String punitName) {
		this.punitName = punitName;
	}

    public Double getStinvdPrice1()
    {
        return stinvdPrice1;
    }

    public void setStinvdPrice1(Double stinvdPrice1)
    {
        this.stinvdPrice1 = stinvdPrice1;
    }

    public Double getStinvdCost1()
    {
        return stinvdCost1;
    }

    public void setStinvdCost1(Double stinvdCost1)
    {
        this.stinvdCost1 = stinvdCost1;
    }
}