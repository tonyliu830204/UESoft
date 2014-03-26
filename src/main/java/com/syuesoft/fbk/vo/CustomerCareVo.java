package com.syuesoft.fbk.vo;

public class CustomerCareVo
{
    private int page;

    private int rows;

    private String iconCls;

    private String state;

    private String ctype_Name;// 车品牌名称
    private String ctype_Name_id;// 车品牌编号
    private String car_Id;
    private String relcampId;// 交强险保险公司
    private String busRelcamp;// 商业险保险公司
    private String car_License;// 车牌照

    private String car_Cstl_Name;// 车型号名称
    private  String  car_Cstl_id;// 车型号编号
    
    private String car_Basinsurance_Date;// 交强到期

    private String car_Businsurance_Date;// 商业到期

    private String car_Annual_Date;// 年检到期 /

    private String car_Examined_Date;// 年审到期

    private String car_Fst_Insurance_Date;// 首保日期

    private String car_Maint_Interva;// 保养间隔 预计保养日期 = 保养间隔 + 最后保养日期

    private String car_Repair_Cnt;// 维修次数

    private String car_Last_Repair_Date;// 最近维修日期

    private String car_Last_Repair_Distance;// 最近维修历程数

    private String car_Last_Maint_Date; // 最后保养日期

    private String car_Last_Maint_Distance;// 保养里程

    private String car_Distance_Per_Day;// 日均行驶里程数

    private String car_Relation_Person;// 驾驶员/联系人

    private String car_Relation_Tel1; // 车主手机

    private String car_Relation_Tel2;// 车主固话
    private String custom_tel1;//车主手机
    private String custom_tel2;//车主固话
    private String receptor;// 接待人

    private String prop_Rep_Per;// 托修人

    private String prop_Phone;// 托修人手机

    private String prop_Tel;// 托修人电话

    private String custom_Name;// 客户名称

    private String yjbydate;// 预计保养日期

    private String car_Buy_Date;// 购车日期

    private String parea_Name;// 客户地址/

    private String end_Time;// 会员到期时间

    private String return_Visit_Date; // 回访日期

    private String car_Vin;

    private String wlcDays;// 未来厂天数

    private String txhf_Date; // 提醒回访日期

    private String yjsb_Date; // 预计首保日期

    private String tc_Id; // 提醒表的id

    private String tx_Date; // 提醒日期

    private String tx_Type; // 提醒类型

    private String tx_Status; // 提醒情况

    private String shoubaotx_person; // 首保提醒员

    private String tx_Shoubao_Date; // 提醒首保日期

    private String erwei_Date; // 二维提醒 日期

    private String status_Id;// 流失状态id

    private String status_Name;// 流失原因

    private String vist_Name;// 回访内容

    private String return_Type;// 回访类型

    private String number; // 数量

    private String bili; // 比例

    private String totals;

    private String ffv_Id;

    private String g_Id;

    private String tx_Return_Visit_Date;// 醒回访日期 就是 下次跟踪日期

    private String visit_Type;

    private String visit_Content;

    private String tx_Resault; // 提醒结果

    private String ls_Quxiang;

    private String group_Name;// 提醒分类

    private String inter_Date;// 进厂日期

    private String reception_Distance;// 行驶里程

    private String preclr_Time;// 结算日期 出厂日期

    private String stf_Name;// 维修工

    private String reception_Id;// 工单号

    private String preclr_Sum_Amount;// 合计金额

    private String reptitem_Name;// 主修项目

    private String yjby_Distance; // 预计保养里程

    private String custom_Name_Addr; // 客户地址

    private String custom_Birthday; // 客户生日

    private String by_Number; // 保养次数 此为进厂次数

    private String exp_Del_Car_Time; // 出厂日期
    private String car_lost;

   private String  group_Name_value;
   private String  tx_Resault_value;
   private String car_lost_value;
   
   private String car_sum;
   private String car_percent;
   
   
   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
    public String getExp_Del_Car_Time()
    {
        return exp_Del_Car_Time;
    }

    public void setExp_Del_Car_Time(String expDelCarTime)
    {
        exp_Del_Car_Time = expDelCarTime;
    }

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

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

    public String getCtype_Name()
    {
        return ctype_Name;
    }

    public void setCtype_Name(String ctypeName)
    {
        ctype_Name = ctypeName;
    }

    public String getCar_Id()
    {
        return car_Id;
    }

    public void setCar_Id(String carId)
    {
        car_Id = carId;
    }

