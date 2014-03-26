package com.syuesoft.sell.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * XsSellCollections entity. @author MyEclipse Persistence Tools
 */

public class XsSellCollections implements java.io.Serializable {

	// Fields

	private Integer collectionsId;
	private String accountId;
	private Integer xsCarId;
	private Integer customId;
	private String accountCode;
	private Double accountReceivables;
	private Double accountCumulative;
	private Double accountAmount;
	private Double accountArrears;
	private Integer accountUnfinished;
	private Double accountArrearsAge;
	private Integer accountType;
	private String accountDate;
	private Integer accountPerson;
	private Integer accountClassify;

	private Set xsSellReceivablesDetails = new HashSet(0);
	private Set xsSellCollectionsDetails = new HashSet(0);
	private XsChilddictionary xsSellCollectionClassify;
	private XsChilddictionary xsSellCollectionType;
	private XsChilddictionary xsSellCollectionUnfinished;

	// Constructors

	public XsChilddictionary getXsSellCollectionClassify() {
		return xsSellCollectionClassify;
	}

	public void setXsSellCollectionClassify(
			XsChilddictionary xsSellCollectionClassify) {
		this.xsSellCollectionClassify = xsSellCollectionClassify;
	}

	public XsChilddictionary getXsSellCollectionType() {
		return xsSellCollectionType;
	}

	public void setXsSellCollectionType(XsChilddictionary xsSellCollectionType) {
		this.xsSellCollectionType = xsSellCollectionType;
	}

	public XsChilddictionary getXsSellCollectionUnfinished() {
		return xsSellCollectionUnfinished;
	}

	public void setXsSellCollectionUnfinished(
			XsChilddictionary xsSellCollectionUnfinished) {
		this.xsSellCollectionUnfinished = xsSellCollectionUnfinished;
	}

	/** default constructor */
	public XsSellCollections() {
	}

	// Property accessors

	public Integer getCollectionsId() {
		return this.collectionsId;
	}

	public Integer getAccountClassify() {
		return accountClassify;
	}

	public void setAccountClassify(Integer accountClassify) {
		this.accountClassify = accountClassify;
	}

	public Set getXsSellCollectionsDetails() {
		return xsSellCollectionsDetails;
	}

	public void setXsSellCollectionsDetails(Set xsSellCollectionsDetails) {
		this.xsSellCollectionsDetails = xsSellCollectionsDetails;
	}

	public Integer getXsCarId() {
		return xsCarId;
	}

	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}

	public Integer getCustomId() {
		return customId;
	}

	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	public void setCollectionsId(Integer collectionsId) {
		this.collectionsId = collectionsId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public Double getAccountReceivables() {
		return this.accountReceivables;
	}

	public void setAccountReceivables(Double accountReceivables) {
		this.accountReceivables = accountReceivables;
	}

	public Double getAccountCumulative() {
		return this.accountCumulative;
	}

	public void setAccountCumulative(Double accountCumulative) {
		this.accountCumulative = accountCumulative;
	}

	public Double getAccountAmount() {
		return this.accountAmount;
	}

	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public Double getAccountArrears() {
		return this.accountArrears;
	}

	public void setAccountArrears(Double accountArrears) {
		this.accountArrears = accountArrears;
	}

	public Integer getAccountUnfinished() {
		return this.accountUnfinished;
	}

	public void setAccountUnfinished(Integer accountUnfinished) {
		this.accountUnfinished = accountUnfinished;
	}

	public Double getAccountArrearsAge() {
		return this.accountArrearsAge;
	}

	public void setAccountArrearsAge(Double accountArrearsAge) {
		this.accountArrearsAge = accountArrearsAge;
	}

	public Integer getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public Integer getAccountPerson() {
		return this.accountPerson;
	}

	public void setAccountPerson(Integer accountPerson) {
		this.accountPerson = accountPerson;
	}

	public Set getXsSellReceivablesDetails() {
		return this.xsSellReceivablesDetails;
	}

	public void setXsSellReceivablesDetails(Set xsSellReceivablesDetails) {
		this.xsSellReceivablesDetails = xsSellReceivablesDetails;
	}

}