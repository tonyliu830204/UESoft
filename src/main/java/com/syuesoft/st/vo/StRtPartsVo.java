package com.syuesoft.st.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class StRtPartsVo implements Serializable{

	private String receptionId;    // 工单号
	private String carLicense;     // 车牌
	private String ctypeName;      // 车类型名称
	private String resvRealTime;   // 车辆进厂日期
	private String customName;     // 客户名称
	private String stfId;          // 接待员id
	private String reptypName;     // 收费性质名称
	private String claimsName;     // 索赔分类名称
	private String punitName;      // 单位名称
	private String storeName;      // 仓库名称
	private String strtpmSumCnt;   // 退货总数量
	private String rcptpartsSumPrice;// 配件总金额
	private String stfName;        // 员工名称
	private String strtpdIndex;    // 退料明细流水号
	private String partsLatestTaxprice;// 含税成本价
	private String partsLatestNoTaxprice;// 含税成本价
	private String partsLatestTaxpriceAmont="0.0";// 含说成本额
	private String rcptpartsIndex;
	private String frtReception;
	private String partsId;
	private String partsName;
	private String rcptpartsCnt;
	private String partsNum;
	private String punitId;
	private String rcptpartsPrice;
	private String rcptpartsAmount="0.0";
	private String charge;
	private String claimsType;
	private String partsRemark;
	private String carVan;
	private String strtpdCnt="0.0";      // 退货数量
	private String manager;        // 经办人
	private String strtpmId;       // 退货单编号
	private String strtpmDate;     // 退货时间
	private String strtpmType;     // 退货分类
	private String storeId;        // 仓库编号
	private String strtpmRemark;   // 备注
	private String strtpmDateStart;
	private String strtpmDateqiEnd;
	private String sellmmId;
	private String sellmmDate;
	private String selldCnt;
	private String selldIndex;     //销售明细编号
	private String selldCostPrice;
	private String selldCostAmount;
	private String selldPrice;
	private String selldAmount;
	private String fitPtype;
    private String strtpdRemark;
    private String indexId;
    private String strtpmDateView;
    private String detailData;
	private String sort;
	private String order;
	private int rows;
	private int page;
	private String strtpmAmont;
	private String strtpmCostAmont;
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
	
	public String getDetailData() {
		return detailData;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getStrtpmDateView() {
		return strtpmDateView;
	}

	public void setStrtpmDateView(String strtpmDateView) {
		this.strtpmDateView = strtpmDateView;
	}

	public String getCarVan() {
		return carVan;
	}

	public void setCarVan(String carVan) {
		this.carVan = carVan;
	}

	public String getStrtpdRemark() {
		return strtpdRemark;
	}

	public void setStrtpdRemark(String strtpdRemark) {
		this.strtpdRemark = strtpdRemark;
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

	public String getPartsNum() {
		return partsNum;
	}

	public void setPartsNum(String partsNum) {
		this.partsNum = partsNum;
	}

	public String getPartsLatestNoTaxprice() {
		return partsLatestNoTaxprice;
	}

	public void setPartsLatestNoTaxprice(String partsLatestNoTaxprice) {
		this.partsLatestNoTaxprice = partsLatestNoTaxprice;
	}

	public String getFitPtype() {
		return fitPtype;
	}

	public void setFitPtype(String fitPtype) {
		this.fitPtype = fitPtype;
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

	public String getSelldCnt() {
		return selldCnt;
	}

	public void setSelldCnt(String selldCnt) {
		this.selldCnt = selldCnt;
	}

	public String getSelldIndex() {
		return selldIndex;
	}

	public void setSelldIndex(String selldIndex) {
		this.selldIndex = selldIndex;
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

	public String getStrtpmDateStart() {
		return strtpmDateStart;
	}

	public void setStrtpmDateStart(String strtpmDateStart) {
		this.strtpmDateStart = strtpmDateStart;
	}

	public String getStrtpmDateqiEnd() {
		return strtpmDateqiEnd;
	}

	public void setStrtpmDateqiEnd(String strtpmDateqiEnd) {
		this.strtpmDateqiEnd = strtpmDateqiEnd;
	}

	public String getPartsLatestTaxpriceAmont() {
		return partsLatestTaxpriceAmont;
	}

	public void setPartsLatestTaxpriceAmont(String partsLatestTaxpriceAmont) {
		this.partsLatestTaxpriceAmont = partsLatestTaxpriceAmont;
	}

	public String getPartsLatestTaxprice() {
		return partsLatestTaxprice;
	}

	public void setPartsLatestTaxprice(String partsLatestTaxprice) {
		this.partsLatestTaxprice = partsLatestTaxprice;
	}

	public String getStrtpdIndex() {
		return strtpdIndex;
	}

	public void setStrtpdIndex(String strtpdIndex) {
		this.strtpdIndex = strtpdIndex;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getStrtpmId() {
		return strtpmId;
	}

	public void setStrtpmId(String strtpmId) {
		this.strtpmId = strtpmId;
	}

	public String getStrtpmDate() {
		return strtpmDate;
	}

	public void setStrtpmDate(String strtpmDate) {
		this.strtpmDate = strtpmDate;
	}

	public String getStrtpmType() {
		return strtpmType;
	}

	public void setStrtpmType(String strtpmType) {
		this.strtpmType = strtpmType;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStrtpmRemark() {
		return strtpmRemark;
	}

	public void setStrtpmRemark(String strtpmRemark) {
		this.strtpmRemark = strtpmRemark;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public String getStrtpmSumCnt() {
		return strtpmSumCnt;
	}

	public void setStrtpmSumCnt(String strtpmSumCnt) {
		this.strtpmSumCnt = strtpmSumCnt;
	}

	public String getRcptpartsSumPrice() {
		return rcptpartsSumPrice;
	}

	public void setRcptpartsSumPrice(String rcptpartsSumPrice) {
		this.rcptpartsSumPrice = rcptpartsSumPrice;
	}

	public String getReceptionId() {
		return receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

	public String getCarLicense() {
		return carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getCtypeName() {
		return ctypeName;
	}

	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
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

	public String getRcptpartsIndex() {
		return rcptpartsIndex;
	}

	public void setRcptpartsIndex(String rcptpartsIndex) {
		this.rcptpartsIndex = rcptpartsIndex;
	}

	public String getFrtReception() {
		return frtReception;
	}

	public void setFrtReception(String frtReception) {
		this.frtReception = frtReception;
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

	public String getClaimsType() {
		return claimsType;
	}

	public void setClaimsType(String claimsType) {
		this.claimsType = claimsType;
	}

	public String getPartsRemark() {
		return partsRemark;
	}

	public void setPartsRemark(String partsRemark) {
		this.partsRemark = partsRemark;
	}

	public String getStrtpdCnt() {
		return strtpdCnt;
	}

	public void setStrtpdCnt(String strtpdCnt) {
		this.strtpdCnt = strtpdCnt;
	}

	public String getReptypName() {
		return reptypName;
	}

	public void setReptypName(String reptypName) {
		this.reptypName = reptypName;
	}

	public String getClaimsName() {
		return claimsName;
	}

	public void setClaimsName(String claimsName) {
		this.claimsName = claimsName;
	}

	public String getPunitName() {
		return punitName;
	}

	public void setPunitName(String punitName) {
		this.punitName = punitName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public String getStrtpmAmont() {
		return strtpmAmont;
	}

	public void setStrtpmAmont(String strtpmAmont) {
		this.strtpmAmont = strtpmAmont;
	}

	public String getStrtpmCostAmont() {
		return strtpmCostAmont;
	}

	public void setStrtpmCostAmont(String strtpmCostAmont) {
		this.strtpmCostAmont = strtpmCostAmont;
	}

}