    public String getCar_License()
    {
        return car_License;
    }

    public void setCar_License(String carLicense)
    {
        car_License = carLicense;
    }

    public String getCar_Cstl_Name()
    {
        return car_Cstl_Name;
    }

    public void setCar_Cstl_Name(String carCstlName)
    {
        car_Cstl_Name = carCstlName;
    }

    public String getCar_Basinsurance_Date()
    {
        return car_Basinsurance_Date;
    }

    public void setCar_Basinsurance_Date(String carBasinsuranceDate)
    {
        car_Basinsurance_Date = carBasinsuranceDate;
    }

    public String getCar_Businsurance_Date()
    {
        return car_Businsurance_Date;
    }

    public void setCar_Businsurance_Date(String carBusinsuranceDate)
    {
        car_Businsurance_Date = carBusinsuranceDate;
    }

    public String getCar_Annual_Date()
    {
        return car_Annual_Date;
    }

    public void setCar_Annual_Date(String carAnnualDate)
    {
        car_Annual_Date = carAnnualDate;
    }

    public String getCar_Examined_Date()
    {
        return car_Examined_Date;
    }

    public void setCar_Examined_Date(String carExaminedDate)
    {
        car_Examined_Date = carExaminedDate;
    }

    public String getCar_Fst_Insurance_Date()
    {
        return car_Fst_Insurance_Date;
    }

    public void setCar_Fst_Insurance_Date(String carFstInsuranceDate)
    {
        car_Fst_Insurance_Date = carFstInsuranceDate;
    }

    public String getCar_Maint_Interva()
    {
        return car_Maint_Interva;
    }

    public void setCar_Maint_Interva(String carMaintInterva)
    {
        car_Maint_Interva = carMaintInterva;
    }

    public String getCar_Repair_Cnt()
    {
        return car_Repair_Cnt;
    }

    public void setCar_Repair_Cnt(String carRepairCnt)
    {
        car_Repair_Cnt = carRepairCnt;
    }

    public String getCar_Last_Repair_Date()
    {
        return car_Last_Repair_Date;
    }

    public void setCar_Last_Repair_Date(String carLastRepairDate)
    {
        car_Last_Repair_Date = carLastRepairDate;
    }

    public String getCar_Last_Repair_Distance()
    {
        return car_Last_Repair_Distance;
    }

    public void setCar_Last_Repair_Distance(String carLastRepairDistance)
    {
        car_Last_Repair_Distance = carLastRepairDistance;
    }

    public String getCar_Last_Maint_Date()
    {
        return car_Last_Maint_Date;
    }

    public void setCar_Last_Maint_Date(String carLastMaintDate)
    {
        car_Last_Maint_Date = carLastMaintDate;
    }

    public String getCar_Last_Maint_Distance()
    {
        return car_Last_Maint_Distance;
    }

    public void setCar_Last_Maint_Distance(String carLastMaintDistance)
    {
        car_Last_Maint_Distance = carLastMaintDistance;
    }

    public String getCar_Distance_Per_Day()
    {
        return car_Distance_Per_Day;
    }

    public void setCar_Distance_Per_Day(String carDistancePerDay)
    {
        car_Distance_Per_Day = carDistancePerDay;
    }

    public String getCar_Relation_Person()
    {
        return car_Relation_Person;
    }

    public void setCar_Relation_Person(String carRelationPerson)
    {
        car_Relation_Person = carRelationPerson;
    }

    public String getCar_Relation_Tel1()
    {
        return car_Relation_Tel1;
    }

    public void setCar_Relation_Tel1(String carRelationTel1)
    {
        car_Relation_Tel1 = carRelationTel1;
    }

    public String getCar_Relation_Tel2()
    {
        return car_Relation_Tel2;
    }

    public void setCar_Relation_Tel2(String carRelationTel2)
    {
        car_Relation_Tel2 = carRelationTel2;
    }

    public String getReceptor()
    {
        return receptor;
    }

    public void setReceptor(String receptor)
    {
        this.receptor = receptor;
    }

    public String getProp_Rep_Per()
    {
        return prop_Rep_Per;
    }

    public void setProp_Rep_Per(String propRepPer)
    {
        prop_Rep_Per = propRepPer;
    }

    public String getProp_Phone()
    {
        return prop_Phone;
    }

    public void setProp_Phone(String propPhone)
    {
        prop_Phone = propPhone;
    }

