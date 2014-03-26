package com.syuesoft.vipmanagement.vo;

public class IntegralIntegratedQueryVo {
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private String vip_Id;
	private String car_License;
	private String car_Vin;
	private String vip_Name;
	private String vip_Birthday;
	private String vip_Tel;
	private String vip_Level_Id;
	private String vip_Level_Name;
	private String vip_Group_Id;
	private String vip_Group_Name;
	private String vip_Status;
	private String vip_StatusValue;
	private String jion_Time;
	private String end_Time;
	private String vip_Integral;                 //当前积分及可用积分
	private String vip_Integral1;                 //当前积分及可用积分
	private String Vip_Balance;
	private String vip_Number;
	private String vip_Age;                      //会龄
	private String rec_Amount;                   //累计储值金额
	private String give_Amount;                  //累计赠送金额
	private String vip_Balance_Sum;              //累计卡金额
	private String vip_Balance12;                //累计卡兑换金额
	private String vip_Balance14;                //累计维修积分
	private String vip_Balance15;                //累计销售积分
	private String vip_Balance18;                //累计兑换扣分
	private String vip_Balance19;                //累计会员卡升级赠分
	private String vip_Balance20;                //累计会员卡降级扣分
	private String vip_Balance21;                //累计续会赠分
	private String vip_Balance22;                //累计退会扣分
	
	private String give_Inte;                    //累计储值赠送积分
	private String give_Inte_Num;                //累计赠分
	
    private String stockId;
	private String preclr_Time;                  //结算时间
	private String preclr_Id;                    //结算单号
	private String reception_Id;                 //工单号
	private String preclr_Sum_Amount;            //结算金额
	private String operatorName;
	
	private String vip_Rec_Date;                 //储值日期
	private String vip_Rec_Id;                   //充值编号
	private String vip_Rec_Note;                 //充值备注
	
	private String give_Inte_Id;  	             //积分赠送编号
	private String give_Inte_Date;               //积分赠送日期
	private String give_Inte_Note;               //积分赠送备注
	
	private String upgrade_Id;                   //升级编号
	private String upgrade_Date;                 //升级日期
	private String memo;                         //升级备注
	private String gift_Points;                  //升级赠送积分
	private String deductionI_ntegration;        //升级时 扣除积分
	
