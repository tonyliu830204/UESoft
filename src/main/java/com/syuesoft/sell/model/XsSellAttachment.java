package com.syuesoft.sell.model;

import java.io.Serializable;

import com.syuesoft.model.BaseBean;

public class XsSellAttachment extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer  attachment_no ;
	private Integer  xs_car_id;
	private Integer count1;
	private Integer count2;
	private Integer operator_persion;

	private String operator_date;
	private String send_persion;
	private Integer operator_type ;
	private String remark;
	private XsChilddictionary xsChilddictionary;

	public XsChilddictionary getXsChilddictionary() {
		return xsChilddictionary;
	}

	public void setXsChilddictionary(XsChilddictionary xsChilddictionary) {
		this.xsChilddictionary = xsChilddictionary;
	}
	public Integer getAttachment_no() {
		return attachment_no;
	}
	public void setAttachment_no(Integer attachmentNo) {
		attachment_no = attachmentNo;
	}
	public Integer getXs_car_id() {
		return xs_car_id;
	}
	public void setXs_car_id(Integer xsCarId) {
		xs_car_id = xsCarId;
	}
	public Integer getCount1() {
		return count1;
	}
	public void setCount1(Integer count1) {
		this.count1 = count1;
	}
	public Integer getCount2() {
		return count2;
	}
	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	public String getSend_persion() {
		return send_persion;
	}
	public void setSend_persion(String sendPersion) {
		send_persion = sendPersion;
	}

	public Integer getOperator_type() {
		return operator_type;
	}
	public void setOperator_type(Integer operatorType) {
		operator_type = operatorType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOperator_persion() {
		return operator_persion;
	}
	public void setOperator_persion(Integer operatorPersion) {
		operator_persion = operatorPersion;
	}
	public String getOperator_date() {
		return operator_date;
	}
	public void setOperator_date(String operatorDate) {
		operator_date = operatorDate;
	}
	
}
