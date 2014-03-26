package com.syuesoft.sell.financemanage.vo;

import java.util.Date;

public class SellReceiptVo {
	private Integer receiptId;		//编号
	private String receiptCode;		//票据编号
	private Integer receiptBank;		//出票银行
	private Double receiptMoney;	//金额
	private Date receiptStartDate;	//出票日期
	private Date  receiptEndDate;	//到期日期
	private Integer receiptState;	//状态
	private String remark;			//备注
	private String bankName;//银行名称
	//分页字段
	private int page;
	private int rows;
	//查询所用字段
	private String queryStartDate;
	private String  queryEndDate;
	private String queryStartDate2;
	private String  queryEndDate2;
	private Integer enterpriseId;	//企业编号
	private Boolean flag;	
	
	
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
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	public String getReceiptCode() {
		return receiptCode;
	}
	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}

	public Integer getReceiptBank() {
		return receiptBank;
	}
	public void setReceiptBank(Integer receiptBank) {
		this.receiptBank = receiptBank;
	}
	public Double getReceiptMoney() {
		return receiptMoney;
	}
	public void setReceiptMoney(Double receiptMoney) {
		this.receiptMoney = receiptMoney;
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
	public Integer getReceiptState() {
		return receiptState;
	}
	public void setReceiptState(Integer receiptState) {
		this.receiptState = receiptState;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	
	
}
