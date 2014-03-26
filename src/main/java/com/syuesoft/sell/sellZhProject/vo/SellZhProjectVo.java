package com.syuesoft.sell.sellZhProject.vo;

import java.io.Serializable;
import java.util.Date;

import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.model.XsCarSellInfo;

public class SellZhProjectVo extends BaseBean implements Serializable  {
	private Boolean flag;
	private Integer zhid;
	private String zhProjectCode;
	private Integer xs_Car_Sel_Id ;
	private Integer xsCarId ;
	private  String sellCode;
	private Integer xs_Custom_Id;
	private Integer detailsId;

	private Integer zsprojectId;				//装潢项目
	private String projectName;
	private Integer unitNum;				//件数
	private Double projectCostamount;				//成本金额
	private Double projectSellamount;				//销售价格
	private Double preferentialPrice;		//优惠价格
	private Double decorateSell;			//装潢销售
	private Double decorateAmount;			//装潢成本
	private String remark;
	private String print;					//打印
	private Integer zhProjectPerson;//代办人
	private Integer zhRemark;

	private String stfName;
	private String zhProjectDate;
	private String zhProjectDate2;
	private String zhLists;
	private String zhFrom;
	private String iszhProject;//是否装潢
	private String inserted;
	private String deleted;
	private String updated;	
	private Integer projectId;
	private Double dbProjectCost;
	private Double costPrice;
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
	private String zhProjecRemark;//装潢备注
	private Integer audit;//审核
	private String auditName;
	private Integer zhProjectReckoning;//转结算
	private String reckoningName;
	private String sort;
	private String order;
	private int rows;
	private int page;
	private Integer states;
	private String accountCode;//结算单号  
	private String carCode;
	private String state;
	private String iconCls;
	private Integer enterpriseId;
	
	
	public String getZhProjectDate2() {
		return zhProjectDate2;
	}
	public void setZhProjectDate2(String zhProjectDate2) {
		this.zhProjectDate2 = zhProjectDate2;
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
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public Integer getZhRemark() {
		return zhRemark;
	}
	public void setZhRemark(Integer zhRemark) {
		this.zhRemark = zhRemark;
	}
	public Integer getXsCarId() {
		return xsCarId;
	}
	public void setXsCarId(Integer xsCarId) {
		this.xsCarId = xsCarId;
	}
	
	public String getReckoningName() {
		return reckoningName;
	}
	public void setReckoningName(String reckoningName) {
		this.reckoningName = reckoningName;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Integer getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}
	public Integer getXs_Custom_Id() {
		return xs_Custom_Id;
	}
	public void setXs_Custom_Id(Integer xsCustomId) {
		xs_Custom_Id = xsCustomId;
	}
	public String getSellCode() {
		return sellCode;
	}
	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public Integer getZhProjectReckoning() {
		return zhProjectReckoning;
	}
	public void setZhProjectReckoning(Integer zhProjectReckoning) {
		this.zhProjectReckoning = zhProjectReckoning;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Double getDbProjectCost() {
		return dbProjectCost;
	}
	public void setDbProjectCost(Double dbProjectCost) {
		this.dbProjectCost = dbProjectCost;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
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
	public String getZhProjectCode() {
		return zhProjectCode;
	}
	public void setZhProjectCode(String zhProjectCode) {
		this.zhProjectCode = zhProjectCode;
	}
	public String getIszhProject() {
		return iszhProject;
	}
	public void setIszhProject(String iszhProject) {
		this.iszhProject = iszhProject;
	}
	public String getZhProjecRemark() {
		return zhProjecRemark;
	}
	public void setZhProjecRemark(String zhProjecRemark) {
		this.zhProjecRemark = zhProjecRemark;
	}
	
	
	
	
	public String getZhLists() {
		return zhLists;
	}
	public void setZhLists(String zhLists) {
		this.zhLists = zhLists;
	}
	public String getZhFrom() {
		return zhFrom;
	}
	public void setZhFrom(String zhFrom) {
		this.zhFrom = zhFrom;
	}
	public String getStfName() {
		return stfName;
	}
	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
	
	
	public Integer getZhProjectPerson() {
		return zhProjectPerson;
	}
	public void setZhProjectPerson(Integer zhProjectPerson) {
		this.zhProjectPerson = zhProjectPerson;
	}
	public String getZhProjectDate() {
		return zhProjectDate;
	}
	public void setZhProjectDate(String zhProjectDate) {
		this.zhProjectDate = zhProjectDate;
	}

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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

	
	public Integer getZhid() {
		return zhid;
	}
	public void setZhid(Integer zhid) {
		this.zhid = zhid;
	}
	public Integer getXs_Car_Sel_Id() {
		return xs_Car_Sel_Id;
	}
	public void setXs_Car_Sel_Id(Integer xsCarSelId) {
		xs_Car_Sel_Id = xsCarSelId;
	}
	public Integer getZsprojectId() {
		return zsprojectId;
	}
	public void setZsprojectId(Integer zsprojectId) {
		this.zsprojectId = zsprojectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(Integer unitNum) {
		this.unitNum = unitNum;
	}

	public Double getProjectCostamount() {
		return projectCostamount;
	}
	public void setProjectCostamount(Double projectCostamount) {
		this.projectCostamount = projectCostamount;
	}
	public Double getProjectSellamount() {
		return projectSellamount;
	}
	public void setProjectSellamount(Double projectSellamount) {
		this.projectSellamount = projectSellamount;
	}
	public Double getPreferentialPrice() {
		return preferentialPrice;
	}
	public void setPreferentialPrice(Double preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}
	public Double getDecorateSell() {
		return decorateSell;
	}
	public void setDecorateSell(Double decorateSell) {
		this.decorateSell = decorateSell;
	}
	public Double getDecorateAmount() {
		return decorateAmount;
	}
	public void setDecorateAmount(Double decorateAmount) {
		this.decorateAmount = decorateAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}


}
