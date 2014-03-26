package com.syuesoft.vipmanagement.vo;

import java.sql.Date;

public class BasVipRechargeVO {
	private Integer vipRecId;
	private String vipId;
	private Integer recAmount;
	private Integer giveAmount;
	private Integer giveInte;
	private String operator;
	private String vipRecNote;
	private Date vipRecDate;
	private Integer nowInte;
	private Integer nowAmount;
	private String payType;
	
	public BasVipRechargeVO(){
		
	}
	
	public BasVipRechargeVO(Integer vipRecId, String vipId,
			Integer recAmount, Integer giveAmount, Integer giveInte,
			String operator, String vipRecNote, Date vipRecDate,
			Integer nowInte,Integer nowAmount,String payType) {
		this.vipRecId = vipRecId;
		this.vipId = vipId;
		this.recAmount = recAmount;
		this.giveAmount = giveAmount;
		this.giveInte = giveInte;
		this.operator = operator;
		this.vipRecNote = vipRecNote;
		this.vipRecDate = vipRecDate;
		this.nowInte = nowInte;
		this.nowAmount = nowAmount;
		this.payType = payType;
	}

	public Integer getVipRecId() {
		return vipRecId;
	}

	public void setVipRecId(Integer vipRecId) {
		this.vipRecId = vipRecId;
	}

	public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId;
	}

	public Integer getRecAmount() {
		return recAmount;
	}

	public void setRecAmount(Integer recAmount) {
		this.recAmount = recAmount;
	}

	public Integer getGiveAmount() {
		return giveAmount;
	}

	public void setGiveAmount(Integer giveAmount) {
		this.giveAmount = giveAmount;
	}

	public Integer getGiveInte() {
		return giveInte;
	}

	public void setGiveInte(Integer giveInte) {
		this.giveInte = giveInte;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getVipRecNote() {
		return vipRecNote;
	}

	public void setVipRecNote(String vipRecNote) {
		this.vipRecNote = vipRecNote;
	}

	public Date getVipRecDate() {
		return vipRecDate;
	}

	public void setVipRecDate(Date vipRecDate) {
		this.vipRecDate = vipRecDate;
	}

	public Integer getNowInte() {
		return nowInte;
	}

	public void setNowInte(Integer nowInte) {
		this.nowInte = nowInte;
	}

	public Integer getNowAmount() {
		return nowAmount;
	}

	public void setNowAmount(Integer nowAmount) {
		this.nowAmount = nowAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
}
