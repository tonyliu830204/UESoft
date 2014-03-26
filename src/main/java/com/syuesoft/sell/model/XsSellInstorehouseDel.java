package com.syuesoft.sell.model;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class XsSellInstorehouseDel  extends BaseBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer detailsId;
	private XsSellInstorehouse instorehouse;
	private XsCarInfo carInfo;
	private XsSellAllocatel sellAllocatel;
	private String carInstorageResult;
	private Integer carInstoragePerson;
	private Date carbackDate;
	private String carbackReason;
	private Date carOutData;
	private Date carPdsData;
	private Integer carPdsPerson;
	private Integer xsCensus;        //是否结转
	private String carPdsResult;
	private Float carInstorageAge;
	private Double vehicleCost;
	private Double notax;
	private Double tax;
	private Integer sellAllocatelType;
	private String orderNumber;//订货单号(只适合导入)
	private  Date instorageDate;//进库日期(只适合导入)
	private Double changeMoney;//改装费
	private Double freightMoney;//运费
	
	
	public Double getChangeMoney() {
		return changeMoney;
	}
	public void setChangeMoney(Double changeMoney) {
		this.changeMoney = changeMoney;
	}
	public Double getFreightMoney() {
		return freightMoney;
	}
	public void setFreightMoney(Double freightMoney) {
		this.freightMoney = freightMoney;
	}
	public Integer getXsCensus() {
		return xsCensus;
	}
	public void setXsCensus(Integer xsCensus) {
		this.xsCensus = xsCensus;
	}
	public XsSellAllocatel getSellAllocatel() {
		return sellAllocatel;
	}
	public void setSellAllocatel(XsSellAllocatel sellAllocatel) {
		this.sellAllocatel = sellAllocatel;
	}
	public Integer getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}
	public XsSellInstorehouse getInstorehouse() {
		return instorehouse;
	}
	public void setInstorehouse(XsSellInstorehouse instorehouse) {
		this.instorehouse = instorehouse;
	}
	public XsCarInfo getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(XsCarInfo carInfo) {
		this.carInfo = carInfo;
	}
	public String getCarInstorageResult() {
		return carInstorageResult;
	}
	public void setCarInstorageResult(String carInstorageResult) {
		this.carInstorageResult = carInstorageResult;
	}
	public Integer getCarInstoragePerson() {
		return carInstoragePerson;
	}
	public void setCarInstoragePerson(Integer carInstoragePerson) {
		this.carInstoragePerson = carInstoragePerson;
	}
	public Date getCarbackDate() {
		return carbackDate;
	}
	public void setCarbackDate(Date date) {
		this.carbackDate = date;
	}
	public String getCarbackReason() {
		return carbackReason;
	}
	public void setCarbackReason(String carbackReason) {
		this.carbackReason = carbackReason;
	}
	public Date getCarOutData() {
		return carOutData;
	}
	public void setCarOutData(Date carOutData) {
		this.carOutData = carOutData;
	}
	public Date getCarPdsData() {
		return carPdsData;
	}
	public void setCarPdsData(Date carPdsData) {
		this.carPdsData = carPdsData;
	}
	public Integer getCarPdsPerson() {
		return carPdsPerson;
	}
	public void setCarPdsPerson(Integer carPdsPerson) {
		this.carPdsPerson = carPdsPerson;
	}
	public String getCarPdsResult() {
		return carPdsResult;
	}
	public void setCarPdsResult(String carPdsResult) {
		this.carPdsResult = carPdsResult;
	}
	public Float getCarInstorageAge() {
		return carInstorageAge;
	}
	public void setCarInstorageAge(Float carInstorageAge) {
		this.carInstorageAge = carInstorageAge;
	}
	public Double getVehicleCost() {
		return vehicleCost;
	}
	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}
	public Double getNotax() {
		return notax;
	}
	public void setNotax(Double notax) {
		this.notax = notax;
	}
	
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Integer getSellAllocatelType() {
		return sellAllocatelType;
	}
	public void setSellAllocatelType(Integer sellAllocatelType) {
		this.sellAllocatelType = sellAllocatelType;
	}
	public Date getInstorageDate() {
		return instorageDate;
	}
	public void setInstorageDate(Date instorageDate) {
		this.instorageDate = instorageDate;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
}
