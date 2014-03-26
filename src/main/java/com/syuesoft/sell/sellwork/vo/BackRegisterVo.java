package com.syuesoft.sell.sellwork.vo;

public class BackRegisterVo {
		
	private Boolean flag;   
	private String inserted;   
	private String deleted;
	private String updated;
	private int page;
	private int rows;
	private int opt;
	private int hours;
	
	private String xs_Custom_Salesman_Detail_Id;
	private String xs_Custom_Salesman_Id;
	private String man_Num;
	private String woman_Num;
	private String custom_Name;
	private String telephone;
	private String first_Ormany;
	private String car_Brand;    			//预购品牌
	private String car_Brand_Name;    		//预购品牌
	private String car_Model;    			//预购车型
	private String car_Model_Name;    		//预购车型
	private String talk_Content;
	private String custom_Level;  			//潜在客户级别编号
	private String custom_Level_Name;  		//潜在客户级别
	private String message_Channel_Name; 	//渠道
	private String message_Channel;  		//渠道编号
	private String talk_Way_Name;
	private String talk_Way;       			//洽谈方式
	private String istestdrive;
	private String testdrive_Model_Name;
	private String testdrive_Model; 		//是乘试驾车型
	private String register_State;
	private String remark;
	private String visit_Status;
	
	private String register_Date;  			//登记日期
	private String register_Date2;  			//登记日期
	private String exit_Date;  				//离店时间
	private String stf_Id;
	private String weather;
	
	private String total;
	private String stf_Name; 				//员工姓名
	private String dept_Name; 				//部门名称
	private String tracing_State; 			//跟踪状态
	private String custom_Id; 				//客户编号
	private String xs_Custom_Telephone; 	//客户手机
	private String xs_Custom_Source; 		//渠道
	private String xs_Custom_Address; 		//地址
	private String xs_Custom_Inputdata; 	//建档日期
	private String register;
	private String xs_Custom_Deal;			//成交状态
	private String abandreasonid;			//放弃跟踪原因id
	private Integer enterpriseId;			//企业编号
	private String operate;					//操作
	private String inData;
	private String inData2;
	
