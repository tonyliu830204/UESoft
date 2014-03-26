package com.syuesoft.vipmanagement.vo;

public class GiftExchangeManagementVo {
	
	private int page;
	private int rows;
	private String sort;
	private String order;
	//接受传来的 datagrid数据 字符串
	private String inserted;   
	private String deleted;
	private String updated;
	
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
	
	private String project_Id;
	private String project_Name;
	private String project_score;
	
	private String exchange_Id;         //
	private String exchange_Date;       //
	private String exchange_Date2;       //
	private String audit_Date;
	private String audit_Situation;
	private String audit_SituationName;
	private String audit_Manager;
	private String audit_ManagerName;
	private String total_Score;         //总共所需积分
	private String Amount;
	private String exchange_User;
	private String operator;            // 操作员
    private String operatorName;        // 操作员
    
	private String exchange_Detail_Id;
	private String exchange_Quantity;
	private String exchange_Amount;
	private String exchange_Memo;
	
	private String data;
	
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
    
    public String getProject_Id()
    {
        return project_Id;
    }
    public void setProject_Id(String projectId)
    {
        project_Id = projectId;
    }
    public String getProject_Name()
    {
        return project_Name;
    }
    public void setProject_Name(String projectName)
    {
        project_Name = projectName;
    }
    public String getProject_score()
    {
        return project_score;
    }
    public void setProject_score(String projectScore)
    {
        project_score = projectScore;
    }
    public String getExchange_Id()
    {
        return exchange_Id;
    }
    public void setExchange_Id(String exchangeId)
    {
        exchange_Id = exchangeId;
    }
    public String getExchange_Date()
    {
        return exchange_Date;
    }
    public void setExchange_Date(String exchangeDate)
    {
        exchange_Date = exchangeDate;
    }
    public String getAudit_Date()
    {
        return audit_Date;
    }
    public void setAudit_Date(String auditDate)
    {
        audit_Date = auditDate;
    }
    public String getAudit_Situation()
    {
        return audit_Situation;
    }
    public void setAudit_Situation(String auditSituation)
    {
        audit_Situation = auditSituation;
    }
    public String getAudit_SituationName()
    {
        return audit_SituationName;
    }
    public void setAudit_SituationName(String auditSituationName)
    {
        audit_SituationName = auditSituationName;
    }
    public String getAudit_Manager()
    {
        return audit_Manager;
    }
    public void setAudit_Manager(String auditManager)
    {
        audit_Manager = auditManager;
    }
    public String getAudit_ManagerName()
    {
        return audit_ManagerName;
    }
    public void setAudit_ManagerName(String auditManagerName)
    {
        audit_ManagerName = auditManagerName;
    }
    public String getTotal_Score()
    {
        return total_Score;
    }
    public void setTotal_Score(String totalScore)
    {
        total_Score = totalScore;
    }
    public String getAmount()
    {
        return Amount;
    }
    public void setAmount(String amount)
    {
        Amount = amount;
    }
    public String getExchange_User()
    {
        return exchange_User;
    }
    public void setExchange_User(String exchangeUser)
    {
        exchange_User = exchangeUser;
    }
    public String getOperator()
    {
        return operator;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }
    public String getOperatorName()
    {
        return operatorName;
    }
    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }
    public String getExchange_Detail_Id()
    {
        return exchange_Detail_Id;
    }
    public void setExchange_Detail_Id(String exchangeDetailId)
    {
        exchange_Detail_Id = exchangeDetailId;
    }
    public String getExchange_Quantity()
    {
        return exchange_Quantity;
    }
    public void setExchange_Quantity(String exchangeQuantity)
    {
        exchange_Quantity = exchangeQuantity;
    }
    public String getExchange_Amount()
    {
        return exchange_Amount;
    }
    public void setExchange_Amount(String exchangeAmount)
    {
        exchange_Amount = exchangeAmount;
    }
    public String getExchange_Memo()
    {
        return exchange_Memo;
    }
    public void setExchange_Memo(String exchangeMemo)
    {
        exchange_Memo = exchangeMemo;
    }
    public String getData()
    {
        return data;
    }
    public void setData(String data)
    {
        this.data = data;
    }
    public String getEnd_Time2()
    {
        return end_Time2;
    }
    public void setEnd_Time2(String endTime2)
    {
        end_Time2 = endTime2;
    }
    public String getExchange_Date2()
    {
        return exchange_Date2;
    }
    public void setExchange_Date2(String exchangeDate2)
    {
        exchange_Date2 = exchangeDate2;
    }
}