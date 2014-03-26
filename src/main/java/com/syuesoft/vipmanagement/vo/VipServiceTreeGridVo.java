package com.syuesoft.vipmanagement.vo;

public class VipServiceTreeGridVo {
	private int page;
	private int rows;
	
	private String state;                                                     //treegrid打开还是关闭
	private String iconCls; 
	private String _parentId; 
	
	private String meal_Name;
	private String meal_Id;
	private String note;
	private String childMenu;                                                 //是否有子菜单
	private String meal_Context;                                                 //是否有子菜单
	private String memo;                                                 //是否有子菜单
	private String number;                                                 //是否有子菜单
	private String create_Time;                                                 //是否有子菜单
	private String person;                                                 //是否有子菜单
	
	private String meal_RId;
	
	private String car_License;
    private String car_Vin;
    private String join_Time;
    private String end_Time;
    private String end_Time2;
    private String vip_Level_Id;//会员等级
    private String vip_Level_Name;//会员等级
    private String vip_Group_Id;//分组名称
    private String vip_Group_Name;//分组名称
    private String vip_Number;//会员卡号
    private String vip_Integral;//可用积分 ////当前积分
    private String vip_Total_Integral;//累计积分
    private String vip_Balance;//会员卡余额 
    private String vip_Id;//会员编号 
    private String vip_Status;//会员状态
    private String vip_Status_value;//会员状态
    private String vip_Name;//会员名称
    private String vip_Birthday;
    private String vip_Tel;
    private String vip_Age;
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
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    public String getIconCls()
    {
        return iconCls;
    }
    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }
    public String get_parentId()
    {
        return _parentId;
    }
    public void set_parentId(String parentId)
    {
        _parentId = parentId;
    }
    public String getMeal_Name()
    {
        return meal_Name;
    }
    public void setMeal_Name(String mealName)
    {
        meal_Name = mealName;
    }
    public String getMeal_Id()
    {
        return meal_Id;
    }
    public void setMeal_Id(String mealId)
    {
        meal_Id = mealId;
    }
    public String getNote()
    {
        return note;
    }
    public void setNote(String note)
    {
        this.note = note;
    }
    public String getChildMenu()
    {
        return childMenu;
    }
    public void setChildMenu(String childMenu)
    {
        this.childMenu = childMenu;
    }
    public String getMeal_Context()
    {
        return meal_Context;
    }
    public void setMeal_Context(String mealContext)
    {
        meal_Context = mealContext;
    }
    public String getMemo()
    {
        return memo;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public String getNumber()
    {
        return number;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }
    public String getCreate_Time()
    {
        return create_Time;
    }
    public void setCreate_Time(String createTime)
    {
        create_Time = createTime;
    }
    public String getPerson()
    {
        return person;
    }
    public void setPerson(String person)
    {
        this.person = person;
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
    public String getJoin_Time()
    {
        return join_Time;
    }
    public void setJoin_Time(String joinTime)
    {
        join_Time = joinTime;
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
    public String getVip_Number()
    {
        return vip_Number;
    }
    public void setVip_Number(String vipNumber)
    {
        vip_Number = vipNumber;
    }
    public String getVip_Integral()
    {
        return vip_Integral;
    }
    public void setVip_Integral(String vipIntegral)
    {
        vip_Integral = vipIntegral;
    }
    public String getVip_Total_Integral()
    {
        return vip_Total_Integral;
    }
    public void setVip_Total_Integral(String vipTotalIntegral)
    {
        vip_Total_Integral = vipTotalIntegral;
    }
    public String getVip_Balance()
    {
        return vip_Balance;
    }
    public void setVip_Balance(String vipBalance)
    {
        vip_Balance = vipBalance;
    }
    public String getVip_Id()
    {
        return vip_Id;
    }
    public void setVip_Id(String vipId)
    {
        vip_Id = vipId;
    }
    public String getVip_Status()
    {
        return vip_Status;
    }
    public void setVip_Status(String vipStatus)
    {
        vip_Status = vipStatus;
    }
    public String getVip_Status_value()
    {
        return vip_Status_value;
    }
    public void setVip_Status_value(String vipStatusValue)
    {
        vip_Status_value = vipStatusValue;
    }
    public String getVip_Name()
    {
        return vip_Name;
    }
    public void setVip_Name(String vipName)
    {
        vip_Name = vipName;
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
    public String getVip_Age()
    {
        return vip_Age;
    }
    public void setVip_Age(String vipAge)
    {
        vip_Age = vipAge;
    }
    public String getMeal_RId()
    {
        return meal_RId;
    }
    public void setMeal_RId(String mealRId)
    {
        meal_RId = mealRId;
    }
}