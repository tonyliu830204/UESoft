package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CarInsuranceManage entity. @author MyEclipse Persistence Tools
 */

public class CarInsuranceManage extends BaseBean {

	// Fields

	private Integer carInsuranceManageId;
	private String insuranceDate;
	private String recordNumber;
	private String auditSituation;
	private String insuranceNumber;
	private String invoiceNumber;
	private String jqSingleNumber;
	private String jqInvoiceNumber;
	private String carBrand;
	private String vinNumber;
	private String theInsuredPerson;
	private String nextInsuranceCompany;
	private String insuranceCompany;
	private String idCard;
	private String insuredAddress;
	private String carPrice;
	private String tel;
	private String engineNumber;
	private String brandModel;
	private String carColor;
	private String businessDate;
	private String jqDate;
	private String businessInsurance;
	private String businessPaid;
	private String jqInsurance;
	private String jqPaid;
	private String travelTax;
	private String travelTaxNumber;
	private String premiums;
	private String actuallyPaid;
	private String registerDate;
	private String customerPaid;
	private String customerBacksection;
	private String profit;
	private String contact;
	private String discountRate;
	private String commissionBusiness;
	private String giftItems;
	private String billsNumber;
	private String presentationValue;
	private String credirCardTypes;
	private String receiptDate;
	private String businessUnits;
	private String manager;
	private String invoiceType;
	private String receptor;
	private String insuranceGroup;
	private String memo;
	private String versionNumber;
	private Set centerCarinInties = new HashSet(0);
	// Constructors

	public Set getCenterCarinInties() {
		return centerCarinInties;
	}

	public void setCenterCarinInties(Set centerCarinInties) {
		this.centerCarinInties = centerCarinInties;
	}

	/** default constructor */
	public CarInsuranceManage() {
	}

	/** minimal constructor */
	public CarInsuranceManage(Integer carInsuranceManageId) {
		this.carInsuranceManageId = carInsuranceManageId;
	}

	/** full constructor */
	public CarInsuranceManage(Integer carInsuranceManageId, String insuranceDate,
			String recordNumber, String auditSituation, String insuranceNumber,
			String invoiceNumber, String jqSingleNumber,
			String jqInvoiceNumber, String carBrand, String vinNumber,
			String theInsuredPerson, String nextInsuranceCompany,
			String insuranceCompany, String idCard, String insuredAddress,
			String carPrice, String tel, String engineNumber,
			String brandModel, String carColor, String businessDate, String jqDate,
			String businessInsurance, String businessPaid, String jqInsurance,
			String jqPaid, String travelTax, String travelTaxNumber,
			String premiums, String actuallyPaid, String registerDate,
			String customerPaid, String customerBacksection, String profit,
			String contact, String discountRate, String commissionBusiness,
			String giftItems, String billsNumber, String presentationValue,
			String credirCardTypes, String receiptDate, String businessUnits,
			String manager, String invoiceType, String receptor,
			String insuranceGroup, String memo, String versionNumber,Set centerCarinInties) {
		this.carInsuranceManageId = carInsuranceManageId;
		this.insuranceDate = insuranceDate;
		this.recordNumber = recordNumber;
		this.auditSituation = auditSituation;
		this.insuranceNumber = insuranceNumber;
		this.invoiceNumber = invoiceNumber;
		this.jqSingleNumber = jqSingleNumber;
		this.jqInvoiceNumber = jqInvoiceNumber;
		this.carBrand = carBrand;
		this.vinNumber = vinNumber;
		this.theInsuredPerson = theInsuredPerson;
		this.nextInsuranceCompany = nextInsuranceCompany;
		this.insuranceCompany = insuranceCompany;
		this.idCard = idCard;
		this.insuredAddress = insuredAddress;
		this.carPrice = carPrice;
		this.tel = tel;
		this.engineNumber = engineNumber;
		this.brandModel = brandModel;
		this.carColor = carColor;
		this.businessDate = businessDate;
		this.jqDate = jqDate;
		this.businessInsurance = businessInsurance;
		this.businessPaid = businessPaid;
		this.jqInsurance = jqInsurance;
		this.jqPaid = jqPaid;
		this.travelTax = travelTax;
		this.travelTaxNumber = travelTaxNumber;
		this.premiums = premiums;
		this.actuallyPaid = actuallyPaid;
		this.registerDate = registerDate;
		this.customerPaid = customerPaid;
		this.customerBacksection = customerBacksection;
		this.profit = profit;
		this.contact = contact;
		this.discountRate = discountRate;
		this.commissionBusiness = commissionBusiness;
		this.giftItems = giftItems;
		this.billsNumber = billsNumber;
		this.presentationValue = presentationValue;
		this.credirCardTypes = credirCardTypes;
		this.receiptDate = receiptDate;
		this.businessUnits = businessUnits;
		this.manager = manager;
		this.invoiceType = invoiceType;
		this.receptor = receptor;
		this.insuranceGroup = insuranceGroup;
		this.memo = memo;
		this.versionNumber = versionNumber;
		this.centerCarinInties = centerCarinInties; 
	}

	// Property accessors

	public Integer getCarInsuranceManageId() {
		return this.carInsuranceManageId;
	}

