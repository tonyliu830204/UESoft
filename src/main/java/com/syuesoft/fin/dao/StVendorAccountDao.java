package com.syuesoft.fin.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.RelcampBalanceOfAccountVo;
import com.syuesoft.model.StVendorAccount;
import com.syuesoft.util.Json;

public interface StVendorAccountDao extends BaseDaoI<StVendorAccount>
{

    /** 供应商对账单信息加载 */
    public Json loadStVendorAccount(String relcampId)
            throws Exception;

    /** 供应商对账单汇总 预加载 */
    public List<RelcampBalanceOfAccountVo> loadStVendorAccountMain(
            String relcampId) throws Exception;

    /** 供应商未对账单汇总 预加载 */
    public Json loadNoPaidStVendorAccountMain(
            String relcampId,int enterpriseId) throws Exception;
}
