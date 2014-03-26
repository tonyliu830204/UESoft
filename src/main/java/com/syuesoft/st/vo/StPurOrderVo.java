package com.syuesoft.st.vo;

import java.io.Serializable;

/**
 * 采购单VO
 * @author SuMing
 */
@SuppressWarnings("serial")
public class StPurOrderVo implements Serializable {
	
	private String orderId;             //采购订单编号                      StPurOrder：采购订单表
	private String orderDate;           //订货日期
	private String orderDates;           //订货日期
	private String storageId;
	private String deliveryDate;        //交货日期
	private String deliveryDates;        //交货日期
	private String numTotal;            //数量合计
	private String orderRemark;         //备注
	private String manager;             //经办人
	private String notesType;           //票据类型
	private String notesTypeId;           //票据类型
	private String classification;      //订单分类
	private String classificationId;      //订单分类
	private String examine;             //审核
	private String examineName;             //审核
	private String transitToState;      //在途状态
	private String transitToStateId;
	private String paid;                //付讫
	private String paidName;                //付讫
	private String taxRate;             //税率
	private String totalAmount;         //采购额
	private String notaxAmount;         //获取合计金额
	private String taxCount;            //税额
	private String buyer;                //采购员
	private String storeName;
	private String punitId;             //配件单位ID
	private String stpredIndex;         //配件流水号
	private String partsId;             //配件编号                                  StPurOrderitem：采购单明细表
	private String partsCount;          //配件数量
	private String taxPrice;            //含税价格
	private String notaxPrice;          //未税价格
	private String taxTotalamont;       //含税金额
	private String notaxTotalamont;     //获取未税额
	private String fitPtype;            //适用车型
	private String pbrdName;
	private String partsId1;             //配件编号
	private String partsName1;           //配件名称   
	private String changePriceId;
	private String pbrdId;
	private String storeId;
	private String relcampJm;
	private String stateName;  
	private String partsRepairPrice;  
	private String itemRemark;          //备注
	private String partsName;           //配件名称                                   FrtParts ：配件档案表
	private String punitName;           //配件单位
	private String partsNowCount;       //配件库存量
	private String partsLibrary;        //库位
	private String relcampId;           //单位编号                                    BasRelationCampany:相关单位表
	private String relcampName;         //单位名称
	private String relcampContact;      //联系人
	private String relcampTel1;         //联系人电话
	private String receptionId1; 
	private String carLicense1; 
	private String stfId;   //员工编号
	private String stfName;//员工名称
	private String orderDateStart;//查询        采购日期开始
	private String orderDateEnd;//查询    采购日期结束
	private String deliveryDateStart;//查询        采购日期开始 
	private String deliveryDateEnd;//查询    采购日期结束
	private String orderId1;//查询    采购单号
	private String relcampName2 ;//查询  供应商名称
	private String transitToState1 ;//查询  在途状态
	private String managerName;             //经办人
	private String transitToCount ;
	private String buyerName;                //采购员
	private String partsId2;//配件编号
	private String detailData;
	private String partsNum;
	private String inserted;
	private String updated;
	private String deleted;
	private String sort;
	private String order;
	private int rows;
	private int page;
	private String key;
	private String ptypeName;
    private String classificationValue;
	private String totalamount;                   //用于存放采购金额
	private String taxcount;                      //用于存放税额
	private String notaxtotalamont;               //用于存放未税金额
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	public String getTransitToStateId() {
		return transitToStateId;
	}
	public void setTransitToStateId(String transitToStateId) {
		this.transitToStateId = transitToStateId;
	}
	public String getDetailData() {
		return detailData;
	}
	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getTaxcount() {
		return taxcount;
	}
	public void setTaxcount(String taxcount) {
		this.taxcount = taxcount;
	}
	public String getNotaxtotalamont() {
		return notaxtotalamont;
	}
	public void setNotaxtotalamont(String notaxtotalamont) {
		this.notaxtotalamont = notaxtotalamont;
	}
	public String getExamineName() {
		return examineName;
	}
	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}
	public String getPaidName() {
		return paidName;
	}
	public void setPaidName(String paidName) {
		this.paidName = paidName;
	}
	public String getOrderDates() {
		return orderDates;
	}
	public void setOrderDates(String orderDates) {
		this.orderDates = orderDates;
	}
	public String getDeliveryDates() {
		return deliveryDates;
	}
	public void setDeliveryDates(String deliveryDates) {
		this.deliveryDates = deliveryDates;
	}
	public String getRelcampJm() {
		return relcampJm;
	}
	public void setRelcampJm(String relcampJm) {
		this.relcampJm = relcampJm;
	}
	public String getStorageId() {
		return storageId;
	}
	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}
	public String getDeliveryDateStart() {
		return deliveryDateStart;
	}
	public void setDeliveryDateStart(String deliveryDateStart) {
		this.deliveryDateStart = deliveryDateStart;
	}
	public String getDeliveryDateEnd() {
		return deliveryDateEnd;
	}
	public void setDeliveryDateEnd(String deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}
	public String getPbrdId() {
		return pbrdId;
	}
	public void setPbrdId(String pbrdId) {
		this.pbrdId = pbrdId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getChangePriceId() {
		return changePriceId;
	}
	public void setChangePriceId(String changePriceId) {
		this.changePriceId = changePriceId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getPbrdName() {
		return pbrdName;
	}
	public void setPbrdName(String pbrdName) {
		this.pbrdName = pbrdName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getPartsRepairPrice() {
		return partsRepairPrice;
	}
	public void setPartsRepairPrice(String partsRepairPrice) {
		this.partsRepairPrice = partsRepairPrice;
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
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getTransitToCount() {
		return transitToCount;
	}
	public void setTransitToCount(String transitToCount) {
		this.transitToCount = transitToCount;
	}
	public String getPartsNum() {
		return partsNum;
	}
	public void setPartsNum(String partsNum) {
		this.partsNum = partsNum;
	}
	public String getInserted() {
		return inserted;
	}
	public void setInserted(String inserted) {
		this.inserted = inserted;
	}
	public String getPtypeName() {
		return ptypeName;
	}
	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
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
	public String getRelcampContact() {
		return relcampContact;
	}
	public void setRelcampContact(String relcampContact) {
		this.relcampContact = relcampContact;
	}
	public String getPartsId2() {
		return partsId2;
	}
	public void setPartsId2(String partsId2) {
		this.partsId2 = partsId2;
	}
	public String getOrderId1() {
		return orderId1;
	}
	public void setOrderId1(String orderId1) {
		this.orderId1 = orderId1;
	}
	public String getRelcampName2() {
		return relcampName2;
	}
	public void setRelcampName2(String relcampName2) {
		this.relcampName2 = relcampName2;
	}
	public String getTransitToState1() {
		return transitToState1;
	}
	public void setTransitToState1(String transitToState1) {
		this.transitToState1 = transitToState1;
	}
	public String getOrderDateStart() {
		return orderDateStart;
	}
	public void setOrderDateStart(String orderDateStart) {
		this.orderDateStart = orderDateStart;
	}
	public String getOrderDateEnd() {
		return orderDateEnd;
	}
	public void setOrderDateEnd(String orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}
	public String getReceptionId1() {
		return receptionId1;
	}
	public void setReceptionId1(String receptionId1) {
		this.receptionId1 = receptionId1;
	}
	public String getCarLicense1() {
		return carLicense1;
	}
	public void setCarLicense1(String carLicense1) {
		this.carLicense1 = carLicense1;
	}
	public String getPunitId() {
		return punitId;
	}
	public void setPunitId(String punitId) {
		this.punitId = punitId;
	}
	public String getPartsId1() {
		return partsId1;
	}
	public void setPartsId1(String partsId1) {
		this.partsId1 = partsId1;
	}
	public String getPartsName1() {
		return partsName1;
	}
	public void setPartsName1(String partsName1) {
		this.partsName1 = partsName1;
	}
	public String getFitPtype() {
		return fitPtype;
	}
	public void setFitPtype(String fitPtype) {
		this.fitPtype = fitPtype;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
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
	public String getPartsId() {
		return partsId;
	}
	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}
	public String getPartsCount() {
		return partsCount;
	}
	public void setPartsCount(String partsCount) {
		this.partsCount = partsCount;
	}
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getNotaxAmount() {
		return notaxAmount;
	}
	public void setNotaxAmount(String notaxAmount) {
		this.notaxAmount = notaxAmount;
	}
	public String getTaxCount() {
		return taxCount;
	}
	public void setTaxCount(String taxCount) {
		this.taxCount = taxCount;
	}
	public String getItemRemark() {
		return itemRemark;
	}
	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
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
	public String getRelcampTel1() {
		return relcampTel1;
	}
	public void setRelcampTel1(String relcampTel1) {
		this.relcampTel1 = relcampTel1;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public String getNumTotal() {
		return numTotal;
	}
	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
	}

	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getStpredIndex() {
		return stpredIndex;
	}
	public void setStpredIndex(String stpredIndex) {
		this.stpredIndex = stpredIndex;
	}
	public String getNotesType() {
		return notesType;
	}
	public void setNotesType(String notesType) {
		this.notesType = notesType;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getExamine() {
		return examine;
	}
	public void setExamine(String examine) {
		this.examine = examine;
	}
	public String getTransitToState() {
		return transitToState;
	}
	public void setTransitToState(String transitToState) {
		this.transitToState = transitToState;
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
	public String getTaxTotalamont() {
		return taxTotalamont;
	}
	public void setTaxTotalamont(String taxTotalamont) {
		this.taxTotalamont = taxTotalamont;
	}
	public String getNotaxTotalamont() {
		return notaxTotalamont;
	}
	public void setNotaxTotalamont(String notaxTotalamont) {
		this.notaxTotalamont = notaxTotalamont;
	}
	public String getNotesTypeId() {
		return notesTypeId;
	}
	public void setNotesTypeId(String notesTypeId) {
		this.notesTypeId = notesTypeId;
	}
	public String getClassificationId() {
		return classificationId;
	}
	public void setClassificationId(String classificationId) {
		this.classificationId = classificationId;
	}
	public String getClassificationValue() {
		return classificationValue;
	}
	public void setClassificationValue(String classificationValue) {
		this.classificationValue = classificationValue;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