    public String getProp_Tel()
    {
        return prop_Tel;
    }

    public void setProp_Tel(String propTel)
    {
        prop_Tel = propTel;
    }

    public String getCustom_Name()
    {
        return custom_Name;
    }

    public void setCustom_Name(String customName)
    {
        custom_Name = customName;
    }

    public String getYjbydate()
    {
        return yjbydate;
    }

    public void setYjbydate(String yjbydate)
    {
        this.yjbydate = yjbydate;
    }

    public String getCar_Buy_Date()
    {
        return car_Buy_Date;
    }

    public void setCar_Buy_Date(String carBuyDate)
    {
        car_Buy_Date = carBuyDate;
    }

    public String getParea_Name()
    {
        return parea_Name;
    }

    public void setParea_Name(String pareaName)
    {
        parea_Name = pareaName;
    }

    public String getEnd_Time()
    {
        return end_Time;
    }

    public void setEnd_Time(String endTime)
    {
        end_Time = endTime;
    }

    public String getReturn_Visit_Date()
    {
        return return_Visit_Date;
    }

    public void setReturn_Visit_Date(String returnVisitDate)
    {
        return_Visit_Date = returnVisitDate;
    }

    public String getCar_Vin()
    {
        return car_Vin;
    }

    public void setCar_Vin(String carVin)
    {
        car_Vin = carVin;
    }

    public String getWlcDays()
    {
        return wlcDays;
    }

    public void setWlcDays(String wlcDays)
    {
        this.wlcDays = wlcDays;
    }

    public String getTxhf_Date()
    {
        return txhf_Date;
    }

    public void setTxhf_Date(String txhfDate)
    {
        txhf_Date = txhfDate;
    }

    public String getYjsb_Date()
    {
        return yjsb_Date;
    }

    public void setYjsb_Date(String yjsbDate)
    {
        yjsb_Date = yjsbDate;
    }

    public String getTc_Id()
    {
        return tc_Id;
    }

    public void setTc_Id(String tcId)
    {
        tc_Id = tcId;
    }

    public String getTx_Date()
    {
        return tx_Date;
    }

    public void setTx_Date(String txDate)
    {
        tx_Date = txDate;
    }

    public String getTx_Type()
    {
        return tx_Type;
    }

    public void setTx_Type(String txType)
    {
        tx_Type = txType;
    }

    public String getTx_Status()
    {
        return tx_Status;
    }

    public void setTx_Status(String txStatus)
    {
        tx_Status = txStatus;
    }

    public String getShoubaotx_person()
    {
        return shoubaotx_person;
    }

    public void setShoubaotx_person(String shoubaotxPerson)
    {
        shoubaotx_person = shoubaotxPerson;
    }

    public String getTx_Shoubao_Date()
    {
        return tx_Shoubao_Date;
    }

    public void setTx_Shoubao_Date(String txShoubaoDate)
    {
        tx_Shoubao_Date = txShoubaoDate;
    }

    public String getErwei_Date()
    {
        return erwei_Date;
    }

    public void setErwei_Date(String erweiDate)
    {
        erwei_Date = erweiDate;
    }

    public String getStatus_Id()
    {
        return status_Id;
    }

    public void setStatus_Id(String statusId)
    {
        status_Id = statusId;
    }

    public String getStatus_Name()
    {
        return status_Name;
    }

    public void setStatus_Name(String statusName)
    {
        status_Name = statusName;
    }

    public String getVist_Name()
    {
        return vist_Name;
    }

    public void setVist_Name(String vistName)
    {
        vist_Name = vistName;
    }

    public String getReturn_Type()
    {
        return return_Type;
    }

