package com.syuesoft.vipmanagement.vo;

public class VipRecordMessageVo {
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String q;
	
	private String vip_Id;
	private String vip_Number;                 //会员卡号
	private String vip_OldNumber;              //会员卡号
	private String vip_Integral;               //可用积分 ////当前积分
	private String vip_Total_Integral;         //累计积分
	private String vip_Balance;                //会员卡余额 
	private String vip_Hobby;                  //会员爱好
	private String vip_Birthday;
	private String vip_Birthday2;
	private String vip_Tel;
	private String join_Time;
	private String join_Time2;
	private String end_Time;//
	private String end_Time2;
	private String vip_Status;                 //会员状态
	private String vip_Status_value;
	private String vip_Name;                   //会员名称
	private String car_License;
	private String car_Last_Maint_Distance;
	private String car_Vin;
	private String ctype_Name;
	private String cbrd_Name;
	private String cbrl_Name;
	private String vip_Age;
	private String vip_Level_Id;               //等级id  
	private String vip_Level_Name;             //会员等级
	private String vip_Group_Id;               //分组id
	private String vip_Group_Name;             //分组名称
	private String vip_Password;               //会员密码
	private String vip_confirmPassword;        //会员确认密码
	private String car_Id;
	private String vipGbfee;
	
	private String car_Basinsurance_Date;      //较强险到期 
	private String car_Businsurance_Date;      //商业险到期
	private String car_Last_Repair_Date;       //最后维修日期
	private String car_Last_Maint_Date;        //最后保养日期
	private String car_Motor_Id;               //发动机号
	private String car_Buy_Date;               //购车日期
	private String car_License_Date;           //领证日期
	private String car_Last_Repair_Distance;   //里程数
	private String color_Name;                 //颜色名称
	private String custom_Id;                  //客户编号
	private String cstg_Name;                  //客户性质
	private String cst_Name;                   //客户类型
	private String custom_Name;                //客户名称
	private String custom_Birthday;
	private String custom_Addr;
	private String custom_Tel1;
	private String custom_Tel2;
	private String custom_Sex;
	private String create_Datetime;            //建档事间
	private String memo;
	private String parea_Name;                 //区域名称
	private String exit_Time;                  //挂失日期	
	private String goon_Time;                  //续会日期
	
	private String vip_Rec_Id;
	private String rec_Amount;                 //充值金额
	private String give_Amount;                //赠送金额
	private String vip_Balance1;               //总金额
	private String give_Inte;                  //赠送积分
	private String vip_Total_Integral1;
	private String operator;                   //操作员
	private String stf_Name;                   //操作员名称	
	private String vip_Rec_Note;               //充值备注
	private String vip_Rec_Date;               //充值日期
	private String vip_Rec_Date2;
	private String rec_Audit_Oper;             //充值审核员id
	private String rec_Audit_OperValue;        //充值审核员
	private String rec_Audit_Date;             //充值审核日期
	private String rec_Audit_Status;           //充值审核状态key
	private String rec_Audit_StatusValue;      //充值审核状态
	private String rec_PayType;                //付款方式
	private String payMent_Name;               //付款方式名称
	private String giv_Amount_Ratio;           //赠送金额系数
	private String give_Inte_Date;             //赠送日期
	private String give_Inte_Id;               //赠送单号
	private String give_Inte_Note;             //赠分备注
	private String give_Inte_Num;              //赠送积分数
	private String car_Flag;                   //是否是会员
	private String sls_Id;                     //供应商id
	private String gb_Fee;                     //工本费
	private String make_User;                  //经办人
	
	private String adjournment_Date;           //续会日期
	private String adjournment_Date2;          //续会日期
    private String adjournment_Integral;       //续会赠送积分
    private String adjournment_Memo;           //续会备注
    private String adjournment_Manage;         //续会经办人
    private String adjournment_ManageName;     //续会经办人
    
    private String exitmember_Date;            //退会日期
    private String exitmember_Date2;           //退会日期
    private String exitmember_Deduct;          //退会扣除积分
    private String exitmember_Memo;            //退会理由
    private String exitmember_Manage;          //退会经办人
    private String exitmember_ManageName;      //退会经办人
    
