package com.syuesoft.vipmanagement.vo;

public class VipAdjournmentVo {

	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private String vip_Id;
	private String car_License;
	private String car_Vin;
	private String vip_Name;
	private String vip_Sex;
	private String vip_Birthday;
	private String vip_Tel;
	private String vip_Level_Id;
	private String vip_Level_Name;
	private String vip_Group_Id;
	private String vip_Group_Name;
	private String vip_Status;
	private String vip_StatusName;
	private String jion_Time;
	private String end_Time;
	private String end_Time2;
	private String vip_Integral;               //当前积分及可用积分
	private String vip_Total_Integral;
	private String Vip_Balance;
	private String vip_Number;                 //会卡号
	private String vip_Age;                    //会龄
	private String vip_Hobby;                  //会员爱好 
	private String adjournment_Id;             //续会赠分
	private String adjournment_Date;           //续会日期
	private String adjournment_Date2;          //续会日期
	private String adjournment_Name;           //续会经办人
	private String adjournment_Integral;       //续会经办人
	private String adjournment_Memo;           //续会备注
	
    public int getPage()
    {
        return page;
    }
    public void setPage(int page)
    {
        this.page = page;
    }
    public int getRows()
    {
        return rows;
    }
    public void setRows(int rows)
    {
        this.rows = rows;
    }
    public String getSort()
    {
        return sort;
    }
    public void setSort(String sort)
    {
        this.sort = sort;
    }
    public String getOrder()
    {
        return order;
    }
    public void setOrder(String order)
    {
        this.order = order;
    }
    public String getVip_Id()
    {
        return vip_Id;
    }
    public void setVip_Id(String vipId)
    {
        vip_Id = vipId;
    }
    public String getCar_License()
    {
        return car_License;
    }
    public void setCar_License(String carLicense)
    {
        car_License = carLicense;
    }
    public String getCar_Vin()
    {
        return car_Vin;
    }
    public void setCar_Vin(String carVin)
    {
        car_Vin = carVin;
    }
    public String getVip_Name()
    {
        return vip_Name;
    }
    public void setVip_Name(String vipName)
    {
        vip_Name = vipName;
    }
    public String getVip_Sex()
    {
        return vip_Sex;
    }
    public void setVip_Sex(String vipSex)
    {
        vip_Sex = vipSex;
    }
    public String getVip_Birthday()
    {
        return vip_Birthday;
    }
    public void setVip_Birthday(String vipBirthday)
    {
        vip_Birthday = vipBirthday;
    }
    public String getVip_Tel()
    {
        return vip_Tel;
    }
    public void setVip_Tel(String vipTel)
    {
        vip_Tel = vipTel;
    }
    public String getVip_Level_Id()
    {
        return vip_Level_Id;
    }
    public void setVip_Level_Id(String vipLevelId)
    {
        vip_Level_Id = vipLevelId;
    }
    public String getVip_Level_Name()
    {
        return vip_Level_Name;
    }
    public void setVip_Level_Name(String vipLevelName)
    {
        vip_Level_Name = vipLevelName;
    }
    public String getVip_Group_Id()
    {
        return vip_Group_Id;
    }
    public void setVip_Group_Id(String vipGroupId)
    {
        vip_Group_Id = vipGroupId;
    }
    public String getVip_Group_Name()
    {
        return vip_Group_Name;
    }
    public void setVip_Group_Name(String vipGroupName)
    {
        vip_Group_Name = vipGroupName;
    }
    public String getVip_Status()
    {
        return vip_Status;
    }
    public void setVip_Status(String vipStatus)
    {
        vip_Status = vipStatus;
    }
    public String getVip_StatusName()
    {
        return vip_StatusName;
    }
    public void setVip_StatusName(String vipStatusName)
    {
        vip_StatusName = vipStatusName;
    }
    public String getJion_Time()
    {
        return jion_Time;
    }
    public void setJion_Time(String jionTime)
    {
        jion_Time = jionTime;
    }
    public String getEnd_Time()
    {
        return end_Time;
    }
    public void setEnd_Time(String endTime)
    {
        end_Time = endTime;
    }
    public String getEnd_Time2()
    {
        return end_Time2;
    }
    public void setEnd_Time2(String endTime2)
    {
        end_Time2 = endTime2;
    }
    public String getVip_Integral()
    {
        return vip_Integral;
    }
    public void setVip_Integral(String vipIntegral)
    {
        vip_Integral = vipIntegral;
    }
    public String getVip_Balance()
    {
        return Vip_Balance;
    }
    public void setVip_Balance(String vipBalance)
    {
        Vip_Balance = vipBalance;
    }
    public String getVip_Number()
    {
        return vip_Number;
    }
    public void setVip_Number(String vipNumber)
    {
        vip_Number = vipNumber;
    }
    public String getAdjournment_Date()
    {
        return adjournment_Date;
    }
    public void setAdjournment_Date(String adjournmentDate)
    {
        adjournment_Date = adjournmentDate;
    }
    public String getAdjournment_Date2()
    {
        return adjournment_Date2;
    }
    public void setAdjournment_Date2(String adjournmentDate2)
    {
        adjournment_Date2 = adjournmentDate2;
    }
    public String getVip_Age()
    {
        return vip_Age;
    }
    public void setVip_Age(String vipAge)
    {
        vip_Age = vipAge;
    }
    public String getVip_Hobby()
    {
        return vip_Hobby;
    }
    public void setVip_Hobby(String vipHobby)
    {
        vip_Hobby = vipHobby;
    }
    public String getAdjournment_Id()
    {
        return adjournment_Id;
    }
    public void setAdjournment_Id(String adjournmentId)
    {
        adjournment_Id = adjournmentId;
    }
    public String getAdjournment_Memo()
    {
        return adjournment_Memo;
    }
    public void setAdjournment_Memo(String adjournmentMemo)
    {
        adjournment_Memo = adjournmentMemo;
    }
    public String getVip_Total_Integral()
    {
        return vip_Total_Integral;
    }
    public void setVip_Total_Integral(String vipTotalIntegral)
    {
        vip_Total_Integral = vipTotalIntegral;
    }
    public String getAdjournment_Name()
    {
        return adjournment_Name;
    }
    public void setAdjournment_Name(String adjournmentName)
    {
        adjournment_Name = adjournmentName;
    }
    public String getAdjournment_Integral()
    {
        return adjournment_Integral;
    }
    public void setAdjournment_Integral(String adjournmentIntegral)
    {
        adjournment_Integral = adjournmentIntegral;
    }
}