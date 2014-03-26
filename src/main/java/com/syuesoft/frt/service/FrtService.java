package com.syuesoft.frt.service;

import java.util.List;

import com.syuesoft.model.FrtRushToRepair;
import com.syuesoft.util.ComboBoxJson;

public interface FrtService
{

    public FrtRushToRepair findrushToRepair(String resvId) throws Exception;// 查找抢修信息

    public void updaterushToRepair(FrtRushToRepair frtRushToRepair)
            throws Exception;// 更新抢修信息

    public Double findDefaultClaimsRate() throws Exception;// 查找默认索赔管理费率

    public String findDefaultClaimsCompany() throws Exception;// 查找默认索赔厂商
    
    public String findDefaultReceptionClew()throws Exception;//获取默认接车提醒参数
    
    public String findDefaultBalanceTimeSect()throws Exception;//查找默认收款查询时间段
    
    public String findDefaultPreclrTimeSect()throws Exception;//查找默认结算查询时间段
    
    public String findDefaultWorkShopVal()throws Exception;//查找默认车间完工检查参数
    
    public String getDefaultClaimsType(Integer enterpriseId)throws Exception;//查找默认索赔性质
    
    public String getDefaultClaimsTypeName(Integer enterpriseId)throws Exception;//查找默认索赔性质名称
    
    public String getDefaultRepairType(Integer enterpriseId)throws Exception;//查找默认收费性质
    
    public String getDefaultRepairTypeName(Integer enterpriseId)throws Exception;//查找默认维修性质名称
    
    public String getDefaultRcptBranch() throws Exception;//查找默认接车分部

    public String getDefaultMaintDistance() throws Exception;//查找默认保养公里数
    
    public String getDefaultMaintDays() throws Exception;//查找默认最大保养间隔天数
    
    public String getDefaultwhetherTax() throws Exception;//查找默认日经营情况查询成本查询是否含税
    
	public String getDefaultFirstmaintain() throws Exception;//指定维修类别首保项
	
	public String getDefaultBillPrictType(Integer enterpriseId) throws Exception;//查找预约估价单配件价格选择
	
	public String getDefaultFinishTimes() throws Exception;//查找接车单默认完工工时(分钟)
	
	public String getDefaultServiceRate() throws Exception;//查找维修管理(辅料费)费率
	
	public String getDefaultServiceWay() throws Exception;//查找维修管理(辅料费方式)
	
}
