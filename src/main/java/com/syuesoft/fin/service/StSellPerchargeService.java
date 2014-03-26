package com.syuesoft.fin.service;

import java.util.List;

import com.syuesoft.fin.vo.StSellPerchargeVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Json;

public interface StSellPerchargeService
{

    /** 维修储备金信息 预加载 */
    public Json loadStSellPercharge(StSellPerchargeVo sspvo)
            throws Exception;

    /** 维修储备金信息 综合查询 */
    public Json searchStSellPerchargeByCondition(
            StSellPerchargeVo sspvo) throws Exception;

    /** 维修预收款信息 预加载 */
    public Json loadPreStSellPercharge(
            StSellPerchargeVo sspvo) throws Exception;

    /** 维修预收款信息 综合查询 */
    public Json searchPreStSellPerchargeByCondition(
            StSellPerchargeVo sspvo) throws Exception;

    /** 维修储备金 车辆信息 预加载 */
    public Json loadFrtCarInfo(
            StSellPerchargeVo sspvo) throws Exception;

    /** 维修储备金 车辆信息 综合查询 */
    public Json searchFrtCarInfoByCondition(
            StSellPerchargeVo sspvo) throws Exception;

    /** 数据加载 */
    public List<ComboBoxJson> loadData(String parentId) throws Exception;

    /** 维修储备金信息 添加 */
    public void saveStSellPercharge(StSellPerchargeVo sspvo) throws Exception;

    /** 维修预收款信息 添加 */
    public void savePreStSellPercharge(StSellPerchargeVo sspVo)
            throws Exception;

    /** 维修储备金信息 删除 */
    public void deleteStSellPercharge(StSellPerchargeVo sspVo) throws Exception;

    /** 维修储备金信息 修改 */
    public void updateStSellPercharge(StSellPerchargeVo sspVo) throws Exception;

    /** 维修预收款信息 修改 */
    public void updatePreStSellPercharge(StSellPerchargeVo sspVo)
            throws Exception;

    /** 根据储备金汇总编号获取储备金明细信息 */
    public Json findStSellPerchargeById(StSellPerchargeVo sspvo) throws Exception;

    /** 根据预收款汇总编号获取预收款明细信息 */
    public Json findPreStSellPerchargeById(
            StSellPerchargeVo sspvo) throws Exception;

    /** 储备金使用记录信息      预加载 */
    public Json loadStUsePercharge(StSellPerchargeVo sspvo)
            throws Exception;

    /** 储备金使用记录信息     综合查询 */
    public Json serachStUsePerchargeByCondition(
            StSellPerchargeVo sspvo) throws Exception;

    /** 预收款使用记录信息    预加载 */
    public Json loadPreStUsePercharge(StSellPerchargeVo sspvo)
            throws Exception;

    /** 预收款使用记录信息    综合查询 */
    public Json serachPreStUsePerchargeByCondition(
            StSellPerchargeVo sspvo) throws Exception;

    /** 维修储备金余额信息 预加载 */
    public Json loadStRecharge() throws Exception;
    
    /**维修储备金余额信息 综合查询*/
    public Json searchStRechargeByCondition(StSellPerchargeVo sspvo) throws Exception;

    /** 维修预收款余额信息 预加载 */
    public Json loadPreStRecharge(StSellPerchargeVo sspvo) throws Exception;

    /** 维修预收款余额信息 综合查询 */
    public Json searchStPreRechargeByCondition(StSellPerchargeVo sspvo) throws Exception;
}
