package com.syuesoft.st.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class StMoveStorehouseVo implements Serializable{
	
	private String partsId;
	private String partsName;
	private String punitName;
	private String partsLibrary;
	private String storeId;
	private String msdmDateView;
	
	private String msdCnt;      //数量
	private String msdNocast;   //未税成本
	private String msdNocastAmont;//未税成本额
	private String partsNowCount; //库存量
	private String changePriceId;
	private String msdRemark;
	private String notaxCast;
	private String taxprice;
	private String notaxprice;
	
	private String msdmSumCnt;//移仓总数量
	private String msdmSumAmont;//移仓总金额
	
	private String msdmOutStorehouseName;
	private String MsdmInStorehouseName;
	
	private String msdmId;
	private String msdmDate;
	private String msdmManager;
	private String msdmManagerName;
	private String msdmRemark;
	private String storeName;
	
	private String msdmDateStart;//查询移仓开始时间
	private String msdmDateEnd;//查询移仓结束时间
    private String msdIndex;
	private String inserted;
	private String updated;
	private String deleted;
	
	private String outStoreId;
	private String inStoreId;
	private String relcampId;
	private String relcampName;
	private String examine;
	
	private String sort;
	private String order;
	private int rows;
	private int page;
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	public String getMsdmDateView() {
		return msdmDateView;
	}
	public void setMsdmDateView(String msdmDateView) {
		this.msdmDateView = msdmDateView;
	}
	public String getExamine() {
		return examine;
	}
	public void setExamine(String examine) {
		this.examine = examine;
	}
	public String getTaxprice() {
		return taxprice;
	}
	public void setTaxprice(String taxprice) {
		this.taxprice = taxprice;
	}
	public String getNotaxprice() {
		return notaxprice;
	}
	public void setNotaxprice(String notaxprice) {
		this.notaxprice = notaxprice;
	}
	public String getNotaxCast() {
		return notaxCast;
	}
	public void setNotaxCast(String notaxCast) {
		this.notaxCast = notaxCast;
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
	public String getMsdIndex() {
		return msdIndex;
	}
	public void setMsdIndex(String msdIndex) {
		this.msdIndex = msdIndex;
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
	public String getMsdRemark() {
		return msdRemark;
	}
	public void setMsdRemark(String msdRemark) {
		this.msdRemark = msdRemark;
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
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getMsdCnt() {
		return msdCnt;
	}
	public void setMsdCnt(String msdCnt) {
		this.msdCnt = msdCnt;
	}
	public String getMsdNocast() {
		return msdNocast;
	}
	public void setMsdNocast(String msdNocast) {
		this.msdNocast = msdNocast;
	}
	public String getMsdNocastAmont() {
		return msdNocastAmont;
	}
	public void setMsdNocastAmont(String msdNocastAmont) {
		this.msdNocastAmont = msdNocastAmont;
	}
	public String getPartsNowCount() {
		return partsNowCount;
	}
	public void setPartsNowCount(String partsNowCount) {
		this.partsNowCount = partsNowCount;
	}
	public String getChangePriceId() {
		return changePriceId;
	}
	public void setChangePriceId(String changePriceId) {
		this.changePriceId = changePriceId;
	}
	public String getMsdmSumCnt() {
		return msdmSumCnt;
	}
	public void setMsdmSumCnt(String msdmSumCnt) {
		this.msdmSumCnt = msdmSumCnt;
	}
	public String getMsdmSumAmont() {
		return msdmSumAmont;
	}
	public void setMsdmSumAmont(String msdmSumAmont) {
		this.msdmSumAmont = msdmSumAmont;
	}
	public String getMsdmOutStorehouseName() {
		return msdmOutStorehouseName;
	}
	public void setMsdmOutStorehouseName(String msdmOutStorehouseName) {
		this.msdmOutStorehouseName = msdmOutStorehouseName;
	}
	public String getMsdmInStorehouseName() {
		return MsdmInStorehouseName;
	}
	public void setMsdmInStorehouseName(String msdmInStorehouseName) {
		MsdmInStorehouseName = msdmInStorehouseName;
	}
	public String getMsdmId() {
		return msdmId;
	}
	public void setMsdmId(String msdmId) {
		this.msdmId = msdmId;
	}
	public String getMsdmDate() {
		return msdmDate;
	}
	public void setMsdmDate(String msdmDate) {
		this.msdmDate = msdmDate;
	}
	public String getMsdmManager() {
		return msdmManager;
	}
	public void setMsdmManager(String msdmManager) {
		this.msdmManager = msdmManager;
	}
	public String getMsdmManagerName() {
		return msdmManagerName;
	}
	public void setMsdmManagerName(String msdmManagerName) {
		this.msdmManagerName = msdmManagerName;
	}
	public String getMsdmRemark() {
		return msdmRemark;
	}
	public void setMsdmRemark(String msdmRemark) {
		this.msdmRemark = msdmRemark;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getMsdmDateStart() {
		return msdmDateStart;
	}
	public void setMsdmDateStart(String msdmDateStart) {
		this.msdmDateStart = msdmDateStart;
	}
	public String getMsdmDateEnd() {
		return msdmDateEnd;
	}
	public void setMsdmDateEnd(String msdmDateEnd) {
		this.msdmDateEnd = msdmDateEnd;
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
	public String getOutStoreId() {
		return outStoreId;
	}
	public void setOutStoreId(String outStoreId) {
		this.outStoreId = outStoreId;
	}
	public String getInStoreId() {
		return inStoreId;
	}
	public void setInStoreId(String inStoreId) {
		this.inStoreId = inStoreId;
	}
}
