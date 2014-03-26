package com.syuesoft.model;

/**
 * BasVipRelationship16 entity. @author MyEclipse Persistence Tools
 */

public class BasVipRelationship16 extends BaseBean {

	// Fields

	private BasVipRelationship16Id id;    
	private Double shijiZengfen;         //赠送积分 

	// Constructors

	/** default constructor */
	public BasVipRelationship16() {
	}

	/** minimal constructor */
	public BasVipRelationship16(BasVipRelationship16Id id) {
		this.id = id;
	}

	/** full constructor */
	public BasVipRelationship16(BasVipRelationship16Id id, Double shijiZengfen) {
		this.id = id;
		this.shijiZengfen = shijiZengfen;
	}

	// Property accessors

	public BasVipRelationship16Id getId() {
		return this.id;
	}

	public void setId(BasVipRelationship16Id id) {
		this.id = id;
	}

	public Double getShijiZengfen() {
		return this.shijiZengfen;
	}

	public void setShijiZengfen(Double shijiZengfen) {
		this.shijiZengfen = shijiZengfen;
	}

}