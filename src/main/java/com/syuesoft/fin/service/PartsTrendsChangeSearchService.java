package com.syuesoft.fin.service;

import java.util.List;
import com.syuesoft.util.Json;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;

/**
 * 配件动态变化查询Service
 * 
 * @author SuMing
 */
public interface PartsTrendsChangeSearchService extends BaseService
{

    /** 财务模块 配件动态变化 出库退料配件信息 预加载 */
    public List<PartsTrendsChangeSearchVo> loadStOutAndRtPartsInfo(PartsTrendsChangeSearchVo ptcsvo) throws Exception;

    /** 财务模块 配件动态变化 出库退料配件信息 综合查询 */
    public List<PartsTrendsChangeSearchVo> searchStOutAndRtPartsInfoByCondition(
            PartsTrendsChangeSearchVo ptcsvo) throws Exception;

    /** 财务模块 配件动态变化 消退单配件信息 预加载 */
    public List<PartsTrendsChangeSearchVo> loadSellPartsAndStRtParts()
            throws Exception;

    /** 财务模块 配件动态变化 消退单配件信息 综合查询 */
    public List<PartsTrendsChangeSearchVo> searchSellPartsAndStRtPartsByCondition(
            PartsTrendsChangeSearchVo ptcsvo) throws Exception;

    /** 财务模块 配件动态变化 入库退货配件信息 预加载 */
    public List<PartsTrendsChangeSearchVo> loadStStorageAndStRtGoods()
            throws Exception;

    /** 财务模块 配件动态变化 入库退货配件信息 综合查询 */
    public List<PartsTrendsChangeSearchVo> searchStStorageAndStRtGoodsByCondition(
            PartsTrendsChangeSearchVo ptcsvo) throws Exception;

    /** 财务模块 配件动态变化 配件动态信息 综合查询 */
    public Json loadPartsDynamicDate(
            PartsTrendsChangeSearchVo ptcsvo) throws Exception;

}
