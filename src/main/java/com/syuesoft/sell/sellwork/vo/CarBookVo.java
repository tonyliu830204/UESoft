package com.syuesoft.sell.sellwork.vo;


public class CarBookVo {

	private int page;
	private int rows;
	
	private Integer enterpriseId; //企业编号
	private String reserve_Id; //编号
	private String reserve_Code;//预定单号
	private String xs_Custom_Id;
	private String xs_Custom_Name;	
	private String xs_Car_Id;   //车辆档案信息编号
	private String stf_Id_Person; //经办人编号
	private String stf_Id_PersonName;	
	private String reserve_Dete;  //预定日期
	private String reserve_Dete2;  //预定日期
	private String xs_Car_Brand;     //
	private String xs_Car_Brand_Name;     //
	private String xs_Car_Color;
	private String xs_Car_Color_Name;
	private String xs_Car_Vin_Number;
	private String xs_Car_Trim;     //内饰
	private String xs_Car_Trim_Name;     //内饰
	private String car_Output_Volume; //排量
	private String car_Output_Volume_Name; //排量
	private String limit_Load_Num;   //限乘人数
	private String pact_Code;        //合同编号
	private String xs_Custom_Property;  //客户性质
	private String xs_Custom_Property_Name;
	private String payment_Money;	 //预付金额	
	private String pay_Way;			//付款方式
	private String pay_Way_Name;			//付款方式
	private String sellingprice;    //车售价
	
	private String order_State;     //订单状态
	private String order_State_Name;	   //订单状态	
	private String predict_Take_Date; //预交车日期
	private String predict_Take_Date2; //预交车日期
	private String stf_Id;
	private String stf_Name;
	private String xs_Distributor_Code; //分销商编号
	private String level;					//级别
	private String level_Name;					//级别
	private String first_Pay_Money;		//按揭首付
	private String loan_Bank;           //按揭银行
	private String loan_Bank_Name;           //按揭银行
	private String loan_Limit_Money;   //按揭额度
	private String loan_Limit_Year;     //按揭年限
	private String vin_Code;    		//
	private String custom_Opinion;		//客户要求
	private String shingle_Money;		//预测上牌费用
	private String decorate_Money;      //装潢金额
	private String allocate_State;		//车辆分配状态
	private String del_State;			//删除标志
	private String remark;				//备注
		
		
	private String xs_Custom_Telephone;		
	private String xs_Model_Name;		
	private String xs_Car_Model_Id;		
	private String examine;				//审核情况
	private String examine_Name;			
			
	
	private String xs_Providebank_Name;	 //银行	
	private String xs_Distributor_Name;	 //分销商	
	private String dept_Name;	 //部门
	private String dept_Id;	 //部门
	
	private String carModerId;		//车辆型号
	private Boolean flag;			//查找标示
 	
	
	
	public String getReserve_Dete2() {
		return reserve_Dete2;
	}
	public void setReserve_Dete2(String reserveDete2) {
		reserve_Dete2 = reserveDete2;
	}
	public String getPredict_Take_Date2() {
		return predict_Take_Date2;
	}
	public void setPredict_Take_Date2(String predictTakeDate2) {
		predict_Take_Date2 = predictTakeDate2;
	}
	
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	public String getXs_Custom_Id() {
		return xs_Custom_Id;
	}
	public void setXs_Custom_Id(String xsCustomId) {
		xs_Custom_Id = xsCustomId;
	}
	public String getXs_Custom_Name() {
		return xs_Custom_Name;
	}
	public void setXs_Custom_Name(String xsCustomName) {
		xs_Custom_Name = xsCustomName;
	}
	public String getXs_Car_Id() {
		return xs_Car_Id;
	}
	public void setXs_Car_Id(String xsCarId) {
		xs_Car_Id = xsCarId;
	}
	public String getStf_Id_Person() {
		return stf_Id_Person;
	}
	public void setStf_Id_Person(String stfIdPerson) {
		stf_Id_Person = stfIdPerson;
	}
	public String getReserve_Dete() {
		return reserve_Dete;
	}
	public void setReserve_Dete(String reserveDete) {
		reserve_Dete = reserveDete;
	}
	public String getXs_Car_Brand() {
		return xs_Car_Brand;
	}
	public void setXs_Car_Brand(String xsCarBrand) {
		xs_Car_Brand = xsCarBrand;
	}
	public String getXs_Car_Brand_Name() {
		return xs_Car_Brand_Name;
	}
	public void setXs_Car_Brand_Name(String xsCarBrandName) {
		xs_Car_Brand_Name = xsCarBrandName;
	}
	public String getXs_Car_Color() {
		return xs_Car_Color;
	}
	public void setXs_Car_Color(String xsCarColor) {
		xs_Car_Color = xsCarColor;
	}
	public String getXs_Car_Color_Name() {
		return xs_Car_Color_Name;
	}
	public void setXs_Car_Color_Name(String xsCarColorName) {
		xs_Car_Color_Name = xsCarColorName;
	}
	public String getXs_Car_Vin_Number() {
		return xs_Car_Vin_Number;
	}
	public void setXs_Car_Vin_Number(String xsCarVinNumber) {
		xs_Car_Vin_Number = xsCarVinNumber;
	}
	
