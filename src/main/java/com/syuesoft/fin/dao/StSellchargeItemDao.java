package com.syuesoft.fin.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.model.StSellChargeitem;

/**
 * 销售单明细DAO接口
 * 
 * @author SuMing
 */
public interface StSellchargeItemDao extends BaseDaoI<StSellChargeitem>
{

    /** 销售应收款明细 预加载 */
    public List<StSellChargeVo> loadStSellchargeItemByChargeId(String chargeId)
            throws Exception;

}
