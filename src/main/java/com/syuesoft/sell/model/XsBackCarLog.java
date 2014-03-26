package com.syuesoft.sell.model;

import java.sql.Timestamp;


/**
 * XsSellReceivablesDetail entity. @author MyEclipse Persistence Tools
 */

public class XsBackCarLog  implements java.io.Serializable {



     private Integer backCarId;						//退款编号	
	 private Integer backCarPerson;					//经办人
	 private Integer backMoneyPerson;				//退款人
	 private Integer invoice;						//发票类型
	 private Integer examine;						//审核情况
     private String backCarCode;					//退款单号	
     private Double backCarSunmoney;				//应退金额
     private Double backCarMoney;					//退换金额
     private String backCarDocument;				//预订单/退车单号
     private String invoiceNum;						//票据编号
     private String backMoneyDate;					//退款日期
     private String backCarDate;					//经办日期


    // Constructors

    /** default constructor */
    public XsBackCarLog() {
    }


	public Integer getBackCarId() {
		return backCarId;
	}


	public void setBackCarId(Integer backCarId) {
		this.backCarId = backCarId;
	}


	public Integer getBackCarPerson() {
		return backCarPerson;
	}


	public void setBackCarPerson(Integer backCarPerson) {
		this.backCarPerson = backCarPerson;
	}


	public Integer getBackMoneyPerson() {
		return backMoneyPerson;
	}


	public void setBackMoneyPerson(Integer backMoneyPerson) {
		this.backMoneyPerson = backMoneyPerson;
	}


	public Integer getInvoice() {
		return invoice;
	}


	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}



	public Integer getExamine() {
		return examine;
	}


	public void setExamine(Integer examine) {
		this.examine = examine;
	}


	public String getBackCarCode() {
		return backCarCode;
	}


	public void setBackCarCode(String backCarCode) {
		this.backCarCode = backCarCode;
	}


	public Double getBackCarSunmoney() {
		return backCarSunmoney;
	}


	public void setBackCarSunmoney(Double backCarSunmoney) {
		this.backCarSunmoney = backCarSunmoney;
	}


	public Double getBackCarMoney() {
		return backCarMoney;
	}


	public void setBackCarMoney(Double backCarMoney) {
		this.backCarMoney = backCarMoney;
	}


	public String getInvoiceNum() {
		return invoiceNum;
	}


	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}


	public String getBackMoneyDate() {
		return backMoneyDate;
	}


	public void setBackMoneyDate(String backMoneyDate) {
		this.backMoneyDate = backMoneyDate;
	}


	public String getBackCarDate() {
		return backCarDate;
	}


	public void setBackCarDate(String backCarDate) {
		this.backCarDate = backCarDate;
	}


	public String getBackCarDocument() {
		return backCarDocument;
	}


	public void setBackCarDocument(String backCarDocument) {
		this.backCarDocument = backCarDocument;
	}

}