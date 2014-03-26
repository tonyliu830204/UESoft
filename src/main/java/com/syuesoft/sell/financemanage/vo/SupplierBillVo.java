package com.syuesoft.sell.financemanage.vo;

import java.sql.Timestamp;

public class SupplierBillVo {

	
	private int page;           		
	private int rows;           		
	private String tax;           		//含税金额
	private String number;				//数量
	private String instorehouseDate; 	//入库日期
	private String instorehouseDate2; 	//入库日期
	private String instorehouseId;		//入库单号
	private String instorehouseCode;	//入库编号
	private String accountPerson;    	//经办人	
	private String accountPersonName;    	//经办人	
	private String accountMoney;    	//已付金额	
	private String accountBalance;    	//应付金额 （余额）
	private String accountSun;    		//总金额
	private String accountType;    		//对账类型	
	private String state;	
	private String state1;	 			//是否付讫
	private String state2;	 			//是否付讫
	private String iconCls;	
	private String xsCarId;      
	private String xsCarCode;      
	private String xsCarVinNumber;      
	private String xsCarLicenseName;      
	private String vehicleCost;      	//单价
	private String nowtax;    			//本次收款  
	private String accountDate;    		//对账日期	
	private String accountDate2;    		//对账日期	
	private String accountCode;         //对账编号  			
	private String accountId;         	//对账单号  			
	private String remark;         	  			
	private String xsCarBrand;         	  			
	private String xsCarModel;         	  			
	private String distributor_id;//分销商
	private String distributor_name;//分销商
	private String allocatecode;//调拨单号
	private String is_invoice;//是否开票
	private String num;
	private Integer enterpriseId;
	private Boolean flag;
	
	
	public String getInstorehouseDate2() {
		return instorehouseDate2;
	}
	public void setInstorehouseDate2(String instorehouseDate2) {
		this.instorehouseDate2 = instorehouseDate2;
	}
	public String getAccountDate2() {
		return accountDate2;
	}
	public void setAccountDate2(String accountDate2) {
		this.accountDate2 = accountDate2;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	private String invoice;
	
	public String getIs_invoice() {
		return is_invoice;
	}
	public void setIs_invoice(String isInvoice) {
		is_invoice = isInvoice;
	}

	public String getAllocatecode() {
		return allocatecode;
	}
	public void setAllocatecode(String allocatecode) {
		this.allocatecode = allocatecode;
	}
	public String getDistributor_id() {
		return distributor_id;
	}
	public void setDistributor_id(String distributorId) {
		distributor_id = distributorId;
	}
	public String getDistributor_name() {
		return distributor_name;
	}
	public void setDistributor_name(String distributorName) {
		distributor_name = distributorName;
	}
	public String getXsCarBrand() {
		return xsCarBrand;
	}
	public void setXsCarBrand(String xsCarBrand) {
		this.xsCarBrand = xsCarBrand;
	}
	public String getXsCarModel() {
		return xsCarModel;
	}
	public void setXsCarModel(String xsCarModel) {
		this.xsCarModel = xsCarModel;
	}
	public String getState1() {
		return state1;
	}
	public void setState1(String state1) {
		this.state1 = state1;
	}
	public String getAccountPersonName() {
		return accountPersonName;
	}
	public void setAccountPersonName(String accountPersonName) {
		this.accountPersonName = accountPersonName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getState2() {
		return state2;
	}
	public void setState2(String state2) {
		this.state2 = state2;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
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
	public String getNowtax() {
		return nowtax;
	}
	public void setNowtax(String nowtax) {
		this.nowtax = nowtax;
	}
	public String getAccountMoney() {
		return accountMoney;
	}
	public void setAccountMoney(String accountMoney) {
		this.accountMoney = accountMoney;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountSun() {
		return accountSun;
	}
	public void setAccountSun(String accountSun) {
		this.accountSun = accountSun;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountPerson() {
		return accountPerson;
	}
	public void setAccountPerson(String accountPerson) {
		this.accountPerson = accountPerson;
	}
	public String getXsCarId() {
		return xsCarId;
	}
	public void setXsCarId(String xsCarId) {
		this.xsCarId = xsCarId;
	}
	public String getXsCarCode() {
		return xsCarCode;
	}
	public void setXsCarCode(String xsCarCode) {
		this.xsCarCode = xsCarCode;
	}
	public String getXsCarVinNumber() {
		return xsCarVinNumber;
	}
	public void setXsCarVinNumber(String xsCarVinNumber) {
		this.xsCarVinNumber = xsCarVinNumber;
	}
	public String getXsCarLicenseName() {
		return xsCarLicenseName;
	}
	public void setXsCarLicenseName(String xsCarLicenseName) {
		this.xsCarLicenseName = xsCarLicenseName;
	}
	public String getVehicleCost() {
		return vehicleCost;
	}
	public void setVehicleCost(String vehicleCost) {
		this.vehicleCost = vehicleCost;
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
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getInstorehouseDate() {
		return instorehouseDate;
	}
	public void setInstorehouseDate(String instorehouseDate) {
		this.instorehouseDate = instorehouseDate;
	}
	public String getInstorehouseId() {
		return instorehouseId;
	}
	public void setInstorehouseId(String instorehouseId) {
		this.instorehouseId = instorehouseId;
	}
	public String getInstorehouseCode() {
		return instorehouseCode;
	}
	public void setInstorehouseCode(String instorehouseCode) {
		this.instorehouseCode = instorehouseCode;
	}
	
	
}
