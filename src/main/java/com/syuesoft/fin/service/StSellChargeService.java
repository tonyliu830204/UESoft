package com.syuesoft.fin.service;

import java.util.List;

import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.model.StSellCharge;

public interface StSellChargeService
{

    /** 销售结算单信息 预加载 */
    public List<StSellChargeVo> loadStSellPreclr() throws Exception;

    /** 销售结算单明细信息 添加 */
    public void addStSellChargeItem(StSellChargeVo stSellChargeVo)
            throws Exception;

    /** 销售应收款信息删除 */
    public void deleteStSellCharge(StSellChargeVo stSellChargeVo)
            throws Exception;

    /** 销售应收款明细 预加载 */
    List<StSellChargeVo> loadStSellchargeItemByChargeId(
            StSellChargeVo stSellChargeVo) throws Exception;

    /** 销售营收款汇总信息 预加载 */
    public List<StSellChargeVo> loadStSellCharge(StSellChargeVo stSellChargeVo)
            throws Exception;

    /** 销售营收款汇总信息 综合查询 */
    public List<StSellChargeVo> searchStSellChargeByCondition(
            StSellChargeVo stSellChargeVo) throws Exception;

    /** 修改为审核 */
    public void examine(StSellChargeVo stSellChargeVo) throws Exception;

    /** 修改为未审核 */
    public void cancelExamine(StSellChargeVo stSellChargeVo) throws Exception;

    /** 取消付款 */
    public void deletePaid(StSellChargeVo stSellChargeVo) throws Exception;

    /** 获取销售应收款汇总已收款金额合计 */
    public StSellChargeVo searchStSellChargeByChargerId(
            StSellChargeVo stSellChargeVo) throws Exception;

}
