package com.syuesoft.sell.model;

import java.util.HashSet;
import java.util.Set;

import com.syuesoft.model.BaseBean;

/**
 * XsCarSellInfo entity. @author MyEclipse Persistence Tools
 */

public class XsCarSellInfo extends BaseBean implements java.io.Serializable {

	// Fields

	private Integer xsCarSelId;
	private Integer customId;
	private Integer xsCarId;
	private XsSellCarReserve xsSellCarReserve;
	private String xsCarSelData;
	private String sellCode;
	private Double xsCarSelTransactionMoney;
	private String xsCarGiveUp;
	private Integer xsCarStfId;
	private String xsCustomReceiptor;
	private Integer xsCarSelType;
	private Double applySum;
	private Double costSum;
	private String xsCarSelRemark;
	private Integer audit;
	private Integer outId;
	private Integer auditPerson;
	private String auditDate;//上报日期         
	private Integer isdbProject;
	private Integer isinsurance;
	private Integer iszhProject;
	private Integer isInvoice;//是否开票
	private String invoiceRemark;
	private String zhProjecRemark;
	private String dbProjectRemark;
	private Integer invoiceReckoning;
	private Integer sellafterStatus; //是否转售后
	private Integer enterpriseId; 	//企业编号

	
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getSellafterStatus() {
		return sellafterStatus;
	}
	public void setSellafterStatus(Integer sellafterStatus) {
		this.sellafterStatus = sellafterStatus;
	}
	public Integer getInvoiceReckoning() {
		return invoiceReckoning;
	}
	public void setInvoiceReckoning(Integer invoiceReckoning) {
		this.invoiceReckoning = invoiceReckoning;
	}
	public Integer getCustomId() {
		return customId;
	}
	public void setCustomId(Integer customId) {
		this.customId = customId;
	}
	public Integer getXsCarId() {
		return xsCarId;
	}
	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}

	public String getSellCode() {
		return sellCode;
	}
	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}

	private Set xsSellDbProjects = new HashSet(0);
	private Set xsSellInsurances = new HashSet(0);
	private Set xsCarCwInfos = new HashSet(0);
	private Set xsSellInvoices = new HashSet(0);
	private Set xsSellLoans = new HashSet(0);
	private Set xsSellZhProjects = new HashSet(0);

	public String getAuditDate() {
		return auditDate;
	}

	public Integer getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(Integer auditPerson) {
		this.auditPerson = auditPerson;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}


	public Integer getXsCarSelId() {
		return this.xsCarSelId;
	}

	public void setXsCarSelId(Integer xsCarSelId) {
		this.xsCarSelId = xsCarSelId;
	}

	public XsSellCarReserve getXsSellCarReserve() {
		return this.xsSellCarReserve;
	}

	public void setXsSellCarReserve(XsSellCarReserve xsSellCarReserve) {
		this.xsSellCarReserve = xsSellCarReserve;
	}

	public String getXsCarSelData() {
		return this.xsCarSelData;
	}

	public void setXsCarSelData(String xsCarSelData) {
		this.xsCarSelData = xsCarSelData;
	}

	public Double getXsCarSelTransactionMoney() {
		return this.xsCarSelTransactionMoney;
	}

	public void setXsCarSelTransactionMoney(Double xsCarSelTransactionMoney) {
		this.xsCarSelTransactionMoney = xsCarSelTransactionMoney;
	}

	public String getXsCarGiveUp() {
		return this.xsCarGiveUp;
	}

	public void setXsCarGiveUp(String xsCarGiveUp) {
		this.xsCarGiveUp = xsCarGiveUp;
	}

	public String getXsCustomReceiptor() {
		return this.xsCustomReceiptor;
	}

	public void setXsCustomReceiptor(String xsCustomReceiptor) {
		this.xsCustomReceiptor = xsCustomReceiptor;
	}

	public Integer getXsCarSelType() {
		return this.xsCarSelType;
	}

	public void setXsCarSelType(Integer xsCarSelType) {
		this.xsCarSelType = xsCarSelType;
	}

	public Double getApplySum() {
		return this.applySum;
	}

	public void setApplySum(Double applySum) {
		this.applySum = applySum;
	}

	public Double getCostSum() {
		return this.costSum;
	}

	public void setCostSum(Double costSum) {
		this.costSum = costSum;
	}

	public String getXsCarSelRemark() {
		return this.xsCarSelRemark;
	}

	public void setXsCarSelRemark(String xsCarSelRemark) {
		this.xsCarSelRemark = xsCarSelRemark;
	}

	public Integer getAudit() {
		return this.audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Integer getOutId() {
		return this.outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Set getXsSellDbProjects() {
		return this.xsSellDbProjects;
	}

	public void setXsSellDbProjects(Set xsSellDbProjects) {
		this.xsSellDbProjects = xsSellDbProjects;
	}

	public Set getXsSellInsurances() {
		return this.xsSellInsurances;
	}

	public void setXsSellInsurances(Set xsSellInsurances) {
		this.xsSellInsurances = xsSellInsurances;
	}

	public Set getXsCarCwInfos() {
		return this.xsCarCwInfos;
	}

	public void setXsCarCwInfos(Set xsCarCwInfos) {
		this.xsCarCwInfos = xsCarCwInfos;
	}

	public Set getXsSellInvoices() {
		return this.xsSellInvoices;
	}

	public void setXsSellInvoices(Set xsSellInvoices) {
		this.xsSellInvoices = xsSellInvoices;
	}

	public Set getXsSellLoans() {
		return this.xsSellLoans;
	}

	public void setXsSellLoans(Set xsSellLoans) {
		this.xsSellLoans = xsSellLoans;
	}

	public Set getXsSellZhProjects() {
		return this.xsSellZhProjects;
	}

	public void setXsSellZhProjects(Set xsSellZhProjects) {
		this.xsSellZhProjects = xsSellZhProjects;
	}

	public Integer getIsdbProject() {
		return isdbProject;
	}

	public void setIsdbProject(Integer isdbProject) {
		this.isdbProject = isdbProject;
	}

	public Integer getIsinsurance() {
		return isinsurance;
	}

	public void setIsinsurance(Integer isinsurance) {
		this.isinsurance = isinsurance;
	}

	public Integer getIszhProject() {
		return iszhProject;
	}

	public void setIszhProject(Integer iszhProject) {
		this.iszhProject = iszhProject;
	}

	public String getInvoiceRemark() {
		return invoiceRemark;
	}

	public void setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
	}

	public String getZhProjecRemark() {
		return zhProjecRemark;
	}

	public void setZhProjecRemark(String zhProjecRemark) {
		this.zhProjecRemark = zhProjecRemark;
	}

	public String getDbProjectRemark() {
		return dbProjectRemark;
	}

	public void setDbProjectRemark(String dbProjectRemark) {
		this.dbProjectRemark = dbProjectRemark;
	}

	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	public Integer getXsCarStfId() {
		return xsCarStfId;
	}

	public void setXsCarStfId(Integer xsCarStfId) {
		this.xsCarStfId = xsCarStfId;
	}
	
    
}