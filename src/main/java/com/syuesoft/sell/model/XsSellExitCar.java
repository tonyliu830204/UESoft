package com.syuesoft.sell.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * XsSellCollections entity. @author MyEclipse Persistence Tools
 */

public class XsSellExitCar  implements java.io.Serializable {


    // Fields    
	
	private Integer enterpriseId;				//企业编号
     private Integer exitCarId;				//退车单编号
     private XsCarSellInfo xsCarSellInfo;		//车辆销售信息编号
     private String exitCarCode;				//退车单号
     private String exitCarDate;				//退车日期
     private Integer exitCarType;					//退车分类
     private Integer exitCarPerson;					//经办人
     private String xsCarPdsDate;				//PDS日期\售前质量检测日期
     private Integer xsCarPdsPerson;				//PDS人员
     private String xsCarPdsResult;				//PDS结果
     private Integer exitCarCheckPerson;		//审核人
     private Integer examine;					//审核状态
     private Integer notice;					//是否通知退款，0未通知，1 已通知
     private String exitCarRemark;					//退车备注
     private Set xsBackCarLogs = new HashSet(0);


    // Constructors

    /** default constructor */
     
    public XsSellExitCar() {
    }


    
	public Integer getEnterpriseId() {
		return enterpriseId;
	}



	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}



	public Integer getNotice() {
		return notice;
	}



	public void setNotice(Integer notice) {
		this.notice = notice;
	}



	public Integer getExitCarId() {
		return exitCarId;
	}


	public void setExitCarId(Integer exitCarId) {
		this.exitCarId = exitCarId;
	}


	public XsCarSellInfo getXsCarSellInfo() {
		return xsCarSellInfo;
	}


	public void setXsCarSellInfo(XsCarSellInfo xsCarSellInfo) {
		this.xsCarSellInfo = xsCarSellInfo;
	}


	public String getExitCarCode() {
		return exitCarCode;
	}


	public void setExitCarCode(String exitCarCode) {
		this.exitCarCode = exitCarCode;
	}


	public String getExitCarDate() {
		return exitCarDate;
	}


	public void setExitCarDate(String exitCarDate) {
		this.exitCarDate = exitCarDate;
	}

	public String getXsCarPdsDate() {
		return xsCarPdsDate;
	}


	public void setXsCarPdsDate(String xsCarPdsDate) {
		this.xsCarPdsDate = xsCarPdsDate;
	}


	public Integer getXsCarPdsPerson() {
		return xsCarPdsPerson;
	}


	public void setXsCarPdsPerson(Integer xsCarPdsPerson) {
		this.xsCarPdsPerson = xsCarPdsPerson;
	}


	public String getXsCarPdsResult() {
		return xsCarPdsResult;
	}


	public void setXsCarPdsResult(String xsCarPdsResult) {
		this.xsCarPdsResult = xsCarPdsResult;
	}


	public Integer getExamine() {
		return examine;
	}


	public void setExamine(Integer examine) {
		this.examine = examine;
	}


	public Set getXsBackCarLogs() {
		return xsBackCarLogs;
	}


	public void setXsBackCarLogs(Set xsBackCarLogs) {
		this.xsBackCarLogs = xsBackCarLogs;
	}



	public Integer getExitCarType() {
		return exitCarType;
	}



	public void setExitCarType(Integer exitCarType) {
		this.exitCarType = exitCarType;
	}



	public String getExitCarRemark() {
		return exitCarRemark;
	}



	public void setExitCarRemark(String exitCarRemark) {
		this.exitCarRemark = exitCarRemark;
	}



	public Integer getExitCarPerson() {
		return exitCarPerson;
	}



	public void setExitCarPerson(Integer exitCarPerson) {
		this.exitCarPerson = exitCarPerson;
	}



	public Integer getExitCarCheckPerson() {
		return exitCarCheckPerson;
	}



	public void setExitCarCheckPerson(Integer exitCarCheckPerson) {
		this.exitCarCheckPerson = exitCarCheckPerson;
	}


}