	private String enterprise_id;              //经办企业
	private String enterprise_name;            //经办企业名称
	private String enterprise_id2;             //使用企业集团
	private String enterprise_name2;           //使用企业集团名称
	private String enterprise_id3;             //经办企业
    private String enterprise_name3;           //经办企业名称
    
	public String getVip_Rec_Id() {
		return vip_Rec_Id;
	}
	public void setVip_Rec_Id(String vipRecId) {
		vip_Rec_Id = vipRecId;
	}
	public String getGb_Fee() {
		return gb_Fee;
	}
	public void setGb_Fee(String gbFee) {
		gb_Fee = gbFee;
	}
	public String getMake_User() {
		return make_User;
	}
	public void setMake_User(String makeUser) {
		make_User = makeUser;
	}
	public String getSls_Id() {
		return sls_Id;
	}
	public void setSls_Id(String slsId) {
		sls_Id = slsId;
	}
	public String getCar_Buy_Date() {
		return car_Buy_Date;
	}
	public void setCar_Buy_Date(String carBuyDate) {
		car_Buy_Date = carBuyDate;
	}
	public String getCar_License_Date() {
		return car_License_Date;
	}
	public void setCar_License_Date(String carLicenseDate) {
		car_License_Date = carLicenseDate;
	}
	public String getCstg_Name() {
		return cstg_Name;
	}
	public void setCstg_Name(String cstgName) {
		cstg_Name = cstgName;
	}
	public String getCst_Name() {
		return cst_Name;
	}
	public void setCst_Name(String cstName) {
		cst_Name = cstName;
	}

