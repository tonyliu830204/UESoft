package com.syuesoft.frt.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;
import com.syuesoft.model.FrtPreClearing;

public interface FrtPreClearingDao extends BaseDaoI<FrtPreClearing>
{

    public void updateBatch(String sql) throws Exception;

    /** 财务模块 工单配件查询 工单结算信息 综合查询 */
    public List<WorkOrderPartsQueryVo> searchFrtPreClearingByCondition(
            String preclrDateStart, String preclrDateEnd, String receptionId,
            String carLicense) throws Exception;

}
