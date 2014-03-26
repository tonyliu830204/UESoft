package com.syuesoft.vipmanagement.vo;

public class InfomationSendManageVo {
	
	private int page;
	private int rows;
	private String sort;
	private String order;
	//接受传来的 datagrid数据 字符串
	private String inserted;   
	private String deleted;
	private String updated;
	
	private String car_Id;
	private String car_License;
	private String car_Buy_Date;
	private String car_Buy_Date1;
	private String car_Vin;
	private String cbrd_Name;
	private String ctype_Name;
	private String custom_Name;
	private String parea_Name;
	private String custom_Tel1;
	private String vip_Level_Name;
	private String vip_Group_Name;
	private String car_Last_Maint_Date;
	private String car_Last_Maint_Date1;
	private String car_Annual_Date;
	private String car_Annual_Date1;
	private String car_Exanined_Date;
	private String car_Exanined_Date1;
	private String car_Businsurance_Date;
	private String car_Businsurance_Date1;
	private String car_Basinsurance_Date; 
	private String car_Basinsurance_Date1; 
	private String car_Last_Repair_Distance;
	private String custom_Id;
	private String send_Content;	//短信发送内容
	private String other_Send_Date;	//定时发送时间
	private String now_Send_Date;	//立即发送时间
	private String send_Number;   	//发送号码
	private String success_Or_Not;	//是否成功
	private String info_Id;			//信息id
	private String test_Number;     //测试号码（发给自己的）
	
	
	public String getTest_Number() {
		return test_Number;
	}
	public void setTest_Number(String testNumber) {
		test_Number = testNumber;
	}
	public String getInfo_Id() {
		return info_Id;
	}
	public void setInfo_Id(String infoId) {
		info_Id = infoId;
	}
	public String getNow_Send_Date() {
		return now_Send_Date;
	}
	public void setNow_Send_Date(String nowSendDate) {
		now_Send_Date = nowSendDate;
	}
	public String getSend_Number() {
		return send_Number;
	}
	public void setSend_Number(String sendNumber) {
		send_Number = sendNumber;
	}
	public String getSuccess_Or_Not() {
		return success_Or_Not;
	}
	public void setSuccess_Or_Not(String successOrNot) {
		success_Or_Not = successOrNot;
	}
	public String getOther_Send_Date() {
		return other_Send_Date;
	}
	public void setOther_Send_Date(String otherSendDate) {
		other_Send_Date = otherSendDate;
	}
	public String getInserted() {
		return inserted;
	}
	public void setInserted(String inserted) {
		this.inserted = inserted;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getSend_Content() {
		return send_Content;
	}
	public void setSend_Content(String sendContent) {
		send_Content = sendContent;
	}
	public String getCustom_Id() {
		return custom_Id;
	}
	public void setCustom_Id(String customId) {
		custom_Id = customId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
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
	public String getCar_License() {
		return car_License;
	}
	public void setCar_License(String carLicense) {
		car_License = carLicense;
	}
	public String getCar_Buy_Date() {
		return car_Buy_Date;
	}
	public void setCar_Buy_Date(String carBuyDate) {
		car_Buy_Date = carBuyDate;
	}
	public String getCar_Vin() {
		return car_Vin;
	}
	public void setCar_Vin(String carVin) {
		car_Vin = carVin;
	}
	public String getCbrd_Name() {
		return cbrd_Name;
	}
	public void setCbrd_Name(String cbrdName) {
		cbrd_Name = cbrdName;
	}
	public String getCtype_Name() {
		return ctype_Name;
	}
	public void setCtype_Name(String ctypeName) {
		ctype_Name = ctypeName;
	}
	public String getCustom_Name() {
		return custom_Name;
	}
	public void setCustom_Name(String customName) {
		custom_Name = customName;
	}
	public String getParea_Name() {
		return parea_Name;
	}
	public void setParea_Name(String pareaName) {
		parea_Name = pareaName;
	}
	public String getCustom_Tel1() {
		return custom_Tel1;
	}
	public void setCustom_Tel1(String customTel1) {
		custom_Tel1 = customTel1;
	}
	public String getVip_Level_Name() {
		return vip_Level_Name;
	}
	public void setVip_Level_Name(String vipLevelName) {
		vip_Level_Name = vipLevelName;
	}
	public String getVip_Group_Name() {
		return vip_Group_Name;
	}
	public void setVip_Group_Name(String vipGroupName) {
		vip_Group_Name = vipGroupName;
	}
	public String getCar_Last_Maint_Date() {
		return car_Last_Maint_Date;
	}
	public void setCar_Last_Maint_Date(String carLastMaintDate) {
		car_Last_Maint_Date = carLastMaintDate;
	}
	public String getCar_Annual_Date() {
		return car_Annual_Date;
	}
	public void setCar_Annual_Date(String carAnnualDate) {
		car_Annual_Date = carAnnualDate;
	}
	public String getCar_Exanined_Date() {
		return car_Exanined_Date;
	}
	public void setCar_Exanined_Date(String carExaninedDate) {
		car_Exanined_Date = carExaninedDate;
	}
	public String getCar_Businsurance_Date() {
		return car_Businsurance_Date;
	}
	public void setCar_Businsurance_Date(String carBusinsuranceDate) {
		car_Businsurance_Date = carBusinsuranceDate;
	}
	public String getCar_Basinsurance_Date() {
		return car_Basinsurance_Date;
	}
	public void setCar_Basinsurance_Date(String carBasinsuranceDate) {
		car_Basinsurance_Date = carBasinsuranceDate;
	}
	public String getCar_Last_Repair_Distance() {
		return car_Last_Repair_Distance;
	}
	public void setCar_Last_Repair_Distance(String carLastRepairDistance) {
		car_Last_Repair_Distance = carLastRepairDistance;
	}
    public String getCar_Annual_Date1()
    {
        return car_Annual_Date1;
    }
    public void setCar_Annual_Date1(String carAnnualDate1)
    {
        car_Annual_Date1 = carAnnualDate1;
    }
    public String getCar_Basinsurance_Date1()
    {
        return car_Basinsurance_Date1;
    }
    public void setCar_Basinsurance_Date1(String carBasinsuranceDate1)
    {
        car_Basinsurance_Date1 = carBasinsuranceDate1;
    }
    public String getCar_Businsurance_Date1()
    {
        return car_Businsurance_Date1;
    }
    public void setCar_Businsurance_Date1(String carBusinsuranceDate1)
    {
        car_Businsurance_Date1 = carBusinsuranceDate1;
    }
    public String getCar_Buy_Date1()
    {
        return car_Buy_Date1;
    }
    public void setCar_Buy_Date1(String carBuyDate1)
    {
        car_Buy_Date1 = carBuyDate1;
    }
    public String getCar_Exanined_Date1()
    {
        return car_Exanined_Date1;
    }
    public void setCar_Exanined_Date1(String carExaninedDate1)
    {
        car_Exanined_Date1 = carExaninedDate1;
    }
    public String getCar_Last_Maint_Date1()
    {
        return car_Last_Maint_Date1;
    }
    public void setCar_Last_Maint_Date1(String carLastMaintDate1)
    {
        car_Last_Maint_Date1 = carLastMaintDate1;
    }
    public String getCar_Id()
    {
        return car_Id;
    }
    public void setCar_Id(String carId)
    {
        car_Id = carId;
    }
    
}