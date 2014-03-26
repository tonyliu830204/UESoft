package com.syuesoft.sell.base.vo;

public class CarModelVo implements java.io.Serializable {
	private Integer modelId;
	private Integer carBrand;
	private Integer modelExamineApprove;
	private Integer modelCancelModel;
	private String modelCode;
	private String modelName;
	private Double modelCostPrice;
	private Double modelSalesPrice;
	private Double modelSalesLimitPrice;
	private Double modelTwoSalesLimitPrice;
	private Double modelThreeSalesLimitPrice;
	private String modelNormsModel;
	private String modelRemark;
	// 车品牌名称
	private String carBrandName;
	// 以下为分页字段
	private String sort;
	private String order;
	private int rows;
	private int page;
	// 查询所用
	private Integer carMId;
	private String carMName;
	private String carMBrand;
	private String q;
	private Integer cbrdId;
	private Integer ctypeId;
	private Integer enterpriseId;
	private String types;
	private Boolean isCancle;//是否取消
	
	public Boolean getIsCancle() {
		return isCancle;
	}

	public void setIsCancle(Boolean isCancle) {
		this.isCancle = isCancle;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Integer getCarMId() {
		return carMId;
	}

	public void setCarMId(Integer carMId) {
		this.carMId = carMId;
	}

	public String getCarMName() {
		return carMName;
	}

	public void setCarMName(String carMName) {
		this.carMName = carMName;
	}

	public String getCarMBrand() {
		return carMBrand;
	}

	public void setCarMBrand(String carMBrand) {
		this.carMBrand = carMBrand;
	}

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public Integer getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getModelExamineApprove() {
		return modelExamineApprove;
	}

	public void setModelExamineApprove(Integer modelExamineApprove) {
		this.modelExamineApprove = modelExamineApprove;
	}

	public Integer getModelCancelModel() {
		return modelCancelModel;
	}

	public void setModelCancelModel(Integer modelCancelModel) {
		this.modelCancelModel = modelCancelModel;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Double getModelCostPrice() {
		return modelCostPrice;
	}

	public void setModelCostPrice(Double modelCostPrice) {
		this.modelCostPrice = modelCostPrice;
	}

	public Double getModelSalesPrice() {
		return modelSalesPrice;
	}

	public void setModelSalesPrice(Double modelSalesPrice) {
		this.modelSalesPrice = modelSalesPrice;
	}

	public Double getModelSalesLimitPrice() {
		return modelSalesLimitPrice;
	}

	public void setModelSalesLimitPrice(Double modelSalesLimitPrice) {
		this.modelSalesLimitPrice = modelSalesLimitPrice;
	}

	public Double getModelTwoSalesLimitPrice() {
		return modelTwoSalesLimitPrice;
	}

	public void setModelTwoSalesLimitPrice(Double modelTwoSalesLimitPrice) {
		this.modelTwoSalesLimitPrice = modelTwoSalesLimitPrice;
	}

	public Double getModelThreeSalesLimitPrice() {
		return modelThreeSalesLimitPrice;
	}

	public void setModelThreeSalesLimitPrice(Double modelThreeSalesLimitPrice) {
		this.modelThreeSalesLimitPrice = modelThreeSalesLimitPrice;
	}

	public String getModelNormsModel() {
		return modelNormsModel;
	}

	public void setModelNormsModel(String modelNormsModel) {
		this.modelNormsModel = modelNormsModel;
	}

	public String getModelRemark() {
		return modelRemark;
	}

	public void setModelRemark(String modelRemark) {
		this.modelRemark = modelRemark;
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

	public Integer getCbrdId() {
		return cbrdId;
	}

	public void setCbrdId(Integer cbrdId) {
		this.cbrdId = cbrdId;
	}

	public Integer getCtypeId() {
		return ctypeId;
	}

	public void setCtypeId(Integer ctypeId) {
		this.ctypeId = ctypeId;
	}

}