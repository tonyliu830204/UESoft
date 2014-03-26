package com.syuesoft.sell.allocateManage.vo;

import java.io.Serializable;

import com.syuesoft.model.BaseBean;

public class SellCustomAnalyseVo extends BaseBean implements Serializable {
	private Integer xsCarSelId;
	private Integer customId;
	private Integer xsCarId;
	private String xsCarSelData;
	private String xsCarSelData2;
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
	private String auditDate;// 上报日期
	private Integer isdbProject;
	private Integer isinsurance;
	private Integer iszhProject;
	private Integer isInvoice;// 是否开票
	private String invoiceRemark;
	private String zhProjecRemark;
	private String dbProjectRemark;
	private Integer invoiceReckoning;
	private Integer sellafterStatus; // 是否转售后

	private Integer carId;
	private Integer carBrand;
	private String carBrandName;// 车辆品牌
	private Integer carColor;
	private String carColorName;// 车辆颜色名称
	private Integer carModel;
	private String carModelName;// 车类型
	private String carCode;// 车辆编号
	private String carLicenseName;// 厂牌名称
	private String carVinNumber;
	private Double modelCostPrice;// 成本价
	private Double totalMoney;// 合计金额(入库表)

	private String inserted;
	private String deleted;
	private String updated;
	private String distributorName;// 分销商
	private Double modelSalesPrice;// 销售价
	private Double carInstorageAge;// 库龄
	private Double vehicleCost;
	private Integer carType;
	private String retreat_date;
	private String retreat_date2;

	private String sort;
	private String order;
	private int rows;
	private int page;

