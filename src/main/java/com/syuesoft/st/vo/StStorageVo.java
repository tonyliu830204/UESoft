package com.syuesoft.st.vo;

import java.io.Serializable;

/**
 * 入库单管理VO
 * 
 * @author SuMing
 */
@SuppressWarnings("serial")
public class StStorageVo implements Serializable {

	// 入库单明细表 表名：StStorageItem
	private String itemIndex; // 流水号
	private String unitPrice; // 单价
	private String partsCount; // 数量
	private String itemAmount; // 含税金额
	private String itemRemark; // 备注
	private String taxcount; // 税额
	private String addpriceRate;
	// 配件调价表 表名：PartsChangePrice
	private String partsRepairPrice; // 维修价
	private String partsSellPrice; // 销售价
	private String partsPointPrice; // 网点价
	private String partsLatestTaxprice; // 含税价
	private String partsLatestNotaxprice; // 未税价
	private String storageDateView;
	// 货品入库单 表名：StGoodsStorage
	private String storageId; // 入库单号
	private String cerNo; // 原凭证号
	private String storageDate; // 入库日期
	private String storeId; // 仓库编号
	private String totalNum; // 数量合计
	private String paid; // 付讫
	private String taxRate; // 税率
	private String taxTotalamont; // 含税金额
	private String notaxTotalamont; // 未税金额
	private String taxPrice; // 含税价格
	private String notaxPrice; // 未税价格
	private String identifyman; // 验收人
	private String identifymanName; // 验收人
	private String bill; // 发票号
	private String remark; // 备注
	private String detailData;

	// 配将档案表 表名：FrtParts
	private String partsId; // 配件编号
	private String partsName; // 配件名称
	private String punitName; // 单位
	private String partsId2;
	private String partsLibrary;
	private String partsSpecialPrice;
	private String partsClaimantPrice;
	// 表名：配件型号表
	private String ptypeName; // 配件型号名称
	private String partsNum;// 采购数量
	// 表名：采购单明细表
	private String stpredIndex; // 配件流水号

	// 采购订单表 表名：StPurOrder
	private String orderId; // 采购订单号
	private String manager; // 经办人ID
	private String managerId; // 经办人
	private String buyer; // 采购员ID
	private String buyerName; // 采购员
	private String totalAmount; // 采购金额
	private String numTotal; // 采购数量
	private String notesType; // 票据类型
	private String examine; // 审核情况
	private String taxCount; // 税额

	// 相关单位表 表名：BasRelationCampany
	private String relcampId; // 供应商编号
	private String relcampName; // 供应商名称
	private String relcampContact;// 联系人电话

	private String relcampId1;
	private String relcampName1;
	// 仓库表 表名：BasStorehouse
	private String storeName; // 仓库名称
	private String orderId1;
	private String orderId3;

	private String storageId1; // 查询 入库单号
	private String storageDateStart;// 查询 开始时间
	private String storageDateEnd;// 查询 结束时间
	private String examine1; // 查询 审核情况
	private String relcampTel1;
	private String relcampFax;
	private String relcampName2;// 汇总 查询 供应商名称
	private String stOutCount;
	private String surplusCount;
	private String q;
	private String sort;
	private String order;
	private int rows;
	private int page;
	private String addpriceRateId;
	private String partsNowCount;
	private Integer enterpriseId;

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getPartsNum() {
		return partsNum;
	}

	public void setPartsNum(String partsNum) {
		this.partsNum = partsNum;
	}

	public String getDetailData() {
		return detailData;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}

	public String getPartsNowCount() {
		return partsNowCount;
	}

	public void setPartsNowCount(String partsNowCount) {
		this.partsNowCount = partsNowCount;
	}

	public String getPartsId2() {
		return partsId2;
	}

	public void setPartsId2(String partsId2) {
		this.partsId2 = partsId2;
	}

	public String getPartsLibrary() {
		return partsLibrary;
	}

	public void setPartsLibrary(String partsLibrary) {
		this.partsLibrary = partsLibrary;
	}

	public String getPartsSpecialPrice() {
		return partsSpecialPrice;
	}

	public void setPartsSpecialPrice(String partsSpecialPrice) {
		this.partsSpecialPrice = partsSpecialPrice;
	}

	public String getPartsClaimantPrice() {
		return partsClaimantPrice;
	}

	public void setPartsClaimantPrice(String partsClaimantPrice) {
		this.partsClaimantPrice = partsClaimantPrice;
	}

	public String getStorageDateView() {
		return storageDateView;
	}

	public void setStorageDateView(String storageDateView) {
		this.storageDateView = storageDateView;
	}

	public String getAddpriceRateId() {
		return addpriceRateId;
	}

	public void setAddpriceRateId(String addpriceRateId) {
		this.addpriceRateId = addpriceRateId;
	}

	public String getAddpriceRate() {
		return addpriceRate;
	}

	public void setAddpriceRate(String addpriceRate) {
		this.addpriceRate = addpriceRate;
	}

	public String getStOutCount() {
		return stOutCount;
	}

	public void setStOutCount(String stOutCount) {
		this.stOutCount = stOutCount;
	}

	public String getSurplusCount() {
		return surplusCount;
	}

