package com.syuesoft.sell.noteManage.vo;

import com.syuesoft.model.BaseBean;

public class SellSmsVo extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 短信发送
	private String sms_id;
	private Integer sft_id;
	private String sft_name;
	private String sms_date;// 即时
	private String d_date;// 定时
	private String sms;
	private Integer sms_way;// 短信发送类型
	private Integer sms_type;
	private Integer sms_state;
	// 短信接收
	private Integer detail_id;
	private Integer customId;
	private String receive_date;
	private String phonecode;
	private Integer receive_state;
	private String inserted;
	private String test_Number; // 测试号码（发给自己的）
	private int rows;
	private int page;
	private String custom_Name;
	private String carLicensePlate;
	private String carVinNumber;
	private String telephone;
	private Integer enterprise_id;
	private String sms_date2;// 
	
	public String getSms_date2() {
		return sms_date2;
	}

	public void setSms_date2(String smsDate2) {
		sms_date2 = smsDate2;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSft_name() {
		return sft_name;
	}

	public void setSft_name(String sftName) {
		sft_name = sftName;
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

	public String getTest_Number() {
		return test_Number;
	}

	public void setTest_Number(String testNumber) {
		test_Number = testNumber;
	}

	public String getInserted() {
		return inserted;
	}

	public void setInserted(String inserted) {
		this.inserted = inserted;
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

	public Integer getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Integer detailId) {
		detail_id = detailId;
	}

	public Integer getCustomId() {
		return customId;
	}

	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	public String getCustom_Name() {
		return custom_Name;
	}

	public void setCustom_Name(String customName) {
		custom_Name = customName;
	}

	public String getCarLicensePlate() {
		return carLicensePlate;
	}

	public void setCarLicensePlate(String carLicensePlate) {
		this.carLicensePlate = carLicensePlate;
	}

	public String getCarVinNumber() {
		return carVinNumber;
	}

	public void setCarVinNumber(String carVinNumber) {
		this.carVinNumber = carVinNumber;
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

	public String getD_date() {
		return d_date;
	}

	public void setD_date(String dDate) {
		d_date = dDate;
	}

}
