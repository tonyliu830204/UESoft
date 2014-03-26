package com.syuesoft.sell.sellwork.vo;

import java.util.Date;


public class SellDbProjectVo {
	private Integer sellid;
	private Integer projectId;
	private String projectName;
	private Double projectAmount;
	private Double projectMomay;
	private Integer  xs_Car_Sel_Id;				//车辆销售信息编号
	private Date instorehouse_Date;	 	//出库日期
	private String out_Id;						//出库单号
	private String reserve_Code;				//预定单编号
	private String xs_Car_Sel_Data;				//销售日期
	private String xs_Car_Sel_Data2;				//销售日期
	private String user_Name;	 			//经办人
	private String xs_Custom_Name;			
	private String stf_Name;	 				//业务员
	private String xs_Car_Vin_Number;			//vin
	private String xs_Car_Ocn;		
	private String xs_Car_Brand;			
	private String xs_Model_Name;	
	private String xs_Car_Sel_Transaction_Money;	//销售价格
	private String xs_Distributor_Name;	 	//分销商		
	private String examine;             	//审核
	private String limit_load_num;			//限乘人数	
	private String mobilephone;				//电话
	private String xs_Car_Give_Up;			//是否放弃购车
	private int page;
	private int rows;
	private String inserted;
	private String deleted;
	private String updated;
	private String sellProGrid;				//代办项目
	private String sellReserve;             //销售单汇总
	private Integer dbProjectPerson;        //经办人
	private String dbPersonName; 			//经办人姓名
	private Date dbProjectDate;          	//代办日期
	private String dbProjectCode;			//代办单号
	private Integer dbExamin;					//审核状态
	private String examinName;
	private String dbProjectRemark;			//收费部门及代办项目备注
	private String isdb_project;			//是否代办
	private String is_invoice;				//是否开票
	private Integer dbProjectReckoning;			//是否转结算
	private String queryProjectDate;		//代办日期(查询字段)
	private String queryProjectDate2;		//代办日期(查询字段)
	private String sellCode;
	private String accountCode;
	private Double db_project_cost;//代办金额
	private Double cost_price;//代办成本
	private Double chae;//差额
	private String state;
	private String iconCls;
	private Integer customId;
	private Integer enterpriseId;    		//企业编号
	private Boolean flag;    		
	private String xuanZ;
	private String dbProjectReckoningN;			//是否转结算
	
	public String getDbProjectReckoningN() {
		return dbProjectReckoningN;
	}
	public void setDbProjectReckoningN(String dbProjectReckoningN) {
		this.dbProjectReckoningN = dbProjectReckoningN;
	}
	public String getXuanZ() {
		return xuanZ;
	}
	public void setXuanZ(String xuanZ) {
		this.xuanZ = xuanZ;
	}
	public String getQueryProjectDate2() {
		return queryProjectDate2;
	}
	public void setQueryProjectDate2(String queryProjectDate2) {
		this.queryProjectDate2 = queryProjectDate2;
	}
	public String getXs_Car_Sel_Data2() {
		return xs_Car_Sel_Data2;
	}
	public void setXs_Car_Sel_Data2(String xsCarSelData2) {
		xs_Car_Sel_Data2 = xsCarSelData2;
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
	public Integer getCustomId() {
		return customId;
	}
	public void setCustomId(Integer customId) {
		this.customId = customId;
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
	
	public Double getDb_project_cost() {
		return db_project_cost;
	}
	public void setDb_project_cost(Double dbProjectCost) {
		db_project_cost = dbProjectCost;
	}
	public Double getCost_price() {
		return cost_price;
	}
	public void setCost_price(Double costPrice) {
		cost_price = costPrice;
	}
	public Double getChae() {
		return chae;
	}
	public void setChae(Double chae) {
		this.chae = chae;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getSellCode() {
		return sellCode;
	}
	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}
	
	public Integer getSellid() {
		return sellid;
	}
	public void setSellid(Integer sellid) {
		this.sellid = sellid;
	}
	
	public Double getProjectMomay() {
		return projectMomay;
	}
	public Double getProjectAmount() {
		return projectAmount;
	}
	public void setProjectAmount(Double projectAmount) {
		this.projectAmount = projectAmount;
	}
	public void setProjectMomay(Double projectMomay) {
		this.projectMomay = projectMomay;
	}
	public Integer getXs_Car_Sel_Id() {
		return xs_Car_Sel_Id;
	}
	public void setXs_Car_Sel_Id(Integer xsCarSelId) {
		xs_Car_Sel_Id = xsCarSelId;
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
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSellProGrid() {
		return sellProGrid;
	}
	public void setSellProGrid(String sellProGrid) {
		this.sellProGrid = sellProGrid;
	}
	public String getSellReserve() {
		return sellReserve;
	}
	public void setSellReserve(String sellReserve) {
		this.sellReserve = sellReserve;
	}
	public Integer getDbProjectPerson() {
		return dbProjectPerson;
	}
	public void setDbProjectPerson(Integer dbProjectPerson) {
		this.dbProjectPerson = dbProjectPerson;
	}
	public Date getDbProjectDate() {
		return dbProjectDate;
	}
	public void setDbProjectDate(Date dbProjectDate) {
		this.dbProjectDate = dbProjectDate;
	}
	public String getDbProjectCode() {
		return dbProjectCode;
	}
	public void setDbProjectCode(String dbProjectCode) {
		this.dbProjectCode = dbProjectCode;
	}

	public Integer getDbExamin() {
		return dbExamin;
	}
	public void setDbExamin(Integer dbExamin) {
		this.dbExamin = dbExamin;
	}
	public String getDbProjectRemark() {
		return dbProjectRemark;
	}
	public void setDbProjectRemark(String dbProjectRemark) {
		this.dbProjectRemark = dbProjectRemark;
	}
	public String getIsdb_project() {
		return isdb_project;
	}
	public void setIsdb_project(String isdbProject) {
		isdb_project = isdbProject;
	}
	public Date getInstorehouse_Date() {
		return instorehouse_Date;
	}
	public void setInstorehouse_Date(Date instorehouseDate) {
		instorehouse_Date = instorehouseDate;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String userName) {
		user_Name = userName;
	}
	public String getQueryProjectDate() {
		return queryProjectDate;
	}
	public void setQueryProjectDate(String queryProjectDate) {
		this.queryProjectDate = queryProjectDate;
	}
	public String getDbPersonName() {
		return dbPersonName;
	}
	public void setDbPersonName(String dbPersonName) {
		this.dbPersonName = dbPersonName;
	}
	public String getExaminName() {
		return examinName;
	}
	public void setExaminName(String examinName) {
		this.examinName = examinName;
	}
	public String getIs_invoice() {
		return is_invoice;
	}
	public void setIs_invoice(String isInvoice) {
		is_invoice = isInvoice;
	}
	public Integer getDbProjectReckoning() {
		return dbProjectReckoning;
	}
	public void setDbProjectReckoning(Integer dbProjectReckoning) {
		this.dbProjectReckoning = dbProjectReckoning;
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
	
	
}
