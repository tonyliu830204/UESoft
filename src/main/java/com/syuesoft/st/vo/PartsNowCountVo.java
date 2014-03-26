package com.syuesoft.st.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class PartsNowCountVo implements Serializable{
     
	private String partsId;             //配件编号一                    
	private String partsId2;            //配件编号二
	private String partsName;           //配件名称
	private String punitName;           //配件单位
	private String fitPtype;            //适用车型
	private String partsNowCount;       //库存量
	private String partsNowCount1;       //库存量
	private String partsLibrary;        //配件库位
	private String posName;             //部位名称
	private String ptypeId;             //配件型号ID
	private String ptypeName;           //配件型号名称
	private String partsLatestTaxprice; //含税成本价
	private String partsLatestNotaxprice;//未税成本价
	private String ctypeId;//配件品牌编号
	private String ctypeName;//配件品牌名称
	private String partsRepairPrice;     //维修价
	private String storeName;            //仓别（仓库名称）
	private String storeId;
	private String stockUpper;     		 //上限
	private String stockLower;   		 //下限
	private String changePriceId;            //配件调价编号
	private String storageDate;          //入库时间
	private String cbrdName;           //车辆品牌名称
	private String cbrdId;             //品牌编号
	private String pbrdId;              //品牌编号
	private String pbrdName;            //品牌名称
	private String partsSellPrice;
	private String partsPointPrice;      //网点价格
	private String partsSpecialPrice;    //特别价格
	private String partsClaimantPrice;   //索赔价格
	private String pnc_strtgmDateStart;  //入库积压
	private String pnc_strtgmDateEnd;    //入库积压
	private String partsNowCountZero;
	private String sort;
	private String order;
	private String q;
	private int rows;
	private int page;
	private String searchStyle;
	private String spaceTime;
	private String inAndOutTime;
	private String pnc_storageDateStart;//出库积压
	private String pnc_storageDateEnd ;//出库积压
	private String StorageId;
	private String inserted;
	private String updated;
	private String deleted;
	private Double classfication;
	private Double sumCount;
	private Double sumTaxPrice;
	private Double sumNoTaxPrice;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	
	
	
	public Double getClassfication() {
		return classfication;
	}

	public void setClassfication(Double classfication) {
		this.classfication = classfication;
	}

	public Double getSumCount() {
		return sumCount;
	}

	public void setSumCount(Double sumCount) {
		this.sumCount = sumCount;
	}

	public Double getSumTaxPrice() {
		return sumTaxPrice;
	}

	public void setSumTaxPrice(Double sumTaxPrice) {
		this.sumTaxPrice = sumTaxPrice;
	}

	public Double getSumNoTaxPrice() {
		return sumNoTaxPrice;
	}

	public void setSumNoTaxPrice(Double sumNoTaxPrice) {
		this.sumNoTaxPrice = sumNoTaxPrice;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getStorageId() {
		return StorageId;
	}

	public void setStorageId(String storageId) {
		StorageId = storageId;
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

	public String getInAndOutTime() {
		return inAndOutTime;
	}

	public void setInAndOutTime(String inAndOutTime) {
		this.inAndOutTime = inAndOutTime;
	}

	public String getPnc_storageDateStart() {
		return pnc_storageDateStart;
	}

	public void setPnc_storageDateStart(String pncStorageDateStart) {
		pnc_storageDateStart = pncStorageDateStart;
	}

	public String getPnc_storageDateEnd() {
		return pnc_storageDateEnd;
	}

	public void setPnc_storageDateEnd(String pncStorageDateEnd) {
		pnc_storageDateEnd = pncStorageDateEnd;
	}

	public String getSpaceTime() {
		return spaceTime;
	}

	public void setSpaceTime(String spaceTime) {
		this.spaceTime = spaceTime;
	}

	public String getPnc_strtgmDateStart() {
		return pnc_strtgmDateStart;
	}

	public void setPnc_strtgmDateStart(String pncStrtgmDateStart) {
		pnc_strtgmDateStart = pncStrtgmDateStart;
	}

	public String getPnc_strtgmDateEnd() {
		return pnc_strtgmDateEnd;
	}

	public void setPnc_strtgmDateEnd(String pncStrtgmDateEnd) {
		pnc_strtgmDateEnd = pncStrtgmDateEnd;
	}

	public String getPartsSellPrice() {
		return partsSellPrice;
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

	public String getChangePriceId() {
		return changePriceId;
	}

	public void setChangePriceId(String changePriceId) {
		this.changePriceId = changePriceId;
	}

	public String getPtypeId() {
		return ptypeId;
	}

	public void setPtypeId(String ptypeId) {
		this.ptypeId = ptypeId;
	}

	public String getPtypeName() {
		return ptypeName;
	}

	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
	}

	public String getCtypeName() {
		return ctypeName;
	}

	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}

	public String getCtypeId() {
		return ctypeId;
	}

	public void setCtypeId(String ctypeId) {
		this.ctypeId = ctypeId;
	}

	public String getPbrdId() {
		return pbrdId;
	}

	public void setPbrdId(String pbrdId) {
		this.pbrdId = pbrdId;
	}

	public String getCbrdId() {
		return cbrdId;
	}

	public void setCbrdId(String cbrdId) {
		this.cbrdId = cbrdId;
	}

	public String getPartsId() {
		return partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public String getPartsId2() {
		return partsId2;
	}

	public void setPartsId2(String partsId2) {
		this.partsId2 = partsId2;
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

	public String getPartsLibrary() {
		return partsLibrary;
	}

	public void setPartsLibrary(String partsLibrary) {
		this.partsLibrary = partsLibrary;
	}

	public String getPartsRepairPrice() {
		return partsRepairPrice;
	}

	public void setPartsRepairPrice(String partsRepairPrice) {
		this.partsRepairPrice = partsRepairPrice;
	}

	public String getPartsLatestNotaxprice() {
		return partsLatestNotaxprice;
	}

	public void setPartsLatestNotaxprice(String partsLatestNotaxprice) {
		this.partsLatestNotaxprice = partsLatestNotaxprice;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStockUpper() {
		return stockUpper;
	}

	public void setStockUpper(String stockUpper) {
		this.stockUpper = stockUpper;
	}

	public String getStockLower() {
		return stockLower;
	}

	public void setStockLower(String stockLower) {
		this.stockLower = stockLower;
	}

	public String getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}
	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getCbrdName() {
		return cbrdName;
	}

	public void setCbrdName(String cbrdName) {
		this.cbrdName = cbrdName;
	}

	public String getPbrdName() {
		return pbrdName;
	}

	public void setPbrdName(String pbrdName) {
		this.pbrdName = pbrdName;
	}

	public String getPartsLatestTaxprice() {
		return partsLatestTaxprice;
	}

	public void setPartsLatestTaxprice(String partsLatestTaxprice) {
		this.partsLatestTaxprice = partsLatestTaxprice;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public String getSearchStyle() {
		return searchStyle;
	}

	public void setSearchStyle(String searchStyle) {
		this.searchStyle = searchStyle;
	}

	public String getPartsNowCount1() {
		return partsNowCount1;
	}

	public void setPartsNowCount1(String partsNowCount1) {
		this.partsNowCount1 = partsNowCount1;
	}

    public String getPartsNowCountZero()
    {
        return partsNowCountZero;
    }

    public void setPartsNowCountZero(String partsNowCountZero)
    {
        this.partsNowCountZero = partsNowCountZero;
    }
}