    public void setReturn_Type(String returnType)
    {
        return_Type = returnType;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getBili()
    {
        return bili;
    }

    public void setBili(String bili)
    {
        this.bili = bili;
    }

    public String getTotals()
    {
        return totals;
    }

    public void setTotals(String totals)
    {
        this.totals = totals;
    }

    public String getFfv_Id()
    {
        return ffv_Id;
    }

    public void setFfv_Id(String ffvId)
    {
        ffv_Id = ffvId;
    }

    public String getG_Id()
    {
        return g_Id;
    }

    public void setG_Id(String gId)
    {
        g_Id = gId;
    }

    public String getTx_Return_Visit_Date()
    {
        return tx_Return_Visit_Date;
    }

    public void setTx_Return_Visit_Date(String txReturnVisitDate)
    {
        tx_Return_Visit_Date = txReturnVisitDate;
    }

    public String getVisit_Type()
    {
        return visit_Type;
    }

    public void setVisit_Type(String visitType)
    {
        visit_Type = visitType;
    }

    public String getVisit_Content()
    {
        return visit_Content;
    }

    public void setVisit_Content(String visitContent)
    {
        visit_Content = visitContent;
    }

    public String getTx_Resault()
    {
        return tx_Resault;
    }

    public void setTx_Resault(String txResault)
    {
        tx_Resault = txResault;
    }

    public String getLs_Quxiang()
    {
        return ls_Quxiang;
    }

    public void setLs_Quxiang(String lsQuxiang)
    {
        ls_Quxiang = lsQuxiang;
    }

    public String getGroup_Name()
    {
        return group_Name;
    }

    public void setGroup_Name(String groupName)
    {
        group_Name = groupName;
    }

    public String getInter_Date()
    {
        return inter_Date;
    }

    public void setInter_Date(String interDate)
    {
        inter_Date = interDate;
    }

    public String getReception_Distance()
    {
        return reception_Distance;
    }

    public void setReception_Distance(String receptionDistance)
    {
        reception_Distance = receptionDistance;
    }

    public String getPreclr_Time()
    {
        return preclr_Time;
    }

    public void setPreclr_Time(String preclrTime)
    {
        preclr_Time = preclrTime;
    }

    public String getStf_Name()
    {
        return stf_Name;
    }

    public void setStf_Name(String stfName)
    {
        stf_Name = stfName;
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

    public String getReptitem_Name()
    {
        return reptitem_Name;
    }

    public void setReptitem_Name(String reptitemName)
    {
        reptitem_Name = reptitemName;
    }

    public String getYjby_Distance()
    {
        return yjby_Distance;
    }

    public void setYjby_Distance(String yjbyDistance)
    {
        yjby_Distance = yjbyDistance;
    }

    public String getCustom_Name_Addr()
    {
        return custom_Name_Addr;
    }

    public void setCustom_Name_Addr(String customNameAddr)
    {
        custom_Name_Addr = customNameAddr;
    }

    public String getCustom_Birthday()
    {
        return custom_Birthday;
    }

    public void setCustom_Birthday(String customBirthday)
    {
        custom_Birthday = customBirthday;
    }

    public String getBy_Number()
    {
        return by_Number;
    }

    public void setBy_Number(String byNumber)
    {
        by_Number = byNumber;
    }

	public String getCtype_Name_id() {
		return ctype_Name_id;
	}

	public void setCtype_Name_id(String ctypeNameId) {
		ctype_Name_id = ctypeNameId;
	}

	public String getCar_Cstl_id() {
		return car_Cstl_id;
	}

	public void setCar_Cstl_id(String carCstlId) {
		car_Cstl_id = carCstlId;
	}

	public String getCustom_tel1() {
		return custom_tel1;
	}

	public void setCustom_tel1(String customTel1) {
		custom_tel1 = customTel1;
	}

	public String getCustom_tel2() {
		return custom_tel2;
	}

	public void setCustom_tel2(String customTel2) {
		custom_tel2 = customTel2;
	}

	public String getCar_lost() {
		return car_lost;
	}

	public void setCar_lost(String carLost) {
		car_lost = carLost;
	}

	public String getGroup_Name_value() {
		return group_Name_value;
	}

	public void setGroup_Name_value(String groupNameValue) {
		group_Name_value = groupNameValue;
	}

	public String getTx_Resault_value() {
		return tx_Resault_value;
	}

	public void setTx_Resault_value(String txResaultValue) {
		tx_Resault_value = txResaultValue;
	}

	public String getCar_lost_value() {
		return car_lost_value;
	}

	public void setCar_lost_value(String carLostValue) {
		car_lost_value = carLostValue;
	}

	public String getRelcampId() {
		return relcampId;
	}

	public void setRelcampId(String relcampId) {
		this.relcampId = relcampId;
	}

	public String getBusRelcamp() {
		return busRelcamp;
	}

	public void setBusRelcamp(String busRelcamp) {
		this.busRelcamp = busRelcamp;
	}

	public String getCar_sum() {
		return car_sum;
	}

	public void setCar_sum(String carSum) {
		car_sum = carSum;
	}

	public String getCar_percent() {
		return car_percent;
	}

	public void setCar_percent(String carPercent) {
		car_percent = carPercent;
	}
    
	
}
