package com.syuesoft.sell.financemanage.vo;

import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsSellAccount;

public class GatheringManageVo {
	private int page;
	private int rows;
	
	private String sell_Code; 					//销售单编号  
	private String reserve_Code;   
	private String reserve_Id;   				//预订单号
	private String reserve_Date;
	private String reserve_Date2;
	private String xs_Custom_Name;
	private String xs_Car_Code;
	private String pact_Code;
	private String xs_Model_Name;
	private String car_Color;
	private String car_Color2;
	private String xs_Car_Vin_Number;
	private String xs_Car_Ocn;
	private String custom_Id;
	private String xs_Car_Id;
	
	 private String account_Id;						//结算单编号
	 private String xs_Car_Sel_Id;					//销售单号
     private String account_Code;					//收款编号
     private String account_Type_Id;
     private String account_Type;					// 收款类型(结算收款、预收款、调拨收款)
     private String account_Money;
     private String account_Date;
     private String account_Date2;//创建日期
     private String account_Person;					//经办人
     private String account_State;
     private String remark;
     private String stf_Id;							//收款人
     private String stf_Name;						//业务员名称
     private String account_Amount;					//余额
     private String account_Arrears;				//欠款
     private String account_ArrearsAge;
     
     private String collections_Id;      			//收款编号
     private String account_Receivables; 			//应收款金额、预收款金额
     private String account_Cumulative; 			//累计收款
     private String account_Unfinished; 			//是否付清
     private String account_Arrears_Age; 			//应收款帐龄
     
     private String details_Id;    					//收款记录编号
     private String date_; 							//收款日期
     private String date_two; 							//收款日期
     private String received_Money;					//本次收款
     private String received_Way;					//付款方式
     private String invoice;						//发票类型
     private String invoice_Num;					//票据编号
     private String detail_Type;					//款项类型(收款、退款)
     private String examine;						//审核情况
     private String stf_Id_Person;					//销售经办人
     private String payment_Money;					//预定单预定收款金额
     private String sum_Money;						//累计预收金额
     private String account_Classify;				//收款分类（代办，装潢，保险）
     private String account_Classify_Name;			//收款分类（代办，装潢，保险）
     
     private String carBrand;
     private String tel;
     private String xs_contacts_person;
     private Integer received_Way_id;
     private Integer enterpriseId;					//企业编号
     private Boolean flag;
     
     
	     
