package com.syuesoft.sell.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellSms extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sms_id;
	private Integer sft_id;
	private String sms_date;
	private String sms;
	private Integer sms_way;
	private Integer sms_type;
	private Integer sms_state;
	private Integer enterprise_id;

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getSms_id() {
		return sms_id;
	}

	public void setSms_id(String smsId) {
		sms_id = smsId;
	}

	public Integer getSft_id() {
		return sft_id;
	}

	public void setSft_id(Integer sftId) {
		sft_id = sftId;
	}

	public String getSms_date() {
		return sms_date;
	}

	public void setSms_date(String smsDate) {
		sms_date = smsDate;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public Integer getSms_way() {
		return sms_way;
	}

	public void setSms_way(Integer smsWay) {
		sms_way = smsWay;
	}

	public Integer getSms_type() {
		return sms_type;
	}

	public void setSms_type(Integer smsType) {
		sms_type = smsType;
	}

	public Integer getSms_state() {
		return sms_state;
	}

	public void setSms_state(Integer smsState) {
		sms_state = smsState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