	public void setCarInsuranceManageId(Integer carInsuranceManageId) {
		this.carInsuranceManageId = carInsuranceManageId;
	}

	public String getInsuranceDate() {
		return this.insuranceDate;
	}

	public void setInsuranceDate(String insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	public String getRecordNumber() {
		return this.recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getAuditSituation() {
		return this.auditSituation;
	}

	public void setAuditSituation(String auditSituation) {
		this.auditSituation = auditSituation;
	}

	public String getInsuranceNumber() {
		return this.insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getJqSingleNumber() {
		return this.jqSingleNumber;
	}

	public void setJqSingleNumber(String jqSingleNumber) {
		this.jqSingleNumber = jqSingleNumber;
	}

	public String getJqInvoiceNumber() {
		return this.jqInvoiceNumber;
	}

	public void setJqInvoiceNumber(String jqInvoiceNumber) {
		this.jqInvoiceNumber = jqInvoiceNumber;
	}

	public String getCarBrand() {
		return this.carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getVinNumber() {
		return this.vinNumber;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	public String getTheInsuredPerson() {
		return this.theInsuredPerson;
	}

	public void setTheInsuredPerson(String theInsuredPerson) {
		this.theInsuredPerson = theInsuredPerson;
	}

	public String getNextInsuranceCompany() {
		return this.nextInsuranceCompany;
	}

	public void setNextInsuranceCompany(String nextInsuranceCompany) {
		this.nextInsuranceCompany = nextInsuranceCompany;
	}

	public String getInsuranceCompany() {
		return this.insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getInsuredAddress() {
		return this.insuredAddress;
	}

	public void setInsuredAddress(String insuredAddress) {
		this.insuredAddress = insuredAddress;
	}

	public String getCarPrice() {
		return this.carPrice;
	}

	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEngineNumber() {
		return this.engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getBrandModel() {
		return this.brandModel;
	}

	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}

	public String getCarColor() {
		return this.carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getBusinessDate() {
		return this.businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getJqDate() {
		return this.jqDate;
	}

	public void setJqDate(String jqDate) {
		this.jqDate = jqDate;
	}

	public String getBusinessInsurance() {
		return this.businessInsurance;
	}

	public void setBusinessInsurance(String businessInsurance) {
		this.businessInsurance = businessInsurance;
	}

	public String getBusinessPaid() {
		return this.businessPaid;
	}

	public void setBusinessPaid(String businessPaid) {
		this.businessPaid = businessPaid;
	}

	public String getJqInsurance() {
		return this.jqInsurance;
	}

	public void setJqInsurance(String jqInsurance) {
		this.jqInsurance = jqInsurance;
	}

	public String getJqPaid() {
		return this.jqPaid;
	}

	public void setJqPaid(String jqPaid) {
		this.jqPaid = jqPaid;
	}

	public String getTravelTax() {
		return this.travelTax;
	}

	public void setTravelTax(String travelTax) {
		this.travelTax = travelTax;
	}

	public String getTravelTaxNumber() {
		return this.travelTaxNumber;
	}

	public void setTravelTaxNumber(String travelTaxNumber) {
		this.travelTaxNumber = travelTaxNumber;
	}

	public String getPremiums() {
		return this.premiums;
	}

	public void setPremiums(String premiums) {
		this.premiums = premiums;
	}

	public String getActuallyPaid() {
		return this.actuallyPaid;
	}

	public void setActuallyPaid(String actuallyPaid) {
		this.actuallyPaid = actuallyPaid;
	}

	public String getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getCustomerPaid() {
		return this.customerPaid;
	}

	public void setCustomerPaid(String customerPaid) {
		this.customerPaid = customerPaid;
	}

	public String getCustomerBacksection() {
		return this.customerBacksection;
	}

	public void setCustomerBacksection(String customerBacksection) {
		this.customerBacksection = customerBacksection;
	}

	public String getProfit() {
		return this.profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public String getCommissionBusiness() {
		return this.commissionBusiness;
	}

	public void setCommissionBusiness(String commissionBusiness) {
		this.commissionBusiness = commissionBusiness;
	}

	public String getGiftItems() {
		return this.giftItems;
	}

	public void setGiftItems(String giftItems) {
		this.giftItems = giftItems;
	}

	public String getBillsNumber() {
		return this.billsNumber;
	}

	public void setBillsNumber(String billsNumber) {
		this.billsNumber = billsNumber;
	}

	public String getPresentationValue() {
		return this.presentationValue;
	}

	public void setPresentationValue(String presentationValue) {
		this.presentationValue = presentationValue;
	}

	public String getCredirCardTypes() {
		return this.credirCardTypes;
	}

	public void setCredirCardTypes(String credirCardTypes) {
		this.credirCardTypes = credirCardTypes;
	}

	public String getReceiptDate() {
		return this.receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getBusinessUnits() {
		return this.businessUnits;
	}

	public void setBusinessUnits(String businessUnits) {
		this.businessUnits = businessUnits;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getReceptor() {
		return this.receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getInsuranceGroup() {
		return this.insuranceGroup;
	}

	public void setInsuranceGroup(String insuranceGroup) {
		this.insuranceGroup = insuranceGroup;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getVersionNumber() {
		return this.versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

}