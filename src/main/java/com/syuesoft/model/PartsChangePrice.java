package com.syuesoft.model;

/**
 * PartsChangePrice entity. @author MyEclipse Persistence Tools
 */

public class PartsChangePrice extends BaseBean {

	// Fields

	private Integer changePriceId;
	private Short storeId;                   //仓库
	private String partsId;                  //配件
	private Double partsRepairPrice;         //维修价格
	private Double partsSellPrice;           //销售价格
	private Double partsPointPrice;          //网店价格
	private Double partsSpecialPrice;        //特殊价格
	private Double partsClaimantPrice;       //索赔价格
	private Double partsLatestTaxprice;      //含税价格
	private Double partsLatestNotaxprice;    //未税价格
	private Double partsNowCount;            //库存量
	private Double stockUpper;
	private Double stockLower;
	private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public PartsChangePrice() {
	}

	/** minimal constructor */
	public PartsChangePrice(Short storeId, String partsId) {
		this.storeId = storeId;
		this.partsId = partsId;
	}

	/** full constructor */
	public PartsChangePrice(Short storeId, String partsId,
			Double partsRepairPrice, Double partsSellPrice,
			Double partsPointPrice, Double partsSpecialPrice,
			Double partsClaimantPrice, Double partsLatestTaxprice,
			Double partsLatestNotaxprice, Double partsNowCount,
			Double stockUpper, Double stockLower) {
		this.storeId = storeId;
		this.partsId = partsId;
		this.partsRepairPrice = partsRepairPrice;
		this.partsSellPrice = partsSellPrice;
		this.partsPointPrice = partsPointPrice;
		this.partsSpecialPrice = partsSpecialPrice;
		this.partsClaimantPrice = partsClaimantPrice;
		this.partsLatestTaxprice = partsLatestTaxprice;
		this.partsLatestNotaxprice = partsLatestNotaxprice;
		this.partsNowCount = partsNowCount;
		this.stockUpper = stockUpper;
		this.stockLower = stockLower;
	}

	// Property accessors

	public Integer getChangePriceId() {
		return this.changePriceId;
	}

	public void setChangePriceId(Integer changePriceId) {
		this.changePriceId = changePriceId;
	}

	public Short getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Double getPartsRepairPrice() {
		return this.partsRepairPrice;
	}

	public void setPartsRepairPrice(Double partsRepairPrice) {
		this.partsRepairPrice = partsRepairPrice;
	}

	public Double getPartsSellPrice() {
		return this.partsSellPrice;
	}

	public void setPartsSellPrice(Double partsSellPrice) {
		this.partsSellPrice = partsSellPrice;
	}

	public Double getPartsPointPrice() {
		return this.partsPointPrice;
	}

	public void setPartsPointPrice(Double partsPointPrice) {
		this.partsPointPrice = partsPointPrice;
	}

	public Double getPartsSpecialPrice() {
		return this.partsSpecialPrice;
	}

	public void setPartsSpecialPrice(Double partsSpecialPrice) {
		this.partsSpecialPrice = partsSpecialPrice;
	}

	public Double getPartsClaimantPrice() {
		return this.partsClaimantPrice;
	}

	public void setPartsClaimantPrice(Double partsClaimantPrice) {
		this.partsClaimantPrice = partsClaimantPrice;
	}

	public Double getPartsLatestTaxprice() {
		return this.partsLatestTaxprice;
	}

	public void setPartsLatestTaxprice(Double partsLatestTaxprice) {
		this.partsLatestTaxprice = partsLatestTaxprice;
	}

	public Double getPartsLatestNotaxprice() {
		return this.partsLatestNotaxprice;
	}

	public void setPartsLatestNotaxprice(Double partsLatestNotaxprice) {
		this.partsLatestNotaxprice = partsLatestNotaxprice;
	}

	public Double getPartsNowCount() {
		return this.partsNowCount;
	}

	public void setPartsNowCount(Double partsNowCount) {
		this.partsNowCount = partsNowCount;
	}

	public Double getStockUpper() {
		return stockUpper;
	}

	public void setStockUpper(Double stockUpper) {
		this.stockUpper = stockUpper;
	}

	public Double getStockLower() {
		return stockLower;
	}

	public void setStockLower(Double stockLower) {
		this.stockLower = stockLower;
	}
}