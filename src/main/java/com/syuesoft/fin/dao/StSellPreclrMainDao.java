package com.syuesoft.fin.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.fin.vo.StSellPreclrVo;
import com.syuesoft.model.StSellPreclrMain;

public interface StSellPreclrMainDao extends BaseDaoI<StSellPreclrMain>
{

    /** 销售结算单汇总信息 预加载 */
    public Datagrid loadStSellPreclrMain(int page, int rows,String start,String end,int enterpriseId)
            throws Exception;

    /** 销售结算单汇总信息 综合查询 */
    public List<StSellPreclrVo> searchStSellPreclrMainByCondition(
            String preclrDateStart, String preclrDateEnd, String preclrId,
            String customId, String classfication, String cerNo, String state)
            throws Exception;

    /** 销售应收款模块 结算单信息 预加载 */
    public List<StSellChargeVo> load_ssc_StSellreclr() throws Exception;
}
