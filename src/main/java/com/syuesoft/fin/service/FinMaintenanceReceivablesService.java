package com.syuesoft.fin.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;

/**
 * 维修应收款,索赔应收款,索赔应收款Service
 * 
 * @author SuMing
 */
public interface FinMaintenanceReceivablesService
{

	/** 维修应收款信息 预加载*/
    public Datagrid loadFinMaintenanceReceivables(
            MaintenanceReceivablesVo mrvo) throws Exception;

    /** 维修应收款信息 综合查询 */
    public List<MaintenanceReceivablesVo> searchFinMaintenanceReceivablesByCondition(
            MaintenanceReceivablesVo mrvo) throws Exception;

    /** 索赔应收款信息 预加载 */
    public Datagrid loadFinClaimsReceivables(
            MaintenanceReceivablesVo mevo) throws Exception;

    /** 索赔应收款信息 综合查询 */
    public List<MaintenanceReceivablesVo> searchFinClaimsReceivablesByCondition(
            MaintenanceReceivablesVo mrvo) throws Exception;

    /** 销售应收款信息 预加载 */
    public Datagrid loadStSellCharge(
            MaintenanceReceivablesVo mrvo) throws Exception;

   /** 销售应收款信息 综合查询 */
    public List<MaintenanceReceivablesVo> searchStSellChargeByCondition(
            MaintenanceReceivablesVo mrvo) throws Exception;

}
