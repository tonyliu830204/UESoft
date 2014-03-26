package com.syuesoft.vipmanagement.vo;

import java.util.Date;

public class ExitMemberVo {
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private String car_Id;
	private String vip_Id;
	private String car_License;
	private String car_Vin;
	private String cbrd_Name;
	private String color_Name;
	private String ctype_Name;
	private String custom_Name;
	private String custom_Sex;
	private String custom_Birthday;
	private String custom_Addr;
	private String custom_Tel1;
	private String custom_Tel2;
	private String vip_Level_Name;
	private String vip_Group_Name;
	private String vip_Status;
	private String jion_Time;
	private String end_Time;
	private String end_Time2;
	private String vip_Integral;	//当前积分及可用积分
	private String Vip_Balance;
	private String vip_Number;
	
	private String memo;    		//入会备注
	private String vip_Age;
	private String parea_Name;
	private String vip_Hobby;
	private String exitmember_Date;  //退回日期
	private String exitmember_Deduct; //退会扣分
	private String exitmember_Memo;  //退回备注
	
	
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
	public String getCar_Id() {
		return car_Id;
	}
	public void setCar_Id(String carId) {
		car_Id = carId;
	}
	public String getVip_Id() {
		return vip_Id;
	}
	public void setVip_Id(String vipId) {
		vip_Id = vipId;
	}
	public String getCar_License() {
		return car_License;
	}
	public void setCar_License(String carLicense) {
		car_License = carLicense;
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
	public String getColor_Name() {
		return color_Name;
	}
	public void setColor_Name(String colorName) {
		color_Name = colorName;
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
	public String getCustom_Sex() {
		return custom_Sex;
	}
	public void setCustom_Sex(String customSex) {
		custom_Sex = customSex;
	}
	public String getCustom_Birthday() {
		return custom_Birthday;
	}
	public void setCustom_Birthday(String customBirthday) {
		custom_Birthday = customBirthday;
	}
	public String getCustom_Addr() {
		return custom_Addr;
	}
	public void setCustom_Addr(String customAddr) {
		custom_Addr = customAddr;
	}
	public String getCustom_Tel1() {
		return custom_Tel1;
	}
	public void setCustom_Tel1(String customTel1) {
		custom_Tel1 = customTel1;
	}
	public String getCustom_Tel2() {
		return custom_Tel2;
	}
	public void setCustom_Tel2(String customTel2) {
		custom_Tel2 = customTel2;
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
	public String getVip_Status() {
		return vip_Status;
	}
	public void setVip_Status(String vipStatus) {
		vip_Status = vipStatus;
	}
	public String getJion_Time() {
		return jion_Time;
	}
	public void setJion_Time(String jionTime) {
		jion_Time = jionTime;
	}
	public String getEnd_Time() {
		return end_Time;
	}
	public void setEnd_Time(String endTime) {
		end_Time = endTime;
	}
	public String getEnd_Time2() {
		return end_Time2;
	}
	public void setEnd_Time2(String endTime2) {
		end_Time2 = endTime2;
	}
	public String getVip_Integral() {
		return vip_Integral;
	}
	public void setVip_Integral(String vipIntegral) {
		vip_Integral = vipIntegral;
	}
	public String getVip_Balance() {
		return Vip_Balance;
	}
	public void setVip_Balance(String vipBalance) {
		Vip_Balance = vipBalance;
	}
	public String getVip_Number() {
		return vip_Number;
	}
	public void setVip_Number(String vipNumber) {
		vip_Number = vipNumber;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getVip_Age() {
		return vip_Age;
	}
	public void setVip_Age(String vipAge) {
		vip_Age = vipAge;
	}
	public String getParea_Name() {
		return parea_Name;
	}
	public void setParea_Name(String pareaName) {
		parea_Name = pareaName;
	}
	public String getVip_Hobby() {
		return vip_Hobby;
	}
	public void setVip_Hobby(String vipHobby) {
		vip_Hobby = vipHobby;
	}
	public String getExitmember_Date() {
		return exitmember_Date;
	}
	public void setExitmember_Date(String exitmemberDate) {
		exitmember_Date = exitmemberDate;
	}
	public String getExitmember_Deduct() {
		return exitmember_Deduct;
	}
	public void setExitmember_Deduct(String exitmemberDeduct) {
		exitmember_Deduct = exitmemberDeduct;
	}
	public String getExitmember_Memo() {
		return exitmember_Memo;
	}
	public void setExitmember_Memo(String exitmemberMemo) {
		exitmember_Memo = exitmemberMemo;
	}
}
