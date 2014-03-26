package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FbkCollect entity. @author MyEclipse Persistence Tools
 */

public class FbkCollect extends BaseBean {

	// Fields
	private FrtPreClearing frtPreClearing;

	private Integer collectId;          //汇总表id
	//private FrtCar frtCar;				//外键(车辆表的id)
	private String returnVisitMembers;  //回访人
	private String callSituation;		//回访状态
	private String satisfaction;		//满意程度
	private String notSatisfaction;		//不满意程度

	private Date returnVisitDate;
	private String handlePerson;
	private String reciptReturnvisit;
	private Integer returnSituatiom;
	private Set fbkDetails = new HashSet(0);

   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	public Integer getCollectId() {
		return this.collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	/*public FrtCar getFrtCar() {
		return this.frtCar;
	}

	public void setFrtCar(FrtCar frtCar) {
		this.frtCar = frtCar;
	}*/

	public String getReturnVisitMembers() {
		return this.returnVisitMembers;
	}

	public void setReturnVisitMembers(String returnVisitMembers) {
		this.returnVisitMembers = returnVisitMembers;
	}

	public String getCallSituation() {
		return this.callSituation;
	}

	public void setCallSituation(String callSituation) {
		this.callSituation = callSituation;
	}

	public String getSatisfaction() {
		return this.satisfaction;
	}

	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}

	public String getNotSatisfaction() {
		return this.notSatisfaction;
	}

	public void setNotSatisfaction(String notSatisfaction) {
		this.notSatisfaction = notSatisfaction;
	}

	public Date getReturnVisitDate() {
		return this.returnVisitDate;
	}

	public void setReturnVisitDate(Date returnVisitDate) {
		this.returnVisitDate = returnVisitDate;
	}

	public String getHandlePerson() {
		return this.handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getReciptReturnvisit() {
		return this.reciptReturnvisit;
	}

	public void setReciptReturnvisit(String reciptReturnvisit) {
		this.reciptReturnvisit = reciptReturnvisit;
	}

	public Integer getReturnSituatiom() {
		return this.returnSituatiom;
	}

	public void setReturnSituatiom(Integer returnSituatiom) {
		this.returnSituatiom = returnSituatiom;
	}

	public Set getFbkDetails() {
		return this.fbkDetails;
	}

	public void setFbkDetails(Set fbkDetails) {
		this.fbkDetails = fbkDetails;
	}

	public FrtPreClearing getFrtPreClearing() {
		return frtPreClearing;
	}

	public void setFrtPreClearing(FrtPreClearing frtPreClearing) {
		this.frtPreClearing = frtPreClearing;
	}
	

}