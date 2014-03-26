package com.syuesoft.sell.sellwork.vo;


public class QuitCarManageVo {
	 private int page;
	 private int rows;
	
	 private Integer enterpriseId;				//企业编号	
	 private String backCar_Id;					//退车款编号	
	 private String backCar_Person;				//经办人
	 private String backMoney_Person;			//退款人
	 private String invoice;					//发票类型
     private String backCar_Code;				//退车款单号	
     private String backCar_Sunmoney;			//应退金额
     private String backCar_Money;				//退换金额
     
     private String invoice_Num;				//票据编号
     private String backMoney_Date;				//退款日期
     private String backCar_Date;				//经办日期
     
     private String exitCar_Id;					//退车单编号
     private String xs_Car_Sel_Id;				//车辆销售信息编号
     private String exitCar_Code;				//退车单号
     private String exitCar_Date;				//退车日期
     private String exitCar_Date2;				//退车日期
     private String exitCar_Type;				//退车分类
     private String exitCar_Person;				//经办人
     private String xs_Car_Pds_Date;			//PDS日期\售前质量检测日期
     private String xs_Car_Pds_Person;			//PDS人员
     private String xs_Car_Pds_Result;			//PDS结果
     private String exitCar_Check_Person;		//经办人
     private String examine;					//审核状态
     private String examine_Name;
     private String sell_Code;					//销售编号
     private String xs_Custom_Id;				//客户编号
     private String xs_Custom_Name;	
     private String xs_Car_Id;	
     private String xs_Car_Code;	
     private String xs_Car_Ocn;	
     private String xs_Car_Vin_Number;	
     private String xs_Car_Sel_Date;	  		//销售日期
     private String xs_Car_Sel_Date2;	  		//销售日期
     private String exitCar_Remark;				//退车备注
     private Integer notice;					//是否通知退款，0未通知，1 已通知
     private String exitCarTypeName;			//退车分类名称
     private Boolean reciveOrSellTag;			//预订或销售区分false为销售，true为预订
     private String reserveId;					//预订单编号
     
     
	public String getExitCar_Date2() {
		return exitCar_Date2;
	}
	public void setExitCar_Date2(String exitCarDate2) {
		exitCar_Date2 = exitCarDate2;
	}
	public String getXs_Car_Sel_Date2() {
		return xs_Car_Sel_Date2;
	}
	public void setXs_Car_Sel_Date2(String xsCarSelDate2) {
		xs_Car_Sel_Date2 = xsCarSelDate2;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getExamine_Name() {
		return examine_Name;
	}
	public void setExamine_Name(String examineName) {
		examine_Name = examineName;
	}
	public String getXs_Car_Sel_Date() {
		return xs_Car_Sel_Date;
	}
	public void setXs_Car_Sel_Date(String xsCarSelDate) {
		xs_Car_Sel_Date = xsCarSelDate;
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
	public String getXs_Car_Code() {
		return xs_Car_Code;
	}
	public void setXs_Car_Code(String xsCarCode) {
		xs_Car_Code = xsCarCode;
	}
	public String getXs_Car_Ocn() {
		return xs_Car_Ocn;
	}
	public void setXs_Car_Ocn(String xsCarOcn) {
		xs_Car_Ocn = xsCarOcn;
	}
	public String getXs_Car_Vin_Number() {
		return xs_Car_Vin_Number;
	}
	public void setXs_Car_Vin_Number(String xsCarVinNumber) {
		xs_Car_Vin_Number = xsCarVinNumber;
	}
	public String getSell_Code() {
		return sell_Code;
	}
	public void setSell_Code(String sellCode) {
		sell_Code = sellCode;
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
	public String getBackCar_Id() {
		return backCar_Id;
	}
	public void setBackCar_Id(String backCarId) {
		backCar_Id = backCarId;
	}
	public String getBackCar_Person() {
		return backCar_Person;
	}
	public void setBackCar_Person(String backCarPerson) {
		backCar_Person = backCarPerson;
	}
	public String getBackMoney_Person() {
		return backMoney_Person;
	}
	public void setBackMoney_Person(String backMoneyPerson) {
		backMoney_Person = backMoneyPerson;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getBackCar_Code() {
		return backCar_Code;
	}
	public void setBackCar_Code(String backCarCode) {
		backCar_Code = backCarCode;
	}
	public String getBackCar_Sunmoney() {
		return backCar_Sunmoney;
	}
	public void setBackCar_Sunmoney(String backCarSunmoney) {
		backCar_Sunmoney = backCarSunmoney;
	}
	public String getBackCar_Money() {
		return backCar_Money;
	}
	public void setBackCar_Money(String backCarMoney) {
		backCar_Money = backCarMoney;
	}
	public String getInvoice_Num() {
		return invoice_Num;
	}
	public void setInvoice_Num(String invoiceNum) {
		invoice_Num = invoiceNum;
	}
	public String getBackMoney_Date() {
		return backMoney_Date;
	}
	public void setBackMoney_Date(String backMoneyDate) {
		backMoney_Date = backMoneyDate;
	}
	public String getBackCar_Date() {
		return backCar_Date;
	}
	public void setBackCar_Date(String backCarDate) {
		backCar_Date = backCarDate;
	}
	public String getExitCar_Id() {
		return exitCar_Id;
	}
	public void setExitCar_Id(String exitCarId) {
		exitCar_Id = exitCarId;
	}
	public String getXs_Car_Sel_Id() {
		return xs_Car_Sel_Id;
	}
	public void setXs_Car_Sel_Id(String xsCarSelId) {
		xs_Car_Sel_Id = xsCarSelId;
	}
	public String getExitCar_Code() {
		return exitCar_Code;
	}
	public void setExitCar_Code(String exitCarCode) {
		exitCar_Code = exitCarCode;
	}
	public String getExitCar_Date() {
		return exitCar_Date;
	}
	public void setExitCar_Date(String exitCarDate) {
		exitCar_Date = exitCarDate;
	}
	
	public String getExitCar_Type() {
		return exitCar_Type;
	}
	public void setExitCar_Type(String exitCarType) {
		exitCar_Type = exitCarType;
	}
	public String getExitCar_Person() {
		return exitCar_Person;
	}
	public void setExitCar_Person(String exitCarPerson) {
		exitCar_Person = exitCarPerson;
	}
	public String getExitCar_Check_Person() {
		return exitCar_Check_Person;
	}
	public void setExitCar_Check_Person(String exitCarCheckPerson) {
		exitCar_Check_Person = exitCarCheckPerson;
	}
	public String getXs_Car_Pds_Date() {
		return xs_Car_Pds_Date;
	}
	public void setXs_Car_Pds_Date(String xsCarPdsDate) {
		xs_Car_Pds_Date = xsCarPdsDate;
	}
	public String getXs_Car_Pds_Person() {
		return xs_Car_Pds_Person;
	}
	public void setXs_Car_Pds_Person(String xsCarPdsPerson) {
		xs_Car_Pds_Person = xsCarPdsPerson;
	}
	public String getXs_Car_Pds_Result() {
		return xs_Car_Pds_Result;
	}
	public void setXs_Car_Pds_Result(String xsCarPdsResult) {
		xs_Car_Pds_Result = xsCarPdsResult;
	}
	public String getExamine() {
		return examine;
	}
	public void setExamine(String examine) {
		this.examine = examine;
	}
	public String getExitCar_Remark() {
		return exitCar_Remark;
	}
	public void setExitCar_Remark(String exitCarRemark) {
		exitCar_Remark = exitCarRemark;
	}
	public Integer getNotice() {
		return notice;
	}
	public void setNotice(Integer notice) {
		this.notice = notice;
	}
	public String getExitCarTypeName() {
		return exitCarTypeName;
	}
	public void setExitCarTypeName(String exitCarTypeName) {
		this.exitCarTypeName = exitCarTypeName;
	}
	public Boolean getReciveOrSellTag() {
		return reciveOrSellTag;
	}
	public void setReciveOrSellTag(Boolean reciveOrSellTag) {
		this.reciveOrSellTag = reciveOrSellTag;
	}
	public String getReserveId() {
		return reserveId;
	}
	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}
     
}
