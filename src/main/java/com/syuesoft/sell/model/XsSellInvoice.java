package com.syuesoft.sell.model;

import java.util.Date;
import com.syuesoft.model.BaseBean;
public class XsSellInvoice extends BaseBean implements java.io.Serializable {
	private Integer id;		//编号
	private String invoiceCode;		//开票单号
	private XsCarSellInfo xsCarSellInfo;		//车辆销售信息
	private Date  invoiceDate;		//开票日期
	private Integer invoicePerson;	//开票人
	private Integer person;			//手款人
	private String invoiceNumber;	//发票号码
	private String achievement;		//业绩系数
	private Double invoiceParce;	//开票金额
	private Integer invoiceType;  //票据类型
	private String invoiceRemark;		//开票备注
	private Integer examin;			//审核状态
	private Double hsDiscount;//含税折扣金额
	private Double wsDiscount;//未税折扣金额
	private Double discount;//折扣税额
	private Double afterHsDiscount;//折后含税金额
	private Double wsPurchase;//未税采购额
	private Double purchase;//采购税额
	private Double afterWsMoney;//折后未税金额
	private Double zhTax;//折后税额
	
	public Double getHsDiscount() {
		return hsDiscount;
	}
	public void setHsDiscount(Double hsDiscount) {
		this.hsDiscount = hsDiscount;
	}
	public Double getWsDiscount() {
		return wsDiscount;
	}
	public void setWsDiscount(Double wsDiscount) {
		this.wsDiscount = wsDiscount;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getAfterHsDiscount() {
		return afterHsDiscount;
	}
	public void setAfterHsDiscount(Double afterHsDiscount) {
		this.afterHsDiscount = afterHsDiscount;
	}
	public Double getWsPurchase() {
		return wsPurchase;
	}
	public void setWsPurchase(Double wsPurchase) {
		this.wsPurchase = wsPurchase;
	}
	public Double getPurchase() {
		return purchase;
	}
	public void setPurchase(Double purchase) {
		this.purchase = purchase;
	}
	public Double getAfterWsMoney() {
		return afterWsMoney;
	}
	public void setAfterWsMoney(Double afterWsMoney) {
		this.afterWsMoney = afterWsMoney;
	}
	public Double getZhTax() {
		return zhTax;
	}
	public void setZhTax(Double zhTax) {
		this.zhTax = zhTax;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public XsCarSellInfo getXsCarSellInfo() {
		return xsCarSellInfo;
	}
	public void setXsCarSellInfo(XsCarSellInfo xsCarSellInfo) {
		this.xsCarSellInfo = xsCarSellInfo;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Integer getInvoicePerson() {
		return invoicePerson;
	}
	public void setInvoicePerson(Integer invoicePerson) {
		this.invoicePerson = invoicePerson;
	}
	public Integer getPerson() {
		return person;
	}
	public void setPerson(Integer person) {
		this.person = person;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public Double getInvoiceParce() {
		return invoiceParce;
	}
	public void setInvoiceParce(Double invoiceParce) {
		this.invoiceParce = invoiceParce;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceRemark() {
		return invoiceRemark;
	}
	public void setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
	}
	public Integer getExamin() {
		return examin;
	}
	public void setExamin(Integer examin) {
		this.examin = examin;
	}
	
	
}