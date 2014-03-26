package com.syuesoft.frt.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.GatheringVo;
import com.syuesoft.util.Msg;

public interface GatheringService
{
    public Datagrid datagridGathering(GatheringVo gtVo) throws Exception;// 查找收款信息

    public Datagrid datagridNoGathering(GatheringVo gtVo) throws Exception;// 查找未收款信息

    public Datagrid datagridNoBatchGathering(GatheringVo gtVo) throws Exception;// 查找批量未收款信息

    public Msg savePreclearingGathering(GatheringVo gtVo) throws Exception;// 增加维修收款

    public Msg saveClaimantGathering(GatheringVo gtVo) throws Exception;// 增加索赔收款

    public Msg saveSellGathering(GatheringVo gtVo) throws Exception;// 增加销售收款

    public Msg savePreclearingBatchGathering(GatheringVo gtVo) throws Exception;// 增加维修批量收款

    public Msg saveClaimantBatchGathering(GatheringVo gtVo) throws Exception;// 增加索赔批量收款

    public Msg saveSellBatchGathering(GatheringVo gtVo) throws Exception;// 增加销售批量收款

    public GatheringVo datagridGatheringByPreclrId(GatheringVo gtVo)
            throws Exception;// 查找收款相关数据

    public GatheringVo datagridBatchGatheringByPreclrId(GatheringVo gtVo)
            throws Exception;// 查找批量收款相关数据

    public Datagrid datagridGatheringOld(GatheringVo gtVo) throws Exception;// 查找收款记录

    public Datagrid datagridBatchGatheringOld(GatheringVo gtVo)
            throws Exception;// 查找批量收款记录

    /*******************************/
    public Datagrid datagridSubstituteGathering(GatheringVo gtVo)
    		throws Exception;// 查找代付收款信息
    
    public Datagrid datagridSubstituteGatheringMain(GatheringVo gtVo)
            throws Exception;// 查找代付收款汇总信息
    
    public Datagrid datagridNoSubstituteGathering(GatheringVo gtVo)
            throws Exception;// 查找代付未收款信息
    
    public Datagrid datagridNoSubstituteGatheringMain(GatheringVo gtVo)
    		throws Exception;// 查找代付未收款汇总信息
    
    public Datagrid datagridNoSubstituteBatchGathering(GatheringVo gtVo)
            throws Exception;// 查找批量代付未收款信息

    public Msg savePreclearingSubstituteGathering(GatheringVo gtVo)
            throws Exception;// 增加代付维修收款

    public Msg saveClaimantSubstituteGathering(GatheringVo gtVo)
            throws Exception;// 增加代付索赔收款

    public Msg saveSellSubstituteGathering(GatheringVo gtVo) throws Exception;// 增加代付销售收款

    public Msg savePreclearingSubstituteGatheringAsBatch(GatheringVo gtVo)
            throws Exception;// 增加代付批量维修收款

    public Msg saveClaimantSubstituteGatheringAsBatch(GatheringVo gtVo)
            throws Exception;// 增加代付批量索赔收款

    public Msg saveSellSubstituteGatheringAsBatch(GatheringVo gtVo)
            throws Exception;// 增加代付批量销售收款

    public Msg savePreclearingBatchSubstituteGathering(GatheringVo gtVo,
            String type, Boolean flag) throws Exception;// 增加批量代付维修收款

    public Msg saveClaimantBatchSubstituteGathering(GatheringVo gtVo,
            String type, Boolean flag) throws Exception;// 增加批量代付索赔收款

    public Msg saveSellBatchSubstituteGathering(GatheringVo gtVo, String type,
            Boolean flag) throws Exception;// 增加批量代付销售收款

    public GatheringVo datagridSubstituteGatheringByPreclrId(GatheringVo gtVo)
            throws Exception;// 查找代付收款相关数据

    public GatheringVo datagridSubstituteGatheringBySspId(GatheringVo gtVo)
            throws Exception;// 查找代付批量收款相关数据

    public GatheringVo datagridBatchSubstituteGatheringBySspId(GatheringVo gtVo)
            throws Exception;// 查找批量代付收款相关数据

    public Datagrid datagridSubstituteGatheringOld(GatheringVo gtVo)
            throws Exception;// 查找代付收款记录

    public Datagrid datagridBatchSubstituteGatheringOld(GatheringVo gtVo)
            throws Exception;// 查找批量代付收款记录

    public Datagrid datagridBatchGatheringCollect(GatheringVo gtVo)
            throws Exception;// 查找批量收款汇总信息

    public Datagrid datagridBatchSubstituteGatheringCollect(GatheringVo gtVo)
            throws Exception;// 查找批量代付收款汇总信息

    public Datagrid datagridNoBatchSubstituteGathering(GatheringVo gtVo)
            throws Exception;// 查找批量代付未收款信息

    /*************************************/
    public Msg findIsClaims(GatheringVo gtVo) throws Exception;// 收银转结算
    
    public Msg modifyTransBalance(GatheringVo gtVo) throws Exception;// 收银转结算

    public GatheringVo findReceptionBeforeBalance(GatheringVo gtVo)
            throws Exception;// 查找维修预收款
}