	private String adjournment_Date;             //续会日期
	private String adjournment_Memo;             //续会理由
	private String adjournment_Rep;              //续会赠分
	private String exitmember_Date;              //退会日期
	private String exitmember_Deduct;            //退会扣分
	private String exitmember_Memo;              //退会理由
	
	
	
	
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
    public String getVip_StatusValue()
    {
        return vip_StatusValue;
    }
    public void setVip_StatusValue(String vipStatusValue)
    {
        vip_StatusValue = vipStatusValue;
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
    public String getVip_Age()
    {
        return vip_Age;
    }
    public void setVip_Age(String vipAge)
    {
        vip_Age = vipAge;
    }
    public String getRec_Amount()
    {
        return rec_Amount;
    }
    public void setRec_Amount(String recAmount)
    {
        rec_Amount = recAmount;
    }
    public String getGive_Amount()
    {
        return give_Amount;
    }
    public void setGive_Amount(String giveAmount)
    {
        give_Amount = giveAmount;
    }
    public String getVip_Balance_Sum()
    {
        return vip_Balance_Sum;
    }
    public void setVip_Balance_Sum(String vipBalanceSum)
    {
        vip_Balance_Sum = vipBalanceSum;
    }
    public String getVip_Balance12()
    {
        return vip_Balance12;
    }
    public void setVip_Balance12(String vipBalance12)
    {
        vip_Balance12 = vipBalance12;
    }
    public String getVip_Balance14()
    {
        return vip_Balance14;
    }
    public void setVip_Balance14(String vipBalance14)
    {
        vip_Balance14 = vipBalance14;
    }
    public String getVip_Balance15()
    {
        return vip_Balance15;
    }
    public void setVip_Balance15(String vipBalance15)
    {
        vip_Balance15 = vipBalance15;
    }
    public String getVip_Balance18()
    {
        return vip_Balance18;
    }
    public void setVip_Balance18(String vipBalance18)
    {
        vip_Balance18 = vipBalance18;
    }
    public String getVip_Balance19()
    {
        return vip_Balance19;
    }
    public void setVip_Balance19(String vipBalance19)
    {
        vip_Balance19 = vipBalance19;
    }
    public String getVip_Balance20()
    {
        return vip_Balance20;
    }
    public void setVip_Balance20(String vipBalance20)
    {
        vip_Balance20 = vipBalance20;
    }
    public String getVip_Balance21()
    {
        return vip_Balance21;
    }
    public void setVip_Balance21(String vipBalance21)
    {
        vip_Balance21 = vipBalance21;
    }
    public String getVip_Balance22()
    {
        return vip_Balance22;
    }
    public void setVip_Balance22(String vipBalance22)
    {
        vip_Balance22 = vipBalance22;
    }
    public String getGive_Inte()
    {
        return give_Inte;
    }
    public void setGive_Inte(String giveInte)
    {
        give_Inte = giveInte;
    }
    public String getGive_Inte_Num()
    {
        return give_Inte_Num;
    }
    public void setGive_Inte_Num(String giveInteNum)
    {
        give_Inte_Num = giveInteNum;
    }
    public String getPreclr_Time()
    {
        return preclr_Time;
    }
    public void setPreclr_Time(String preclrTime)
    {
        preclr_Time = preclrTime;
    }
    public String getPreclr_Id()
    {
        return preclr_Id;
    }
    public void setPreclr_Id(String preclrId)
    {
        preclr_Id = preclrId;
    }
    public String getReception_Id()
    {
        return reception_Id;
    }
    public void setReception_Id(String receptionId)
    {
        reception_Id = receptionId;
    }
    public String getPreclr_Sum_Amount()
    {
        return preclr_Sum_Amount;
    }
    public void setPreclr_Sum_Amount(String preclrSumAmount)
    {
        preclr_Sum_Amount = preclrSumAmount;
    }
    public String getVip_Rec_Date()
    {
        return vip_Rec_Date;
    }
    public void setVip_Rec_Date(String vipRecDate)
    {
        vip_Rec_Date = vipRecDate;
    }
    public String getVip_Rec_Id()
    {
        return vip_Rec_Id;
    }
    public void setVip_Rec_Id(String vipRecId)
    {
        vip_Rec_Id = vipRecId;
    }
    public String getVip_Rec_Note()
    {
        return vip_Rec_Note;
    }
    public void setVip_Rec_Note(String vipRecNote)
    {
        vip_Rec_Note = vipRecNote;
    }
    public String getGive_Inte_Id()
    {
        return give_Inte_Id;
    }
    public void setGive_Inte_Id(String giveInteId)
    {
        give_Inte_Id = giveInteId;
    }
    public String getGive_Inte_Date()
    {
        return give_Inte_Date;
    }
    public void setGive_Inte_Date(String giveInteDate)
    {
        give_Inte_Date = giveInteDate;
    }
    public String getGive_Inte_Note()
    {
        return give_Inte_Note;
    }
    public void setGive_Inte_Note(String giveInteNote)
    {
        give_Inte_Note = giveInteNote;
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
    public String getMemo()
    {
        return memo;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public String getGift_Points()
    {
        return gift_Points;
    }
    public void setGift_Points(String giftPoints)
    {
        gift_Points = giftPoints;
    }
    public String getDeductionI_ntegration()
    {
        return deductionI_ntegration;
    }
    public void setDeductionI_ntegration(String deductionINtegration)
    {
        deductionI_ntegration = deductionINtegration;
    }
    public String getAdjournment_Date()
    {
        return adjournment_Date;
    }
    public void setAdjournment_Date(String adjournmentDate)
    {
        adjournment_Date = adjournmentDate;
    }
    public String getAdjournment_Memo()
    {
        return adjournment_Memo;
    }
    public void setAdjournment_Memo(String adjournmentMemo)
    {
        adjournment_Memo = adjournmentMemo;
    }
    public String getAdjournment_Rep()
    {
        return adjournment_Rep;
    }
    public void setAdjournment_Rep(String adjournmentRep)
    {
        adjournment_Rep = adjournmentRep;
    }
    public String getExitmember_Date()
    {
        return exitmember_Date;
    }
    public void setExitmember_Date(String exitmemberDate)
    {
        exitmember_Date = exitmemberDate;
    }
    public String getExitmember_Deduct()
    {
        return exitmember_Deduct;
    }
    public void setExitmember_Deduct(String exitmemberDeduct)
    {
        exitmember_Deduct = exitmemberDeduct;
    }
    public String getExitmember_Memo()
    {
        return exitmember_Memo;
    }
    public void setExitmember_Memo(String exitmemberMemo)
    {
        exitmember_Memo = exitmemberMemo;
    }
    public String getVip_Integral1()
    {
        return vip_Integral1;
    }
    public void setVip_Integral1(String vipIntegral1)
    {
        vip_Integral1 = vipIntegral1;
    }
    public String getStockId()
    {
        return stockId;
    }
    public void setStockId(String stockId)
    {
        this.stockId = stockId;
    }
    public String getOperatorName()
    {
        return operatorName;
    }
    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }
}