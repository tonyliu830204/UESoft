package com.syuesoft.sell.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellReceiveSms extends BaseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer detail_id;
	private String sms_id;
	private Integer custom_id;
	private String receive_date;
	private String phonecode;
	private Integer receive_state;
	private String  custom_name;
	
	public String getCustom_name() {
		return custom_name;
	}

	public void setCustom_name(String customName) {
		custom_name = customName;
	}

	public Integer getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Integer detailId) {
		detail_id = detailId;
	}

	public String getSms_id() {
		return sms_id;
	}

	public void setSms_id(String smsId) {
		sms_id = smsId;
	}

	public Integer getCustom_id() {
		return custom_id;
	}

	public void setCustom_id(Integer customId) {
		custom_id = customId;
	}

	public String getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(String receiveDate) {
		receive_date = receiveDate;
	}

	public String getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}

	public Integer getReceive_state() {
		return receive_state;
	}

	public void setReceive_state(Integer receiveState) {
		receive_state = receiveState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
