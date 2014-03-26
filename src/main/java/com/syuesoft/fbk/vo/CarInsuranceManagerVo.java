package com.syuesoft.fbk.vo;

import java.util.HashSet;
import java.util.Set;

import com.syuesoft.model.InsuranceType;

public class CarInsuranceManagerVo
{
    private String carInsuranceManageId;

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

    private String id;

    private String intype;// 险种

    private String incount;// 保险金额

    private String infeelv;// 费率

    private String infee;// 保险费

    private String intoubao;// 投保

    public CarInsuranceManagerVo()
    {
    }

    public CarInsuranceManagerVo(String carInsuranceManageId,
            String insuranceDate, String recordNumber, String auditSituation,
            String insuranceNumber, String invoiceNumber,
            String jqSingleNumber, String jqInvoiceNumber, String carBrand,
            String vinNumber, String theInsuredPerson,
            String nextInsuranceCompany, String insuranceCompany,
            String idCard, String insuredAddress, String carPrice, String tel,
            String engineNumber, String brandModel, String carColor,
            String businessDate, String jqDate, String businessInsurance,
            String businessPaid, String jqInsurance, String jqPaid,
            String travelTax, String travelTaxNumber, String premiums,
            String actuallyPaid, String registerDate, String customerPaid,
            String customerBacksection, String profit, String contact,
            String discountRate, String commissionBusiness, String giftItems,
            String billsNumber, String presentationValue,
            String credirCardTypes, String receiptDate, String businessUnits,
            String manager, String invoiceType, String receptor,
            String insuranceGroup, String memo, String versionNumber,
            String id, String intype, String incount, String infeelv,
            String infee, String intoubao)
    {
        super();
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
        this.id = id;
        this.intype = intype;
        this.incount = incount;
        this.infeelv = infeelv;
        this.infee = infee;
        this.intoubao = intoubao;
    }

    public String getCarInsuranceManageId()
    {
        return carInsuranceManageId;
    }

    public void setCarInsuranceManageId(String carInsuranceManageId)
    {
        this.carInsuranceManageId = carInsuranceManageId;
    }

    public String getInsuranceDate()
    {
        return insuranceDate;
    }

    public void setInsuranceDate(String insuranceDate)
    {
        this.insuranceDate = insuranceDate;
    }

    public String getRecordNumber()
    {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber)
    {
        this.recordNumber = recordNumber;
    }

    public String getAuditSituation()
    {
        return auditSituation;
    }

    public void setAuditSituation(String auditSituation)
    {
        this.auditSituation = auditSituation;
    }

