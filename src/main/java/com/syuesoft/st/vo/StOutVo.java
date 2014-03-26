package com.syuesoft.st.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class StOutVo implements Serializable{
	
	private String partsId; // 配件编号
	private String partsName; // 配件名称
	private String punitName; // 配件单位
	private String partsLibrary; // 配件库位
	private String fitPtype; // 适用车型编号
	private String ptypeName; //适用车型名称
	private String storeId; // 获取配件所属仓别ID
	private String partsSellPrice; // 销售价
	private String partsLatestTaxprice; // 含税成本
	private String partsLatestNotaxprice; // 未税成本
	private String partsNowCount; // 库存
	private String storeName; // 仓库名称
	private String stomId; // 出库单号
	private String stomDate; // 出库日期
	private String stomRemark; // 备注
	private String pickingMemberName;// 领料员名称
	private String pickingMember;// 领料员ID
	private String pickingMemberName1;//查询 领料员名称
	private String pickingMember1;//查询 领料员ID
	private String outItemRemark; // 备注
	private String itemCount; // 数量
	private String itemCountHidden; // 数量
	private String itemCount1;//参考数量
	private String itemPrice; // 销售价
	private String rcptpartsIndex;
	private String amount; // 销售金额
	private String notaxCastamont; // 含税成本额
	private String taxCastamont; // 未税成本额
	private String taxCast; // 含税成本
	private String notaxCast; // 未税成本
	private String claimsType; // 索赔分类
	private String itemCharge; // 收费性质
	private String totalNum; // 数量
	private String stomSumAmount; // 销售额
	private String noTaxCastamont; // 未税成本额
	private String taxCastamont1; // 含税成本额
	private String cerNo; // 原凭证号
	private String receptionId; // 工单号
	private String receptionId2; //查询     工单号
	private String carLicense; // 车辆牌照
	private String carVan; // 车辆VIN号
	private String carLastRepairDistance; // 最后维修里程数
	private String ctypeName; // 车辆类型名称
	private String resvRealTime; // 实际进店时间
	private String stfId; // 接待员id
	private String stfName; //接待员名称
	private String repgrpName; // 维修班组ID
	private String customName; // 客户名称
	private String customId; // 客户ID
	private String manager; // 经办人
	private String managerId;
	private String managerName; // 经办人
	private String xiaojiaxishu;//销价系数
	private String reptName; // 维修类别名称
	private String rcptpartsCnt;// 数量
	private String punitId;// 单位编号
	private String rcptpartsPrice;// 销售价
	private String rcptpartsAmount;// 销售金额
	private String charge;// 收费性质
	private String claimsType1;// 表示索赔分类
	private String partsLatestTaxpriceAmont;// 含说成本额
	private String changePriceId;
	private String stomDateStart;
	private String stomDateEnd;
	private String inserted;
	private String updated;
	private String deleted;
	private Short  claimsId;
	private String partsRemark;
	private String claimsName;
	private String itemIndex;
	private String partsCount;
	private String surPlus;
	private String relcampId;
	private String relcampName;
	private String planParts;
	private String pregrpName;
	private String stomDateView;
	private String detailData;
	private String pbrdName;
	

	private String pbrdId;
	private String startTime;
	private String endTime;
	private String taxPrice;
	private Short itemChargeId;
	private String typeName;
	private String typeId;
	private String outPartsCount;
	private String rtOutPartsCount;
	private String sort;
	private String order;
	private int rows;
	private int page;
	private String q;
    private Integer enterpriseId;
    public String getPbrdId() {
		return pbrdId;
	}

	public void setPbrdId(String pbrdId) {
		this.pbrdId = pbrdId;
	}
    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	public String getOutPartsCount() {
		return outPartsCount;
	}
	public void setOutPartsCount(String outPartsCount) {
		this.outPartsCount = outPartsCount;
	}
	public String getRtOutPartsCount() {
		return rtOutPartsCount;
	}
	public void setRtOutPartsCount(String rtOutPartsCount) {
		this.rtOutPartsCount = rtOutPartsCount;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Short getItemChargeId() {
		return itemChargeId;
	}
	public void setItemChargeId(Short itemChargeId) {
		this.itemChargeId = itemChargeId;
	}
	public String getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPbrdName() {
		return pbrdName;
	}
	public void setPbrdName(String pbrdName) {
		this.pbrdName = pbrdName;
	}
	public String getDetailData() {
		return detailData;
	}
	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}
	public String getItemCountHidden() {
		return itemCountHidden;
	}
	public void setItemCountHidden(String itemCountHidden) {
		this.itemCountHidden = itemCountHidden;
	}
	public String getRcptpartsIndex() {
		return rcptpartsIndex;
	}
	public void setRcptpartsIndex(String rcptpartsIndex) {
		this.rcptpartsIndex = rcptpartsIndex;
	}
	public String getStomDateView() {
		return stomDateView;
	}
	public void setStomDateView(String stomDateView) {
		this.stomDateView = stomDateView;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getPregrpName() {
		return pregrpName;
	}
	public void setPregrpName(String pregrpName) {
		this.pregrpName = pregrpName;
	}
	public String getPlanParts() {
		return planParts;
	}
	public void setPlanParts(String planParts) {
		this.planParts = planParts;
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
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getSurPlus() {
		return surPlus;
	}
	public void setSurPlus(String surPlus) {
		this.surPlus = surPlus;
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
	public String getPartsRemark() {
		return partsRemark;
	}
	public void setPartsRemark(String partsRemark) {
		this.partsRemark = partsRemark;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
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
	public String getPtypeName() {
		return ptypeName;
	}
	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getPartsSellPrice() {
		return partsSellPrice;
	}
	public void setPartsSellPrice(String partsSellPrice) {
		this.partsSellPrice = partsSellPrice;
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
	public void setPartsLatestNotaxprice(String partsLatestNotaxprice) {
		this.partsLatestNotaxprice = partsLatestNotaxprice;
	}
	public String getPartsNowCount() {
		return partsNowCount;
	}
	public void setPartsNowCount(String partsNowCount) {
		this.partsNowCount = partsNowCount;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStomId() {
		return stomId;
	}
	public void setStomId(String stomId) {
		this.stomId = stomId;
	}
	public String getStomDate() {
		return stomDate;
	}
	public void setStomDate(String stomDate) {
		this.stomDate = stomDate;
	}
	public String getStomRemark() {
		return stomRemark;
	}
	public void setStomRemark(String stomRemark) {
		this.stomRemark = stomRemark;
	}
	public String getPickingMemberName() {
		return pickingMemberName;
	}
	public void setPickingMemberName(String pickingMemberName) {
		this.pickingMemberName = pickingMemberName;
	}
	public String getPickingMember() {
		return pickingMember;
	}
	public void setPickingMember(String pickingMember) {
		this.pickingMember = pickingMember;
	}
	public String getPickingMemberName1() {
		return pickingMemberName1;
	}
	public void setPickingMemberName1(String pickingMemberName1) {
		this.pickingMemberName1 = pickingMemberName1;
	}
	public String getPickingMember1() {
		return pickingMember1;
	}
	public void setPickingMember1(String pickingMember1) {
		this.pickingMember1 = pickingMember1;
	}
	public String getOutItemRemark() {
		return outItemRemark;
	}
	public void setOutItemRemark(String outItemRemark) {
		this.outItemRemark = outItemRemark;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	public String getItemCount1() {
		return itemCount1;
	}
	public void setItemCount1(String itemCount1) {
		this.itemCount1 = itemCount1;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getNotaxCastamont() {
		return notaxCastamont;
	}
	public void setNotaxCastamont(String notaxCastamont) {
		this.notaxCastamont = notaxCastamont;
	}
	public String getTaxCastamont() {
		return taxCastamont;
	}
	public void setTaxCastamont(String taxCastamont) {
		this.taxCastamont = taxCastamont;
	}
	public String getTaxCast() {
		return taxCast;
	}
	public void setTaxCast(String taxCast) {
		this.taxCast = taxCast;
	}
	public String getNotaxCast() {
		return notaxCast;
	}
	public void setNotaxCast(String notaxCast) {
		this.notaxCast = notaxCast;
	}
	public String getClaimsType() {
		return claimsType;
	}
	public void setClaimsType(String claimsType) {
		this.claimsType = claimsType;
	}
	public String getItemCharge() {
		return itemCharge;
	}
	public void setItemCharge(String itemCharge) {
		this.itemCharge = itemCharge;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getStomSumAmount() {
		return stomSumAmount;
	}
	public void setStomSumAmount(String stomSumAmount) {
		this.stomSumAmount = stomSumAmount;
	}
	public String getNoTaxCastamont() {
		return noTaxCastamont;
	}
	public void setNoTaxCastamont(String noTaxCastamont) {
		this.noTaxCastamont = noTaxCastamont;
	}
	public String getTaxCastamont1() {
		return taxCastamont1;
	}
	public void setTaxCastamont1(String taxCastamont1) {
		this.taxCastamont1 = taxCastamont1;
	}
	public String getCerNo() {
		return cerNo;
	}
	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}
	public String getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}
	public String getReceptionId2() {
		return receptionId2;
	}
	public void setReceptionId2(String receptionId2) {
		this.receptionId2 = receptionId2;
	}
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public String getCarVan() {
		return carVan;
	}
	public void setCarVan(String carVan) {
		this.carVan = carVan;
	}
	public String getCarLastRepairDistance() {
		return carLastRepairDistance;
	}
	public void setCarLastRepairDistance(String carLastRepairDistance) {
		this.carLastRepairDistance = carLastRepairDistance;
	}
	public String getCtypeName() {
		return ctypeName;
	}
	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}
	public String getResvRealTime() {
		return resvRealTime;
	}
	public void setResvRealTime(String resvRealTime) {
		this.resvRealTime = resvRealTime;
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
	public String getRepgrpName() {
		return repgrpName;
	}
	public void setRepgrpName(String repgrpName) {
		this.repgrpName = repgrpName;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getXiaojiaxishu() {
		return xiaojiaxishu;
	}
	public void setXiaojiaxishu(String xiaojiaxishu) {
		this.xiaojiaxishu = xiaojiaxishu;
	}
	public String getReptName() {
		return reptName;
	}
	public void setReptName(String reptName) {
		this.reptName = reptName;
	}
	public String getRcptpartsCnt() {
		return rcptpartsCnt;
	}
	public void setRcptpartsCnt(String rcptpartsCnt) {
		this.rcptpartsCnt = rcptpartsCnt;
	}
	public String getPunitId() {
		return punitId;
	}
	public void setPunitId(String punitId) {
		this.punitId = punitId;
	}
	public String getRcptpartsPrice() {
		return rcptpartsPrice;
	}
	public void setRcptpartsPrice(String rcptpartsPrice) {
		this.rcptpartsPrice = rcptpartsPrice;
	}
	public String getRcptpartsAmount() {
		return rcptpartsAmount;
	}
	public void setRcptpartsAmount(String rcptpartsAmount) {
		this.rcptpartsAmount = rcptpartsAmount;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getClaimsType1() {
		return claimsType1;
	}
	public void setClaimsType1(String claimsType1) {
		this.claimsType1 = claimsType1;
	}
	public String getPartsLatestTaxpriceAmont() {
		return partsLatestTaxpriceAmont;
	}
	public void setPartsLatestTaxpriceAmont(String partsLatestTaxpriceAmont) {
		this.partsLatestTaxpriceAmont = partsLatestTaxpriceAmont;
	}
	public String getChangePriceId() {
		return changePriceId;
	}
	public void setChangePriceId(String changePriceId) {
		this.changePriceId = changePriceId;
	}
	public String getStomDateStart() {
		return stomDateStart;
	}
	public void setStomDateStart(String stomDateStart) {
		this.stomDateStart = stomDateStart;
	}
	public String getStomDateEnd() {
		return stomDateEnd;
	}
	public void setStomDateEnd(String stomDateEnd) {
		this.stomDateEnd = stomDateEnd;
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
	public Short getClaimsId() {
		return claimsId;
	}
	public void setClaimsId(Short claimsId) {
		this.claimsId = claimsId;
	}
	public String getClaimsName() {
		return claimsName;
	}
	public void setClaimsName(String claimsName) {
		this.claimsName = claimsName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}