	public String getDate_two() {
		return date_two;
	}
	public void setDate_two(String dateTwo) {
		date_two = dateTwo;
	}
	public String getReserve_Date2() {
		return reserve_Date2;
	}
	public String getAccount_Date2() {
		return account_Date2;
	}
	public void setAccount_Date2(String accountDate2) {
		account_Date2 = accountDate2;
	}
	public void setReserve_Date2(String reserveDate2) {
		reserve_Date2 = reserveDate2;
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
		public Integer getReceived_Way_id() {
			return received_Way_id;
		}
		public void setReceived_Way_id(Integer receivedWayId) {
			received_Way_id = receivedWayId;
		}
		public String getXs_contacts_person() {
			return xs_contacts_person;
		}
		public void setXs_contacts_person(String xsContactsPerson) {
			xs_contacts_person = xsContactsPerson;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getCarBrand() {
			return carBrand;
		}
		public void setCarBrand(String carBrand) {
			this.carBrand = carBrand;
		}
		public String getAccount_Classify_Name() {
			return account_Classify_Name;
		}
		public void setAccount_Classify_Name(String accountClassifyName) {
			account_Classify_Name = accountClassifyName;
		}
		public String getAccount_Classify() {
			return account_Classify;
		}
		public void setAccount_Classify(String accountClassify) {
			account_Classify = accountClassify;
		}
		public String getSum_Money() {
			return sum_Money;
		}
		public void setSum_Money(String sumMoney) {
			sum_Money = sumMoney;
		}
		public String getReserve_Id() {
			return reserve_Id;
		}
		public void setReserve_Id(String reserveId) {
			reserve_Id = reserveId;
		}
		public String getPayment_Money() {
			return payment_Money;
		}
		public void setPayment_Money(String paymentMoney) {
			payment_Money = paymentMoney;
		}
		public String getStf_Name() {
			return stf_Name;
		}
		public void setStf_Name(String stfName) {
			stf_Name = stfName;
		}
		public String getCustom_Id() {
			return custom_Id;
		}
		public void setCustom_Id(String customId) {
			custom_Id = customId;
		}
		public String getXs_Car_Id() {
			return xs_Car_Id;
		}
		public void setXs_Car_Id(String xsCarId) {
			xs_Car_Id = xsCarId;
		}
		public String getSell_Code() {
			return sell_Code;
		}
		public void setSell_Code(String sellCode) {
			sell_Code = sellCode;
		}
		public String getStf_Id_Person() {
			return stf_Id_Person;
		}
		public void setStf_Id_Person(String stfIdPerson) {
			stf_Id_Person = stfIdPerson;
		}
		public String getCollections_Id() {
			return collections_Id;
		}
		public void setCollections_Id(String collectionsId) {
			collections_Id = collectionsId;
		}
		public String getAccount_Receivables() {
			return account_Receivables;
		}
		public void setAccount_Receivables(String accountReceivables) {
			account_Receivables = accountReceivables;
		}
		public String getAccount_Cumulative() {
			return account_Cumulative;
		}
		public void setAccount_Cumulative(String accountCumulative) {
			account_Cumulative = accountCumulative;
		}
		public String getAccount_Unfinished() {
			return account_Unfinished;
		}
		public void setAccount_Unfinished(String accountUnfinished) {
			account_Unfinished = accountUnfinished;
		}
		public String getAccount_Arrears_Age() {
			return account_Arrears_Age;
		}
		public void setAccount_Arrears_Age(String accountArrearsAge) {
			account_Arrears_Age = accountArrearsAge;
		}
		public String getDetails_Id() {
			return details_Id;
		}
		public void setDetails_Id(String detailsId) {
			details_Id = detailsId;
		}
		public String getDate_() {
			return date_;
		}
		public void setDate_(String date) {
			date_ = date;
		}
		public String getReceived_Money() {
			return received_Money;
		}
		public void setReceived_Money(String receivedMoney) {
			received_Money = receivedMoney;
		}
		public String getReceived_Way() {
			return received_Way;
		}
		public void setReceived_Way(String receivedWay) {
			received_Way = receivedWay;
		}
		public String getInvoice() {
			return invoice;
		}
		public void setInvoice(String invoice) {
			this.invoice = invoice;
		}
		public String getInvoice_Num() {
			return invoice_Num;
		}
		public void setInvoice_Num(String invoiceNum) {
			invoice_Num = invoiceNum;
		}
		public String getDetail_Type() {
			return detail_Type;
		}
		public void setDetail_Type(String detailType) {
			detail_Type = detailType;
		}
		public String getExamine() {
			return examine;
		}
		public void setExamine(String examine) {
			this.examine = examine;
		}
		public String getAccount_ArrearsAge() {
			return account_ArrearsAge;
		}
		public void setAccount_ArrearsAge(String accountArrearsAge) {
			account_ArrearsAge = accountArrearsAge;
		}
		public String getAccount_Arrears() {
			return account_Arrears;
		}
		public void setAccount_Arrears(String accountArrears) {
			account_Arrears = accountArrears;
		}
		public String getAccount_Amount() {
			return account_Amount;
		}
		public void setAccount_Amount(String accountAmount) {
			account_Amount = accountAmount;
		}
		public String getStf_Id() {
			return stf_Id;
		}
		public void setStf_Id(String stfId) {
			stf_Id = stfId;
		}
		public String getAccount_Id() {
			return account_Id;
		}
		public void setAccount_Id(String accountId) {
			account_Id = accountId;
		}
		public String getXs_Car_Sel_Id() {
			return xs_Car_Sel_Id;
		}
		public void setXs_Car_Sel_Id(String xsCarSelId) {
			xs_Car_Sel_Id = xsCarSelId;
		}
		public String getAccount_Code() {
			return account_Code;
		}
		public void setAccount_Code(String accountCode) {
			account_Code = accountCode;
		}
		public String getAccount_Type_Id() {
			return account_Type_Id;
		}
		public void setAccount_Type_Id(String accountTypeId) {
			account_Type_Id = accountTypeId;
		}
		public String getAccount_Type() {
			return account_Type;
		}
		public void setAccount_Type(String accountType) {
			account_Type = accountType;
		}
		public String getAccount_Money() {
			return account_Money;
		}
		public void setAccount_Money(String accountMoney) {
			account_Money = accountMoney;
		}
		public String getAccount_Date() {
			return account_Date;
		}
		public void setAccount_Date(String accountDate) {
			account_Date = accountDate;
		}
		public String getAccount_Person() {
			return account_Person;
		}
		public void setAccount_Person(String accountPerson) {
			account_Person = accountPerson;
		}
		public String getAccount_State() {
			return account_State;
		}
		public void setAccount_State(String accountState) {
			account_State = accountState;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getReserve_Code() {
			return reserve_Code;
		}
		public void setReserve_Code(String reserveCode) {
			reserve_Code = reserveCode;
		}
		public String getReserve_Date() {
			return reserve_Date;
		}
		public void setReserve_Date(String reserveDate) {
			reserve_Date = reserveDate;
		}
		public String getXs_Custom_Name() {
			return xs_Custom_Name;
		}
		public void setXs_Custom_Name(String xsCustomName) {
			xs_Custom_Name = xsCustomName;
		}
		public String getXs_Car_Code() {
			return xs_Car_Code;
		}
		public void setXs_Car_Code(String xsCarCode) {
			xs_Car_Code = xsCarCode;
		}
		public String getPact_Code() {
			return pact_Code;
		}
		public void setPact_Code(String pactCode) {
			pact_Code = pactCode;
		}
		public String getXs_Model_Name() {
			return xs_Model_Name;
		}
		public void setXs_Model_Name(String xsModelName) {
			xs_Model_Name = xsModelName;
		}
		public String getCar_Color() {
			return car_Color;
		}
		public void setCar_Color(String carColor) {
			car_Color = carColor;
		}
		public String getCar_Color2() {
			return car_Color2;
		}
		public void setCar_Color2(String carColor2) {
			car_Color2 = carColor2;
		}
		public String getXs_Car_Vin_Number() {
			return xs_Car_Vin_Number;
		}
		public void setXs_Car_Vin_Number(String xsCarVinNumber) {
			xs_Car_Vin_Number = xsCarVinNumber;
		}
		public String getXs_Car_Ocn() {
			return xs_Car_Ocn;
		}
		public void setXs_Car_Ocn(String xsCarOcn) {
			xs_Car_Ocn = xsCarOcn;
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
	
}
