package com.syuesoft.sell.sellAccount.vo;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;

public class SellAccountVo extends BaseBean implements Serializable {
    private Integer accountId;//结算单编号
    private Integer xs_Car_Sel_Id ;//车辆销售信息编号
    private String accountCode;//结算单号
    private String accountTypeId;//销售结算类型编号(保险单号、代办编号、装潢编号，调拨编号等)
    private Integer accountType;//结算类型(保险、代办、装潢、按揭，调拨等)
    private String accountTypeName;
    private Double accountMoney;//结算金额
    private String accountDate;//结算日期
    private String accountDate2;//结算日期
    private Integer accountPerson;//经办人
    private String person;
    private Integer accountGyration;//是否回转
    private String gyration;
    private Integer accountState;//是否转收银
    private String state;
    private Integer enterprise_id;
    private Boolean flag;
	private String remark;//备注
    private int rows;
	private int page;
    
    public String getAccountDate2() {
		return accountDate2;
	}
	public void setAccountDate2(String accountDate2) {
		this.accountDate2 = accountDate2;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Integer getEnterprise_id() {
		return enterprise_id;
	}	
	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}

    public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getGyration() {
		return gyration;
	}
	public void setGyration(String gyration) {
		this.gyration = gyration;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getXs_Car_Sel_Id() {
		return xs_Car_Sel_Id;
	}
	public void setXs_Car_Sel_Id(Integer xsCarSelId) {
		xs_Car_Sel_Id = xsCarSelId;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public Double getAccountMoney() {
		return accountMoney;
	}
	public void setAccountMoney(Double accountMoney) {
		this.accountMoney = accountMoney;
	}

	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	public Integer getAccountPerson() {
		return accountPerson;
	}
	public void setAccountPerson(Integer accountPerson) {
		this.accountPerson = accountPerson;
	}
	public Integer getAccountGyration() {
		return accountGyration;
	}
	public void setAccountGyration(Integer accountGyration) {
		this.accountGyration = accountGyration;
	}
	public Integer getAccountState() {
		return accountState;
	}
	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
}
