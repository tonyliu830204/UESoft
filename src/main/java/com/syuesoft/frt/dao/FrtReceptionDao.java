package com.syuesoft.frt.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.FrtReception;
import com.syuesoft.util.Json;

public interface FrtReceptionDao extends BaseDaoI<FrtReception>
{

    public void updateFrtReception(FrtReception fr) throws Exception;// 更新预约信息

    public void clear() throws Exception;

    public void flush() throws Exception;

    /** (出库单模块，预出库模块 工单退料模块 ) 信息综合查询 */
    public Json loadFrtReception(final int page, final int rows,
            final String sort, final String order, final String state,final int enterprise_id)
            throws Exception;

    /** (出库单模块，预出库模块 工单退料模块 ) 综合查询 */
    public Json searchFrtReceptionByCondition(final String receptionId,
            final String carLicense, final String customId, final String carVan,final int enterprise_id)
            throws Exception;
    
    /**前台接车接待员业务统计*/
    public Json loadSearchReceptorWork(FrtReceptionVo frtReceptionVo)throws Exception;
    
}
