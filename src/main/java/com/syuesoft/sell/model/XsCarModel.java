package com.syuesoft.sell.model;

import java.io.Serializable;

public class XsCarModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private Integer enterpriseId;
	private XsChilddictionary xsChilddictionary;

	
	public XsChilddictionary getXsChilddictionary() {
		return xsChilddictionary;
	}

	public void setXsChilddictionary(XsChilddictionary xsChilddictionary) {
		this.xsChilddictionary = xsChilddictionary;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
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
}