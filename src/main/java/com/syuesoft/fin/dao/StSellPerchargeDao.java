package com.syuesoft.fin.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StSellPercharge;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Json;

public interface StSellPerchargeDao extends BaseDaoI<StSellPercharge>
{

    /** 维修储备金信息 预加载 */
    public Json loadStSellPercharge(int page, int rows,String beginDate,String endDate,int enterpriseId)
            throws Exception;

    /** 维修储备金信息 综合查询 */
    public Json searchStSellPerchargeByCondition(
            String perchargeDateBegin, String perchargeDateEnd,
            String carLicense, String vin,Integer enterpriseId) throws Exception;

    /** 维修预收款信息 预加载 */
    public Json loadPreStSellPercharge(int page, int rows,String perchargeDateBegin,String perchargeDateEnd,int enterpriseId)
            throws Exception;

    /** 维修预收款信息 综合查询 */
    public Json searchPreStSellPerchargeByCondition(
            String perchargeDateBegin, String perchargeDateEnd,
            String carLicense, String vin) throws Exception;

    /** 维修储备金 车辆信息 预加载 */
    public Json loadFrtCarInfo(int page, int rows,int enterprise_id)
            throws Exception;

    /** 维修储备金 车辆信息 综合查询 */
    public Json searchFrtCarInfoByCondition(String carLicense,int enterprise_id) throws Exception;

    /** 数据加载 */
    public List<ComboBoxJson> loadData(final String key) throws Exception;

    /** 根据储备金汇总编号获取储备金明细信息 */
    public Json findStSellPerchargeById(String rechargeId) throws Exception;

    /** 根据储备金汇总编号获取储备金明细信息 */
    public Json findPreStSellPerchargeById(String rechargeId) throws Exception;

    /** 储备金使用记录信息 预加载 */
    public Json loadStUsePercharge(int page, int rows,String start,String end,String rechargeId,int enterpriseId)
            throws Exception;

    /** 储备金使用记录信息 综合查询 */
    public Json serachStUsePerchargeByCondition(
            String repcollTimeStart, String repcollTimeEnd, String carLicense,
            String customName) throws Exception;

    /** 预收款使用记录信息 综合查询 */
    public Json serachPreStUsePerchargeByCondition(
            String repcollTimeStart, String repcollTimeEnd, String carLicense,
            String customName) throws Exception;

    /** 预收款使用记录使用记录信息 预加载 */
    public Json loadPreStUsePercharge(int page, int rows,String start,String end,String rechargeId,int enterpriseId)
            throws Exception;

    /** 判断车辆编号是否已存在于维修收款表中 **/
    public boolean isExist(String carId) throws Exception;

    
}
