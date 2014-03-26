package com.syuesoft.fin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.fin.service.PartsTrendsChangeSearchService;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;

import com.syuesoft.st.dao.StGoodsStorageDAO;
import com.syuesoft.st.dao.StOutMainDAO;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.util.Json;

/**
 * 配件动态变化查询ServiceImpl
 * 
 * @author SuMing
 */
public class PartsTrendsChangeSearchServiceImpl extends BaseServiceImpl implements PartsTrendsChangeSearchService
{

    @Autowired
    StOutMainDAO stOutMainDAO;

    @Autowired
    StSellOrderDAO stSellOrderDAO;

    @Autowired
    StGoodsStorageDAO stGoodsStorageDAO;

    /**
     * 财务模块 配件动态变化 出库退料配件信息 预加载
     */
    public List<PartsTrendsChangeSearchVo> loadStOutAndRtPartsInfo(PartsTrendsChangeSearchVo ptcsvo)
            throws Exception
    {
        return stOutMainDAO.loadStOutAndRtPartsInfo(ptcsvo);
    }

    /**
     * 财务模块 配件动态变化 出库退料配件信息 综合查询
     */
    public List<PartsTrendsChangeSearchVo> searchStOutAndRtPartsInfoByCondition(
            PartsTrendsChangeSearchVo ptcsvo) throws Exception
    {
        return stOutMainDAO.searchStOutAndRtPartsInfoByCondition(ptcsvo);
    }

    /**
     * 财务模块 配件动态变化 消退单配件信息 预加载
     */
    public List<PartsTrendsChangeSearchVo> loadSellPartsAndStRtParts()
            throws Exception
    {
        return stSellOrderDAO.loadSellPartsAndStRtParts();
    }

    /**
     * 财务模块 配件动态变化 消退单配件信息 综合查询
     */
    public List<PartsTrendsChangeSearchVo> searchSellPartsAndStRtPartsByCondition(
            PartsTrendsChangeSearchVo ptcsvo) throws Exception
    {
        return stSellOrderDAO.searchSellPartsAndStRtPartsByCondition(ptcsvo);
    }

    /**
     * 财务模块 配件动态变化 入库退货配件信息 预加载
     */
    public List<PartsTrendsChangeSearchVo> loadStStorageAndStRtGoods()
            throws Exception
    {
        return stGoodsStorageDAO.loadStStorageAndStRtGoods();
    }

    /**
     * 财务模块 配件动态变化 入库退货配件信息 综合查询
     */
    public List<PartsTrendsChangeSearchVo> searchStStorageAndStRtGoodsByCondition(
            PartsTrendsChangeSearchVo ptcsvo) throws Exception
    {
        return stGoodsStorageDAO.searchStStorageAndStRtGoodsByCondition(ptcsvo);
    }
    
    public Json loadPartsDynamicDate(PartsTrendsChangeSearchVo ptcsvo) throws Exception
    {
        return stGoodsStorageDAO.loadPartsDynamicDate(ptcsvo);
    }
}