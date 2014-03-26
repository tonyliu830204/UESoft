package com.syuesoft.sell.carAllocate.vo;


/**
 * 车辆预定Vo
 * @author mulangtao
 *
 */

public class SellCarReserveVo {
	private Integer reserveId;
	private String reserveCode;
	private Integer customId;
	private String customName;//客户姓名
	private Integer xsCarId;
	private Integer stfIdPerson;
	private String  reserveDete ;//预定时间


	private String reserveDete2;
	private Integer carBrand;
	private String carBrandName;//车辆品牌
	private Integer carModel;
	private String carModelName;//车辆型号名称
	private Integer carColor;
	private String carColorName;//车辆颜色名称
	private Integer carTrim;
	private Integer carOutputVolume;
	private Integer limitLoadNum;
	private String pactCode;
	private Integer customProperty;
	private Double paymentMoney;//预付
	private Integer payWay;
	private Double sellingprice;//售价
	private Integer examine;
	private Integer orderState;
	private String predictTakeDate;//预交时间
	private String predictTakeDate2;//预交时间
	private Integer stfId;
	private String stfName;//业务员
	private String xsDistributorCode;
	private Integer level;
	private String levelName;//等级名称
	private Double firstPayMoney;
	private Integer loanBank;
	private Double loanLimitMoney;
	private Double loanLimitYear;
	private String vinCode;
	private String customOpinion;//客户要求
	private Double shingleMoney;
	private Double decorateMoney;
	private Integer allocateState;
	private Integer delState;
	private String remark;
	private Integer vinNum;
	private Integer carId;
	private String xs_car_assemble_data;//配车日期
	private Integer enterpriseId;		//企业編號
	private Boolean flag;
	
	
	
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getXs_car_assemble_data() {
		return xs_car_assemble_data;
	}
	public void setXs_car_assemble_data(String xsCarAssembleData) {
		xs_car_assemble_data = xsCarAssembleData;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	private String sort;
	private String order;
	private int rows;
	private int page;
	
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
	public Integer getVinNum() {
		return vinNum;
	}
	public void setVinNum(Integer vinNum) {
		this.vinNum = vinNum;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getCarModelName() {
		return carModelName;
	}
	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}
	public String getCarColorName() {
		return carColorName;
	}
	public void setCarColorName(String carColorName) {
		this.carColorName = carColorName;
	}
	public String getStfName() {
		return stfName;
	}
	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Integer getReserveId() {
		return reserveId;
	}
	public void setReserveId(Integer reserveId) {
		this.reserveId = reserveId;
	}
	public String getReserveCode() {
		return reserveCode;
	}
	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
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
	public Integer getStfIdPerson() {
		return stfIdPerson;
	}
	public void setStfIdPerson(Integer stfIdPerson) {
		this.stfIdPerson = stfIdPerson;
	}
	
	
	public Integer getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}
	public Integer getCarModel() {
		return carModel;
	}
	public void setCarModel(Integer carModel) {
		this.carModel = carModel;
	}
	public Integer getCarColor() {
		return carColor;
	}
	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}
	public Integer getCarTrim() {
		return carTrim;
	}
	public void setCarTrim(Integer carTrim) {
		this.carTrim = carTrim;
	}
	public Integer getCarOutputVolume() {
		return carOutputVolume;
	}
	public void setCarOutputVolume(Integer carOutputVolume) {
		this.carOutputVolume = carOutputVolume;
	}
	public Integer getLimitLoadNum() {
		return limitLoadNum;
	}
	public void setLimitLoadNum(Integer limitLoadNum) {
		this.limitLoadNum = limitLoadNum;
	}
	public String getPactCode() {
		return pactCode;
	}
	public void setPactCode(String pactCode) {
		this.pactCode = pactCode;
	}
	public Integer getCustomProperty() {
		return customProperty;
	}
	public void setCustomProperty(Integer customProperty) {
		this.customProperty = customProperty;
	}
	public Double getPaymentMoney() {
		return paymentMoney;
	}
	public void setPaymentMoney(Double paymentMoney) {
		this.paymentMoney = paymentMoney;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public Double getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(Double sellingprice) {
		this.sellingprice = sellingprice;
	}
	public Integer getExamine() {
		return examine;
	}
	public void setExamine(Integer examine) {
		this.examine = examine;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public String getPredictTakeDate() {
		return predictTakeDate;
	}
	public void setPredictTakeDate(String predictTakeDate) {
		this.predictTakeDate = predictTakeDate;
	}
	public Integer getStfId() {
		return stfId;
	}
	public void setStfId(Integer stfId) {
		this.stfId = stfId;
	}
	public String getXsDistributorCode() {
		return xsDistributorCode;
	}
	public void setXsDistributorCode(String xsDistributorCode) {
		this.xsDistributorCode = xsDistributorCode;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Double getFirstPayMoney() {
		return firstPayMoney;
	}
	public void setFirstPayMoney(Double firstPayMoney) {
		this.firstPayMoney = firstPayMoney;
	}
	public Integer getLoanBank() {
		return loanBank;
	}
	public void setLoanBank(Integer loanBank) {
		this.loanBank = loanBank;
	}
	public Double getLoanLimitMoney() {
		return loanLimitMoney;
	}
	public void setLoanLimitMoney(Double loanLimitMoney) {
		this.loanLimitMoney = loanLimitMoney;
	}
	public Double getLoanLimitYear() {
		return loanLimitYear;
	}
	public void setLoanLimitYear(Double loanLimitYear) {
		this.loanLimitYear = loanLimitYear;
	}
	public String getVinCode() {
		return vinCode;
	}
	public void setVinCode(String vinCode) {
		this.vinCode = vinCode;
	}
	public String getCustomOpinion() {
		return customOpinion;
	}
	public void setCustomOpinion(String customOpinion) {
		this.customOpinion = customOpinion;
	}
	public Double getShingleMoney() {
		return shingleMoney;
	}
	public void setShingleMoney(Double shingleMoney) {
		this.shingleMoney = shingleMoney;
	}
	public Double getDecorateMoney() {
		return decorateMoney;
	}
	public void setDecorateMoney(Double decorateMoney) {
		this.decorateMoney = decorateMoney;
	}
	public Integer getAllocateState() {
		return allocateState;
	}
	public void setAllocateState(Integer allocateState) {
		this.allocateState = allocateState;
	}
	public Integer getDelState() {
		return delState;
	}
	public void setDelState(Integer delState) {
		this.delState = delState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPredictTakeDate2() {
		return predictTakeDate2;
	}
	public void setPredictTakeDate2(String predictTakeDate2) {
		this.predictTakeDate2 = predictTakeDate2;
	}
	public String getCarBrandName() {
		return carBrandName;
	}
	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}
	public String getReserveDete() {
		return reserveDete;
	}
	public void setReserveDete(String reserveDete) {
		this.reserveDete = reserveDete;
	}
	public String getReserveDete2() {
		return reserveDete2;
	}
	public void setReserveDete2(String reserveDete2) {
		this.reserveDete2 = reserveDete2;
	}
	
}