package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FrtCustom entity. @author MyEclipse Persistence Tools
 */

public class FrtCustom extends BaseBean {

	// Fields

	private String customId;
	private BasCustomNature basCustomNature;
	private BasCustomType basCustomType;
	private BasCustomGroup basCustomGroup;
	private BasCustomArea basCustomArea;
	private String customName;
	private String customJm;
	private Date createDate;
	private String customAddr;
	private String customTel1;
	private String customTel2;
	private String customEmail;
	private String bankOfDeposit;
	private String bankAccount;
	private String taxId;
	private String invoicingAddress;
	private String invoicingTel;
	private String fax;
	private String linkman;
	private String linkmanTel;
	private String customRemark1;
	private String customRemark2;
	private String customIden;
	private Date customBirthday;
	private String customLvl;
	private String customSex;
	private String customLisence;
	private Boolean customFlag;//启用与禁用
	private Set frtCars = new HashSet(0);
	 private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public FrtCustom() {
	}

	/** minimal constructor */
	public FrtCustom(String customId) {
		this.customId = customId;
	}

	/** full constructor */
	public FrtCustom(String customId, BasCustomNature basCustomNature, BasCustomType basCustomType, BasCustomGroup basCustomGroup, BasCustomArea basCustomArea, String customName, String customJm,
			Date createDate, String customAddr, String customTel1, String customTel2, String customEmail, String bankOfDeposit, String bankAccount, String taxId, String invoicingAddress,
			String invoicingTel, String fax, String linkman, String linkmanTel, String customRemark1, String customRemark2, String customIden, Date customBirthday, String customLvl,
			String customSex, String customLisence, Set frtCars) {
		this.customId = customId;
		this.basCustomNature = basCustomNature;
		this.basCustomType = basCustomType;
		this.basCustomGroup = basCustomGroup;
		this.basCustomArea = basCustomArea;
		this.customName = customName;
		this.customJm = customJm;
		this.createDate = createDate;
		this.customAddr = customAddr;
		this.customTel1 = customTel1;
		this.customTel2 = customTel2;
		this.customEmail = customEmail;
		this.bankOfDeposit = bankOfDeposit;
		this.bankAccount = bankAccount;
		this.taxId = taxId;
		this.invoicingAddress = invoicingAddress;
		this.invoicingTel = invoicingTel;
		this.fax = fax;
		this.linkman = linkman;
		this.linkmanTel = linkmanTel;
		this.customRemark1 = customRemark1;
		this.customRemark2 = customRemark2;
		this.customIden = customIden;
		this.customBirthday = customBirthday;
		this.customLvl = customLvl;
		this.customSex = customSex;
		this.customLisence = customLisence;
		this.frtCars = frtCars;
	}

	// Property accessors

	public String getCustomId() {
		return this.customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public BasCustomNature getBasCustomNature() {
		return this.basCustomNature;
	}

	public void setBasCustomNature(BasCustomNature basCustomNature) {
		this.basCustomNature = basCustomNature;
	}

	public BasCustomType getBasCustomType() {
		return this.basCustomType;
	}

	public void setBasCustomType(BasCustomType basCustomType) {
		this.basCustomType = basCustomType;
	}

	public BasCustomGroup getBasCustomGroup() {
		return this.basCustomGroup;
	}

	public void setBasCustomGroup(BasCustomGroup basCustomGroup) {
		this.basCustomGroup = basCustomGroup;
	}

	public BasCustomArea getBasCustomArea() {
		return this.basCustomArea;
	}

	public void setBasCustomArea(BasCustomArea basCustomArea) {
		this.basCustomArea = basCustomArea;
	}

	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomJm() {
		return this.customJm;
	}

	public void setCustomJm(String customJm) {
		this.customJm = customJm;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCustomAddr() {
		return this.customAddr;
	}

	public void setCustomAddr(String customAddr) {
		this.customAddr = customAddr;
	}

	public String getCustomTel1() {
		return this.customTel1;
	}

	public void setCustomTel1(String customTel1) {
		this.customTel1 = customTel1;
	}

	public String getCustomTel2() {
		return this.customTel2;
	}

	public void setCustomTel2(String customTel2) {
		this.customTel2 = customTel2;
	}

	public String getCustomEmail() {
		return this.customEmail;
	}

	public void setCustomEmail(String customEmail) {
		this.customEmail = customEmail;
	}

	public String getBankOfDeposit() {
		return this.bankOfDeposit;
	}

	public void setBankOfDeposit(String bankOfDeposit) {
		this.bankOfDeposit = bankOfDeposit;
	}

	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
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

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmanTel() {
		return this.linkmanTel;
	}

	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}

	public String getCustomRemark1() {
		return this.customRemark1;
	}

	public void setCustomRemark1(String customRemark1) {
		this.customRemark1 = customRemark1;
	}

	public String getCustomRemark2() {
		return this.customRemark2;
	}

	public void setCustomRemark2(String customRemark2) {
		this.customRemark2 = customRemark2;
	}

	public String getCustomIden() {
		return this.customIden;
	}

	public void setCustomIden(String customIden) {
		this.customIden = customIden;
	}

	public Date getCustomBirthday() {
		return this.customBirthday;
	}

	public void setCustomBirthday(Date customBirthday) {
		this.customBirthday = customBirthday;
	}

	public String getCustomLvl() {
		return this.customLvl;
	}

	public void setCustomLvl(String customLvl) {
		this.customLvl = customLvl;
	}

	public String getCustomSex() {
		return this.customSex;
	}

	public void setCustomSex(String customSex) {
		this.customSex = customSex;
	}

	public String getCustomLisence() {
		return this.customLisence;
	}

	public void setCustomLisence(String customLisence) {
		this.customLisence = customLisence;
	}

	public Set getFrtCars() {
		return this.frtCars;
	}

	public void setFrtCars(Set frtCars) {
		this.frtCars = frtCars;
	}

	public Boolean getCustomFlag() {
		return customFlag;
	}

	public void setCustomFlag(Boolean customFlag) {
		this.customFlag = customFlag;
	}

}