package com.syuesoft.fin.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;
import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.model.StSellCharge;

public interface StSellChargeDao extends BaseDaoI<StSellCharge>
{

    /** 加载销售应收款信息 */
    public Datagrid loadStSellCharge(int page, int rows,String startTime,String endTime,int enterprseId)
            throws Exception;

    /** 销售应收款信息 综合查询 */
    public List<MaintenanceReceivablesVo> searchStSellChargeByCondition(
            String sellmmDateStart, String sellmmDateEnd,
            String preclrSumAmount, String carBrand, String preclrDateStart,
            String preclrDateEnd, String cerNo, String customName,
            String carLicense, String carVin) throws Exception;

    /** 销售营收款汇总信息 预加载 */
    public List<StSellChargeVo> loadsStSellCharge(int page, int rows)
            throws Exception;

    /** 销售营收款汇总信息 综合查询 */
    public List<StSellChargeVo> searchStSellChargeByCondition(
            String verifyDate, String chargeId, String preclrId,
            String stockOut, String verifyState) throws Exception;
}
