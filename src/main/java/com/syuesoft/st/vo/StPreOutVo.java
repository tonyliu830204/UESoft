package com.syuesoft.st.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class StPreOutVo implements Serializable{

	private String stpredIndex;    //编号
	private String fitPtype;       //类型
	private String partsId;        //配件编号
	private String partsName;      //配件名称
	private String punitName;      //单位
	private String partsInstPrice;//成本价
	private String stpredCnt;      //数量
	private String partsNowCount;  //库存
	private String partsLibrary;   //库位
	private String reptName;       //索赔
	private String claimsId;       //索赔分类编号
	private String reptypname;     //收费性质
	private String reptypId;       //收费性质编号
	private String stfId;          //领料员编号
	private String stfName;        //领料员姓名
	private String numTotal;       //总数量
	private String taxCast;        //含税成本
	private String noTaxCast;      //未税成本
	private String itemPrice;      //销售价
	private String amount;          //销售金额
	private String taxCastAmont;   //含税成本额
	private String noTaxCastAmont; //未税成本额
	private String partsLatestTaxprice;//含税成本价
	private String itemCount;
	private String claimsType ;
	private String itemCharge;
	private String stpremId;        //预出单号
	private String stpremTime;      //预出日期
	private String stpremSumAmount; //合计预出库金额
	private String stpremSumCost;   //合计预出库成本额
	private String stpremStfId;     //相关人员编号（经办人编号）
	private String manager;     //相关人员编号（经办人编号）
	private String stpremFlg;       //预出库分类
	private String storeId;         //仓库编号
	private String storeName;       //仓库名称
	private String stomRemark;      //备注
	private String receptionId;     //（前台接车表）工单号
	private String carLicense;      //(车辆档案表)车牌照
	private String ctypeName;       //(车辆档案表中拿车辆品牌编号到车辆型号表中找到车辆型号名称)
	private String carInTime;       //（前台接车表）入厂时间
	private String receptionRepPer; //（前台接车表“三包人员”）前台接待
	private String reptName1;        //（	前台接车表中"维修类别编号"）维修分类表中获取 ：维修分类名称
	private String customName;       //(客户档案表)客户名称
	private String carVan;           //（车辆档案表）vin号
    private String ChangePriceId;
	private String repgrpName;       //维修班组
	private String stpremTimeStart ; //查询    预出库时间
	private String stpremTimeEnd;//查询   预出库截至时间
	private String relcampId;
	private String stpremTimeView;
	private String notaxCast;
	private String stpremFlgId;
	private String detailData;
	private String sort;
	private String order;
	private int rows;
	private int page;
	private String itemRemark;
	private String inserted;
	private String updated;
	private String deleted;
	private String sumCount  ;
	private String sellAmont;
	private String taxCastamont;
	private String notaxCastamont;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	
	public String getItemRemark() {
		return itemRemark;
	}
	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
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
	public String getNotaxCast() {
		return notaxCast;
	}
	public void setNotaxCast(String notaxCast) {
		this.notaxCast = notaxCast;
	}
	public String getDetailData() {
		return detailData;
	}
	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}
	public String getStpremFlgId() {
		return stpremFlgId;
	}
	public void setStpremFlgId(String stpremFlgId) {
		this.stpremFlgId = stpremFlgId;
	}
	public String getStpremTimeView() {
		return stpremTimeView;
	}
	public void setStpremTimeView(String stpremTimeView) {
		this.stpremTimeView = stpremTimeView;
	}
	public String getSumCount() {
		return sumCount;
	}
	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}
	public String getSellAmont() {
		return sellAmont;
	}
	public void setSellAmont(String sellAmont) {
		this.sellAmont = sellAmont;
	}
	public String getRelcampId() {
		return relcampId;
	}
	public void setRelcampId(String relcampId) {
		this.relcampId = relcampId;
	}
	public String getRepgrpName() {
		return repgrpName;
	}
	public void setRepgrpName(String repgrpName) {
		this.repgrpName = repgrpName;
	}
	public String getChangePriceId() {
		return ChangePriceId;
	}
	public void setChangePriceId(String changePriceId) {
		ChangePriceId = changePriceId;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
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
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getStpremTimeStart() {
		return stpremTimeStart;
	}
	public void setStpremTimeStart(String stpremTimeStart) {
		this.stpremTimeStart = stpremTimeStart;
	}
	public String getStpremTimeEnd() {
		return stpremTimeEnd;
	}
	public void setStpremTimeEnd(String stpremTimeEnd) {
		this.stpremTimeEnd = stpremTimeEnd;
	}
	public String getPartsLatestTaxprice() {
		return partsLatestTaxprice;
	}
	public void setPartsLatestTaxprice(String partsLatestTaxprice) {
		this.partsLatestTaxprice = partsLatestTaxprice;
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
	
	public String getTaxCast() {
		return taxCast;
	}
	public void setTaxCast(String taxCast) {
		this.taxCast = taxCast;
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
	public String getNoTaxCast() {
		return noTaxCast;
	}
	public void setNoTaxCast(String noTaxCast) {
		this.noTaxCast = noTaxCast;
	}
	public String getTaxCastAmont() {
		return taxCastAmont;
	}
	public void setTaxCastAmont(String taxCastAmont) {
		this.taxCastAmont = taxCastAmont;
	}
	public String getNoTaxCastAmont() {
		return noTaxCastAmont;
	}
	public void setNoTaxCastAmont(String noTaxCastAmont) {
		this.noTaxCastAmont = noTaxCastAmont;
	}
	public String getNumTotal() {
		return numTotal;
	}
	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCarVan() {
		return carVan;
	}
	public void setCarVan(String carVan) {
		this.carVan = carVan;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
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
	public String getCarInTime() {
		return carInTime;
	}
	public void setCarInTime(String carInTime) {
		this.carInTime = carInTime;
	}
	public String getReceptionRepPer() {
		return receptionRepPer;
	}
	public void setReceptionRepPer(String receptionRepPer) {
		this.receptionRepPer = receptionRepPer;
	}
	public String getReptName1() {
		return reptName1;
	}
	public void setReptName1(String reptName1) {
		this.reptName1 = reptName1;
	}
	
	
	public String getStpredIndex() {
		return stpredIndex;
	}
	public void setStpredIndex(String stpredIndex) {
		this.stpredIndex = stpredIndex;
	}
	public String getFitPtype() {
		return fitPtype;
	}
	public void setFitPtype(String fitPtype) {
		this.fitPtype = fitPtype;
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
	
	public String getPartsInstPrice() {
		return partsInstPrice;
	}
	public void setPartsInstPrice(String partsInstPrice) {
		this.partsInstPrice = partsInstPrice;
	}
	public String getStpredCnt() {
		return stpredCnt;
	}
	public void setStpredCnt(String stpredCnt) {
		this.stpredCnt = stpredCnt;
	}
	public String getPartsNowCount() {
		return partsNowCount;
	}
	public void setPartsNowCount(String partsNowCount) {
		this.partsNowCount = partsNowCount;
	}
	public String getPartsLibrary() {
		return partsLibrary;
	}
	public void setPartsLibrary(String partsLibrary) {
		this.partsLibrary = partsLibrary;
	}
	public String getReptName() {
		return reptName;
	}
	public void setReptName(String reptName) {
		this.reptName = reptName;
	}
	public String getReptypname() {
		return reptypname;
	}
	public void setReptypname(String reptypname) {
		this.reptypname = reptypname;
	}
	public String getClaimsId() {
		return claimsId;
	}
	public void setClaimsId(String claimsId) {
		this.claimsId = claimsId;
	}
	public String getReptypId() {
		return reptypId;
	}
	public void setReptypId(String reptypId) {
		this.reptypId = reptypId;
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
	public String getStpremId() {
		return stpremId;
	}
	public void setStpremId(String stpremId) {
		this.stpremId = stpremId;
	}
	public String getStpremTime() {
		return stpremTime;
	}
	public void setStpremTime(String stpremTime) {
		this.stpremTime = stpremTime;
	}
	public String getStpremSumAmount() {
		return stpremSumAmount;
	}
	public void setStpremSumAmount(String stpremSumAmount) {
		this.stpremSumAmount = stpremSumAmount;
	}
	public String getStpremSumCost() {
		return stpremSumCost;
	}
	public void setStpremSumCost(String stpremSumCost) {
		this.stpremSumCost = stpremSumCost;
	}
	public String getStpremStfId() {
		return stpremStfId;
	}
	public void setStpremStfId(String stpremStfId) {
		this.stpremStfId = stpremStfId;
	}
	public String getStpremFlg() {
		return stpremFlg;
	}
	public void setStpremFlg(String stpremFlg) {
		this.stpremFlg = stpremFlg;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStomRemark() {
		return stomRemark;
	}
	public void setStomRemark(String stomRemark) {
		this.stomRemark = stomRemark;
	}	
	
	
}
