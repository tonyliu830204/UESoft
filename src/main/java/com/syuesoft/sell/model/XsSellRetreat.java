package com.syuesoft.sell.model;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellRetreat extends BaseBean implements Serializable{
	/**
	 * 
	 */
	private Integer retreatId;
	private XsSellInstorehouseDel details;
	private  String retreatCode;
	private Date retreatDate;
	private  String context;
	private Date xsCarPdsData;
	private Integer xsCarPdsPerson;
	private String xsCarPdsResult2;
	private Integer examine;
	private  Integer inInstorehouse;
	private Integer outInstorehouse;
	private Integer enterprise_id ;
 	
	public Integer getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}
	private Integer person;
	private Integer instorehouseType;
	private Integer xsSupplierId;
	
	public Integer getRetreatId() {
		return retreatId;
	}
	public void setRetreatId(Integer retreatId) {
		this.retreatId = retreatId;
	}
	
	public XsSellInstorehouseDel getDetails() {
		return details;
	}
	public void setDetails(XsSellInstorehouseDel details) {
		this.details = details;
	}
	public String getRetreatCode() {
		return retreatCode;
	}
	public void setRetreatCode(String retreatCode) {
		this.retreatCode = retreatCode;
	}
	public Date getRetreatDate() {
		return retreatDate;
	}
	public void setRetreatDate(Date retreatDate) {
		this.retreatDate = retreatDate;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getXsCarPdsData() {
		return xsCarPdsData;
	}
	public void setXsCarPdsData(Date xsCarPdsData) {
		this.xsCarPdsData = xsCarPdsData;
	}
	public Integer getXsCarPdsPerson() {
		return xsCarPdsPerson;
	}
	public void setXsCarPdsPerson(Integer xsCarPdsPerson) {
		this.xsCarPdsPerson = xsCarPdsPerson;
	}
	public String getXsCarPdsResult2() {
		return xsCarPdsResult2;
	}
	public void setXsCarPdsResult2(String xsCarPdsResult2) {
		this.xsCarPdsResult2 = xsCarPdsResult2;
	}
	public Integer getExamine() {
		return examine;
	}
	public void setExamine(Integer examine) {
		this.examine = examine;
	}
	public Integer getInInstorehouse() {
		return inInstorehouse;
	}
	public void setInInstorehouse(Integer inInstorehouse) {
		this.inInstorehouse = inInstorehouse;
	}
	public Integer getOutInstorehouse() {
		return outInstorehouse;
	}
	public void setOutInstorehouse(Integer outInstorehouse) {
		this.outInstorehouse = outInstorehouse;
	}
	public Integer getPerson() {
		return person;
	}
	public void setPerson(Integer person) {
		this.person = person;
	}
	public Integer getInstorehouseType() {
		return instorehouseType;
	}
	public void setInstorehouseType(Integer instorehouseType) {
		this.instorehouseType = instorehouseType;
	}
	public Integer getXsSupplierId() {
		return xsSupplierId;
	}
	public void setXsSupplierId(Integer xsSupplierId) {
		this.xsSupplierId = xsSupplierId;
	}

}
