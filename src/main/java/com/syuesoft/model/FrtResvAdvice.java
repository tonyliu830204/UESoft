package com.syuesoft.model;

import java.util.Date;

/**
 * FrtResvItem entity. @author MyEclipse Persistence Tools
 */

public class FrtResvAdvice extends BaseBean {

	// Fields

	private Integer adviceId;
	private FrtCar frtCar;
	private String adviceContext;
	private Date adviceTime;
	private Date adviceTimeEnd;
	private Integer writePerson;
	private String procesContext;
	private Integer procesState;
	private Integer procesProson;
	private Short adviceClass;
	private String resvId;
	private Short adviceFlg;
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	// Constructors

	/** default constructor */
	public FrtResvAdvice() {
	}

	public Integer getAdviceId() {
		return adviceId;
	}

	public void setAdviceId(Integer adviceId) {
		this.adviceId = adviceId;
	}

	public FrtCar getFrtCar() {
		return frtCar;
	}

	public void setFrtCar(FrtCar frtCar) {
		this.frtCar = frtCar;
	}

	public String getAdviceContext() {
		return adviceContext;
	}

	public void setAdviceContext(String adviceContext) {
		this.adviceContext = adviceContext;
	}

	public Date getAdviceTime() {
		return adviceTime;
	}

	public void setAdviceTime(Date adviceTime) {
		this.adviceTime = adviceTime;
	}

	public Integer getWritePerson() {
		return writePerson;
	}

	public void setWritePerson(Integer writePerson) {
		this.writePerson = writePerson;
	}

	public String getProcesContext() {
		return procesContext;
	}

	public void setProcesContext(String procesContext) {
		this.procesContext = procesContext;
	}

	public Integer getProcesState() {
		return procesState;
	}

	public void setProcesState(Integer procesState) {
		this.procesState = procesState;
	}

	public Integer getProcesProson() {
		return procesProson;
	}

	public void setProcesProson(Integer procesProson) {
		this.procesProson = procesProson;
	}

	public Date getAdviceTimeEnd() {
		return adviceTimeEnd;
	}

	public void setAdviceTimeEnd(Date adviceTimeEnd) {
		this.adviceTimeEnd = adviceTimeEnd;
	}

	public Short getAdviceClass() {
		return adviceClass;
	}

	public void setAdviceClass(Short adviceClass) {
		this.adviceClass = adviceClass;
	}

	public Short getAdviceFlg() {
		return adviceFlg;
	}

	public void setAdviceFlg(Short adviceFlg) {
		this.adviceFlg = adviceFlg;
	}

	public String getResvId() {
		return resvId;
	}

	public void setResvId(String resvId) {
		this.resvId = resvId;
	}
	
}