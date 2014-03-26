package com.syuesoft.frt.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtWorkOrderItemVo;
import com.syuesoft.frt.vo.FrtWorkOrderVo;
import com.syuesoft.frt.vo.frtWorkOrderSettlementSituationBalanceVo;
import com.syuesoft.util.ComboBoxJson;

public interface FrtWorkOrderService
{
    public Datagrid datagridFrtWorkOrderBase(FrtWorkOrderVo fwoVo)
            throws Exception; // 工单-基本信息

    public List datagridFrtWorkOrderBaseByReceptionId(FrtWorkOrderVo fwoVo)
            throws Exception;// 工单-基本信息-子项

    public Datagrid datagridFrtWorkOrderItem(FrtWorkOrderVo fwoVo)
            throws Exception; // 工单-维修项目

    public List datagridFrtWorkOrderItemByReceptionId(FrtWorkOrderVo fwoVo)
            throws Exception; // 工单-维修项目-子项

    public Datagrid datagridFrtWorkOrderParts(FrtWorkOrderVo fwoVo)
            throws Exception; // 工单-维修配件

    public List datagridFrtWorkOrderPartsByReceptionId(FrtWorkOrderVo fwoVo)
            throws Exception; // 工单-维修配件-子项

    public Datagrid datagridFrtWorkOrderPreClearingItem(FrtWorkOrderVo fwoVo)
            throws Exception; // 结算单-工时清单

    public List datagridFrtWorkOrderPreClearingItemByPreclrId(
            FrtWorkOrderVo fwoVo) throws Exception;// 结算单-工时清单-子项

    public Datagrid datagridFrtWorkOrderPreClearingParts(FrtWorkOrderVo fwoVo)
            throws Exception; // 结算单-材料清单

    public List datagridFrtWorkOrderPreClearingPartsByPreclrId(
            FrtWorkOrderVo fwoVo) throws Exception;// 结算单-材料清单-子项

    public Datagrid datagridFrtWorkOrderClaimParts(FrtWorkOrderVo fwoVo)
            throws Exception; // 索赔-配件查询

    public Datagrid datagridFrtWorkOrderRepaiStatisticalStatement(
            FrtWorkOrderVo fwoVo) throws Exception; // 维修业务统计报表

    public Datagrid datagridFrtWorkOrderclaimExwarehouse(FrtWorkOrderVo fwoVo)
            throws Exception; // 索赔出库查询

    public List datagridFrtWorkOrderclaimExwarehouseByStomId(
            FrtWorkOrderVo fwoVo) throws Exception; // 索赔出库-子项查询

    public Datagrid datagridFrtWorkOrderSettlementSituationItem(
            FrtWorkOrderVo fwoVo) throws Exception; // 工单-结算情况-工时清单

    public Datagrid datagridFrtWorkOrderSettlementSituationParts(
            FrtWorkOrderVo fwoVo) throws Exception; // 工单-结算情况-材料清单

    public frtWorkOrderSettlementSituationBalanceVo datagridFrtWorkOrderSettlementSituationBalance(
            FrtWorkOrderVo fwoVo) throws Exception; // 工单-结算情况-费用明细

}
