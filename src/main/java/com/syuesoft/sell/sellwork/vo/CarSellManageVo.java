package com.syuesoft.sell.sellwork.vo;

import java.util.Date;

import com.syuesoft.sell.model.XsCarSellInfo;


	public class CarSellManageVo {
	
	private Integer enterpriseId;
	private String inserted;
	private String updated;
	private int page;
	private int rows;
	private String xs_Car_Cw_Info_Id; 	//车辆财务信息编号
	
	private String xs_Car_Id; 			//车辆编号
	private String A_Amount;           	//按揭金额
	private String aply_Amount;        	//预收款金额
	private String insurance_Amount;   	//保险金额
	private String project_Amount;    	//代办项目金额
	private String ornament_Amount;    	//装潢金额
	private String J_Classify;          //结算分类
	private String examinePerson;             //审核人
	private String examine;             //审核
	private String remark;              //备注
	private String xs_Car_Sel_Id;				//车辆销售信息编号
	private String reserve_Id;					//预定主键
	private String reserve_Code;				//预定单编号
	private String xs_Car_Sel_Data;				//销售日期
	private String xs_Car_Sel_Data2;			//销售日期
	private String xs_Car_Sel_Transaction_Money;//销售价格
	private String payment_Way;					//付款方式	
	private String payment_Amount;				//预付金额
	private String xs_Car_Give_Up;				//是否放弃购车
	private String xs_Custom_Receiptor;			//提车人
	private String xs_Car_Sel_Type;				//销售分类	
	private String xs_Car_Sel_TypeName;				//销售分类名称	
	private String apply_Sum;					//应收汇总	
	private String cost_Sum;					//成本合计
	private String xs_Car_Sel_Remark;			//备注
	private String xs_Car_LicenseName;
	private String xs_Car_LicensePlate;
	private String xs_Custom_CodeCard;
	private String xs_Custom_Source;			//信息来源
	
	private String xs_Custom_Source_Name;			//信息来源名称
	private String xs_Model_CostPrice;
	private String xs_Model_SalesPrice;
	private String xs_Model_SalesLimitPrice;
	
	private String id;						//编号
	private String db_Project_Id;			//代办项目编号
	private String db_Project_Cost;			//代办费用
	private String cost_Price;				//成本金额
	private String print;					//打印
	
	private String numbers;					//组织代码
	private String id_Numbers;				//身份证号码
	private String zip_Code;				//邮政编码
	private String linkman;					//联系人
	private String car_Color;				//车辆颜色
	private String car_Color_Name;				//车辆颜色名称
	private String xs_Car_InteriorColor;	//内饰色id
	private String xs_Car_InteriorColor_Name;//内饰色名称
	private String car_Vin_Number; 		//
	private String car_Brand_Id; 			//车辆品牌编号
	private String car_Brand_Name; 			//车辆品牌名称
	private String xs_Car_Model_Id;			//车辆型号编号
	private String xs_Car_Model;			//车辆型号
	private String card_Color;				//号牌底色	
	private String vin;						//车架号
	private String engine_Number;			//发动机号
	private String outprint;				//排量/功率
	private String xs_Car_RideLimit_Number;	//限乘人数
	private String xs_Car_Price;			//销售标准价格
	
	private String zh_Project;				//装潢项目
	private String unit_Num;				//件数
	private String selL_Price;				//销售价格
	private String preferential_Price;		//优惠价格
	private String decorate_Sell;			//装潢销售
	private String decorate_Amount;			//装潢成本
	private String payment_Money;			//预收金额
	
	private String xs_Car_Vin_Number;		//vin
	private String xs_Car_Motor_Number;			
	private String xs_Car_Brand;			
	private String xs_Car_Brand_Name;			
	private String xs_Model_Id;			
	private String xs_Model_Name;			
	private String xs_Car_Ocn;			
	private String xs_Car_Color_Name;			
	private String xs_Car_Color;			
	private String car_Trim;			
	private String xs_Custom_Name;			
	private String xs_Custom_Phone;
	private String xs_Distributor_Name;	 		//分销商		
	private String instorehouse_Date;	 		//出库日期
	private String user_Name;	 				//经办人
	private String stf_Name;	 				//业务员
	private String stf_Id;	 					//业务员编号
	private String limit_load_num;				//限乘人数	
	private String mobilephone;					//电话
	private String out_Id;						//出库单号
	private String child_Id;					//
	private String check_Comtent;				//PDI检测内容
	private String status;						//PDI检测状态
	private String status_Name;						//PDI检测状态名称
	
	private String Pid;							//PDI检测id
	private String check_Date;					//检测日期
	private String check_Date2;					//检测日期
	private String check_Person;				//检测人
	
	private String zhProjecRemark;				//装潢备注
	
	private String iszhProject;
	public String getCheck_Date2() {
		return check_Date2;
	}
	public void setCheck_Date2(String checkDate2) {
		check_Date2 = checkDate2;
	}
	private String carLicense_Name;				//厂牌名称
	private String carLicense_Plate;			//车牌照
	private String carMotor_Number;				//发动机号
	private String xs_Distributor_Id;			//分销商编号
	private String xs_Custom_Id;				//客户编号
	
	private String xs_Car_Stf_Id;			//代办人
	private String xs_Car_Stf_Name;			//代办人
	
	private String xs_Custom_Code_Card;		//代码证
	private String xs_Custom_Telephone;			//电话
	private String xs_Contacts_Person;		//客户联系人
	private String xs_Custom_Address;		//地址
	private String xs_Custom_Zipcode;		//邮编
	private String Xs_Custom_Credentials;	//证件号码
	private String sell_Code;				//销售编号
	private String xs_Custom_Hide_Level;	//客户级别
	private String xs_Custom_Property;		//客户类型

	private String xs_Custom_Hide_Level_Name;	//客户级别名称
	private String xs_Custom_Property_Name;		//客户类型名称
	
	
	private Date  consult_Plan_Date;		//计划回访日期
	private Integer   consult_Rate ;		//回访进度
	private String   xs_Custom_Code ;		//客户编号
	private  String invoice_date;			//保险日期
	private  String invoice_date2;			//保险日期
	private  String invoice_parce;			//保险金额
	private String state;
	private String iconCls;
	private String retreat_date;			//出库日期
	private String retreat_date2;			//出库日期
	private String carCode;
	private String xs_car_type;
	private String deptId;
	
	private String year_;		//按揭年限
	private String balance_;		//按揭余额
	private String first_Payment;	//首付款
	private String loan_Bank;		//按揭银行
	
	private Boolean isCarOut;		//判断是否出库
	
	private String invoice_reckoning;	//转结算
	
	private Boolean flag;	
	private String queryCarModel;
	
	
	
	public String getQueryCarModel() {
		return queryCarModel;
	}
	public void setQueryCarModel(String queryCarModel) {
		this.queryCarModel = queryCarModel;
	}
	public String getXs_Car_Stf_Name() {
		return xs_Car_Stf_Name;
	}
	public void setXs_Car_Stf_Name(String xsCarStfName) {
		xs_Car_Stf_Name = xsCarStfName;
	}
	public String getXs_Car_Sel_Data2() {
		return xs_Car_Sel_Data2;
	}
	public void setXs_Car_Sel_Data2(String xsCarSelData2) {
		xs_Car_Sel_Data2 = xsCarSelData2;
	}
	public String getInvoice_date2() {
		return invoice_date2;
	}
	public void setInvoice_date2(String invoiceDate2) {
		invoice_date2 = invoiceDate2;
	}
	public String getRetreat_date2() {
		return retreat_date2;
	}
	public void setRetreat_date2(String retreatDate2) {
		retreat_date2 = retreatDate2;
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getXs_car_type() {
		return xs_car_type;
	}
	public void setXs_car_type(String xsCarType) {
		xs_car_type = xsCarType;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getRetreat_date() {
		return retreat_date;
	}
	public void setRetreat_date(String retreatDate) {
		retreat_date = retreatDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(String invoiceDate) {
		invoice_date = invoiceDate;
	}
	public String getInvoice_parce() {
		return invoice_parce;
	}
	public void setInvoice_parce(String invoiceParce) {
		invoice_parce = invoiceParce;
	}
	public String getXs_Car_LicenseName() {
		return xs_Car_LicenseName;
	}
	public void setXs_Car_LicenseName(String xsCarLicenseName) {
		xs_Car_LicenseName = xsCarLicenseName;
	}
	public String getXs_Car_LicensePlate() {
		return xs_Car_LicensePlate;
	}
	public void setXs_Car_LicensePlate(String xsCarLicensePlate) {
		xs_Car_LicensePlate = xsCarLicensePlate;
	}
	public String getXs_Custom_CodeCard() {
		return xs_Custom_CodeCard;
	}
	public void setXs_Custom_CodeCard(String xsCustomCodeCard) {
		xs_Custom_CodeCard = xsCustomCodeCard;
	}
	public String getXs_Custom_Source() {
		return xs_Custom_Source;
	}
	public void setXs_Custom_Source(String xsCustomSource) {
		xs_Custom_Source = xsCustomSource;
	}
	public String getXs_Model_CostPrice() {
		return xs_Model_CostPrice;
	}
	public void setXs_Model_CostPrice(String xsModelCostPrice) {
		xs_Model_CostPrice = xsModelCostPrice;
	}
	public String getXs_Model_SalesPrice() {
		return xs_Model_SalesPrice;
	}
	public void setXs_Model_SalesPrice(String xsModelSalesPrice) {
		xs_Model_SalesPrice = xsModelSalesPrice;
	}
	public String getXs_Model_SalesLimitPrice() {
		return xs_Model_SalesLimitPrice;
	}
	public void setXs_Model_SalesLimitPrice(String xsModelSalesLimitPrice) {
		xs_Model_SalesLimitPrice = xsModelSalesLimitPrice;
	}
	public String getXs_Car_RideLimit_Number() {
		return xs_Car_RideLimit_Number;
	}
	public void setXs_Car_RideLimit_Number(String xsCarRideLimitNumber) {
		xs_Car_RideLimit_Number = xsCarRideLimitNumber;
	}
	public String getXs_Car_Price() {
		return xs_Car_Price;
	}
	public void setXs_Car_Price(String xsCarPrice) {
		xs_Car_Price = xsCarPrice;
	}
	public String getXs_Car_InteriorColor() {
		return xs_Car_InteriorColor;
	}
	public void setXs_Car_InteriorColor(String xsCarInteriorColor) {
		xs_Car_InteriorColor = xsCarInteriorColor;
	}
	public String getXs_Car_InteriorColor_Name() {
		return xs_Car_InteriorColor_Name;
	}
	public void setXs_Car_InteriorColor_Name(String xsCarInteriorColorName) {
		xs_Car_InteriorColor_Name = xsCarInteriorColorName;
	}
	public String getXs_Car_Brand_Name() {
		return xs_Car_Brand_Name;
	}
	public void setXs_Car_Brand_Name(String xsCarBrandName) {
		xs_Car_Brand_Name = xsCarBrandName;
	}
	public String getXs_Model_Id() {
		return xs_Model_Id;
	}
	public void setXs_Model_Id(String xsModelId) {
		xs_Model_Id = xsModelId;
	}
	public String getXs_Car_Color_Name() {
		return xs_Car_Color_Name;
	}
	public void setXs_Car_Color_Name(String xsCarColorName) {
		xs_Car_Color_Name = xsCarColorName;
	}
	public String getCar_Brand_Id() {
		return car_Brand_Id;
	}
	public void setCar_Brand_Id(String carBrandId) {
		car_Brand_Id = carBrandId;
	}
	
	public String getCar_Vin_Number() {
		return car_Vin_Number;
	}
	public void setCar_Vin_Number(String carVinNumber) {
		car_Vin_Number = carVinNumber;
	}
	
	public String getCheck_Date() {
		return check_Date;
	}
	public void setCheck_Date(String checkDate) {
		check_Date = checkDate;
	}
	public String getCheck_Person() {
		return check_Person;
	}
	public void setCheck_Person(String checkPerson) {
		check_Person = checkPerson;
	}
	public String getXs_Custom_Code() {
		return xs_Custom_Code;
	}
	public void setXs_Custom_Code(String xsCustomCode) {
		xs_Custom_Code = xsCustomCode;
	}
	public Date getConsult_Plan_Date() {
		return consult_Plan_Date;
	}
	public void setConsult_Plan_Date(Date consultPlanDate) {
		consult_Plan_Date = consultPlanDate;
	}
	public Integer getConsult_Rate() {
		return consult_Rate;
	}
	public void setConsult_Rate(Integer consultRate) {
		consult_Rate = consultRate;
	}
	public String getXs_Custom_Property() {
		return xs_Custom_Property;
	}
	public void setXs_Custom_Property(String xsCustomProperty) {
		xs_Custom_Property = xsCustomProperty;
	}
	public String getXs_Custom_Hide_Level() {
		return xs_Custom_Hide_Level;
	}
	public void setXs_Custom_Hide_Level(String xsCustomHideLevel) {
		xs_Custom_Hide_Level = xsCustomHideLevel;
	}
	public String getStf_Id() {
		return stf_Id;
	}
	public void setStf_Id(String stfId) {
		stf_Id = stfId;
	}
	public String getXs_Custom_Telephone() {
		return xs_Custom_Telephone;
	}
	public void setXs_Custom_Telephone(String xsCustomTelephone) {
		xs_Custom_Telephone = xsCustomTelephone;
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
	public String getSell_Code() {
		return sell_Code;
	}
	public void setSell_Code(String sellCode) {
		sell_Code = sellCode;
	}
	public String getXs_Custom_Credentials() {
		return Xs_Custom_Credentials;
	}
	public void setXs_Custom_Credentials(String xsCustomCredentials) {
		Xs_Custom_Credentials = xsCustomCredentials;
	}
	public String getXs_Custom_Code_Card() {
		return xs_Custom_Code_Card;
	}
	public void setXs_Custom_Code_Card(String xsCustomCodeCard) {
		xs_Custom_Code_Card = xsCustomCodeCard;
	}
	public String getXs_Contacts_Person() {
		return xs_Contacts_Person;
	}
	public void setXs_Contacts_Person(String xsContactsPerson) {
		xs_Contacts_Person = xsContactsPerson;
	}
	public String getXs_Custom_Address() {
		return xs_Custom_Address;
	}
	public void setXs_Custom_Address(String xsCustomAddress) {
		xs_Custom_Address = xsCustomAddress;
	}
	public String getXs_Custom_Zipcode() {
		return xs_Custom_Zipcode;
	}
	public void setXs_Custom_Zipcode(String xsCustomZipcode) {
		xs_Custom_Zipcode = xsCustomZipcode;
	}
	public String getXs_Custom_Id() {
		return xs_Custom_Id;
	}
	public void setXs_Custom_Id(String xsCustomId) {
		xs_Custom_Id = xsCustomId;
	}
	public String getXs_Distributor_Id() {
		return xs_Distributor_Id;
	}
	public void setXs_Distributor_Id(String xsDistributorId) {
		xs_Distributor_Id = xsDistributorId;
	}
	public String getCarMotor_Number() {
		return carMotor_Number;
	}
	public void setCarMotor_Number(String carMotorNumber) {
		carMotor_Number = carMotorNumber;
	}
	public String getCarLicense_Plate() {
		return carLicense_Plate;
	}
	public void setCarLicense_Plate(String carLicensePlate) {
		carLicense_Plate = carLicensePlate;
	}
	public String getCarLicense_Name() {
		return carLicense_Name;
	}
	public void setCarLicense_Name(String carLicenseName) {
		carLicense_Name = carLicenseName;
	}
	public String getPayment_Money() {
		return payment_Money;
	}
	public void setPayment_Money(String paymentMoney) {
		payment_Money = paymentMoney;
	}
	public String getZhProjecRemark() {
		return zhProjecRemark;
	}
	public void setZhProjecRemark(String zhProjecRemark) {
		this.zhProjecRemark = zhProjecRemark;
	}
	public String getInserted() {
		return inserted;
	}
	public void setInserted(String inserted) {
		this.inserted = inserted;
	}
	public String getPid() {
		return Pid;
	}
	public void setPid(String pid) {
		Pid = pid;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getChild_Id() {
		return child_Id;
	}
	public void setChild_Id(String childId) {
		child_Id = childId;
	}
	public String getCheck_Comtent() {
		return check_Comtent;
	}
	public void setCheck_Comtent(String checkComtent) {
		check_Comtent = checkComtent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOut_Id() {
		return out_Id;
	}
	public void setOut_Id(String outId) {
		out_Id = outId;
	}
	public String getLimit_load_num() {
		return limit_load_num;
	}
	public void setLimit_load_num(String limitLoadNum) {
		limit_load_num = limitLoadNum;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getStf_Name() {
		return stf_Name;
	}
	public void setStf_Name(String stfName) {
		stf_Name = stfName;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String userName) {
		user_Name = userName;
	}
	public String getInstorehouse_Date() {
		return instorehouse_Date;
	}
	public void setInstorehouse_Date(String instorehouseDate) {
		instorehouse_Date = instorehouseDate;
	}
	
	public String getXs_Distributor_Name() {
		return xs_Distributor_Name;
	}
	public void setXs_Distributor_Name(String xsDistributorName) {
		xs_Distributor_Name = xsDistributorName;
	}
	public String getXs_Car_Vin_Number() {
		return xs_Car_Vin_Number;
	}
	public void setXs_Car_Vin_Number(String xsCarVinNumber) {
		xs_Car_Vin_Number = xsCarVinNumber;
	}
	public String getXs_Car_Motor_Number() {
		return xs_Car_Motor_Number;
	}
	public void setXs_Car_Motor_Number(String xsCarMotorNumber) {
		xs_Car_Motor_Number = xsCarMotorNumber;
	}
	public String getXs_Car_Brand() {
		return xs_Car_Brand;
	}
	public void setXs_Car_Brand(String xsCarBrand) {
		xs_Car_Brand = xsCarBrand;
	}
	public String getXs_Model_Name() {
		return xs_Model_Name;
	}
	public void setXs_Model_Name(String xsModelName) {
		xs_Model_Name = xsModelName;
	}
	public String getXs_Car_Ocn() {
		return xs_Car_Ocn;
	}
	public void setXs_Car_Ocn(String xsCarOcn) {
		xs_Car_Ocn = xsCarOcn;
	}
	public String getXs_Car_Color() {
		return xs_Car_Color;
	}
	public void setXs_Car_Color(String xsCarColor) {
		xs_Car_Color = xsCarColor;
	}
	public String getCar_Trim() {
		return car_Trim;
	}
	public void setCar_Trim(String carTrim) {
		car_Trim = carTrim;
	}
	public String getXs_Custom_Name() {
		return xs_Custom_Name;
	}
	public void setXs_Custom_Name(String xsCustomName) {
		xs_Custom_Name = xsCustomName;
	}
	public String getXs_Custom_Phone() {
		return xs_Custom_Phone;
	}
	public void setXs_Custom_Phone(String xsCustomPhone) {
		xs_Custom_Phone = xsCustomPhone;
	}
	public String getXs_Car_Id() {
		return xs_Car_Id;
	}
	public void setXs_Car_Id(String xsCarId) {
		xs_Car_Id = xsCarId;
	}
	public String getXs_Car_Cw_Info_Id() {
		return xs_Car_Cw_Info_Id;
	}
	public void setXs_Car_Cw_Info_Id(String xsCarCwInfoId) {
		xs_Car_Cw_Info_Id = xsCarCwInfoId;
	}
	public String getA_Amount() {
		return A_Amount;
	}
	public void setA_Amount(String aAmount) {
		A_Amount = aAmount;
	}
	public String getAply_Amount() {
		return aply_Amount;
	}
	public void setAply_Amount(String aplyAmount) {
		aply_Amount = aplyAmount;
	}
	public String getInsurance_Amount() {
		return insurance_Amount;
	}
	public void setInsurance_Amount(String insuranceAmount) {
		insurance_Amount = insuranceAmount;
	}
	public String getProject_Amount() {
		return project_Amount;
	}
	public void setProject_Amount(String projectAmount) {
		project_Amount = projectAmount;
	}
	public String getOrnament_Amount() {
		return ornament_Amount;
	}
	public void setOrnament_Amount(String ornamentAmount) {
		ornament_Amount = ornamentAmount;
	}
	public String getJ_Classify() {
		return J_Classify;
	}
	public void setJ_Classify(String jClassify) {
		J_Classify = jClassify;
	}
	public String getExamine() {
		return examine;
	}
	public void setExamine(String examine) {
		this.examine = examine;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getXs_Car_Sel_Id() {
		return xs_Car_Sel_Id;
	}
	public void setXs_Car_Sel_Id(String xsCarSelId) {
		xs_Car_Sel_Id = xsCarSelId;
	}
	public String getReserve_Id() {
		return reserve_Id;
	}
	public void setReserve_Id(String reserveId) {
		reserve_Id = reserveId;
	}
	public String getReserve_Code() {
		return reserve_Code;
	}
	public void setReserve_Code(String reserveCode) {
		reserve_Code = reserveCode;
	}
	public String getXs_Car_Sel_Data() {
		return xs_Car_Sel_Data;
	}
	public void setXs_Car_Sel_Data(String xsCarSelData) {
		xs_Car_Sel_Data = xsCarSelData;
	}
	public String getXs_Car_Sel_Transaction_Money() {
		return xs_Car_Sel_Transaction_Money;
	}
	public void setXs_Car_Sel_Transaction_Money(String xsCarSelTransactionMoney) {
		xs_Car_Sel_Transaction_Money = xsCarSelTransactionMoney;
	}
	public String getPayment_Way() {
		return payment_Way;
	}
	public void setPayment_Way(String paymentWay) {
		payment_Way = paymentWay;
	}
	public String getPayment_Amount() {
		return payment_Amount;
	}
	public void setPayment_Amount(String paymentAmount) {
		payment_Amount = paymentAmount;
	}
	public String getXs_Car_Give_Up() {
		return xs_Car_Give_Up;
	}
	public void setXs_Car_Give_Up(String xsCarGiveUp) {
		xs_Car_Give_Up = xsCarGiveUp;
	}
	public String getXs_Custom_Receiptor() {
		return xs_Custom_Receiptor;
	}
	public void setXs_Custom_Receiptor(String xsCustomReceiptor) {
		xs_Custom_Receiptor = xsCustomReceiptor;
	}
	public String getXs_Car_Sel_Type() {
		return xs_Car_Sel_Type;
	}
	public void setXs_Car_Sel_Type(String xsCarSelType) {
		xs_Car_Sel_Type = xsCarSelType;
	}
	public String getApply_Sum() {
		return apply_Sum;
	}
	public void setApply_Sum(String applySum) {
		apply_Sum = applySum;
	}
	public String getCost_Sum() {
		return cost_Sum;
	}
	public void setCost_Sum(String costSum) {
		cost_Sum = costSum;
	}
	public String getXs_Car_Sel_Remark() {
		return xs_Car_Sel_Remark;
	}
	public void setXs_Car_Sel_Remark(String xsCarSelRemark) {
		xs_Car_Sel_Remark = xsCarSelRemark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDb_Project_Id() {
		return db_Project_Id;
	}
	public void setDb_Project_Id(String dbProjectId) {
		db_Project_Id = dbProjectId;
	}
	public String getDb_Project_Cost() {
		return db_Project_Cost;
	}
	public void setDb_Project_Cost(String dbProjectCost) {
		db_Project_Cost = dbProjectCost;
	}
	public String getCost_Price() {
		return cost_Price;
	}
	public void setCost_Price(String costPrice) {
		cost_Price = costPrice;
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getId_Numbers() {
		return id_Numbers;
	}
	public void setId_Numbers(String idNumbers) {
		id_Numbers = idNumbers;
	}
	public String getZip_Code() {
		return zip_Code;
	}
	public void setZip_Code(String zipCode) {
		zip_Code = zipCode;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getCar_Color() {
		return car_Color;
	}
	public void setCar_Color(String carColor) {
		car_Color = carColor;
	}
	
	public String getXs_Car_Model_Id() {
		return xs_Car_Model_Id;
	}
	public void setXs_Car_Model_Id(String xsCarModelId) {
		xs_Car_Model_Id = xsCarModelId;
	}
	public String getXs_Car_Model() {
		return xs_Car_Model;
	}
	public void setXs_Car_Model(String xsCarModel) {
		xs_Car_Model = xsCarModel;
	}
	public String getCard_Color() {
		return card_Color;
	}
	public void setCard_Color(String cardColor) {
		card_Color = cardColor;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getEngine_Number() {
		return engine_Number;
	}
	public void setEngine_Number(String engineNumber) {
		engine_Number = engineNumber;
	}
	public String getOutprint() {
		return outprint;
	}
	public void setOutprint(String outprint) {
		this.outprint = outprint;
	}
	
	public String getZh_Project() {
		return zh_Project;
	}
	public void setZh_Project(String zhProject) {
		zh_Project = zhProject;
	}
	public String getUnit_Num() {
		return unit_Num;
	}
	public void setUnit_Num(String unitNum) {
		unit_Num = unitNum;
	}
	public String getSelL_Price() {
		return selL_Price;
	}
	public void setSelL_Price(String selLPrice) {
		selL_Price = selLPrice;
	}
	public String getPreferential_Price() {
		return preferential_Price;
	}
	public void setPreferential_Price(String preferentialPrice) {
		preferential_Price = preferentialPrice;
	}
	public String getDecorate_Sell() {
		return decorate_Sell;
	}
	public void setDecorate_Sell(String decorateSell) {
		decorate_Sell = decorateSell;
	}
	public String getDecorate_Amount() {
		return decorate_Amount;
	}
	public void setDecorate_Amount(String decorateAmount) {
		decorate_Amount = decorateAmount;
	}
	public String getIszhProject() {
		return iszhProject;
	}
	public void setIszhProject(String iszhProject) {
		this.iszhProject = iszhProject;
	}
	public String getXs_Car_Stf_Id() {
		return xs_Car_Stf_Id;
	}
	public void setXs_Car_Stf_Id(String xsCarStfId) {
		xs_Car_Stf_Id = xsCarStfId;
	}
	public String getExaminePerson() {
		return examinePerson;
	}
	public void setExaminePerson(String examinePerson) {
		this.examinePerson = examinePerson;
	}
	public String getXs_Car_Sel_TypeName() {
		return xs_Car_Sel_TypeName;
	}
	public void setXs_Car_Sel_TypeName(String xsCarSelTypeName) {
		xs_Car_Sel_TypeName = xsCarSelTypeName;
	}
	public String getYear_() {
		return year_;
	}
	public void setYear_(String year) {
		year_ = year;
	}
	public String getBalance_() {
		return balance_;
	}
	public void setBalance_(String balance) {
		balance_ = balance;
	}
	public String getFirst_Payment() {
		return first_Payment;
	}
	public void setFirst_Payment(String firstPayment) {
		first_Payment = firstPayment;
	}
	public String getLoan_Bank() {
		return loan_Bank;
	}
	public void setLoan_Bank(String loanBank) {
		loan_Bank = loanBank;
	}
	public Boolean getIsCarOut() {
		return isCarOut;
	}
	public void setIsCarOut(Boolean isCarOut) {
		this.isCarOut = isCarOut;
	}
	public String getCar_Brand_Name() {
		return car_Brand_Name;
	}
	public void setCar_Brand_Name(String carBrandName) {
		car_Brand_Name = carBrandName;
	}
	public String getCar_Color_Name() {
		return car_Color_Name;
	}
	public void setCar_Color_Name(String carColorName) {
		car_Color_Name = carColorName;
	}
	public String getXs_Custom_Hide_Level_Name() {
		return xs_Custom_Hide_Level_Name;
	}
	public void setXs_Custom_Hide_Level_Name(String xsCustomHideLevelName) {
		xs_Custom_Hide_Level_Name = xsCustomHideLevelName;
	}
	public String getXs_Custom_Property_Name() {
		return xs_Custom_Property_Name;
	}
	public void setXs_Custom_Property_Name(String xsCustomPropertyName) {
		xs_Custom_Property_Name = xsCustomPropertyName;
	}
	public String getXs_Custom_Source_Name() {
		return xs_Custom_Source_Name;
	}
	public void setXs_Custom_Source_Name(String xsCustomSourceName) {
		xs_Custom_Source_Name = xsCustomSourceName;
	}
	public String getInvoice_reckoning() {
		return invoice_reckoning;
	}
	public void setInvoice_reckoning(String invoiceReckoning) {
		invoice_reckoning = invoiceReckoning;
	}
	public String getStatus_Name() {
		return status_Name;
	}
	public void setStatus_Name(String statusName) {
		status_Name = statusName;
	}

}
