package com.syuesoft.fin.service;

import java.util.List;

import com.syuesoft.bas.service.BaseService;
import com.syuesoft.fin.vo.RelcampBalanceOfAccountVo;
import com.syuesoft.util.Json;

public interface RelcampBalanceOfAccountService extends BaseService
{

    /** 点击选择供应商加载对应供应商对账汇总信息 */
    public RelcampBalanceOfAccountVo loadRelcampMain(
            RelcampBalanceOfAccountVo rboavo) throws Exception;

    /** 财务模块 供应商对账 入退单汇总信息加载 */
    public Json loadStRtGoods(
            RelcampBalanceOfAccountVo rboavo) throws Exception;

    /** 根据入退单号获取供应商应付账款及剩余账款 */
    public RelcampBalanceOfAccountVo searchByStVendorAccount(
            RelcampBalanceOfAccountVo rboavo) throws Exception;

    /** 供应商对账单添加 */
    public void add(RelcampBalanceOfAccountVo rboavo) throws Exception;

    /** 供应商对账单信息 预加载 */
    public Json loadStVendorAccount(
            RelcampBalanceOfAccountVo rboavo) throws Exception;

    /** 供应商对账单汇总 预加载 */
    public List<RelcampBalanceOfAccountVo> loadStVendorAccountMain(
            RelcampBalanceOfAccountVo rboavo) throws Exception;

    /** 供应商未对账汇总 预加载 */
    public Json loadNoPaidStVendorAccountMain(
            RelcampBalanceOfAccountVo rboavo) throws Exception;

    /** 供应商对账单信息 删除 */
    public void delete(RelcampBalanceOfAccountVo rboavo) throws Exception;
}
