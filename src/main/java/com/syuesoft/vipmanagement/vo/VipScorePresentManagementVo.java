package com.syuesoft.vipmanagement.vo;

public class VipScorePresentManagementVo {
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private String vip_Level_Id;//会员等级
	private String vip_Level_Name;//会员等级
	private String vip_Group_Id;//分组名称
	private String vip_Group_Name;//分组名称
	private String vip_Number;//会员卡号
	private String vip_Integral;//可用积分 ////当前积分
	private String vip_Total_Integral;//累计积分
	private String vip_Balance;//会员卡余额 
	private String vip_Id;//会员编号 
	private String join_Time;
	private String end_Time;//
	private String end_Time2;//
	private String vip_Status;//会员状态
	private String vip_Status_value;//会员状态
	private String car_License;
	private String car_Vin;
	private String ctype_Name;
	private String cbrd_Name;
	private String car_Id;
	private String vip_Name;//客户名称
	private String vip_Birthday;
	private String vip_Tel;
	private String vip_Age;
	private String projectItem;
	
	private Integer vip_rec_id; //充值编号
	private String rec_Amount;//充值金额
	private String give_Amount;//赠送金额
	private String give_Inte; // 怎送积分
	private String operator;  // 操作员
	private String operatorName;  // 操作员
	private String vip_Rec_Note; // 充值备注
	private String give_Inte_Date;  //赠送日期
	private String give_Inte_Date2;  //赠送日期
	private String give_Inte_Id;   //赠送单号
	private String give_Inte_Note; //赠分备注
	private String shenhe_Qingkuang; //审核情况
	private String shenhe_QingkuangName; //审核情况
	private String shenhe_Riqi;    //审核日期
	private String shenhe_Yuan;   // 审核员
	private String shenhe_YuanName;   // 审核员
	
	private String give_Inte_Pro_Id;  //赠送积分项目id
    private String give_Inte_Pro_Name;  // 赠送积分项目名称
    private String give_Inte_Num;   //赠送积分
    private String shiji_Zengfen;  //实际赠分
    private String sum;           //总积分数
	
	public String getVip_Age() {
		return vip_Age;
	}
	public void setVip_Age(String vipAge) {
		vip_Age = vipAge;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public Integer getVip_rec_id() {
		return vip_rec_id;
	}
	public void setVip_rec_id(Integer vipRecId) {
		vip_rec_id = vipRecId;
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
	public String getVip_Id() {
		return vip_Id;
	}
	public void setVip_Id(String vipId) {
		vip_Id = vipId;
	}

	public String getGive_Inte_Pro_Id() {
		return give_Inte_Pro_Id;
	}
	public void setGive_Inte_Pro_Id(String giveInteProId) {
		give_Inte_Pro_Id = giveInteProId;
	}
	public String getGive_Inte_Pro_Name() {
		return give_Inte_Pro_Name;
	}
	public void setGive_Inte_Pro_Name(String giveInteProName) {
		give_Inte_Pro_Name = giveInteProName;
	}
	public String getGive_Inte_Num() {
		return give_Inte_Num;
	}
	public void setGive_Inte_Num(String giveInteNum) {
		give_Inte_Num = giveInteNum;
	}
	public String getShiji_Zengfen() {
		return shiji_Zengfen;
	}
	public void setShiji_Zengfen(String shijiZengfen) {
		shiji_Zengfen = shijiZengfen;
	}
	public String getShenhe_Qingkuang() {
		return shenhe_Qingkuang;
	}
	public void setShenhe_Qingkuang(String shenheQingkuang) {
		shenhe_Qingkuang = shenheQingkuang;
	}
	public String getShenhe_Riqi() {
		return shenhe_Riqi;
	}
	public void setShenhe_Riqi(String shenheRiqi) {
		shenhe_Riqi = shenheRiqi;
	}
	public String getShenhe_Yuan() {
		return shenhe_Yuan;
	}
	public void setShenhe_Yuan(String shenheYuan) {
		shenhe_Yuan = shenheYuan;
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
	public String getVip_Group_Name() {
		return vip_Group_Name;
	}
	public void setVip_Group_Name(String vipGroupName) {
		vip_Group_Name = vipGroupName;
	}
	public String getCar_Id() {
		return car_Id;
	}
	public void setCar_Id(String carId) {
		car_Id = carId;
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
    public String getVip_Level_Id()
    {
        return vip_Level_Id;
    }
    public void setVip_Level_Id(String vipLevelId)
    {
        vip_Level_Id = vipLevelId;
    }
    public String getVip_Group_Id()
    {
        return vip_Group_Id;
    }
    public void setVip_Group_Id(String vipGroupId)
    {
        vip_Group_Id = vipGroupId;
    }
    public String getOperatorName()
    {
        return operatorName;
    }
    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }
    public String getVip_Status_value()
    {
        return vip_Status_value;
    }
    public void setVip_Status_value(String vipStatusValue)
    {
        vip_Status_value = vipStatusValue;
    }
    public String getShenhe_QingkuangName()
    {
        return shenhe_QingkuangName;
    }
    public void setShenhe_QingkuangName(String shenheQingkuangName)
    {
        shenhe_QingkuangName = shenheQingkuangName;
    }
    public String getGive_Inte_Date2()
    {
        return give_Inte_Date2;
    }
    public void setGive_Inte_Date2(String giveInteDate2)
    {
        give_Inte_Date2 = giveInteDate2;
    }
    public String getEnd_Time2()
    {
        return end_Time2;
    }
    public void setEnd_Time2(String endTime2)
    {
        end_Time2 = endTime2;
    }
    public String getProjectItem()
    {
        return projectItem;
    }
    public void setProjectItem(String projectItem)
    {
        this.projectItem = projectItem;
    }
    public String getShenhe_YuanName()
    {
        return shenhe_YuanName;
    }
    public void setShenhe_YuanName(String shenheYuanName)
    {
        shenhe_YuanName = shenheYuanName;
    }
}