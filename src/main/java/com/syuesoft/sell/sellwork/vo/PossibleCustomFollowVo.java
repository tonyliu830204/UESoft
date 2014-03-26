package com.syuesoft.sell.sellwork.vo;



public class PossibleCustomFollowVo {
	
	
	private Integer enterpriseId;		//企业编号
	private String state;
	private String iconCls;
	private String custom_Id;
	private String xs_Custom_Name;
	private String interview_Date;   	//预约日期
	private String interview_Date2;   	//预约日期
	private String xs_Custom_Telephone;
	private String car_Brand;
	private String xs_Custom_Deal;
	private String deal;
	private String tracing_Date;		//跟踪日期
	private String tracing_Date2;		//跟踪日期
	private String stf_Id;
	private String stf_Name;
	private String stf_Id_Person;
	private String user_Name;
	private String custom_Property;     //客户性质
	private String custom_Property_Id;
	private String xs_Custom_Hide_Level_Id;
	private String xs_Custom_Hide_Level;
	private String hinder_Content;                //故障描述
	private String xs_Contacts_Person;            //联系人
	
	private String tracing_Id;
	private String tracing_Code;				//跟踪单号
	private String tracing_Day_Number;			//要求跟踪天数
	private String tracing_Address;
	private String ambience;					//气氛	
	private String tracing_Way;					//跟踪方式
						//跟踪方式
	private String steer_Trial;					//是否是乘试驾
	private String car_Model;					//试乘试驾车型
	private String talk_Title;                 //讨论主题
	private String tracing_Summary;				//跟踪 总结
	private String next_Interview_Date;			//下次预约日期
	private String examine_Opinion;				//审批意见
	private String examine_Date;
	private String examine_Flag;					//审核状态
	private String reserve_Id;
	private String xs_Custom_Inputdata;       //建档日期
	private String xs_Custom_Inputdata2;       //建档日期
	private String reserve_Date;       			//预约日期
	private String xs_Custom_Salesman_Detail_Id;       			//来电客流登记 明细编号
	
	private String price_Need;
	private String use_Need;
	private String capability_Need;
	private String colour_Need;
	private String cai_Model_Need;
	private String payment_Way;//支付方式
	private String payment;
	private String predict_Buy_Date;
	private String predict_Buy_Date2;
	private String buy_Probability;
	private String buy_Probabilitys;
	private String probability;
	private String obstacle;
	private String bespeak_Date;
	private String need_State;
	private String need_Type;
	private String tracing;
	private String ambienceN;
	private String carModelN;
	private String carModelNa;
	private String levaJiange;
	private int rows;
	private int page;
	private String obstacleN;
	private String customCode;
	private String CarColourId;
	private String CarColour;
	private String custom_application;
	private String application;
	private String contactsPerson;
	private String times;
	private String lose_Date;
	private String lose_Date2;
	private Boolean flag;
	private String hide_Level;
	
	
	
