package com.syuesoft.sell.financemanage.vo;

public class RefundmentVo {
	 private int page;
	 private int rows;
	 
	 private String backCar_Id;					//退车款编号	
	 private String backCar_Person;				//经办人
	 private String backMoney_Person;			//退款人
	 private String invoice;					//发票类型
	 private String backCar_Code;				//退车款单号	
	 private String backCar_Sunmoney;			//应退金额
	 private String backCar_Money;				//退换金额
	 private String backCar_Document;			//预订单/退车单号
	 private String invoice_Num;				//票据编号
	 private String backMoney_Date;				//退款日期
	 private String backMoney_Date2;				//退款日期
	 private String backCar_Date;				//经办日期
	 private String backCar_Date2;				//经办日期
	 private String exitCar_Id;					//退车单号
	 
	 private String stfName;
	 private String prefix;						//预订单、退车单退款标示
	 private String invoiceId;
	 private Integer aduitId;
	 
	 private String aduitName;
	 private Boolean editTag;					//修改标示or退款记录标示
	 private String noBackCar_Money;			//未退金额
	 
	 private Integer enterpriseId;
	 private Boolean flag;
	 
	 
	 
	public String getBackCar_Date2() {
		return backCar_Date2;
	}
	public void setBackCar_Date2(String backCarDate2) {
		backCar_Date2 = backCarDate2;
	}
	public String getBackMoney_Date2() {
		return backMoney_Date2;
	}
	public void setBackMoney_Date2(String backMoneyDate2) {
		backMoney_Date2 = backMoneyDate2;
	}
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
	public String getExitCar_Id() {
		return exitCar_Id;
	}
	public void setExitCar_Id(String exitCarId) {
		exitCar_Id = exitCarId;
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
	public String getBackCar_Id() {
		return backCar_Id;
	}
	public void setBackCar_Id(String backCarId) {
		backCar_Id = backCarId;
	}
	public String getBackCar_Person() {
		return backCar_Person;
	}
	public void setBackCar_Person(String backCarPerson) {
		backCar_Person = backCarPerson;
	}
	public String getBackMoney_Person() {
		return backMoney_Person;
	}
	public void setBackMoney_Person(String backMoneyPerson) {
		backMoney_Person = backMoneyPerson;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getBackCar_Code() {
		return backCar_Code;
	}
	public void setBackCar_Code(String backCarCode) {
		backCar_Code = backCarCode;
	}
	public String getBackCar_Sunmoney() {
		return backCar_Sunmoney;
	}
	public void setBackCar_Sunmoney(String backCarSunmoney) {
		backCar_Sunmoney = backCarSunmoney;
	}
	public String getBackCar_Money() {
		return backCar_Money;
	}
	public void setBackCar_Money(String backCarMoney) {
		backCar_Money = backCarMoney;
	}
	public String getInvoice_Num() {
		return invoice_Num;
	}
	public void setInvoice_Num(String invoiceNum) {
		invoice_Num = invoiceNum;
	}
	public String getBackMoney_Date() {
		return backMoney_Date;
	}
	public void setBackMoney_Date(String backMoneyDate) {
		backMoney_Date = backMoneyDate;
	}
	public String getBackCar_Date() {
		return backCar_Date;
	}
	public void setBackCar_Date(String backCarDate) {
		backCar_Date = backCarDate;
	}
	public String getStfName() {
		return stfName;
	}
	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
	public String getBackCar_Document() {
		return backCar_Document;
	}
	public void setBackCar_Document(String backCarDocument) {
		backCar_Document = backCarDocument;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public Integer getAduitId() {
		return aduitId;
	}
	public void setAduitId(Integer aduitId) {
		this.aduitId = aduitId;
	}
	public String getAduitName() {
		return aduitName;
	}
	public void setAduitName(String aduitName) {
		this.aduitName = aduitName;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Boolean getEditTag() {
		return editTag;
	}
	public void setEditTag(Boolean editTag) {
		this.editTag = editTag;
	}
	public String getNoBackCar_Money() {
		return noBackCar_Money;
	}
	public void setNoBackCar_Money(String noBackCarMoney) {
		noBackCar_Money = noBackCarMoney;
	}
	 
	 
}