	public String getXs_Car_Trim() {
		return xs_Car_Trim;
	}
	public void setXs_Car_Trim(String xsCarTrim) {
		xs_Car_Trim = xsCarTrim;
	}
	public String getXs_Car_Trim_Name() {
		return xs_Car_Trim_Name;
	}
	public void setXs_Car_Trim_Name(String xsCarTrimName) {
		xs_Car_Trim_Name = xsCarTrimName;
	}
	public String getCar_Output_Volume() {
		return car_Output_Volume;
	}
	public void setCar_Output_Volume(String carOutputVolume) {
		car_Output_Volume = carOutputVolume;
	}
	public String getCar_Output_Volume_Name() {
		return car_Output_Volume_Name;
	}
	public void setCar_Output_Volume_Name(String carOutputVolumeName) {
		car_Output_Volume_Name = carOutputVolumeName;
	}
	public String getLimit_Load_Num() {
		return limit_Load_Num;
	}
	public void setLimit_Load_Num(String limitLoadNum) {
		limit_Load_Num = limitLoadNum;
	}
	public String getPact_Code() {
		return pact_Code;
	}
	public void setPact_Code(String pactCode) {
		pact_Code = pactCode;
	}
	
	
	public String getXs_Custom_Property() {
		return xs_Custom_Property;
	}
	public void setXs_Custom_Property(String xsCustomProperty) {
		xs_Custom_Property = xsCustomProperty;
	}
	