	public String getCar_Flag() {
		return car_Flag;
	}
	public void setCar_Flag(String carFlag) {
		car_Flag = carFlag;
	}
	public String getVip_Group_Name() {
		return vip_Group_Name;
	}
	public void setVip_Group_Name(String vipGroupName) {
		vip_Group_Name = vipGroupName;
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
	public String getVip_Id() {
		return vip_Id;
	}
	public void setVip_Id(String vipId) {
		vip_Id = vipId;
	}
	public String getVip_Level_Name() {
		return vip_Level_Name;
	}
	public void setVip_Level_Name(String vipLevelName) {
		vip_Level_Name = vipLevelName;
	}
	public String getVip_Number() {
		return vip_Number;
	}
	public void setVip_Number(String vipNumber) {
		vip_Number = vipNumber;
	}
	public String getVip_Integral() {
		return vip_Integral;
	}
	public void setVip_Integral(String vipIntegral) {
		vip_Integral = vipIntegral;
	}
	public String getVip_Total_Integral() {
		return vip_Total_Integral;
	}
	public void setVip_Total_Integral(String vipTotalIntegral) {
		vip_Total_Integral = vipTotalIntegral;
	}
	public String getVip_Balance() {
		return vip_Balance;
	}
	public void setVip_Balance(String vipBalance) {
		vip_Balance = vipBalance;
	}
	public String getVip_Hobby() {
		return vip_Hobby;
	}
	public void setVip_Hobby(String vipHobby) {
		vip_Hobby = vipHobby;
	}
	public String getVip_Birthday() {
		return vip_Birthday;
	}
	public void setVip_Birthday(String vipBirthday) {
		vip_Birthday = vipBirthday;
	}
	public String getVip_Tel() {
		return vip_Tel;
	}
	public void setVip_Tel(String vipTel) {
		vip_Tel = vipTel;
	}
	public String getJoin_Time() {
		return join_Time;
	}
	public void setJoin_Time(String joinTime) {
		join_Time = joinTime;
	}
	public String getEnd_Time() {
		return end_Time;
	}
	public void setEnd_Time(String endTime) {
		end_Time = endTime;
	}
	public String getVip_Status() {
		return vip_Status;
	}
	public void setVip_Status(String vipStatus) {
		vip_Status = vipStatus;
	}
	public String getVip_Name() {
		return vip_Name;
	}
	public void setVip_Name(String vipName) {
		vip_Name = vipName;
	}
	public String getCar_License() {
		return car_License;
	}
	public void setCar_License(String carLicense) {
		car_License = carLicense;
	}
	public String getCar_Last_Maint_Distance() {
		return car_Last_Maint_Distance;
	}
	public void setCar_Last_Maint_Distance(String carLastMaintDistance) {
		car_Last_Maint_Distance = carLastMaintDistance;
	}
	
	public String getCar_Vin() {
		return car_Vin;
	}
	public void setCar_Vin(String carVin) {
		car_Vin = carVin;
	}
	public String getCtype_Name() {
		return ctype_Name;
	}
	public void setCtype_Name(String ctypeName) {
		ctype_Name = ctypeName;
	}
	public String getCbrd_Name() {
		return cbrd_Name;
	}
	public void setCbrd_Name(String cbrdName) {
		cbrd_Name = cbrdName;
	}
	public String getVip_Age() {
		return vip_Age;
	}
	public void setVip_Age(String vipAge) {
		vip_Age = vipAge;
	}
	public String getVip_Level_Id() {
		return vip_Level_Id;
	}
	public void setVip_Level_Id(String vipLevelId) {
		vip_Level_Id = vipLevelId;
	}
	public String getVip_Group_Id() {
		return vip_Group_Id;
	}
	public void setVip_Group_Id(String vipGroupId) {
		vip_Group_Id = vipGroupId;
	}
	public String getVip_Password() {
		return vip_Password;
	}
	public void setVip_Password(String vipPassword) {
		vip_Password = vipPassword;
	}
	public String getCar_Id() {
		return car_Id;
	}
	public void setCar_Id(String carId) {
		car_Id = carId;
	}
	public String getCar_Basinsurance_Date() {
		return car_Basinsurance_Date;
	}
	public void setCar_Basinsurance_Date(String carBasinsuranceDate) {
		car_Basinsurance_Date = carBasinsuranceDate;
	}
	public String getCar_Businsurance_Date() {
		return car_Businsurance_Date;
	}
	public void setCar_Businsurance_Date(String carBusinsuranceDate) {
		car_Businsurance_Date = carBusinsuranceDate;
	}
	public String getCar_Last_Repair_Date() {
		return car_Last_Repair_Date;
	}
	public void setCar_Last_Repair_Date(String carLastRepairDate) {
		car_Last_Repair_Date = carLastRepairDate;
	}
	public String getCar_Last_Maint_Date() {
		return car_Last_Maint_Date;
	}
	public void setCar_Last_Maint_Date(String carLastMaintDate) {
		car_Last_Maint_Date = carLastMaintDate;
	}
	public String getCar_Motor_Id() {
		return car_Motor_Id;
	}
	public void setCar_Motor_Id(String carMotorId) {
		car_Motor_Id = carMotorId;
	}
	public String getCar_Last_Repair_Distance() {
		return car_Last_Repair_Distance;
	}
	public void setCar_Last_Repair_Distance(String carLastRepairDistance) {
		car_Last_Repair_Distance = carLastRepairDistance;
	}
	public String getColor_Name() {
		return color_Name;
	}
	public void setColor_Name(String colorName) {
		color_Name = colorName;
	}
	public String getCustom_Id() {
		return custom_Id;
	}
	public void setCustom_Id(String customId) {
		custom_Id = customId;
	}
	public String getCustom_Name() {
		return custom_Name;
	}
	public void setCustom_Name(String customName) {
		custom_Name = customName;
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
	
	public String getCustom_Sex() {
		return custom_Sex;
	}
	public void setCustom_Sex(String customSex) {
		custom_Sex = customSex;
	}
	public String getCreate_Datetime()
    {
        return create_Datetime;
    }
    public void setCreate_Datetime(String createDatetime)
    {
        create_Datetime = createDatetime;
    }
    public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getParea_Name() {
		return parea_Name;
	}
	public void setParea_Name(String pareaName) {
		parea_Name = pareaName;
	}
	public String getExit_Time() {
		return exit_Time;
	}
	public void setExit_Time(String exitTime) {
		exit_Time = exitTime;
	}
	public String getGoon_Time() {
		return goon_Time;
	}
	public void setGoon_Time(String goonTime) {
		goon_Time = goonTime;
	}
	public String getRec_Amount() {
		return rec_Amount;
	}
	public void setRec_Amount(String recAmount) {
		rec_Amount = recAmount;
	}
	public String getGive_Amount() {
		return give_Amount;
	}
	public void setGive_Amount(String giveAmount) {
		give_Amount = giveAmount;
	}
	public String getGive_Inte() {
		return give_Inte;
	}
	public void setGive_Inte(String giveInte) {
		give_Inte = giveInte;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getVip_Rec_Note() {
		return vip_Rec_Note;
	}
	public void setVip_Rec_Note(String vipRecNote) {
		vip_Rec_Note = vipRecNote;
	}
	public String getVip_Rec_Date() {
		return vip_Rec_Date;
	}
	public void setVip_Rec_Date(String vipRecDate) {
		vip_Rec_Date = vipRecDate;
	}
	public String getGiv_Amount_Ratio() {
		return giv_Amount_Ratio;
	}
	public void setGiv_Amount_Ratio(String givAmountRatio) {
		giv_Amount_Ratio = givAmountRatio;
	}
	public String getGive_Inte_Date() {
		return give_Inte_Date;
	}
	public void setGive_Inte_Date(String giveInteDate) {
		give_Inte_Date = giveInteDate;
	}
	public String getGive_Inte_Id() {
		return give_Inte_Id;
	}
	public void setGive_Inte_Id(String giveInteId) {
		give_Inte_Id = giveInteId;
	}
	public String getGive_Inte_Note() {
		return give_Inte_Note;
	}
	public void setGive_Inte_Note(String giveInteNote) {
		give_Inte_Note = giveInteNote;
	}
	public String getGive_Inte_Num() {
		return give_Inte_Num;
	}
	public void setGive_Inte_Num(String giveInteNum) {
		give_Inte_Num = giveInteNum;
	}
	public String getRec_Audit_Oper() {
		return rec_Audit_Oper;
	}
	public void setRec_Audit_Oper(String recAuditOper) {
		rec_Audit_Oper = recAuditOper;
	}
	public String getRec_Audit_Date() {
		return rec_Audit_Date;
	}
	public void setRec_Audit_Date(String recAuditDate) {
		rec_Audit_Date = recAuditDate;
	}
	public String getRec_Audit_Status() {
		return rec_Audit_Status;
	}
	public void setRec_Audit_Status(String recAuditStatus) {
		rec_Audit_Status = recAuditStatus;
	}
	public String getRec_PayType() {
		return rec_PayType;
	}
	public void setRec_PayType(String recPayType) {
		rec_PayType = recPayType;
	}
	public String getPayMent_Name() {
		return payMent_Name;
	}
	public void setPayMent_Name(String payMentName) {
		payMent_Name = payMentName;
	}
	public String getStf_Name() {
		return stf_Name;
	}
	public void setStf_Name(String stfName) {
		stf_Name = stfName;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
    public String getEnterprise_id()
    {
        return enterprise_id;
    }
    public void setEnterprise_id(String enterpriseId)
    {
        enterprise_id = enterpriseId;
    }
    public String getEnterprise_name()
    {
        return enterprise_name;
    }
    public void setEnterprise_name(String enterpriseName)
    {
        enterprise_name = enterpriseName;
    }
    public String getEnterprise_id2()
    {
        return enterprise_id2;
    }
    public void setEnterprise_id2(String enterpriseId2)
    {
        enterprise_id2 = enterpriseId2;
    }
    public String getEnterprise_name2()
    {
        return enterprise_name2;
    }
    public void setEnterprise_name2(String enterpriseName2)
    {
        enterprise_name2 = enterpriseName2;
    }
    public String getVip_confirmPassword()
    {
        return vip_confirmPassword;
    }
    public void setVip_confirmPassword(String vipConfirmPassword)
    {
        vip_confirmPassword = vipConfirmPassword;
    }
    public String getCbrl_Name()
    {
        return cbrl_Name;
    }
    public void setCbrl_Name(String cbrlName)
    {
        cbrl_Name = cbrlName;
    }
    public String getVip_OldNumber()
    {
        return vip_OldNumber;
    }
    public void setVip_OldNumber(String vipOldNumber)
    {
        vip_OldNumber = vipOldNumber;
    }
    public String getVip_Status_value()
    {
        return vip_Status_value;
    }
    public void setVip_Status_value(String vipStatusValue)
    {
        vip_Status_value = vipStatusValue;
    }
    public String getVip_Balance1()
    {
        return vip_Balance1;
    }
    public void setVip_Balance1(String vipBalance1)
    {
        vip_Balance1 = vipBalance1;
    }
    public String getVip_Total_Integral1()
    {
        return vip_Total_Integral1;
    }
    public void setVip_Total_Integral1(String vipTotalIntegral1)
    {
        vip_Total_Integral1 = vipTotalIntegral1;
    }
    public String getRec_Audit_StatusValue()
    {
        return rec_Audit_StatusValue;
    }
    public void setRec_Audit_StatusValue(String recAuditStatusValue)
    {
        rec_Audit_StatusValue = recAuditStatusValue;
    }
    public String getRec_Audit_OperValue()
    {
        return rec_Audit_OperValue;
    }
    public void setRec_Audit_OperValue(String recAuditOperValue)
    {
        rec_Audit_OperValue = recAuditOperValue;
    }
    public String getJoin_Time2()
    {
        return join_Time2;
    }
    public void setJoin_Time2(String joinTime2)
    {
        join_Time2 = joinTime2;
    }
    public String getEnd_Time2()
    {
        return end_Time2;
    }
    public void setEnd_Time2(String endTime2)
    {
        end_Time2 = endTime2;
    }
    public String getVip_Rec_Date2()
    {
        return vip_Rec_Date2;
    }
    public void setVip_Rec_Date2(String vipRecDate2)
    {
        vip_Rec_Date2 = vipRecDate2;
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
    public String getAdjournment_Integral()
    {
        return adjournment_Integral;
    }
    public void setAdjournment_Integral(String adjournmentIntegral)
    {
        adjournment_Integral = adjournmentIntegral;
    }
    public String getAdjournment_Memo()
    {
        return adjournment_Memo;
    }
    public void setAdjournment_Memo(String adjournmentMemo)
    {
        adjournment_Memo = adjournmentMemo;
    }
    public String getVip_Birthday2()
    {
        return vip_Birthday2;
    }
    public void setVip_Birthday2(String vipBirthday2)
    {
        vip_Birthday2 = vipBirthday2;
    }
    public String getVipGbfee()
    {
        return vipGbfee;
    }
    public void setVipGbfee(String vipGbfee)
    {
        this.vipGbfee = vipGbfee;
    }
    public String getAdjournment_Manage()
    {
        return adjournment_Manage;
    }
    public void setAdjournment_Manage(String adjournmentManage)
    {
        adjournment_Manage = adjournmentManage;
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
    public String getExitmember_Manage()
    {
        return exitmember_Manage;
    }
    public void setExitmember_Manage(String exitmemberManage)
    {
        exitmember_Manage = exitmemberManage;
    }
    public String getAdjournment_ManageName()
    {
        return adjournment_ManageName;
    }
    public void setAdjournment_ManageName(String adjournmentManageName)
    {
        adjournment_ManageName = adjournmentManageName;
    }
    public String getExitmember_ManageName()
    {
        return exitmember_ManageName;
    }
    public void setExitmember_ManageName(String exitmemberManageName)
    {
        exitmember_ManageName = exitmemberManageName;
    }
    public String getExitmember_Date2()
    {
        return exitmember_Date2;
    }
    public void setExitmember_Date2(String exitmemberDate2)
    {
        exitmember_Date2 = exitmemberDate2;
    }
    public String getEnterprise_id3()
    {
        return enterprise_id3;
    }
    public void setEnterprise_id3(String enterpriseId3)
    {
        enterprise_id3 = enterpriseId3;
    }
    public String getEnterprise_name3()
    {
        return enterprise_name3;
    }
    public void setEnterprise_name3(String enterpriseName3)
    {
        enterprise_name3 = enterpriseName3;
    }
}