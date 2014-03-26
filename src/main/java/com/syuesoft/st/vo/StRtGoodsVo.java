package com.syuesoft.st.vo;

import java.io.Serializable;

/**
 * 退货单service
 * @author SuMing
 */
@SuppressWarnings("serial")
public class StRtGoodsVo implements Serializable{

	private String storageId; // 货品入库单号
	private String manager; // 经办人姓名
	private String buyer; // 采购员名称
	private String storeId; // 仓库名称
	private String storeName; // 仓库名称
	private String relcampName;// 供应商名称
	private String relcampName1;// 供应商名称
	private String relcampId; // 供应商编号
	private String relcampId1; // 供应商编号
	private String relcampTel1;// 供应商电话一
	private String relcampTel2;// 供应商电话二
	private String relcampRemark1;// 供应商备注
	private String storageDateView;
	private String storageDate;//入库日期
	private String detailData;
	private String strtgdNoCostPrice;
	private String strtgdNoCostAmount;
	private String strtgmSumNoCost;

	private String buyerId;// 经办人编号
	private String managerId;// 采购员编号
	private String strtgdRemark;// 退货单明细备注

	private String itemIndex; // 序号
	private String partsId; // 配件编号
	private String partsName; // 配件名称
	private String punitName; // 配件单位

	private String partsCount;// 数量
	private String taxPrice; // 含税价
	private String taxTotalamont;// 含税额
	private String partsSellPrice;// 销售价

	private String strtgdCnt = "0.0";// 退货明细数量

	private String numTotal;// 退货总数量
	private String strtgmSumAmount;// 退货总金额
	private String partsLatestTaxprice;// 退货成本价
	private String partsLatestNoTaxprice;// 退货成本价
	private String strtgmSumCost;// 退货汇总成本额
	private String strtgdCostAmount;// 退货明细成本额
	private String strtgdCostPrice;//退货明细成本价

	private String strtgmDate;// 退货时间
	private String strtgmId;// 退货单号
	private String strtgmRemark;// 退货备注
	private String totalNum;//退货总数量
	private String costAmount;//退货总成本额
	private String StrtgmDateStart;
	private String StrtgmDateEnd;
	private String strtgdIndex;
	
