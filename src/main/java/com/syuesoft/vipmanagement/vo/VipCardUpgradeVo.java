package com.syuesoft.vipmanagement.vo;

public class VipCardUpgradeVo {

	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private String vip_Id;
	private String car_License;
	private String car_Vin;
	private String vip_Total_Integral;
	private String vip_Total_Integral2;
	private String vip_Integral;//当前积分及可用积分
	private String vip_Integral2;//当前积分及可用积分
	private String Vip_Balance;
	private String vip_Status;
	private String vip_StatusName;
	private String jion_Time;
	private String end_Time;
	private String end_Time2;
	private String vip_Number;
	private String vip_name;
	private String vip_Age;     //会龄
	private String vip_Balance_Sum; //累计卡余额
	private String vip_Birthday;
	private String vip_Tel;
	private String vip_Level_Id;//等级id  
    private String vip_Level_Name;//会员等级
    private String vip_Group_Id;//分组id
    private String vip_Group_Name;//分组名称
	
	private String upgrade_Id;    //升级编号
	private String upgrade_Date;    //升级日期
	private String upgrade_Date2;    //升级日期
	private String audit_Situation; // 审核情况
	private String audit_SituationValue; // 审核情况
	private String audit_Date;     // 审核日期
	private String audit_Managers; //审核经办
	private String audit_ManagersName; //审核经办
	private String managers;       //经办人
	private String managersName;       //经办人
 	private String memo;           //备注
 	
 	private String upgrade_detail_Id;//等级id  
 	private String oldVip_Level_Id;//等级id  
    private String oldVip_Level_Name;//会员等级
    private String newVip_Level_Id;//等级id  
    private String newVip_Level_Name;//会员等级
	private String deduction_Integration;  //扣除积分
	private String gift_Points;            //升级时赠送的积分
	
	//接受传来的 datagrid数据 字符串
	private String inserted;   
	private String deleted;
	private String updated;
	
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
    public String getVip_Total_Integral()
    {
        return vip_Total_Integral;
    }
    public void setVip_Total_Integral(String vipTotalIntegral)
    {
        vip_Total_Integral = vipTotalIntegral;
    }
    public String getVip_Total_Integral2()
    {
        return vip_Total_Integral2;
    }
    public void setVip_Total_Integral2(String vipTotalIntegral2)
    {
        vip_Total_Integral2 = vipTotalIntegral2;
    }
    public String getVip_Integral()
    {
        return vip_Integral;
    }
    public void setVip_Integral(String vipIntegral)
    {
        vip_Integral = vipIntegral;
    }
    public String getVip_Integral2()
    {
        return vip_Integral2;
    }
    public void setVip_Integral2(String vipIntegral2)
    {
        vip_Integral2 = vipIntegral2;
    }
    public String getVip_Balance()
    {
        return Vip_Balance;
    }
    public void setVip_Balance(String vipBalance)
    {
        Vip_Balance = vipBalance;
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
    public String getVip_Number()
    {
        return vip_Number;
    }
    public void setVip_Number(String vipNumber)
    {
        vip_Number = vipNumber;
    }
    public String getVip_name()
    {
        return vip_name;
    }
    public void setVip_name(String vipName)
    {
        vip_name = vipName;
    }
    public String getVip_Age()
    {
        return vip_Age;
    }
    public void setVip_Age(String vipAge)
    {
        vip_Age = vipAge;
    }
    public String getVip_Balance_Sum()
    {
        return vip_Balance_Sum;
    }
    public void setVip_Balance_Sum(String vipBalanceSum)
    {
        vip_Balance_Sum = vipBalanceSum;
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
    public String getUpgrade_Id()
    {
        return upgrade_Id;
    }
    public void setUpgrade_Id(String upgradeId)
    {
        upgrade_Id = upgradeId;
    }
    public String getUpgrade_Date()
    {
        return upgrade_Date;
    }
    public void setUpgrade_Date(String upgradeDate)
    {
        upgrade_Date = upgradeDate;
    }
    public String getUpgrade_Date2()
    {
        return upgrade_Date2;
    }
    public void setUpgrade_Date2(String upgradeDate2)
    {
        upgrade_Date2 = upgradeDate2;
    }
    public String getAudit_Situation()
    {
        return audit_Situation;
    }
    public void setAudit_Situation(String auditSituation)
    {
        audit_Situation = auditSituation;
    }
    public String getAudit_SituationValue()
    {
        return audit_SituationValue;
    }
    public void setAudit_SituationValue(String auditSituationValue)
    {
        audit_SituationValue = auditSituationValue;
    }
    public String getAudit_Date()
    {
        return audit_Date;
    }
    public void setAudit_Date(String auditDate)
    {
        audit_Date = auditDate;
    }
    public String getAudit_Managers()
    {
        return audit_Managers;
    }
    public void setAudit_Managers(String auditManagers)
    {
        audit_Managers = auditManagers;
    }
    public String getAudit_ManagersName()
    {
        return audit_ManagersName;
    }
    public void setAudit_ManagersName(String auditManagersName)
    {
        audit_ManagersName = auditManagersName;
    }
    public String getManagers()
    {
        return managers;
    }
    public void setManagers(String managers)
    {
        this.managers = managers;
    }
    public String getManagersName()
    {
        return managersName;
    }
    public void setManagersName(String managersName)
    {
        this.managersName = managersName;
    }
    public String getMemo()
    {
        return memo;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public String getDeduction_Integration()
    {
        return deduction_Integration;
    }
    public void setDeduction_Integration(String deductionIntegration)
    {
        deduction_Integration = deductionIntegration;
    }
    public String getGift_Points()
    {
        return gift_Points;
    }
    public void setGift_Points(String giftPoints)
    {
        gift_Points = giftPoints;
    }
    public String getInserted()
    {
        return inserted;
    }
    public void setInserted(String inserted)
    {
        this.inserted = inserted;
    }
    public String getDeleted()
    {
        return deleted;
    }
    public void setDeleted(String deleted)
    {
        this.deleted = deleted;
    }
    public String getUpdated()
    {
        return updated;
    }
    public void setUpdated(String updated)
    {
        this.updated = updated;
    }
    public String getOldVip_Level_Id()
    {
        return oldVip_Level_Id;
    }
    public void setOldVip_Level_Id(String oldVipLevelId)
    {
        oldVip_Level_Id = oldVipLevelId;
    }
    public String getOldVip_Level_Name()
    {
        return oldVip_Level_Name;
    }
    public void setOldVip_Level_Name(String oldVipLevelName)
    {
        oldVip_Level_Name = oldVipLevelName;
    }
    public String getUpgrade_detail_Id()
    {
        return upgrade_detail_Id;
    }
    public void setUpgrade_detail_Id(String upgradeDetailId)
    {
        upgrade_detail_Id = upgradeDetailId;
    }
    public String getNewVip_Level_Id()
    {
        return newVip_Level_Id;
    }
    public void setNewVip_Level_Id(String newVipLevelId)
    {
        newVip_Level_Id = newVipLevelId;
    }
    public String getNewVip_Level_Name()
    {
        return newVip_Level_Name;
    }
    public void setNewVip_Level_Name(String newVipLevelName)
    {
        newVip_Level_Name = newVipLevelName;
    }
}