    public String getInsuranceNumber()
    {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber)
    {
        this.insuranceNumber = insuranceNumber;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public String getJqSingleNumber()
    {
        return jqSingleNumber;
    }

    public void setJqSingleNumber(String jqSingleNumber)
    {
        this.jqSingleNumber = jqSingleNumber;
    }

    public String getJqInvoiceNumber()
    {
        return jqInvoiceNumber;
    }

    public void setJqInvoiceNumber(String jqInvoiceNumber)
    {
        this.jqInvoiceNumber = jqInvoiceNumber;
    }

    public String getCarBrand()
    {
        return carBrand;
    }

    public void setCarBrand(String carBrand)
    {
        this.carBrand = carBrand;
    }

    public String getVinNumber()
    {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber)
    {
        this.vinNumber = vinNumber;
    }

    public String getTheInsuredPerson()
    {
        return theInsuredPerson;
    }

    public void setTheInsuredPerson(String theInsuredPerson)
    {
        this.theInsuredPerson = theInsuredPerson;
    }

    public String getNextInsuranceCompany()
    {
        return nextInsuranceCompany;
    }

    public void setNextInsuranceCompany(String nextInsuranceCompany)
    {
        this.nextInsuranceCompany = nextInsuranceCompany;
    }

    public String getInsuranceCompany()
    {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany)
    {
        this.insuranceCompany = insuranceCompany;
    }

    public String getIdCard()
    {
        return idCard;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getInsuredAddress()
    {
        return insuredAddress;
    }

    public void setInsuredAddress(String insuredAddress)
    {
        this.insuredAddress = insuredAddress;
    }

    public String getCarPrice()
    {
        return carPrice;
    }

    public void setCarPrice(String carPrice)
    {
        this.carPrice = carPrice;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getEngineNumber()
    {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber)
    {
        this.engineNumber = engineNumber;
    }

    public String getBrandModel()
    {
        return brandModel;
    }

    public void setBrandModel(String brandModel)
    {
        this.brandModel = brandModel;
    }

    public String getCarColor()
    {
        return carColor;
    }

    public void setCarColor(String carColor)
    {
        this.carColor = carColor;
    }

    public String getBusinessDate()
    {
        return businessDate;
    }

    public void setBusinessDate(String businessDate)
    {
        this.businessDate = businessDate;
    }

    public String getJqDate()
    {
        return jqDate;
    }

    public void setJqDate(String jqDate)
    {
        this.jqDate = jqDate;
    }

    public String getBusinessInsurance()
    {
        return businessInsurance;
    }

    public void setBusinessInsurance(String businessInsurance)
    {
        this.businessInsurance = businessInsurance;
    }

    public String getBusinessPaid()
    {
        return businessPaid;
    }

    public void setBusinessPaid(String businessPaid)
    {
        this.businessPaid = businessPaid;
    }

    public String getJqInsurance()
    {
        return jqInsurance;
    }

    public void setJqInsurance(String jqInsurance)
    {
        this.jqInsurance = jqInsurance;
    }

    public String getJqPaid()
    {
        return jqPaid;
    }

    public void setJqPaid(String jqPaid)
    {
        this.jqPaid = jqPaid;
    }

    public String getTravelTax()
    {
        return travelTax;
    }

    public void setTravelTax(String travelTax)
    {
        this.travelTax = travelTax;
    }

    public String getTravelTaxNumber()
    {
        return travelTaxNumber;
    }

    public void setTravelTaxNumber(String travelTaxNumber)
    {
        this.travelTaxNumber = travelTaxNumber;
    }

    public String getPremiums()
    {
        return premiums;
    }

    public void setPremiums(String premiums)
    {
        this.premiums = premiums;
    }

    public String getActuallyPaid()
    {
        return actuallyPaid;
    }

    public void setActuallyPaid(String actuallyPaid)
    {
        this.actuallyPaid = actuallyPaid;
    }

    public String getRegisterDate()
    {
        return registerDate;
    }

    public void setRegisterDate(String registerDate)
    {
        this.registerDate = registerDate;
    }

    public String getCustomerPaid()
    {
        return customerPaid;
    }

    public void setCustomerPaid(String customerPaid)
    {
        this.customerPaid = customerPaid;
    }

    public String getCustomerBacksection()
    {
        return customerBacksection;
    }

    public void setCustomerBacksection(String customerBacksection)
    {
        this.customerBacksection = customerBacksection;
    }

    public String getProfit()
    {
        return profit;
    }

    public void setProfit(String profit)
    {
        this.profit = profit;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getDiscountRate()
    {
        return discountRate;
    }

    public void setDiscountRate(String discountRate)
    {
        this.discountRate = discountRate;
    }

    public String getCommissionBusiness()
    {
        return commissionBusiness;
    }

    public void setCommissionBusiness(String commissionBusiness)
    {
        this.commissionBusiness = commissionBusiness;
    }

    public String getGiftItems()
    {
        return giftItems;
    }

    public void setGiftItems(String giftItems)
    {
        this.giftItems = giftItems;
    }

    public String getBillsNumber()
    {
        return billsNumber;
    }

    public void setBillsNumber(String billsNumber)
    {
        this.billsNumber = billsNumber;
    }

    public String getPresentationValue()
    {
        return presentationValue;
    }

    public void setPresentationValue(String presentationValue)
    {
        this.presentationValue = presentationValue;
    }

    public String getCredirCardTypes()
    {
        return credirCardTypes;
    }

    public void setCredirCardTypes(String credirCardTypes)
    {
        this.credirCardTypes = credirCardTypes;
    }

    public String getReceiptDate()
    {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate)
    {
        this.receiptDate = receiptDate;
    }

    public String getBusinessUnits()
    {
        return businessUnits;
    }

    public void setBusinessUnits(String businessUnits)
    {
        this.businessUnits = businessUnits;
    }

    public String getManager()
    {
        return manager;
    }

    public void setManager(String manager)
    {
        this.manager = manager;
    }

    public String getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getReceptor()
    {
        return receptor;
    }

    public void setReceptor(String receptor)
    {
        this.receptor = receptor;
    }

    public String getInsuranceGroup()
    {
        return insuranceGroup;
    }

    public void setInsuranceGroup(String insuranceGroup)
    {
        this.insuranceGroup = insuranceGroup;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getVersionNumber()
    {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber)
    {
        this.versionNumber = versionNumber;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getIntype()
    {
        return intype;
    }

    public void setIntype(String intype)
    {
        this.intype = intype;
    }

    public String getIncount()
    {
        return incount;
    }

    public void setIncount(String incount)
    {
        this.incount = incount;
    }

    public String getInfeelv()
    {
        return infeelv;
    }

    public void setInfeelv(String infeelv)
    {
        this.infeelv = infeelv;
    }

    public String getInfee()
    {
        return infee;
    }

    public void setInfee(String infee)
    {
        this.infee = infee;
    }

    public String getIntoubao()
    {
        return intoubao;
    }

    public void setIntoubao(String intoubao)
    {
        this.intoubao = intoubao;
    }
}
