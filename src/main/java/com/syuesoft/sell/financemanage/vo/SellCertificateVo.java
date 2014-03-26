package com.syuesoft.sell.financemanage.vo;

import java.util.Date;

public class SellCertificateVo {
	private Integer certificateId;		//编号
	private  Integer xsCarId;			//车辆档案信息编号
	private Integer receiptId;			//承兑汇票编号
	private String xsCarCertificate;	//合格证
	private Integer xsCarCertificateState;	//合格证状态
	private String certificateStateName;
	private Date redemptionDate;		//赎回日期
	private String certificatePerson;	//领证人
	private Date certificateDate;		//领证日期
	private String remark;				//备注
	private Date instorehouseDate;		//入库日期
	private String receiptCode;		//发票单号
	private Integer carBrand;
	private Integer carColor;
	private Integer carModelId;
	private String  carBrandName;
	private String carModelName;
	private String colorName;
	private String carVinNumber;

	private Integer receiptBank;		//出票银行
	private String bankName;
	private Date receiptStartDate;	//出票日期
	private Date  receiptEndDate;	//到期日期
	private Integer xsSellAllocatelType;
	private String allocatelTypeName;
	private String carCode;
	//分页字段
	private int page;
	private int rows;
	//查询字段
	private String queryStartDate;
	private String  queryEndDate;
	private String queryInstorehouseDate;
	private String queryStartDate2;
	private String  queryEndDate2;
	private String queryInstorehouseDate2;
	private String sellState;
	private String sellStateName;
	
	public String getSellStateName() {
		return sellStateName;
	}
	public void setSellStateName(String sellStateName) {
		this.sellStateName = sellStateName;
	}
	public String getSellState() {
		return sellState;
	}
	public void setSellState(String sellState) {
		this.sellState = sellState;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getQueryStartDate2() {
		return queryStartDate2;
	}
	public void setQueryStartDate2(String queryStartDate2) {
		this.queryStartDate2 = queryStartDate2;
	}
	public String getQueryEndDate2() {
		return queryEndDate2;
	}
	public void setQueryEndDate2(String queryEndDate2) {
		this.queryEndDate2 = queryEndDate2;
	}
	public String getQueryInstorehouseDate2() {
		return queryInstorehouseDate2;
	}
	public void setQueryInstorehouseDate2(String queryInstorehouseDate2) {
		this.queryInstorehouseDate2 = queryInstorehouseDate2;
	}
	private Integer enterpriseId;
	private Boolean flag;

	
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(Integer certificateId) {
		this.certificateId = certificateId;
	}
	public Integer getXsCarId() {
		return xsCarId;
	}
	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	public String getXsCarCertificate() {
		return xsCarCertificate;
	}
	public void setXsCarCertificate(String xsCarCertificate) {
		this.xsCarCertificate = xsCarCertificate;
	}
	public Integer getXsCarCertificateState() {
		return xsCarCertificateState;
	}
	public void setXsCarCertificateState(Integer xsCarCertificateState) {
		this.xsCarCertificateState = xsCarCertificateState;
	}
	public Date getRedemptionDate() {
		return redemptionDate;
	}
	public void setRedemptionDate(Date redemptionDate) {
		this.redemptionDate = redemptionDate;
	}
	public String getCertificatePerson() {
		return certificatePerson;
	}
	public void setCertificatePerson(String certificatePerson) {
		this.certificatePerson = certificatePerson;
	}
	public Date getCertificateDate() {
		return certificateDate;
	}
	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Date getInstorehouseDate() {
		return instorehouseDate;
	}
	public void setInstorehouseDate(Date instorehouseDate) {
		this.instorehouseDate = instorehouseDate;
	}
	public String getReceiptCode() {
		return receiptCode;
	}
	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}
	public Integer getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}
	public Integer getCarColor() {
		return carColor;
	}
	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}
	public Integer getCarModelId() {
		return carModelId;
	}
	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}
	public String getCarBrandName() {
		return carBrandName;
	}
	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}
	public String getCarModelName() {
		return carModelName;
	}
	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getCarVinNumber() {
		return carVinNumber;
	}
	public void setCarVinNumber(String carVinNumber) {
		this.carVinNumber = carVinNumber;
	}
	public String getCertificateStateName() {
		return certificateStateName;
	}
	public void setCertificateStateName(String certificateStateName) {
		this.certificateStateName = certificateStateName;
	}

	public Integer getReceiptBank() {
		return receiptBank;
	}
	public void setReceiptBank(Integer receiptBank) {
		this.receiptBank = receiptBank;
	}
	public Date getReceiptStartDate() {
		return receiptStartDate;
	}
	public void setReceiptStartDate(Date receiptStartDate) {
		this.receiptStartDate = receiptStartDate;
	}
	public Date getReceiptEndDate() {
		return receiptEndDate;
	}
	public void setReceiptEndDate(Date receiptEndDate) {
		this.receiptEndDate = receiptEndDate;
	}
	public Integer getXsSellAllocatelType() {
		return xsSellAllocatelType;
	}
	public void setXsSellAllocatelType(Integer xsSellAllocatelType) {
		this.xsSellAllocatelType = xsSellAllocatelType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAllocatelTypeName() {
		return allocatelTypeName;
	}
	public void setAllocatelTypeName(String allocatelTypeName) {
		this.allocatelTypeName = allocatelTypeName;
	}
	public String getQueryStartDate() {
		return queryStartDate;
	}
	public void setQueryStartDate(String queryStartDate) {
		this.queryStartDate = queryStartDate;
	}
	public String getQueryEndDate() {
		return queryEndDate;
	}
	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}
	public String getQueryInstorehouseDate() {
		return queryInstorehouseDate;
	}
	public void setQueryInstorehouseDate(String queryInstorehouseDate) {
		this.queryInstorehouseDate = queryInstorehouseDate;
	}	
}
