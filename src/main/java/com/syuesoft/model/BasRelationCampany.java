package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasRelationCampany entity. @author MyEclipse Persistence Tools
 */

public class BasRelationCampany extends BaseBean{

	// Fields

	private Short relcampId;
	private String relcampName;
	private Short relcampFlg;
	private String relcampAddr;
	private String relcampZipCode;
	private String relcampContact;
	private String relcampTel1;
	private String relcampTel2;
	private String relcampFax;
	private String relcampBank;
	private String relcampAccount;
	private String relcampTaxNum;
	private String relcampRemark1;
	private String relcampRemark2;
	private Date createDatetime;
	private String relcampJm;
	private String invoicingAddress;
	private String invoicingTel;
	private Short attrId;
	private Set stPurOrders = new HashSet(0);
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public BasRelationCampany() {
	}

	/** full constructor */
	public BasRelationCampany(String relcampName, Short relcampFlg, String relcampAddr, String relcampZipCode, String relcampContact, String relcampTel1, String relcampTel2, String relcampFax,
			String relcampBank, String relcampAccount, String relcampTaxNum, String relcampRemark1, String relcampRemark2, Date createDatetime, String relcampJm, String invoicingAddress,
			String invoicingTel, Short attrId, Set stPurOrders) {
		this.relcampName = relcampName;
		this.relcampFlg = relcampFlg;
		this.relcampAddr = relcampAddr;
		this.relcampZipCode = relcampZipCode;
		this.relcampContact = relcampContact;
		this.relcampTel1 = relcampTel1;
		this.relcampTel2 = relcampTel2;
		this.relcampFax = relcampFax;
		this.relcampBank = relcampBank;
		this.relcampAccount = relcampAccount;
		this.relcampTaxNum = relcampTaxNum;
		this.relcampRemark1 = relcampRemark1;
		this.relcampRemark2 = relcampRemark2;
		this.createDatetime = createDatetime;
		this.relcampJm = relcampJm;
		this.invoicingAddress = invoicingAddress;
		this.invoicingTel = invoicingTel;
		this.attrId = attrId;
		this.stPurOrders = stPurOrders;
	}

	// Property accessors

	public Short getRelcampId() {
		return this.relcampId;
	}

	public void setRelcampId(Short relcampId) {
		this.relcampId = relcampId;
	}

	public String getRelcampName() {
		return this.relcampName;
	}

	public void setRelcampName(String relcampName) {
		this.relcampName = relcampName;
	}

	public Short getRelcampFlg() {
		return this.relcampFlg;
	}

	public void setRelcampFlg(Short relcampFlg) {
		this.relcampFlg = relcampFlg;
	}

	public String getRelcampAddr() {
		return this.relcampAddr;
	}

	public void setRelcampAddr(String relcampAddr) {
		this.relcampAddr = relcampAddr;
	}

	public String getRelcampZipCode() {
		return this.relcampZipCode;
	}

	public void setRelcampZipCode(String relcampZipCode) {
		this.relcampZipCode = relcampZipCode;
	}

	public String getRelcampContact() {
		return this.relcampContact;
	}

	public void setRelcampContact(String relcampContact) {
		this.relcampContact = relcampContact;
	}

	public String getRelcampTel1() {
		return this.relcampTel1;
	}

	public void setRelcampTel1(String relcampTel1) {
		this.relcampTel1 = relcampTel1;
	}

	public String getRelcampTel2() {
		return this.relcampTel2;
	}

	public void setRelcampTel2(String relcampTel2) {
		this.relcampTel2 = relcampTel2;
	}

	public String getRelcampFax() {
		return this.relcampFax;
	}

	public void setRelcampFax(String relcampFax) {
		this.relcampFax = relcampFax;
	}

	public String getRelcampBank() {
		return this.relcampBank;
	}

	public void setRelcampBank(String relcampBank) {
		this.relcampBank = relcampBank;
	}

	public String getRelcampAccount() {
		return this.relcampAccount;
	}

	public void setRelcampAccount(String relcampAccount) {
		this.relcampAccount = relcampAccount;
	}

	public String getRelcampTaxNum() {
		return this.relcampTaxNum;
	}

	public void setRelcampTaxNum(String relcampTaxNum) {
		this.relcampTaxNum = relcampTaxNum;
	}

	public String getRelcampRemark1() {
		return this.relcampRemark1;
	}

	public void setRelcampRemark1(String relcampRemark1) {
		this.relcampRemark1 = relcampRemark1;
	}

	public String getRelcampRemark2() {
		return this.relcampRemark2;
	}

	public void setRelcampRemark2(String relcampRemark2) {
		this.relcampRemark2 = relcampRemark2;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getRelcampJm() {
		return this.relcampJm;
	}

	public void setRelcampJm(String relcampJm) {
		this.relcampJm = relcampJm;
	}

	public String getInvoicingAddress() {
		return this.invoicingAddress;
	}

	public void setInvoicingAddress(String invoicingAddress) {
		this.invoicingAddress = invoicingAddress;
	}

	public String getInvoicingTel() {
		return this.invoicingTel;
	}

	public void setInvoicingTel(String invoicingTel) {
		this.invoicingTel = invoicingTel;
	}

	public Short getAttrId() {
		return this.attrId;
	}

	public void setAttrId(Short attrId) {
		this.attrId = attrId;
	}

	public Set getStPurOrders() {
		return this.stPurOrders;
	}

	public void setStPurOrders(Set stPurOrders) {
		this.stPurOrders = stPurOrders;
	}

}