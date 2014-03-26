package com.syuesoft.fin.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;
import com.syuesoft.model.FinClaimsReceivables;

/**
 * 索赔应收款信息 预加载及综合查询dao
 * 
 * @author SuMing
 */
public interface FinClaimsReceivablesDao extends BaseDaoI<FinClaimsReceivables>
{

    /** 索赔应收款信息 预加载 */
    public Datagrid loadFinClaimsReceivables(int page,
            int rows,String startDate,String endDate,int enterpriseId) throws Exception;

    /** 索赔应收款信息 综合查询 */
    public List<MaintenanceReceivablesVo> searchFinClaimsReceivablesByCondition(
            String claimantmTimeStart, String claimantmTimeEnd,
            String claimantmAmount, String stfName, String resvRealTimeStart,
            String resvRealTimeEnd, String receptionId, String relcampName,
            String receLicense, String carVin, String cbrdName)
            throws Exception;

}