	public void setSurplusCount(String surplusCount) {
		this.surplusCount = surplusCount;
	}

	public String getIdentifymanName() {
		return identifymanName;
	}

	public void setIdentifymanName(String identifymanName) {
		this.identifymanName = identifymanName;
	}

	public String getRelcampContact() {
		return relcampContact;
	}

	public void setRelcampContact(String relcampContact) {
		this.relcampContact = relcampContact;
	}

	public String getOrderId3() {
		return orderId3;
	}

	public void setOrderId3(String orderId3) {
		this.orderId3 = orderId3;
	}

	public String getStorageDateStart() {
		return storageDateStart;
	}

	public void setStorageDateStart(String storageDateStart) {
		this.storageDateStart = storageDateStart;
	}

	public String getStorageDateEnd() {
		return storageDateEnd;
	}

	public void setStorageDateEnd(String storageDateEnd) {
		this.storageDateEnd = storageDateEnd;
	}

	public String getExamine1() {
		return examine1;
	}

	public void setExamine1(String examine1) {
		this.examine1 = examine1;
	}

	public String getStorageId1() {
		return storageId1;
	}

	public void setStorageId1(String storageId1) {
		this.storageId1 = storageId1;
	}

	public String getRelcampName2() {
		return relcampName2;
	}

	public void setRelcampName2(String relcampName2) {
		this.relcampName2 = relcampName2;
	}

	public String getRelcampTel1() {
		return relcampTel1;
	}

	public void setRelcampTel1(String relcampTel1) {
		this.relcampTel1 = relcampTel1;
	}

	public String getRelcampFax() {
		return relcampFax;
	}

	public void setRelcampFax(String relcampFax) {
		this.relcampFax = relcampFax;
	}

	public String getRelcampId1() {
		return relcampId1;
	}

	public void setRelcampId1(String relcampId1) {
		this.relcampId1 = relcampId1;
	}

	public String getRelcampName1() {
		return relcampName1;
	}

	public void setRelcampName1(String relcampName1) {
		this.relcampName1 = relcampName1;
	}

	public String getOrderId1() {
		return orderId1;
	}

	public void setOrderId1(String orderId1) {
		this.orderId1 = orderId1;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getItemIndex() {
		return itemIndex;
	}

	public String getTaxTotalamont() {
		return taxTotalamont;
	}

	public void setTaxTotalamont(String taxTotalamont) {
		this.taxTotalamont = taxTotalamont;
	}

	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getPartsCount() {
		return partsCount;
	}

	public void setPartsCount(String partsCount) {
		this.partsCount = partsCount;
	}

	public String getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(String itemAmount) {
		this.itemAmount = itemAmount;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public String getPartsRepairPrice() {
		return partsRepairPrice;
	}

	public void setPartsRepairPrice(String partsRepairPrice) {
		this.partsRepairPrice = partsRepairPrice;
	}

	public String getPartsSellPrice() {
		return partsSellPrice;
	}

	public String getTaxcount() {
		return taxcount;
	}

	public void setTaxcount(String taxcount) {
		this.taxcount = taxcount;
	}

	public void setPartsSellPrice(String partsSellPrice) {
		this.partsSellPrice = partsSellPrice;
	}

	public String getPartsPointPrice() {
		return partsPointPrice;
	}

	public void setPartsPointPrice(String partsPointPrice) {
		this.partsPointPrice = partsPointPrice;
	}

	public String getPartsLatestTaxprice() {
		return partsLatestTaxprice;
	}

	public void setPartsLatestTaxprice(String partsLatestTaxprice) {
		this.partsLatestTaxprice = partsLatestTaxprice;
	}

	public String getPartsLatestNotaxprice() {
		return partsLatestNotaxprice;
	}

	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public void setPartsLatestNotaxprice(String partsLatestNotaxprice) {
		this.partsLatestNotaxprice = partsLatestNotaxprice;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getCerNo() {
		return cerNo;
	}

	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}

	public String getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRelcampId() {
		return relcampId;
	}

	public void setRelcampId(String relcampId) {
		this.relcampId = relcampId;
	}

	public String getRelcampName() {
		return relcampName;
	}

	public void setRelcampName(String relcampName) {
		this.relcampName = relcampName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}

	public String getNotaxPrice() {
		return notaxPrice;
	}

	public void setNotaxPrice(String notaxPrice) {
		this.notaxPrice = notaxPrice;
	}

	public String getIdentifyman() {
		return identifyman;
	}

	public void setIdentifyman(String identifyman) {
		this.identifyman = identifyman;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNotesType() {
		return notesType;
	}

	public void setNotesType(String notesType) {
		this.notesType = notesType;
	}

	public String getStpredIndex() {
		return stpredIndex;
	}

	public void setStpredIndex(String stpredIndex) {
		this.stpredIndex = stpredIndex;
	}

	public String getPtypeName() {
		return ptypeName;
	}

	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
	}

	public String getNotaxTotalamont() {
		return notaxTotalamont;
	}

	public void setNotaxTotalamont(String notaxTotalamont) {
		this.notaxTotalamont = notaxTotalamont;
	}

	public String getTaxCount() {
		return taxCount;
	}

	public void setTaxCount(String taxCount) {
		this.taxCount = taxCount;
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

}