	private Integer cuId;
	private String cuName;
	private Integer num;
	private Double percent;
	private String ctype;
	private String selectedField;
	private String stfName;
	private String fdJ;
	private String ocn;
	private Double decorate_amount;// 装潢成本
	private Double decorate_sell;// 装潢销售
	private Double cost_price;// 代办成本
	private Double db_project_cost;// 代办销售
	private Double custom_cost;// 客户付保；
	private Double prime_cost;// 保险成本
	private Double xs_model_salesLimitPrice;// 销售限价
	private Double invoice_parce;// 开票金额
	private Double zhml;
	private Double dbml;
	private Double bxml;
	private Double zml;
	private Double xjml;
	public String invoice_code;
	public String invoice_date;
	private Integer enterprise_id;
	public String times;
	public Boolean flag;
	private String upDate;
	private String upDate2;
	
	
	public String getUpDate() {
		return upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public String getUpDate2() {
		return upDate2;
	}

	public void setUpDate2(String upDate2) {
		this.upDate2 = upDate2;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public Double getDecorate_amount() {
		return decorate_amount;
	}

	public void setDecorate_amount(Double decorateAmount) {
		decorate_amount = decorateAmount;
	}

	public Double getDecorate_sell() {
		return decorate_sell;
	}

	public void setDecorate_sell(Double decorateSell) {
		decorate_sell = decorateSell;
	}

	public Double getCost_price() {
		return cost_price;
	}

	public void setCost_price(Double costPrice) {
		cost_price = costPrice;
	}

	public Double getDb_project_cost() {
		return db_project_cost;
	}

	public void setDb_project_cost(Double dbProjectCost) {
		db_project_cost = dbProjectCost;
	}

	public Double getCustom_cost() {
		return custom_cost;
	}

	public void setCustom_cost(Double customCost) {
		custom_cost = customCost;
	}

	public Double getPrime_cost() {
		return prime_cost;
	}

	public void setPrime_cost(Double primeCost) {
		prime_cost = primeCost;
	}

	public Double getXs_model_salesLimitPrice() {
		return xs_model_salesLimitPrice;
	}

	public void setXs_model_salesLimitPrice(Double xsModelSalesLimitPrice) {
		xs_model_salesLimitPrice = xsModelSalesLimitPrice;
	}

	public Double getInvoice_parce() {
		return invoice_parce;
	}

	public void setInvoice_parce(Double invoiceParce) {
		invoice_parce = invoiceParce;
	}

	public Double getZhml() {
		return zhml;
	}

	public void setZhml(Double zhml) {
		this.zhml = zhml;
	}

	public Double getDbml() {
		return dbml;
	}

	public void setDbml(Double dbml) {
		this.dbml = dbml;
	}

	public Double getBxml() {
		return bxml;
	}

	public void setBxml(Double bxml) {
		this.bxml = bxml;
	}

	public Double getZml() {
		return zml;
	}

	public void setZml(Double zml) {
		this.zml = zml;
	}

	public Double getXjml() {
		return xjml;
	}

	public void setXjml(Double xjml) {
		this.xjml = xjml;
	}

	public String getInvoice_code() {
		return invoice_code;
	}

	public void setInvoice_code(String invoiceCode) {
		invoice_code = invoiceCode;
	}

	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoiceDate) {
		invoice_date = invoiceDate;
	}

	public String getFdJ() {
		return fdJ;
	}

	public void setFdJ(String fdJ) {
		this.fdJ = fdJ;
	}

	public String getOcn() {
		return ocn;
	}

	public void setOcn(String ocn) {
		this.ocn = ocn;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public String getXsCarSelData2() {
		return xsCarSelData2;
	}

	public void setXsCarSelData2(String xsCarSelData2) {
		this.xsCarSelData2 = xsCarSelData2;
	}

	public String getRetreat_date2() {
		return retreat_date2;
	}

	public void setRetreat_date2(String retreatDate2) {
		retreat_date2 = retreatDate2;
	}

	public String getRetreat_date() {
		return retreat_date;
	}

	public void setRetreat_date(String retreatDate) {
		retreat_date = retreatDate;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getSelectedField() {
		return selectedField;
	}

	public void setSelectedField(String selectedField) {
		this.selectedField = selectedField;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public Integer getCuId() {
		return cuId;
	}

	public void setCuId(Integer cuId) {
		this.cuId = cuId;
	}

	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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

	public Integer getXsCarSelId() {
		return xsCarSelId;
	}

	public void setXsCarSelId(Integer xsCarSelId) {
		this.xsCarSelId = xsCarSelId;
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

	public String getXsCarSelData() {
		return xsCarSelData;
	}

	public void setXsCarSelData(String xsCarSelData) {
		this.xsCarSelData = xsCarSelData;
	}

	public String getSellCode() {
		return sellCode;
	}

	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}

	public Double getXsCarSelTransactionMoney() {
		return xsCarSelTransactionMoney;
	}

	public void setXsCarSelTransactionMoney(Double xsCarSelTransactionMoney) {
		this.xsCarSelTransactionMoney = xsCarSelTransactionMoney;
	}

	public String getXsCarGiveUp() {
		return xsCarGiveUp;
	}

	public void setXsCarGiveUp(String xsCarGiveUp) {
		this.xsCarGiveUp = xsCarGiveUp;
	}

	public Integer getXsCarStfId() {
		return xsCarStfId;
	}

	public void setXsCarStfId(Integer xsCarStfId) {
		this.xsCarStfId = xsCarStfId;
	}

	public String getXsCustomReceiptor() {
		return xsCustomReceiptor;
	}

	public void setXsCustomReceiptor(String xsCustomReceiptor) {
		this.xsCustomReceiptor = xsCustomReceiptor;
	}

	public Integer getXsCarSelType() {
		return xsCarSelType;
	}

	public void setXsCarSelType(Integer xsCarSelType) {
		this.xsCarSelType = xsCarSelType;
	}

	public Double getApplySum() {
		return applySum;
	}

	public void setApplySum(Double applySum) {
		this.applySum = applySum;
	}

	public Double getCostSum() {
		return costSum;
	}

	public void setCostSum(Double costSum) {
		this.costSum = costSum;
	}

	public String getXsCarSelRemark() {
		return xsCarSelRemark;
	}

	public void setXsCarSelRemark(String xsCarSelRemark) {
		this.xsCarSelRemark = xsCarSelRemark;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Integer getOutId() {
		return outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Integer getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(Integer auditPerson) {
		this.auditPerson = auditPerson;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
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

	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
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

	public Integer getInvoiceReckoning() {
		return invoiceReckoning;
	}

	public void setInvoiceReckoning(Integer invoiceReckoning) {
		this.invoiceReckoning = invoiceReckoning;
	}

	public Integer getSellafterStatus() {
		return sellafterStatus;
	}

	public void setSellafterStatus(Integer sellafterStatus) {
		this.sellafterStatus = sellafterStatus;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public Integer getCarColor() {
		return carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public String getCarColorName() {
		return carColorName;
	}

	public void setCarColorName(String carColorName) {
		this.carColorName = carColorName;
	}

	public Integer getCarModel() {
		return carModel;
	}

	public void setCarModel(Integer carModel) {
		this.carModel = carModel;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarLicenseName() {
		return carLicenseName;
	}

	public void setCarLicenseName(String carLicenseName) {
		this.carLicenseName = carLicenseName;
	}

	public String getCarVinNumber() {
		return carVinNumber;
	}

	public void setCarVinNumber(String carVinNumber) {
		this.carVinNumber = carVinNumber;
	}

	public Double getModelCostPrice() {
		return modelCostPrice;
	}

	public void setModelCostPrice(Double modelCostPrice) {
		this.modelCostPrice = modelCostPrice;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getInserted() {
		return inserted;
	}

	public void setInserted(String inserted) {
		this.inserted = inserted;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public Double getModelSalesPrice() {
		return modelSalesPrice;
	}

	public void setModelSalesPrice(Double modelSalesPrice) {
		this.modelSalesPrice = modelSalesPrice;
	}

	public Double getCarInstorageAge() {
		return carInstorageAge;
	}

	public void setCarInstorageAge(Double carInstorageAge) {
		this.carInstorageAge = carInstorageAge;
	}

	public Double getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

}
