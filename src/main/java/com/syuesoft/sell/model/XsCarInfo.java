package com.syuesoft.sell.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsCarInfo extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer carId;
	private Integer carBrand;
	private Integer carColor;
	private Integer carInteriorColor;
	private Integer carModelId;
	private Integer carOptional;
	private Integer carMortgageState;
	private Integer carCertificateState;
	private Integer carType;
	private String carCode;
	private String carLicensePlate;
	private String carLicenseName;
	private String carVinNumber;
	private String carMotorNumber;
	private String carOcn;
	private String carCertificate;
	private Date carMakeData;
	private String carUnlockingKeyNumber;
	private Integer carRideLimitNumber;
	private String carTradeCheckBill;
	private String carProductionAddress;
	private String carAddress;
	private Timestamp carCopyData;
	private Timestamp carAssembleData;
	private Timestamp carEndCheckData;
	private Timestamp carFristInsuranceData;
	private Timestamp carForecastData;
	private Double carPrice;
	private Integer distributorId;
	private Integer carSellState;
	private Integer carDistributState;
	private Integer fixStatus;
	private Date servicingNextdate;
	private String upData;
	private Integer upPerson;
	private Integer norms;// 规格
	private Integer toolCase;// 工具包
	private String proveFile;// 证明文件
	private String rzBook;// 认证书
	private Integer footd;// 脚垫
	private Integer enterpriseId;
	private XsChilddictionary  brandChild;
	private XsChilddictionary  colorChild;
	private XsChilddictionary  interiorColorChild;
	
	private XsChilddictionary  carTypeChild;
	private XsChilddictionary  normsChild;
	private XsChilddictionary  toolCaseChild;
	private XsChilddictionary  footdChild;
	private XsChilddictionary  sellStateChild;
	private XsChilddictionary  certificateChild;
	private XsChilddictionary  distributChild;
	private XsChilddictionary  fixStatusChild;
	
	
	public XsChilddictionary getCertificateChild() {
		return certificateChild;
	}

	public void setCertificateChild(XsChilddictionary certificateChild) {
		this.certificateChild = certificateChild;
	}

	public XsChilddictionary getDistributChild() {
		return distributChild;
	}

	public void setDistributChild(XsChilddictionary distributChild) {
		this.distributChild = distributChild;
	}

	public XsChilddictionary getFixStatusChild() {
		return fixStatusChild;
	}

	public void setFixStatusChild(XsChilddictionary fixStatusChild) {
		this.fixStatusChild = fixStatusChild;
	}

	public XsChilddictionary getSellStateChild() {
		return sellStateChild;
	}

	public void setSellStateChild(XsChilddictionary sellStateChild) {
		this.sellStateChild = sellStateChild;
	}

	public XsChilddictionary getCarTypeChild() {
		return carTypeChild;
	}

	public void setCarTypeChild(XsChilddictionary carTypeChild) {
		this.carTypeChild = carTypeChild;
	}

	public XsChilddictionary getNormsChild() {
		return normsChild;
	}

	public void setNormsChild(XsChilddictionary normsChild) {
		this.normsChild = normsChild;
	}

	public XsChilddictionary getToolCaseChild() {
		return toolCaseChild;
	}

	public void setToolCaseChild(XsChilddictionary toolCaseChild) {
		this.toolCaseChild = toolCaseChild;
	}

	public XsChilddictionary getFootdChild() {
		return footdChild;
	}

	public void setFootdChild(XsChilddictionary footdChild) {
		this.footdChild = footdChild;
	}

	public XsChilddictionary getBrandChild() {
		return brandChild;
	}

	public void setBrandChild(XsChilddictionary brandChild) {
		this.brandChild = brandChild;
	}

	public XsChilddictionary getColorChild() {
		return colorChild;
	}

	public void setColorChild(XsChilddictionary colorChild) {
		this.colorChild = colorChild;
	}

	public XsChilddictionary getInteriorColorChild() {
		return interiorColorChild;
	}

	public void setInteriorColorChild(XsChilddictionary interiorColorChild) {
		this.interiorColorChild = interiorColorChild;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getNorms() {
		return norms;
	}

	public void setNorms(Integer norms) {
		this.norms = norms;
	}

	public Integer getToolCase() {
		return toolCase;
	}

	public void setToolCase(Integer toolCase) {
		this.toolCase = toolCase;
	}

	public String getProveFile() {
		return proveFile;
	}

	public void setProveFile(String proveFile) {
		this.proveFile = proveFile;
	}

	public String getRzBook() {
		return rzBook;
	}

	public void setRzBook(String rzBook) {
		this.rzBook = rzBook;
	}

	public Integer getFootd() {
		return footd;
	}

	public void setFootd(Integer footd) {
		this.footd = footd;
	}

	public String getUpData() {
		return upData;
	}

	public void setUpData(String upData) {
		this.upData = upData;
	}

	public Integer getUpPerson() {
		return upPerson;
	}

	public void setUpPerson(Integer upPerson) {
		this.upPerson = upPerson;
	}

	public Integer getFixStatus() {
		return fixStatus;
	}

	public void setFixStatus(Integer fixStatus) {
		this.fixStatus = fixStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Integer getCarColor() {
		return carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public Integer getCarInteriorColor() {
		return carInteriorColor;
	}

	public void setCarInteriorColor(Integer carInteriorColor) {
		this.carInteriorColor = carInteriorColor;
	}

	public Integer getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}

	public Integer getCarOptional() {
		return carOptional;
	}

	public void setCarOptional(Integer carOptional) {
		this.carOptional = carOptional;
	}

	public Integer getCarMortgageState() {
		return carMortgageState;
	}

	public void setCarMortgageState(Integer carMortgageState) {
		this.carMortgageState = carMortgageState;
	}

	public Integer getCarCertificateState() {
		return carCertificateState;
	}

	public void setCarCertificateState(Integer carCertificateState) {
		this.carCertificateState = carCertificateState;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarLicensePlate() {
		return carLicensePlate;
	}

	public void setCarLicensePlate(String carLicensePlate) {
		this.carLicensePlate = carLicensePlate;
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

	public String getCarMotorNumber() {
		return carMotorNumber;
	}

	public void setCarMotorNumber(String carMotorNumber) {
		this.carMotorNumber = carMotorNumber;
	}

	public String getCarOcn() {
		return carOcn;
	}

	public void setCarOcn(String carOcn) {
		this.carOcn = carOcn;
	}

	public String getCarCertificate() {
		return carCertificate;
	}

	public void setCarCertificate(String carCertificate) {
		this.carCertificate = carCertificate;
	}

	public Date getCarMakeData() {
		return carMakeData;
	}

	public void setCarMakeData(Date carMakeData) {
		this.carMakeData = carMakeData;
	}

	public String getCarUnlockingKeyNumber() {
		return carUnlockingKeyNumber;
	}

	public void setCarUnlockingKeyNumber(String carUnlockingKeyNumber) {
		this.carUnlockingKeyNumber = carUnlockingKeyNumber;
	}

	public Integer getCarRideLimitNumber() {
		return carRideLimitNumber;
	}

	public void setCarRideLimitNumber(Integer carRideLimitNumber) {
		this.carRideLimitNumber = carRideLimitNumber;
	}

	public String getCarTradeCheckBill() {
		return carTradeCheckBill;
	}

	public void setCarTradeCheckBill(String carTradeCheckBill) {
		this.carTradeCheckBill = carTradeCheckBill;
	}

	public String getCarProductionAddress() {
		return carProductionAddress;
	}

	public void setCarProductionAddress(String carProductionAddress) {
		this.carProductionAddress = carProductionAddress;
	}

	public String getCarAddress() {
		return carAddress;
	}

	public void setCarAddress(String carAddress) {
		this.carAddress = carAddress;
	}

	public Timestamp getCarCopyData() {
		return carCopyData;
	}

	public void setCarCopyData(Timestamp carCopyData) {
		this.carCopyData = carCopyData;
	}

	public Timestamp getCarAssembleData() {
		return carAssembleData;
	}

	public void setCarAssembleData(Timestamp carAssembleData) {
		this.carAssembleData = carAssembleData;
	}

	public Timestamp getCarEndCheckData() {
		return carEndCheckData;
	}

	public void setCarEndCheckData(Timestamp carEndCheckData) {
		this.carEndCheckData = carEndCheckData;
	}

	public Timestamp getCarFristInsuranceData() {
		return carFristInsuranceData;
	}

	public void setCarFristInsuranceData(Timestamp carFristInsuranceData) {
		this.carFristInsuranceData = carFristInsuranceData;
	}

	public Timestamp getCarForecastData() {
		return carForecastData;
	}

	public void setCarForecastData(Timestamp carForecastData) {
		this.carForecastData = carForecastData;
	}

	public Double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(Double carPrice) {
		this.carPrice = carPrice;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}

	public Integer getCarSellState() {
		return carSellState;
	}

	public void setCarSellState(Integer carSellState) {
		this.carSellState = carSellState;
	}

	public Integer getCarDistributState() {
		return carDistributState;
	}

	public void setCarDistributState(Integer carDistributState) {
		this.carDistributState = carDistributState;
	}

	public Date getServicingNextdate() {
		return servicingNextdate;
	}

	public void setServicingNextdate(Date servicingNextdate) {
		this.servicingNextdate = servicingNextdate;
	}

}
