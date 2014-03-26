package com.syuesoft.sell.model;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellCertificate extends BaseBean implements Serializable{
	private Integer certificateId;		//编号
	private  Integer xsCarId;			//车辆档案信息编号
	private Integer receiptId;			//承兑汇票编号
	private String xsCarCertificate;	//合格证
	private Integer xsCarCertificateState;	//合格证状态
	private Date redemptionDate;		//赎回日期
	private String certificatePerson;	//领证人
	private Date certificateDate;		//领证日期
	private String remark;				//备注
	private XsChilddictionary xsSellCertificateState;//合格证状态
	
	public XsChilddictionary getXsSellCertificateState() {
		return xsSellCertificateState;
	}
	public void setXsSellCertificateState(XsChilddictionary xsSellCertificateState) {
		this.xsSellCertificateState = xsSellCertificateState;
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
	
}