	public String getHide_Level() {
		return hide_Level;
	}
	public void setHide_Level(String hideLevel) {
		hide_Level = hideLevel;
	}
	public String getInterview_Date2() {
		return interview_Date2;
	}
	public void setInterview_Date2(String interviewDate2) {
		interview_Date2 = interviewDate2;
	}
	public String getTracing_Date2() {
		return tracing_Date2;
	}
	public void setTracing_Date2(String tracingDate2) {
		tracing_Date2 = tracingDate2;
	}
	public String getXs_Custom_Inputdata2() {
		return xs_Custom_Inputdata2;
	}
	public void setXs_Custom_Inputdata2(String xsCustomInputdata2) {
		xs_Custom_Inputdata2 = xsCustomInputdata2;
	}
	public String getPredict_Buy_Date2() {
		return predict_Buy_Date2;
	}
	public void setPredict_Buy_Date2(String predictBuyDate2) {
		predict_Buy_Date2 = predictBuyDate2;
	}
	public String getLose_Date() {
		return lose_Date;
	}
	public void setLose_Date(String loseDate) {
		lose_Date = loseDate;
	}
	public String getLose_Date2() {
		return lose_Date2;
	}
	public void setLose_Date2(String loseDate2) {
		lose_Date2 = loseDate2;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getXs_Contacts_Person() {
		return xs_Contacts_Person;
	}
	public void setXs_Contacts_Person(String xsContactsPerson) {
		xs_Contacts_Person = xsContactsPerson;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getBuy_Probabilitys() {
		return buy_Probabilitys;
	}
	public void setBuy_Probabilitys(String buyProbabilitys) {
		buy_Probabilitys = buyProbabilitys;
	}
	public String getContactsPerson() {
		return contactsPerson;
	}
	public void setContactsPerson(String contactsPerson) {
		this.contactsPerson = contactsPerson;
	}
	public String getDeal() {
		return deal;
	}
	public void setDeal(String deal) {
		this.deal = deal;
	}
	public String getTracing() {
		return tracing;
	}
	public void setTracing(String tracing) {
		this.tracing = tracing;
	}
	
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getCarModelNa() {
		return carModelNa;
	}
	public void setCarModelNa(String carModelNa) {
		this.carModelNa = carModelNa;
	}
	public String getCarColourId() {
		return CarColourId;
	}
	public void setCarColourId(String carColourId) {
		CarColourId = carColourId;
	}
	public String getCarColour() {
		return CarColour;
	}
	public void setCarColour(String carColour) {
		CarColour = carColour;
	}
	public String getCustom_application() {
		return custom_application;
	}
	public void setCustom_application(String customApplication) {
		custom_application = customApplication;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getObstacleN() {
		return obstacleN;
	}
	public void setObstacleN(String obstacleN) {
		this.obstacleN = obstacleN;
	}
	
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getLevaJiange() {
		return levaJiange;
	}
	public void setLevaJiange(String levaJiange) {
		this.levaJiange = levaJiange;
	}
	public String getCarModelN() {
		return carModelN;
	}
	public void setCarModelN(String carModelN) {
		this.carModelN = carModelN;
	}

	
	public String getAmbienceN() {
		return ambienceN;
	}
	public void setAmbienceN(String ambienceN) {
		this.ambienceN = ambienceN;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	private String remark;
	
	private String level;
	private String levelName;
	
	
	public String getPrice_Need() {
		return price_Need;
	}
	public void setPrice_Need(String priceNeed) {
		price_Need = priceNeed;
	}
	public String getUse_Need() {
		return use_Need;
	}
	public void setUse_Need(String useNeed) {
		use_Need = useNeed;
	}
	public String getCapability_Need() {
		return capability_Need;
	}
	public void setCapability_Need(String capabilityNeed) {
		capability_Need = capabilityNeed;
	}
	public String getColour_Need() {
		return colour_Need;
	}
	public void setColour_Need(String colourNeed) {
		colour_Need = colourNeed;
	}
	public String getCai_Model_Need() {
		return cai_Model_Need;
	}
	public void setCai_Model_Need(String caiModelNeed) {
		cai_Model_Need = caiModelNeed;
	}
	public String getPayment_Way() {
		return payment_Way;
	}
	public void setPayment_Way(String paymentWay) {
		payment_Way = paymentWay;
	}
	public String getPredict_Buy_Date() {
		return predict_Buy_Date;
	}
	public void setPredict_Buy_Date(String predictBuyDate) {
		predict_Buy_Date = predictBuyDate;
	}
	public String getBuy_Probability() {
		return buy_Probability;
	}
	public void setBuy_Probability(String buyProbability) {
		buy_Probability = buyProbability;
	}
	public String getObstacle() {
		return obstacle;
	}
	public void setObstacle(String obstacle) {
		this.obstacle = obstacle;
	}
	public String getBespeak_Date() {
		return bespeak_Date;
	}
	public void setBespeak_Date(String bespeakDate) {
		bespeak_Date = bespeakDate;
	}
	public String getNeed_State() {
		return need_State;
	}
	public void setNeed_State(String needState) {
		need_State = needState;
	}
	public String getNeed_Type() {
		return need_Type;
	}
	public void setNeed_Type(String needType) {
		need_Type = needType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getXs_Custom_Salesman_Detail_Id() {
		return xs_Custom_Salesman_Detail_Id;
	}
	public void setXs_Custom_Salesman_Detail_Id(String xsCustomSalesmanDetailId) {
		xs_Custom_Salesman_Detail_Id = xsCustomSalesmanDetailId;
	}
	public String getReserve_Date() {
		return reserve_Date;
	}
	public void setReserve_Date(String reserveDate) {
		reserve_Date = reserveDate;
	}
	public String getXs_Custom_Inputdata() {
		return xs_Custom_Inputdata;
	}
	public void setXs_Custom_Inputdata(String xsCustomInputdata) {
		xs_Custom_Inputdata = xsCustomInputdata;
	}
	public String getExamine_Flag() {
		return examine_Flag;
	}
	public void setExamine_Flag(String examineFlag) {
		examine_Flag = examineFlag;
	}
	public String getReserve_Id() {
		return reserve_Id;
	}
	public void setReserve_Id(String reserveId) {
		reserve_Id = reserveId;
	}
	public String getTracing_Day_Number() {
		return tracing_Day_Number;
	}
	public void setTracing_Day_Number(String tracingDayNumber) {
		tracing_Day_Number = tracingDayNumber;
	}
	public String getAmbience() {
		return ambience;
	}
	public void setAmbience(String ambience) {
		this.ambience = ambience;
	}
	public String getTracing_Way() {
		return tracing_Way;
	}
	public void setTracing_Way(String tracingWay) {
		tracing_Way = tracingWay;
	}
	public String getSteer_Trial() {
		return steer_Trial;
	}
	public void setSteer_Trial(String steerTrial) {
		steer_Trial = steerTrial;
	}
	public String getCar_Model() {
		return car_Model;
	}
	public void setCar_Model(String carModel) {
		car_Model = carModel;
	}
	public String getNext_Interview_Date() {
		return next_Interview_Date;
	}
	public void setNext_Interview_Date(String nextInterviewDate) {
		next_Interview_Date = nextInterviewDate;
	}
	public String getExamine_Date() {
		return examine_Date;
	}
	public void setExamine_Date(String examineDate) {
		examine_Date = examineDate;
	}
	public String getTracing_Id() {
		return tracing_Id;
	}
	public void setTracing_Id(String tracingId) {
		tracing_Id = tracingId;
	}
	public String getTracing_Code() {
		return tracing_Code;
	}
	public void setTracing_Code(String tracingCode) {
		tracing_Code = tracingCode;
	}
	public String getTracing_Address() {
		return tracing_Address;
	}
	public void setTracing_Address(String tracingAddress) {
		tracing_Address = tracingAddress;
	}
	public String getTalk_Title() {
		return talk_Title;
	}
	public void setTalk_Title(String talkTitle) {
		talk_Title = talkTitle;
	}
	public String getTracing_Summary() {
		return tracing_Summary;
	}
	public void setTracing_Summary(String tracingSummary) {
		tracing_Summary = tracingSummary;
	}
	public String getExamine_Opinion() {
		return examine_Opinion;
	}
	public void setExamine_Opinion(String examineOpinion) {
		examine_Opinion = examineOpinion;
	}
	public String getCustom_Id() {
		return custom_Id;
	}
	public void setCustom_Id(String customId) {
		custom_Id = customId;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getCustom_Property_Id() {
		return custom_Property_Id;
	}
	public void setCustom_Property_Id(String customPropertyId) {
		custom_Property_Id = customPropertyId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getXs_Custom_Hide_Level_Id() {
		return xs_Custom_Hide_Level_Id;
	}
	public void setXs_Custom_Hide_Level_Id(String xsCustomHideLevelId) {
		xs_Custom_Hide_Level_Id = xsCustomHideLevelId;
	}
	public String getXs_Custom_Name() {
		return xs_Custom_Name;
	}
	public void setXs_Custom_Name(String xsCustomName) {
		xs_Custom_Name = xsCustomName;
	}
	public String getInterview_Date() {
		return interview_Date;
	}
	public void setInterview_Date(String interviewDate) {
		interview_Date = interviewDate;
	}
	public String getXs_Custom_Telephone() {
		return xs_Custom_Telephone;
	}
	public void setXs_Custom_Telephone(String xsCustomTelephone) {
		xs_Custom_Telephone = xsCustomTelephone;
	}
	public String getCar_Brand() {
		return car_Brand;
	}
	public void setCar_Brand(String carBrand) {
		car_Brand = carBrand;
	}
	public String getXs_Custom_Deal() {
		return xs_Custom_Deal;
	}
	public void setXs_Custom_Deal(String xsCustomDeal) {
		xs_Custom_Deal = xsCustomDeal;
	}
	public String getTracing_Date() {
		return tracing_Date;
	}
	public void setTracing_Date(String tracingDate) {
		tracing_Date = tracingDate;
	}
	public String getStf_Id() {
		return stf_Id;
	}
	public void setStf_Id(String stfId) {
		stf_Id = stfId;
	}
	public String getStf_Name() {
		return stf_Name;
	}
	public void setStf_Name(String stfName) {
		stf_Name = stfName;
	}
	public String getStf_Id_Person() {
		return stf_Id_Person;
	}
	public void setStf_Id_Person(String stfIdPerson) {
		stf_Id_Person = stfIdPerson;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String userName) {
		user_Name = userName;
	}
	public String getCustom_Property() {
		return custom_Property;
	}
	public void setCustom_Property(String customProperty) {
		custom_Property = customProperty;
	}
	public String getXs_Custom_Hide_Level() {
		return xs_Custom_Hide_Level;
	}
	public void setXs_Custom_Hide_Level(String xsCustomHideLevel) {
		xs_Custom_Hide_Level = xsCustomHideLevel;
	}
	public String getHinder_Content() {
		return hinder_Content;
	}
	public void setHinder_Content(String hinderContent) {
		hinder_Content = hinderContent;
	}
	
	

	  
}
