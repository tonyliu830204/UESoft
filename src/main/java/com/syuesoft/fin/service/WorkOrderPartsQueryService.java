package com.syuesoft.fin.service;

import java.util.List;

import com.syuesoft.bas.service.BaseService;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;

public interface WorkOrderPartsQueryService extends BaseService
{

    /** 工单结算信息(按工单) */
    public Datagrid loadFrtPreClearing(WorkOrderPartsQueryVo workOrderPartsQueryVo) throws Exception;

    /** 工单结算信息-子项信息(按工单)*/
    public List<WorkOrderPartsQueryVo> loadStOutAndStRtPartsDetail(
            WorkOrderPartsQueryVo workOrderPartsQueryVo) throws Exception;
    
    /** 工单结算信息(按配件) */
    public Datagrid loadFrtPreClearingForParts(WorkOrderPartsQueryVo workOrderPartsQueryVo) throws Exception;

    /** 工单结算信息-子项信息(按配件)*/
    public List<WorkOrderPartsQueryVo> loadStOutAndStRtPartsDetailForParts(
            WorkOrderPartsQueryVo workOrderPartsQueryVo) throws Exception;
    
    /** 未确认索赔配件信息 预加载 */
    public Datagrid loadFinClaimantParts(WorkOrderPartsQueryVo workOrderPartsQueryVo) throws Exception;

}
