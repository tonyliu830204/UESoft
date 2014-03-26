package com.syuesoft.sell.sellwork.vo;

import java.util.Date;

import com.syuesoft.sell.model.XsCarSellInfo;

public class SellInvoiceVo {
	private Integer xs_Car_Sel_Id; // 车辆销售信息编号
	private Date instorehouse_Date; // 出库日期
	private String out_Id; // 出库单号
	private String reserve_Code; // 预定单编号
	private String xs_Car_Sel_Data; // 销售日期
	private String xs_Car_Sel_Data2; // 销售日期
	private String user_Name; // 经办人
	private String xs_Custom_Name;
	private String stf_Name; // 业务员
	private String xs_Car_Vin_Number; // vin
	private String xs_Car_Ocn;
	private String xs_Car_Brand;
	private String xs_Model_Name;
	private String xs_Car_Sel_Transaction_Money; // 销售价格
	private String xs_Distributor_Name; // 分销商
	private String examine; // 审核
	private String limit_load_num; // 限乘人数
	private String mobilephone; // 电话
	private String xs_Car_Give_Up; // 是否放弃购车
	private int page;
	private int rows;
	private Integer isInvoice; // 是否开票
	// 开票信息
	private Integer id; // 编号
	private String invoiceCode; // 开票单号
	private Date invoiceDate; // 开票日期
	private Integer invoicePerson; // 开票人
	private Integer person; // 收款人
	private String invoicePersonN; // 开票人
	private String personN; // 收款人
	private String invoiceNumber; // 发票号码
	private String achievement; // 业绩系数
	private Double invoiceParce; // 开票金额
	private Integer invoiceType; // 票据类型
	private String invoiceRemark; // 开票备注
	private Integer invoiceExamin; // 开票审核状态
	private String invoiceExaminName; // 开票审核状态名称
	private Double hsDiscount;// 含税折扣金额
	private Double wsDiscount;// 未税折扣金额
	private Double discount;// 折扣税额
	private Double afterHsDiscount;// 折后含税金额
	private Double wsPurchase;// 未税采购额
	private Double purchase;// 采购税额
	private Double afterWsMoney;// 折后未税金额
	private Double zhTax;// 折后税额
	private Integer enterprise_id;
	private Boolean flag;
	private String sellCode;
	
	
	
	public String getInvoicePersonN() {
		return invoicePersonN;
	}

	public void setInvoicePersonN(String invoicePersonN) {
		this.invoicePersonN = invoicePersonN;
	}

	public String getPersonN() {
		return personN;
	}

	public void setPersonN(String personN) {
		this.personN = personN;
	}

	public String getXs_Car_Sel_Data2() {
		return xs_Car_Sel_Data2;
	}

	public void setXs_Car_Sel_Data2(String xsCarSelData2) {
		xs_Car_Sel_Data2 = xsCarSelData2;
	}

	public String getSellCode() {
		return sellCode;
	}

	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
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

	public Double getHsDiscount() {
		return hsDiscount;
	}

	public void setHsDiscount(Double hsDiscount) {
		this.hsDiscount = hsDiscount;
	}

	public Double getWsDiscount() {
		return wsDiscount;
	}

	public void setWsDiscount(Double wsDiscount) {
		this.wsDiscount = wsDiscount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getAfterHsDiscount() {
		return afterHsDiscount;
	}

	public void setAfterHsDiscount(Double afterHsDiscount) {
		this.afterHsDiscount = afterHsDiscount;
	}

	public Double getWsPurchase() {
		return wsPurchase;
	}

	public void setWsPurchase(Double wsPurchase) {
		this.wsPurchase = wsPurchase;
	}

	public Double getPurchase() {
		return purchase;
	}

	public void setPurchase(Double purchase) {
		this.purchase = purchase;
	}

	public Double getAfterWsMoney() {
		return afterWsMoney;
	}

	public void setAfterWsMoney(Double afterWsMoney) {
		this.afterWsMoney = afterWsMoney;
	}

	public Double getZhTax() {
		return zhTax;
	}

	public void setZhTax(Double zhTax) {
		this.zhTax = zhTax;
	}

	public Integer getXs_Car_Sel_Id() {
		return xs_Car_Sel_Id;
	}

	public void setXs_Car_Sel_Id(Integer xsCarSelId) {
		xs_Car_Sel_Id = xsCarSelId;
	}

	public Date getInstorehouse_Date() {
		return instorehouse_Date;
	}

	public void setInstorehouse_Date(Date instorehouseDate) {
		instorehouse_Date = instorehouseDate;
	}

	public String getOut_Id() {
		return out_Id;
	}

	public void setOut_Id(String outId) {
		out_Id = outId;
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

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String userName) {
		user_Name = userName;
	}

	public String getXs_Custom_Name() {
		return xs_Custom_Name;
	}

	public void setXs_Custom_Name(String xsCustomName) {
		xs_Custom_Name = xsCustomName;
	}

	public String getStf_Name() {
		return stf_Name;
	}

	public void setStf_Name(String stfName) {
		stf_Name = stfName;
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

	public String getXs_Car_Sel_Transaction_Money() {
		return xs_Car_Sel_Transaction_Money;
	}

	public void setXs_Car_Sel_Transaction_Money(String xsCarSelTransactionMoney) {
		xs_Car_Sel_Transaction_Money = xsCarSelTransactionMoney;
	}

	public String getXs_Distributor_Name() {
		return xs_Distributor_Name;
	}

	public void setXs_Distributor_Name(String xsDistributorName) {
		xs_Distributor_Name = xsDistributorName;
	}

	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
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

	public String getXs_Car_Give_Up() {
		return xs_Car_Give_Up;
	}

	public void setXs_Car_Give_Up(String xsCarGiveUp) {
		xs_Car_Give_Up = xsCarGiveUp;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Integer getInvoicePerson() {
		return invoicePerson;
	}

	public void setInvoicePerson(Integer invoicePerson) {
		this.invoicePerson = invoicePerson;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public Double getInvoiceParce() {
		return invoiceParce;
	}

	public void setInvoiceParce(Double invoiceParce) {
		this.invoiceParce = invoiceParce;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceRemark() {
		return invoiceRemark;
	}

	public void setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
	}

	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	public Integer getInvoiceExamin() {
		return invoiceExamin;
	}

	public void setInvoiceExamin(Integer invoiceExamin) {
		this.invoiceExamin = invoiceExamin;
	}

	public String getInvoiceExaminName() {
		return invoiceExaminName;
	}

	public void setInvoiceExaminName(String invoiceExaminName) {
		this.invoiceExaminName = invoiceExaminName;
	}

}