	public String getXs_Custom_Property_Name() {
		return xs_Custom_Property_Name;
	}
	public void setXs_Custom_Property_Name(String xsCustomPropertyName) {
		xs_Custom_Property_Name = xsCustomPropertyName;
	}
	public String getPayment_Money() {
		return payment_Money;
	}
	public void setPayment_Money(String paymentMoney) {
		payment_Money = paymentMoney;
	}
	public String getPay_Way() {
		return pay_Way;
	}
	public void setPay_Way(String payWay) {
		pay_Way = payWay;
	}
	public String getPay_Way_Name() {
		return pay_Way_Name;
	}
	public void setPay_Way_Name(String payWayName) {
		pay_Way_Name = payWayName;
	}
	public String getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(String sellingprice) {
		this.sellingprice = sellingprice;
	}
	public String getOrder_State() {
		return order_State;
	}
	public void setOrder_State(String orderState) {
		order_State = orderState;
	}
	public String getOrder_State_Name() {
		return order_State_Name;
	}
	public void setOrder_State_Name(String orderStateName) {
		order_State_Name = orderStateName;
	}
	public String getPredict_Take_Date() {
		return predict_Take_Date;
	}
	public void setPredict_Take_Date(String predictTakeDate) {
		predict_Take_Date = predictTakeDate;
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
	public String getXs_Distributor_Code() {
		return xs_Distributor_Code;
	}
	public void setXs_Distributor_Code(String xsDistributorCode) {
		xs_Distributor_Code = xsDistributorCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevel_Name() {
		return level_Name;
	}
	public void setLevel_Name(String levelName) {
		level_Name = levelName;
	}
	public String getFirst_Pay_Money() {
		return first_Pay_Money;
	}
	public void setFirst_Pay_Money(String firstPayMoney) {
		first_Pay_Money = firstPayMoney;
	}
	public String getLoan_Bank() {
		return loan_Bank;
	}
	public void setLoan_Bank(String loanBank) {
		loan_Bank = loanBank;
	}
	public String getLoan_Bank_Name() {
		return loan_Bank_Name;
	}
	public void setLoan_Bank_Name(String loanBankName) {
		loan_Bank_Name = loanBankName;
	}
	public String getLoan_Limit_Money() {
		return loan_Limit_Money;
	}
	public void setLoan_Limit_Money(String loanLimitMoney) {
		loan_Limit_Money = loanLimitMoney;
	}
	public String getLoan_Limit_Year() {
		return loan_Limit_Year;
	}
	public void setLoan_Limit_Year(String loanLimitYear) {
		loan_Limit_Year = loanLimitYear;
	}
	public String getVin_Code() {
		return vin_Code;
	}
	public void setVin_Code(String vinCode) {
		vin_Code = vinCode;
	}
	public String getCustom_Opinion() {
		return custom_Opinion;
	}
	public void setCustom_Opinion(String customOpinion) {
		custom_Opinion = customOpinion;
	}
	public String getShingle_Money() {
		return shingle_Money;
	}
	public void setShingle_Money(String shingleMoney) {
		shingle_Money = shingleMoney;
	}
	public String getDecorate_Money() {
		return decorate_Money;
	}
	public void setDecorate_Money(String decorateMoney) {
		decorate_Money = decorateMoney;
	}
	public String getAllocate_State() {
		return allocate_State;
	}
	public void setAllocate_State(String allocateState) {
		allocate_State = allocateState;
	}
	public String getDel_State() {
		return del_State;
	}
	public void setDel_State(String delState) {
		del_State = delState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getXs_Custom_Telephone() {
		return xs_Custom_Telephone;
	}
	public void setXs_Custom_Telephone(String xsCustomTelephone) {
		xs_Custom_Telephone = xsCustomTelephone;
	}
	public String getXs_Model_Name() {
		return xs_Model_Name;
	}
	public void setXs_Model_Name(String xsModelName) {
		xs_Model_Name = xsModelName;
	}
	public String getXs_Car_Model_Id() {
		return xs_Car_Model_Id;
	}
	public void setXs_Car_Model_Id(String xsCarModelId) {
		xs_Car_Model_Id = xsCarModelId;
	}
	public String getExamine() {
		return examine;
	}
	public void setExamine(String examine) {
		this.examine = examine;
	}
	public String getExamine_Name() {
		return examine_Name;
	}
	public void setExamine_Name(String examineName) {
		examine_Name = examineName;
	}
	public String getXs_Providebank_Name() {
		return xs_Providebank_Name;
	}
	public void setXs_Providebank_Name(String xsProvidebankName) {
		xs_Providebank_Name = xsProvidebankName;
	}
	public String getXs_Distributor_Name() {
		return xs_Distributor_Name;
	}
	public void setXs_Distributor_Name(String xsDistributorName) {
		xs_Distributor_Name = xsDistributorName;
	}
	public String getDept_Name() {
		return dept_Name;
	}
	public void setDept_Name(String deptName) {
		dept_Name = deptName;
	}
	public String getDept_Id() {
		return dept_Id;
	}
	public void setDept_Id(String deptId) {
		dept_Id = deptId;
	}
	public String getCarModerId() {
		return carModerId;
	}
	public void setCarModerId(String carModerId) {
		this.carModerId = carModerId;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getStf_Id_PersonName() {
		return stf_Id_PersonName;
	}
	public void setStf_Id_PersonName(String stfIdPersonName) {
		stf_Id_PersonName = stfIdPersonName;
	}
	
}