	private String sort;
	private String order;
	private int rows;
	private int page;
	private String inserted;
	private String updated;
	private String deleted;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }

	public String getStrtgmSumNoCost() {
		return strtgmSumNoCost;
	}
	public void setStrtgmSumNoCost(String strtgmSumNoCost) {
		this.strtgmSumNoCost = strtgmSumNoCost;
	}
	public String getStrtgdNoCostPrice() {
		return strtgdNoCostPrice;
	}
	public void setStrtgdNoCostPrice(String strtgdNoCostPrice) {
		this.strtgdNoCostPrice = strtgdNoCostPrice;
	}
	public String getStrtgdNoCostAmount() {
		return strtgdNoCostAmount;
	}
	public void setStrtgdNoCostAmount(String strtgdNoCostAmount) {
		this.strtgdNoCostAmount = strtgdNoCostAmount;
	}
	public String getDetailData() {
		return detailData;
	}
	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}
	public String getStorageDateView() {
		return storageDateView;
	}
	public void setStorageDateView(String storageDateView) {
		this.storageDateView = storageDateView;
	}
	public String getStrtgdIndex() {
		return strtgdIndex;
	}
	public void setStrtgdIndex(String strtgdIndex) {
		this.strtgdIndex = strtgdIndex;
	}
	public String getStrtgmDateStart() {
		return StrtgmDateStart;
	}
	public void setStrtgmDateStart(String strtgmDateStart) {
		StrtgmDateStart = strtgmDateStart;
	}
	public String getStrtgmDateEnd() {
		return StrtgmDateEnd;
	}
	public void setStrtgmDateEnd(String strtgmDateEnd) {
		StrtgmDateEnd = strtgmDateEnd;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getCostAmount() {
		return costAmount;
	}
	public void setCostAmount(String costAmount) {
		this.costAmount = costAmount;
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
	public String getPartsLatestNoTaxprice() {
		return partsLatestNoTaxprice;
	}
	public void setPartsLatestNoTaxprice(String partsLatestNoTaxprice) {
		this.partsLatestNoTaxprice = partsLatestNoTaxprice;
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

	public String getRelcampName1() {
		return relcampName1;
	}

	public void setRelcampName1(String relcampName1) {
		this.relcampName1 = relcampName1;
	}

	public String getRelcampId1() {
		return relcampId1;
	}

	public void setRelcampId1(String relcampId1) {
		this.relcampId1 = relcampId1;
	}

	public String getStrtgdCostPrice() {
		return strtgdCostPrice;
	}

	public void setStrtgdCostPrice(String strtgdCostPrice) {
		this.strtgdCostPrice = strtgdCostPrice;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getStrtgdRemark() {
		return strtgdRemark;
	}

	public void setStrtgdRemark(String strtgdRemark) {
		this.strtgdRemark = strtgdRemark;
	}

	public String getStrtgmDate() {
		return strtgmDate;
	}

	public void setStrtgmDate(String strtgmDate) {
		this.strtgmDate = strtgmDate;
	}

	public String getStrtgmId() {
		return strtgmId;
	}

	public void setStrtgmId(String strtgmId) {
		this.strtgmId = strtgmId;
	}

	public String getStrtgmRemark() {
		return strtgmRemark;
	}

	public void setStrtgmRemark(String strtgmRemark) {
		this.strtgmRemark = strtgmRemark;
	}

	public String getStrtgmSumCost() {
		return strtgmSumCost;
	}

	public void setStrtgmSumCost(String strtgmSumCost) {
		this.strtgmSumCost = strtgmSumCost;
	}

	public String getStrtgdCostAmount() {
		return strtgdCostAmount;
	}

	public void setStrtgdCostAmount(String strtgdCostAmount) {
		this.strtgdCostAmount = strtgdCostAmount;
	}

	public String getPartsLatestTaxprice() {
		return partsLatestTaxprice;
	}

	public void setPartsLatestTaxprice(String partsLatestTaxprice) {
		this.partsLatestTaxprice = partsLatestTaxprice;
	}

	public String getStrtgmSumAmount() {
		return strtgmSumAmount;
	}

	public void setStrtgmSumAmount(String strtgmSumAmount) {
		this.strtgmSumAmount = strtgmSumAmount;
	}

	public String getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
	}

	public String getTaxTotalamont() {
		return taxTotalamont;
	}

	public void setTaxTotalamont(String taxTotalamont) {
		this.taxTotalamont = taxTotalamont;
	}

	public String getPartsSellPrice() {
		return partsSellPrice;
	}

	public void setPartsSellPrice(String partsSellPrice) {
		this.partsSellPrice = partsSellPrice;
	}

	public String getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}

	public String getPartsCount() {
		return partsCount;
	}

	public void setPartsCount(String partsCount) {
		this.partsCount = partsCount;
	}

	public String getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
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

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getRelcampName() {
		return relcampName;
	}

	public void setRelcampName(String relcampName) {
		this.relcampName = relcampName;
	}

	public String getRelcampId() {
		return relcampId;
	}

	public void setRelcampId(String relcampId) {
		this.relcampId = relcampId;
	}

	public String getRelcampTel1() {
		return relcampTel1;
	}

	public void setRelcampTel1(String relcampTel1) {
		this.relcampTel1 = relcampTel1;
	}

	public String getRelcampTel2() {
		return relcampTel2;
	}

	public void setRelcampTel2(String relcampTel2) {
		this.relcampTel2 = relcampTel2;
	}

	public String getRelcampRemark1() {
		return relcampRemark1;
	}

	public void setRelcampRemark1(String relcampRemark1) {
		this.relcampRemark1 = relcampRemark1;
	}

	public String getStrtgdCnt() {
		return strtgdCnt;
	}

	public void setStrtgdCnt(String strtgdCnt) {
		this.strtgdCnt = strtgdCnt;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}

}
