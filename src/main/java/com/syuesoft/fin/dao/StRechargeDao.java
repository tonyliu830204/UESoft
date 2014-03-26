package com.syuesoft.fin.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.StSellPerchargeVo;
import com.syuesoft.model.StRecharge;
import com.syuesoft.util.Json;

/**
 * 维修预收款余额Dao
 * @author SuMing
 */
public interface StRechargeDao extends BaseDaoI<StRecharge>
{

    /** 维修储备金余额信息    预加载 */
    public Json loadStRecharge() throws Exception;
    
    /** 维修储备金余额信息    综合查询*/
    public Json searchStRechargeByCondition(String carLicense, String customName) throws Exception;

    /** 维修预收款余额信息    预加载 */
    public Json loadPreStRecharge(StSellPerchargeVo sspvo) throws Exception;

    /** 维修预收款余额信息    综合查询 */
    public Json searchStPreRechargeByCondition(String carLicense, String customName) throws Exception;

    /** 判断车辆编号是否已存在于维修收款表中(不包含预收款)*/
    public boolean isExist(String carId) throws Exception;

    /** 判断车辆编号是否已存在于维修收款表中中(不包含储备金)*/
    public boolean isPreExist(String carId) throws Exception;

}
