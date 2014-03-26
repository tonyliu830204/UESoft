package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * FrtParts entity. @author MyEclipse Persistence Tools
 */

public class FrtParts extends BaseBean {

	// Fields

	private String partsId;                    //配件编号
	private BasPartsType basPartsType; 
	private String partsId2;                  //配件编码2
	private String partsName;                 //配件名称
	private String partsSimpleId;             //配件简码
	private String fitPtype;                  //适合型号
	private String posName;                   //配件部位
	private String punitName;                 //配件单位
	private String stateName;                 //产地
	private String partsLibrary;                 //配件库位
	private Integer partsNeedPoint;           //兑换积分
	private String partsRemark;               //配件描述
	private Boolean partsFlag;                 //配件标志
	private String partsProperty;				//配件属性
	private String partsAge;					//配件年份
	private String partsSpecs;					//配件规格
	private Integer enterpriseId;
   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	private Set stStorageItems = new HashSet(0);

	// Constructors

	/** default constructor */
	public FrtParts() {
	}

	/** minimal constructor */
	public FrtParts(String partsId) {
		this.partsId = partsId;
	}

	/** full constructor */
	public FrtParts(String partsId, BasPartsType basPartsType, String partsId2, String partsName, String partsSimpleId, String fitPtype, String posName, String punitName, String stateName,
			String partsLibrary, Integer partsNeedPoint, String partsRemark, Boolean partsFlag, Set stStorageItems) {
		this.partsId = partsId;
		this.basPartsType = basPartsType;
		this.partsId2 = partsId2;
		this.partsName = partsName;
		this.partsSimpleId = partsSimpleId;
		this.fitPtype = fitPtype;
		this.posName = posName;
		this.punitName = punitName;
		this.stateName = stateName;
		this.partsLibrary = partsLibrary;
		this.partsNeedPoint = partsNeedPoint;
		this.partsRemark = partsRemark;
		this.partsFlag = partsFlag;
		this.stStorageItems = stStorageItems;
	}

	// Property accessors

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public BasPartsType getBasPartsType() {
		return this.basPartsType;
	}

	public void setBasPartsType(BasPartsType basPartsType) {
		this.basPartsType = basPartsType;
	}

	public String getPartsId2() {
		return this.partsId2;
	}

	public void setPartsId2(String partsId2) {
		this.partsId2 = partsId2;
	}

	public String getPartsName() {
		return this.partsName;
	}

	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}

	public String getPartsSimpleId() {
		return this.partsSimpleId;
	}

	public void setPartsSimpleId(String partsSimpleId) {
		this.partsSimpleId = partsSimpleId;
	}

	public String getFitPtype() {
		return this.fitPtype;
	}

	public void setFitPtype(String fitPtype) {
		this.fitPtype = fitPtype;
	}

	public String getPosName() {
		return this.posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getPunitName() {
		return this.punitName;
	}

	public void setPunitName(String punitName) {
		this.punitName = punitName;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getPartsLibrary() {
		return this.partsLibrary;
	}

	public void setPartsLibrary(String partsLibrary) {
		this.partsLibrary = partsLibrary;
	}

	public Integer getPartsNeedPoint() {
		return this.partsNeedPoint;
	}

	public void setPartsNeedPoint(Integer partsNeedPoint) {
		this.partsNeedPoint = partsNeedPoint;
	}

	public String getPartsRemark() {
		return this.partsRemark;
	}

	public void setPartsRemark(String partsRemark) {
		this.partsRemark = partsRemark;
	}

	public Boolean getPartsFlag() {
		return partsFlag;
	}

	public void setPartsFlag(Boolean partsFlag) {
		this.partsFlag = partsFlag;
	}

	public Set getStStorageItems() {
		return this.stStorageItems;
	}

	public void setStStorageItems(Set stStorageItems) {
		this.stStorageItems = stStorageItems;
	}

	public String getPartsProperty() {
		return partsProperty;
	}

	public void setPartsProperty(String partsProperty) {
		this.partsProperty = partsProperty;
	}

	public String getPartsAge() {
		return partsAge;
	}

	public void setPartsAge(String partsAge) {
		this.partsAge = partsAge;
	}

	public String getPartsSpecs() {
		return partsSpecs;
	}

	public void setPartsSpecs(String partsSpecs) {
		this.partsSpecs = partsSpecs;
	}

}