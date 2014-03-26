package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StPurOrder entity. @author MyEclipse Persistence Tools
 */


public class StPurOrder extends BaseBean {

	// Fields

	private String orderId;
	private BasRelationCampany basRelationCampany;
	private Short buyer;
	private Short manager;
	private Date orderDate;
	private Double numTotal;
	private Date deliveryDate;
	private String orderRemark;
	private String receptionId;
	private String notesType;
	private String classification;
	private String examine;
	private String transitToState;
	private String paid;
	private Double taxRate;
	private Double totalAmount;
	private Double notaxTotalamont;
	private Double taxCount;
	@SuppressWarnings("unchecked")
	private Set stPurOrderitems = new HashSet(0);
	   private Integer enterpriseId;

	   public Integer getEnterpriseId() {
	       return enterpriseId;
	   }

	   public void setEnterpriseId(Integer enterpriseId) {
	       this.enterpriseId = enterpriseId;
	   }
	// Constructors

	/** default constructor */
	public StPurOrder() {
	}

	/** minimal constructor */
	public StPurOrder(String orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public StPurOrder(String orderId, BasRelationCampany basRelationCampany,
			Short buyer, Short manager, Date orderDate, Double numTotal,
			Date deliveryDate, String orderRemark, String receptionId,
			String notesType, String classification, String examine,
			String transitToState, String paid, Double taxRate,
			Double totalAmount, Double notaxTotalamont, Double taxCount,
			Set stPurOrderitems) {
		this.orderId = orderId;
		this.basRelationCampany = basRelationCampany;
		this.buyer = buyer;
		this.manager = manager;
		this.orderDate = orderDate;
		this.numTotal = numTotal;
		this.deliveryDate = deliveryDate;
		this.orderRemark = orderRemark;
		this.receptionId = receptionId;
		this.notesType = notesType;
		this.classification = classification;
		this.examine = examine;
		this.transitToState = transitToState;
		this.paid = paid;
		this.taxRate = taxRate;
		this.totalAmount = totalAmount;
		this.notaxTotalamont = notaxTotalamont;
		this.taxCount = taxCount;
		this.stPurOrderitems = stPurOrderitems;
	}

	// Property accessors

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BasRelationCampany getBasRelationCampany() {
		return this.basRelationCampany;
	}

	public void setBasRelationCampany(BasRelationCampany basRelationCampany) {
		this.basRelationCampany = basRelationCampany;
	}

	public Short getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Short buyer) {
		this.buyer = buyer;
	}

	public Short getManager() {
		return this.manager;
	}

	public void setManager(Short manager) {
		this.manager = manager;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getNumTotal() {
		return this.numTotal;
	}

	public void setNumTotal(Double numTotal) {
		this.numTotal = numTotal;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderRemark() {
		return this.orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

	public String getNotesType() {
		return this.notesType;
	}

	public void setNotesType(String notesType) {
		this.notesType = notesType;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getTransitToState() {
		return this.transitToState;
	}

	public void setTransitToState(String transitToState) {
		this.transitToState = transitToState;
	}

	public Double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getNotaxTotalamont() {
		return this.notaxTotalamont;
	}

	public void setNotaxTotalamont(Double notaxTotalamont) {
		this.notaxTotalamont = notaxTotalamont;
	}

	public Double getTaxCount() {
		return this.taxCount;
	}

	public void setTaxCount(Double taxCount) {
		this.taxCount = taxCount;
	}

	public Set getStPurOrderitems() {
		return this.stPurOrderitems;
	}

	public void setStPurOrderitems(Set stPurOrderitems) {
		this.stPurOrderitems = stPurOrderitems;
	}

	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

}