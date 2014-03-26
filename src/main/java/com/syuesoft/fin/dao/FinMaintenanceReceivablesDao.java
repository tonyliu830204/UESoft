package com.syuesoft.fin.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;
import com.syuesoft.model.FinMaintenanceReceivables;

/**
 * 维修应收款Dao
 * 
 * @author SuMing
 */
public interface FinMaintenanceReceivablesDao extends
        BaseDaoI<FinMaintenanceReceivables>
{
	/**
     * 维修应收款信息 预加载
     */
    public Datagrid loadFinMaintenanceReceivables(
            int page, int rows,String startTime,String endTime,int enterpriseId) throws Exception;
            
    /** 维修应收款信息 综合查询 */
    public List<MaintenanceReceivablesVo> searchFinMaintenanceReceivablesByCondition(
            String preclrTimeStart, String preclrTimeEnd,
            String preclrSumAmount, String preFlg, String stfId,
            String resvRealTimeStart, String resvRealTimeEnd,
            String receptionId, String customName, String carLicense,
            String carVin, String carBrand) throws Exception;
}