	public String getInData2() {
		return inData2;
	}
	public void setInData2(String inData2) {
		this.inData2 = inData2;
	}
	public String getRegister_Date2() {
		return register_Date2;
	}
	public void setRegister_Date2(String registerDate2) {
		register_Date2 = registerDate2;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
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
	public String getCar_Brand_Name() {
		return car_Brand_Name;
	}
	public void setCar_Brand_Name(String carBrandName) {
		car_Brand_Name = carBrandName;
	}
	public String getAbandreasonid() {
		return abandreasonid;
	}
	public void setAbandreasonid(String abandreasonid) {
		this.abandreasonid = abandreasonid;
	}
	public String getCar_Brand() {
		return car_Brand;
	}
	public void setCar_Brand(String carBrand) {
		car_Brand = carBrand;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	
	public String getInData() {
		return inData;
	}
	public void setInData(String inData) {
		this.inData = inData;
	}
	public String getXs_Custom_Deal() {
		return xs_Custom_Deal;
	}
	public void setXs_Custom_Deal(String xsCustomDeal) {
		xs_Custom_Deal = xsCustomDeal;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getExit_Date() {
		return exit_Date;
	}
	public void setExit_Date(String exitDate) {
		exit_Date = exitDate;
	}
	public int getOpt() {
		return opt;
	}
	public void setOpt(int opt) {
		this.opt = opt;
	}
	public String getCar_Model_Name() {
		return car_Model_Name;
	}
	public void setCar_Model_Name(String carModelName) {
		car_Model_Name = carModelName;
	}
	public String getCustom_Level_Name() {
		return custom_Level_Name;
	}
	public void setCustom_Level_Name(String customLevelName) {
		custom_Level_Name = customLevelName;
	}
	public String getMessage_Channel_Name() {
		return message_Channel_Name;
	}
	public void setMessage_Channel_Name(String messageChannelName) {
		message_Channel_Name = messageChannelName;
	}
	public String getTalk_Way_Name() {
		return talk_Way_Name;
	}
	public void setTalk_Way_Name(String talkWayName) {
		talk_Way_Name = talkWayName;
	}
	public String getTestdrive_Model_Name() {
		return testdrive_Model_Name;
	}
	public void setTestdrive_Model_Name(String testdriveModelName) {
		testdrive_Model_Name = testdriveModelName;
	}
	public String getXs_Custom_Inputdata() {
		return xs_Custom_Inputdata;
	}
	public void setXs_Custom_Inputdata(String xsCustomInputdata) {
		xs_Custom_Inputdata = xsCustomInputdata;
	}
	public String getXs_Custom_Address() {
		return xs_Custom_Address;
	}
	public void setXs_Custom_Address(String xsCustomAddress) {
		xs_Custom_Address = xsCustomAddress;
	}
	public String getXs_Custom_Source() {
		return xs_Custom_Source;
	}
	public void setXs_Custom_Source(String xsCustomSource) {
		xs_Custom_Source = xsCustomSource;
	}
	public String getXs_Custom_Telephone() {
		return xs_Custom_Telephone;
	}
	public void setXs_Custom_Telephone(String xsCustomTelephone) {
		xs_Custom_Telephone = xsCustomTelephone;
	}
	public String getCustom_Id() {
		return custom_Id;
	}
	public void setCustom_Id(String customId) {
		custom_Id = customId;
	}
	public String getTracing_State() {
		return tracing_State;
	}
	public void setTracing_State(String tracingState) {
		tracing_State = tracingState;
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
	public String getStf_Name() {
		return stf_Name;
	}
	public void setStf_Name(String stfName) {
		stf_Name = stfName;
	}
	public String getDept_Name() {
		return dept_Name;
	}
	public void setDept_Name(String deptName) {
		dept_Name = deptName;
	}
	public String getInserted() {
		return inserted;
	}
	public void setInserted(String inserted) {
		this.inserted = inserted;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getXs_Custom_Salesman_Detail_Id() {
		return xs_Custom_Salesman_Detail_Id;
	}
	public void setXs_Custom_Salesman_Detail_Id(String xsCustomSalesmanDetailId) {
		xs_Custom_Salesman_Detail_Id = xsCustomSalesmanDetailId;
	}
	public String getXs_Custom_Salesman_Id() {
		return xs_Custom_Salesman_Id;
	}
	public void setXs_Custom_Salesman_Id(String xsCustomSalesmanId) {
		xs_Custom_Salesman_Id = xsCustomSalesmanId;
	}
	public String getMan_Num() {
		return man_Num;
	}
	public void setMan_Num(String manNum) {
		man_Num = manNum;
	}
	public String getWoman_Num() {
		return woman_Num;
	}
	public void setWoman_Num(String womanNum) {
		woman_Num = womanNum;
	}
	public String getCustom_Name() {
		return custom_Name;
	}
	public void setCustom_Name(String customName) {
		custom_Name = customName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFirst_Ormany() {
		return first_Ormany;
	}
	public void setFirst_Ormany(String firstOrmany) {
		first_Ormany = firstOrmany;
	}
	public String getCar_Model() {
		return car_Model;
	}
	public void setCar_Model(String carModel) {
		car_Model = carModel;
	}
	public String getTalk_Content() {
		return talk_Content;
	}
	public void setTalk_Content(String talkContent) {
		talk_Content = talkContent;
	}
	public String getCustom_Level() {
		return custom_Level;
	}
	public void setCustom_Level(String customLevel) {
		custom_Level = customLevel;
	}
	public String getMessage_Channel() {
		return message_Channel;
	}
	public void setMessage_Channel(String messageChannel) {
		message_Channel = messageChannel;
	}
	public String getTalk_Way() {
		return talk_Way;
	}
	public void setTalk_Way(String talkWay) {
		talk_Way = talkWay;
	}
	public String getIstestdrive() {
		return istestdrive;
	}
	public void setIstestdrive(String istestdrive) {
		this.istestdrive = istestdrive;
	}
	public String getTestdrive_Model() {
		return testdrive_Model;
	}
	public void setTestdrive_Model(String testdriveModel) {
		testdrive_Model = testdriveModel;
	}
	public String getRegister_State() {
		return register_State;
	}
	public void setRegister_State(String registerState) {
		register_State = registerState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String getVisit_Status() {
		return visit_Status;
	}
	public void setVisit_Status(String visitStatus) {
		visit_Status = visitStatus;
	}
	public String getRegister_Date() {
		return register_Date;
	}
	public void setRegister_Date(String registerDate) {
		register_Date = registerDate;
	}
	
	public String getStf_Id() {
		return stf_Id;
	}
	public void setStf_Id(String stfId) {
		stf_Id = stfId;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
	
	
}
