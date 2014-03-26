package com.syuesoft.st.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class StSellOrderVo implements Serializable{

	// 配件基本信息
	private String partsId;// 配件编号
	private String partsName;// 配件名称
	private String punitName;// 配件单位
	private String partsLibrary;// 库位
	private String fitPtype;// 适用车型
	private String partsNowCount;// 库存
	private String unitPrice; // 单价
	private String totalAmount; // 总金额
	private String discountRate;// 折扣率
	private String discountPrice;// 折扣价
	private String customId;// 客户编号
	private String customName;// 客户名称
	private String selldIndex;
	private String stfId;// 领料员编号
	private String stfName;// 领料员名称
	private String stfId1;// 领料员编号
	private String stfName1;// 领料员名称
	private String selldCnt; // 销售数量
	private String selldPrice;// 销售单价
	private String selldAmount;// 销售总额
	private String selldCostPrice;// 销售成本
	private String selldCostAmount;// 销售成本额
	private String selldCutRate;// 折扣率
	private String sellRemark;// 备注
	private String sellmmId; // 销售单号
	private String sellmmDate;// 销售日期
	private String sellmmDateView;// 销售日期
	private String sellcustomId;// 销售客户编号
	private String sellcustomName;// 销售客户名称
	private String sellmmSumAmount;// 销售总金额
	private String sellmmSumCost;// 销售总成本额
	private String sellmmCnt;// 销售数量
	private String psellId;// 销售分类
	private String psellName;// 销售分类
	private String sellmmClearingStatus;// 结算状态
	private String sellmmStfId;// 领料人id
	private String sellmmStfName;// 领料人名称
	private String sellmmRemark;// 备注
	private String carLicense;// 车牌照
	private String carId;// 车辆编号
	private String billType;// 发票编号
	private String sellManager;// 经办人编号
	private String sellManagerName;// 经办人名称
	private String carVin;// VIN号
	private String carMotorId;// 发动机号
	private String carRelationTel1;// 顾客电话
	private String storeId;
	private String storeName;
	private String sellmmDateStart;
	private String sellmmDateEnd;
	private String changePriceId;
	private String inserted;
	private String updated;
	private String deleted;
	private String taxCostPrice;
	private String priceType;
	private String priceTypeId;
	private String taxCostPriceAmont;
	private String selldCutPrice;
	private String priceQuotiety;
	private String relcampId;
	private String relcampName;
	private String numberIndex;
	private String detailData;
	private String preclrInoiceType;
	private String preclrInoiceTypeId;
	private String ctypeName;
	private String order;
	private String sort;
	private String preclrState;
	
	private int rows;
	private int page;
	  private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	public String getDetailData() {
		return detailData;
	}
	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}
	public String getRelcampName() {
		return relcampName;
	}
	public void setRelcampName(String relcampName) {
		this.relcampName = relcampName;
	}
	
	public String getNumberIndex() {
		return numberIndex;
	}
	public void setNumberIndex(String numberIndex) {
		this.numberIndex = numberIndex;
	}
	public String getSelldIndex() {
		return selldIndex;
	}
	public void setSelldIndex(String selldIndex) {
		this.selldIndex = selldIndex;
	}
	public String getRelcampId() {
		return relcampId;
	}
	public void setRelcampId(String relcampId) {
		this.relcampId = relcampId;
	}
	public String getPriceQuotiety() {
		return priceQuotiety;
	}
	public void setPriceQuotiety(String priceQuotiety) {
		this.priceQuotiety = priceQuotiety;
	}
	public String getSelldCutPrice() {
		return selldCutPrice;
	}
	public void setSelldCutPrice(String selldCutPrice) {
		this.selldCutPrice = selldCutPrice;
	}
	public String getPriceTypeId() {
		return priceTypeId;
	}
	public void setPriceTypeId(String priceTypeId) {
		this.priceTypeId = priceTypeId;
	}
	public String getTaxCostPriceAmont() {
		return taxCostPriceAmont;
	}
	public void setTaxCostPriceAmont(String taxCostPriceAmont) {
		this.taxCostPriceAmont = taxCostPriceAmont;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getTaxCostPrice() {
		return taxCostPrice;
	}
	public void setTaxCostPrice(String taxCostPrice) {
		this.taxCostPrice = taxCostPrice;
	}
	public String getSellmmDateView() {
		return sellmmDateView;
	}
	public void setSellmmDateView(String sellmmDateView) {
		this.sellmmDateView = sellmmDateView;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getPartsId() {
		return partsId;
	}
	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public String getPunitName() {
		return punitName;
	}
	public void setPunitName(String punitName) {
		this.punitName = punitName;
	}
	public String getPartsLibrary() {
		return partsLibrary;
	}
	public void setPartsLibrary(String partsLibrary) {
		this.partsLibrary = partsLibrary;
	}
	public String getFitPtype() {
		return fitPtype;
	}
	public void setFitPtype(String fitPtype) {
		this.fitPtype = fitPtype;
	}
	public String getPartsNowCount() {
		return partsNowCount;
	}
	public void setPartsNowCount(String partsNowCount) {
		this.partsNowCount = partsNowCount;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getStfId() {
		return stfId;
	}
	public void setStfId(String stfId) {
		this.stfId = stfId;
	}
	public String getStfName() {
		return stfName;
	}
	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
	public String getStfId1() {
		return stfId1;
	}
	public void setStfId1(String stfId1) {
		this.stfId1 = stfId1;
	}
	public String getStfName1() {
		return stfName1;
	}
	public void setStfName1(String stfName1) {
		this.stfName1 = stfName1;
	}
	public String getSelldCnt() {
		return selldCnt;
	}
	public void setSelldCnt(String selldCnt) {
		this.selldCnt = selldCnt;
	}
	public String getSelldPrice() {
		return selldPrice;
	}
	public void setSelldPrice(String selldPrice) {
		this.selldPrice = selldPrice;
	}
	public String getSelldAmount() {
		return selldAmount;
	}
	public void setSelldAmount(String selldAmount) {
		this.selldAmount = selldAmount;
	}
	public String getSelldCostPrice() {
		return selldCostPrice;
	}
	public void setSelldCostPrice(String selldCostPrice) {
		this.selldCostPrice = selldCostPrice;
	}
	public String getSelldCostAmount() {
		return selldCostAmount;
	}
	public void setSelldCostAmount(String selldCostAmount) {
		this.selldCostAmount = selldCostAmount;
	}
	public String getSelldCutRate() {
		return selldCutRate;
	}
	public void setSelldCutRate(String selldCutRate) {
		this.selldCutRate = selldCutRate;
	}
	public String getSellRemark() {
		return sellRemark;
	}
	public void setSellRemark(String sellRemark) {
		this.sellRemark = sellRemark;
	}
	public String getSellmmId() {
		return sellmmId;
	}
	public void setSellmmId(String sellmmId) {
		this.sellmmId = sellmmId;
	}
	public String getSellmmDate() {
		return sellmmDate;
	}
	public void setSellmmDate(String sellmmDate) {
		this.sellmmDate = sellmmDate;
	}
	public String getSellcustomId() {
		return sellcustomId;
	}
	public void setSellcustomId(String sellcustomId) {
		this.sellcustomId = sellcustomId;
	}
	public String getSellcustomName() {
		return sellcustomName;
	}
	public void setSellcustomName(String sellcustomName) {
		this.sellcustomName = sellcustomName;
	}
	public String getSellmmSumAmount() {
		return sellmmSumAmount;
	}
	public void setSellmmSumAmount(String sellmmSumAmount) {
		this.sellmmSumAmount = sellmmSumAmount;
	}
	public String getSellmmSumCost() {
		return sellmmSumCost;
	}
	public void setSellmmSumCost(String sellmmSumCost) {
		this.sellmmSumCost = sellmmSumCost;
	}
	public String getSellmmCnt() {
		return sellmmCnt;
	}
	public void setSellmmCnt(String sellmmCnt) {
		this.sellmmCnt = sellmmCnt;
	}
	public String getPsellId() {
		return psellId;
	}
	public void setPsellId(String psellId) {
		this.psellId = psellId;
	}
	public String getPsellName() {
		return psellName;
	}
	public void setPsellName(String psellName) {
		this.psellName = psellName;
	}
	public String getSellmmClearingStatus() {
		return sellmmClearingStatus;
	}
	public void setSellmmClearingStatus(String sellmmClearingStatus) {
		this.sellmmClearingStatus = sellmmClearingStatus;
	}
	public String getSellmmStfId() {
		return sellmmStfId;
	}
	public void setSellmmStfId(String sellmmStfId) {
		this.sellmmStfId = sellmmStfId;
	}
	public String getSellmmStfName() {
		return sellmmStfName;
	}
	public void setSellmmStfName(String sellmmStfName) {
		this.sellmmStfName = sellmmStfName;
	}
	public String getSellmmRemark() {
		return sellmmRemark;
	}
	public void setSellmmRemark(String sellmmRemark) {
		this.sellmmRemark = sellmmRemark;
	}
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getSellManager() {
		return sellManager;
	}
	public void setSellManager(String sellManager) {
		this.sellManager = sellManager;
	}
	public String getSellManagerName() {
		return sellManagerName;
	}
	public void setSellManagerName(String sellManagerName) {
		this.sellManagerName = sellManagerName;
	}
	public String getCarVin() {
		return carVin;
	}
	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}
	public String getCarMotorId() {
		return carMotorId;
	}
	public void setCarMotorId(String carMotorId) {
		this.carMotorId = carMotorId;
	}
	public String getCarRelationTel1() {
		return carRelationTel1;
	}
	public void setCarRelationTel1(String carRelationTel1) {
		this.carRelationTel1 = carRelationTel1;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getSellmmDateStart() {
		return sellmmDateStart;
	}
	public void setSellmmDateStart(String sellmmDateStart) {
		this.sellmmDateStart = sellmmDateStart;
	}
	public String getSellmmDateEnd() {
		return sellmmDateEnd;
	}
	public void setSellmmDateEnd(String sellmmDateEnd) {
		this.sellmmDateEnd = sellmmDateEnd;
	}
	public String getChangePriceId() {
		return changePriceId;
	}
	public void setChangePriceId(String changePriceId) {
		this.changePriceId = changePriceId;
	}
	public String getInserted() {
		return inserted;
	}
	public void setInserted(String inserted) {
		this.inserted = inserted;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	public String toString() {
		return this.sellManagerName+"   "+this.sellmmStfName;
	}
	public String getCtypeName() {
		return ctypeName;
	}
	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}
	public String getPreclrInoiceType() {
		return preclrInoiceType;
	}
	public void setPreclrInoiceType(String preclrInoiceType) {
		this.preclrInoiceType = preclrInoiceType;
	}
	public String getPreclrInoiceTypeId() {
		return preclrInoiceTypeId;
	}
	public void setPreclrInoiceTypeId(String preclrInoiceTypeId) {
		this.preclrInoiceTypeId = preclrInoiceTypeId;
	}
	public String getPreclrState() {
		return preclrState;
	}
	public void setPreclrState(String preclrState) {
		this.preclrState = preclrState;
	}
	
}
